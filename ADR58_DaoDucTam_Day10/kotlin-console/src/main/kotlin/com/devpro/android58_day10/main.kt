package com.devpro.android58_day10

/**
 * Demo Kotlin cơ bản - Android58 Day10
 * Nhập xuất dữ liệu từ bàn phím
 */

// ========== MAIN ==========
fun main() {
    println("╔══════════════════════════════════════╗")
    println("║     DEMO KOTLIN CƠ BẢN - DAY 10      ║")
    println("╚══════════════════════════════════════╝")

    demoHomework()
}

fun demoHomework() {
    println("--- Giải Bài Tập Về Nhà ---")

    // Bài 1: Compress chuỗi
    val s1 = "aaabbcaaa"
    println("Bài 1: Compress \"$s1\" -> ${compressString(s1)}")
    println("Bài 1: Compress \"abc\" -> ${compressString("abc")}")
    println("Bài 1: Compress \"aabbcc\" -> ${compressString("aabbcc")}")

    // Bài 2: Tính giai thừa
    val n = 5
    println("Bài 2: $n! = ${factorial(n)}")

    // Bài 3: Tìm số lớn thứ nhì
    val list3 = listOf(10, 5, 8, 20, 15, 20, 12)
    println("Bài 3: Số lớn thứ nhì trong $list3 là ${findSecondLargest(list3)}")

    // Bài 4: Chuỗi liên tiếp tăng dài nhất
    val list4 = listOf(1, 3, 5, 4, 7, 8, 9, 2)
    println("Bài 4: Độ dài chuỗi liên tiếp tăng dài nhất của $list4 là ${longestIncreasingSubsequenceLength(list4)}")

    // Bài 5: Chuyển số La Mã thành số nguyên
    val roman = "LVIII"
    println("Bài 5: La Mã \"$roman\" -> ${romanToInt(roman)}")
    println("Bài 5: La Mã \"IX\" -> ${romanToInt("IX")}")
    println("Bài 5: La Mã \"XII\" -> ${romanToInt("XII")}")
}

// Bài 1: Compress chuỗi
fun compressString(s: String): String {
    if (s.isEmpty()) return ""
    val result = StringBuilder()
    var count = 1
    for (i in 1 until s.length) {
        if (s[i] == s[i - 1]) {
            count++
        } else {
            result.append(s[i - 1])
            if (count > 1) result.append(count)
            count = 1
        }
    }
    result.append(s.last())
    if (count > 1) result.append(count)
    return result.toString()
}

// Bài 2: Tính giai thừa n!
fun factorial(n: Int): Long {
    if (n < 0) return -1
    var res = 1L
    for (i in 1..n) {
        res *= i
    }
    return res
}

// Bài 3: Tìm số lớn thứ nhì (không dùng hàm có sẵn)
fun findSecondLargest(list: List<Int>): Int? {
    if (list.size < 2) return null
    var firstMax = Int.MIN_VALUE
    var secondMax = Int.MIN_VALUE

    for (num in list) {
        if (num > firstMax) {
            secondMax = firstMax
            firstMax = num
        } else if (num > secondMax && num < firstMax) {
            secondMax = num
        }
    }
    return if (secondMax == Int.MIN_VALUE) null else secondMax
}

// Bài 4: Tìm độ dài chuỗi liên tiếp tăng dài nhất
fun longestIncreasingSubsequenceLength(list: List<Int>): Int {
    if (list.isEmpty()) return 0
    var maxLen = 1
    var currentLen = 1
    for (i in 1 until list.size) {
        if (list[i] > list[i - 1]) {
            currentLen++
        } else {
            if (currentLen > maxLen) maxLen = currentLen
            currentLen = 1
        }
    }
    return if (currentLen > maxLen) currentLen else maxLen
}

// Bài 5: Chuyển số La Mã thành số nguyên
fun romanToInt(s: String): Int {
    val romanMap = mapOf(
        'I' to 1, 'V' to 5, 'X' to 10, 'L' to 50,
        'C' to 100, 'D' to 500, 'M' to 1000
    )
    var total = 0
    for (i in 0 until s.length) {
        val current = romanMap[s[i]] ?: 0
        if (i + 1 < s.length) {
            val next = romanMap[s[i + 1]] ?: 0
            if (current < next) {
                total -= current
            } else {
                total += current
            }
        } else {
            total += current
        }
    }
    return total
}

fun add(a: Int, b: Int) = a + b

fun greet(name: String = "Hải", greeting: String? = null): String {
    return "${greeting ?: ""}, $name!"
}

fun isLeapYear(year: Int): Boolean = (year % 4 == 0 && year % 100 == 0) || year % 400 == 0

fun isPrime(number: Int): Boolean {
    if (number < 2) return false
    for (i in 2..Math.sqrt(number.toDouble()).toInt()) {
        if (number % i == 0) return false
    }
    return true
}
