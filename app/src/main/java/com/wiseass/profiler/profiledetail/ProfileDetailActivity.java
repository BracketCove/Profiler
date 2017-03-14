package com.wiseass.profiler.profiledetail;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.wiseass.profiler.R;
import com.wiseass.profiler.util.ActivityUtils;

public class ProfileDetailActivity extends AppCompatActivity {
    private static final String DETAIL_FRAGMENT = "DETAIL_FRAGMENT";

    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_detail);

        manager = this.getFragmentManager();

        ProfileDetailFragment fragment = (ProfileDetailFragment) manager.findFragmentByTag(DETAIL_FRAGMENT);

        if (fragment == null){
            fragment = ProfileDetailFragment.newInstance();
        }

        ActivityUtils.addFragmentToActivity(manager,
                fragment,
                R.id.cont_profile_detail_fragment,
                DETAIL_FRAGMENT
        );

    }
}
