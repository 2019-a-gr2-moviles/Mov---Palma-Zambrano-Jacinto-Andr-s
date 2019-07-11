package com.example.parcelable

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private var tienePermisosLocalizacion=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        solicitarPermisosLocalizacion()

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        establecerConfiguracionMapa(mMap)
        val sydney = LatLng(-1.047637, -80.460384)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Portoviejo"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,17f))
    }
    @SuppressLint("MissingPermission")
    fun establecerConfiguracionMapa(mapa:GoogleMap) {
        val contexto = this.applicationContext
        with(mapa) {
            val permisoFindLocation = ContextCompat.checkSelfPermission(
                this.applicationContext,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
            val tienePermiso = permisoFindLocation == PackageManager.PERMISSION_GRANTED
            if (tienePermiso) {

            }

            this.uiSettings.isZoomControlsEnabled = true
            mapa.isMyLocationEnabled = true
        }
    }
    }
    fun solicitarPermisosLocalizacion(){
        val permisoFindLocation = ContextCompat.checkSelfPermission(
            this.applicationContext,
            android.Manifest.permission.ACCESS_FINE_LOCATION
            )
        val tienePermiso=permisoFindLocation==PackageManager.PERMISSION_GRANTED
        if (tienePermiso){
            Log.i("mapa","Tiene Permisos de FINE_LOCATION")
            this.tienePermisosLocalizacion=true
        }else{
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ),
                1
            )
        }
    }
}
