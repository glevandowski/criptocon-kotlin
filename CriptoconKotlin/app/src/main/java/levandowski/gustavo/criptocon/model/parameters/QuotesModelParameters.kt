package levandowski.gustavo.criptocon.model.parameters

import com.google.gson.annotations.SerializedName
import levandowski.gustavo.criptocon.model.PriceBRL
import levandowski.gustavo.criptocon.model.PriceUSD

/**
 * Created by glevandowski on 08/07/18.
 */
class QuotesModelParameters() {

    @SerializedName("BRL")
    lateinit var brl: PriceBRL

    constructor(brl: PriceBRL):this(){
        this.brl = brl
    }
}