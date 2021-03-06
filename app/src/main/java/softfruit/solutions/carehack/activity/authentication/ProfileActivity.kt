package softfruit.solutions.carehack.activity.authentication

import android.app.Activity
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_profile.*
import softfruit.solutions.carehack.R
import softfruit.solutions.carehack.activity.MainActivity
import softfruit.solutions.carehack.model.User
import softfruit.solutions.carehack.utils.Utils
import java.io.ByteArrayOutputStream
import java.io.IOException


class ProfileActivity : AppCompatActivity() {


    val TAG: String = "ProfileActivity"
    //Cloud Storage
    val storage = FirebaseStorage.getInstance()

    // Create a storage reference from our app
    val storageRef = storage.reference

    // Create a child reference
    // imagesRef now points to "images"
    val imagesRef = storageRef.child("images")

    val profileDefaultRef = imagesRef.child("profile/avatar.jpg")

    var profileImage: Boolean = false

    var profileRef: StorageReference? = null

    // Write a message to the database
    val database = FirebaseDatabase.getInstance()
    val mDatabase = database.getReference("QuickDoc")


    var profileImageUrl: String = ""

    var gender: Boolean = true

    override fun onStart() {
        super.onStart()
        if (FirebaseAuth.getInstance().uid == null) {
            startActivity(Intent(this, PhoneAuthActivity::class.java))
            finish()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot != null) {
                    Utils.instance.makeLogd(TAG, "dataSnapshot " + dataSnapshot.value.toString())
                    //Utils.instance.makeLogd(TAG, "dataSnapshot " + dataSnapshot.child("users").child(FirebaseAuth.getInstance().uid))
//                    dataSnapshot.child("users").children
                    for (snapshot in dataSnapshot.child("users").children) {
                        Utils.instance.makeLogd(TAG, "dataSnapshot " + snapshot.toString())

                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })

        pick_a_photo.setOnClickListener(View.OnClickListener {
            imagePicker(1)
            camera.visibility = View.GONE
        })
        finish.setOnClickListener(View.OnClickListener {
            if (fullnameText.text.toString().length < 4) {

            }
            uploadProfileImage()
        })


        male.setOnClickListener(View.OnClickListener {
            if (gender) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    male.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.colorPrimary))
                    female.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.colorIconInactive))
                }
                gender = false
            }
        })

        female.setOnClickListener(View.OnClickListener {
            if (!gender) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    female.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.colorPrimary))
                    male.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.colorIconInactive))
                }
                gender = true
            }
        })
    }

    fun imagePicker(PICK_IMAGE_REQUEST: Int) {
        val intent = Intent()
        // Show only images, no videos or anything else
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        // Always show the chooser (if there are multiple options available)
        startActivityForResult(Intent.createChooser(intent, "Profile Picture"), PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            val uri = data.data
            when (requestCode) {
                1 -> {
                    try {
                        val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, uri)
                        profile_image.setImageBitmap(bitmap)
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }

                }
            }
        }
    }

    private fun uploadProfileImage() {
        // Get the data from an ImageView as bytes
        profile_image.isDrawingCacheEnabled = true
        profile_image.buildDrawingCache()
        val bitmap = profile_image.drawingCache
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        profileRef = imagesRef.child("profile/" + FirebaseAuth.getInstance().uid + ".jpg")

        val uploadTask = profileRef!!.putBytes(data)
        uploadTask.addOnFailureListener(OnFailureListener {
            // Handle unsuccessful uploads
            profileImage = false
            uploadProfileDetails()
        }).addOnSuccessListener(OnSuccessListener<Any> { taskSnapshot ->
            // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
            profileImageUrl = taskSnapshot.toString()
            Utils.instance.makeLogd(TAG, "download url " + taskSnapshot.toString())
            Utils.instance.makeLogd(TAG, "download url " + taskSnapshot)
            profileImage = true
            uploadProfileDetails()
        })
    }

    private fun uploadProfileDetails() {
        var profileImageUrl = profileDefaultRef.toString()
        if (profileImage) {
            profileImageUrl = profileImageUrl
        }

        val user = User(FirebaseAuth.getInstance().uid.toString(),
                fullnameText.text.toString(),
                ageText.text.toString(), placeText.text.toString(), profileImageUrl)

        mDatabase.child("users").child(FirebaseAuth.getInstance().uid).setValue(user)

        val intent = Intent(this@ProfileActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
