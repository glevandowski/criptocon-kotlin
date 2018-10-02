package levandowski.gustavo.criptocon.ui

import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.SearchView
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import levandowski.gustavo.criptocon.R
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
        val g: Gson = GsonBuilder().create();
        val retro: Retrofit = Retrofit.Builder().client(HttpRequest.interceptorAPI(this)).baseUrl(CoinService.BASE_URL).addConverterFactory(GsonConverterFactory.create(g)).build()
        service = retro.create(CoinService::class.java)
    }

    fun setBottomSheet(resource:Int) {
        val llBottomSheet:FrameLayout = findViewById(resource)
        val float:FloatingActionButton = findViewById(R.id.floatingActionButton2)
        val search:SearchView = findViewById(R.id.searchViewCoins)
        val bottomSheetBehavior = BottomSheetBehavior.from(llBottomSheet)

        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED)

        bottomSheetBehavior.setPeekHeight(200)

        bottomSheetBehavior.setHideable(false)

        float.setOnClickListener{
            float.setVisibility(View.GONE)
            search.setVisibility(View.VISIBLE)
            search.requestFocusFromTouch()
        }

        bottomSheetBehavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                // React to state change

            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                // React to dragging events
            }
        })
    }

}