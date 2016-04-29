package com.typicalfoobar.lightswitchmobile.uicomponent;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LightSwitchServerSettingsView extends SettingsSectionView {
    public LightSwitchServerSettingsView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);

        if (isInEditMode())
        {
            return;
        }

        setTitle("Light Switch Server Settings");

        initFromDatabase();
    }

    private void initFromDatabase() {
        // Get the LinearLayout that we'll be adding the views to
        LinearLayout contentView = this.getContentView();

        // Clear the layout first
        contentView.removeAllViews();

        TextView textView = new TextView(getContext());
        textView.setText("TESTING");

        contentView.addView(textView);
    }
}
