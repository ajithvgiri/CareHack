package softfruit.solutions.carehack.utils

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.preference.PreferenceManager
import android.provider.Settings
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.View
import android.widget.Toast
import softfruit.solutions.carehack.R

/**
 * Created by ajithvgiri on 21/10/17.
 */

class Utils private constructor() {


    fun makeLogd(TAG: String, message: String) {
        Log.d(TAG, message)
    }

    fun makeLogw(TAG: String, message: String) {
        Log.w(TAG, message)
    }

    fun makeLoge(TAG: String, message: String) {
        Log.e(TAG, message)
    }

    companion object {
        private var utils: Utils = Utils()

        val instance: Utils
            get() = utils
    }

    fun checkPermission(activity: Activity, request_code: Int, view: View): Boolean {
        var result = 0
        when (request_code) {
            1 -> result = ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_CALENDAR)
            else -> {
            }
        }

        if (result == PackageManager.PERMISSION_GRANTED) {
            return true
        } else {
            requestPermission(activity, request_code, view)
            return false
        }
    }

    fun requestPermission(activity: Activity, request_code: Int, view: View) {
        when (request_code) {
            1 -> if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.WRITE_CALENDAR)) {
                Snackbar.make(view, activity.getString(R.string.calendar_permission), Snackbar.LENGTH_LONG).setAction("Allow") {
                    //Toast.makeText(activity, activity.getString(R.string.permission_request_message), Toast.LENGTH_LONG).show();
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + activity.packageName))
                    intent.addCategory(Intent.CATEGORY_DEFAULT)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    activity.startActivity(intent)
                }.show()

            } else {
                ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.WRITE_CALENDAR), request_code)
            }
            else -> {
            }
        }
    }

    fun calendarPermission(activity: Activity): Boolean {

        val sharedpreferences = PreferenceManager.getDefaultSharedPreferences(activity)


        val PERMISSION_CONSTANT = 101
        val REQUEST_PERMISSION_SETTING = 102
        var status = false

        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.WRITE_CALENDAR)) {
                //Show Information about why you need the permission
                val builder = AlertDialog.Builder(activity)
                builder.setTitle("Need Calendar Permission")
                builder.setMessage("This app needs storage permission.")
                builder.setPositiveButton("Grant") { dialog, which ->
                    dialog.cancel()
                    ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.WRITE_CALENDAR), PERMISSION_CONSTANT)
                }
                builder.setNegativeButton("Cancel") { dialog, which -> dialog.cancel() }
                builder.show()
            } else if (sharedpreferences.getBoolean(Manifest.permission.WRITE_CALENDAR, false)) {
                //Previously Permission Request was cancelled with 'Dont Ask Again',
                // Redirect to Settings after showing Information about why you need the permission
                val builder = AlertDialog.Builder(activity)
                builder.setTitle("Need Calendar Permission")
                builder.setMessage("This app needs storage permission.")
                builder.setPositiveButton("Grant") { dialog, _ ->
                    dialog.cancel()
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri = Uri.fromParts("package", activity.packageName, null)
                    intent.data = uri
                    activity.startActivityForResult(intent, REQUEST_PERMISSION_SETTING)
                    Toast.makeText(activity, "Go to Permissions to Grant Storage", Toast.LENGTH_LONG).show()
                }
                builder.setNegativeButton("Cancel") { dialog, which -> dialog.cancel() }
                builder.show()
            } else {
                //just request the permission
                ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.WRITE_CALENDAR), PERMISSION_CONSTANT)
            }
            val editor = sharedpreferences.edit()
            editor.putBoolean(Manifest.permission.WRITE_CALENDAR, true)
            editor.commit()
        } else {
            //You already have the permission, just go ahead.
            status = true
        }
        return status
    }
}
