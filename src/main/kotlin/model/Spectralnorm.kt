package model

import util.Print

class Spectralnorm: Benchmark
{
    lateinit var matriz: Array<IntArray>

    override fun benchmark()
    {

    }

    override fun criarTeste(tamanho:Int)
    {
        //criando matriz tamanhoXtamanho
        matriz = Array(tamanho, {IntArray(tamanho)})
        //printando matriz (falta embaralhar os numeros)
        //Print.printMatriz(matriz)
    }

}