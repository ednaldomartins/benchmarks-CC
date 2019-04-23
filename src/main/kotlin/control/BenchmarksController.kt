package control

import model.Benchmark
import util.Print

class BenchmarksController
{
    fun benchmarks(benchmark: Benchmark, tamanho: Int)
    {
        benchmark.criarTeste(tamanho)
        Print.printConsumoMemoriaMB()

        val tempoInicial = System.currentTimeMillis()
        benchmark.benchmark()
        val tempo = (System.currentTimeMillis() - tempoInicial)

        Print.printConsumoMemoriaMB()
        println( "\n\ntempo: ${tempo}ms" )
    }
}