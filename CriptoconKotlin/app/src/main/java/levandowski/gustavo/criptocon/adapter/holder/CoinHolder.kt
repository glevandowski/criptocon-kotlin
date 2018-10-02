package levandowski.gustavo.criptocon.adapter.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import levandowski.gustavo.criptocon.R

/**
 * Created by glevandowski on 30/06/18.
 */
open class CoinHolder(holder: View): RecyclerView.ViewHolder(holder) {
    var coinName:TextView = holder.findViewById(R.id.coin_answer_view_name);
    var coinQuotes:TextView = holder.findViewById(R.id.coin_answer_view_price)
    var coinPriceOneHour:TextView = holder.findViewById(R.id.coin_answer_view_price_one_hour)
    var coinPriceTwentyHour:TextView = holder.findViewById(R.id.coin_answer_view_price_twenty_four_hours)
    var percentsOne:TextView = holder.findViewById(R.id.coin_percentage_one_hour)
    var percentsTwenty:TextView = holder.findViewById(R.id.coin_percentage_twenty_four_hours)
}