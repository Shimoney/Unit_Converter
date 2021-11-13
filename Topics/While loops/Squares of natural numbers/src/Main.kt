fun main() {
    val max = readLine()!!.toInt()
    var num = 1
    while (num*num <= max) {
        println(num*num)
        num++
    }
    
}
