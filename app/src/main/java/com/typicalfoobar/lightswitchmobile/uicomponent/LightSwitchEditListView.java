package com.typicalfoobar.lightswitchmobile.uicomponent;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.typicalfoobar.lightswitchmobile.R;
import com.typicalfoobar.lightswitchmobile.model.LightSwitch;

import java.util.ArrayList;
import java.util.List;

public class LightSwitchEditListView extends SettingsSectionView {
    private ArrayList<LightSwitchEditView> lightSwitchEditViews = new ArrayList<>();

    public LightSwitchEditListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);

        if (isInEditMode())
        {
            return;
        }

        setTitle("Outlets");
    }

    public void setLightSwitchList(List<LightSwitch> lightSwitchList) {
        // Get the LinearLayout that we'll be adding the views to
        LinearLayout lightSwitchNamesLinearLayout = (LinearLayout) findViewById(R.id.linearLayout);

        // Clear the layout first
        lightSwitchNamesLinearLayout.removeAllViews();

        // Loop through each light switch
        for (int i = 0; i < lightSwitchList.size(); ++i) {
            String title = "Outlet " + (i + 1) + ": ";
            LightSwitchEditView lightSwitchEditView = new LightSwitchEditView(getContext());
            lightSwitchEditView.setTitle(title);
            lightSwitchEditView.setLightSwitch(lightSwitchList.get(i));

            lightSwitchNamesLinearLayout.addView(lightSwitchEditView);
            this.lightSwitchEditViews.add(lightSwitchEditView);
        }
    }

    public List<LightSwitch> getEditedLightSwitchList() {
        ArrayList<LightSwitch> lightSwitchList = new ArrayList<>();

        for (LightSwitchEditView lightSwitchEditView : this.lightSwitchEditViews) {
            LightSwitch lightSwitch = lightSwitchEditView.getLightSwitch();
            if (lightSwitch.isEdited()) {
                lightSwitchList.add(lightSwitch);
            }
        }

        return lightSwitchList;
    }
}
