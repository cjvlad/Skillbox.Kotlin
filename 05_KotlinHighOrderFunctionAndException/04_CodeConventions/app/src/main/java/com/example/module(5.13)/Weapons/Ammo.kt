package com.example.skillboxkotlin.Weapons
import kotlin.math.roundToInt

enum class Ammo(
    private val damage: Int, // урон
    private val criticalDamageChance: Int, // шанс критического урона
    private val criticalDamageRatio: Int // коэффициент критического урона
) {

    LOW_POWER(5, 5, 1),
    MEDIUM_POWER(15, 25, 2),
    MAX_POWER(20, 40, 5);

    fun getCurrentDamage(): Int { // получение текущего урона
        return if ((Math.random() * 100).roundToInt() > criticalDamageChance)
            damage
        else
            damage * criticalDamageRatio
    }
}
