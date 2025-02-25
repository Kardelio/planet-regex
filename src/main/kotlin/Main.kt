package bk.edu

/*
matches (/contains, /entire)
find (/all)
replace (/replaceFirst)
split
 */

fun main() {
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

fun splitter(){
    val regex = """\W+""".toRegex()
    val beautiful = "Roses are red, Violets are blue"

    println(regex.split(beautiful))
}

fun groups(){
    val a = """(xyz)x""".toRegex()
    //change x to be (x) and see result!
    val b = "xyz yz xyzx xzy"
    val c = a.findAll(b)
    printAllMatchResult(c,a.toString(), b)
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


fun meta(){
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
    println(printMatchResult(fin, regex.toString(),"abcbabbd"))
    val finA = regex.findAll("abcb abbd")
    println(printAllMatchResult(finA, regex.toString(),"abcb abbd"))

}


/*
MatchResult class

As such, they have a value, which is the matched String or substring:

val regex = """a([bc]+)d?""".toRegex()
val matchResult = regex.find("abcb abbd")

assertEquals("abcb", matchResult.value)
 */

fun printAllMatchResult(mr: Sequence<MatchResult>?, pattern: String,orig: String): String {
    println("____")
    println("ALL")
    println("____")
    if (mr == null) {
        println("-NULL-")
        return "-"
    } else {
        println("Regex pattern is : ${pattern}")
        mr.forEachIndexed { index, matchResult ->  println(printMatchResult(matchResult, pattern, orig)) }
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
        mr.groupValues.forEachIndexed { index, s ->  println("${index} = ${s}") }
        println("=====")
        return ""
    }
}