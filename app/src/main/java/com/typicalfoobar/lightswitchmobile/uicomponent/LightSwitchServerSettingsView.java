package com.typicalfoobar.lightswitchmobile.uicomponent;

import android.content.Context;
import android.util.AttributeSet;

public class LightSwitchServerSettingsView extends SettingsSectionView {
    public LightSwitchServerSettingsView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);

        if (isInEditMode())
        {
            return;
        }

        setTitle("Light Switch Server Settings");
    }
}
