package softfruit.solutions.carehack.activity.doctors

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_doctors.*
import softfruit.solutions.carehack.R
import softfruit.solutions.carehack.adapter.DoctorsAdapter
import softfruit.solutions.carehack.model.Doctors
import java.util.*

class DoctorsActivity : AppCompatActivity() {

    private val doctors = ArrayList<Doctors>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctors)
        doctorRecyclerView.layoutManager = LinearLayoutManager(this)
        doctorRecyclerView.adapter = DoctorsAdapter(this, doctors)
        doctors.add(Doctors(1, "", "", "", "", ""))
        doctors.add(Doctors(1, "", "", "", "", ""))
        doctors.add(Doctors(1, "", "", "", "", ""))
        doctors.add(Doctors(1, "", "", "", "", ""))
        doctors.add(Doctors(1, "", "", "", "", ""))
        doctors.add(Doctors(1, "", "", "", "", ""))
        doctors.add(Doctors(1, "", "", "", "", ""))
        doctors.add(Doctors(1, "", "", "", "", ""))
        doctors.add(Doctors(1, "", "", "", "", ""))
        doctors.add(Doctors(1, "", "", "", "", ""))
        doctors.add(Doctors(1, "", "", "", "", ""))
        doctors.add(Doctors(1, "", "", "", "", ""))
        doctors.add(Doctors(1, "", "", "", "", ""))
    }
}
