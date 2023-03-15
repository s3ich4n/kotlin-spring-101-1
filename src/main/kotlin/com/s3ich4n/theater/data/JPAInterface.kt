package com.s3ich4n.theater.data

import com.s3ich4n.theater.domain.Seat
import org.springframework.data.jpa.repository.JpaRepository

interface SeatRepository : JpaRepository<Seat, Long>
