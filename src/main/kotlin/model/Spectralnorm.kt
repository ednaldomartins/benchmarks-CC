package model

import util.Print

class Spectralnorm: Benchmark
{
    lateinit var matriz: Array<IntArray>
    lateinit var matrizA: Array<DoubleArray>
    lateinit var matrizTransposta: Array<DoubleArray>
    lateinit var matrizResultante: Array<DoubleArray>
    var resultado: Double = 0.0

    override fun benchmark()
    {
        construirMatrizA()
        construirMatrizTransposta(matrizA)
        multiplicarMatrizes(matrizTransposta, matrizA)

        for (i in 0 .. (matrizResultante.size-1))
        {
            for (j in 0 .. (matrizResultante.size-1))
                resultado += Math.pow(matrizResultante[i][j], 2.0)
        }
        resultado = Math.sqrt(( resultado ))
        println("\n\n"+resultado)
    }

    override fun criarTeste(tamanho:Int)
    {
        //criando matriz tamanhoXtamanho
        matriz = Array(tamanho) {IntArray(tamanho)}
        matrizA = Array(tamanho) {DoubleArray(tamanho)}
    }

    private fun construirMatrizA()
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
        //terceira parte
        for (i in 0 .. (matriz.size-1))
        {
            for (j in 0 .. (matriz.size-1))
                matrizA[i][j] = 1.0/(matriz[i][j])
        }
    }

    private fun construirMatrizTransposta(m: Array<DoubleArray>)
    {
        matrizTransposta = Array(m.size) {DoubleArray(m.size)}
        for (i in 0 .. (m.size-1))
        {
            for (j in 0 .. (m.size-1))
                matrizTransposta[j][i] = m[i][j]
        }
    }

    private fun multiplicarMatrizes(m1: Array<DoubleArray>, m2: Array<DoubleArray>)
    {
        if (m1.size != m2[0].size && m2.size != m1[0].size)
            System.exit(1)
        else
            matrizResultante = Array(m1.size) {DoubleArray(m2.size)}
        //multiplicação de linhaXcoluna termo a termo
        for (i in 0 .. (m1.size-1))
        {
            for (j in 0 .. (m1.size-1))
            {
                var res = 0.0
                for (k in 0 .. (m1.size-1))
                    res += m1[i][k]*m2[k][j]
                matrizResultante[i][j] = res
            }
        }
    }

}