package pa1pal.responsivemaps.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import pa1pal.responsivemaps.R;
import pa1pal.responsivemaps.fragment.MapsFragment;
import pa1pal.responsivemaps.fragment.PlacesFragment;

/**
 * User: pa1pal
 * Date: 12/16/16
 */

public class MainActivity extends FragmentActivity
        implements PlacesFragment.OnPlaceSelectedListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PlacesFragment firstFragment = new PlacesFragment();
        MapsFragment articleFragment = new MapsFragment();

        firstFragment.setArguments(getIntent().getExtras());

        getSupportFragmentManager().beginTransaction()
                .add(R.id.places_fragment, firstFragment).addToBackStack(null).commit();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.maps_fragment, articleFragment).addToBackStack(null).commit();

    }

    public void onPlaceSelected(int position) {
        MapsFragment articleFrag = (MapsFragment)
                getSupportFragmentManager().findFragmentById(R.id.maps_fragment);
        articleFrag.updateArticleView(position);
    }
}