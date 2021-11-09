package com.ismin.csproject

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.activity.result.ActivityResult
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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult

class MainActivity : AppCompatActivity(),MainActivityCallback,OnMapReadyCallback {
    private val stations = StationList()


    val supportMapFragment: SupportMapFragment = SupportMapFragment.newInstance()
    private lateinit var clusterManager: ClusterManager<Point>

    val SERVER_BASE_URL = "https://project-jla-qja.cleverapps.io/"
    val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(SERVER_BASE_URL)
        .build()

    val stationService = retrofit.create(StationService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadAllStations()


    }


    private fun displayStationList() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val fragment = FragmentStationList.newInstance(stations.getAllStations())
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
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("id_station", id)
        startForResult.launch(intent)
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




    private fun addAllPoint() {
        for (station in stations.getAllStations()) {
            addPoint(station.ylatitude,station.xlongitude)
        }


    }
    private fun addPoint(lat: Double,lng: Double) {



        clusterManager.addItem(Point(lat, lng, lat.toString(), lng.toString()))



    }

    override fun onMapReady(gmap: GoogleMap) {
        gmap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(46.227638, 2.213749), 6f))

        // Initialize the manager with the context and the map.
        // (Activity extends context, so we can pass 'this' in the constructor.)
        clusterManager = ClusterManager(this, gmap)

        // Point the map's listeners at the listeners implemented by the cluster
        // manager.
        gmap.setOnCameraIdleListener(clusterManager)
        gmap.setOnMarkerClickListener(clusterManager)

        addAllPoint()




    }


    private fun loadAllStations() {
        stationService.getStations().enqueue(object : Callback<List<StationToView>> {


            override fun onFailure(call: Call<List<StationToView>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error when trying to fetch stations" + t.localizedMessage, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<List<StationToView>>,
                response: Response<List<StationToView>>
            ) {
                val allStations: List<StationToView>? = response.body()

                allStations?.forEach {
                    stations.addStation(it)
                }
                displayStationList()
            }


        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //Clear the Activity's bundle of the subsidiary fragments' bundles.
        outState.clear()
    }


    private val startForResult = registerForActivityResult(StartActivityForResult()) {
            result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            val station = result.data?.getSerializableExtra(STATION_TO_CHANGE) as Station
            stations.setBookMarked(station)
            displayStationList()
        }
    }


}