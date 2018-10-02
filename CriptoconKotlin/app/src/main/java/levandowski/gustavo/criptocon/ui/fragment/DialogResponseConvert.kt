package levandowski.gustavo.criptocon.ui.fragment

import android.annotation.SuppressLint
import android.app.Dialog
import android.app.DialogFragment
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import levandowski.gustavo.criptocon.R
import levandowski.gustavo.criptocon.listeners.DataDialogListener



@SuppressLint("ValidFragment")
/**
 * Created by glevandowski on 08/07/18.
 */
 class DialogResponseConvert() : DialogFragment() {
    lateinit var dialogFragment:Dialog
    lateinit var buttonClose:Button
    lateinit var buttonOptions:Button
    lateinit var textAnswer:TextView
    lateinit var listener:DataDialogListener

    constructor(listener: DataDialogListener):this(){
        this.listener = listener
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.dialog_response_convert, container, false);
        this.findViews(view)
        this.init()
        this.setupDialog()
        this.setActionEvents()
        return view
    }

    fun init() { dialogFragment = Dialog(activity)}

    fun setupDialog(){
       dialogFragment.requestWindowFeature(Window.FEATURE_NO_TITLE)
       dialogFragment.setContentView(R.layout.dialog_response_convert)
       getDialog().getWindow().getAttributes().alpha = 0.9f
       getDialog().getWindow().setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT));
    }

    fun setActionEvents(){
      listener.setTextDialog(textAnswer = textAnswer)

      buttonClose.setOnClickListener {
          dismiss()
      }
      buttonOptions.setOnClickListener {
          Toast.makeText(activity,"Em Breve Novidades!",Toast.LENGTH_LONG).show()
      }
    }

    fun findViews(view:View){
      buttonClose = view.findViewById(R.id.button_close_response_convert_dialog)
      buttonOptions = view.findViewById(R.id.button_options_response_convert_dialog)
      textAnswer = view.findViewById(R.id.text_response_convert_dialog)
    }
}