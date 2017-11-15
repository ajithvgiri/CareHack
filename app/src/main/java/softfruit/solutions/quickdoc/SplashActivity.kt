package softfruit.solutions.quickdoc

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startActivity(Intent(this, PhoneAuthActivity::class.java))
        // close splash activity
        finish()

    }
}
