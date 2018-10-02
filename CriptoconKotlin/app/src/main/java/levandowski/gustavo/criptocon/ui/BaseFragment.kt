package levandowski.gustavo.criptocon.ui

import android.app.Fragment
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import levandowski.gustavo.criptocon.network.model.Coin
import levandowski.gustavo.criptocon.util.AppDialogs
import levandowski.gustavo.criptocon.util.ManageKeyboard

open class BaseFragment:Fragment() {
    lateinit var manageKeyboard: ManageKeyboard
    lateinit var coinArrayList:ArrayList<Coin>
    lateinit var appDialogs: AppDialogs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        coinArrayList = ArrayList()
        manageKeyboard = ManageKeyboard(activity)
        appDialogs = AppDialogs(activity)
        receivingBundle()
    }

    protected fun receivingBundle() {
        val receivingBundle:ArrayList<Coin> = arguments?.getParcelableArrayList<Coin>("KEY") ?:ArrayList()
        receivingBundle.filterIndexed { index, value -> coinArrayList.add(receivingBundle.get(index)) }
    }

    fun Button.onClickExternalUrl(string:String) =
            this.setOnClickListener{  startActivity(Intent(Intent.ACTION_VIEW).setData(Uri.parse(string)))}

    fun Any.toast(context: Context, duration: Int = Toast.LENGTH_SHORT): Toast =
            Toast.makeText(context, this.toString(), duration).apply { show() }

}