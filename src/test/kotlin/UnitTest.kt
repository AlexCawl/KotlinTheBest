import org.alexcawl.space_gen_lib.subject.*
import org.alexcawl.space_gen_lib.system.exception.PlanetNotFoundException
import org.alexcawl.space_gen_lib.system.exception.SpaceSystemBuildException
import org.alexcawl.space_gen_lib.system.exception.StarDuplicateException
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test
import kotlin.test.assertEquals

class UnitTest {
    @Test
    fun `check if builder throws when star is not existing`() {
        val builder: SpaceSystem.Builder = SpaceSystem.Builder()

        assertThrows<SpaceSystemBuildException> {
            builder.build()
        }
    }

    @Test
    fun `check if builder throws when star is duplicate`() {
        val builder: SpaceSystem.Builder = SpaceSystem.Builder()
        val star = Star(
            weight = 100.0,
            radius = 100.0,
            name = "Sun"
        )
        assertThrows<StarDuplicateException> {
            builder.add(star)
            builder.add(star)
        }
    }

    @Test
    fun `check if builder throws when unknown celestial body class`() {
        val builder: SpaceSystem.Builder = SpaceSystem.Builder()
        val unknownClassObject = object : CelestialBody(
            weight = 100.0,
            radius = 100.0,
            rotationTime = 365,
            rotationType = RotationOrientation.CLOCKWISE
        ) {}
        assertThrows<IllegalStateException> {
            builder.add(unknownClassObject)
        }
    }

    @Test
    fun `check if size of system correct when iterated`() {
        val builder: SpaceSystem.Builder = SpaceSystem.Builder()
        val star = Star(
            weight = 100.0,
            radius = 100.0,
            name = "Sun"
        )
        val planet1 = Planet(
            weight = 100.0,
            radius = 100.0,
            rotationTime = 365,
            rotationType = RotationOrientation.CLOCKWISE,
            name = "Earth",
            distanceFromSun = 1000.0
        )
        val planet2 = Planet(
            weight = 20.0,
            radius = 20.0,
            rotationTime = 156,
            rotationType = RotationOrientation.COUNTERCLOCKWISE,
            name = "Mercury",
            distanceFromSun = 100.0
        )
        assertEquals(expected = 3, actual = builder.add(star).add(planet1).add(planet2).build().toList().size)
    }

    @Test
    fun `check if distance between planet and star is correct`() {
        val builder: SpaceSystem.Builder = SpaceSystem.Builder()
        val star = Star(
            weight = 100.0,
            radius = 100.0,
            name = "Sun"
        )
        val planet1 = Planet(
            weight = 100.0,
            radius = 100.0,
            rotationTime = 365,
            rotationType = RotationOrientation.CLOCKWISE,
            name = "Earth",
            distanceFromSun = 1000.0
        )
        val planet2 = Planet(
            weight = 20.0,
            radius = 20.0,
            rotationTime = 156,
            rotationType = RotationOrientation.COUNTERCLOCKWISE,
            name = "Mercury",
            distanceFromSun = 100.0
        )
        assertEquals(
            1000,
            builder.add(star)
                .add(planet1)
                .add(planet2)
                .build()
                .getDistance("Sun", "Earth", 15)
                .toInt()
        )
    }

    @Test
    fun `check if distance between planet and planet is correct`() {
        val builder: SpaceSystem.Builder = SpaceSystem.Builder()
        val star = Star(
            weight = 100.0,
            radius = 100.0,
            name = "Sun"
        )
        val planet1 = Planet(
            weight = 100.0,
            radius = 100.0,
            rotationTime = 365,
            rotationType = RotationOrientation.CLOCKWISE,
            name = "Earth",
            distanceFromSun = 1000.0
        )
        val planet2 = Planet(
            weight = 20.0,
            radius = 20.0,
            rotationTime = 156,
            rotationType = RotationOrientation.COUNTERCLOCKWISE,
            name = "Mercury",
            distanceFromSun = 100.0
        )
        assertEquals(
            938,
            builder.add(star)
                .add(planet1)
                .add(planet2)
                .build()
                .getDistance("Mercury", "Earth", 15)
                .toInt()
        )
    }


    @Test
    fun `check if distance between planet and fake_planet is not existing`() {
        val builder: SpaceSystem.Builder = SpaceSystem.Builder()
        val star = Star(
            weight = 100.0,
            radius = 100.0,
            name = "Sun"
        )
        val planet1 = Planet(
            weight = 100.0,
            radius = 100.0,
            rotationTime = 365,
            rotationType = RotationOrientation.CLOCKWISE,
            name = "Earth",
            distanceFromSun = 1000.0
        )
        val planet2 = Planet(
            weight = 20.0,
            radius = 20.0,
            rotationTime = 156,
            rotationType = RotationOrientation.COUNTERCLOCKWISE,
            name = "Mercury",
            distanceFromSun = 100.0
        )
        assertThrows<PlanetNotFoundException> {
            builder.add(star)
                .add(planet1)
                .add(planet2)
                .build()
                .getDistance("FakeName", "Earth", 15)
                .toInt()

        }
    }


    @Test
    fun `check if distance between fake_planet and planet is not existing`() {
        val builder: SpaceSystem.Builder = SpaceSystem.Builder()
        val star = Star(
            weight = 100.0,
            radius = 100.0,
            name = "Sun"
        )
        val planet1 = Planet(
            weight = 100.0,
            radius = 100.0,
            rotationTime = 365,
            rotationType = RotationOrientation.CLOCKWISE,
            name = "Earth",
            distanceFromSun = 1000.0
        )
        val planet2 = Planet(
            weight = 20.0,
            radius = 20.0,
            rotationTime = 156,
            rotationType = RotationOrientation.COUNTERCLOCKWISE,
            name = "Mercury",
            distanceFromSun = 100.0
        )
        assertThrows<PlanetNotFoundException> {
            builder.add(star)
                .add(planet1)
                .add(planet2)
                .build()
                .getDistance("Mercury", "FakeName", 15)
                .toInt()
        }
    }


    @Test
    fun `check if distance between star and fake_planet is not existing`() {
        val builder: SpaceSystem.Builder = SpaceSystem.Builder()
        val star = Star(
            weight = 100.0,
            radius = 100.0,
            name = "Sun"
        )
        val planet1 = Planet(
            weight = 100.0,
            radius = 100.0,
            rotationTime = 365,
            rotationType = RotationOrientation.CLOCKWISE,
            name = "Earth",
            distanceFromSun = 1000.0
        )
        val planet2 = Planet(
            weight = 20.0,
            radius = 20.0,
            rotationTime = 156,
            rotationType = RotationOrientation.COUNTERCLOCKWISE,
            name = "Mercury",
            distanceFromSun = 100.0
        )
        assertThrows<PlanetNotFoundException> {
            builder.add(star)
                .add(planet1)
                .add(planet2)
                .build()
                .getDistance("Sun", "FakeName", 15)
        }
    }

    @Test
    fun `check if system builds correctly`() {
        val builder: SpaceSystem.Builder = SpaceSystem.Builder()
        val star = Star(
            weight = 100.0,
            radius = 100.0,
            name = "Sun"
        )
        val planet1 = Planet(
            weight = 100.0,
            radius = 100.0,
            rotationTime = 365,
            rotationType = RotationOrientation.CLOCKWISE,
            name = "Earth",
            distanceFromSun = 1000.0
        )
        val planet2 = Planet(
            weight = 20.0,
            radius = 20.0,
            rotationTime = 156,
            rotationType = RotationOrientation.COUNTERCLOCKWISE,
            name = "Mercury",
            distanceFromSun = 100.0
        )
        assertDoesNotThrow {
            builder.add(star)
                .add(planet1)
                .add(planet2)
                .build()
        }
    }
}