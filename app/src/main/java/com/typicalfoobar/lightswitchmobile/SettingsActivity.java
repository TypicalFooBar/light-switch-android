package com.typicalfoobar.lightswitchmobile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.typicalfoobar.lightswitchmobile.model.LightSwitch;
import com.typicalfoobar.lightswitchmobile.uicomponent.LightSwitchEditListView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SettingsActivity extends AppCompatActivity {
    private RequestQueue requestQueue;
    private LightSwitchEditListView lightSwitchEditListView;


    // TODO: Make this a setting
    private String lightSwitchServerBaseUrl = "http://192.168.1.108";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        this.requestQueue = Volley.newRequestQueue(this);

        // Init the view with the editable light switch information
        initLightSwitchEditListView();

        // Init the server settings
        initServerSettings();
    }

    private void initServerSettings() {

    }

    private void initLightSwitchEditListView() {
        // Query the light-switch-server for the list of lights
        StringRequest stringRequest = new StringRequest(Request.Method.GET, this.lightSwitchServerBaseUrl + "/api/light-switch?action=getLightSwitchList",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Create the Gson object
                        Gson gson = new Gson();

                        // Deserialize the JSON response into a list of LightSwitch objects
                        ArrayList<LightSwitch>lightSwitchList = gson.fromJson(response, new TypeToken<ArrayList<LightSwitch>>(){}.getType());

                        // The LightSwitchEditListView element
                        lightSwitchEditListView = (LightSwitchEditListView)findViewById(R.id.lightSwitchEditListView);

                        // Add the light switch list
                        lightSwitchEditListView.setLightSwitchList(lightSwitchList);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );

        // Add this request to the queue
        this.requestQueue.add(stringRequest);
    }

    public void onSaveButtonClick(View view) {
        // Create the Gson object
        Gson gson = new Gson();

        // Get the JSON string for this light switch list
        final String lightSwitchListJson = gson.toJson(this.lightSwitchEditListView.getEditedLightSwitchList(), new TypeToken<ArrayList<LightSwitch>>(){}.getType());

        // Send the new light switch info to the server
        StringRequest stringRequest = new StringRequest(Request.Method.POST, this.lightSwitchServerBaseUrl + "/api/light-switch?action=updateLightSwitch",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        finish();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("lightSwitchList", lightSwitchListJson);

                return params;
            }
        };

        // Add this request to the queue
        this.requestQueue.add(stringRequest);

        for(LightSwitch lightSwitch : this.lightSwitchEditListView.getEditedLightSwitchList()) {

        }
    }
}
