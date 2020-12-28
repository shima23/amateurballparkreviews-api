package com.myapp.amateurballparkreviewsapi.domain.service

import com.myapp.amateurballparkreviewsapi.domain.factory.LeagueFactory
import com.myapp.amateurballparkreviewsapi.domain.repository.LeagueRepository
import com.myapp.amateurballparkreviewsapi.presentation.dto.LeagueDto
import com.myapp.amateurballparkreviewsapi.presentation.dto.LeagueRegisterRequestDto
import com.myapp.amateurballparkreviewsapi.presentation.dto.LeagueRegisterResponseDto
import com.myapp.amateurballparkreviewsapi.presentation.dto.LeagueScoreRequestDto
import org.springframework.stereotype.Service

@Service
class LeagueService(private val leagueRepository: LeagueRepository,
                    private val leagueFactory: LeagueFactory
) {
    fun getLeague(leagueId: Int): LeagueDto {
        val league = leagueRepository.getLeague(leagueId)
        return LeagueDto(league)
    }

    fun registerLeague(requestDto: LeagueRegisterRequestDto): LeagueRegisterResponseDto {
        val league = leagueFactory.createLeague(requestDto)
        return LeagueRegisterResponseDto(leagueRepository.registerLeague(league).id!!)
    }

    fun registerLeagueScoreDto(requestDto: LeagueScoreRequestDto) {
        val leagueScore = leagueFactory.createLeagueScore(requestDto)
        leagueRepository.registerLeagueScore(leagueScore)
    }
}
