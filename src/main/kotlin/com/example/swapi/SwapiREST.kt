package com.example.swapi

import org.json.JSONArray
import org.json.JSONObject
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.math.min

@RestController
class SwapiREST {

    val swapiUrlString = "https://swapi.dev/api/starships"

    @GetMapping("/starships")
    fun getSpaceships(): String {

        val swapiCaller = SwapiCaller(swapiUrlString)
        val swapiResponse = swapiCaller.httpGetResponse()

        return processSwapiResponse(swapiResponse)
    }

    fun processSwapiResponse(swapiResponse: String): String {
        val jsonResponse = JSONObject(swapiResponse)

        val resultsArray = jsonResponse.get("results") as JSONArray
        val starshipList = getMutableList(resultsArray)
        starshipList.sortWith(compareByDescending { getCost(it) })

        val maxReturnSize = 10
        val response = JSONObject()
        response.put("expensive", JSONArray(extractMaxFirstEntries(starshipList, maxReturnSize)))
        return response.toString()
    }

    private fun getMutableList(jsonArray: JSONArray): MutableList<Map<String, String>> {
        return jsonArray.toList().filterIsInstance<Map<String, String>>().toMutableList()
    }

    private fun getCost(it: Map<String, String>): Long {
        val costInCredits = it.getOrDefault("cost_in_credits", "0")
        return (
                if (costInCredits.equals("unknown")) 0
                else costInCredits.toLong()
                )
    }

    private fun extractMaxFirstEntries(mutableList: MutableList<Map<String, String>>, maxSize: Int) =
        mutableList.subList(0, min(maxSize, mutableList.size) - 1)
}