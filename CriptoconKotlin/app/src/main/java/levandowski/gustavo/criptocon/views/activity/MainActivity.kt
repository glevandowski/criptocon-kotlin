package levandowski.gustavo.criptocon.views.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import levandowski.gustavo.criptocon.R
import levandowski.gustavo.criptocon.listeners.IsOpenFragmentListener
import levandowski.gustavo.criptocon.model.Coin
import levandowski.gustavo.criptocon.model.parameters.CoinModelParameters
import levandowski.gustavo.criptocon.util.OpenManageClass
import levandowski.gustavo.criptocon.views.BaseActivity
import levandowski.gustavo.criptocon.views.fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

 class MainActivity:BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var progress: ProgressBar
    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    var isOpen: IsOpenFragmentListener ?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progress = findViewById(R.id.progressBar)
        setSupportActionBar(toolbar)
        this.setupNavigation()

        this.getCoin(true)
        }

     fun showSplashScreen(){
         isOpen?.isOpenFragment(isOpen = true)
         OpenManageClass.openFragment(SplashFragment(),fragmentManager,true)
     }

     fun showFirstFragment(){
         isOpen?.isOpenFragment(isOpen = false)
         progress.visibility = View.GONE
         fragment = QuotesFragment()
         OpenManageClass.openFragment(fragment,fragmentManager,true,coinArrayList)
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

    fun share() = startActivity(Intent(Intent.ACTION_SEND).setType("text/plain").putExtra(Intent.EXTRA_TEXT,
            "*Curta a Página e acompanhe nosso Trabalho* CriptoCon - https://www.facebook.com/CriptoCon39/"))


    fun refresh(view:View){
       swipeRefreshLayout =  view.findViewById(R.id.swipe_refresh_quotes)

       swipeRefreshLayout.setOnRefreshListener {
           coinArrayList.clear()
           getCoin(false)
       }
    }

     fun getCoin(isFirstAcess:Boolean){

         if(isFirstAcess) this.showSplashScreen()

         createData()

         service.getCoin(getString(R.string.key_api),5000).enqueue(object: Callback<CoinModelParameters>{

             override fun onResponse(call:Call<CoinModelParameters>, response: Response<CoinModelParameters>) {
                 if(!response.isSuccessful) return

                 for (i in response.body()?.data?.indices?.iterator()!!) {
                     coinArrayList.add(response.body()?.data?.get(i) ?: Coin())
                 }
                 showFirstFragment()
             }
             override fun onFailure(call:Call<CoinModelParameters>, t: Throwable) {
                 call.cancel()
                 startActivity(Intent(this@MainActivity, FailureActivity::class.java).putExtra("Fail","Verifique sua conexão à internet"))
             }
         })
     }

     override fun onNavigationItemSelected(item: MenuItem): Boolean {
         // switch case em kotlin
         when (item.itemId) {
             R.id.nav_cotacao -> {
                 fragment = QuotesFragment()
                 OpenManageClass.openFragment(fragment,fragmentManager,true,coinArrayList)
             }
             R.id.nav_carteira -> {
                 fragment = WalletFragment()
                 OpenManageClass.openFragment(fragment,fragmentManager,true)
             }
             R.id.nav_conversor -> {
                 fragment = ConvertCoinsFragment()
                 OpenManageClass.openFragment(fragment,fragmentManager,true,coinArrayList)
             }
             R.id.nav_sugestoes -> {
                 fragment = SuggestionFragment()
                 OpenManageClass.openFragment(fragment,fragmentManager,true)
             }
             R.id.nav_compartilhar -> {
                 share()
             }
             R.id.nav_sobre -> {
                 fragment = AboutFragment()
                 OpenManageClass.openFragment(fragment,fragmentManager,true)
             }
         }

         drawer_layout.closeDrawer(GravityCompat.START)
         return true
     }
}
