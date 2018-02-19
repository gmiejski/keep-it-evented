package org.miejski.keepit.domain.notes

class NoteNotFound(val user: String, val noteID: String) : RuntimeException("Note not found: $noteID for user: $user")