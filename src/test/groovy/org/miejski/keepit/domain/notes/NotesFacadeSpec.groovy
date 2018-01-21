package org.miejski.keepit.domain.notes

import org.miejski.keepit.domain.notes.commands.CreateNoteCommand
import spock.lang.Specification

class NotesFacadeSpec extends Specification {

    def notesFacade = Configuration.testNotesFacade()
    def createNoteCommand = new CreateNoteCommand("First note")
    def createNoteCommand2 = new CreateNoteCommand("Second note")
    def customerId = UUID.randomUUID().toString()

    def "Returns list of text notes created by user"() {
        given:
        notesFacade.createNote(customerId, createNoteCommand)
        notesFacade.createNote(customerId, createNoteCommand2)

        when:
        def notes = notesFacade.getNotes(customerId)

        then:
        notes.notes*.content as Set == [createNoteCommand, createNoteCommand2]*.content as Set
    }
}
