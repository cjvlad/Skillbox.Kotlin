package com.example.skillboxkotlin.Warriors
import com.example.skillboxkotlin.Weapons.AbstractWeapon
import com.example.skillboxkotlin.Weapons.Weapons

class Soldier_Advanced(
    maxHealth: Int = 200,
    chanceOfDamage: Int = 85,
    shotAccuracy: Int = 75,
    weapon: AbstractWeapon = Weapons.minigun

) : AbstractWarrior(maxHealth, chanceOfDamage, shotAccuracy, weapon) {

    override var health: Int = maxHealth

    override fun toString(): String {
        return "Soldier_Advanced (maxHealth = $maxHealth, chanceOfDamage = $chanceOfDamage, " +
                "shotAccuracy = $shotAccuracy, weapon = $weapon)"
    }
}
