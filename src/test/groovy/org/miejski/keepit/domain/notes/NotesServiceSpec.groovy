package org.miejski.keepit.domain.notes

import org.miejski.keepit.api.NoteDto
import org.miejski.keepit.domain.NoteType
import org.miejski.keepit.domain.notes.archive.ArchiveNoteCommand
import org.miejski.keepit.domain.notes.create.CreateNoteCommand
import spock.lang.Specification

class NotesServiceSpec extends Specification {

    def notesFacade = new NotesConfiguration().testNotesService()
    def createNoteCommand = new CreateNoteCommand("First note")
    def createNoteCommand2 = new CreateNoteCommand("Second note")
    def userID = UUID.randomUUID().toString()

    def "returns list of notes created by user"() {
        given:
        notesFacade.createNote(userID, createNoteCommand)
        notesFacade.createNote(userID, createNoteCommand2)

        when:
        def notes = notesFacade.getAll(userID, [] as Set)

        then:
        notes.notes*.content as Set == [createNoteCommand, createNoteCommand2]*.content as Set
    }

    def "returns list of standard notes only"() {
        given:
        def note1 = notesFacade.createNote(userID, createNoteCommand)
        def note2 = notesFacade.createNote(userID, createNoteCommand2)

        when:
        notesFacade.archiveNote(userID, new ArchiveNoteCommand(note1.id))

        then:
        def notes = notesFacade.getAll(userID, [NoteType.STANDARD] as Set)
        notes.notes*.content as Set == [createNoteCommand2]*.content as Set
    }

    def "Archived notes are not retrieved only on archived notes listing"() {
        given:
        def note1 = notesFacade.createNote(userID, createNoteCommand)
        def note2 = notesFacade.createNote(userID, createNoteCommand2)

        when:
        def noteArchievedResponse = notesFacade.archiveNote(userID, new ArchiveNoteCommand(note2.id))

        then:
        noteArchievedResponse.id == note2.id

        when: "retrieving archived notes"
        def archievedNotesResponse = notesFacade.getAll(userID, [NoteType.ARCHIVED] as Set)

        then: "archived notes are returned only"
        archievedNotesResponse.notes as Set == [new NoteDto(note2.id, note2.content)] as Set

        and: "when retrieving standard notes"
        def standardNotesResponse = notesFacade.getAll(userID, [NoteType.STANDARD] as Set)

        then: "only non-archived are returned"
        standardNotesResponse.notes as Set == [new NoteDto(note1.id, note1.content)] as Set
    }

}
