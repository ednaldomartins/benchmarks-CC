package control

import model.Benchmark

class BenchmarksController
{
    fun benchmarks(benchmark: Benchmark, tamanho: Int)
    {
        benchmark.criarTeste(tamanho)

        val tempoInicial = System.currentTimeMillis()
        benchmark.benchmark()
        val tempoFinal = System.currentTimeMillis()

        val tempo = (tempoFinal - tempoInicial)
        println( "\n\n\n\n\ntempo: $tempo milesegundos" )
    }
}