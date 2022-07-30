var numberOfPurchasedTickets = 0
var currentIncome = 0
var totalIncome = 1
var percentage = 0.0
var totalSeats = 0

fun main() {
    println("Enter the number of rows:")
    val rows = readln().toInt()
    println("Enter the number of seats in each row:")
    val columns = readln().toInt()
    totalSeats = rows * columns
    val cinema = createCinema(rows, columns)
//    println(cinema.size)//rows
//    println(cinema[0].size)//columns

    if (rows * columns <= 60) {
        totalIncome = rows * columns * 10
    } else {
        if (rows % 2 == 0)
            totalIncome = (rows / 2 * columns) * 10 + (rows / 2 * columns) * 8
        else {
            totalIncome = (rows / 2 * columns) * 10 + ((rows - rows / 2) * columns) * 8
        }
    }

    while (true) {
        if (menu(cinema) == -1)
            break
    }
}


fun menu(cinema: MutableList<MutableList<String>>): Int {
    println(
        "1. Show the seats\n" +
                "2. Buy a ticket\n" +
                "3. Statistics\n" +
                "0. Exit"
    )
    when (readln()) {
        "0" -> {
            return -1
        }
        "1" -> {
            printCinema(cinema)
            return 0
        }
        "2" -> {
            buySeat(cinema)
            return 0
        }
        "3" -> {
            statistics()
            return 0

        }
        else -> {
            return 0
        }
    }

}

fun statistics() {
    if (numberOfPurchasedTickets == 0)
        percentage = 0.0
    else
        percentage = (numberOfPurchasedTickets.toDouble() * 100) / totalSeats
    var fomattedPercentage = "%.2f".format(percentage)
    println("Number of purchased tickets: $numberOfPurchasedTickets")
    println("Percentage: $fomattedPercentage%")
    println("Current income: \$$currentIncome")
    println("Total income: \$$totalIncome")
}

fun printCinema(cinema: MutableList<MutableList<String>>) {
    var count = 1
    println("Cinema:")
    for (i in 1..cinema[0].size) {
        if (i == 1)
            print(" ")
        print(" " + i.toString())
    }
    println()
    for (i in cinema) {
        println(count.toString() + " " + i.joinToString(" "))
        count++
    }
}

fun createCinema(x: Int, y: Int): MutableList<MutableList<String>> {
    return MutableList(x) { MutableList(y) { "S" } }
}

fun buySeat(cinema: MutableList<MutableList<String>>) {

    while (true) {
        println("Enter a row number:")
        val row = readln().toInt()
        println("Enter a seat number in that row:")
        val seatNumber = readln().toInt()
        if (row <= cinema.size && seatNumber <= cinema[0].size) { // girilen sayilar valid mi
            if (cinema[row - 1][seatNumber - 1] == "S") {
                cinema[row - 1][seatNumber - 1] = "B"
                numberOfPurchasedTickets++
                if (cinema.size * cinema[0].size <= 60) {
                    println("Ticket price: \$10")
                    currentIncome += 10
                    break
                } else if (cinema.size / 2 < row) {
                    println("Ticket price: \$8")
                    currentIncome += 8
                    break
                } else {
                    println("Ticket price: \$10")
                    currentIncome += 10
                    break
                }
            } else {
                println("That ticket has already been purchased!")
            }

        } else {
            println("Wrong input!")
        }
    }


}