package com.android.mevabe.common.model;

import com.facebook.Profile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thuyld on 1/20/17.
 */
public class MyProfile implements Serializable {
    private List<ProfileChildModel> children;
    private Profile myPro;

    /**
     * Constructor
     */
    public MyProfile(Profile loginProfile) {
        myPro = loginProfile;
        children = new ArrayList<>();
    }

    /**
     * Find child by child ID
     *
     * @param childID long
     * @return ProfileChildModel
     */
    public ProfileChildModel getChild(long childID) {
        for (ProfileChildModel child : children) {
            if (child.getId() == childID) {
                return child;
            }
        }

        return null;
    }

    public List<ProfileChildModel> getChildren() {
        return children;
    }

    public void setChildren(List<ProfileChildModel> children) {
        this.children = children;
    }

    public com.facebook.Profile getMyPro() {
        return myPro;
    }

    public void setMyPro(com.facebook.Profile myPro) {
        this.myPro = myPro;
    }
}