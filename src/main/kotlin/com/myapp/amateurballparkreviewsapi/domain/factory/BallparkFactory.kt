package com.myapp.amateurballparkreviewsapi.domain.factory

import com.myapp.amateurballparkreviewsapi.domain.model.Ballpark
import com.myapp.amateurballparkreviewsapi.persistence.dto.BallParkDetailResponseDto
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

    fun createBallparkDetailFromEntity(entity: BallparkEntity): BallParkDetailResponseDto {
        return BallParkDetailResponseDto(
            entity.id!!,
            entity.name!!,
            entity.prefectures!!,
            entity.municipalities!!,
            calcRating(entity.reviewDetailEntities),
            entity.reviewDetailEntities.size,
            entity.residence,
            entity.webUrl,
            entity.fee,
            entity.parking,
            entity.googleMapUrl,
            entity.useType,
            entity.groundType,
            entity.groundSize,
            entity.nightGameLight,
            entity.bench,
            entity.bullpen,
            entity.waterPlace
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
