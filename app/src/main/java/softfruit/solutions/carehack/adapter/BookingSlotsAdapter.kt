package softfruit.solutions.carehack.adapter

import android.app.Activity
import android.support.v4.content.ContextCompat
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import softfruit.solutions.carehack.R
import softfruit.solutions.carehack.model.Slot
import softfruit.solutions.carehack.utils.Utils

/*
 * Created by ajithvgiri on 18/06/17.
 */

class BookingSlotsAdapter(internal var activity: Activity, private var slots: List<Slot>) : RecyclerView.Adapter<BookingSlotsAdapter.ViewHolder>() {
    private val TAG: String = "BookingSlots"

    private var selectedPosition: Int = 0
    private var selectedPositionTime: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.bookings_slot_adapter, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val slot = slots[position]

        if (!slot.slot_time.isNotEmpty()) {
            holder.slot.visibility = View.GONE
            if (slot.selected) {
                holder.slotCard.setCardBackgroundColor(ContextCompat.getColor(activity, R.color.colorPrimary))
                holder.slotDate.setTextColor(ContextCompat.getColor(activity, R.color.colorText))
                holder.slotMonth.setTextColor(ContextCompat.getColor(activity, R.color.colorText))
            } else {
                holder.slotCard.setCardBackgroundColor(ContextCompat.getColor(activity, R.color.colorBackground))
                holder.slotDate.setTextColor(ContextCompat.getColor(activity, R.color.colorTextGrey))
                holder.slotMonth.setTextColor(ContextCompat.getColor(activity, R.color.colorTextGrey))
            }
            holder.slotDate.text = slot.slot_date
            holder.slotMonth.text = slot.slot_month
            holder.slotCard.setOnClickListener({
                Utils.instance.makeLogd(TAG, "position " + position)
                Utils.instance.makeLogd(TAG, "selectedPosition " + selectedPosition)
                val selectedSlot = slots[selectedPosition]
                selectedSlot.selected = false
                Utils.instance.makeLogd(TAG, "selectedPosition " + selectedPosition)
                slot.selected = true
                selectedPosition = position
                notifyDataSetChanged()
            })

        } else {
            holder.slot.visibility = View.VISIBLE
            holder.slotDate.visibility = View.GONE
            holder.slotMonth.visibility = View.GONE
            holder.slot.text = slot.slot_time
            if (slot.selected) {
                holder.slotCard.setCardBackgroundColor(ContextCompat.getColor(activity, R.color.colorPrimary))
                holder.slot.setTextColor(ContextCompat.getColor(activity, R.color.colorText))
            } else {
                holder.slotCard.setCardBackgroundColor(ContextCompat.getColor(activity, R.color.colorBackground))
                holder.slot.setTextColor(ContextCompat.getColor(activity, R.color.colorTextGrey))
            }

            holder.slotCard.setOnClickListener({
                val selectedSlot = slots[selectedPositionTime]
                selectedSlot.selected = false
                slot.selected = true
                selectedPositionTime = position
                notifyDataSetChanged()
            })

        }


    }

    override fun getItemCount(): Int = slots.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var slotDate: TextView = itemView.findViewById<View>(R.id.slotDate) as TextView
        var slotMonth: TextView = itemView.findViewById<View>(R.id.slotMonth) as TextView
        var slot: TextView = itemView.findViewById<View>(R.id.slot) as TextView
        var slotCard: CardView = itemView.findViewById<View>(R.id.slotCard) as CardView

    }


}
