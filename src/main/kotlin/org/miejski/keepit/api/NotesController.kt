package org.miejski.keepit.api

import org.miejski.keepit.domain.NoteType
import org.miejski.keepit.domain.NotesFacade
import org.miejski.keepit.domain.listNotes.ListNotesDTO
import org.miejski.keepit.domain.listNotes.complete.CompleteItemCommand
import org.miejski.keepit.domain.listNotes.create.CreateListNoteCommand
import org.miejski.keepit.domain.listNotes.items.AddListItemCommand
import org.miejski.keepit.domain.notes.archive.ArchiveNoteCommand
import org.miejski.keepit.domain.notes.create.CreateNoteCommand
import org.miejski.keepit.domain.notes.edit.EditNoteCommand
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
        return ResponseEntity.ok(NoteCreatedResponse(noteCreated.id))
    }

    @RequestMapping(value = ["/notes/{id}/update"], method = arrayOf(RequestMethod.POST))
    fun editNote(@RequestBody editNoteCommand: EditNoteCommand): ResponseEntity<NoteEditedResponse> {
        val noteCreated = notesFacade.editNote(activeUserId, editNoteCommand)
        return ResponseEntity.ok(NoteEditedResponse(noteCreated.id))
    }

    @RequestMapping(value = ["/notes/{id}/archive"], method = arrayOf(RequestMethod.POST))
    fun archiveNote(@RequestBody archiveNoteCommand: ArchiveNoteCommand): ResponseEntity<NoteArchivedResponse> {
        val noteArchived = notesFacade.archiveNote(activeUserId, archiveNoteCommand)
        return ResponseEntity.ok(NoteArchivedResponse(noteArchived.id))
    }

    @RequestMapping(value = ["/listNotes"], method = arrayOf(RequestMethod.POST))
    fun createListNote(@RequestBody createNoteCommand: CreateListNoteCommand): ResponseEntity<NoteCreatedResponse> {
        val noteCreated = notesFacade.createListNote(activeUserId, createNoteCommand)
        return ResponseEntity.ok(NoteCreatedResponse(noteCreated.id))
    }

    @RequestMapping(value = ["/listNotes"], method = arrayOf(RequestMethod.GET))
    fun getListNotes(): ResponseEntity<ListNotesDTO> {
        return ResponseEntity.ok(notesFacade.getListNotes(activeUserId))
    }

    @RequestMapping(value = ["/listNotes/{id}"], method = arrayOf(RequestMethod.POST))
    fun addListItem(@RequestBody addListItemCommand: AddListItemCommand): ResponseEntity<Void> {
        notesFacade.addListItem(activeUserId, addListItemCommand)
        return ResponseEntity.ok().build()
    }

    @RequestMapping(value = ["/listNotes/{id}/items/{itemId}"], method = arrayOf(RequestMethod.POST))
    fun completeItem(@RequestBody completeItemCommand: CompleteItemCommand): ResponseEntity<Void> {
        notesFacade.completeItem(activeUserId, completeItemCommand)
        return ResponseEntity.ok().build()
    }


}