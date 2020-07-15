package com.example.skillboxkotlin.Warriors
import com.example.skillboxkotlin.Weapons.AbstractWeapon
import com.example.skillboxkotlin.Weapons.Weapons

class Soldier_Intermediate(
    maxHealth: Int = 150,
    chanceOfDamage: Int = 20,
    shotAccuracy: Int = 50,
    weapon: AbstractWeapon = Weapons.rifle

) : AbstractWarrior(maxHealth, chanceOfDamage, shotAccuracy, weapon) {

    override var health: Int = maxHealth

    override fun toString(): String {
        return "Soldier_Intermediate (maxHealth = $maxHealth, chanceOfDamage = $chanceOfDamage, " +
                "shotAccuracy = $shotAccuracy, weapon = $weapon)"
    }
}
