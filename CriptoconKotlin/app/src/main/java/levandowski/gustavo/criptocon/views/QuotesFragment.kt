package levandowski.gustavo.criptocon.views

import android.os.Bundle
import android.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.SearchView;
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import levandowski.gustavo.criptocon.R
import levandowski.gustavo.criptocon.adapters.CoinAdapter
import levandowski.gustavo.criptocon.model.Coin
import levandowski.gustavo.criptocon.util.ManageKeyboard

/**
 *extends de fragment
 */
class QuotesFragment : Fragment() ,SearchView.OnQueryTextListener{

     lateinit var reciclerView: RecyclerView;
     lateinit var coinArrayList: ArrayList<Coin>
     lateinit var adapterCoin: CoinAdapter
     lateinit var searchCoins:SearchView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflando fragment em kotlin
        var view: View = inflater.inflate(R.layout.fragment_quotes, container, false)
        this.findViews(view)
        this.init()
        this.setupSearchView()
        this.setupRecyclerViewLayout()
        this.setupAdapter()
        this.receivingBundle()
        return view;
    }


    fun findViews(view: View) {
        (activity as MainActivity).supportActionBar?.show()
        (activity as MainActivity).refresh(view)
        reciclerView = RecyclerView(view.context)
        reciclerView = view.findViewById(R.id.recyclerView)
        searchCoins =  view.findViewById(R.id.searchViewCoins)
    }

    fun init(){
        coinArrayList = ArrayList()

    }

    fun setupAdapter() {
        adapterCoin = CoinAdapter(activity, coinArrayList);
        reciclerView.setAdapter(adapterCoin);
    }

    fun setupRecyclerViewLayout() {
        reciclerView.setHasFixedSize(true);
        var layoutManager:RecyclerView.LayoutManager = LinearLayoutManager(activity);
        reciclerView.setLayoutManager(layoutManager);
    }


    fun setupAnimation() {
        var resId:Int  = R.anim.layout_anim_fall_down;
        var animation: LayoutAnimationController = AnimationUtils.loadLayoutAnimation(getActivity(), resId);
        reciclerView.setLayoutAnimation(animation);
    }

    private fun receivingBundle() {
        val args = arguments
        val receivingBundle:ArrayList<Coin> = args?.getParcelableArrayList<Coin>("KEY") ?:ArrayList()

        for (i in receivingBundle.indices.iterator()) {
            coinArrayList.add(receivingBundle.get(i))
        }
        this.setupAnimation()

        adapterCoin.notifyDataSetChanged()
    }

    fun setupSearchView() {
        searchCoins.setIconifiedByDefault(false);
        searchCoins.setOnQueryTextListener(this);
        searchCoins.setSubmitButtonEnabled(true);
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        adapterCoin.getFilter().filter(query)
        return true}

    override fun onQueryTextChange(newText: String?): Boolean {
        adapterCoin.getFilter().filter(newText)
        return true
    }
}
