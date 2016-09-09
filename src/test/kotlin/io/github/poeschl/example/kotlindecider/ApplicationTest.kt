package io.github.poeschl.example.kotlindecider

import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

/**
 * Project: KotlinDecider
 * Created by Markus Pöschl on 01.07.16.
 */
class ApplicationTest {

    private val outContent = ByteArrayOutputStream()

    @Before
    fun setup() {
        System.setOut(PrintStream(outContent))
    }

    //TODO: Find a nice way to inject mocked new instances AND test also with cmd arguments

    @Test
    fun testOnRoundChoice() {
        val appTest = Application(arrayOf("-o", "A"))

        appTest.onRoundChoice("Test")

        assertThat(outContent.toString().trim()).isEqualTo("Test")
    }
}