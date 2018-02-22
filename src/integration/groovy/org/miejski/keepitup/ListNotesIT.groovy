package org.miejski.keepitup

import org.miejski.keepit.api.NotesController
import org.miejski.keepit.domain.listNotes.create.CreateListNoteCommand
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
        notes.notes.find {it.id == note1.body.noteId}.items*.content as Set == createNote1.tasks as Set
        notes.notes.find {it.id == note2.body.noteId}.items*.content as Set == createNote2.tasks as Set
    }
}
