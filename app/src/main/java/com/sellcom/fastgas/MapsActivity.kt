package com.sellcom.fastgas

import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.google.android.gms.maps.*

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import android.location.LocationManager
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import android.location.Criteria
import android.os.Build
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_maps.*
import androidx.appcompat.app.ActionBarDrawerToggle





class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        /*setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
        window.statusBarColor = Color.TRANSPARENT*/

        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
            decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        configureNavigationDrawer()
        configureToolbar()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_items, menu)
        return true
    }

    private fun configureToolbar() {
        setSupportActionBar(toolbar)

        setSupportActionBar(toolbar)
        val drawerToggle = ActionBarDrawerToggle(
            this, drawer,
            toolbar, R.string.drawer_open, R.string.drawer_close)

        drawer.setDrawerListener(drawerToggle)

        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)
        //val actionbar = supportActionBar
    }

    private fun configureNavigationDrawer() {
        //drawerLayout = findViewById(R.id.drawer_layout) as DrawerLayout
        //val navView = findViewById(R.id.navigation) as NavigationView
        navigationV.setNavigationItemSelectedListener { false }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.setPadding(0,230,0,0);

        val mapStyleOptions = MapStyleOptions.loadRawResourceStyle(this, R.raw.style_json)
        mMap.setMapStyle(mapStyleOptions)

        val locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
        val criteria = Criteria()

        if ( Build.VERSION.SDK_INT >= 23 &&
            ContextCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return
        }
        val location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false)!!)
        if (location != null) {
            mMap.animateCamera(
                CameraUpdateFactory.newLatLngZoom(
                    LatLng(
                        location.latitude,
                        location.longitude
                    ), 13f
                )
            )

            val cameraPosition = CameraPosition.Builder()
                .target(
                    LatLng(
                        location.latitude,
                        location.longitude
                    )
                )      // Sets the center of the map to location user
                .zoom(17f)                   // Sets the zoom
                .bearing(90f)                // Sets the orientation of the camera to east
                .tilt(40f)                   // Sets the tilt of the camera to 30 degrees
                .build()                   // Creates a CameraPosition from the builder
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
        }
    }

}
