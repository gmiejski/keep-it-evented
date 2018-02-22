package org.miejski.keepitup

import org.miejski.keepit.api.NotesController
import org.miejski.keepit.domain.listNotes.complete.CompleteItemCommand
import org.miejski.keepit.domain.listNotes.create.CreateListNoteCommand
import org.miejski.keepit.domain.listNotes.items.AddListItemCommand
import org.miejski.keepitup.configuration.MainTest
import org.springframework.beans.factory.annotation.Autowired

class ListNotesIT extends MainTest {

    @Autowired
    NotesController controller
    private CreateListNoteCommand createNote1 = new CreateListNoteCommand(["first", "second"])
    private CreateListNoteCommand createNote2 = new CreateListNoteCommand(["item3", "item4"])

    def "returns user's list notes"() {
        given:
        def note1 = controller.createListNote(createNote1).body
        def note2 = controller.createListNote(createNote2).body

        when:
        def notes = controller.getListNotes().body

        then:
        notes.notes.size() == 2
        notes.getNote(note1.noteId).items*.content as Set == createNote1.tasks as Set
        notes.getNote(note2.noteId).items*.content as Set == createNote2.tasks as Set
    }

    def "add list item to user's note"() {
        given:
        def note = controller.createListNote(createNote1).body
        def addListItemCommand = new AddListItemCommand(note.noteId, "some rubbish content")

        when:
        controller.addListItem(addListItemCommand)

        then:
        def notes = controller.getListNotes().body
        notes.notes.size() == 1
        notes.getNote(note.noteId).items*.content as Set == createNote1.tasks + [addListItemCommand.content] as Set
    }

    def "marking note item as completed"() {
        given:
        def note = controller.createListNote(createNote1).body
        def completedItemID = controller.getListNotes().body.getNote(note.noteId).items.first().id
        def completeCommand = new CompleteItemCommand(note.noteId, completedItemID)

        when:
        controller.completeItem(completeCommand)

        then:
        def notes = controller.getListNotes().body
        notes.notes.size() == 1
        notes.getNote(note.noteId)?.getItem(completedItemID)?.isCompleted
    }
}
