fun main() {
    val queueList = Queue1<String>()
    queueList.enqueue("1-ый")
    queueList.enqueue("2-ой")
    println(queueList.dequeue())
    println(queueList.dequeue())
}

class Queue1 <T>() {
    private val listInt = mutableListOf<T>()

    fun enqueue(item: T) {
        listInt.add(item)
    }

    fun dequeue(): T? {
        if(listInt.isNotEmpty()){
            return listInt.removeAt(0)
        }
        return null
    }
}