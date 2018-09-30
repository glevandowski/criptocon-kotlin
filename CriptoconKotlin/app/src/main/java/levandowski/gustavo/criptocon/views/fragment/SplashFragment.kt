package levandowski.gustavo.criptocon.views.fragment

import android.os.Bundle
import android.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import levandowski.gustavo.criptocon.R
import levandowski.gustavo.criptocon.listeners.IsOpenFragmentListener
import levandowski.gustavo.criptocon.views.BaseFragment
import levandowski.gustavo.criptocon.views.activity.MainActivity


class SplashFragment : BaseFragment() ,IsOpenFragmentListener{
    var isOpenManage:Boolean = true;

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view:View = inflater.inflate(R.layout.fragment_splash, container, false)
        getActivity()?.overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up)

        (activity as MainActivity).supportActionBar?.hide()

        if(isOpenManage == false) { fragmentManager?.popBackStack() }

        return view
    }

    override fun isOpenFragment(isOpen: Boolean) {
        isOpenManage = isOpen
    }
}
