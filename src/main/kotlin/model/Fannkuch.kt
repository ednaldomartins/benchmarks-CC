package model

import util.Print

class Fannkuch: Benchmark
{
    /**
     * IntArray = int[]         ---> primitiva
     * Array<Int> = Integer[]   ---> Objeto
     */
    override fun benchmark(vetor: IntArray)
    {
        var i = 0
        var count = 0
        Print.printArray(vetor)
        while (i < vetor.size)
        {
            if (i != vetor[i])
            {
                permutacao(vetor, vetor[i], i)
                Print.printArray(vetor)
                i = 0
                count++
            }
            else
                i++
        }
        print("\n\ncount = $count")
    }

    /**
     * O metodo deve colocar o @elemento na posicao correta, e em seguida
     * inverter todos os elementos que ficaram antes do @elemento
     * @pos guarda apenas a posicao inicial em que o @elemento se encontrava no @vetor
     */
    private fun permutacao (vetor: IntArray, elemento: Int, pos: Int)
    {
        //se valor da posicao atual for 2, entao cria vetor de tamanho 3, criando um aux(i, i, 2)
        val aux = vetor.copyOfRange(0, elemento+1)
        vetor[elemento] = elemento

        //apaga o elemento do vetor aux copiando o posterior na posicao atual
        for (i in pos .. (elemento-1) )
            aux[i] = aux[i+1]

        //o vetor receber do aux de forma invertida
        for (i in 0 .. (elemento-1) )
            vetor[i] = aux[ (elemento-i)-1]

    }

}