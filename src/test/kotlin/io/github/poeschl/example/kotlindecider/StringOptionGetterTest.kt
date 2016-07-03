package io.github.poeschl.example.kotlindecider

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

/**
 * Project: KotlinDecider
 * Created by Markus Pöschl on 03.07.16.
 */
class StringOptionGetterTest {
    @Test
    fun testGetOptions() {
        val testGetter = StringOptionGetter("A,B,C")

        assertThat(testGetter.options).containsExactly("A", "B", "C")
    }

    @Test
    fun testParseOptions() {
        val testGetter = StringOptionGetter("")

        val parseResult = testGetter.parseString("A,B,C")

        assertThat(parseResult).containsExactly("A", "B", "C")
    }

}