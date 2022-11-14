import java.math.BigDecimal
import java.math.RoundingMode

fun deposit(start: BigDecimal, interest: BigDecimal, years: Int): BigDecimal {
    return (
        start * (
            BigDecimal.ONE + interest.setScale(2, RoundingMode.CEILING) / BigDecimal(100.0)
        ).pow(years)
    ).setScale(2, RoundingMode.CEILING)
}

fun main() {
    // write your code here
    val earning = deposit(
        readln().toBigDecimal(),
        readln().toBigDecimal(),
        readln().toInt()
    )
    println("Amount of money in the account: $earning")
}
