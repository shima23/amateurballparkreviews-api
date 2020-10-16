package com.myapp.amateurballparkreviewsapi.presentation

import com.myapp.amateurballparkreviewsapi.domain.model.Ballpark
import com.myapp.amateurballparkreviewsapi.domain.service.BallparkService
import com.myapp.amateurballparkreviewsapi.persistence.dto.BallparkSearchRequestDto
import org.springframework.web.bind.annotation.*

@RestController
class BallparkApi(private val ballparkService: BallparkService) {

    @CrossOrigin
    @GetMapping("/")
    fun getHello(): String {
        return "Hello World!!"
    }

    @CrossOrigin
    @GetMapping("/list")
    fun listBallparkAll(): List<Ballpark> {
        val list = ballparkService.listBallparkAll()
        return list
    }

    @CrossOrigin
    @PostMapping("/list/search", consumes = ["application/json;charset=UTF-8"], produces = ["application/json;charset=UTF-8"])
    fun listBallpark(@RequestBody reqDto: BallparkSearchRequestDto): List<Ballpark> {
        val list = ballparkService.listBallpark(reqDto)
        return list
    }

}
