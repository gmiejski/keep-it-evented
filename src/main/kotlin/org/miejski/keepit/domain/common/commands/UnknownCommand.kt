package org.miejski.keepit.domain.common.commands

class UnknownCommand(command: Command) : RuntimeException("Unknown command ${command.javaClass.name}}")