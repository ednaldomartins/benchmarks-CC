import model.Fannkuch

fun main()
{
    print("teste")
    var bm = Fannkuch()

    //teste do site online: https://benchmarksgame-team.pages.debian.net/benchmarksgame/description/fannkuchredux.html
    var v1 = intArrayOf(3,1,0,4,2)
    var v2 = intArrayOf(2,5,1,3,0,4)

    bm.benchmark(v1)
}