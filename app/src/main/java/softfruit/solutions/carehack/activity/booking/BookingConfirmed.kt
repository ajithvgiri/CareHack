package softfruit.solutions.carehack.activity.booking

import android.annotation.SuppressLint
import android.content.ContentValues
import android.os.Bundle
import android.provider.CalendarContract
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_booking_confirmed.*
import softfruit.solutions.carehack.R
import softfruit.solutions.carehack.utils.Utils
import java.util.*


class BookingConfirmed : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking_confirmed)

        if (Utils.instance.calendarPermission(this)) {
            addtoCalendar.setOnClickListener(View.OnClickListener { addtoCalendar(2017, 10, 19, 10, 0) })
        }
    }


    @SuppressLint("MissingPermission")
    fun addtoCalendar(year: Int, month: Int, date: Int, hour: Int, minute: Int) {

        val calID: Long = 3
        var startMillis: Long = 0
        var endMillis: Long = 0
        val beginTime = Calendar.getInstance()
        val endTime = Calendar.getInstance()
        beginTime.set(year, month, date, hour, minute)
        endTime.set(year, month, date, hour, minute)
        startMillis = beginTime.timeInMillis
        endMillis = endTime.timeInMillis


        val cr = contentResolver
        val values = ContentValues().apply {
            put(CalendarContract.Events.DTSTART, startMillis)
            put(CalendarContract.Events.DTEND, endMillis)
            put(CalendarContract.Events.TITLE, "Dr.Nimisha Anil  - Care Hack")
            put(CalendarContract.Events.DESCRIPTION, "Appointment with Dr. Nimisha Anil, Cardiologist")
            put(CalendarContract.Events.EVENT_LOCATION, "UL Cyber Park, Kerala")
            put(CalendarContract.Events.CALENDAR_ID, calID)
            put(CalendarContract.Events.EVENT_TIMEZONE, "India/Chennai")
        }

        val uri = cr.insert(CalendarContract.Events.CONTENT_URI, values)

        Toast.makeText(this, getString(R.string.successfully_added_to_calendar), Toast.LENGTH_SHORT).show()
        finish()
    }
}
