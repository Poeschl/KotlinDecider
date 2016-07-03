package io.github.poeschl.example.kotlindecider

/**
 * Project: KotlinDecider
 * Created by Markus Pöschl on 02.07.16.
 */
class StringOptionGetter(inputString: String) : OptionGetter {

    var parsedOptions: List<String> = emptyList()

    override val options: List<String>
        get() = parsedOptions

    init {
        parsedOptions = parseString(inputString)
    }

    internal fun parseString(inputString: String): List<String> {
        return inputString.split(',')
    }
}