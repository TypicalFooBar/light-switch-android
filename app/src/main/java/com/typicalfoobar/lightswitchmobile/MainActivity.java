package com.typicalfoobar.lightswitchmobile;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.typicalfoobar.lightswitchmobile.model.LightSwitch;
import com.typicalfoobar.lightswitchmobile.uicomponent.LightSwitchImageButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        refreshLightSwitchList();
    }

    public void refreshLightSwitchList() {
        // The main scroll view linear layout
        final LinearLayout mainScrollViewLinearLayout = (LinearLayout)findViewById(R.id.mainScrollViewLinearLayout);

        // Clear the current view
        mainScrollViewLinearLayout.removeAllViews();

        // TODO: Make this a setting
        String lightSwitchServerBaseUrl = "http://192.168.1.108";

        final Context context = this;

        // Query the light-switch-server for the list of lights
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, lightSwitchServerBaseUrl + "/api/light-switch?action=getLightSwitchList",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Create the Gson object
                        Gson gson = new Gson();

                        // Deserialize the JSON response into a list of LightSwitch objects
                        ArrayList<LightSwitch> lightSwitchList = gson.fromJson(response, new TypeToken<ArrayList<LightSwitch>>(){}.getType());

                        // For each LightSwitch
                        for (LightSwitch lightSwitch : lightSwitchList) {
                            LightSwitchImageButton lightSwitchImageButton = new LightSwitchImageButton(context, lightSwitch);

                            mainScrollViewLinearLayout.addView(lightSwitchImageButton);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        queue.add(stringRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
        else if (id == R.id.action_refresh) {
            refreshLightSwitchList();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
