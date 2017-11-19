package softfruit.solutions.carehack.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*
import softfruit.solutions.carehack.R
import softfruit.solutions.carehack.SplashActivity
import softfruit.solutions.carehack.model.User
import softfruit.solutions.carehack.utils.Utils

class ProfileFragment : Fragment() {

    val TAG: String = "ProfileFragment"


    // Write a message to the database
    val database = FirebaseDatabase.getInstance()
    val mDatabase = database.getReference("QuickDoc").child("users").child(FirebaseAuth.getInstance().uid)

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_profile, container, false)
        view.logout.setOnClickListener(View.OnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(activity, SplashActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            activity.startActivity(intent)
            activity.finish()
        })


        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot != null) {
                    Utils.instance.makeLogd(TAG, "dataSnapshot " + dataSnapshot.value.toString())
                    val user = dataSnapshot.getValue(User::class.java)
                    if (user != null) {
                        Utils.instance.makeLogd(TAG, "dataSnapshot " + dataSnapshot.toString())
                        fullnameText.text = user.fullname
                        ageText.text = user.age + " years old"
                        placeText.text = user.place
                        //Picasso.with(activity).load(user.image_url).into(profile_image)
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })

        return view
    }

}// Required empty public constructor
