package com.javad_mozaffari.grammar;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.util.TypedValue;
import android.widget.TextView;

public class InfoPage extends AppCompatActivity {

    JustifiedTextView txtJustify;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_page);

        txtJustify=findViewById(R.id.txtJustify);
        txtJustify.setText(getString(R.string.about_me));
        txtJustify.setTextSize(TypedValue.COMPLEX_UNIT_SP,21);
        txtJustify.setTextColor(getColor(R.color.dark_blue));

    }
}