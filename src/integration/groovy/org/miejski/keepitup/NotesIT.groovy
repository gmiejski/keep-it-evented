package org.miejski.keepitup

import org.miejski.keepit.api.NoteDto
import org.miejski.keepit.api.NotesController
import org.miejski.keepit.domain.notes.commands.CreateNoteCommand
import org.miejski.keepit.domain.notes.commands.EditNoteCommand
import org.miejski.keepitup.configuration.MainTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus

class NotesIT extends MainTest {

    @Autowired
    NotesController controller

    def "returns list of text notes created by user"() {
        given:
        controller.createNote(new CreateNoteCommand("Some content"))
        controller.createNote(new CreateNoteCommand("Another note"))

        when:
        def notes = controller.getNotes().getBody()

        then:
        notes.getNotes() as Set == [new NoteDto("Some content"),
                                    new NoteDto("Another note")] as Set
    }

    def "updated note has new content and new last modification date"() {
        given:
        def noteCreatedResponse = controller.createNote(new CreateNoteCommand("Some content"))

        when:
        def editResponse = controller.editNote(new EditNoteCommand(noteCreatedResponse.body.noteId, "New content"))

        then:
        editResponse.statusCode == HttpStatus.OK
        def notes = controller.getNotes().getBody()
        notes.getNotes() as Set == [new NoteDto("New content")] as Set
    }
}
