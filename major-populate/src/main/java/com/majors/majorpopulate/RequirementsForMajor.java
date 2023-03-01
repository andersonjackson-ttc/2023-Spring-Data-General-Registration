package com.majors.majorpopulate;

import java.util.ArrayList;
import java.util.List;

public class RequirementsForMajor
{
    public RequirementsForMajor()
    {
        majorRequirement = new ArrayList<>();
        majorElectives = new ArrayList<>();
    }
    private  List<MajorRequirements> majorRequirement;
    public List<MajorRequirements> getMajorRequirement() {
        return majorRequirement;
    }

    public void setMajorRequirement(List<MajorRequirements> majorRequirement) {
        this.majorRequirement = majorRequirement;
    }


    private  List<MajorElectives> majorElectives ;
    public List<MajorElectives> getMajorElectives() {
        return majorElectives;
    }

    public void setMajorElectives(List<MajorElectives> majorElectives) {
        this.majorElectives = majorElectives;
    }


}
