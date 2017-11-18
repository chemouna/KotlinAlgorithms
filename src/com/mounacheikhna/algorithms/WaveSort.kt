package com.mounacheikhna.algorithms

class WaveSort {

    private fun swap(arr: IntArray, i: Int, j: Int) {
        val temp = arr[j]
        arr[j] = arr[i]
        arr[i] = temp
    }

    private fun sortInWave(arr: IntArray) {
        IntProgression.fromClosedRange(2, arr.size, 2).forEachIndexed { i, v ->
            if (v < arr[i - 1]) {
                swap(arr, i, i - 1)
            }
            if (v < arr[i + 1]) {
                swap(arr, i, i + 1)
            }
        }

    }

    /*

    if we make sure that all even positioned (at index 0, 2, 4, ..) elements are greater than their adjacent odd elements,
    we don’t need to worry about odd positioned element. Following are simple steps.
    1) Traverse all even positioned elements of input array, and do following.
        ….a) If current element is smaller than previous odd element, swap previous and current.
        ….b) If current element is smaller than next odd element, swap next and current.
     */

    fun main(args: Array<String>) {
        val ob = WaveSort()
        val arr = intArrayOf(10, 90, 49, 2, 1, 5, 23)
        ob.sortInWave(arr)
        for (i in arr)
            print(i.toString() + " ")
    }


}