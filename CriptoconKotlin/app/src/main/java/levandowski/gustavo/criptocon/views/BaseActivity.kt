package levandowski.gustavo.criptocon.views

import android.support.v7.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import levandowski.gustavo.criptocon.api.HttpRequest
import levandowski.gustavo.criptocon.api.services.CoinService
import levandowski.gustavo.criptocon.model.Coin
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class BaseActivity:AppCompatActivity() {

    var coinArrayList:ArrayList<Coin> = ArrayList()
    lateinit var service: CoinService
    lateinit var fragment:BaseFragment

    fun createData(){
        val g: Gson =  GsonBuilder().create();
        val retro: Retrofit =  Retrofit.Builder().client(HttpRequest.interceptorAPI(this)).baseUrl(CoinService.BASE_URL).addConverterFactory(GsonConverterFactory.create(g)).build()
        service = retro.create(CoinService::class.java)
    }
}