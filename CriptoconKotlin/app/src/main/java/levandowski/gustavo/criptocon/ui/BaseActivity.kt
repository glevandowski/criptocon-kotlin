package levandowski.gustavo.criptocon.ui

import android.support.v7.app.AppCompatActivity
import com.google.gson.GsonBuilder
import levandowski.gustavo.criptocon.network.HttpRequest
import levandowski.gustavo.criptocon.network.services.CoinService
import levandowski.gustavo.criptocon.network.model.Coin
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class BaseActivity:AppCompatActivity() {

    var coinArrayList: ArrayList<Coin> = ArrayList()
    lateinit var service: CoinService
    lateinit var fragment: BaseFragment

    fun createData() {
        service = Retrofit.Builder()
                .client(HttpRequest
                .interceptorAPI(this@BaseActivity))
                .baseUrl(CoinService.BASE_URL)
                .addConverterFactory(GsonConverterFactory
                .create(GsonBuilder().create()))
                .build()
                .create(CoinService::class.java)
    }
}