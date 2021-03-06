package softfruit.solutions.carehack.activity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import softfruit.solutions.carehack.R
import softfruit.solutions.carehack.fragments.BookingFragment
import softfruit.solutions.carehack.fragments.HomeFragment
import softfruit.solutions.carehack.fragments.ProfileFragment

class MainActivity : AppCompatActivity() {



    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                changeFragment(BookingFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
//                replaceFragment(HomeFragment.newInstance("", ""))
                changeFragment(HomeFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_profile -> {
//                replaceFragment(ProfileFragment.newInstance("", ""))
                changeFragment(ProfileFragment())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        changeFragment(HomeFragment())
    }


    fun changeFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
        transaction.replace(fragmentLayout.id, fragment)
        transaction.addToBackStack(null)

        // Commit the transaction
        transaction.commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }


}

