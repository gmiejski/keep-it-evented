package org.miejski.keepit.domain

import org.miejski.keepit.domain.listNotes.create.CreateListNoteCommand
import org.miejski.keepit.domain.listNotes.items.AddListItemCommand
import spock.lang.Specification

class ListNotesFacadeSpec extends Specification {

    def notesFacade = new Configuration().testNotesFacade()

    def userID = UUID.randomUUID().toString()
    def createListNoteCommand = new CreateListNoteCommand(["item1", "item2"])
    def createListNoteCommand2 = new CreateListNoteCommand(["item3", "item4"])

    def "returns list of notes created by user"() {
        given:
        notesFacade.createListNote(userID, createListNoteCommand)
        notesFacade.createListNote(userID, createListNoteCommand2)

        when:
        def notes = notesFacade.getListNotes(userID)

        then:
        notes.notes.size() == 2
        notes.notes.first().items*.content as Set == createListNoteCommand.tasks as Set
        notes.notes.last().items*.content as Set == createListNoteCommand2.tasks as Set
    }

    def "add item to list note"() {
        given:
        def createdNote = notesFacade.createListNote(userID, createListNoteCommand)
        def addItemCommand = new AddListItemCommand(createdNote.id, "some new content")

        when:
        def listNote = notesFacade.addListItem(userID, addItemCommand)

        then:
        listNote.id == createdNote.id
        listNote.items*.content as Set == createListNoteCommand.tasks + [addItemCommand.content] as Set
        def notes = notesFacade.getListNotes(userID)
        notes.notes.size() == 1
        notes.notes.first().items*.content as Set == createListNoteCommand.tasks + [addItemCommand.content] as Set
    }
}
