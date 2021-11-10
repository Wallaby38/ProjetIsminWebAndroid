package com.ismin.csproject

import android.content.Context

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


private const val ARG_STATIONS= "stations"



class FragmentStationList : Fragment() {

    private lateinit var stations: ArrayList<StationToView>
    private lateinit var adapter: StationAdapter

    private lateinit var listener : MainActivityCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val argStations = requireArguments().getSerializable(ARG_STATIONS) as ArrayList<StationToView>?
        stations = argStations ?: ArrayList()
        adapter = StationAdapter(stations)
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_station_list, container, false)

        val rcvStation = view.findViewById<RecyclerView>(R.id.f_station_list_rcv_stations)
        rcvStation.layoutManager = LinearLayoutManager(context)
        rcvStation.adapter = adapter
        adapter.setOnItemClickListener(object : StationAdapter.onItemClickListener{
            override fun onItemClick(position: String) {
                listener.goToInfoStation(position)
            }

        })
        return view;
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainActivityCallback) {
            listener = context
        } else {
            throw RuntimeException("$context must implement MyActivityCallback")
        }
    }


    companion object {
        @JvmStatic
        fun newInstance(stations: ArrayList<StationToView>) =
            FragmentStationList().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_STATIONS, stations)
                }
            }
    }
}