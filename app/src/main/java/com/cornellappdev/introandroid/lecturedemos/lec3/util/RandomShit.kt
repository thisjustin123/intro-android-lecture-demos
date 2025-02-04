package com.cornellappdev.introandroid.lecturedemos.lec3.util

import kotlin.random.Random

val randomShit = listOf(
    "Plink",
    "Plonk",
    "Hello everybody my name is Markiplier",
    "42",
    "follow @thiscookin_g",
    "android best subteam",
    "please do not the car",
    "consume uranium",
    "de tasty my beloved",
    "help",
    "android good job market",
    "my life for aiur",
    "josten go",
    "oooooooh im blinded by the lights",
    "dont do drugs. do android development."
)

fun randomSelect(): List<String> {
    val size = Random.nextInt(10, 20)
    val myList = mutableListOf<String>()
    for (i in 0 until size) {
        myList.add(randomShit.random())
    }
    return myList
}
