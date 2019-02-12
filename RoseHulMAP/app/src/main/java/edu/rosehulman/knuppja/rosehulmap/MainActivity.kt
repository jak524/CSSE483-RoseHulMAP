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
import android.util.Log
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference


class MainActivity : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener,
    SplashFragment.OnLoginButtonPressedListener{

    val auth = FirebaseAuth.getInstance()
    lateinit var authListener: FirebaseAuth.AuthStateListener
    private val RC_SIGN_IN = 1
    lateinit var uid: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        initializeListeners()


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
        return when (item.itemId) {
            R.id.action_logout -> {
                // TODO: Sign out.
                auth.signOut()
                true
            }
            else -> super.onOptionsItemSelected(item)
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

    // TODO: add (and remove) an auth state listener upon start (and stop).
    override fun onStart() {
        super.onStart()
        auth.addAuthStateListener(authListener)
    }

    override fun onStop() {
        super.onStop()
        auth.removeAuthStateListener(authListener)
    }

    private fun initializeListeners() {
        // TODO: Create an AuthStateListener that passes the UID
        // to the MovieQuoteFragment if the user is logged in
        // and goes back to the Splash fragment otherwise.
        // See https://firebase.google.com/docs/auth/users#the_user_lifecycle
        authListener = FirebaseAuth.AuthStateListener { auth ->
            val user = auth.currentUser
            Log.d(Constants.TAG, "In auth listener, User: $user")
            if (user != null) {
                this.uid = user.uid
                Log.d(Constants.TAG, "Main UID: ${user.uid}")
                Log.d(Constants.TAG, "Name: ${user.displayName}")
                Log.d(Constants.TAG, "Email: ${user.email}")
                Log.d(Constants.TAG, "Photo: ${user.photoUrl}")
                Log.d(Constants.TAG, "Phone: ${user.phoneNumber}")
                switchToFullCampusMapFragment(user.uid)
            } else {
                switchToSplashFragment()
            }
        }
    }

    private fun switchToSplashFragment() {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment_container, SplashFragment())
        ft.commit()
    }

    private fun switchToFullCampusMapFragment(uid: String) {
        val fm = supportFragmentManager
        val ft = supportFragmentManager.beginTransaction()
        val newFrag = FullCampusMapFragment()
//        newFrag.fragMan = fm
//        newFrag.uid = uid
//        newFrag.showAll = showAll
        ft.replace(R.id.fragment_container, newFrag)
        ft.commit()
    }

    override fun onLoginButtonPressed() {
        launchLoginUI()
    }

    private fun launchLoginUI() {
        // TODO: Build a login intent and startActivityForResult(intent, ...)
        // For details, see https://firebase.google.com/docs/auth/android/firebaseui#sign_in
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build()
        )

        val loginIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .setLogo(R.drawable.ic_launcher_custom)
            .build()

        startActivityForResult(loginIntent, RC_SIGN_IN)
    }
}
