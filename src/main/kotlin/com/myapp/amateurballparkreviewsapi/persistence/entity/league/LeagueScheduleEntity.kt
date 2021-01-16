package com.myapp.amateurballparkreviewsapi.persistence.entity.league

import java.sql.Timestamp
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "LEAGUE_SCHEDULE")
data class LeagueScheduleEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    var id: Int? = null,

    @Column(name = "LEAGUE_ID", nullable = false)
    var leagueId: Int? = null,

    @Column(name = "EVENT_TYPE", nullable = false, length = 1)
    var eventType: String? = null,

    @Column(name = "EVENT_DATE", nullable = false)
    var eventDate: Date? = null,

    @Column(name = "EVENT_START_DATE", nullable = false)
    var eventStartDate: Timestamp? = null,

    @Column(name = "EVENT_END_DATE", nullable = false)
    var eventEndDate: Timestamp? = null,

    @Column(name = "EVENT_TITLE", nullable = false, length = 100)
    var eventTitle: String? = null,

    @Column(name = "NOTE", nullable = true, columnDefinition = "TEXT")
    var note: String? = null
)
