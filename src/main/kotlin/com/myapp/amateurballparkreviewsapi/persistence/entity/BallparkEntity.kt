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

    @Column(name = "RESIDENCE", nullable = true, length = 255)
    var residence: String? = null,

    @Column(name = "WEB_URL", nullable = true, length = 1000)
    var webUrl: String? = null,

    @Column(name = "FEE", nullable = true, length = 255)
    var fee: String? = null,

    @Column(name = "PARKING", nullable = true, length = 255)
    var parking: String? = null,

    @Column(name = "GOOGLE_MAP_URL", nullable = true, length = 1000)
    var googleMapUrl: String? = null,

    @Column(name = "USE_TYPE", nullable = true, length = 100)
    var useType: String? = null,

    @Column(name = "GROUND_TYPE", nullable = true, length = 100)
    var groundType: String? = null,

    @Column(name = "GROUND_SIZE", nullable = true, length = 100)
    var groundSize: String? = null,

    @Column(name = "NIGHT_GAME_LIGHT", nullable = true, length = 100)
    var nightGameLight: String? = null,

    @Column(name = "BENCH", nullable = true, length = 100)
    var bench: String? = null,

    @Column(name = "BULLPEN", nullable = true, length = 100)
    var bullpen: String? = null,

    @Column(name = "WATER_PLACE", nullable = true, length = 100)
    var waterPlace: String? = null,

    @OneToMany(mappedBy = "ballparkId")
    var reviewDetailEntities: List<ReviewDetailEntity>
)
