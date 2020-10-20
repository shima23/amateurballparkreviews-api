package com.myapp.amateurballparkreviewsapi.presentation.api

import com.myapp.amateurballparkreviewsapi.domain.model.Ballpark
import com.myapp.amateurballparkreviewsapi.domain.service.BallparkService
import com.myapp.amateurballparkreviewsapi.presentation.dto.BallParkDetailResponseDto
import com.myapp.amateurballparkreviewsapi.presentation.dto.BallparkSearchRequestDto
import org.springframework.web.bind.annotation.*

@RestController
class BallparkApi(private val ballparkService: BallparkService) {

    @CrossOrigin
    @GetMapping("/list")
    fun listBallparkAll(): List<Ballpark> {
        return ballparkService.listBallparkAll()
    }

    @CrossOrigin
    @PostMapping("/list/search", consumes = ["application/json;charset=UTF-8"], produces = ["application/json;charset=UTF-8"])
    fun listBallpark(@RequestBody reqDto: BallparkSearchRequestDto): List<Ballpark> {
        return ballparkService.listBallpark(reqDto)
    }

    @CrossOrigin
    @GetMapping("/{id}")
    fun getBallparkDetail(@PathVariable id: Int): BallParkDetailResponseDto {
        return ballparkService.getBallparkDetail(id)
    }



}
