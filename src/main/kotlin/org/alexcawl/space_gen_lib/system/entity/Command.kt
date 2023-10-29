package org.alexcawl.space_gen_lib.system.entity

sealed class Command(val args: List<String>) {
    class AddPlanetCommand(args: List<String>) : Command(args)

    class AddStarCommand(args: List<String>) : Command(args)

    class GetDistanceCommand(args: List<String>) : Command(args)

    class GetSortedCommand(args: List<String>) : Command(args)

    class Load(val path: String) : Command(listOf())

    class Save(val path: String) : Command(listOf())

    object ShowSystemCommand : Command(listOf())

    object UndefinedCommand : Command(listOf())
}