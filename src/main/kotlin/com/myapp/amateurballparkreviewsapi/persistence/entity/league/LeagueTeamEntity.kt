package com.myapp.amateurballparkreviewsapi.persistence.entity.league

import javax.persistence.*

@Entity
@Table(name = "LEAGUE_TEAM")
data class LeagueTeamEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    var id: Int? = null,

    @Column(name = "LEAGUE_ID", nullable = false)
    var leagueId: Int? = null,

    @Column(name = "TEAM_NAME", nullable = false, length = 100)
    var teamName: String? = null
)
