package levandowski.gustavo.criptocon.model

import com.google.gson.annotations.SerializedName
import java.text.NumberFormat

/**
 * Created by glevandowski on 08/07/18.
 */
class PriceBRL() {

  @SerializedName("price")
   var price:String = ""

  @SerializedName("volume_24h")
   var volumTwentyHours:String = ""

  @SerializedName("market_cap")
   var marketCap:String = ""

  @SerializedName("percent_change_1h")
   var percentChangeOneHour:String = ""

  @SerializedName("percent_change_24h")
   var percentChangeTwentyHour:String =""

  @SerializedName("percent_change_7d")
   var percentChangeWeek:String = ""

  constructor(price:String,volumTwentyHours:String,marketCap:String,percentChangeOneHour:String,percentChangeTwentyHour:String,percentChangeWeek:String):this(){
      this.price = price
      this.volumTwentyHours = volumTwentyHours
      this.marketCap = marketCap
      this.percentChangeOneHour = percentChangeOneHour
      this.percentChangeTwentyHour = percentChangeTwentyHour
      this.percentChangeWeek = percentChangeWeek
  }

    fun getFormatPrice():String{
        val currencyFormat = NumberFormat.getCurrencyInstance()
        return currencyFormat.format(price.toDouble())
    }
}