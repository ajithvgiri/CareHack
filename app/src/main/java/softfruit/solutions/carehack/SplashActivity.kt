package softfruit.solutions.carehack

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import softfruit.solutions.carehack.activity.MainActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startActivity(Intent(this, MainActivity::class.java))
        // close splash activity
        finish()

    }
}
