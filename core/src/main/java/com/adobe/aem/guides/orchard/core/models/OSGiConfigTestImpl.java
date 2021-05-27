package com.adobe.aem.guides.orchard.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import com.adobe.aem.guides.orchard.core.services.OSGiConfigExam;

import java.util.List;

@Model(adaptables = SlingHttpServletRequest.class,
        adapters = OSGiConfigTest.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class OSGiConfigTestImpl implements OSGiConfigTest{


    /*--------Start Tutorial #31--------*/
    @OSGiService
    OSGiConfigExam oSGiConfig;

    @Override
    public String getCandidateName() {
        return oSGiConfig.getPersonName();
    }

    @Override
    public boolean isCandidateInterested() {
        return oSGiConfig.isCandidatedInterested();
    }

    @Override
    public String[] getSkills() {
        return oSGiConfig.getSkills();
    }

    @Override
    public String getGender() {
        return oSGiConfig.getGender();
    }

}
