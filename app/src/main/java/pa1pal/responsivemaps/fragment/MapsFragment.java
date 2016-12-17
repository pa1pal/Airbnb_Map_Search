package pa1pal.responsivemaps.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import pa1pal.responsivemaps.R;
import pa1pal.responsivemaps.utils.Data;
import static pa1pal.responsivemaps.utils.Data.locations;

/**
 * User: pa1pal
 * Date: 12/16/16
 */

public class MapsFragment extends Fragment implements OnMapReadyCallback {
    final static String ARG_POSITION = "position";

    int mCurrentPosition = -1;

    private GoogleMap mMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflater1 = inflater.inflate(R.layout.map_view, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        return inflater1;
    }

    public void updateArticleView(int position) {
        LatLng ll = locations[position];
        mMap.addMarker(new MarkerOptions().position(ll));
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(ll, 15);
        mMap.animateCamera(cameraUpdate, 1500, null);
        Log.d("Click", "Clicked on position: " + position);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(ARG_POSITION, mCurrentPosition);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng firstPosition = locations[1];
        for (int i = 0; i< Data.locations.length; i++){
            mMap.addMarker(new MarkerOptions().position(locations[i]));
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLng(firstPosition));
        mMap.animateCamera(CameraUpdateFactory.zoomTo( 12.0f ), 400, null);

    }
}