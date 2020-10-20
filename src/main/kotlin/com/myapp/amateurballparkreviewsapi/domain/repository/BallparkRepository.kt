package com.myapp.amateurballparkreviewsapi.domain.repository

import com.myapp.amateurballparkreviewsapi.domain.factory.BallparkFactory
import com.myapp.amateurballparkreviewsapi.domain.model.Ballpark
import com.myapp.amateurballparkreviewsapi.presentation.dto.BallParkDetailResponseDto
import com.myapp.amateurballparkreviewsapi.presentation.dto.BallparkSearchRequestDto
import com.myapp.amateurballparkreviewsapi.persistence.entity.BallparkEntity
import org.springframework.stereotype.Repository
import com.myapp.amateurballparkreviewsapi.persistence.repository.BallparkEntityRepository
import org.springframework.data.jpa.domain.Specification

@Repository
class BallparkRepository(private val entityRepository: BallparkEntityRepository) {

    fun findAll(): List<Ballpark> {
        val ballparkList = mutableListOf<Ballpark>()
        entityRepository.findAll().forEach {
            ballparkList.add(BallparkFactory().createBallparkFromEntity(it))
        }
        return ballparkList
    }

    fun findConditions(reqDto: BallparkSearchRequestDto): List<Ballpark> {
        val ballparkList = mutableListOf<Ballpark>()
        entityRepository.findAll(Specification.where(createSpecification(reqDto))).forEach {
            ballparkList.add(BallparkFactory().createBallparkFromEntity(it))
        }
        return ballparkList
    }

    fun findById(id: Int): BallParkDetailResponseDto {
        val ballpark = entityRepository.findById(id).get()
        return BallparkFactory().createBallparkDetailFromEntity(ballpark)
    }

    private fun createSpecification(reqDto: BallparkSearchRequestDto): Specification<BallparkEntity> {
       return Specification { root, query, cb ->
           cb.or(
               cb.like(cb.lower(root.get("name")), getContainsLikePattern(reqDto.name)),
               cb.like(cb.lower(root.get("prefectures")), reqDto.prefectures)
               //TODO:検索条件を後で追加
           )
       }
    }

    private fun getContainsLikePattern(input: String): String? {
        return if (input.isEmpty()) {
            input
        } else {
            "%$input%"
        }
    }
}
