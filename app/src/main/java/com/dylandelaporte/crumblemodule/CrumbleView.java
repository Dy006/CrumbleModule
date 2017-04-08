package com.dylandelaporte.crumblemodule;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by Dylan on 08/04/2017.
 */

public class CrumbleView extends LinearLayout {

    private LinearLayout linearLayout;
    private Boolean showArrows;

    public CrumbleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.CrumbleView, 0, 0);

        try {
            showArrows = typedArray.getBoolean(R.styleable.CrumbleView_showArrows, true);
        } finally {
            typedArray.recycle();
        }

        Log.d(TAG, "BreadcrumbView: test");

        ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.crumble_layout, this);

        linearLayout = (LinearLayout) ((HorizontalScrollView)getChildAt(0)).getChildAt(0);
    }

    private static ArrayList<View> getViewsByTag(ViewGroup root, String tag){
        ArrayList<View> views = new ArrayList<View>();
        final int childCount = root.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = root.getChildAt(i);
            if (child instanceof ViewGroup) {
                views.addAll(getViewsByTag((ViewGroup) child, tag));
            }

            final Object tagObj = child.getTag();
            if (tagObj != null && tagObj.equals(tag)) {
                views.add(child);
            }

        }
        return views;
    }

    public void addElement(String name) {
        Button button = new Button(getContext());
        //button.setTextAppearance(getContext(), android.R.style.Widget_Holo_Button_Borderless);
        button.setText(name);

        this.linearLayout.addView(button);
    }

    public Boolean isShowArrows() {
        return showArrows;
    }
}
