package levandowski.gustavo.criptocon.model.parameters

import com.google.gson.annotations.SerializedName
import levandowski.gustavo.criptocon.model.Coin
import levandowski.gustavo.criptocon.model.Status

/**
 * Created by glevandowski on 07/07/18.
 */
class CoinModelParameters(data:ArrayList<Coin>, status:Status) {
    @SerializedName("data")
    var data: ArrayList<Coin> = data

    @SerializedName("status")
    var status = status
}