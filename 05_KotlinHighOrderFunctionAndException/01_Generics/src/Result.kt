sealed class Resoult< out T, R>

data class Success<T, R>(var defaultValue: T) : Resoult<T, R>()
data class Error<T, R>(var item: R) : Resoult<T, R>()


fun main() {
    val item1 :Resoult<Int,String> = getResoult()
    val item2 :Resoult<Any, String> = item1
    println(item1.toString())
    println(item2.toString())
}
fun getResoult() : Resoult<Int, String>{
    val item : Resoult<Int, String> = Success(99)
    return item
}