package org.miejski.keepit.domain.listNotes

import org.miejski.keepit.domain.listNotes.complete.CompleteItemCommand
import org.miejski.keepit.domain.listNotes.create.CreateListNoteCommand
import org.miejski.keepit.domain.listNotes.items.AddListItemCommand
import org.miejski.keepit.infrastructure.eventstore.InMemoryEventStore
import spock.lang.Specification

class ListNotesServiceSpec extends Specification {
    def listNotesService = new ListNotesServiceConfiguration().testListNotesService(new InMemoryEventStore())

    def userID = UUID.randomUUID().toString()
    def createListNoteCommand = new CreateListNoteCommand(["item1", "item2"])
    def createListNoteCommand2 = new CreateListNoteCommand(["item3", "item4"])

    def "returns list of notes created by user"() {
        given:
        listNotesService.createListNote(userID, createListNoteCommand)
        listNotesService.createListNote(userID, createListNoteCommand2)

        when:
        def notes = listNotesService.getAll(userID)

        then:
        notes.notes.size() == 2
        notes.notes.first().items*.content as Set == createListNoteCommand.tasks as Set
        notes.notes.last().items*.content as Set == createListNoteCommand2.tasks as Set
    }

    def "add item to list note"() {
        given:
        def createdNote = listNotesService.createListNote(userID, createListNoteCommand)
        def addItemCommand = new AddListItemCommand(createdNote.id, "some new content")

        when:
        def listNote = listNotesService.addListItem(userID, addItemCommand)

        then:
        listNote.id == createdNote.id
        listNote.items*.content as Set == createListNoteCommand.tasks + [addItemCommand.content] as Set
        def notes = listNotesService.getAll(userID)
        notes.notes.size() == 1
        notes.notes.first().items*.content as Set == createListNoteCommand.tasks + [addItemCommand.content] as Set
    }

    def "marking note item as completed"() {
        given:
        def note = listNotesService.createListNote(userID, createListNoteCommand)
        def completeCommand = new CompleteItemCommand(note.id, note.items.first().id)
        when:
        listNotesService.completeItem(userID, completeCommand)

        then:
        def notes = listNotesService.getAll(userID)
        notes.notes.size() == 1
        notes.getNote(note.id)?.getItem(note.items.first().id)?.isCompleted
    }
}
