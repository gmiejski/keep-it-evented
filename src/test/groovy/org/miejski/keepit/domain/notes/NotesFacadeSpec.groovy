package org.miejski.keepit.domain.notes

import org.miejski.keepit.api.NoteDto
import org.miejski.keepit.domain.Configuration
import org.miejski.keepit.domain.NoteType
import org.miejski.keepit.domain.notes.archive.ArchiveNoteCommand
import org.miejski.keepit.domain.notes.create.CreateNoteCommand
import spock.lang.Specification

class NotesFacadeSpec extends Specification {

    def notesFacade = new Configuration().testNotesFacade()
    def createNoteCommand = new CreateNoteCommand("First note")
    def createNoteCommand2 = new CreateNoteCommand("Second note")
    def customerId = UUID.randomUUID().toString()

    def "returns list of text notes created by user"() {
        given:
        notesFacade.createNote(customerId, createNoteCommand)
        notesFacade.createNote(customerId, createNoteCommand2)

        when:
        def notes = notesFacade.getNotes(customerId, [] as Set)

        then:
        notes.notes*.content as Set == [createNoteCommand, createNoteCommand2]*.content as Set
    }

    def "returns list of standard notes only"() {
        given:
        def note1 = notesFacade.createNote(customerId, createNoteCommand)
        def note2 = notesFacade.createNote(customerId, createNoteCommand2)

        when:
        notesFacade.archiveNote(customerId, new ArchiveNoteCommand(note1.ID()))

        then:
        def notes = notesFacade.getNotes(customerId, [NoteType.STANDARD] as Set)
        notes.notes*.content as Set == [createNoteCommand2]*.content as Set
    }

    def "Archived notes are not retrieved only on archived notes listing"() {
        given:
        def note1 = notesFacade.createNote(customerId, createNoteCommand)
        def note2 = notesFacade.createNote(customerId, createNoteCommand2)

        when:
        def noteArchievedResponse = notesFacade.archiveNote(customerId, new ArchiveNoteCommand(note2.ID()))

        then:
        noteArchievedResponse.ID() == note2.ID()

        when: "retrieving archived notes"
        def archievedNotesResponse = notesFacade.getNotes(customerId, [NoteType.ARCHIVED] as Set)

        then: "archived notes are returned only"
        archievedNotesResponse.notes as Set == [new NoteDto(note2.content)] as Set

        and: "when retrieving standard notes"
        def standardNotesResponse = notesFacade.getNotes(customerId, [NoteType.STANDARD] as Set)

        then: "only non-archived are returned"
        standardNotesResponse.notes as Set == [new NoteDto(note1.content)] as Set
    }

}
