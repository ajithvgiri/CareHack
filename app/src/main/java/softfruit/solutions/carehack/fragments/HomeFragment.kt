package softfruit.solutions.carehack.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_home.view.*
import softfruit.solutions.carehack.R
import softfruit.solutions.carehack.adapter.CategoriesAdapter
import softfruit.solutions.carehack.model.Category
import java.util.*

class HomeFragment : Fragment() {


    private val categories = ArrayList<Category>()


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_home, container, false)




        view.categoryRecyclerView.layoutManager = GridLayoutManager(activity, 2)
        view.categoryRecyclerView.adapter = CategoriesAdapter(activity, categories)
        categories.add(Category(1, "General Medicine", R.drawable.ic_general_medicine))
        categories.add(Category(2, "Gynecology", R.drawable.ic_gynecology))
        categories.add(Category(3, "Pediatrics", R.drawable.ic_pediatrics))
        categories.add(Category(4, "Dermatology", R.drawable.ic_dermatology))
        categories.add(Category(5, "Dentistry", R.drawable.ic_dentistry))
        categories.add(Category(6, "Cardiology", R.drawable.ic_cardiology))
        categories.add(Category(7, "ENT", R.drawable.ic_ent))
        categories.add(Category(8, "Gastroenterology", R.drawable.ic_gastroenterology))
        categories.add(Category(9, "Orthopedic", R.drawable.ic_orthopedic))
        categories.add(Category(10, "Neurology", R.drawable.ic_neurology))

        return view
    }


}
