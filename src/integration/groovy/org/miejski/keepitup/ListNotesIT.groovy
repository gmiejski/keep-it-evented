package org.miejski.keepitup

import org.miejski.keepit.api.NotesController
import org.miejski.keepit.domain.listNotes.create.CreateListNoteCommand
import org.miejski.keepitup.configuration.MainTest
import org.springframework.beans.factory.annotation.Autowired

class ListNotesIT extends MainTest {

    @Autowired
    NotesController controller
    private CreateListNoteCommand createNote1 = new CreateListNoteCommand(["first", "second"])

}
