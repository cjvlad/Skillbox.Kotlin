package com.example.skillboxkotlin.Weapons

import NoAmmoException
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

    fun getAmmo() {
        if (fireType is FireType.singleShot) {
            if (isHaveAmmo) {
                listAmmo.removeAt(listAmmo.lastIndex)
            } else throw NoAmmoException()
        } else {
            for (currentElem in 1..FireType.burstingShot.spendedBullets) {
                if (isHaveAmmo) {
                    listAmmo.removeAt(listAmmo.lastIndex)
                } else throw NoAmmoException()
            }
        }
    }
}
