package com.lbins.FruitsBusiness;

import android.app.Activity;
import android.os.Bundle;
import com.lbins.FruitsBusiness.bean.BaseActivity;

public class MainActivity extends BaseActivity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
}
