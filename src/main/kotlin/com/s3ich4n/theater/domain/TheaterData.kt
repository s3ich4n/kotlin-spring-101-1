package com.s3ich4n.theater.domain

import java.math.BigDecimal

class TheaterData {
    val theaterRows: Int = 15
    val theaterNums: Int = 36

    val sizeOfRestrictedView: Int = 2
    val sizeOfBasicView: Int = 1

    val bestView = 0..1
    val leftRestrictedView = 0..sizeOfRestrictedView
    val rightRestrictedView = theaterNums - sizeOfRestrictedView..theaterNums
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
