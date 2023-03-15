package com.s3ich4n.theater.control

import TheaterData

class CheckAvailabilityBackingBean() {
    private val theaterData: TheaterData = TheaterData()

    val seatCols = 1..theaterData.theaterColumns
    val seatRows = 'A'..'O'

    var selectedSeatColumn : Int = 1
    var selectedSeatRow : Char = 'A'
    var result : String = ""
}
