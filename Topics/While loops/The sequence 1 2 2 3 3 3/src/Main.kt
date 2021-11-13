fun main() {
    val maxCount = readLine()!!.toInt()
    var count = 1
    var num = 1
    while (num <= maxCount) {
        var count2 = count
        while (count2 > 0 && num <= maxCount) {
            print("$count ")
            num++
            count2--
        }
        count++  
    }
}
