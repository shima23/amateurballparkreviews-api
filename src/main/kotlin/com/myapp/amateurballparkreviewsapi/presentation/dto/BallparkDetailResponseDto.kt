package com.myapp.amateurballparkreviewsapi.presentation.dto

data class BallparkDetailResponseDto(
    var id: Int,
    // 球場名
    var name: String,
    // 都道府県名
    val prefectures: String,
    // 市区町村名
    val municipalities: String,
    // ユーザー評価
    val rating: String,
    // レビュー件数
    val count: Int,
    // 住所
    val residence: String?,
    // ホームページ
    val webUrl: String?,
    // 利用料金
    val fee: String?,
    // 駐車場
    val parking: String?,
    // GoogleMapのURL
    val googleMapUrl: String?,
    // 利用種別
    val useType: String?,
    // グラウンド種別
    val groundType: String?,
    // 広さ
    val groundSize: String?,
    // 照明
    val nightGameLight: String?,
    // ベンチ
    val bench: String?,
    // ブルペン
    val bullpen: String?,
    // 水場
    val waterPlace: String?
)
