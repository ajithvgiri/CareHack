package softfruit.solutions.quickdoc

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import softfruit.solutions.quickdoc.activity.MainActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startActivity(Intent(this, MainActivity::class.java))
        // close splash activity
        finish()

    }
}
