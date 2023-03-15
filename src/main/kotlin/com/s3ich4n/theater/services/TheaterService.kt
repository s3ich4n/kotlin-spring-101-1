package com.s3ich4n.theater.services

import com.s3ich4n.theater.domain.Seat
import com.s3ich4n.theater.domain.TheaterData
import org.springframework.stereotype.Service
import java.math.BigDecimal


@Service
class TheaterService {
    //  constructor로 기본값을 미리 만들어놓기
    //  이 값은 mutable임에 유의
    private val hiddenSeats = mutableListOf<Seat>()

    constructor() {
        val theaterData = TheaterData()

        fun getPrice(row: Int, num: Int) : BigDecimal {
            return when {
                row in theaterData.basicView -> theaterData.basicPrice
                num in theaterData.leftRestrictedView || num in theaterData.rightRestrictedView -> theaterData.basicPrice + theaterData.restrictedViewPrice
                row in theaterData.bestView -> theaterData.basicPrice + theaterData.bestViewPrice
                else -> theaterData.basicPrice + theaterData.standardPrice
            }
        }

        fun getDescription(row: Int, num: Int) : String {
            return when {
                row == theaterData.theaterRows -> theaterData.nameOfBackRow
                row == theaterData.theaterRows - 1 -> theaterData.nameOfCheaperSeat
                num in theaterData.leftRestrictedView || num in theaterData.rightRestrictedView -> theaterData.nameOfRestrictedSeat
                row in theaterData.bestView -> theaterData.nameOfBestViewSeat
                else -> theaterData.nameOfStandardSeat
            }
        }

        for (row in 0..theaterData.theaterRows) {
            for (num in 0..theaterData.theaterNums) {
                hiddenSeats.add(Seat((row+64).toChar(), num, getPrice(row, num), getDescription(row, num)))
            }
        }
    }

    // 이런 이뮤터블 값에 기존 뮤터블을 넣어준다.
    val seats
        get() = hiddenSeats.toList()

    fun find(num: Int, row: Char) : Seat
        = seats.first { it.row == row && it.num == num }
}
