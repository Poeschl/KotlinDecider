package io.github.poeschl.example.kotlindecider

import org.kohsuke.args4j.CmdLineException
import org.kohsuke.args4j.CmdLineParser
import org.kohsuke.args4j.Option

/**
 * Project: KotlinDecider
 * Created by Markus Pöschl on 01.07.16.
 */

fun main(args: Array<String>) {
    Application(args).run()
}

class Application(args: Array<String> = emptyArray()) : Decider.RoundListener {
    private val optionGetter: OptionGetter
    private val decider: Decider

    @Option(name = "--options", usage = "Specify a comma separated list of options to choose from.", aliases = arrayOf("-o"))
    private var cmdInputOptions = ""

    private var correctInitialized = true

    init {
        parseArgs(args)

        optionGetter = StringOptionGetter(cmdInputOptions)
        decider = Decider(optionGetter.options)
    }

    fun run() {
        if (correctInitialized) {
            decider.roundListener = this
            val finalChoice = decider.getDecision()
            println("##############")
            println(finalChoice)
        }
    }

    override fun onRoundChoice(roundChoice: String) {
        println(roundChoice)
    }

    private fun parseArgs(args: Array<String>) {
        val cmdParser = CmdLineParser(this)

        try {
            cmdParser.parseArgument(args.toMutableList())

        } catch (ex: CmdLineException) {
            System.err.println(ex.message)
            cmdParser.printUsage(System.out)
            System.out.println("Example: java KotlinDecider" + cmdParser.printExample(org.kohsuke.args4j.OptionHandlerFilter.ALL))
            correctInitialized = false
        }
    }
}
