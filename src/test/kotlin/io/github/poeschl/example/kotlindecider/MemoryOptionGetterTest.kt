package io.github.poeschl.example.kotlindecider

import org.assertj.core.api.Assertions
import org.junit.Test

/**
 * Project: KotlinDecider
 * Created by Markus Pöschl on 01.07.16.
 */
class MemoryOptionGetterTest {

    @Test
    fun testGetOptions() {
        val listToCheck = arrayListOf("Kabir", "PUNJAB PALACE", "Hot Wok", "Rewe", "Pizza and More")

        val getter = MemoryOptionGetter()

        Assertions.assertThat(getter.options).containsAll(listToCheck)
    }
}