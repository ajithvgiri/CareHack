package softfruit.solutions.quickdoc.activity.authentication

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.activity_phone_auth.*
import softfruit.solutions.quickdoc.R
import softfruit.solutions.quickdoc.activity.MainActivity
import softfruit.solutions.quickdoc.utils.Utils
import java.util.concurrent.TimeUnit

class PhoneAuthActivity : AppCompatActivity() {
    var TAG: String = "PhoneAuthActivity"

    var mVerificationId: String? = null
    val mAuth = FirebaseAuth.getInstance()
    val mResendToken: PhoneAuthProvider.ForceResendingToken? = null
    val mCallbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            // This callback will be invoked in two situations:
            // 1 - Instant verification. In some cases the phone number can be instantly
            //     verified without needing to send or enter a verification code.
            // 2 - Auto-retrieval. On some devices Google Play services can automatically
            //     detect the incoming verification SMS and perform verificaiton without
            //     user action.
            Utils.instance.makeLogd(TAG, "onVerificationCompleted:" + credential)
            val intent = Intent(this@PhoneAuthActivity, PhoneVerificationActivity::class.java)
            intent.putExtra("credential", credential)
            startActivity(intent)
        }

        override fun onVerificationFailed(e: FirebaseException) {
            // This callback is invoked in an invalid request for verification is made,
            // for instance if the the phone number format is not valid.
            Utils.instance.makeLogw(TAG, "onVerificationFailed" + e)
            progressBar.visibility = View.GONE
            sendOTP.isEnabled = true
            phone_number.isEnabled = true

            if (e is FirebaseAuthInvalidCredentialsException) {
                // Invalid request
                Utils.instance.makeLoge(TAG, "Invalid request")
                // ...
            } else if (e is FirebaseTooManyRequestsException) {
                // The SMS quota for the project has been exceeded
                Utils.instance.makeLoge(TAG, "The SMS quota for the project has been exceeded")
                // ...
            }

            // Show a message and update the UI
            // ...
        }

        override fun onCodeSent(verificationId: String?,
                                token: PhoneAuthProvider.ForceResendingToken?) {
            // The SMS verification code has been sent to the provided phone number, we
            // now need to ask the user to enter the code and then construct a credential
            // by combining the code with a verification ID.
            Utils.instance.makeLogd(TAG, "onCodeSent:" + verificationId!!)
            // Save verification ID and resending token so we can use them later
            mVerificationId = verificationId

            val intent = Intent(this@PhoneAuthActivity, PhoneVerificationActivity::class.java)
            intent.putExtra("mVerificationId", mVerificationId)
            startActivity(intent)
            //mResendToken = token;
            // ...
        }
    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth.currentUser
        if (currentUser != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_auth)

        sendOTP.setOnClickListener(View.OnClickListener {
            if (phone_number.text.toString().length == 10) {
                phoneNumberAuth("+91" + phone_number.text.toString())
                progressBar.visibility = View.VISIBLE
                sendOTP.isEnabled = false
                phone_number.isEnabled = false
            }
        })
    }


    private fun phoneNumberAuth(phoneNumber: String) {
        val phoneAuthProvider = PhoneAuthProvider.getInstance()
        phoneAuthProvider.verifyPhoneNumber(
                phoneNumber, // Phone number to verify
                60, // Timeout duration
                TimeUnit.SECONDS, // Unit of timeout
                this@PhoneAuthActivity, // Activity (for callback binding)
                mCallbacks)     // OnVerificationStateChangedCallbacks

    }

}
