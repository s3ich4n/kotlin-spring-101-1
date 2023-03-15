package com.s3ich4n.theater.data

import com.s3ich4n.theater.domain.Performance
import com.s3ich4n.theater.domain.Seat
import org.springframework.data.jpa.repository.JpaRepository

interface SeatRepository : JpaRepository<Seat, Long>

interface PerformanceRepository : JpaRepository<Performance, Long>
