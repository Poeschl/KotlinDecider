package io.github.poeschl.example.kotlindecider

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.Assertions
import org.junit.Test
import org.mockito.Mockito.`when`
import java.util.*

/**
 * Project: KotlinDecider
 * Created by Markus Pöschl on 01.07.16.
 */
class DeciderImplTest {

    @Test
    fun testLongestDecision() {
        val testList = arrayListOf("A", "B", "C")
        val randomMock: Random = mock()
        `when`(randomMock.nextDouble()).thenReturn(1.0)
        val testDecider = Decider(testList, randomMock)

        val decisionResult = testDecider.getDecision()

        val expectedIndex = Decider.MAX_RANDOM_ROUNDS % 3
        Assertions.assertThat(decisionResult).isEqualTo(testList[expectedIndex])
    }

    @Test
    fun testQuickestDecision() {
        val testList = arrayListOf("A", "B", "C")
        val randomMock: Random = mock()
        `when`(randomMock.nextDouble()).thenReturn(0.0)
        val testDecider = Decider(testList, randomMock)

        val decisionResult = testDecider.getDecision()

        Assertions.assertThat(decisionResult).isEqualTo(testList[0])
    }

    @Test
    fun testRoundListener() {
        val testList = arrayListOf("A", "B", "C")
        val randomMock: Random = mock()
        `when`(randomMock.nextDouble()).thenReturn(0.1)
        val listenerMock: Decider.RoundListener = mock()
        val testDecider = Decider(testList, randomMock)
        testDecider.roundListener = listenerMock

        testDecider.getDecision()

        verify(listenerMock, com.nhaarman.mockito_kotlin.times(Decider.MAX_RANDOM_ROUNDS / 10 + 1)).onRoundChoice(any())
    }
}