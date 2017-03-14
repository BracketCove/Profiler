package com.wiseass.profiler.createaccount;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wiseass.profiler.R;
import com.wiseass.profiler.login.LoginFragment;
import com.wiseass.profiler.util.ActivityUtils;

public class CreateAccountActivity extends AppCompatActivity {
    private static final String CREATE_FRAGMENT = "CREATE_FRAGMENT";

    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        manager = this.getFragmentManager();

        //set up fragment
        CreateAccountFragment fragment = (CreateAccountFragment)
                manager.findFragmentByTag(CREATE_FRAGMENT);

        if (fragment == null){
            fragment = CreateAccountFragment.newInstance();
        }

        ActivityUtils.addFragmentToActivity(manager,
                fragment,
                R.id.cont_create_fragment,
                CREATE_FRAGMENT
        );

    }
}
