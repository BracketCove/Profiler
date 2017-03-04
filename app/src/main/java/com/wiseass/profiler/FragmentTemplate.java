package com.wiseass.profiler;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Ryan on 01/03/2017.
 */

public class FragmentTemplate extends Fragment implements Contract.View {
    //private Contract.Presenter presenter;

    public FragmentTemplate() {
        // Required empty public constructor
    }

    public static FragmentTemplate newInstance(){
        FragmentTemplate fragment = new FragmentTemplate();
        return fragment;

    }


    @Override
    public void setPresenter(Contract.Presenter presenter){
        this.presenter = presenter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //This is important. It helps our View/Presenter/Service survive orientation changes
        this.setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment, container, false);

        //Where we assign our Views
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        //Where we create our presenter if necessary
        if (presenter == null) {
            presenter = new Presenter();
        }

        presenter.subscribe();

    }

    @Override
    public void onDestroy(){
        presenter.unsubscribe();
        super.onDestroy();
    }
}