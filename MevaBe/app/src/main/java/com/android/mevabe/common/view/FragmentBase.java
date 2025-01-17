package com.android.mevabe.common.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.mevabe.common.AppData;
import com.android.mevabe.common.model.MyProfile;

/**
 * Created by thuyld on 12/14/16.
 */
public abstract class FragmentBase extends Fragment {
    protected Bundle savedState;
    protected LayoutInflater mInflater;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mInflater = inflater;
        View layoutView = inflater.inflate(getLayoutContentViewXML(), container, false);
        initView(layoutView);

        return layoutView;
    }

    /**
     * Get facebook profile
     *
     * @return MyProfile
     */
    public MyProfile getMyProfile() {
        return AppData.getMyProfile();
    }


    //##############################################################################################
    //____________________________________  abstract methods
    //##############################################################################################

    /**
     * Get resource layout ID for view of fragment
     *
     * @return int
     */
    public abstract int getLayoutContentViewXML();


    /**
     * Init view ot fragment
     *
     * @param layoutView View
     */
    public abstract void initView(View layoutView);


    //##############################################################################################
    //____________________________________  Override optional methods
    //##############################################################################################

    /**
     * Do something when toolbar has clicked. Need to override
     *
     * @param v View
     */
    public void onToolBarClicked(View v) {

    }
}

