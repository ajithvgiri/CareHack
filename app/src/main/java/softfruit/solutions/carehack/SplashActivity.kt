package softfruit.solutions.carehack

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import softfruit.solutions.carehack.activity.authentication.PhoneAuthActivity

class SplashActivity : AppCompatActivity() {

    override fun onStart() {
        super.onStart()
        startActivity(Intent(this, PhoneAuthActivity::class.java))
        // close splash activity
        finish()
    }
}
