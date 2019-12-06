package com.sellcom.fastgas

import android.app.Activity
import android.os.Bundle
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.MapView

class MainActivity : Activity(), OnMapReadyCallback {

    private lateinit var mapView: MapView
    private lateinit var gmap: GoogleMap

    private val MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var mapViewBundle: Bundle? = null
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY)
        }

        mapView = findViewById(R.id.mapView)
        mapView.onCreate(mapViewBundle)
        mapView.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        gmap = googleMap!!
        gmap.setMinZoomPreference(12F)
        val ny = LatLng(40.7143528, -74.0059731)
        gmap.moveCamera(CameraUpdateFactory.newLatLng(ny))
    }

}
