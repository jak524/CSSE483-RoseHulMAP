package edu.rosehulman.knuppja.rosehulmap

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import android.content.ActivityNotFoundException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{

    private lateinit var settingsRef: DocumentReference
    val auth = FirebaseAuth.getInstance()
    lateinit var authListener: FirebaseAuth.AuthStateListener
    private val RC_SIGN_IN = 1
    lateinit var uid: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)



        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.academic_buildings -> {
                val fm = supportFragmentManager
                val ft = supportFragmentManager.beginTransaction()
                val fragment = BuildingListFragment(fm)
                ft.replace(R.id.fragment_container, fragment)
                ft.addToBackStack("")
                ft.commit()
            }
            R.id.professors -> {

                val fm = supportFragmentManager
                val ft = supportFragmentManager.beginTransaction()
                val fragment = ProfessorListFragment(fm)
                ft.replace(R.id.fragment_container, fragment)
                ft.addToBackStack("")
                ft.commit()

            }
            R.id.facilities -> {

                val fm = supportFragmentManager
                val ft = supportFragmentManager.beginTransaction()
                val fragment = FacilitiesFragment()
                ft.replace(R.id.fragment_container, fragment)
                ft.addToBackStack("")
                ft.commit()

            }
            R.id.full_campus_map -> {
                val fm = supportFragmentManager
                val ft = supportFragmentManager.beginTransaction()
                val fragment = FullCampusMapFragment()
                ft.replace(R.id.fragment_container, fragment)
                ft.addToBackStack("")
                ft.commit()

            }

            R.id.favorites -> {


            }

            R.id.contact -> {
                //https://medium.com/@cketti/android-sending-email-using-intents-3da63662c58f
                val mailto = "mailto:gishbd@rose-hulman.edu" +
                        "&subject=" + Uri.encode("RoseHulmap Contact")
                val emailIntent = Intent(Intent.ACTION_SENDTO)
                emailIntent.data = Uri.parse(mailto)
                try {
                    startActivity(emailIntent)
                } catch (e: ActivityNotFoundException) {
                    //TODO: Handle case where no email app is available
                }

            }

        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
