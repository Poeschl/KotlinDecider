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
    @Option(name = "--runs", usage = "Specify the runs which should be considered.", aliases = arrayOf("-r"), required = false)
    private var cmdRuns = 1

    private var correctInitialized = true

    init {
        parseArgs(args)

        optionGetter = StringOptionGetter(cmdInputOptions)
        decider = Decider(optionGetter.options)
    }

    fun run() {
        if (correctInitialized && cmdInputOptions.isNotEmpty()) {
            decider.roundListener = this
            val resultList = mutableListOf<String>()

            for (i in 0..cmdRuns - 1) {
                resultList.add(decider.getDecision())
                println("---\n" + resultList[i])
            }
            println("##############")
            resultList.forEach { println(it) }
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
            println("Example: java KotlinDecider" + cmdParser.printExample(org.kohsuke.args4j.OptionHandlerFilter.ALL))
            correctInitialized = false
        }
    }
}
