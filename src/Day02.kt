import kotlin.math.abs

fun main() {
    fun isDifferenceInRange(a: Int, b: Int): Boolean {
        val difference = abs(a - b)
        return difference in 1..3
    }

    fun isIncreasing(items: List<Int>): Boolean {
        return items
            .zipWithNext()
            .all { (current, next) -> next > current && isDifferenceInRange(current, next) }
    }

    fun isDecreasing(items: List<Int>): Boolean {
        return items
            .zipWithNext()
            .all { (current, next) -> next < current && isDifferenceInRange(current, next) }
    }

    fun canBecomeIncreasing(list: List<Int>): Boolean {
        var violations = 0
        var index = 0;

        while (index < list.size - 1) {
            val current = list[index]
            val next = list[index + 1]

            if (current >= next) {
                violations++
                if (violations > 1) return false

                val newList = list.filterIndexed { idx, _ -> idx != index }
                if (!isIncreasing(newList)) {
                    return false;
                }
            }

            if (!isDifferenceInRange(current, next)) {
                violations++
                if (violations > 1) return false

                val newListWithoutCurrent = list.filterIndexed { idx, _ -> idx != index }
                val newListWithoutNext = list.filterIndexed { idx, _ -> idx != index }
                if (!isIncreasing(newListWithoutCurrent)) {
                    return false;
                }
                if (!isIncreasing(newListWithoutNext)) {
                    violations--
                }
            }

            index ++
        }

        return true
    }

    fun canBecomeDecreasing(list: List<Int>): Boolean {
        var violations = 0
        var index = 0

        while (index < list.size - 1) {
            val current = list[index]
            val next = list[index + 1]

            if (current <= next) {
                violations++
                if (violations > 1) return false

                val newList = list.filterIndexed { idx, _ -> idx != index }
                if (!isDecreasing(newList)) {
                    return false;
                }
            }

            index ++
        }

        return true
    }

    fun part1(input: List<String>): Int {
        var safeReport = 0;

        input.forEach { line ->
            val items = line.trim().split("\\s+".toRegex()).map{ it.toInt() }

            if (isIncreasing(items) || isDecreasing(items)) {
                safeReport += 1;
            }

        }

        return safeReport
    }

    fun part2(input: List<String>): Int {
        var safeReport = 0;

        input.forEach { line ->
            val items = line.trim().split("\\s+".toRegex()).map{ it.toInt() }

            if (isIncreasing(items) || isDecreasing(items)) {
                safeReport += 1;
            } else {
                if (canBecomeIncreasing(items) || canBecomeDecreasing(items)) {
                    safeReport += 1;
                }
            }
        }

        println(safeReport)

        return safeReport
    }

    // Read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
