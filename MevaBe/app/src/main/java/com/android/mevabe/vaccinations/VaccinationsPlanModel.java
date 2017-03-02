package com.android.mevabe.vaccinations;

import com.android.mevabe.model.BaseModel;
import com.android.mevabe.model.ProfileChildModel;

import java.io.Serializable;

/**
 * Created by leducthuy on 3/1/17.
 */
public class VaccinationsPlanModel extends BaseModel implements Serializable {
    private String vaccinName;
    private String vaccinPeriod;
    private String vaccinDes;

    private ProfileChildModel child;

    /**
     * Constructor
     *
     * @param child        ProfileChildModel
     * @param vaccinName   String
     * @param vaccinPeriod String
     * @param vaccinDes    String
     */
    public VaccinationsPlanModel(ProfileChildModel child, String vaccinName, String vaccinPeriod, String vaccinDes) {
        this.child = child;
        this.vaccinName = vaccinName;
        this.vaccinPeriod = vaccinPeriod;
        this.vaccinDes = vaccinDes;
    }

    public String getChildInfo() {
        String childInfo = "";
        if (child != null) {
            childInfo = String.format("%s (%s)", child.getName(), "3T");
        } else {
            childInfo = String.format("%s (%s)", "Linh Ngọc", "3T");
        }

        return childInfo;
    }

    public String getVaccinName() {
        return vaccinName;
    }

    public void setVaccinName(String vaccinName) {
        this.vaccinName = vaccinName;
    }

    public String getVaccinPeriod() {
        return vaccinPeriod;
    }

    public void setVaccinPeriod(String vaccinPeriod) {
        this.vaccinPeriod = vaccinPeriod;
    }

    public String getVaccinDes() {
        return vaccinDes;
    }

    public void setVaccinDes(String vaccinDes) {
        this.vaccinDes = vaccinDes;
    }

    public ProfileChildModel getChild() {
        return child;
    }

    public void setChild(ProfileChildModel child) {
        this.child = child;
    }
}
