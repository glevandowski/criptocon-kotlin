package levandowski.gustavo.criptocon.services

import levandowski.gustavo.criptocon.model.parameters.CoinModelParameters
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by glevandowski on 01/07/18.
 */
 interface CoinService {

    @GET("?convert=BRL")
    fun getCoin(@Query("start") start:Int, @Query("limit") limit:Int, @Query("structure") structure: String): Call<CoinModelParameters>

    companion object {
        var BASE_URL: String = "https://api.coinmarketcap.com/v2/ticker/";
    }

}