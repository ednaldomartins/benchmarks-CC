import control.BenchmarksController
import model.*

fun main()
{
    val bc = BenchmarksController()
    print("Escolha um dos teste abaixo:" +
            "\n1. Fannkuch" +
            "\n2. Spectralnorm" +
            "\n3. NBody" +
            "\n4. BinaryTree" +
            "\n5. Mandelbrot" +
            "\n\nopcao: ")
    val input = Integer.parseInt( readLine()!! )
    when( input )
    {
        1 -> {
            println("Digite o tamanho do vetor")
            val tamanho = Integer.parseInt( readLine()!! )
            bc.benchmarks(Fannkuch(), tamanho)
        }
        2 ->{
            println("Digite o tamanho da matriz (tamanhoXtamanho)")
            val tamanho = Integer.parseInt( readLine()!! )
            bc.benchmarks(Spectralnorm(), tamanho)
        }
        3 -> {
            println("Digite o tamanho do teste (tamanho<1 para n = 50000000)")
            val tamanho = Integer.parseInt( readLine()!! )
            bc.benchmarks(NBody(), tamanho)
        }
        4 -> {
            println("Digite o tamanho da profundidade da árvore(se menor que 6, entao n = 6)")
            val tamanho = Integer.parseInt( readLine()!! )
            bc.benchmarks(BinaryTrees(), tamanho)
        }
        5 -> {
            println("Digite o tamanho dos vetores e da matriz (tamanho<1 para n = 6000)")
            val tamanho = Integer.parseInt( readLine()!! )
            bc.benchmarks(Mandelbrot(), tamanho)
        }
        else-> println("Digite um valor existente no Menu")
    }

}