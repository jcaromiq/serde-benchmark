package com.joaco

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.openjdk.jmh.annotations.*
import java.util.concurrent.TimeUnit

@State(Scope.Benchmark)
@Fork(1)
@Warmup(iterations = 0)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)

class KotlinxBenchmark {
    private lateinit var json: String
    private lateinit var movie: Movie

    @Setup
    fun setUp() {
        json = """{"name":"Endgame","studio":"Marvel","rating":9.2}"""
        movie = Movie("Endgame", "Marvel", 9.2f)
    }

    @Benchmark()
    fun serialize(): String {
        return Json.encodeToString(movie)
    }

    @Benchmark
    fun deserialize(): Movie {
        return Json.decodeFromString<Movie>(json)
    }


}
