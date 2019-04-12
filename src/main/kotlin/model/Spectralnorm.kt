package model

import util.Print

class Spectralnorm: Benchmark
{
    lateinit var matriz: Array<IntArray>
    lateinit var matrizA: Array<FloatArray>

    override fun benchmark()
    {
        var j = 0
        var a = 1
        //primeira parte
        while (j in 0 .. (matriz.size-1))
        {
            //divisor é a soma (a + j) ===> 1/(a+j)
            matriz[0][j] = a + j
            //primeiro 'a' recebe o valor de 'i', depois i incrementa
            a += j++
        }
        //segunda parte
        for (i in 1 .. (matriz.size-1))
        {
            //agora na linha posterior 'a' receber o termo(i1) da linha anterior
            a = matriz[i-1][1] + 1
            for (j in 0 .. (matriz.size-1))
            {
                matriz[i][j] = a + j
                //agora 'a' recebe o valor de 'i' e tambem o do 'j'
                a += i + j
            }
        }
        Print.printMatriz(matriz)
        //terceira parte
        for (i in 0 .. (matriz.size-1))
        {
            for (j in 0 .. (matriz.size-1))
                matrizA[i][j] = 1.0f/(matriz[i][j])
        }
        Print.printMatriz(matrizA)
    }

    override fun criarTeste(tamanho:Int)
    {
        //criando matriz tamanhoXtamanho
        matriz = Array(tamanho, {IntArray(tamanho)})
        matrizA = Array(tamanho, {FloatArray(tamanho)})
    }

}