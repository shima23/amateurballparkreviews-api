package com.myapp.amateurballparkreviewsapi.presentation.api

import com.myapp.amateurballparkreviewsapi.domain.service.LeagueService
import com.myapp.amateurballparkreviewsapi.presentation.dto.LeagueDto
import com.myapp.amateurballparkreviewsapi.presentation.dto.LeagueRegisterRequestDto
import com.myapp.amateurballparkreviewsapi.presentation.dto.LeagueRegisterResponseDto
import com.myapp.amateurballparkreviewsapi.presentation.dto.LeagueScoreRequestDto
import org.springframework.web.bind.annotation.*

@RestController
class LeagueApi(private val leagueService: LeagueService) {
    @GetMapping("/league/list")
    fun listLeague() {

    }

    @GetMapping("/league/{id}")
    fun getLeague(@PathVariable id: Int): LeagueDto {
        return leagueService.getLeague(id)
    }

    @PostMapping("/league")
    fun registerLeague(@RequestBody requestDto: LeagueRegisterRequestDto): LeagueRegisterResponseDto {
        return leagueService.registerLeague(requestDto)
    }

    @PutMapping("/league/{id}")
    fun updateLeague(@PathVariable id: Int) {

    }

    @PostMapping("/league/score")
    fun registerLeagueScore(@RequestBody requestDto: LeagueScoreRequestDto) {
        leagueService.registerLeagueScoreDto(requestDto)
    }

}
