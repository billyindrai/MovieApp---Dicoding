package com.billyindrai.architecturecomponent.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*

class Converter {
    @TypeConverter
    fun storedStringToObjects(data: String?): List<Genre>? {
        val gson = Gson()
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type = object : TypeToken<List<Genre>?>() {}.type
        return gson.fromJson<List<Genre>>(data, listType)
    }

    @TypeConverter
    fun objectsToStoredString(myObjects: List<Genre?>?): String? {
        val gson = Gson()
        return gson.toJson(myObjects)
    }
}