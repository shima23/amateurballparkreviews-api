package com.myapp.amateurballparkreviewsapi.presentation.api

import com.myapp.amateurballparkreviewsapi.domain.service.LeagueService
import com.myapp.amateurballparkreviewsapi.presentation.dto.league.*
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

    @PutMapping("/league")
    fun updateLeague(@RequestBody requestDto: LeagueUpdateRequestDto) {
        return leagueService.updateLeague(requestDto)
    }

    @PostMapping("/league/score")
    fun registerLeagueScore(@RequestBody requestDto: LeagueScoreRequestDto) {
        leagueService.registerLeagueScoreDto(requestDto)
    }

}
