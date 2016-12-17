package pa1pal.responsivemaps.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pa1pal.responsivemaps.R;
import pa1pal.responsivemaps.adapter.PlacesAdapter;
import pa1pal.responsivemaps.utils.RecyclerItemClickListner;

/**
 * User: pa1pal
 * Date: 12/16/16
 */

public class PlacesFragment extends Fragment implements RecyclerItemClickListner.OnItemClickListener{

    OnPlaceSelectedListener mCallback;

    RecyclerView mRecyclerView;

    private PlacesAdapter placesAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        placesAdapter = new PlacesAdapter();
    }

    @Override
    public void onItemClick(View childView, int position) {
        mCallback.onPlaceSelected(position);
    }

    @Override
    public void onItemLongPress(View childView, int position) {

    }

    public interface OnPlaceSelectedListener {
        void onPlaceSelected(int position);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.places_view, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListner(getActivity(), this));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        mRecyclerView.setAdapter(placesAdapter);
        return view;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        try {
            mCallback = (OnPlaceSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString());
        }
    }
}