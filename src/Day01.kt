import kotlin.math.abs

fun main() {
    fun getLists(input: List<String>): Pair<MutableList<Int>, MutableList<Int>> {
        val leftList = mutableListOf<Int>()
        val rightList = mutableListOf<Int>()

        // Parse the numbers into two separate lists
        input.forEach { line ->
            val columns = line.trim().split("\\s+".toRegex())
            if (columns.size == 2) {
                leftList.add(columns[0].toInt())
                rightList.add(columns[1].toInt())
            }
        }

        return Pair(leftList, rightList)
    }
    fun part1(input: List<String>): Int {
        val (leftList, rightList) = getLists(input)

        leftList.sort()
        rightList.sort()

        var totalDistance = 0;
        // Calculate the total distance
        for (i in leftList.indices) {
            totalDistance += abs(rightList[i] - leftList[i])
        }

        println("Total distance: $totalDistance")
        return totalDistance
    }

    fun part2(input: List<String>): Int {
        val (leftList, rightList) = getLists(input)

        var totalDistance = 0;
        // Calculate the total distance
        for (leftListItem in leftList) {
            val count = rightList.count { it == leftListItem }
            totalDistance += leftListItem * count
        }

        println("Total distance: $totalDistance")
        return totalDistance
    }

    // Read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_test")
    check(part2(testInput) == 31)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
