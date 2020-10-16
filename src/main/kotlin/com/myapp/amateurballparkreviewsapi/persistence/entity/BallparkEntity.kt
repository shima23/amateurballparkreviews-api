package com.myapp.amateurballparkreviewsapi.persistence.entity

import javax.persistence.*

@Entity
@Table(name = "EX_BALLPARK")
data class BallparkEntity(
    @Id
    @Column(name = "ID")
    var id: Int? = null,

    @Column(name = "NAME", nullable = false, length = 100)
    var name: String? = null,

    @Column(name = "PREFECTURES", nullable = false, length = 100)
    var prefectures: String? = null,

    @Column(name = "MUNICIPALITIES", nullable = false, length = 100)
    var municipalities: String? = null,

    @Column(name = "GROUND_TYPE", nullable = false)
    var ground_type: Int? = null,

    @Column(name = "GRASS_TYPE", nullable = false)
    var grass_type: Int? = null,

    @OneToMany(mappedBy = "ballparkId")
    var reviewDetailEntities: List<ReviewDetailEntity>
)
