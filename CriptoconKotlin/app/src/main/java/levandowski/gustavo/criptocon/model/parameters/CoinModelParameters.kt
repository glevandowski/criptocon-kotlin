package levandowski.gustavo.criptocon.model.parameters

import com.google.gson.annotations.SerializedName
import levandowski.gustavo.criptocon.model.Coin
import levandowski.gustavo.criptocon.model.MetaData

/**
 * Created by glevandowski on 07/07/18.
 */
class CoinModelParameters(data:ArrayList<Coin>,metadata:MetaData) {
    @SerializedName("data")
    var data: ArrayList<Coin> = data

    @SerializedName("metadata")
    var metadata = metadata
}