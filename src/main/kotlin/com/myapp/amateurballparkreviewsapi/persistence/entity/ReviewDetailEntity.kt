package com.myapp.amateurballparkreviewsapi.persistence.entity

import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "EX_REVIEW_DETAILS")
data class ReviewDetailEntity(
    @Id
    @Column(name = "ID")
    var id: Int? = null,

    @Column(name = "RATING", nullable = false)
    var rating: BigDecimal? = null,

    @Column(name = "NOTE", length = 2000)
    var note: String? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BALLPARK_ID", referencedColumnName = "id")
    var ballparkId: BallparkEntity

)   {
    override fun toString(): String {
        return "ReviewDetailEntity(id=$id, name=$rating, note=$note)"
    }
}
