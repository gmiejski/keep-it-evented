package org.miejski.keepit.domain.notes

class NoteNotFound(user: String, noteID: String) : RuntimeException("Note not found: $noteID for user: $user")