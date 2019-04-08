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
    }
}