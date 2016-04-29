package com.typicalfoobar.lightswitchmobile.model;

import android.text.Editable;
import android.text.TextWatcher;

public class LightSwitchServerSettings {
    public String protocol;
    public String address;
    public String port;

    private boolean isEdited = false;
    public boolean isEdited() {
        return this.isEdited;
    }

    public TextWatcher protocolTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable s) {
            // Set the new string
            protocol = s.toString();

            // This has been edited
            isEdited = true;
        }
    };

    public TextWatcher addressTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable s) {
            // Set the new string
            address = s.toString();

            // This has been edited
            isEdited = true;
        }
    };

    public TextWatcher portTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable s) {
            // Set the new string
            port = s.toString();

            // This has been edited
            isEdited = true;
        }
    };
}
