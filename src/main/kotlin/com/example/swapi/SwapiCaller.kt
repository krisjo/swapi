package com.example.swapi

import java.net.HttpURLConnection
import java.net.URL

class SwapiCaller(url: String) {
    val urlString = url

    fun httpGetResponse(): String {
        val swapiResponse = StringBuffer()

        val swapiUrl = URL(urlString)
        with(swapiUrl.openConnection() as HttpURLConnection) {
            requestMethod = "GET"
            setRequestProperty("accept", "application/json; charset=UTF-8")

            if (responseCode == 200) {
                inputStream.bufferedReader().use {
                    it.lines().forEach { line ->
                        println(line)
                        swapiResponse.append(line)
                    }
                }
            } else {
                return responseMessage // ToDo: Improve error handling
            }
        }
        return swapiResponse.toString()
    }

}