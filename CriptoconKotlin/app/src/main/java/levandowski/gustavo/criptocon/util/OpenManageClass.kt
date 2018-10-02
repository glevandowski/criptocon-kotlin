package levandowski.gustavo.criptocon.util

import android.os.Bundle
import android.app.Fragment
import android.app.FragmentManager
import android.util.Log
import levandowski.gustavo.criptocon.R
import levandowski.gustavo.criptocon.network.model.Coin

/**
 * Created by glevandowski on 30/06/18.
 */
open class OpenManageClass {

    companion object {

        fun openFragment(calledFragment: Fragment, manager: FragmentManager,noAddToBackStack: Boolean) {
            if (noAddToBackStack)
                manager.beginTransaction().replace(R.id.content_main, calledFragment).commitAllowingStateLoss()
            else
            manager.beginTransaction().replace(R.id.content_main, calledFragment).addToBackStack(calledFragment::class.java.getName()).commitAllowingStateLoss()
            Log.d("comando",calledFragment.javaClass.getName())

        }

        fun openFragment(calledFragment: Fragment, manager: FragmentManager,noAddToBackStack: Boolean,myJson:ArrayList<Coin>) {
            val bundle = Bundle()
            bundle.putParcelableArrayList("KEY",myJson)
            calledFragment.setArguments(bundle)

            if (noAddToBackStack)
                manager.beginTransaction().replace(R.id.content_main, calledFragment).commitAllowingStateLoss();
            else
                manager.beginTransaction().replace(R.id.content_main, calledFragment).addToBackStack(calledFragment::class.java.getName()).commitAllowingStateLoss()
            Log.d("comando",calledFragment.javaClass.getName())

        }
    }
}
