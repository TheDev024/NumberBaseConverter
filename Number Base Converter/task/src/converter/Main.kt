package converter

import java.math.BigInteger
import java.util.*

val scanner = Scanner(System.`in`)

class NumberBaseConverter {
    fun convert(n: String, from: BigInteger, to: BigInteger): String {
        val dec = toDecimal(n, from)
        return fromDecimal(dec, to)
    }

    private fun toDecimal(number: String, base: BigInteger): BigInteger {
        var converted = BigInteger.ZERO
        var i = number.lastIndex
        for (digit in number.uppercase()) {
            val n = when (digit) {
                in '0'..'9' -> digit.digitToInt().toBigInteger()
                else -> (digit.code - 55).toBigInteger()
            }
            converted += n * base.pow(i--)
        }
        return converted
    }

    private fun fromDecimal(number: BigInteger, base: BigInteger): String {
        var converted = ""
        var n = number
        while (n > BigInteger.ZERO) {
            val digit = when (val rem = n.divideAndRemainder(base)[1]) {
                in BigInteger.ZERO..BigInteger.valueOf(9) -> rem.toInt().digitToChar()
                else -> (rem + 55.toBigInteger()).toInt().toChar()
            }
            converted += digit
            n /= base
        }
        converted = converted.reversed()

        return converted
    }
}

fun main() {
    val converter = NumberBaseConverter()
    core@while (true) {
        print("Enter two numbers in format: {source base} {target base} (To quit type /exit) ")
        val input = scanner.nextLine()
        if (input == "/exit") break@core
        val (from, to) = input.split(" ")
        while (true) {
            print("Enter number in base $from to convert to base $to (To go back type /back) ")
            val n = scanner.nextLine()
            if (n == "/back") break
            println("Conversion result: " + converter.convert(n, from.toBigInteger(), to.toBigInteger()))
        }
    }
}
