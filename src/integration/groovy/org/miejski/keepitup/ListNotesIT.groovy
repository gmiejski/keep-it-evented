package org.miejski.keepitup

import org.miejski.keepit.api.NotesController
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
        def note1 = controller.createListNote(createNote1)
        def note2 = controller.createListNote(createNote2)

        when:
        def notes = controller.getListNotes().getBody()

        then:
        notes.notes.size() == 2
        notes.notes.find { it.id == note1.body.noteId }.items*.content as Set == createNote1.tasks as Set
        notes.notes.find { it.id == note2.body.noteId }.items*.content as Set == createNote2.tasks as Set
    }

    def "add list item to user's note"() {
        given:
        def note = controller.createListNote(createNote1)
        def addListItemCommand = new AddListItemCommand(note.body.noteId, "some rubbish content")

        when:
        controller.addListItem(addListItemCommand)

        then:
        def notes = controller.getListNotes().getBody()
        notes.notes.size() == 1
        notes.notes.first().items*.content as Set == createNote1.tasks + [addListItemCommand.content] as Set
    }
}
