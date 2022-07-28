package com.joaco

import kotlinx.serialization.KSerializer
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Fork
import org.openjdk.jmh.annotations.Measurement
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.Setup
import org.openjdk.jmh.annotations.State
import org.openjdk.jmh.annotations.Warmup
import java.util.concurrent.*
import kotlin.reflect.typeOf

@State(Scope.Benchmark)
@Fork(1)
@Warmup(iterations = 5)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)

class KotlinxBenchmark {
    private lateinit var json: String
    private lateinit var movie: Movie
    private lateinit var serializer: KSerializer<Movie>

    @Setup
    fun setUp() {
        json = """{"name":"Endgame","studio":"Marvel","rating":9.2}"""
        movie = Movie("Endgame", "Marvel", 9.2f)
        @Suppress("UNCHECKED_CAST")
        serializer = serializer(typeOf<Movie>()) as KSerializer<Movie>
    }

    @Benchmark()
    fun serialize() = Json.encodeToString(movie)

    @Benchmark
    fun deserialize() = Json.decodeFromString<Movie>(json)

    @Benchmark
    fun serializeMemoized() = Json.encodeToString(serializer, movie)

    @Benchmark
    fun deserializeMemoized() = Json.decodeFromString(serializer, json)
}
