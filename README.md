# BENCHMARKING SERDE LIBRARIES IN JVM

Benchmark most important libraries for Serialize/Deserialize in the jvm

* [Jackson 2.14.1](https://github.com/FasterXML/jackson-core)
* [Kotlinx Serialization 1.4.1](https://github.com/Kotlin/kotlinx.serialization)
* [GSON 2.10](https://github.com/google/gson)

Using [JMH 1.36](https://github.com/openjdk/jmh) as Benchmark tool

## HOW TO USE
```
./gradlew benchmark
```

## REPORT

```
Benchmark                              Mode  Cnt        Score        Error  Units
DeserializeBenchmark.gson             thrpt    5   113451.693 ±  21302.048  ops/s
DeserializeBenchmark.gsonMemoized     thrpt    5  1258932.806 ± 202443.061  ops/s
DeserializeBenchmark.jackson          thrpt    5     7320.871 ±  11382.451  ops/s
DeserializeBenchmark.jacksonMemoized  thrpt    5   870175.114 ±  56983.868  ops/s
DeserializeBenchmark.kotlinx          thrpt    5  1472199.156 ± 458727.010  ops/s
DeserializeBenchmark.kotlinxMemoized  thrpt    5  2616191.702 ± 274387.406  ops/s
DeserializeBenchmark.kotlinxWay       thrpt    5  2663739.907 ± 522767.480  ops/s
SerializeBenchmark.gson               thrpt    5   120116.597 ±  72109.725  ops/s
SerializeBenchmark.gsonMemoized       thrpt    5  1227384.510 ± 284068.641  ops/s
SerializeBenchmark.jackson            thrpt    5    11743.604 ±    378.925  ops/s
SerializeBenchmark.jacksonMemoized    thrpt    5  2899099.082 ±  59709.978  ops/s
SerializeBenchmark.kotlinx            thrpt    5  2126410.027 ± 175240.199  ops/s
SerializeBenchmark.kotlinxMemoized    thrpt    5  4344792.051 ± 392252.976  ops/s
SerializeBenchmark.kotlinxWay         thrpt    5  4185206.404 ± 824291.419  ops/s

```
### DESERIALIZE
![](resources/deserialize.png)

### SERIALIZE
![](resources/serialize.png)
