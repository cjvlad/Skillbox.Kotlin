package com.example.skillboxkotlin.Warriors
import com.example.skillboxkotlin.Weapons.AbstractWeapon
import com.example.skillboxkotlin.Weapons.FireType
import kotlin.math.roundToInt


abstract class AbstractWarrior(
    val maxHealth: Int, // максимальный уровень здоровья
    override val chanceOfDamage: Int, // шанс избежать попадания
    val shotAccuracy: Int, // точность - вероятность попадания
    val weapon: AbstractWeapon // оружие
) : Warrior {

    override val isKilled: Boolean
        get() {
            return health <= 0
        }

    open var health: Int = maxHealth // текущий уровень здоровья

    override fun attack(warrior: Warrior) {
        if (weapon.fireType is FireType.singleShot) {
                weapon.getAmmo()
                weapon.reload()
            }
            if (!((100 * Math.random()).roundToInt() > shotAccuracy &&
                        (100 * Math.random()).roundToInt() > warrior.chanceOfDamage)
            ) {
                val totalDamage = weapon.createAmmo().getCurrentDamage()
                warrior.takeDamage(totalDamage)
            }
            else {
                weapon.getAmmo()
                weapon.reload()
                var totalDamageSum = 0
            for (currentElem in 1..FireType.burstingShot.spendedBullets) {
                if (!((100 * Math.random()).roundToInt() > shotAccuracy &&
                            (100 * Math.random()).roundToInt() > warrior.chanceOfDamage)
                ) {
                    val totalDamage = weapon.createAmmo().getCurrentDamage()
                    totalDamageSum += totalDamage
                }
            }
                warrior.takeDamage(totalDamageSum)
        }
    }

    override fun takeDamage(damage: Int) { // понести урон
        if ((health - damage) > 0)
            health -= damage
        else
            health = 0
    }
}