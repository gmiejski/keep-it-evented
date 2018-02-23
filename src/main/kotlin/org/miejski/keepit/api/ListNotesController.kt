package org.miejski.keepit.api

import org.miejski.keepit.domain.listNotes.ListNotesDTO
import org.miejski.keepit.domain.listNotes.ListNotesService
import org.miejski.keepit.domain.listNotes.complete.CompleteItemCommand
import org.miejski.keepit.domain.listNotes.create.CreateListNoteCommand
import org.miejski.keepit.domain.listNotes.items.AddListItemCommand
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController


@RestController
@Service
class ListNotesController(val listNotesService: ListNotesService) {

    val activeUserId = "onlyLoggedInUser"

    @RequestMapping(value = ["/listNotes"], method = arrayOf(RequestMethod.POST))
    fun createListNote(@RequestBody createNoteCommand: CreateListNoteCommand): ResponseEntity<NoteCreatedResponse> {
        val noteCreated = listNotesService.createListNote(activeUserId, createNoteCommand)
        return ResponseEntity.ok(NoteCreatedResponse(noteCreated.id))
    }

    @RequestMapping(value = ["/listNotes"], method = arrayOf(RequestMethod.GET))
    fun getListNotes(): ResponseEntity<ListNotesDTO> {
        return ResponseEntity.ok(listNotesService.getAll(activeUserId))
    }

    @RequestMapping(value = ["/listNotes/{id}"], method = arrayOf(RequestMethod.POST))
    fun addListItem(@RequestBody addListItemCommand: AddListItemCommand): ResponseEntity<Void> {
        listNotesService.addListItem(activeUserId, addListItemCommand)
        return ResponseEntity.ok().build()
    }

    @RequestMapping(value = ["/listNotes/{id}/items/{itemId}"], method = arrayOf(RequestMethod.POST))
    fun completeItem(@RequestBody completeItemCommand: CompleteItemCommand): ResponseEntity<Void> {
        listNotesService.completeItem(activeUserId, completeItemCommand)
        return ResponseEntity.ok().build()
    }
}