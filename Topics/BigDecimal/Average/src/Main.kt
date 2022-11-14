import java.math.BigDecimal
import java.math.RoundingMode

fun main() {
    // write your code here
    val a = readln().toBigDecimal()
    val b = readln().toBigDecimal()
    val c = readln().toBigDecimal()
    val average = (a + b + c).setScale(0, RoundingMode.FLOOR) / BigDecimal(3.0).setScale(0, RoundingMode.FLOOR)
    println(average)
}