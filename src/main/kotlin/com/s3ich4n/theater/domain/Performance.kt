package com.s3ich4n.theater.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class Performance(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,
    val title: String, ) {
    constructor() : this(0, "")
}
