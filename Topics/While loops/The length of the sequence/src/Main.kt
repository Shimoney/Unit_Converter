fun main() {
    var num = readLine()!!.toInt()
    var count = 0
    while (num!=0) {
        count++
        num = readLine()!!.toInt()
    }
    print(count)
}
