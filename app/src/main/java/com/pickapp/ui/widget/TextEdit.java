package com.pickapp.ui.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

import androidx.core.content.res.ResourcesCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.pickapp.R;

public class TextEdit extends TextInputEditText {
    public TextEdit(Context context) {
        super(context);
        init();
    }

    public TextEdit(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TextEdit(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        this.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.et_line, null));
        EditText editText = this;

        this.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setTextError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void setTextError(CharSequence error) {
        this.setBackground(error == null ? ResourcesCompat.getDrawable(getResources(), R.drawable.et_line, null)
                : ResourcesCompat.getDrawable(getResources(), R.drawable.et_line_danger, null));
    }

    @Override
    public void setError(CharSequence error) {
        setTextError(error);
    }

    @Override
    public void setError(CharSequence error, Drawable icon) {
        setTextError(error);
    }
}
