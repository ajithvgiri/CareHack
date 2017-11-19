package softfruit.solutions.carehack.activity.booking

import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_booking.*
import softfruit.solutions.carehack.R
import softfruit.solutions.carehack.adapter.BookingSlotsAdapter
import softfruit.solutions.carehack.model.Slot

class BookingActivity : AppCompatActivity() {


    private val dates = ArrayList<Slot>()
    private val times = ArrayList<Slot>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)
        val layoutManger1 = LinearLayoutManager(this)
        val layoutManger2 = LinearLayoutManager(this)
        layoutManger1.orientation = LinearLayoutManager.HORIZONTAL
        layoutManger2.orientation = LinearLayoutManager.HORIZONTAL
        dateRecyclerView.layoutManager = layoutManger1
        timeRecyclerView.layoutManager = layoutManger2
        dateRecyclerView.adapter = BookingSlotsAdapter(this, dates)
        timeRecyclerView.adapter = BookingSlotsAdapter(this, times)


        book_appointment.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, BookingConfirmed::class.java))
            finish()
        })

        consultation.setOnClickListener(View.OnClickListener {
            consultation.setBackgroundResource(R.drawable.button_shape_small_active)
            consultation.setTextColor(ContextCompat.getColor(this,R.color.colorText))
            followup.setBackgroundResource(R.drawable.button_shape_small_inactive)
            followup.setTextColor(ContextCompat.getColor(this,R.color.colorTextGrey))
        })

        followup.setOnClickListener(View.OnClickListener {
            followup.setBackgroundResource(R.drawable.button_shape_small_active)
            followup.setTextColor(ContextCompat.getColor(this,R.color.colorText))
            consultation.setBackgroundResource(R.drawable.button_shape_small_inactive)
            consultation.setTextColor(ContextCompat.getColor(this,R.color.colorTextGrey))
        })



        dates.add(Slot(1, "01", "Nov", false))
        dates.add(Slot(1, "02", "Nov", false))
        dates.add(Slot(1, "03", "Nov", false))
        dates.add(Slot(1, "04", "Nov", false))
        dates.add(Slot(1, "05", "Nov", false))
        dates.add(Slot(1, "06", "Nov", false))
        dates.add(Slot(1, "07", "Nov", false))
        dates.add(Slot(1, "08", "Nov", false))
        dates.add(Slot(1, "09", "Nov", false))
        dates.add(Slot(1, "10", "Nov", false))
        dates.add(Slot(1, "11", "Nov", false))
        dates.add(Slot(1, "12", "Nov", false))
        dates.add(Slot(1, "13", "Nov", false))
        dates.add(Slot(1, "14", "Nov", false))
        dates.add(Slot(1, "15", "Nov", false))
        dates.add(Slot(1, "16", "Nov", false))
        dates.add(Slot(1, "17", "Nov", false))
        dates.add(Slot(1, "18", "Nov", false))
        dates.add(Slot(1, "19", "Nov", false))
        dates.add(Slot(1, "20", "Nov", false))
        dates.add(Slot(1, "21", "Nov", false))
        dates.add(Slot(1, "22", "Nov", false))
        dates.add(Slot(1, "23", "Nov", false))
        dates.add(Slot(1, "24", "Nov", false))
        dates.add(Slot(1, "25", "Nov", false))
        dates.add(Slot(1, "26", "Nov", false))
        dates.add(Slot(1, "27", "Nov", false))
        dates.add(Slot(1, "28", "Nov", false))
        dates.add(Slot(1, "29", "Nov", false))
        dates.add(Slot(1, "30", "Nov", false))
        dates.add(Slot(1, "31", "Nov", false))


        times.add(Slot(1, "09:30 am", false))
        times.add(Slot(1, "10:30 am", false))
        times.add(Slot(1, "10:30 am", false))
        times.add(Slot(1, "11:30 am", false))
        times.add(Slot(1, "12:30 pm", false))
        times.add(Slot(1, "01:30 pm", false))
        times.add(Slot(1, "02:30 pm", false))
        times.add(Slot(1, "03:30 pm", false))
        times.add(Slot(1, "04:30 pm", false))
        times.add(Slot(1, "05:30 pm", false))
        times.add(Slot(1, "06:30 pm", false))
        times.add(Slot(1, "10:30 am", false))
        times.add(Slot(1, "11:30 am", false))
        times.add(Slot(1, "09:30 am", false))
        times.add(Slot(1, "01:30 pm", false))
        times.add(Slot(1, "02:30 am", false))
        times.add(Slot(1, "03:30 pm", false))
        times.add(Slot(1, "04:30 am", false))
        times.add(Slot(1, "05:30 pm", false))
        times.add(Slot(1, "06:30 am", false))
        times.add(Slot(1, "07:30 pm", false))
        times.add(Slot(1, "08:30 am", false))
        times.add(Slot(1, "09:30 pm", false))
        times.add(Slot(1, "10:30 am", false))
        times.add(Slot(1, "11:30 pm", false))
        times.add(Slot(1, "12:30 am", false))
        times.add(Slot(1, "01:30 pm", false))
        times.add(Slot(1, "02:30 am", false))
        times.add(Slot(1, "03:30 pm", false))
        times.add(Slot(1, "04:30 am", false))
        times.add(Slot(1, "05:30 pm", false))
    }
}