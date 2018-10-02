package levandowski.gustavo.criptocon.util

import android.app.Activity
import android.content.Context
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager.RESULT_UNCHANGED_SHOWN
import android.content.Context.INPUT_METHOD_SERVICE
import android.view.View
import android.view.inputmethod.InputMethodManager


/**
 * Created by glevandowski on 04/07/18.
 */
class ManageKeyboard(activity: Activity) {
    var activity:Activity

    init {
        this.activity = activity
    }

    fun hideKeyBoard() = activity.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


    fun showKeyBoard() = (activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).
            toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.RESULT_UNCHANGED_SHOWN)


    fun isActive():Boolean = (activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager).isActive

}