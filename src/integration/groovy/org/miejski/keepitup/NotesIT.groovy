package org.miejski.keepitup

import org.miejski.keepit.api.NoteDto
import org.miejski.keepit.api.NotesController
import org.miejski.keepit.domain.notes.NoteType
import org.miejski.keepit.domain.notes.archive.ArchiveNoteCommand
import org.miejski.keepit.domain.notes.create.CreateNoteCommand
import org.miejski.keepit.domain.notes.edit.EditNoteCommand
import org.miejski.keepitup.configuration.MainTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus

class NotesIT extends MainTest {

    @Autowired
    NotesController controller
    private CreateNoteCommand createNote1 = new CreateNoteCommand("Some content")
    private CreateNoteCommand createNote2 = new CreateNoteCommand("Another note")

    def "returns list of text notes created by user"() {
        given:
        controller.createNote(createNote1)
        controller.createNote(createNote2)

        when:
        def notes = controller.getNotes([] as Set).getBody()

        then:
        notes.notes as Set == [new NoteDto("Some content"),
                               new NoteDto("Another note")] as Set

        when: "retrieve al standard notes"
        def standardNotes = controller.getNotes([NoteType.STANDARD] as Set).getBody()

        then:
        notes.notes as Set == standardNotes.notes as Set

    }

    def "updated note has new content and new last modification date"() {
        given:
        def noteCreatedResponse = controller.createNote(createNote1)

        when:
        def editResponse = controller.editNote(new EditNoteCommand(noteCreatedResponse.body.noteId, "New content"))

        then:
        editResponse.statusCode == HttpStatus.OK
        def notes = controller.getNotes([] as Set).getBody()
        notes.notes as Set == [new NoteDto("New content")] as Set
    }

    def "Archived notes are not retrieved only on archived notes listing"() {
        given:
        def note1 = controller.createNote(createNote1).body
        def note2 = controller.createNote(createNote2).body

        when:
        def noteArchievedResponse = controller.archiveNote(new ArchiveNoteCommand(note2.noteId))

        then:
        noteArchievedResponse.statusCode == HttpStatus.OK
        noteArchievedResponse.body.noteId == note2.noteId

        when: "retrieving archived notes"
        def archievedNotesResponse = controller.getNotes([NoteType.ARCHIVED] as Set)

        then: "archived notes are returned only"
        archievedNotesResponse.statusCode == HttpStatus.OK
        archievedNotesResponse.body.notes as Set == [new NoteDto(createNote2.content)] as Set

        and: "when retrieving standard notes"
        def standardNotesResponse = controller.getNotes([NoteType.STANDARD] as Set)

        then: "only non-archived are returned"
        standardNotesResponse.statusCode == HttpStatus.OK
        standardNotesResponse.body.notes as Set == [new NoteDto(createNote1.content)] as Set
    }
}
