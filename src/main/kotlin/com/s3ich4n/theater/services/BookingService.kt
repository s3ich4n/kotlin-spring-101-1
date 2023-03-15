package com.s3ich4n.theater.services

import com.s3ich4n.theater.domain.Seat
import org.springframework.stereotype.Service


@Service
class BookingService {

    fun isSeatFree(seat: Seat) : Boolean {
        return true
    }
}
