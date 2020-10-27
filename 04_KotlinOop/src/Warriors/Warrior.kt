package com.example.skillboxkotlin.Warriors

interface Warrior {
    val isKilled: Boolean
    val chanceOfDamage: Int  // шанс избежать попадания

    fun attack(warrior: Warrior) // атаковать - на вход принимает врага (Warrior)
    fun takeDamage(damage: Int) // понести урон - на вход принимает урон (Int)
}