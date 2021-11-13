fun main() {
    var max = 0
    var num = readLine()!!.toInt()
    while(num != 0) {
        if (max < num)
        max = num
        num = readLine()!!.toInt()
    }
    print(max)
}
