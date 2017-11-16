package softfruit.solutions.quickdoc.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import softfruit.solutions.quickdoc.R
import softfruit.solutions.quickdoc.activity.doctors.DoctorsActivity
import softfruit.solutions.quickdoc.model.Category

/**
 * Created by ajithvgiri on 18/06/17.
 */

class CategoriesAdapter(internal var activity: Activity, internal var categories: List<Category>) : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {
    internal var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.category_adapter, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categories[position]
        holder.categoryTitle.text = category.category
        holder.categoryIcon.setImageResource(category.icon)
        holder.categoryCard.setOnClickListener(View.OnClickListener {
            val intent = Intent(activity, DoctorsActivity::class.java)
            activity.startActivity(intent)
        })
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var categoryTitle: TextView
        var categoryCard: CardView
        var categoryIcon: ImageView

        init {
            categoryTitle = itemView.findViewById<View>(R.id.categoryTitle) as TextView
            categoryCard = itemView.findViewById<View>(R.id.categoryCard) as CardView
            categoryIcon = itemView.findViewById<View>(R.id.categoryIcon) as ImageView
        }
    }


}
