package io.github.poeschl.example.kotlindecider

import java.util.*

/**
 * Project: KotlinDecider
 * Created by Markus Pöschl on 01.07.16.
 */
open class Decider(private val options: List<String>, private val random: Random = Random()) {
    companion object {
        val ROUND_WAITING_TIME: Long = 50
        val MAX_RANDOM_ROUNDS = 200
    }

    var roundListener: RoundListener? = null
        set(value) {
            field = value
        }

    fun getDecision(): String {
        val rounds = Math.round(random.nextDouble() * MAX_RANDOM_ROUNDS).toInt()
        var currentChoice = options[0]
        for (i in 0..rounds) {
            currentChoice = options[i % (options.size)]
            roundListener?.onRoundChoice(currentChoice)

            Thread.sleep(ROUND_WAITING_TIME)
        }

        return currentChoice;
    }


    interface RoundListener {
        fun onRoundChoice(roundChoice: String)
    }
}