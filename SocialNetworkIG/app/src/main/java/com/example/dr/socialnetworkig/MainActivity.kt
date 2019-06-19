package com.example.dr.socialnetworkig

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    private var navigationView: NavigationView? = null
    private var drawerLayout: DrawerLayout? = null
    private val postList: RecyclerView? = null
    private var mToolbar: Toolbar? = null
    private var actionBarDrawerToggle: ActionBarDrawerToggle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mToolbar = findViewById<View>(R.id.main_page_toolbar) as Toolbar
        setSupportActionBar(mToolbar)
        supportActionBar!!.title = "Home"

        drawerLayout = findViewById<View>(R.id.drawable_layout) as DrawerLayout
        actionBarDrawerToggle =
            ActionBarDrawerToggle(this@MainActivity, drawerLayout, R.string.drawer_open, R.string.drawer_close)
        drawerLayout!!.addDrawerListener(actionBarDrawerToggle!!)
        actionBarDrawerToggle!!.syncState()
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        navigationView = findViewById<View>(R.id.navigation_view) as NavigationView
        val navView = navigationView!!.inflateHeaderView(R.layout.navigation_header)

        navigationView!!.setNavigationItemSelectedListener { menuItem ->
            UserMenuSelector(menuItem)

            false
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (actionBarDrawerToggle!!.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(item)
    }

    private fun UserMenuSelector(menuItem: MenuItem) {
        when (menuItem.itemId) {
            R.id.nav_profile -> Toast.makeText(this, "Profile", Toast.LENGTH_LONG).show()
            R.id.nav_home -> Toast.makeText(this, "Home", Toast.LENGTH_LONG).show()
            R.id.nav_friends -> Toast.makeText(this, "Friend List", Toast.LENGTH_LONG).show()
            R.id.nav_find_friends -> Toast.makeText(this, "Find Friends", Toast.LENGTH_LONG).show()
            R.id.nav_messages -> Toast.makeText(this, "Messages", Toast.LENGTH_LONG).show()
            R.id.nav_settings -> Toast.makeText(this, "Settings", Toast.LENGTH_LONG).show()
            R.id.nav_logout -> Toast.makeText(this, "Logout", Toast.LENGTH_LONG).show()
        }
    }
}
