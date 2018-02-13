package org.miejski.keepit.api

import org.miejski.keepit.domain.notes.NotesFacade
import org.miejski.keepit.domain.notes.commands.CreateNoteCommand
import org.miejski.keepit.domain.notes.commands.EditNoteCommand
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController


@RestController
@Service
class NotesController(val facade: NotesFacade) {

    val activeUserId = "onlyLoggedInUser"

    @RequestMapping(value = ["/notes"], method = arrayOf(RequestMethod.GET))
    fun getNotes(): ResponseEntity<NotesDto> {
        return ResponseEntity.ok(facade.getNotes(activeUserId))
    }

    @RequestMapping(value = ["/notes/{id}/update"], method = arrayOf(RequestMethod.POST))
    fun editNote(@RequestBody editNoteCommand: EditNoteCommand): ResponseEntity<NoteEditedResponse> {
        val noteCreated = facade.editNote(activeUserId, editNoteCommand)
        return ResponseEntity.ok(NoteEditedResponse(noteCreated.ID()))
    }


    @RequestMapping(value = ["/notes"], method = arrayOf(RequestMethod.POST))
    fun createNote(@RequestBody createNoteCommand: CreateNoteCommand): ResponseEntity<NoteCreatedResponse> {
        val noteCreated = facade.createNote(activeUserId, createNoteCommand)
        return ResponseEntity.ok(NoteCreatedResponse(noteCreated.ID()))
    }

}