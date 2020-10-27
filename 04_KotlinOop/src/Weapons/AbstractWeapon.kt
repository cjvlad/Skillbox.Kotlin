package com.example.skillboxkotlin.Weapons

import kotlin.math.min

abstract class AbstractWeapon(
    private val sizeOfMagazine: Int, // максимальное количество патронов в магазине
    val fireType: FireType
) {
    private var listAmmo: MutableList<Ammo> = mutableListOf() // текущий список патронов
    val isHaveAmmo: Boolean // факт наличия патронов
        get() = listAmmo.isNotEmpty()

    abstract fun createAmmo(): Ammo // создание патрона

    fun reload() { // перезарядка
        val newListOfAmmo = mutableListOf<Ammo>()
        val range = 1..sizeOfMagazine
        for (currentAmmo in range) {
            newListOfAmmo.add(createAmmo())
        }
        listAmmo = newListOfAmmo
    }

    fun getAmmo(): MutableList<Ammo> { // получение патронов для выстрела
        val list = mutableListOf<Ammo>()
        val count = min(listAmmo.size, fireType.spendedBullets)
        for (i in 0 until count) {
            list.add(listAmmo.removeAt(i))
        }
        return list
    }
}
