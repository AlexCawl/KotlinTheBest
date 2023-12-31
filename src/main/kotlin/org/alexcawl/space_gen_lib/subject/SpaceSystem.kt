package org.alexcawl.space_gen_lib.subject

import org.alexcawl.space_gen_lib.system.exception.PlanetNotFoundException
import org.alexcawl.space_gen_lib.system.exception.SpaceSystemBuildException
import org.alexcawl.space_gen_lib.system.exception.StarDuplicateException

class SpaceSystem private constructor() : Iterable<CelestialBody> {
    private var _star: Star? = null
    val star: Star get() = _star!!

    private val _planets: MutableList<Planet> = mutableListOf()
    val planets: List<Planet> get() = _planets.toList()


    @Throws(StarDuplicateException::class)
    private fun addStar(star: Star) {
        when (this._star) {
            null -> this._star = star
            else -> throw StarDuplicateException("Star already existed!")
        }
    }

    private fun addPlanet(planet: Planet) {
        _planets.add(planet)
    }

    @Throws(PlanetNotFoundException::class)
    private fun getDistanceFromSun(planetName: String): Double {
        return when (val planet: Planet? = _planets.find { it.name == planetName }) {
            null -> throw PlanetNotFoundException("Planet with name $planetName not found in system!")
            else -> planet.distanceFromSun
        }
    }

    @Throws(PlanetNotFoundException::class)
    private fun getDistanceBetween(planetName1: String, planetName2: String, time: Long): Double {
        val planet1: Planet? = _planets.find { it.name == planetName1 }
        val planet2: Planet? = _planets.find { it.name == planetName2 }

        return when {
            planet1 == null -> throw PlanetNotFoundException("Planet with name $planetName1 not found in system!")
            planet2 == null -> throw PlanetNotFoundException("Planet with name $planetName2 not found in system!")
            else -> planet1.getDistance(planet2, time)
        }
    }

    @Throws(PlanetNotFoundException::class)
    fun getDistance(name1: String, name2: String, time: Long): Double {
        if (name1 == name2) return 0.0
        return when (star.name) {
            name1 -> getDistanceFromSun(name2)
            name2 -> getDistanceFromSun(name1)
            else -> getDistanceBetween(name1, name2, time)
        }
    }

    override fun iterator(): Iterator<CelestialBody> =
        mutableListOf<CelestialBody>(star).apply { addAll(_planets) }.toList().iterator()

    fun asSorted(comparator: Comparator<in CelestialBody>): List<CelestialBody> =
        mutableListOf<CelestialBody>(star).apply { addAll(_planets) }.toList().sortedWith(comparator)

    class Builder {
        val system: SpaceSystem = SpaceSystem()

        @Throws(IllegalStateException::class, StarDuplicateException::class)
        fun add(celestialBody: CelestialBody): Builder {
            when (celestialBody) {
                is Planet -> system.addPlanet(celestialBody)
                is Star -> system.addStar(celestialBody)
                else -> throw IllegalStateException("Unknown type of ${celestialBody.javaClass}")
            }
            return this
        }

        @Throws(SpaceSystemBuildException::class)
        fun build(): SpaceSystem = system.apply {
            when (_star) {
                null -> throw SpaceSystemBuildException("No star in system!")
            }
        }

        fun clear(): Builder {
            system._star = null
            system._planets.clear()
            return this
        }
    }
}