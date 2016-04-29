package com.typicalfoobar.lightswitchmobile.uicomponent;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.typicalfoobar.lightswitchmobile.R;
import com.typicalfoobar.lightswitchmobile.databinding.SettingsSectionViewBinding;

public class SettingsSectionView extends LinearLayout {
    // Bind variables
    private SettingsSectionViewBinding binding;

    public SettingsSectionView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);

        if (isInEditMode())
        {
            // Insert test data
            inflate(context, R.layout.settings_section_view, this);
            TextView titleTextView = (TextView)findViewById(R.id.titleTextView);
            titleTextView.setText("Section Title");
            return;
        }

        // Inflate the view and set it's data binding
        this.binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.settings_section_view, this, true);
    }

    public void setTitle(String title) {
        // Set the binding
        this.binding.setTitle(title);
    }

    protected LinearLayout getContentView() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout);

        return linearLayout;
    }
}
