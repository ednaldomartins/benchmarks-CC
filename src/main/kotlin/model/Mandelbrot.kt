package model

import java.io.BufferedOutputStream
import java.util.concurrent.atomic.AtomicInteger

/**
 * https://benchmarksgame-team.pages.debian.net/benchmarksgame/program/mandelbrot-java-2.html
 *
 * The Computer Language Benchmarks Game
 * https://salsa.debian.org/benchmarksgame-team/benchmarksgame/
 *
 * contributed by Stefan Krause
 * slightly modified by Chad Whipkey
 * parallelized by Colin D Bennett 2008-10-04
 * reduce synchronization cost by The Anh Tran
 * optimizations and refactoring by Enotus 2010-11-11
 * optimization by John Stalcup 2012-2-19
*/
class Mandelbrot: Benchmark {
    var n = 6000

    override fun criarTeste(tamanho: Int) {
        if (tamanho>0) this.n = tamanho
    }

    lateinit var out: Array<ByteArray>
    lateinit var yCt: AtomicInteger
    lateinit var Crb: DoubleArray
    lateinit var Cib: DoubleArray

    private fun getByte(x: Int, y: Int): Int {
        var res = 0
        var i = 0
        while (i < 8) {
            var Zr1 = Crb[x + i]
            var Zi1 = Cib[y]

            var Zr2 = Crb[x + i + 1]
            var Zi2 = Cib[y]

            var b = 0
            var j = 49
            do {
                val nZr1 = Zr1 * Zr1 - Zi1 * Zi1 + Crb[x + i]
                val nZi1 = Zr1 * Zi1 + Zr1 * Zi1 + Cib[y]
                Zr1 = nZr1
                Zi1 = nZi1

                val nZr2 = Zr2 * Zr2 - Zi2 * Zi2 + Crb[x + i + 1]
                val nZi2 = Zr2 * Zi2 + Zr2 * Zi2 + Cib[y]
                Zr2 = nZr2
                Zi2 = nZi2

                if (Zr1 * Zr1 + Zi1 * Zi1 > 4) {
                    b = b or 2
                    if (b == 3) break
                }
                if (Zr2 * Zr2 + Zi2 * Zi2 > 4) {
                    b = b or 1
                    if (b == 3) break
                }
            } while (--j > 0)
            res = (res shl 2) + b
            i += 2
        }
        return res xor -1
    }

    internal fun putLine(y: Int, line: ByteArray) {
        for (xb in line.indices)
            line[xb] = getByte(xb * 8, y).toByte()
    }

    @Throws(Exception::class)
    override fun benchmark() {
        Crb = DoubleArray(n + 7)
        Cib = DoubleArray(n + 7)
        val invN = 2.0 / n
        for (i in 0 until n) {
            Cib[i] = i * invN - 1.0
            Crb[i] = i * invN - 1.5
        }
        yCt = AtomicInteger()
        out = Array(n) { ByteArray((n + 7) / 8) }

        val pool = arrayOfNulls<Thread>(2 * Runtime.getRuntime().availableProcessors())
        for (i in pool.indices)
            pool[i] = object : Thread() {
                override fun run() {
                    var y = yCt.getAndIncrement()
                    while (y < out.size){
                        putLine(y, out[y])
                        y = yCt.getAndIncrement()
                    }
                }
            }
        for (t in pool) t?.start()
        for (t in pool) t?.join()

        val stream = BufferedOutputStream(System.out)
        stream.write("P4\n$n $n\n".toByteArray())
        for (i in 0 until n) stream.write(out[i])
        //stream.close()
    }
}