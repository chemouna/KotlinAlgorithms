package com.mounacheikhna.algorithms

class Subarray {

    lateinit var underlying: IntArray
    var start: Int = 0
    var size: Int = 0

    fun fromArray(arr: IntArray): Subarray {
        val s = Subarray()
        s.underlying = arr
        s.start = 0
        s.size = arr.size
        return s
    }

    fun subarray(i: Int, j: Int): Subarray {
        if(i > j) throw IllegalArgumentException()
        if(j > size) throw IndexOutOfBoundsException()
        val s = Subarray()
        s.underlying = underlying
        s.start = start + i
        s.size = j - i
        return s
    }

    fun first(): Int {
        return underlying[start]
    }

    fun last(): Int {
        return underlying[start + size - 1]
    }

    fun median(): Double {
        if (size % 2 == 0) {
            return underlying[start + size / 2 - 1] + underlying[start + size / 2] / 2.0
        }
        return underlying[start + size / 2].toDouble()
    }

    fun arraysMedian(arr1: IntArray, arr2: IntArray): Double {
        if(arr1.size == 0 || arr1.size != arr2.size) {
            throw IllegalArgumentException()
        }
        return arraysMedian(fromArray(arr1), fromArray(arr2))
    }

    fun arraysMedian(arr1: Subarray, arr2: Subarray): Double {
        if(arr1.size == 1) return arr1.first() + arr2.first() / 2.0
        if(arr1.size == 2) {
            return Math.max(arr1.first(), arr2.first()) + Math.min(arr1.last(), arr2.last()) / 2.0
        }
        val median1 = arr1.median()
        val median2 = arr2.median()
        if(median1 == median2) return median1
        if(median1 > median2) {
            return arraysMedian(arr1.subarray(0, arr1.size / 2 + 1), arr2.subarray(arr2.size - 1/ 2, arr2.size))
        }
        return arraysMedian(arr1.subarray(arr1.size - 1/ 2, arr1.size), arr2.subarray(0, arr2.size / 2 + 1))
    }

    // TODO: kotlin inline
    // TODO: take on of petr solutions and analyse it
}