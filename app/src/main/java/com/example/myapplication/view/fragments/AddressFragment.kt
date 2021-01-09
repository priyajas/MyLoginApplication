package com.example.myapplication.view.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.myapplication.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_address.*


class AddressFragment : Fragment(), OnMapReadyCallback {

    private lateinit var googleMap: GoogleMap
    private var lat=000.0
    private var lng=000.0
    val args: ListFragmentArgs by navArgs()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        map_view.onCreate(savedInstanceState)
        map_view.onResume()
        map_view.getMapAsync(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_address, container, false)
    }

    override fun onMapReady(map: GoogleMap?) {
        map?.let {
            googleMap=map
            val markerm=  LatLng(lat,lng)
            println( "lataddress is $lat and lngaddress is $lng");
            googleMap.addMarker(MarkerOptions().position(markerm).title("User location").icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(markerm, 5F))
            googleMap.animateCamera(CameraUpdateFactory.zoomTo(5F), 2000, null);
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         lat= args.lat.toDouble()
         lng= args.lng.toDouble()
    }



}
