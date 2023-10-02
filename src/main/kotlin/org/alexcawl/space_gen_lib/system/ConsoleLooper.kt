package org.alexcawl.space_gen_lib.system

import kotlin.concurrent.thread

class ConsoleLooper(
    private val handler: Handler
) : Looper {
    private val queue: MessageQueue = ApplicationMessageQueue()

    override fun prepare() {
        thread {
            while (true) {
                val input: String = readln()
                when {
                    input.startsWith("ap") -> {
                        val tokens = input.split(" ")
                        queue.addMessage(
                            ApplicationMessage.AddPlanet(
                                tokens[1],
                                tokens[2].toDouble(),
                                tokens[3].toDouble(),
                                tokens[4].toDouble(),
                                tokens[5].toLong()
                            )
                        )
                    }

                    input.startsWith("as") -> {
                        val tokens = input.split(" ")
                        queue.addMessage(
                            ApplicationMessage.AddStar(
                                tokens[1],
                                tokens[2],
                                tokens[3].toDouble(),
                                tokens[4].toDouble()
                            )
                        )
                    }

                    input.startsWith("d") -> {
                        val tokens = input.split(" ")
                        queue.addMessage(
                            ApplicationMessage.GetDistance(
                                tokens[1],
                                tokens[2],
                                tokens[3].toLong()
                            )
                        )
                    }

                    else -> {
                        println("Unknown command! [$input]")
                    }
                }
            }
        }
        thread {
            while (true) {
                if (!queue.isEmpty()) {
                    handler.handle(queue.popMessage())
                }
            }
        }
    }
}