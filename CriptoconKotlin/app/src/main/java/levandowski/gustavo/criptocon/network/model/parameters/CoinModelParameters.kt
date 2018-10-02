package levandowski.gustavo.criptocon.network.model.parameters

import com.google.gson.annotations.SerializedName
import levandowski.gustavo.criptocon.network.model.Coin
import levandowski.gustavo.criptocon.network.model.Status

/**
 * Created by glevandowski on 07/07/18.
 */
class CoinModelParameters(data:ArrayList<Coin>, status:Status) {
    @SerializedName("data")
    var data: ArrayList<Coin> = data

    @SerializedName("status")
    var status = status
}