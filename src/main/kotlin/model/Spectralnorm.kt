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
        multiplicarMatrizes(matrizA, matrizTransposta)
        fatoracaoLU(matrizResultante)

        resultado = matrizResultante[0][0]
        for (i in 1 .. (matrizResultante.size-1))
            resultado *= matrizResultante[i][i]
        resultado = Math.sqrt(Math.abs( resultado ))
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
        Print.printMatriz(matriz)
        //terceira parte
        for (i in 0 .. (matriz.size-1))
        {
            for (j in 0 .. (matriz.size-1))
                matrizA[i][j] = 1.0/(matriz[i][j])
        }
        Print.printMatriz(matrizA)
    }

    private fun construirMatrizTransposta(m: Array<DoubleArray>)
    {
        matrizTransposta = Array(m.size) {DoubleArray(m.size)}
        for (i in 0 .. (m.size-1))
        {
            for (j in 0 .. (m.size-1))
                matrizTransposta[j][i] = m[i][j]
        }
        Print.printMatriz(matrizTransposta)
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
            var res = 0.0
            for (j in 0 .. (m1.size-1))
            {
                for (k in 0 .. (m1.size-1))
                    res += m1[i][k]*m2[j][k]
                matrizResultante[i][j] = res
            }
        }
        Print.printMatriz(matrizResultante)
    }

    /*
     *  (SEM PIVOTEAMENTO PARCIAL)
     *  Posso usar o metodo diretamente sem reordenar a matriz porque
     *  nenhum das linhas ou colunas possuem 0, portante, nao terei
     *  nenhum linha dividindo termo por 0. Esse metodo vai apenas usar
     *  os conceitos da fatoração LU para construir a matriz U
     */
    private fun fatoracaoLU(mU: Array<DoubleArray>)
    {
        var mL = Array(mU.size) {DoubleArray(mU.size)}
        for (i in 0 .. (mU.size-1))
        {
            //pivos
            for (j in (i+1) .. (mU.size-1))
                mL[j][i] = mU[j][i]/mU[i][i]

            for (j in (i+1) .. (mU.size-1))
            {
                for (k in i .. (mU.size-1))
                {

                    mU[j][k] = mU[j][k] - mL[j][k] * mU[i][k]

                }
            }

        }
        Print.printMatriz(mU)
    }
}