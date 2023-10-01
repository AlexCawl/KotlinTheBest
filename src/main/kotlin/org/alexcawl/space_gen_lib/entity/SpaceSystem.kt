package org.alexcawl.space_gen_lib.entity

class SpaceSystem private constructor() {
    private var _star: Star? = null
    private val _planets: MutableList<Planet> = mutableListOf()
    val star: Star
        get() = _star!!
    private val planets: List<Planet>
        get() = _planets.toList()

    companion object {
        class Builder {
            private val system: SpaceSystem = SpaceSystem()

            @Throws(IllegalStateException::class)
            fun addStar(star: Star): Builder = this.apply {
                system.addStar(star)
            }

            fun addPlanet(planet: Planet): Builder = this.apply {
                system.addPlanet(planet)
            }

            fun addPlanets(planets: List<Planet>): Builder = this.apply {
                planets.forEach {
                    system.addPlanet(it)
                }
            }

            @Throws(IllegalStateException::class)
            fun build(): SpaceSystem = system.apply {
                when (_star) {
                    null -> throw IllegalStateException("No star in system!")
                }
            }
        }
    }


    @Throws(IllegalStateException::class)
    private fun addStar(star: Star) {
        when (_star) {
            null -> _star = star
            else -> throw IllegalStateException("Star duplicated!")
        }
    }

    private fun addPlanet(planet: Planet) {
        _planets.add(planet)
    }

    @Throws(IllegalStateException::class)
    fun getDistanceFromSun(planetName: String): Double {
        return when (val planet: Planet? = _planets.find { it.name == planetName }) {
            null -> throw IllegalStateException("Planet with name $planetName not found in system!")
            else -> planet.distanceFromSun
        }
    }

    @Throws(IllegalStateException::class)
    fun getDistanceBetween(planetName1: String, planetName2: String, time: Long): Double {
        val planet1: Planet? = _planets.find { it.name == planetName1 }
        val planet2: Planet? = _planets.find { it.name == planetName2 }

        return when {
            planet1 == null -> throw IllegalStateException("Planet with name $planetName1 not found in system!")
            planet2 == null -> throw IllegalStateException("Planet with name $planetName2 not found in system!")
            else -> planet1.getDistance(planet2, time)
        }
    }
}