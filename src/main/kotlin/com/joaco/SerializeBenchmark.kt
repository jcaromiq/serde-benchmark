package com.joaco

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.google.gson.Gson
import kotlinx.serialization.KSerializer
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import org.openjdk.jmh.annotations.*
import java.util.concurrent.TimeUnit
import kotlin.reflect.typeOf

@State(Scope.Benchmark)
@Fork(1)
@Warmup(iterations = 5)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
class SerializeBenchmark {
    private lateinit var initializedMapper: ObjectMapper
    private lateinit var json: String
    private lateinit var movie: Movie
    private lateinit var serializer: KSerializer<Movie>
    private lateinit var gson: Gson


    @Setup
    fun setUp() {
        json = """{"name":"Endgame","studio":"Marvel","rating":9.2}"""
        movie = Movie("Endgame", "Marvel", 9.2f)
        initializedMapper = jacksonObjectMapper()
        @Suppress("UNCHECKED_CAST")
        serializer = serializer(typeOf<Movie>()) as KSerializer<Movie>
        gson = Gson()
    }

    @Benchmark
    fun kotlinx() = Json.encodeToString(movie)

    @Benchmark
    fun kotlinxWay() = Json.encodeToString(Movie.serializer(),movie)

    @Benchmark
    fun kotlinxMemoized() = Json.encodeToString(serializer, movie)

    @Benchmark
    fun jackson(): String {
        val mapper = jacksonObjectMapper()
        return mapper.writeValueAsString(movie)
    }

    @Benchmark
    fun jacksonMemoized() = initializedMapper.writeValueAsString(movie)

    @Benchmark
    fun gson(): String {
        val gson = Gson()
        return gson.toJson(movie)
    }

    @Benchmark
    fun gsonMemoized() = gson.toJson(movie)
}
