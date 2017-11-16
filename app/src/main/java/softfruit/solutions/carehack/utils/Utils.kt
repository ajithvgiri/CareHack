package softfruit.solutions.carehack.utils

import android.util.Log

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

}
