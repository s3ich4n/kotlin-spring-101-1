import java.math.BigDecimal


data class Seat(val row: Int, val num: Int, val price: BigDecimal, val description: String) {
    override fun toString(): String = "Seat $row-$num $$price ($description)"
}


class TheaterData {
    val theaterRows: Int = 15
    val theaterColumns: Int = 36

    val sizeOfRestrictedView: Int = 2
    val sizeOfBasicView: Int = 1

    val bestView = 0..1
    val leftRestrictedView = 0..sizeOfRestrictedView
    val rightRestrictedView = theaterColumns - sizeOfRestrictedView..theaterColumns
    val basicView = theaterRows - sizeOfBasicView..theaterRows

    // 좌석 가격
    val basicPrice = BigDecimal("14.50")
    val restrictedViewPrice = BigDecimal("2.0")
    val standardPrice = BigDecimal("3.5")
    val bestViewPrice = BigDecimal("6.5")

    // 좌석명
    val nameOfBackRow = "Back row"
    val nameOfCheaperSeat = "Cheaper seat"
    val nameOfRestrictedSeat = "Restricted view"
    val nameOfBestViewSeat = "Best view"
    val nameOfStandardSeat = "Standard seat"
}


class Theater {
    //  constructor로 기본값을 미리 만들어놓기
    //  이 값은 mutable임에 유의
    private val hiddenSeats = mutableListOf<Seat>()

    constructor() {
        val theaterData = TheaterData()

        fun getPrice(row: Int, col: Int) : BigDecimal {
            return when {
                row in theaterData.basicView -> theaterData.basicPrice
                col in theaterData.leftRestrictedView || col in theaterData.rightRestrictedView -> theaterData.basicPrice + theaterData.restrictedViewPrice
                row in theaterData.bestView -> theaterData.basicPrice + theaterData.bestViewPrice
                else -> theaterData.basicPrice + theaterData.standardPrice
            }
        }

        fun getDescription(row: Int, col: Int) : String {
            return when {
                row == theaterData.theaterRows -> theaterData.nameOfBackRow
                row == theaterData.theaterRows - 1 -> theaterData.nameOfCheaperSeat
                col in theaterData.leftRestrictedView || col in theaterData.rightRestrictedView -> theaterData.nameOfRestrictedSeat
                row in theaterData.bestView -> theaterData.nameOfBestViewSeat
                else -> theaterData.nameOfStandardSeat
            }
        }

        for (row in 0..theaterData.theaterRows) {
            for (col in 0..theaterData.theaterColumns) {
                hiddenSeats.add(Seat(row, col, getPrice(row, col), getDescription(row, col)))
            }
        }
    }

    // 이런 이뮤터블 값에 기존 뮤터블을 넣어준다.
    val seats
        get() = hiddenSeats.toList()

}
