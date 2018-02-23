package org.miejski.keepit.api

import org.miejski.keepit.domain.NoteType
import org.miejski.keepit.domain.notes.NotesService
import org.miejski.keepit.domain.notes.archive.ArchiveNoteCommand
import org.miejski.keepit.domain.notes.create.CreateNoteCommand
import org.miejski.keepit.domain.notes.edit.EditNoteCommand
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*


@RestController
@Service
class NotesController(val notesService: NotesService) {

    val activeUserId = "onlyLoggedInUser"

    @RequestMapping(value = ["/notes"], method = arrayOf(RequestMethod.GET))
    fun getNotes(@RequestParam("filter", required = false) filters: Set<NoteType> = setOf()): ResponseEntity<NotesDto> {
        return ResponseEntity.ok(notesService.getAll(activeUserId, filters))
    }

    @RequestMapping(value = ["/notes"], method = arrayOf(RequestMethod.POST))
    fun createNote(@RequestBody createNoteCommand: CreateNoteCommand): ResponseEntity<NoteCreatedResponse> {
        val noteCreated = notesService.createNote(activeUserId, createNoteCommand)
        return ResponseEntity.ok(NoteCreatedResponse(noteCreated.id))
    }

    @RequestMapping(value = ["/notes/{id}/update"], method = arrayOf(RequestMethod.POST))
    fun editNote(@RequestBody editNoteCommand: EditNoteCommand): ResponseEntity<NoteEditedResponse> {
        val noteCreated = notesService.editNote(activeUserId, editNoteCommand)
        return ResponseEntity.ok(NoteEditedResponse(noteCreated.id))
    }

    @RequestMapping(value = ["/notes/{id}/archive"], method = arrayOf(RequestMethod.POST))
    fun archiveNote(@RequestBody archiveNoteCommand: ArchiveNoteCommand): ResponseEntity<NoteArchivedResponse> {
        val noteArchived = notesService.archiveNote(activeUserId, archiveNoteCommand)
        return ResponseEntity.ok(NoteArchivedResponse(noteArchived.id))
    }
}