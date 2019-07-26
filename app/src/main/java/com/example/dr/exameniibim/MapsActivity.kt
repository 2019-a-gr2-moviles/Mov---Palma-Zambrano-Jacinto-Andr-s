package com.example.dr.exameniibim

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
import com.google.firebase.database.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnCameraMoveListener,
    GoogleMap.OnCameraIdleListener, GoogleMap.OnCameraMoveStartedListener{

    override fun onCameraMoveStarted(p0: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        establecerConfiguracionMapa(mMap)
        establecerListenerMovimientoMapa(mMap)

    }

    override fun onCameraMove() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCameraIdle() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private lateinit var mMap: GoogleMap
    private var tienePermisoLozalizacion = false
    lateinit var ref: DatabaseReference
    lateinit var refHijos : DatabaseReference
    lateinit var listaAutores: MutableList<Autor>
    lateinit var listaHijos : MutableList<Libro>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        solicitarPermisosLocalización()

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        obtenerPadres()
        obtenerHijos(listaAutores)

    }



    private fun solicitarPermisosLocalización() {
        val permisoFindLocation = ContextCompat.checkSelfPermission(
            this.applicationContext,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        )
        val tienePermiso = permisoFindLocation == PackageManager.PERMISSION_GRANTED
        if(tienePermiso){
            Log.i("mapa","Tiene permisos de FINE_LOCATION")
            this.tienePermisoLozalizacion=true
        }else{
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ),1
            )
        }
    }

    fun establecerListenerMovimientoMapa(map:GoogleMap){
        with(map){
            setOnCameraIdleListener { this@MapsActivity }
            setOnCameraMoveListener { this@MapsActivity }
            setOnCameraMoveListener { this@MapsActivity }

        }
    }

    fun anadirMarcador(latLng: LatLng,title:String){
        mMap.addMarker(
            MarkerOptions().position(latLng).title(title)
        )
    }

    fun moverCamaraConZoom(latLng: LatLng, zoom : Float=10F){
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,zoom))

    }

    fun establecerConfiguracionMapa(mapa:GoogleMap){
        val contexto = this.applicationContext
        with(mapa){
            val permisoFindLocation = ContextCompat.checkSelfPermission(
                contexto,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )

            val tienePermiso = permisoFindLocation == PackageManager.PERMISSION_GRANTED
            if(tienePermiso){
                mapa.isMyLocationEnabled=true
            }
            this.uiSettings.isZoomControlsEnabled=true
            uiSettings.isMyLocationButtonEnabled=true

            //SETONMARKERCLICKLISTENER

        }
    }

    fun obtenerPadres(){

        listaAutores = mutableListOf()
        ref = FirebaseDatabase.getInstance().getReference("Autores")
        ref.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()) {
                    listaAutores.clear()
                    for (autor in p0.children) {
                        val aut = autor.getValue(Autor::class.java)
                        listaAutores.add(aut!!)
                    }

                    for(autorx in listaAutores){
                        Log.i("autor", autorx.nombres)
                    }


                }
            }

        })


    }

    private fun obtenerHijos(listaAutores : MutableList<Autor>) {

        for(autox in listaAutores){

        }
        ref = FirebaseDatabase.getInstance().getReference("Libros").child(id)
        ref.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {

                if(p0.exists()){
                    listaHijos.clear()
                    for(libro in p0.children){
                        val lib = libro.getValue(Libro::class.java)
                        listaHijos.add(lib!!)
                    }

    }

}


