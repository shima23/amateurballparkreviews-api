package com.myapp.amateurballparkreviewsapi.persistence.entity.league

import com.myapp.amateurballparkreviewsapi.domain.model.League
import javax.persistence.*

@Entity
@Table(name = "LEAGUE")
data class LeagueEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    var id: Int? = null,

    @Column(name = "LEAGUE_NAME", nullable = false, length = 100)
    var leagueName: String? = null,

    @Column(name = "LEAGUE_LOGO", nullable = true, length = 1000)
    var leagueLogo: String? = null,

    @Column(name = "IMG_URL1", nullable = true, length = 1000)
    var imgUrl1: String? = null,

    @Column(name = "IMG_URL2", nullable = true, length = 1000)
    var imgUrl2: String? = null,

    @Column(name = "IMG_URL3", nullable = true, length = 1000)
    var imgUrl3: String? = null,

    @Column(name = "DESCRIPTION", nullable = false, columnDefinition = "TEXT")
    var description: String? = null

) {
    constructor(league: League) : this(
        league.id,
        league.leagueName,
        league.leagueLogo,
        league.imgUrl1,
        league.imgUrl2,
        league.imgUrl3,
        league.description

    )
}
