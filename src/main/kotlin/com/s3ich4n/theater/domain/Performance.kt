package com.s3ich4n.theater.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany

@Entity
class Performance(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,
    val title: String, ) {

    @OneToMany(mappedBy = "performance")
    private var _booking: MutableList<Booking> = mutableListOf()
    val booking: List<Booking>
        get() = _booking.toList()
}
