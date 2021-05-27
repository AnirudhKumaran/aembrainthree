package com.adobe.aem.guides.orchard.core.models;

import java.util.List;

import com.adobe.aem.guides.orchard.core.services.OSGiFactoryConfig;

public interface OSGiConfigTest {
    public String getCandidateName();
    public boolean isCandidateInterested();
    public String[] getSkills() ;
    public String getGender();
}