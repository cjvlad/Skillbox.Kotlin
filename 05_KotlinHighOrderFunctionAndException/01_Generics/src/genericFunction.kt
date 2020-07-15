import java.lang.Math.floor

fun main() {
    filterList(arrayListOf(1.2, 3.4, 5.6, 7.8, 122.0, 122.2, 9223372036854775808.0, 9223372036854775809.0)).forEach { println(it) }
    filterList(arrayListOf(2, 22, 5, 66, 77, 78, 90 )).forEach { println(it) }
}
fun <T: Number> filterList(numList: List<T>): List<T> {
    val outputList = mutableListOf<T>()
    numList.forEach {
        val castedToDouble: Double = it.toDouble()
        if (castedToDouble > Long.MAX_VALUE) {
            outputList.add(it)
        } else {
            if (floor(castedToDouble) == castedToDouble && it.toLong() % 2 == 0L) {
                outputList.add(it)
            }
        }
    }
    return outputList
}