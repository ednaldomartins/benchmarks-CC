package util

class Print
{
    companion object
    {
        fun printArray(intArray: IntArray)
        {
            print("\n\nIntArray: ")
            for ( i in 0 .. (intArray.size-1) )
                print(" " + intArray[i])
        }

        fun printMatriz(intMatriz: Array<IntArray>)
        {
            for (i in 0 .. intMatriz.size-1)
            {
                print("\n")
                for (j in 0..intMatriz.size - 1)
                    print(" ${intMatriz[i][j]}")
            }
        }

        fun printMatriz(floatMatriz: Array<FloatArray>)
        {
            for (i in 0 .. floatMatriz.size-1)
            {
                print("\n")
                for (j in 0..floatMatriz.size - 1)
                    print(" ${floatMatriz[i][j]}")
            }
        }
    }
}