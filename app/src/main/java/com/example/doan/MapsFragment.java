package com.example.doan;

import android.content.pm.PackageManager;
import android.health.connect.datatypes.ExerciseRoute;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

public class MapsFragment extends Fragment {
    private final int FINE_PERMISSION_CODE = 1;
    private GoogleMap myMap;
    Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
//            LatLng sydney = new LatLng(-177, 543);
//            googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//            googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity());
            myMap = googleMap;
            if (currentLocation != null) {
                LatLng myLatLng = new LatLng(10.8546964, 106.7854153);
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLatLng, 15));
                googleMap.addMarker(new MarkerOptions().position(myLatLng).title("Vị trí của tôi"));
                LatLng myLatLng1 = new LatLng(10.83144, 106.73213);
                googleMap.addMarker(new MarkerOptions().position(myLatLng).title("Bãi xe Hiệp Bình Chánh"));
                LatLng myLatLng2 = new LatLng(10.83245, 106.73200);
                googleMap.addMarker(new MarkerOptions().position(myLatLng).title("Bãi xe Hiệp Bình Chánh"));
                LatLng myLatLng3 = new LatLng(10.83317, 106.73288);
                googleMap.addMarker(new MarkerOptions().position(myLatLng).title("Bãi xe Hiệp Bình Chánh"));
                LatLng myLatLng4 = new LatLng(10.83274, 106.73157);
                googleMap.addMarker(new MarkerOptions().position(myLatLng).title("Bãi xe Hiệp Bình Chánh"));
                LatLng myLatLng5 = new LatLng(10.83357, 106.73279);
                googleMap.addMarker(new MarkerOptions().position(myLatLng).title("Bãi xe Hiệp Bình Chánh"));

            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Lấy vị trí hiện tại
//        getLastLocation();

        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
//    private void getLastLocation() {
//        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, FINE_PERMISSION_CODE);
//            return;
//        }
//        fusedLocationProviderClient.getLastLocation()
//                .addOnSuccessListener(new OnSuccessListener<Location>() {
//                    @Override
//                    public void onSuccess(Location location) {
//                        if (location != null) {
//                            currentLocation = location;
//                        }
//                    }
//                });
//    }

}