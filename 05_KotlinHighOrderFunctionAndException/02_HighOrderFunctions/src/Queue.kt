fun main() {
    val queueList = Queue(mutableListOf("Москва"))
    queueList.enqueue("Новосибирск")
    queueList.enqueue("Казань")
    queueList.enqueue("Красноярск")
    queueList.enqueue("Санкт-Петербург")
    queueList.enqueue("Екатеринбург")
    queueList.enqueue("Омск")

    val sortedQueue = queueList.filter { name -> name.length > 10 }
    println(sortedQueue)
}

class Queue<T>(list: MutableList<T>) {
    private var queueList = list

    fun enqueue(item: T) {
        queueList.add(item)
    }

    fun filter(lambda: (T) -> Boolean): Queue<T> {
        return Queue(this.queueList.filter { lambda(it) }.toMutableList())
    }

    override fun toString(): String {
        return "Queue(queueList=$queueList)"
    }
}