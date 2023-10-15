package org.alexcawl.space_gen_lib.setup

sealed class Command(val args: List<String>) {
    class AddPlanetCommand(args: List<String>) : Command(args)

    class AddStarCommand(args: List<String>) : Command(args)

    class GetDistanceCommand(args: List<String>) : Command(args)

    class GetSortedCommand(args: List<String>) : Command(args)

    class InvalidArgs(val cause: String) : Command(listOf())

    object UndefinedCommand : Command(listOf())
}