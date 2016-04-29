package com.typicalfoobar.lightswitchmobile.uicomponent;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.typicalfoobar.lightswitchmobile.R;
import com.typicalfoobar.lightswitchmobile.databinding.LightSwitchEditViewBinding;
import com.typicalfoobar.lightswitchmobile.model.LightSwitch;

public class LightSwitchEditView extends LinearLayout {
    // Bind variables
    private LightSwitchEditViewBinding binding;
    private String title;
    private LightSwitch lightSwitch;

    public LightSwitchEditView(Context context) {
        super(context);

        // Inflate the view and set it's data binding
        this.binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.light_switch_edit_view, this, true);
    }

    public void setTitle(String title) {
        // Set the variable
        this.title = title;

        // Set the binding
        this.binding.setTitle(title);
    }

    public void setLightSwitch(LightSwitch lightSwitch) {
        // Set the variable
        this.lightSwitch = lightSwitch;

        // Set the binding
        this.binding.setLightSwitch(lightSwitch);
    }


    public LightSwitch getLightSwitch() {
        return this.lightSwitch;
    }
}
