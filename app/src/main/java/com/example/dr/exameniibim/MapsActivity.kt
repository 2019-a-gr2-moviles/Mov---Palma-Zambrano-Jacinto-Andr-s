package com.example.dr.exameniibim

import android.content.Intent
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
    GoogleMap.OnCameraIdleListener, GoogleMap.OnCameraMoveStartedListener {

    override fun onCameraMoveStarted(p0: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap


        establecerConfiguracionMapa(mMap)
        establecerListenerMovimientoMapa(mMap)
        var posicion:LatLng
        var titulo =""

        for(a in ServicioMapa.todolosHijos) {
            titulo=a.idLibro
            for (i in ServicioMapa.latitudes) {
                Log.i("VERGA1", i.toString())
                for (j in ServicioMapa.longitudes) {
                    Log.i("VERGA2", j.toString())
                    posicion = LatLng(i.toDouble(), j.toDouble())
                    anadirMarcador(posicion, titulo)
                }

            }
        }

        for(key in ServicioMapa.arregloIdHijos){
            Log.i("LAPUTALLAVE",key)
        }



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
    lateinit var ref1: DatabaseReference
    lateinit var ref2: DatabaseReference
    var arregloIdPadres = ArrayList<String?>()
    //var arregloIdHijos = ArrayList<String?>()
    lateinit var datosHijo:Libro








    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        solicitarPermisosLocalización()
        obtenerPadres()



    }

    private fun obtenerPadres() {

        ref = FirebaseDatabase.getInstance().getReference("Autores")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()) {
                    for (autor in p0.children) {
                        arregloIdPadres.add("Libros/"+autor.key)
                    }
                    for(i in arregloIdPadres){
                        Log.i("id del padre",i)
                        ref1 = FirebaseDatabase.getInstance().getReference(i!!)
                        ref1.addValueEventListener(object : ValueEventListener {
                            override fun onCancelled(p1: DatabaseError) {
                                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                            }
                            override fun onDataChange(p1: DataSnapshot) {
                                if (p1.exists()) {
                                    ServicioMapa.arregloIdHijos.clear()

                                    for (libro in p1.children) {
                                        ServicioMapa.arregloIdHijos.add(i + "/" + libro.key)
                                    }

                                    for(llave in ServicioMapa.arregloIdHijos){
                                        Log.i("LlaveHijo",llave)
                                        ref2 = FirebaseDatabase.getInstance().getReference(llave!!)
                                        ref2.addValueEventListener(object : ValueEventListener{
                                            override fun onCancelled(p3: DatabaseError) {
                                                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                                            }

                                            override fun onDataChange(p3: DataSnapshot) {
                                                //ServicioMapa.latitudes.clear()
                                                //ServicioMapa.longitudes.clear()
                                                for(librox in p3.children){
                                                    datosHijo= p3.getValue(Libro::class.java) as Libro

                                                    Log.i("longitudHijo",datosHijo.longitud.toString())
                                                    ServicioMapa.todolosHijos.add(datosHijo)
                                                    ServicioMapa.latitudes.add((datosHijo.latitud))
                                                    ServicioMapa.longitudes.add(datosHijo.longitud)

                                                }

                                            }

                                        })

                                    }


                                }
                            }
                        })

                    }
                }
            }
        })


        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }



    private fun solicitarPermisosLocalización() {
        val permisoFindLocation = ContextCompat.checkSelfPermission(
            this.applicationContext,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        )
        val tienePermiso = permisoFindLocation == PackageManager.PERMISSION_GRANTED
        if (tienePermiso) {
            Log.i("mapa", "Tiene permisos de FINE_LOCATION")
            this.tienePermisoLozalizacion = true
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ), 1
            )
        }
    }

    fun establecerListenerMovimientoMapa(map: GoogleMap) {
        with(map) {
            setOnCameraIdleListener { this@MapsActivity }
            setOnCameraMoveListener { this@MapsActivity }
            setOnCameraMoveListener { this@MapsActivity }

        }
    }

    fun anadirMarcador(latLng: LatLng, title: String) {
        mMap.addMarker(
            MarkerOptions().position(latLng).title(title)
        )
    }

    fun moverCamaraConZoom(latLng: LatLng, zoom: Float = 10F) {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom))

    }

    fun establecerConfiguracionMapa(mapa: GoogleMap) {
        val contexto = this.applicationContext
        with(mapa) {
            val permisoFindLocation = ContextCompat.checkSelfPermission(
                contexto,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )

            val tienePermiso = permisoFindLocation == PackageManager.PERMISSION_GRANTED
            if (tienePermiso) {
                mapa.isMyLocationEnabled = true
            }
            this.uiSettings.isZoomControlsEnabled = true
            uiSettings.isMyLocationButtonEnabled = true

            mMap.setOnMarkerClickListener {marker ->
                var id = marker.title

                for(hijo in ServicioMapa.todolosHijos){
                    if (id == hijo.idLibro){

                        ServicioMapa.libroIntent = hijo.idLibro

                    }
                }

                Log.i("HIJOPUTAMADRE", ServicioMapa.libroIntent)
                mostrarMarker(ServicioMapa.libroIntent)

                false
            }

        }

    }


fun mostrarMarker(idMarker:String){
    val intentMostrar = Intent(this, MostrarLibroActivity::class.java)
    //intentMostrar.putExtra("idLibro", idMarker)
    this.startActivity(intentMostrar)
    this.finish()
}



}


