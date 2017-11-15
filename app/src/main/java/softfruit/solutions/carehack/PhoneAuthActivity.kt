package softfruit.solutions.carehack

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.telephony.PhoneNumberFormattingTextWatcher
import android.view.View
import kotlinx.android.synthetic.main.activity_phone_auth.*

class PhoneAuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_auth)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            phone_number.addTextChangedListener(PhoneNumberFormattingTextWatcher("+91"))
        }

        sendOTP.setOnClickListener(View.OnClickListener { startActivity(Intent(this, PhoneVerificationActivity::class.java)) })
    }
}
