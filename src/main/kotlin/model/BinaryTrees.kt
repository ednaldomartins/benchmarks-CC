package model

import util.Print
import java.io.IOException
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

/**
 * link, Java: https://benchmarksgame-team.pages.debian.net/benchmarksgame/program/binarytrees-java-7.html
 *
 * The Computer Language Benchmarks Game
 * https://salsa.debian.org/benchmarksgame-team/benchmarksgame/
 *
 * based on Jarkko Miettinen's Java program
 * contributed by Tristan Dupont
 * *reset*
 */
class BinaryTrees: Benchmark {
    var n:Int = 0

    companion object {
        @JvmStatic val MIN_DEPTH = 4
        @JvmStatic val EXECUTOR_SERVICE = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())
    }

    override fun criarTeste(tamanho: Int) {
        this.n = tamanho
    }

    @Throws(IOException::class)
    override fun benchmark() {
        val maxDepth = if ( n < (MIN_DEPTH+2) ) (MIN_DEPTH+2) else n
        val stretchDepth = maxDepth + 1

        println("stretch tree of depth $stretchDepth \t check: ${bottomUpTree(stretchDepth).itemCheck()}")

        val longLivedTree:TreeNode = bottomUpTree(maxDepth)
        val size = ((maxDepth - MIN_DEPTH)/(2)) + 1
        val results = arrayOfNulls<String>(size)

        for (d in MIN_DEPTH .. maxDepth step 2) {
            val depth = d

            EXECUTOR_SERVICE.execute {
                var check = 0
                //kotlin bit shifting
                val iterations = 1 shl (maxDepth - depth + MIN_DEPTH)
                for (i in 1 .. iterations) {
                    val treeNode1:TreeNode = bottomUpTree(depth)
                    check += treeNode1.itemCheck()
                }
                results[(depth - MIN_DEPTH) / 2] = "$iterations \t trees of depth $depth \t check: $check"
			}
        }

        EXECUTOR_SERVICE.shutdown()
        EXECUTOR_SERVICE.awaitTermination(120L, TimeUnit.SECONDS)

        for ( str in results) println(str)
        println("long lived tree of depth $maxDepth \t check: ${longLivedTree.itemCheck()}")
        Print.printConsumoMemoriaMB()
    }

    class TreeNode {
        private var left:TreeNode? = null
        private var right:TreeNode? = null

        constructor(left: TreeNode? = null, right: TreeNode? = null)

        fun itemCheck():Int {
            // not null ? DO IT...
            left?.let { return (1 + left!!.itemCheck() + right!!.itemCheck()) }
            return 1
        }
    }

    fun bottomUpTree(depth:Int):TreeNode {
        if (0 < depth)
            return TreeNode(bottomUpTree(depth - 1), bottomUpTree(depth - 1))
        return TreeNode()
    }

}
