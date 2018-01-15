package org.miejski.keepit.domain.notes

import org.miejski.keepit.domain.notes.commands.CreateNoteCommand
import spock.lang.Specification

class NotesFacadeSpec extends Specification {

    def notesFacade = new NotesFacade()
    def createNoteCommand = new CreateNoteCommand("First note")
    def createNoteCommand2 = new CreateNoteCommand("Second note")

    def "Returns list of text notes created by user"() {
        given:
        notesFacade.createNote(createNoteCommand)
        notesFacade.createNote(createNoteCommand2)

        when:
        def notes = notesFacade.getNotes()

        then:
        notes.notes*.content as Set == [createNoteCommand, createNoteCommand2]*.content as Set
    }
}
