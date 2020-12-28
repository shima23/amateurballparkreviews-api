package com.myapp.amateurballparkreviewsapi.presentation.api

import com.myapp.amateurballparkreviewsapi.domain.model.Ballpark
import com.myapp.amateurballparkreviewsapi.domain.service.BallparkService
import com.myapp.amateurballparkreviewsapi.presentation.dto.BallparkDetailResponseDto
import com.myapp.amateurballparkreviewsapi.presentation.dto.BallparkSearchRequestDto
import org.springframework.web.bind.annotation.*

@RestController
class BallparkApi(private val ballparkService: BallparkService) {

    @GetMapping("/list")
    fun listBallparkAll(): List<Ballpark> {
        return ballparkService.listBallparkAll()
    }

    @PostMapping("/list/search", consumes = ["application/json;charset=UTF-8"], produces = ["application/json;charset=UTF-8"])
    fun listBallpark(@RequestBody reqDto: BallparkSearchRequestDto): List<Ballpark> {
        return ballparkService.listBallpark(reqDto)
    }

    @GetMapping("/{id}")
    fun getBallparkDetail(@PathVariable id: Int): BallparkDetailResponseDto {
        return ballparkService.getBallparkDetail(id)
    }


}
