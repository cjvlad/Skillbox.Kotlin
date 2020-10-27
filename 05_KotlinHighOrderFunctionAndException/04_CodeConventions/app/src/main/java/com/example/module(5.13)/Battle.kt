package com.example.skillboxkotlin

class Battle(val teamSize: Int) {

    val firstTeam = Team(teamSize) // команда 1 (Team)
    val secondTeam = Team(teamSize) // команда 2 (Team)
    var isBattleFinished: Boolean = false // битва завершена

    fun getBattleState(): BattleState { // получить состояние битвы
        var livingWarriorsFirstTeam = 0
        var livingWarriorsSecondTeam = 0

        for (item1 in firstTeam.teamList) {
            if (item1.isKilled) continue
            livingWarriorsFirstTeam += 1
        }
        for (item2 in secondTeam.teamList) {
            if (item2.isKilled) continue
            livingWarriorsSecondTeam += 1
        }

        if (livingWarriorsFirstTeam != 0 && livingWarriorsSecondTeam != 0)
            return BattleState.Progress(firstTeam.countHealth(), secondTeam.countHealth())
        else if (livingWarriorsFirstTeam == 0 && livingWarriorsSecondTeam != 0) {
            isBattleFinished = true
            return BattleState.SecondTeamWon
        } else if (livingWarriorsFirstTeam != 0 && livingWarriorsSecondTeam == 0) {
            isBattleFinished = true
            return BattleState.FirstTeamWon
        } else {
            isBattleFinished = true
            return BattleState.Draw
        }
    }

    fun makeIteration() { // совершить следующую итерацию битвы
        shuffled()
        for (elem in 0 until teamSize) {
            if (!firstTeam.teamList[elem].isKilled && !secondTeam.teamList[elem].isKilled) {
                firstTeam.teamList[elem].attack(secondTeam.teamList[elem])
                secondTeam.teamList[elem].attack(firstTeam.teamList[elem])
            } else continue
        }
    }

    private fun shuffled() {
        firstTeam.teamList.shuffle()
        secondTeam.teamList.shuffle()
    }
}
