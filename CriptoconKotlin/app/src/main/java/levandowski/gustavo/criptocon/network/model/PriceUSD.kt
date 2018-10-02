package levandowski.gustavo.criptocon.network.model

import com.google.gson.annotations.SerializedName

/**
 * Created by glevandowski on 08/07/18.
 */
class PriceUSD() {
    @SerializedName("price")
    lateinit var price:String

    @SerializedName("volume_24h")
    lateinit var volumTwentyHours:String

    @SerializedName("market_cap")
    lateinit var marketCap:String

    @SerializedName("percent_change_1h")
    lateinit var percentChangeOneHour:String

    @SerializedName("percent_change_24h")
    lateinit var percentChangeTwentyHour:String

    @SerializedName("percent_change_7d")
    lateinit var percentChangeWeek:String

    constructor(price:String,volumTwentyHours:String,marketCap:String,percentChangeOneHour:String,percentChangeTwentyHour:String,percentChangeWeek:String):this(){
        this.price = price
        this.volumTwentyHours = volumTwentyHours
        this.marketCap = marketCap
        this.percentChangeOneHour = percentChangeOneHour
        this.percentChangeTwentyHour = percentChangeTwentyHour
        this.percentChangeWeek = percentChangeWeek
    }
}