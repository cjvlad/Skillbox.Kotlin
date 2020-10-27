package com.example.skillboxkotlin
import com.example.skillboxkotlin.Warriors.AbstractWarrior
import com.example.skillboxkotlin.Warriors.Soldier_Beginner
import com.example.skillboxkotlin.Warriors.Soldier_Intermediate
import com.example.skillboxkotlin.Warriors.Soldier_Advanced
import kotlin.math.roundToInt

class Team(val teamSize: Int) {

    var teamList = mutableListOf<AbstractWarrior>()

    private fun makeTeam() {
        val range = 1..teamSize
        for (item in range) {
            when ((100 * Math.random()).roundToInt()) {
                in 0..50 -> teamList.add(Soldier_Beginner())
                in 51..85 -> teamList.add(Soldier_Intermediate())
                in 86..100 -> teamList.add(Soldier_Advanced())
            }
        }
    }

    init {
        makeTeam()
    }

    fun countHealth(): Int {
        var countHealth = 0

        for (item in teamList) {
            countHealth += item.health
        }
        return countHealth
    }
}
