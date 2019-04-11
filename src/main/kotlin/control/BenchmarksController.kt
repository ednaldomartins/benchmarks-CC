package control

import model.Benchmark

class BenchmarksController
{
    fun benchmarks(benchmark: Benchmark, tamanho:Int)
    {
        benchmark.criarTeste(tamanho)
        benchmark.benchmark()
    }
}