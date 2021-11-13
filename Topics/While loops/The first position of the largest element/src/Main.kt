import java.util.*
fun main() {
    val scanner = Scanner(System.`in`)
    var maxPosition = 1
    var max = Int.MIN_VALUE
    var currentPosition = 1
    while (scanner.hasNextInt()) {
        val num = scanner.nextInt()
        if (max < num) {
            max = num
            maxPosition = currentPosition
        }
        currentPosition++
    }
    print("$max $maxPosition")
}

