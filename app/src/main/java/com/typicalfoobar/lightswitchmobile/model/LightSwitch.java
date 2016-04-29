package com.typicalfoobar.lightswitchmobile.model;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * Created by derek on 4/20/16.
 */
public class LightSwitch {
    public int id;
    public String name;
    public boolean active;
    public int pinNumber;

    private boolean isEdited = false;
    public boolean isEdited() {
        return this.isEdited;
    }

    public TextWatcher nameTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable s) {
            // Set the new name
            name = s.toString();

            // This has been edited
            isEdited = true;
        }
    };
}
