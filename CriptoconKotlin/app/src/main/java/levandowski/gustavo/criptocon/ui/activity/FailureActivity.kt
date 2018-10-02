package levandowski.gustavo.criptocon.ui.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.TextView
import levandowski.gustavo.criptocon.R

class FailureActivity : AppCompatActivity() {

    lateinit var textFailure:TextView;
    lateinit var buttonFailure: Button;
    lateinit var buttonReconect: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_failure)
        supportActionBar?.hide()
        findViews()
        setAction()

        textFailure.setText(setBundle())
    }

    fun setBundle():String {
        val bundle :Bundle = intent.extras
        val message = bundle.getString("Fail")
        return message
    }

    fun findViews() {
        textFailure = findViewById(R.id.text_fail_activity)
        buttonFailure = findViewById(R.id.btn_fail_activity)
        buttonReconect = findViewById(R.id.btn_fail_reconect)
    }

    fun setAction() {
        buttonFailure.setOnClickListener { startActivity(Intent(Settings.ACTION_WIRELESS_SETTINGS)); }

        buttonReconect.setOnClickListener {
            finish()
            startActivity(Intent(this@FailureActivity, MainActivity::class.java))
        }
    }
}
