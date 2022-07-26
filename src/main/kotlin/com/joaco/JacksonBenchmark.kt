package com.joaco

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.openjdk.jmh.annotations.*
import java.util.concurrent.TimeUnit

@State(Scope.Benchmark)
@Fork(1)
@Warmup(iterations = 0)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)

class JacksonBenchmark {
    private lateinit var initializedMapper: ObjectMapper
    private lateinit var json: String
    private lateinit var movie: Movie

    @Setup
    fun setUp() {
        json = """{"name":"Endgame","studio":"Marvel","rating":9.2}"""
        movie = Movie("Endgame", "Marvel", 9.2f)
        initializedMapper = jacksonObjectMapper()
    }


    @Benchmark
    fun serializeWithInitializedMapper(): String {
        return initializedMapper.writeValueAsString(movie)
    }

    @Benchmark
    fun deserializeWithInitializedMapper(): Movie {
        return initializedMapper.readValue(json)
    }

    @Benchmark
    fun serializeWithoutInitializedMapper(): String {
        val mapper = jacksonObjectMapper()
        return mapper.writeValueAsString(movie)
    }

    @Benchmark
    fun deserializeWithoutInitializedMapper(): Movie {
        val mapper = jacksonObjectMapper()
        return mapper.readValue(json)
    }
}
