package bk.edu

/**
 * Main method to run is at the bottom!
 */

val red = "\u001b[31m"
val green = "\u001b[32m"
val reset = "\u001b[0m"

fun initializeRegexObjects() {
    println("")
    println("----------------------------")
    println(" ===> initializeRegexObjects")
    val obj1 = """.*""".toRegex()
    val obj2 = Regex(""".*""")
    println(obj1.pattern)
    println(obj2.pattern)
    println("^ They are the same!")
}

fun findExamples() {
    println("")
    println("------------------")
    println(" ===> findExamples")
    val multilineText = """
        abcde
        abc
        abcdefgh
    """.trimIndent()
    val pattern = Regex("""abc""")
    val output = pattern.findAll(multilineText)
    output.map { Pair(it.value, it.range) }
        .forEachIndexed { index, pair -> println("(Match ${index}) = ${pair.first} : ${pair.second}") }
    println("^ Found 3 matches")
}
/*
(Match 0) = abc : 0..2
(Match 1) = abc : 6..8
(Match 2) = abc : 10..12
*/

fun alternationExample() {
    println("")
    println("------------------------")
    println(" ===> alternationExample")
    //IMPORTANT: In the case below watch out for the start of the line
    // being actually at the start of each line.
    val input = """I like dogs, but not lions.
I like dogs, but not tigers.
I like penguins, but not lions.
I like penguins, but not tigers.""".trimIndent()
    val pattern = """^I like (dogs|penguins), but not (lions|tigers).$""".toRegex(RegexOption.MULTILINE)

    val results = pattern.findAll(input)
    results.forEach {
        println("Matches: ${it.groups.size}")
        it.groups.forEach {
            println(" --> ${it}")
        }
    }
    println("^ Found multiple matches, one on each line.")
}

fun destructedExample() {
    println("")
    println("-----------------------")
    println(" ===> destructedExample")
    val pattern = """(\w+) (\w+).*""".toRegex()
    pattern.matchEntire("Here is a word")?.destructured?.let { (first, second) ->
        println("First word: \"${first}\"")
        println("Second word: \"${second}\"")
    }
    println("^ Each word is in its own capture group accessed easily via `destructured`")
}

fun weirdEarnPerHourExample() {
    println("")
    println("-----------------------------")
    println(" ===> weirdEarnPerHourExample")
    val pattern = Regex("""(\d+(?= Euros)).*(\d+(?= hours?))""", RegexOption.IGNORE_CASE)
    val inputString = """
        Tim earns 10 Euros every 2 hours.
        Sarah earns 15 Euros every 1 hour.
        John earns 5 euros every 3 hours.
        Mike earns 5 Euros every 2 hour.
    """.trimIndent()
    val matches = pattern.findAll(inputString)
    //Alternative line below
    //matches.map { it.groups.map { "${it!!.value} - ${it!!.range}" } }.forEach(::println)
    matches.map { it.destructured }.forEach { (amount, hours) ->
        println("${(amount.toFloat() / hours.toFloat())} per 1 hour")
    }
    println("^ prints the price per hour for every matching line!")
}
/*
5.0 per 1 hour
15.0 per 1 hour
1.6666666 per 1 hour
2.5 per 1 hour
*/

fun visualisedOutput() {
    println("")
    println("----------------------")
    println(" ===> visualisedOutput")
    val input = "aaabbbccc"
    val out = """a?bbbccc""".toRegex().find(input)
    println("Match for (aaabbbccc) : ${out!!.groups.toString()}")
    input.forEachIndexed { index, c ->
        var col = red
        if (out.range.contains(index)) {
            col = green
        }
        print("${col}${c}${reset}")
    }
    println("^ visually see where a match was and was not")
}

fun splitByRegexExample() {
    println("")
    println("-------------------------")
    println(" ===> splitByRegexExample")
    val regex = """\W+""".toRegex() //Split by none word-based characters
    val beautiful = "Roses are red, Violets are blue"

    println(regex.split(beautiful))
    println("^ Split the sentence by words using easy Regex Pattern and `Split` function")
}

fun firstGroupIsMatchItself() {
    println("")
    println("-----------------------------")
    println(" ===> firstGroupIsMatchItself")
    val pattern = """.*(word).*""".toRegex()
    val output = pattern.matchEntire("first word is this word")
    output?.groups?.forEachIndexed { index, matchGroup ->
        println("Index: ${index} -> Match Group: ${matchGroup!!.value}")
    }
    println("^ See how the first `Matching Group` is the actual LINE where the match happens itself (index 0)")
}

fun comparisonBetweenDifferentMatchFunctions() {
    println("")
    println("----------------------------------------------")
    println(" ===> comparisonBetweenDifferentMatchFunctions")
    val regex = """a([bc]+)d?""".toRegex()
    println("containsMatchIn: ${regex.containsMatchIn("xabcdy")}")
    println("matches: ${regex.matches("abcd")}")
    val matchResult = regex.matchEntire("abbccbbd")
    println(printMatchResult(matchResult, regex.toString(), "abbccbbd"))
    val fin = regex.find("abcbabbd")
    println(printMatchResult(fin, regex.toString(), "abcbabbd"))
}

fun printAllMatchResult(mr: Sequence<MatchResult>?, pattern: String, orig: String): String {
    println(" * Multiple MatchResult ===>")
    if (mr == null) {
        println("-NULL-")
        return "-"
    } else {
        println("Regex pattern is : ${pattern}")
        mr.forEachIndexed { index, matchResult -> println(printMatchResult(matchResult, pattern, orig)) }
        return ""
    }
}

fun printMatchResult(mr: MatchResult?, pattern: String, orig: String): String {
    if (mr == null) {
        println("-NULL-")
        return ""
    } else {
        println("Input was: \"${orig}\"")
        println("Regex pattern is : \"${pattern}\"")
        println("Match range was: ${mr.range}")
        println("Match groups were: ${mr.groups}")
        mr.groups.forEachIndexed { index, matchGroup -> println("   ${index} -> ${matchGroup?.value} at ${matchGroup?.range}") }
        mr.groupValues.forEachIndexed { index, s -> println("   ${index} = ${s}") }
        return ""
    }
}

fun metaCharacterTest() {
    println("")
    println("-----------------------")
    println(" ===> metaCharacterTest")
    val a = """\s+""".toRegex()
    val b = ""
    val c = a.matches(b)
    println(c)
    println("^ Really simple example showing that backslash meta characters work too!")
}


fun groupsExample() {
    println("")
    println("-------------------")
    println(" ===> groupsExample")
    val a = """(xyz)x""".toRegex()
    val b = "xyz yz xyzx xzy"
    val c = a.findAll(b)
    printAllMatchResult(c, a.toString(), b)
    println("^ Show casing mutliple MatchResults from `findAll()`")
}

fun main() {
    /**
     * Functions below can be found ordered
     * from the top of this file to the main function HERE
     */
    initializeRegexObjects()
    findExamples()
    alternationExample()
    destructedExample()
    weirdEarnPerHourExample()
    visualisedOutput()
    splitByRegexExample()
    firstGroupIsMatchItself()
    comparisonBetweenDifferentMatchFunctions()
    metaCharacterTest()
    groupsExample()
}

// =====================================
// ========= COMMENTS BELOW ============
// =====================================

/*
    /d - any digit
    /D - any character not a decimal digit

    /w - word character A-Za-z0-9
    /W - NOT a word char

    /s - white space
    /S - not a white space

    Tip: regular expressions often contain characters that would be interpreted as escape sequences in String literals.
    We can thus use raw Strings to forget about multiple levels of escaping: """ """

    2. Raw String
    A raw string can contain newlines (not new line escape character) and arbitrary text. A raw string is delimited by a triple quote """. For example,

    Matching options:
    Regex("a(b|c)+d?", CANON_EQ)
    Regex("a(b|c)+d?", setOf(DOT_MATCHES_ALL, COMMENTS))
    "a(b|c)+d?".toRegex(MULTILINE)
    "a(b|c)+d?".toRegex(setOf(IGNORE_CASE, COMMENTS, UNIX_LINES))

    IGNORE_CASE – enables case-insensitive matching
    MULTILINE – changes the meaning of ^ and $ (see Pattern)
    LITERAL – causes metacharacters or escape sequences in the pattern to be given no special meaning
    UNIX_LINES – in this mode, only the \n is recognized as a line terminator
    COMMENTS – permits whitespace and comments in the pattern
    DOT_MATCHES_ALL – causes the dot to match any character, including a line terminator
    CANON_EQ – enables equivalence by canonical decomposition (see Pattern)

    MATCHES

    If we want the whole String to match instead, we use matches:

    val regex = """a([bc]+)d?""".toRegex()
    assertTrue(regex.matches("abcd"))

    If we only need a partial match, we can use containsMatchIn:

    val regex = """a([bc]+)d?""".toRegex()

    assertTrue(regex.containsMatchIn("xabcdy"))
    val matchResult = regex.matchEntire("abbccbbd")
*/

// =====================================
// ======= / COMMENTS ABOVE ============
// =====================================
