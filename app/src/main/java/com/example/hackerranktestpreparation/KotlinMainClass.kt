package com.example.hackerranktestpreparation

fun main() {


//    reverseArray(arrayOf(2, 3, 4, 5, 6))
//    println(timeConversion("12:00:00AM"))
//    plusMinus(arrayOf<Int>(12, 15, -11, -3, 0))
//    plusMinus(arrayOf<Int>(12, 15, -11, -3, 0))
//    fizzBuzz(12)
}

fun reverseArray(a: Array<Int>): Array<Int> {
    // Write your code here

    val sizeArr = a.size
    val reversedArr = Array(sizeArr) { 0 }
    for (i in 0 until sizeArr) {
        reversedArr[i] = a[(sizeArr - 1) - i]
    }

    return reversedArr

}

fun timeConversion(s: String): String {
    // Write your code here

    var hour = s.substring(0, 2)
    val mint = s.substring(3, 5)
    val sec = s.substring(6, 8)
    val amPm = s.substring(8, 10)

    if (amPm == "AM") {
        if (hour == "12")
            return "00:$mint:$sec"
        return "$hour:$mint:$sec"
    } else {
        if (hour == "12")
            return "$hour:$mint:$sec"
        val sum = hour.toInt() + 12
        return "$sum:$mint:$sec"
    }

}

fun miniMaxSum(arr: Array<Int>): Unit {
    // Write your code here
    val minValue = arr.min()
    val maxValue = arr.max()
    var totalSum = 0L
    arr.forEach { totalSum += it }

    val minSum = totalSum - maxValue
    val maxSum = totalSum - minValue

    println("$minSum $maxSum")
}

fun plusMinus(arr: Array<Int>): Unit {
    // Write your code here

    val arrSize: Float = arr.size.toFloat()
    val positiveNumbers = String.format("%.6f", arr.count { it > 0 } / arrSize)
    val negativeNumbers = String.format("%.6f", arr.count { it < 0 } / arrSize)
    val zeroNumbers = String.format("%.6f", arr.count { it == 0 } / arrSize)

    println("$positiveNumbers $negativeNumbers $zeroNumbers")

}

fun fizzBuzz(n: Int): Unit {
    // Write your code here
    for (i in 1..n) {
        if (i % 3 == 0 && i % 5 == 0) {
            println("FizzBuzz")
        } else if (i % 3 == 0) {
            println("Fizz")
        } else if (i % 5 == 0) {
            println("Buzz")
        } else {
            println("" + i)
        }
    }

}