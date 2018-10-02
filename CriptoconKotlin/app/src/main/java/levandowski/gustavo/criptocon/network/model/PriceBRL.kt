package levandowski.gustavo.criptocon.network.model

import com.google.gson.annotations.SerializedName
import java.text.NumberFormat
import java.util.*

/**
 * Created by glevandowski on 08/07/18.
 */
class PriceBRL() {

  @SerializedName("price")
   var price:String = ""

  @SerializedName("volume_24h")
   var volumTwentyHours:String = ""

  @SerializedName("percent_change_1h")
   var percentChangeOneHour:String = ""

  @SerializedName("percent_change_24h")
   var percentChangeTwentyHour:String =""

  @SerializedName("percent_change_7d")
   var percentChangeWeek:String = ""

  @SerializedName("market_cap")
  var marketCap:String = ""

  @SerializedName("last_updated")
  var lastUpdated:String = ""

  constructor(price:String,volumTwentyHours:String,percentChangeOneHour:String,percentChangeTwentyHour:String,percentChangeWeek:String,marketCap:String,lastUpdated:String):this(){
      this.price = price
      this.volumTwentyHours = volumTwentyHours
      this.percentChangeOneHour = percentChangeOneHour
      this.percentChangeTwentyHour = percentChangeTwentyHour
      this.percentChangeWeek = percentChangeWeek
      this.marketCap = marketCap
      this.lastUpdated = lastUpdated
  }

    fun getFormatPrice():String = NumberFormat.getCurrencyInstance(Locale("pt","BR")).format(price.toDouble())

}