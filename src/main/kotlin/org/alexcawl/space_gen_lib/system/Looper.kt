package org.alexcawl.space_gen_lib.system

class Looper private constructor() : ILooper {
    private val queue: IMessageQueue = MessageQueue()
    private val handlers: MutableMap<Class<out IHandler>, IHandler> = mutableMapOf()
    private val defaultHandler: IHandler by lazy { Handler(this) }

    companion object {
        fun prepare(): ILooper = Looper()
    }

    override fun bindHandler(handler: IHandler) {
        handlers[handler.javaClass] = handler
    }

    override fun receive(message: Message) = queue.add(message)

    override fun loop() {
        while (true) {
            when (queue.isEmpty()) {
                true -> Unit
                false -> {
                    val message: Message = queue.pop()
                    val handler: IHandler? = handlers[message.handler]
                    (handler ?: defaultHandler).handle(message.task)
                }
            }
        }
    }
}