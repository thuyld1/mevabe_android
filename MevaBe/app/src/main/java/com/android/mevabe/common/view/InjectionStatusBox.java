package com.android.mevabe.common.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.mevabe.R;
import com.android.mevabe.common.Constants;

import java.util.Calendar;

/**
 * Created by leducthuy on 3/16/17.
 */
public class InjectionStatusBox extends FrameLayout {
    private ImageView statusThumb;
    private TextView inDayCountDown;
    private TextView inTitleCountDown;

    public InjectionStatusBox(Context context) {
        super(context);

        // Build GUI for view
        buildGUI();
    }

    public InjectionStatusBox(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InjectionStatusBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public InjectionStatusBox(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * Build GUI for view
     */
    private void buildGUI() {
        // Load layer for view
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View layout = inflater.inflate(R.layout.view_injection_status_box, this, true);
        addView(layout);

        // Bind view
        statusThumb = (ImageView) layout.findViewById(R.id.status_thumb);
        inDayCountDown = (TextView) layout.findViewById(R.id.in_day_countdown);
        inTitleCountDown = (TextView) layout.findViewById(R.id.in_title_countdown);

    }

    /**
     * Bind view for data
     *
     * @param status int
     * @param date   long
     */
    public void bindData(int status, long date) {
        // Case status is OK
        if (status == Constants.VACCINE_INJECTION_DATE_OK) {
            statusThumb.setImageResource(R.drawable.vaccinations_history_ok);
            statusThumb.setVisibility(View.VISIBLE);
            inDayCountDown.setVisibility(View.GONE);
            inTitleCountDown.setVisibility(View.GONE);
        } else if (date > 0) {
            // Base on injection date to show status
            Calendar current = Calendar.getInstance();
            Calendar inDate = Calendar.getInstance();
            inDate.setTimeInMillis(date);

            // Case current is over injection date
            if (current.after(inDate)) {
                statusThumb.setImageResource(R.drawable.vaccinations_history_over);
                statusThumb.setVisibility(View.VISIBLE);
                inDayCountDown.setVisibility(View.GONE);
                inTitleCountDown.setVisibility(View.GONE);
            } else {
                statusThumb.setVisibility(View.GONE);
                inDayCountDown.setVisibility(View.VISIBLE);
                inTitleCountDown.setVisibility(View.VISIBLE);

                // Calculate day count down
                long countDown = inDate.getTimeInMillis() - current.getTimeInMillis();
                inDayCountDown.setText("" + (countDown / 24 * 60 * 60 * 1000));
            }

        } else {
            statusThumb.setImageResource(R.drawable.vaccinations_history_na);
            statusThumb.setVisibility(View.VISIBLE);
            inDayCountDown.setVisibility(View.GONE);
            inTitleCountDown.setVisibility(View.GONE);
        }
    }
}
