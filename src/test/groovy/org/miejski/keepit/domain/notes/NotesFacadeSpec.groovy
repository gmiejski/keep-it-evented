package org.miejski.keepit.domain.notes

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
}
