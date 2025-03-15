package com.example.tmdb.data

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import java.io.IOException
import java.time.LocalDate


internal class LocalDateAdapter : TypeAdapter<LocalDate?>() {
    @Throws(IOException::class)
    override fun write(jsonWriter: JsonWriter, localDate: LocalDate?) {
        if (localDate == null) {
            jsonWriter.nullValue()
        } else {
            jsonWriter.value(localDate.toString())
        }
    }

    @Throws(IOException::class)
    override fun read(jsonReader: JsonReader): LocalDate? {
        if (jsonReader.peek() === JsonToken.NULL) {
            jsonReader.nextNull()
            return null
        } else {
            return LocalDate.parse(jsonReader.nextString())
        }
    }
}