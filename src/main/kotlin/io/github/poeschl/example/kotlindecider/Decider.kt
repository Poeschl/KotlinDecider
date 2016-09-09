package io.github.poeschl.example.kotlindecider

import java.util.*

/**
 * Project: KotlinDecider
 * Created by Markus Pöschl on 01.07.16.
 */
open class Decider(private val options: List<String>, private val random: Random = Random()) {
    companion object {
        val BASIC_ROUND_WAITING_TIME: Long = 50
        val MIN_RANDOM_ROUNDS = 30
        val MAX_RANDOM_ROUNDS = 200
        val MIN_WAIT_ADDITION = 80
        val MAX_WAIT_ADDITION = 150
    }

    var roundListener: RoundListener? = null
        set(value) {
            field = value
        }

    fun getDecision(): String {
        val rounds = getRandomRounds()
        var currentChoice = options[0]
        var waitTime = BASIC_ROUND_WAITING_TIME
        for (i in 0..rounds) {
            currentChoice = options[i % (options.size)]
            roundListener?.onRoundChoice(currentChoice)

            if (i >= rounds - 10) {
                waitTime += getRandomWaitTimeAddition()
            }
            Thread.sleep(waitTime)
        }

        return currentChoice;
    }

    private fun getRandomRounds(): Int {
        return MIN_RANDOM_ROUNDS + Math.round(random.nextDouble() * MAX_RANDOM_ROUNDS).toInt()
    }

    private fun getRandomWaitTimeAddition(): Int {
        return MIN_WAIT_ADDITION + Math.round(random.nextDouble() * MAX_WAIT_ADDITION).toInt()
    }


    interface RoundListener {
        fun onRoundChoice(roundChoice: String)
    }
}