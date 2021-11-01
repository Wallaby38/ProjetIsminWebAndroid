package com.ismin.csproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val stations = StationList()

    private val adapter = StationAdapter(stations.getAllStations())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        displayStationList()

    }


    private fun displayStationList() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val fragment = FragmentStationList.newInstance(stations.getAllStations())
        fragmentTransaction.replace(R.id.a_main_lyt_fragment_container, fragment)
        fragmentTransaction.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.list -> {
                // User chose the "Settings" item, show the app settings UI...
                Toast.makeText(this, "LISTE", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.map -> {
                // User chose the "Favorite" action, mark the current item as a favorite...
                Toast.makeText(this, "MAP", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.info -> {
                // User chose the "Favorite" action, mark the current item as a favorite...
                Toast.makeText(this, "INFO", Toast.LENGTH_SHORT).show()
                true
            }
            // If we got here, the user's action was not recognized.
            else -> super.onOptionsItemSelected(item)
        }
    }

}