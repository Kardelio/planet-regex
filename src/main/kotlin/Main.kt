package bk.edu

/*
matches (/contains, /entire)
find (/all)
replace (/replaceFirst)
split
 */

fun String.fff(){

}

fun weirdEarnPerHourExample(): Boolean{

    val pattern = Regex("""(\d+(?= Euros)).*(\d+(?= hours?))""", RegexOption.IGNORE_CASE)
//    pattern.fin
    val inputString = """
        Tim earns 10 Euros every 2 hours.
        Sarah earns 15 Euros every 1 hour.
        John earns 5 euros every 3 hours.
        Mike earns 5 Euros every 2 hour.
    """.trimIndent()
    val matches = pattern.findAll(inputString)
    //IMPORTANT THIS LINE
    matches.map { it.groups.map { "${it!!.value} - ${it!!.range}" } }.forEach(::println)
    matches.map { it.destructured }.forEach { (amount,hours) ->
        println("${(amount.toFloat() / hours.toFloat())} per 1 hour")
    }
    return true
}
/*
5.0 per 1 hour
15.0 per 1 hour
1.6666666 per 1 hour
2.5 per 1 hour
 */

fun abc() {
    //
    val STR = """I like dogs, but not lions.
I like dogs, but not tigers.
I like penguins, but not lions.
I like penguins, but not tigers.""".trimIndent()
    println("--${STR}--")

    val pat = """^I like (dogs|penguins), but not (lions|tigers).$""".toRegex(RegexOption.MULTILINE)
    val ee = pat.findAll(STR) //sort of like the global flag
    println(ee.javaClass.simpleName)
    println("ee: Seq MatchRersult")
    ee.forEach {
        println(it.javaClass.simpleName)
        println("Matches: ${it.groups.size}")
        println("Whole sentence ")
        it.groups.forEach {
            println("    ${it}")
        }
    }
    destr()
}


fun destr(){
    val pa = """(\w+) (\w+).*""".toRegex()
    pa.matchEntire("Here is a word")?.destructured?.let { (first, second) ->
        println("---")
        println(first)
        println(second)
    }
}


fun showRegexMatchesVisually(strToSearch: String, pattern: Regex) {
    println("Matches...")
    val a = pattern.containsMatchIn(strToSearch)
    val mm = pattern.find(strToSearch)
    println(a)
    val e: MatchGroup? = mm!!.groups.first()
    println(e)

    println("---")

}

//fun findAllInstances

//fun replace
// Everything after this is in red
val red = "\u001b[31m"
val green = "\u001b[32m"

// Resets previous color codes
val reset = "\u001b[0m"


fun cleanup() {
    val input = "aaabbbccc"
    val out = """a?bbbccc""".toRegex().find(input)
    println(out!!.groups.toString())
    println("${red}test${reset}")
    input.forEachIndexed { index, c ->
        print("${green}${c}${reset}")
    }


}

fun main() {
    weirdEarnPerHourExample()
    System.exit(0)
    abc()
    System.exit(0)
    cleanup()
    System.exit(0)
    showRegexMatchesVisually("aaabbbccc", """a{1,2}""".toRegex())
    System.exit(0)
    println("Hello World!")
    val t = """.*""".toRegex()
    val c = "hello"
    val out = t.matches(c)
    println(c)
    println("---------")
    test()

    meta()
    groups()
    splitter()
}

fun splitter() {
    val regex = """\W+""".toRegex()
    val beautiful = "Roses are red, Violets are blue"

    println(regex.split(beautiful))
}

fun groups() {
    val a = """(xyz)x""".toRegex()
    //change x to be (x) and see result!
    val b = "xyz yz xyzx xzy"
    val c = a.findAll(b)
    printAllMatchResult(c, a.toString(), b)
    /*
    Regex pattern is : (xyz)x
    Str was: xyz yz xyzx xzy
    Regex pattern is : (xyz)x
    7..10
    [MatchGroup(value=xyzx, range=7..10), MatchGroup(value=xyz, range=7..9)]
    0 -> xyzx at 7..10
    1 -> xyz at 7..9
    0 = xyzxRegex pattern is : (xyz)x
Str was: xyz yz xyzx xzy
Regex pattern is : (xyz)x
7..10
[MatchGroup(value=xyzx, range=7..10), MatchGroup(value=xyz, range=7..9)]
0 -> xyzx at 7..10
1 -> xyz at 7..9
0 = xyzx
1 = xyz
    1 = xyz

    HUH?
    kotlin regex matching patterns and then regex captutre groups!
     */
}


fun meta() {
    val a = """\s+""".toRegex()
    val b = ""
    val c = a.matches(b)
    println(c)
}

/*
Metacharacter classes:

/d - any digit
/D - any character not a decimal digit

/w - word character A-Za-z0-9
/W - NOT a word char

/s - white space
/S - not a white space

 */

/*
Regex()
vs
"".toRegex()
 */

/*
Tip: regular expressions often contain characters that would be interpreted as escape sequences in String literals.
We can thus use raw Strings to forget about multiple levels of escaping: """ """

2. Raw String
A raw string can contain newlines (not new line escape character) and arbitrary text. A raw string is delimited by a triple quote """. For example,

fun main(args: Array<String>) {

    val myString = """
    for (character in "Hey!")
        println(character)
"""
    print(myString)
}
When you run the program, the output will be:

    for (character in "Hey!")
        println(character)
You can remove the leading whitespaces of a raw string using trimMargin() function. For example,

 */

/*
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
 */

/*
MATCHES

If we want the whole String to match instead, we use matches:

val regex = """a([bc]+)d?""".toRegex()
assertTrue(regex.matches("abcd"))

If we only need a partial match, we can use containsMatchIn:

val regex = """a([bc]+)d?""".toRegex()

assertTrue(regex.containsMatchIn("xabcdy"))
val matchResult = regex.matchEntire("abbccbbd")
 */

fun test() {
    val regex = """a([bc]+)d?""".toRegex()

    println(regex.containsMatchIn("xabcdy"))
    println(regex.matches("abcd"))
    val matchResult = regex.matchEntire("abbccbbd")
    println(printMatchResult(matchResult, regex.toString(), "abbccbbd"))
    val fin = regex.find("abcbabbd")
    println(printMatchResult(fin, regex.toString(), "abcbabbd"))
    val finA = regex.findAll("abcb abbd")
    println(printAllMatchResult(finA, regex.toString(), "abcb abbd"))

}


/*
MatchResult class

As such, they have a value, which is the matched String or substring:

val regex = """a([bc]+)d?""".toRegex()
val matchResult = regex.find("abcb abbd")

assertEquals("abcb", matchResult.value)
 */

fun printAllMatchResult(mr: Sequence<MatchResult>?, pattern: String, orig: String): String {
    println("____")
    println("ALL")
    println("____")
    if (mr == null) {
        println("-NULL-")
        return "-"
    } else {
        println("Regex pattern is : ${pattern}")
        mr.forEachIndexed { index, matchResult -> println(printMatchResult(matchResult, pattern, orig)) }
        println("=====")
        return ""
    }
}

fun printMatchResult(mr: MatchResult?, pattern: String, orig: String): String {
    if (mr == null) {
        println("-NULL-")
        return ""
    } else {
        println("Str was: ${orig}")
        println("Regex pattern is : ${pattern}")
        println(mr.range)
        println(mr.groups)
        mr.groups.forEachIndexed { index, matchGroup -> println("${index} -> ${matchGroup?.value} at ${matchGroup?.range}") }
        mr.groupValues.forEachIndexed { index, s -> println("${index} = ${s}") }
        println("=====")
        return ""
    }
}