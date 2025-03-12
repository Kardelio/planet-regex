package bk.edu

import java.io.File

fun getFile(path: String): List<String> {
    return File(path).readLines()
}

fun checkAnswer(myAns: Int, theAns: Int) {
    if (myAns != theAns) {
        throw Exception("Wrong Answer => ${myAns} != ${theAns}")
    } else {
        println("-----------------")
        println("CORRECT ANSWER!")
        println("-----------------")
    }
}

fun splitToMul(input: String): Pair<Int, Int> {
    val justTheValues = input.substringAfter("(").substringBefore(")")
    val values = justTheValues.split(",")
    return values[0].toInt() to values[1].toInt()
}

fun splitToMultipleMul(input: String): Int {
    val out = """mul\([0-9]{1,3},[0-9]{1,3}\)""".toRegex().findAll(input)
    return out.map {
        val splitValues = splitToMul(it.groupValues[0])
        splitValues.first * splitValues.second
    }.sum()
}

fun main() {
    val inputLines = getFile("src/main/kotlin/aoc-day3-input.txt")
    part1(inputLines)
    part2(inputLines)
}

fun part1(inputLines: List<String>) {
    val part1Answer = """mul\([0-9]{1,3},[0-9]{1,3}\)""".toRegex().findAll(inputLines.joinToString("")).map {
        val splitValues = splitToMul(it.groupValues[0])
        splitValues.first * splitValues.second
    }.sum()
    println()
    println("Part 1 Answer: ${part1Answer}")
    checkAnswer(part1Answer, 184576302)
}

fun part2(inputLines: List<String> ) {
    val doAndDonts = """.+?(?=do|don\'t)""".toRegex().find(inputLines.joinToString(""))
    var incrementer = 0
    doAndDonts?.let {
        val justOperations = """mul\([0-9]{1,3},[0-9]{1,3}\)""".toRegex().findAll(it.groupValues[0])
        justOperations.forEach { me ->
            val splitValues = splitToMul(me.groupValues[0])
            incrementer += splitValues.first * splitValues.second
        }
    }

    val out = """do\(\)((?!don't\(\)).)*mul\([0-9]{1,3},[0-9]{1,3}\)""".toRegex().findAll(inputLines.joinToString(""))
    val sol = out.map {
        val multipleSplitValues = splitToMultipleMul(it.groupValues[0])
        multipleSplitValues
    }.sum() + incrementer
    println()
    println("Part 2 Answer: ${sol}")
    checkAnswer(sol, 118173507)
}

//answers:
//part1:184576302
//part2:118173507