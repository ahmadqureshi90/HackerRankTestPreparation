package com.example.hackerranktestpreparation.network

import com.google.gson.Gson
import java.lang.reflect.Type

object RemoteRepository {

    fun <T> getResponseFromGson(rawResponse: String, dataClassType: Type): T
    {
        return Gson().fromJson(rawResponse, dataClassType)
    }
}