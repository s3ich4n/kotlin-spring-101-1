package com.s3ich4n.theater.control

import com.s3ich4n.theater.domain.Booking
import com.s3ich4n.theater.domain.Performance
import com.s3ich4n.theater.domain.Seat

class CheckAvailabilityBackingBean() {
    var selectedSeatNum : Int = 1
    var selectedSeatRow : Char = 'A'
    var selectedPerformance : Long? = null
    var customerName : String = ""

    var available : Boolean? = null
    var seat: Seat? = null
    var performance: Performance? = null
    var booking: Booking? = null}
