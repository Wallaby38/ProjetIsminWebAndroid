package com.ismin.csproject

import android.content.Intent
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
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.ClusterItem
import com.google.maps.android.clustering.ClusterManager

class MainActivity : AppCompatActivity(),MainActivityCallback,OnMapReadyCallback {
    private val stations = StationList()

    private val adapter = StationAdapter(stations.getAllStationsToView())
    val supportMapFragment: SupportMapFragment = SupportMapFragment.newInstance()
    private lateinit var clusterManager: ClusterManager<Point>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        displayStationList()

    }


    private fun displayStationList() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val fragment = FragmentStationList.newInstance(stations.getAllStationsToView())
        fragmentTransaction.replace(R.id.a_main_lyt_fragment_container, fragment)
        fragmentTransaction.commit()
    }

    private fun displayInfoApp() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val fragment = AppInfo.newInstance()
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
                displayStationList()
                true
            }
            R.id.map -> {
                // User chose the "Favorite" action, mark the current item as a favorite...
                Toast.makeText(this, "MAP", Toast.LENGTH_SHORT).show()
                displayMap()
                true
            }
            R.id.info -> {
                // User chose the "Favorite" action, mark the current item as a favorite...
                Toast.makeText(this, "INFO", Toast.LENGTH_SHORT).show()
                displayInfoApp()
                true
            }
            // If we got here, the user's action was not recognized.
            else -> super.onOptionsItemSelected(item)
        }
    }


    fun displayMap() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        supportMapFragment.getMapAsync(this)
        fragmentTransaction.replace(R.id.a_main_lyt_fragment_container, supportMapFragment)
        fragmentTransaction.commit()
    }

    override fun goToInfoStation(id : String) {
        Toast.makeText(this,id, Toast.LENGTH_SHORT).show()
        val intent = Intent(this, DetailActivity::class.java)
        val station : Station? = stations.getStation(id)
        intent.putExtra("station", station)
        this.startActivity(intent)
    }
    inner class Point(
        lat: Double,
        lng: Double,
        title: String,
        snippet: String
    ) : ClusterItem {

        private val position: LatLng
        private val title: String
        private val snippet: String

        override fun getPosition(): LatLng {
            return position
        }

        override fun getTitle(): String? {
            return title
        }

        override fun getSnippet(): String? {
            return snippet
        }

        init {
            position = LatLng(lat, lng)
            this.title = title
            this.snippet = snippet
        }


    }




    private fun addItems() {

        // Set some lat/lng coordinates to start with.
        var lat = 51.5145160
        var lng = -0.1270060

        // Add ten cluster items in close proximity, for purposes of this example.
        for (i in 0..9) {
            val offset = i / 60.0
            lat += offset
            lng += offset
            val offsetItem =
                Point(lat, lng, "Title $i", "Snippet $i")
            clusterManager.addItem(offsetItem)
        }
    }

    override fun onMapReady(gmap: GoogleMap) {
        gmap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(51.503186, -0.126446), 10f))

        // Initialize the manager with the context and the map.
        // (Activity extends context, so we can pass 'this' in the constructor.)
        clusterManager = ClusterManager(this, gmap)

        // Point the map's listeners at the listeners implemented by the cluster
        // manager.
        gmap.setOnCameraIdleListener(clusterManager)
        gmap.setOnMarkerClickListener(clusterManager)

        // Add cluster items (markers) to the cluster manager.
        addItems()
    }

}