package util

class Print
{
    companion object
    {
        fun printArray(intArray: IntArray, nomeArray: String = "")
        {
            print("\n\n${intArray.javaClass.canonicalName}: $nomeArray")
            for ( i in 0 .. (intArray.size-1) )
                print(" " + intArray[i])
        }

        fun printMatriz(intMatriz: Array<IntArray>, nomeMatriz: String = "")
        {
            print("\n\n${intMatriz.javaClass.canonicalName}: $nomeMatriz")
            for (i in 0 .. (intMatriz.size-1))
            {
                print("\n")
                for (j in 0 .. (intMatriz.size - 1))
                    print(" ${intMatriz[i][j]}")
            }
        }

        fun printMatriz(floatMatriz: Array<FloatArray>, nomeMatriz: String = "")
        {
            print("\n\n${floatMatriz.javaClass.canonicalName}: $nomeMatriz")
            for (i in 0 .. (floatMatriz.size-1))
            {
                print("\n")
                for (j in 0 .. (floatMatriz.size - 1))
                    print(" ${floatMatriz[i][j]}")
            }
        }

        fun printMatriz(doubleMatriz: Array<DoubleArray>, nomeMatriz: String = "")
        {
            print("\n\n${doubleMatriz.javaClass.canonicalName}: $nomeMatriz")
            for (i in 0 .. (doubleMatriz.size-1))
            {
                print("\n")
                for (j in 0 .. (doubleMatriz.size - 1))
                    print(" ${doubleMatriz[i][j]}")
            }
        }
    }
}