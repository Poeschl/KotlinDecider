package io.github.poeschl.example.kotlindecider

import com.nhaarman.mockito_kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.mockito.Mockito.`when`
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

    @Ignore //No nice option to inject the test mock objects
    @Test
    fun testRun() {
        val optionGetterMock: MemoryOptionGetter = mock()
        `when`(optionGetterMock.options).thenReturn(arrayListOf("A", "B", "C"))
        val deciderMock: Decider = mock()
        `when`(deciderMock.getDecision()).thenReturn("A")
        val appTest = Application()

        appTest.run()

        assertThat(outContent.toString().trim()).contains("##############").endsWith("A").doesNotEndWith("B").doesNotEndWith("C")
    }

    @Test
    fun testOnRoundChoice() {
        val appTest = Application()

        appTest.onRoundChoice("Test")

        assertThat(outContent.toString().trim()).isEqualTo("Test")
    }

}