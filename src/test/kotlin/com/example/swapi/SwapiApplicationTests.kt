package com.example.swapi

import org.json.JSONArray
import org.json.JSONObject
import org.junit.jupiter.api.Test
import org.mockito.Mockito.verify
import org.springframework.boot.test.context.SpringBootTest
import kotlin.math.exp
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@SpringBootTest
class SwapiApplicationTests {

    val swapiCallerToTest = SwapiCaller("https://swapi.dev/api/starships")
    val swapiRESTToTest = SwapiREST()

    @Test
    fun contextLoads() {
    }

    @Test
    fun swapiCallerIT() {
        val response = swapiCallerToTest.httpGetResponse()
        assertTrue(response.length > 0, "Should contain text")
    }

    @Test
    fun swapiResponseProcessingTest() {
        val result = swapiRESTToTest.processSwapiResponse(createTestSwapiResponse())
        assertTrue(result.length > 0, "Should contain text")
        val resultJson = JSONObject(result)
        val expensiveArray = resultJson.getJSONArray("expensive")
        assertEquals(2, expensiveArray.length(), "Number of starships")
        assertTrue(expensiveArray.getJSONObject(0).getLong("cost_in_credits") >=
                expensiveArray.getJSONObject(1).getLong("cost_in_credits"),
        "Wrong sort order")
    }

    private fun createTestSwapiResponse() = "{\"results\": [\n" +
            "{\n" +
            "\"name\": \"CR90 corvette\",\n" +
            "\"model\": \"CR90 corvette\",\n" +
            "\"manufacturer\": \"Corellian Engineering Corporation\",\n" +
            "\"cost_in_credits\": \"3500000\",\n" +
            "\"length\": \"150\",\n" +
            "\"max_atmosphering_speed\": \"950\",\n" +
            "\"crew\": \"30-165\",\n" +
            "\"passengers\": \"600\",\n" +
            "\"cargo_capacity\": \"3000000\",\n" +
            "\"consumables\": \"1 year\",\n" +
            "\"hyperdrive_rating\": \"2.0\",\n" +
            "\"MGLT\": \"60\",\n" +
            "\"starship_class\": \"corvette\",\n" +
            "\"pilots\": [],\n" +
            "\"films\": [\n" +
            "\"https://swapi.dev/api/films/1/\",\n" +
            "\"https://swapi.dev/api/films/3/\",\n" +
            "\"https://swapi.dev/api/films/6/\"\n" +
            "],\n" +
            "\"created\": \"2014-12-10T14:20:33.369000Z\",\n" +
            "\"edited\": \"2014-12-20T21:23:49.867000Z\",\n" +
            "\"url\": \"https://swapi.dev/api/starships/2/\"\n" +
            "},\n" +
            "{\n" +
            "\"name\": \"Star Destroyer\",\n" +
            "\"model\": \"Imperial I-class Star Destroyer\",\n" +
            "\"manufacturer\": \"Kuat Drive Yards\",\n" +
            "\"cost_in_credits\": \"150000000\",\n" +
            "\"length\": \"1,600\",\n" +
            "\"max_atmosphering_speed\": \"975\",\n" +
            "\"crew\": \"47,060\",\n" +
            "\"passengers\": \"n/a\",\n" +
            "\"cargo_capacity\": \"36000000\",\n" +
            "\"consumables\": \"2 years\",\n" +
            "\"hyperdrive_rating\": \"2.0\",\n" +
            "\"MGLT\": \"60\",\n" +
            "\"starship_class\": \"Star Destroyer\",\n" +
            "\"pilots\": [],\n" +
            "\"films\": [\n" +
            "\"https://swapi.dev/api/films/1/\",\n" +
            "\"https://swapi.dev/api/films/2/\",\n" +
            "\"https://swapi.dev/api/films/3/\"\n" +
            "],\n" +
            "\"created\": \"2014-12-10T15:08:19.848000Z\",\n" +
            "\"edited\": \"2014-12-20T21:23:49.870000Z\",\n" +
            "\"url\": \"https://swapi.dev/api/starships/3/\"\n" +
            "},\n" +
            "{\n" +
            "\"name\": \"Sentinel-class landing craft\",\n" +
            "\"model\": \"Sentinel-class landing craft\",\n" +
            "\"manufacturer\": \"Sienar Fleet Systems, Cyngus Spaceworks\",\n" +
            "\"cost_in_credits\": \"240000\",\n" +
            "\"length\": \"38\",\n" +
            "\"max_atmosphering_speed\": \"1000\",\n" +
            "\"crew\": \"5\",\n" +
            "\"passengers\": \"75\",\n" +
            "\"cargo_capacity\": \"180000\",\n" +
            "\"consumables\": \"1 month\",\n" +
            "\"hyperdrive_rating\": \"1.0\",\n" +
            "\"MGLT\": \"70\",\n" +
            "\"starship_class\": \"landing craft\",\n" +
            "\"pilots\": [],\n" +
            "\"films\": [\n" +
            "\"https://swapi.dev/api/films/1/\"\n" +
            "],\n" +
            "\"created\": \"2014-12-10T15:48:00.586000Z\",\n" +
            "\"edited\": \"2014-12-20T21:23:49.873000Z\",\n" +
            "\"url\": \"https://swapi.dev/api/starships/5/\"\n" +
            "}]},\n"
}
