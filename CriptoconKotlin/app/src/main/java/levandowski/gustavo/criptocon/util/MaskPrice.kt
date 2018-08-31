package levandowski.gustavo.criptocon.util

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import java.text.NumberFormat

/**
 * Created by glevandowski on 04/07/18.
 */
 class MaskPrice(campo:EditText): TextWatcher {

    var isUpdating: Boolean = false
    var nf = NumberFormat.getCurrencyInstance()
    var campo: EditText

    init { this.campo = campo }

    override fun onTextChanged(s:CharSequence, start:Int, before:Int, after:Int) {

        if (isUpdating) {
            isUpdating = false
            return
        }
        isUpdating = true

        var str:String = s.toString ();

        var hasMask:Boolean = ((str.indexOf("R$") > -1 || str.indexOf("$") > -1) && (str.indexOf(".") > -1 || str.indexOf(",") > -1));

        if (hasMask) {
            str = str.replace("R$", "").replace(",", "").replace(".", "");
        }

            str = nf.format((str.toDouble() / 100))
            campo.setText(str);
            campo.setSelection(campo.getText().length);

        }

       override fun beforeTextChanged(s:CharSequence, start:Int,count:Int, after:Int) {}

       override fun afterTextChanged(s:Editable) {}
}
