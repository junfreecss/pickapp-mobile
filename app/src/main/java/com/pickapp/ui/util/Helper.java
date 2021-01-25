package com.pickapp.ui.util;

import android.view.View;
import android.view.ViewGroup;


public class Helper {

    public static void enableView(View view, boolean enabled) {
        view.setEnabled(enabled);

        if ( view instanceof ViewGroup) {
            ViewGroup group = (ViewGroup)view;

            for ( int idx = 0 ; idx < group.getChildCount() ; idx++ ) {
                enableView(group.getChildAt(idx), enabled);
            }
        }
    }

}
