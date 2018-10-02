package levandowski.gustavo.criptocon.ui.fragment

import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.SearchView;
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import levandowski.gustavo.criptocon.R
import levandowski.gustavo.criptocon.adapter.CoinAdapter
import levandowski.gustavo.criptocon.ui.BaseFragment
import levandowski.gustavo.criptocon.ui.activity.MainActivity
/**
 *extends de fragment
 */
class QuotesFragment : BaseFragment() ,SearchView.OnQueryTextListener{

     lateinit var reciclerView: RecyclerView;
     lateinit var adapterCoin: CoinAdapter
     lateinit var searchCoins:SearchView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_quotes, container, false)
        this.findViews(view)
        this.setupSearchView()
        this.setupRecyclerViewLayout()
        this.setupAdapter()
        this.setupAnimation()
        this.setupBottomSheet(view)
        return view;
    }

    fun findViews(view: View) {
        (activity as MainActivity).supportActionBar?.show()
        (activity as MainActivity).refresh(view)
        reciclerView = RecyclerView(view.context)
        reciclerView = view.findViewById(R.id.recyclerView)
        searchCoins =  view.findViewById(R.id.searchViewCoins)

    }

    fun setupBottomSheet(view: View) {
        val llBottomSheet: FrameLayout = view.findViewById(R.id.bottom_sheet)
        val float: FloatingActionButton = view.findViewById(R.id.floatingActionButton2)

        searchBottomSheet.setBottomSheet(llBottomSheet, float, searchCoins, manageKeyboard)
    }

    fun setupAdapter() {
        adapterCoin = CoinAdapter(activity, coinArrayList)
        reciclerView.setAdapter(adapterCoin)
        adapterCoin.notifyDataSetChanged()
    }

    fun setupRecyclerViewLayout() {
        reciclerView.setHasFixedSize(true);
        reciclerView.setLayoutManager(LinearLayoutManager(activity));
    }

    fun setupAnimation() =
        reciclerView.setLayoutAnimation( AnimationUtils.loadLayoutAnimation(getActivity(),  R.anim.layout_anim_fall_down));

    fun setupSearchView() {
        searchCoins.setIconifiedByDefault(false);
        searchCoins.setOnQueryTextListener(this);
        searchCoins.setSubmitButtonEnabled(true);
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        adapterCoin.getFilter().filter(query)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        adapterCoin.getFilter().filter(newText)
        return true
    }
}
