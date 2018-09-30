package levandowski.gustavo.criptocon.views.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import levandowski.gustavo.criptocon.R
import levandowski.gustavo.criptocon.views.BaseFragment

class WalletFragment : BaseFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       var view:View = inflater.inflate(R.layout.fragment_wallet, container, false)

        return view
    }

}
