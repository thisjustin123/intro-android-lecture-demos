package com.cornellappdev.introandroid.lecturedemos.lec6

import com.cornellappdev.introandroid.lecturedemos.lec6.retrofit.Cat
import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

@Singleton
class FakeCatNetworkingRepository @Inject constructor() {

    private val fakeCat = Cat(
//        length = "12 to 16 inches",
//        origin = "Southeast Asia",
//        familyFriendly = 3,
//        shedding = 3,
//        generalHealth = 2,
//        playfulness = 5,
//        childrenFriendly = 5,
//        grooming = 3,
//        intelligence = 5,
//        otherPetsFriendly = 5,
//        minWeight = 6,
//        maxWeight = 10,
//        minLifeExpectancy = 9,
//        maxLifeExpectancy = 15,
        imageLink = "https://api-ninjas.com/images/cats/abyssinian.jpg",
        name = "Abyssinian"
    )

    /**
     * A fake networking call that can throw an error or return a cat.
     */
    suspend fun getCats(name: String?): List<Cat> {
        // Mock a networking call delay time...
        delay(500)
        // 50% chance to throw error, 50% chance to return a cat.
        val chance = Random.nextInt(1, 101)
        return if (chance <= 50) {
            throw RuntimeException("Cat service is down! Please try again later.")
        } else {
            listOf(fakeCat)
        }
    }
}
