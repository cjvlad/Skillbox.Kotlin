import com.example.skillboxkotlin.Battle

fun main() {
    print("Введите количество воинов в командах: ")
    val teamSize = readLine()?.toIntOrNull() ?: 0
    val battle = Battle(teamSize)
    println("Начало битвы")
    while (!battle.isBattleFinished) {
        println(battle.getBattleState())
        battle.makeIteration()
    }
}
