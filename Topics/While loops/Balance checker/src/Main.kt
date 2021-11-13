import java.util.Scanner
fun main() {
    val scanner = Scanner(System.`in`)
    var remainBalance = scanner.nextInt()
    while (scanner.hasNext() && remainBalance >= 0) {
        val lastPayment = scanner.nextInt()
        if (remainBalance >= lastPayment) {
            remainBalance -= lastPayment
        } else {
            print("Error, insufficient funds for the purchase." + 
                " Your balance is $remainBalance, but you need $lastPayment.")
            remainBalance -= lastPayment
        }
    }   
    if (!scanner.hasNext() && remainBalance >= 0) {
        print("Thank you for choosing us to manage your account! Your balance is $remainBalance.")
    }
}
