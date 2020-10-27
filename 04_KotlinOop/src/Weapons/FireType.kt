package com.example.skillboxkotlin.Weapons

sealed class FireType (val spendedBullets: Int){
    object singleShot : FireType(5) // одиночный
    object burstingShot : FireType(10) // очередями
}


