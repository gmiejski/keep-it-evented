package org.miejski.keepit.domain

import org.miejski.keepit.domain.listNotes.create.CreateListNoteCommand
import spock.lang.Specification

class ListNotesFacadeSpec extends Specification {
    def notesFacade = new Configuration().testNotesFacade()
    def createListNoteCommand = new CreateListNoteCommand(["item1", "item2"])
    def createListNoteCommand2 = new CreateListNoteCommand(["item3", "item4"])
    def customerId = UUID.randomUUID().toString()

    def "returns list of notes created by user"() {
        given:
        notesFacade.createListNote(customerId, createListNoteCommand)
        notesFacade.createListNote(customerId, createListNoteCommand2)

        when:
        def notes = notesFacade.getListNotes(customerId)

        then:
        notes.notes.size() == 2
        notes.notes.first().items*.content as Set == createListNoteCommand.tasks as Set
        notes.notes.last().items*.content as Set == createListNoteCommand2.tasks as Set
    }
}
