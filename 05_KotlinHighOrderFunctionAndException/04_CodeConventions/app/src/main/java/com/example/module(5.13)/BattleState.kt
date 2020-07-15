package com.example.skillboxkotlin

sealed class BattleState {

    class Progress(var commandAHealth: Int, var commandBHealth: Int) : BattleState() { // прогресс
        override fun toString(): String {
            return "Progress (commandAHealth = $commandAHealth, commandBHealth = $commandBHealth)"
        }
    }
    object FirstTeamWon : BattleState() { // команда 1 одержала победу
        override fun toString(): String {
            return "Победила первая команда"
        }
    }
    object SecondTeamWon : BattleState() { // команда 2 одержала победу
        override fun toString(): String {
            return "Победила вторая команда"
        }
    }
    object Draw : BattleState() { // ничья
        override fun toString(): String {
            return "Битва закончилась вничью"
        }
    }
}
