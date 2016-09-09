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
    private val errContent = ByteArrayOutputStream()

    @Before
    fun setup() {
        System.setOut(PrintStream(outContent))
        System.setErr(PrintStream(errContent))
    }

    @Test
    fun testOnRoundChoice() {
        val appTest = Application(arrayOf("-o", "A"))

        appTest.onRoundChoice("Test")

        assertThat(outContent.toString().trim()).isEqualTo("Test")
    }

    @Test
    fun testWrongCommandLineInput() {
        val appTest = Application(arrayOf("-foobar"))

        appTest.run()

        assertThat(errContent.toString().trim()).contains("is not a valid option")
        assertThat(outContent.toString().trim()).doesNotContain("##############")
    }

    @Test
    fun testOptionsInCommandLine() {
        val appTest = Application(arrayOf("-o", "A,B,C"))

        appTest.run()

        assertThat(errContent.toString().trim()).isEmpty()
        assertThat(outContent.toString().trim()).contains("##############")
    }

    @Test
    fun testSeveralRuns() {
        val appTest = Application(arrayOf("-o", "A,B,C", "-r", "2"))

        appTest.run()

        //Checks if the printout contains two results in the end
        assertThat(outContent.toString().trim()).matches("(.*|\n)*#+\n(.+\n?){2}")
    }
}