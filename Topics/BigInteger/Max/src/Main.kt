fun main() {
    val a = readln().toBigInteger()
    val b = readln().toBigInteger()
    val max = (a + b + (a - b).abs()) / 2.toBigInteger()
    println(max)
}