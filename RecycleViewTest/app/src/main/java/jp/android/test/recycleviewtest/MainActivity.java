package jp.android.test.recycleviewtest;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getFragmentManager();
            // Or: FragmentManager fragmentManager = getSupportFragmentManager()
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            RecycleViewFragment fragment = new RecycleViewFragment();
            fragmentTransaction.add(R.id.sample_content_fragment, fragment);
            fragmentTransaction.commit();
        }
    }
}
