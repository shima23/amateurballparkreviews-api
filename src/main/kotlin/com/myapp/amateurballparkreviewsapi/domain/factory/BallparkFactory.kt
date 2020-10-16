package com.myapp.amateurballparkreviewsapi.domain.factory

import com.myapp.amateurballparkreviewsapi.domain.model.Ballpark
import org.springframework.stereotype.Component
import com.myapp.amateurballparkreviewsapi.persistence.entity.BallparkEntity
import com.myapp.amateurballparkreviewsapi.persistence.entity.ReviewDetailEntity
import java.math.BigDecimal
import java.math.RoundingMode

@Component
class BallparkFactory {
    fun createBallparkFromEntity(entity: BallparkEntity): Ballpark {
        return Ballpark(
            entity.id!!,
            entity.name!!,
            entity.prefectures!!,
            entity.municipalities!!,
            calcRating(entity.reviewDetailEntities),
            entity.reviewDetailEntities.size
        )
    }

    private fun calcRating(reviewDetailEntities: List<ReviewDetailEntity>): String {
        if(reviewDetailEntities.isEmpty()) return ""

        var ratingSum = BigDecimal(0)
        reviewDetailEntities.forEach {
            ratingSum = ratingSum.add(it.rating)
        }

        val rating = ratingSum.divide(BigDecimal(reviewDetailEntities.size),1, RoundingMode.UP)
        return rating.toPlainString()
    }
}
