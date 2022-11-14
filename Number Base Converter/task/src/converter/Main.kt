package converter

import java.math.BigDecimal
import java.math.BigInteger
import java.math.RoundingMode
import java.util.*

val scanner = Scanner(System.`in`)

/**
 *
 */
class NumberBaseConverter {

    fun convert(n: String, from: BigInteger, to: BigInteger): String {
        val dec = toDecimal(n, from)
        return fromDecimal(dec, to)
    }

    private fun toDecimal(number: String, base: BigInteger): String = when {
        !number.contains(".") -> toDecimalInteger(number, base)

        number.contains(".") && number.split(".")[1].matches(Regex("^0*0$")) -> "${
            toDecimalInteger(
                number.split(".")[0], base
            )
        }.${number.split(".")[1]}"

        else -> "${toDecimalInteger(number.split(".")[0], base)}${
            toDecimalFraction(
                number.split(".")[1], base.toBigDecimal()
            )
        }"
    }

    private fun toDecimalInteger(number: String, base: BigInteger): String {
        var converted = BigInteger.ZERO
        var i = number.lastIndex
        for (digit in number.uppercase()) {
            val n = when (digit) {
                in '0'..'9' -> digit.digitToInt().toBigInteger()
                else -> (digit.code - 55).toBigInteger()
            }
            converted += n * base.pow(i--)
        }
        return converted.toString()
    }

    private fun toDecimalFraction(fraction: String, base: BigDecimal): String {
        var convertedFraction: BigDecimal = BigDecimal.ZERO
        var i = 1
        for (char in fraction.uppercase()) {
            val digit = when (char) {
                in '0'..'9' -> char.digitToInt().toBigDecimal()
                else -> char.code.toBigDecimal() - 55.toBigDecimal()
            }
            convertedFraction += digit.setScale(5) / base.pow(i++)
        }
        var convertedString = convertedFraction.toString().removePrefix("0")
        if (convertedString.length < 6) repeat(6 - convertedString.length) { convertedString += '0' }
        return convertedString
    }

    private fun fromDecimal(number: String, base: BigInteger): String = when {
        !number.contains(".") -> fromDecimalInteger(number, base)

        number.contains(".") && number.split(".")[1].matches(Regex("^0*0$")) -> "${
            fromDecimalInteger(
                number.split(".")[0], base
            )
        }.${number.split(".")[1]}"

        else -> "${fromDecimalInteger(number.split(".")[0], base)}${
            fromDecimalFraction(
                number.split(".")[1], base.toBigDecimal()
            )
        }"
    }

    private fun fromDecimalInteger(number: String, base: BigInteger): String {
        var converted = ""
        var n = number.toBigInteger()
        while (n > BigInteger.ZERO) {
            val digit = when (val rem = n.divideAndRemainder(base)[1]) {
                in BigInteger.ZERO..BigInteger.valueOf(9) -> rem.toInt().digitToChar()
                else -> (rem + 55.toBigInteger()).toInt().toChar()
            }
            converted += digit
            n /= base
        }
        return converted.reversed()
    }

    private fun fromDecimalFraction(fraction: String, base: BigDecimal): String {
        var converted = "."
        var n = "0.$fraction".toBigDecimal()
        var i = 0
        while (n > BigDecimal.ZERO && i++ < 5) {
            val rem = (n * base).setScale(0, RoundingMode.FLOOR)
            val digit = when (rem) {
                in BigDecimal.ZERO..BigDecimal(9.0) -> rem.toString()
                else -> (rem + 55.toBigDecimal()).toInt().toChar().toString()
            }
            n = n * base - rem
            converted += digit
        }
        if (converted.length < 6) repeat(6 - converted.length) { converted += '0' }
        return converted
    }
}

fun main() {
    val converter = NumberBaseConverter()
    core@ while (true) {
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
