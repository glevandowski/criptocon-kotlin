package levandowski.gustavo.criptocon.views.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import levandowski.gustavo.criptocon.R
import levandowski.gustavo.criptocon.views.BaseFragment

class SuggestionFragment : BaseFragment() {
    lateinit var buttonWalltime:Button
    lateinit var buttonBinance:Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view:View = inflater.inflate(R.layout.fragment_suggestion, container, false)
        this.findViews(view)
        this.actionEvents()
        return view
    }

    fun findViews(view: View){
        buttonWalltime = view.findViewById(R.id.four_btn_card_one);
        buttonBinance = view.findViewById(R.id.four_btn_card_two);

    }

    fun actionEvents(){
        buttonWalltime.onClickExternalUrl("https://walltime.info/")

        buttonBinance.onClickExternalUrl("https://play.google.com/store/apps/details?id=com.binance.dev")
    }
}
