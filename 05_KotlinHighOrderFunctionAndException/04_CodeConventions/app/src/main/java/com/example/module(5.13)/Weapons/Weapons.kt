package com.example.skillboxkotlin.Weapons

object Weapons {
    object rivolver : AbstractWeapon(10, FireType.singleShot) {
        override fun createAmmo(): Ammo {
            return Ammo.LOW_POWER
        }

        override fun toString(): String {
            return "Револьвер"
        }
    }

    object rifle : AbstractWeapon(25, FireType.singleShot) {
        override fun createAmmo(): Ammo {
            return Ammo.MEDIUM_POWER
        }

        override fun toString(): String {
            return "Ружье"
        }
    }

    object sniperRifle : AbstractWeapon(15, FireType.burstingShot) {
        override fun createAmmo(): Ammo {
            return Ammo.MAX_POWER
        }

        override fun toString(): String {
            return "Снайперская винтовка"
        }
    }

    object minigun : AbstractWeapon(150, FireType.burstingShot) {
        override fun createAmmo(): Ammo {
            return Ammo.MAX_POWER
        }

        override fun toString(): String {
            return "Полумёт"
        }
    }
}
