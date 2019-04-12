import control.BenchmarksController
import model.Fannkuch
import model.Spectralnorm
import java.util.*

fun main()
{
    //var bm:Benchmark

    //teste do site online: https://benchmarksgame-team.pages.debian.net/benchmarksgame/description/fannkuchredux.html
    //var v1 = intArrayOf(3,1,0,4,2)
    //bm = Fannkuch()
    //bm.criarTeste(5)
    //bm.vetor = v1
    //bm.benchmark()


    //teste 2: spectralnorm
    //bm = Spectralnorm()
    //bm.criarTeste(10)
    var bc = BenchmarksController()
    print("Escolha um dos teste abaixo:" +
            "\n1. Fannkuch" +
            "\n2. Spectralnorm" +
            "\n")
    val input =Integer.parseInt( readLine()!! )
    when(input )
    {
        1 -> {
            println("Digite o tamanho do teste")
            val tamanho = Integer.parseInt( readLine()!! )
            bc.benchmarks(Fannkuch(), tamanho)
        }
        2 ->{
            println("Digite o tamanho do teste")
            val tamanho = Integer.parseInt( readLine()!! )
            bc.benchmarks(Spectralnorm(), tamanho)
        }
        else-> println("Digite um valor existente no Menu")
    }

}