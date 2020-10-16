package com.myapp.amateurballparkreviewsapi.domain.service

import com.myapp.amateurballparkreviewsapi.domain.model.Ballpark
import com.myapp.amateurballparkreviewsapi.domain.repository.BallparkRepository
import com.myapp.amateurballparkreviewsapi.persistence.dto.BallparkSearchRequestDto
import org.springframework.stereotype.Service

@Service
class BallparkService(private val ballparkRepository: BallparkRepository) {

    /** 全件取得 */
    fun listBallparkAll(): List<Ballpark> {
        return ballparkRepository.findAll()
    }

    /** 単語検索 */
    fun listBallpark(reqDto: BallparkSearchRequestDto): List<Ballpark> {
        return ballparkRepository.findConditions(reqDto)
    }
}
