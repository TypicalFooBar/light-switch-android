package com.typicalfoobar.lightswitchmobile.uicomponent;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.typicalfoobar.lightswitchmobile.R;
import com.typicalfoobar.lightswitchmobile.model.LightSwitch;

public class LightSwitchImageButton extends LinearLayout {
    private ImageView imageView;
    private TextView textView;
    private LightSwitch lightSwitch;

    public LightSwitchImageButton(final Context context, final LightSwitch lightSwitch) {
        super(context);

        this.lightSwitch = lightSwitch;

        inflate(getContext(), R.layout.light_switch_image_button, this);
        this.imageView = (ImageView)findViewById(R.id.lightSwitchImageButtonImageView);
        this.textView = (TextView)findViewById(R.id.lightSwitchImageButtonTextView);

        // Set the image
        setImageResource();

        // Show the name of the light switch
        textView.setText(lightSwitch.name);

        // Set the onClickListener
        this.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Flip the active switch
                lightSwitch.active = !lightSwitch.active;

                // Send this update for this light switch to the server
                updateLightSwitchOnServer();

                // Set the image
                setImageResource();
            }
        });
    }

    public void setImageResource() {
        // If the light switch is active
        if (lightSwitch.active) {
            // Use the light on image
            setImageResource(R.drawable.light_on_96);
        }
        else { // Else, the light switch is not active
            // Use the light off image
            setImageResource(R.drawable.light_off_96);
        }
    }

    public void setImageResource(int drawable) {
        this.imageView.setImageResource(drawable);
    }

    private void updateLightSwitchOnServer() {
        Gson gson = new Gson();

        String jsonString = gson.toJson(lightSwitch);
        String jsonStringUrlEncoded = "";

        // TODO: Make this a setting
        String lightSwitchServerBaseUrl = "http://192.168.1.108";

        try {
            jsonStringUrlEncoded = java.net.URLEncoder.encode(jsonString, "UTF-8");
        }
        catch (Exception e) {

        }
        // Update this light switch on the server
        RequestQueue queue = Volley.newRequestQueue(this.getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, lightSwitchServerBaseUrl + "/api/light-switch?action=updateLightSwitch&lightSwitch=" + jsonStringUrlEncoded,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

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
}
