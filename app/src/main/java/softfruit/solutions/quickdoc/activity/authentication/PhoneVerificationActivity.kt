package softfruit.solutions.quickdoc.activity.authentication

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.*
import kotlinx.android.synthetic.main.activity_phone_verification.*
import softfruit.solutions.quickdoc.R
import softfruit.solutions.quickdoc.utils.Utils

class PhoneVerificationActivity : AppCompatActivity() {

    var TAG: String = "PhoneVerificationActivity"
    val mAuth = FirebaseAuth.getInstance()
    var mVerificationId: String = ""
    lateinit var credential: PhoneAuthCredential
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_verification)

        val extras = intent.extras
        if (extras != null) {
            if (extras.getString("mVerificationId") != null) {
                mVerificationId = extras.getString("mVerificationId")
            }

            if (extras.get("credential") != null) {
                credential = extras.get("credential") as PhoneAuthCredential
                signInWithPhoneAuthCredential(credential!!)
            }

        }

        verifyOTP.setOnClickListener(View.OnClickListener { verifyPhoneNumberWithCode(mVerificationId, pinEntryEditText.text.toString()) })

    }


    private fun verifyPhoneNumberWithCode(verificationId: String, code: String) {
        // [START verify_with_code]
        val credential = PhoneAuthProvider.getCredential(verificationId, code)
        // [END verify_with_code]
        signInWithPhoneAuthCredential(credential)
    }

    // [START sign_in_with_phone]
    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, OnCompleteListener<AuthResult> { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Utils.instance.makeLogd(TAG, "signInWithCredential:success")
                        val intent = Intent(this, ProfileActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                        startActivity(intent)
                        finish()
                        // [START_EXCLUDE]
                        //updateUI(STATE_SIGNIN_SUCCESS, user);
                        // [END_EXCLUDE]
                    } else {
                        // Sign in failed, display a message and update the UI
                        Utils.instance.makeLogw(TAG, "signInWithCredential:failure" + task.exception!!)
                        if (task.exception is FirebaseAuthInvalidCredentialsException) {
                            // The verification code entered was invalid
                            // [START_EXCLUDE silent]
                            //mVerificationField.setError("Invalid code.");
                            // [END_EXCLUDE]
                        }
                        // [START_EXCLUDE silent]
                        // Update UI
                        //updateUI(STATE_SIGNIN_FAILED);
                        // [END_EXCLUDE]
                    }
                })
    }
    // [END sign_in_with_phone]

}
