package com.morihacky.android.icobutton.sample;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.morihacky.android.icobutton.IcoButton;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class DemoActivity
    extends ActionBarActivity {

    boolean toggleAIndicator = false;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.demo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.toggle)
    public void onToggle(IcoButton btn) {
        if (toggleAIndicator) {
            btn.setText("Toggle A");
            btn.setIcon(getResources().getDrawable(android.R.drawable.checkbox_on_background));
        } else {
            btn.setText("Toggle B");
            btn.setIcon(getResources().getDrawable(android.R.drawable.checkbox_off_background));
        }
        toggleAIndicator = !toggleAIndicator;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        ButterKnife.inject(this);
    }
}
