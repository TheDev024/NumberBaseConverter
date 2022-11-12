package converter

import kotlin.math.pow

class NumberBaseConverter {
    fun convertFrom(n: Int, base: Int): String {
        return when (base) {
            2 -> toBinary(n)

            8 -> toOctal(n)

            16 -> toHex(n)

            else -> ""
        }
    }

    fun convertTo(n: String, base: Int): Int = toDecimal(base, n)

    private fun toDecimal(base: Int, n: String): Int {
        var number = 0

        var i = 0

        for (digit in n.reversed()) {
            val a = when(digit) {
                'A' -> 10

                'B' -> 11

                'C' -> 12

                'D' -> 13

                'E' -> 14

                'F' -> 15

                else -> digit.digitToInt()
            }

            number += a * base.toDouble().pow(i++).toInt()
        }

        return number
    }

    private fun toBinary(n: Int): String {
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

    private fun toOctal(n: Int): String {
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

    private fun toHex(n: Int): String {
        var hex = ""
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

    while (true) {
        print("Do you want to convertFrom /from decimal or /to decimal? (To quit type /exit) ")
        when (readln()) {
            "/exit" -> break

            "/from" -> {
                print("Enter number in decimal system:")
                val n = readln().toInt()
                print("Enter target base:")
                val base = readln().toInt()
                print("Conversion result: ")
                println(converter.convertFrom(n, base))
            }

            "/to" -> {
                print("Enter source number: ")
                val source = readln()
                print("Enter source base: ")
                val base = readln().toInt()
                println("Conversion to decimal result: ${converter.convertTo(source.uppercase(), base)}")
            }
        }

    }
}