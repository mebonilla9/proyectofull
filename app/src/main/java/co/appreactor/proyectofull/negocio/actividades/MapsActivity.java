package co.appreactor.proyectofull.negocio.actividades;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import co.appreactor.proyectofull.R;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    private GoogleMap mMap;
    private LocationManager localizador;

    // para solicitud de permisos en tiempo de ejecucion
    private final int codigoPermiso = 33;
    private final String[] permisos = new String[]{Manifest.permission.ACCESS_FINE_LOCATION};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        localizador = (LocationManager) getSystemService(LOCATION_SERVICE);
        validarPermisoLocalizacion();
    }

    private void validarPermisoLocalizacion() {
        int permisoConcedido = ContextCompat.checkSelfPermission(
                MapsActivity.this, permisos[0]);
        if (permisoConcedido == PackageManager.PERMISSION_GRANTED) {
            solicitarLocalizacion();
            return;
        }
        ActivityCompat.requestPermissions(MapsActivity.this, permisos, codigoPermiso);
    }

    private void solicitarLocalizacion() {
        try{
            localizador.requestLocationUpdates(LocationManager.GPS_PROVIDER,30000,1,this);
        } catch (SecurityException e){
            e.printStackTrace();
        }
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
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null){
            mMap.clear();
            LatLng ubicacion = new LatLng(location.getLatitude(),location.getLongitude());
            mMap.addMarker(
                    new MarkerOptions()
                    .title("Aqui ando!")
                    .snippet("Mi ubicacion")
                    .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher_round))
                    .position(ubicacion)
            );
            mMap.moveCamera(CameraUpdateFactory.newLatLng(ubicacion));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ubicacion, 15));
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == codigoPermiso &&
                grantResults.length > 0 &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED){
            solicitarLocalizacion();
        }
    }
}
