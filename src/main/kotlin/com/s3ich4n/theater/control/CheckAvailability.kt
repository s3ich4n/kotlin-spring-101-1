package com.s3ich4n.theater.control

import com.s3ich4n.theater.domain.TheaterData

class CheckAvailabilityBackingBean() {
    private val theaterData: TheaterData = TheaterData()

    val seatNums = 1..theaterData.theaterNums
    val seatRows = 'A'..'O'

    var selectedSeatNum : Int = 1
    var selectedSeatRow : Char = 'A'
    var result : String = ""
}
