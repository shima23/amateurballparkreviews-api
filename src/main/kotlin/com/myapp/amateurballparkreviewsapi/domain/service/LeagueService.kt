package com.myapp.amateurballparkreviewsapi.domain.service

import com.myapp.amateurballparkreviewsapi.domain.factory.LeagueFactory
import com.myapp.amateurballparkreviewsapi.domain.model.league.LeagueScore
import com.myapp.amateurballparkreviewsapi.domain.model.league.LeagueScoreSummary
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
        val leagueScoreSummary = leagueRepository.getLeagueScore(leagueId)
        val leagueNotice = leagueRepository.getLeagueNotice(leagueId).sortedByDescending { it.updatedDate }
        return LeagueDto(league, leagueTeam, createSummaryLeagueScore(leagueScoreSummary), leagueNotice)
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
