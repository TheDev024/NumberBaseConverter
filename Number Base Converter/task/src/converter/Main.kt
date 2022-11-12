package converter

import java.lang.Math.pow
import kotlin.math.pow

class NumberBaseConverter {
    fun convert(n: Int, base: Int): String {
        return when (base) {
            2 -> toBinary(n)

            8 -> toOctal(n)

            16 -> toHex(n)

            else -> ""
        }
    }

    fun toBinary(n: Int): String {
        var binary = 0
        var i = 0
        var a = n
        while (a > 0) {
            binary += (a % 2) * 10.0.pow(i.toDouble()).toInt()
            a /= 2
            i += 1
        }
        return binary.toString()
    }

    fun toOctal(n: Int): String {
        var octal = 0
        var i = 0
        var a = n
        while (a > 0) {
            octal += (a % 8) * 10.0.pow(i.toDouble()).toInt()
            a /= 8
            i += 1
        }
        return octal.toString()
    }

    fun toHex(n: Int): String {
        var hex = ""
        var i = 0
        var a = n
        while (a > 0) {
            hex += when (a % 16) {
                10 -> 'A'
                11 -> 'B'
                12 -> 'C'
                13 -> 'D'
                14 -> 'E'
                15 -> 'F'
                else -> (a % 16).digitToChar()
            }
            a /= 16
        }
        return hex.reversed()
    }
}

fun main() {
    val converter = NumberBaseConverter()
    print("Enter number in decimal system:")
    val n = readln().toInt()
    print("Enter target base:")
    val base = readln().toInt()
    print("Conversion result: ")
    println(converter.convert(n, base))
}