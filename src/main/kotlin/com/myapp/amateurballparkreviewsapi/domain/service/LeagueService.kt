package com.myapp.amateurballparkreviewsapi.domain.service

import com.myapp.amateurballparkreviewsapi.domain.factory.LeagueFactory
import com.myapp.amateurballparkreviewsapi.domain.model.LeagueScore
import com.myapp.amateurballparkreviewsapi.domain.model.LeagueScoreSummary
import com.myapp.amateurballparkreviewsapi.domain.repository.LeagueRepository
import com.myapp.amateurballparkreviewsapi.presentation.dto.league.*
import org.springframework.stereotype.Service

@Service
class LeagueService(private val leagueRepository: LeagueRepository,
                    private val leagueFactory: LeagueFactory
) {
    fun getLeague(leagueId: Int): LeagueDto {
        val league = leagueRepository.getLeague(leagueId)
        val leagueTeam = leagueRepository.getLeagueTeam(leagueId)
        val leagueScoreList = leagueRepository.getLeagueScore(leagueId)
        return LeagueDto(league, leagueTeam, createSummaryLeagueScore(leagueScoreList))
    }

    fun registerLeague(requestDto: LeagueRegisterRequestDto): LeagueRegisterResponseDto {
        val league = leagueFactory.createLeagueForRegister(requestDto)
        return LeagueRegisterResponseDto(leagueRepository.registerLeague(league).id!!)
    }

    fun updateLeague(requestDto: LeagueUpdateRequestDto) {
        val league = leagueFactory.createLeagueForUpdate(requestDto)
        leagueRepository.updateLeague(league)
    }

    fun registerLeagueScoreDto(requestDto: LeagueScoreRequestDto) {
        val leagueScore = leagueFactory.createLeagueScore(requestDto)
        leagueRepository.registerLeagueScore(leagueScore)
    }

    private fun createSummaryLeagueScore(leagueScoreList: List<LeagueScore>): List<LeagueScoreSummary> {
        val leagueScoreSummaryList = mutableListOf<LeagueScoreSummary>()
        val yearList = mutableListOf<Int>()
        // 登録されているスコアから年度を抽出
        leagueScoreList.distinctBy { leagueScore -> leagueScore.year }.forEach { yearList.add(it.year!!) }
        // 年度ごとのSummaryを作成
        yearList.forEach { year ->
            val leagueScoreListByYear = leagueScoreList.filter { leagueScore -> leagueScore.year == year }
            leagueScoreSummaryList.add(LeagueScoreSummary(year, leagueScoreListByYear))

        }
        return leagueScoreSummaryList
    }
}
