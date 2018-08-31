package levandowski.gustavo.criptocon.services

import levandowski.gustavo.criptocon.model.parameters.CoinModelParameters
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by glevandowski on 01/07/18.
 */
 interface CoinService {

    @GET("listings/latest?convert=BRL")
    fun getCoin(@Query("CMC_PRO_API_KEY") header:String, @Query("limit") limit:Int): Call<CoinModelParameters>

    companion object {
        var BASE_URL: String = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/";
    }

}