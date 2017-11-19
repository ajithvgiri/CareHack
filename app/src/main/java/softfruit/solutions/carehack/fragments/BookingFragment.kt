package softfruit.solutions.carehack.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_booking.view.*
import softfruit.solutions.carehack.R
import softfruit.solutions.carehack.adapter.BookingsAdapter
import softfruit.solutions.carehack.model.Bookings
import java.util.*

class BookingFragment : Fragment() {


    private val bookings = ArrayList<Bookings>()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_booking, container, false)
        view.bookingsRecyclerView.layoutManager = LinearLayoutManager(activity)
        view.bookingsRecyclerView.adapter = BookingsAdapter(activity, bookings)
        bookings.add(Bookings("1", "12 Nov 2017", "10:30", "Dr. Nimisha Anil", "MBBS, Cardiologist"))
        bookings.add(Bookings("1", "12 Nov 2017", "10:30", "Dr. Nimisha Anil", "MBBS, Cardiologist"))
        bookings.add(Bookings("1", "12 Nov 2017", "10:30", "Dr. Nimisha Anil", "MBBS, Cardiologist"))
        bookings.add(Bookings("1", "12 Nov 2017", "10:30", "Dr. Nimisha Anil", "MBBS, Cardiologist"))
        bookings.add(Bookings("1", "12 Nov 2017", "10:30", "Dr. Nimisha Anil", "MBBS, Cardiologist"))
        bookings.add(Bookings("1", "12 Nov 2017", "10:30", "Dr. Nimisha Anil", "MBBS, Cardiologist"))
        bookings.add(Bookings("1", "12 Nov 2017", "10:30", "Dr. Nimisha Anil", "MBBS, Cardiologist"))
        bookings.add(Bookings("1", "12 Nov 2017", "10:30", "Dr. Nimisha Anil", "MBBS, Cardiologist"))
        bookings.add(Bookings("1", "12 Nov 2017", "10:30", "Dr. Nimisha Anil", "MBBS, Cardiologist"))
        bookings.add(Bookings("1", "12 Nov 2017", "10:30", "Dr. Nimisha Anil", "MBBS, Cardiologist"))
        bookings.add(Bookings("1", "12 Nov 2017", "10:30", "Dr. Nimisha Anil", "MBBS, Cardiologist"))
        bookings.add(Bookings("1", "12 Nov 2017", "10:30", "Dr. Nimisha Anil", "MBBS, Cardiologist"))
        bookings.add(Bookings("1", "12 Nov 2017", "10:30", "Dr. Nimisha Anil", "MBBS, Cardiologist"))
        bookings.add(Bookings("1", "12 Nov 2017", "10:30", "Dr. Nimisha Anil", "MBBS, Cardiologist"))
        bookings.add(Bookings("1", "12 Nov 2017", "10:30", "Dr. Nimisha Anil", "MBBS, Cardiologist"))
        return view

    }


}// Required empty public constructor
