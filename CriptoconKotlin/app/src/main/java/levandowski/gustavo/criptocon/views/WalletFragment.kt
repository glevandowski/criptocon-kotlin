package levandowski.gustavo.criptocon.views


import android.os.Bundle
import android.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import levandowski.gustavo.criptocon.R

class WalletFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       var view:View = inflater.inflate(R.layout.fragment_wallet, container, false)

        return view
    }

}
