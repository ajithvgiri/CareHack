package softfruit.solutions.carehack.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import softfruit.solutions.carehack.R
import softfruit.solutions.carehack.activity.booking.BookingActivity
import softfruit.solutions.carehack.model.Doctors

/**
 * Created by ajithvgiri on 18/06/17.
 */

class DoctorsAdapter(internal var activity: Activity, internal var doctors: List<Doctors>) : RecyclerView.Adapter<DoctorsAdapter.ViewHolder>() {
    internal var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.doctors_adapter, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val doctor = doctors[position]
//        holder.slotTitle.text = doctor.name
//        holder.categoryIcon.setImageResource(doctors.icon)
//        holder.slotCard.setOnClickListener(View.OnClickListener {
//            Toast.makeText(activity, "" + position, Toast.LENGTH_LONG).show()
//        })
        holder.doctorCard.setOnClickListener(View.OnClickListener { activity.startActivity(Intent(activity, BookingActivity::class.java)) })

    }

    override fun getItemCount(): Int {
        return doctors.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        var slotTitle: TextView
var doctorCard: CardView = itemView.findViewById<View>(R.id.doctorCard) as CardView
//        var categoryIcon: ImageView

        init {
//            slotTitle = itemView.findViewById<View>(R.id.slotTitle) as TextView
            //            categoryIcon = itemView.findViewById<View>(R.id.categoryIcon) as ImageView
        }
    }


}
