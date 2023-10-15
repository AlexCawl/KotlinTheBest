package org.alexcawl.space_gen_lib.system

interface IHandler {
    fun handle(task: Runnable)

    fun post(task: Runnable)
}