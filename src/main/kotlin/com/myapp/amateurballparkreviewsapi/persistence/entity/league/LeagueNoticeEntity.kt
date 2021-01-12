package com.myapp.amateurballparkreviewsapi.persistence.entity.league

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "LEAGUE_NOTICE")
data class LeagueNoticeEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    var id: Int? = null,

    @Column(name = "LEAGUE_ID", nullable = false)
    var leagueId: Int? = null,

    @Column(name = "TITLE", nullable = false, length = 100)
    var title: String? = null,

    @Column(name = "NOTICE", nullable = false, columnDefinition = "TEXT")
    var notice: String? = null,

    @Column(name = "CREATED_DATE", nullable = false)
    var createdDate: Date? = null,

    @Column(name = "UPDATED_DATE", nullable = false)
    var updatedDate: Date? = null
)
