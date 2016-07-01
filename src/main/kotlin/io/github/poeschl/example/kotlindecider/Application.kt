package io.github.poeschl.example.kotlindecider

/**
 * Project: KotlinDecider
 * Created by Markus Pöschl on 01.07.16.
 */

fun main(args: Array<String>) {
    Application().run()
}

class Application : Decider.RoundListener {
    private val optionGetter: OptionGetter
    private val decider: Decider

    init {
        optionGetter = MemoryOptionGetter()
        decider = Decider(optionGetter.options)
    }

    fun run() {

        decider.roundListener = this
        val finalChoice = decider.getDecision()
        println("##############")
        println(finalChoice)
    }

    override fun onRoundChoice(roundChoice: String) {
        println(roundChoice)
    }
}
