package softfruit.solutions.quickdoc.activity.authentication

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_profile.*
import softfruit.solutions.quickdoc.R
import softfruit.solutions.quickdoc.activity.MainActivity
import softfruit.solutions.quickdoc.model.User
import java.io.ByteArrayOutputStream
import java.io.IOException

class ProfileActivity : AppCompatActivity() {


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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        pick_a_photo.setOnClickListener(View.OnClickListener { imagePicker(1) })
        finish.setOnClickListener(View.OnClickListener {
            if (fullnameText.text.toString().length < 4) {

            }
            uploadProfileImage()
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
                    var bitmap: Bitmap? = null
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, uri)
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
        uploadTask.addOnFailureListener(OnFailureListener { exception ->
            // Handle unsuccessful uploads
            profileImage = false
            uploadProfileDetails()
        }).addOnSuccessListener(OnSuccessListener<Any> { taskSnapshot ->
            // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
            val downloadUrl = taskSnapshot
            profileImage = true
            uploadProfileDetails()
        })
    }

    private fun uploadProfileDetails() {
        var profileImageUrl = profileDefaultRef.toString()
        if (profileImage) {
            profileImageUrl = profileRef.toString()
        }

        val user = User(FirebaseAuth.getInstance().uid.toString(),
                fullnameText.text.toString(),
                ageText.text.toString(), emailText.text.toString(), placeText.text.toString(), profileImageUrl)

        mDatabase.child("users").child(FirebaseAuth.getInstance().uid).setValue(user)

        val intent = Intent(this@ProfileActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
