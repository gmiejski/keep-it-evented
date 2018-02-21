package org.miejski.keepit.api

import org.miejski.keepit.domain.NoteType
import org.miejski.keepit.domain.notes.NotesFacade
import org.miejski.keepit.domain.notes.archive.ArchiveNoteCommand
import org.miejski.keepit.domain.notes.create.CreateNoteCommand
import org.miejski.keepit.domain.notes.edit.EditNoteCommand
import org.miejski.keepit.domain.listNotes.create.CreateListNoteCommand
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*


@RestController
@Service
class NotesController(val notesFacade: NotesFacade) {

    val activeUserId = "onlyLoggedInUser"

    @RequestMapping(value = ["/notes"], method = arrayOf(RequestMethod.GET))
    fun getNotes(@RequestParam("filter", required = false) filters: Set<NoteType> = setOf()): ResponseEntity<NotesDto> {
        return ResponseEntity.ok(notesFacade.getNotes(activeUserId, filters))
    }

    @RequestMapping(value = ["/notes"], method = arrayOf(RequestMethod.POST))
    fun createNote(@RequestBody createNoteCommand: CreateNoteCommand): ResponseEntity<NoteCreatedResponse> {
        val noteCreated = notesFacade.createNote(activeUserId, createNoteCommand)
        return ResponseEntity.ok(NoteCreatedResponse(noteCreated.ID()))
    }

    @RequestMapping(value = ["/listNotes"], method = arrayOf(RequestMethod.POST))
    fun createListNote(@RequestBody createNoteCommand: CreateListNoteCommand): ResponseEntity<NoteCreatedResponse> {
        val noteCreated = notesFacade.createListNote(activeUserId, createNoteCommand)
        return ResponseEntity.ok(NoteCreatedResponse(noteCreated.ID()))
    }

    @RequestMapping(value = ["/notes/{id}/update"], method = arrayOf(RequestMethod.POST))
    fun editNote(@RequestBody editNoteCommand: EditNoteCommand): ResponseEntity<NoteEditedResponse> {
        val noteCreated = notesFacade.editNote(activeUserId, editNoteCommand)
        return ResponseEntity.ok(NoteEditedResponse(noteCreated.ID()))
    }

    @RequestMapping(value = ["/notes/{id}/archive"], method = arrayOf(RequestMethod.POST))
    fun archiveNote(@RequestBody archiveNoteCommand: ArchiveNoteCommand): ResponseEntity<NoteArchivedResponse> {
        val noteArchived = notesFacade.archiveNote(activeUserId, archiveNoteCommand)
        return ResponseEntity.ok(NoteArchivedResponse(noteArchived.ID()))
    }

}