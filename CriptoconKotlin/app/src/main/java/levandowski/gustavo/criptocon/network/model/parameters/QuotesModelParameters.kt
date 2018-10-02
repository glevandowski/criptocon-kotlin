package levandowski.gustavo.criptocon.network.model.parameters

import com.google.gson.annotations.SerializedName
import levandowski.gustavo.criptocon.network.model.PriceBRL

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