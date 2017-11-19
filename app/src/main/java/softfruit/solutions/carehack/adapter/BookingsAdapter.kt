package softfruit.solutions.carehack.adapter

import android.app.Activity
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import softfruit.solutions.carehack.R
import softfruit.solutions.carehack.model.Bookings

/**
 * Created by ajithvgiri on 18/06/17.
 */

class BookingsAdapter(internal var activity: Activity, internal var bookings: List<Bookings>) : RecyclerView.Adapter<BookingsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.bookings_adapter, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val booking = bookings[position]
        holder.bookingTime.text = booking.time
        holder.bookingDate.text = booking.date
        holder.bookingDoctor.text = booking.doctor
        holder.bookingCard.setOnClickListener(View.OnClickListener {
            Toast.makeText(activity, "Clicked", Toast.LENGTH_SHORT).show()
        })
    }

    override fun getItemCount(): Int {
        return bookings.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var bookingTime: TextView = itemView.findViewById<View>(R.id.bookingTime) as TextView
        var bookingDate: TextView = itemView.findViewById<View>(R.id.bookingDate) as TextView
        var bookingDoctor: TextView = itemView.findViewById<View>(R.id.bookingDoctor) as TextView
        var bookingCard: CardView = itemView.findViewById<View>(R.id.bookingCard) as CardView

    }


}
