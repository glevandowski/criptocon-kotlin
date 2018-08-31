package levandowski.gustavo.criptocon.views

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import levandowski.gustavo.criptocon.R
import levandowski.gustavo.criptocon.listeners.IsOpenFragmentListener
import levandowski.gustavo.criptocon.model.Coin
import levandowski.gustavo.criptocon.model.parameters.CoinModelParameters
import levandowski.gustavo.criptocon.services.CoinService
import levandowski.gustavo.criptocon.util.OpenManageClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


open class MainActivity():AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    var isOpen: IsOpenFragmentListener ?= null
    var coinArrayList:ArrayList<Coin> = ArrayList()

    lateinit var service:CoinService
    lateinit var progress:ProgressBar
    lateinit var swipeRefreshLayout:SwipeRefreshLayout

    constructor(isOpenFragmentListener: IsOpenFragmentListener):this(){
        this.isOpen = isOpenFragmentListener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progress = findViewById(R.id.progressBar)
        setSupportActionBar(toolbar)
        this.setupNavigation()

        this.getCoin(true)
        }


    fun setupNavigation(){
        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // switch case em kotlin
        when (item.itemId) {
            R.id.nav_cotacao -> {
                OpenManageClass.openFragment(QuotesFragment(),fragmentManager,true,coinArrayList)
            }
            R.id.nav_carteira -> {
              OpenManageClass.openFragment(WalletFragment(),fragmentManager,true)
            }
            R.id.nav_conversor -> {
              OpenManageClass.openFragment(ConvertCoinsFragment(),fragmentManager,true,coinArrayList)
            }
            R.id.nav_sugestoes -> {
              OpenManageClass.openFragment(SuggestionFragment(),fragmentManager,true)
            }
            R.id.nav_compartilhar -> {
               share()
            }
            R.id.nav_sobre -> {
               OpenManageClass.openFragment(AboutFragment(),fragmentManager,true)
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    fun share() = startActivity(Intent(Intent.ACTION_SEND).setType("text/plain").putExtra(Intent.EXTRA_TEXT,
            "*Curta a Página e acompanhe nosso Trabalho* CriptoCon - https://www.facebook.com/CriptoCon39/"))

    fun getCoin(isFirstAcess:Boolean){

        if(isFirstAcess)
        this.showSplashScreen()

        createData()

        service.getCoin("e9a8c51c-6358-4138-9c38-c7e6f90277dc",5000).enqueue(object: Callback<CoinModelParameters>{

            override fun onResponse(call:Call<CoinModelParameters>, response: Response<CoinModelParameters>) {
               if(!response.isSuccessful){
                   "Verifique sua conexão à internet".toast(applicationContext)
                   return
               }

              for (i in response.body()?.data?.indices?.iterator()!!) {
                    coinArrayList.add(response.body()?.data?.get(i) ?: Coin())
                }
                showFirstFragment()
            }
            override fun onFailure(call:Call<CoinModelParameters>, t: Throwable) {
                call.cancel()
                "Verifique sua conexão à internet".toast(applicationContext).show()
            }
        })
    }

    fun createData(){
        val g: Gson =  GsonBuilder().create();
        val retro: Retrofit =  Retrofit.Builder().baseUrl(CoinService.BASE_URL).addConverterFactory(GsonConverterFactory.create(g)).build()
        service = retro.create(CoinService::class.java)
    }

    fun refresh(view:View){
       swipeRefreshLayout =  view.findViewById(R.id.swipe_refresh_quotes)

       swipeRefreshLayout.setOnRefreshListener {
           coinArrayList.clear()
           getCoin(false)
       }
    }

    /***
     * simplificando toast
     */
    fun Any.toast(context: Context, duration: Int = Toast.LENGTH_SHORT): Toast {
        return Toast.makeText(context, this.toString(), duration).apply { show() }
    }

    fun showSplashScreen(){
        isOpen?.isOpenFragment(isOpen = true)
        OpenManageClass.openFragment(SplashFragment(),fragmentManager,true)
    }

    fun showFirstFragment(){
        isOpen?.isOpenFragment(isOpen = false)
        progress.visibility = View.GONE
        OpenManageClass.openFragment(QuotesFragment(),fragmentManager,true,coinArrayList)
    }

}
