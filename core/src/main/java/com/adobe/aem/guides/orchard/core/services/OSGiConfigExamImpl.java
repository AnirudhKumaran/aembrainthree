package com.adobe.aem.guides.orchard.core.services;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.*;

@Component(service = OSGiConfigExam.class,immediate = true)
@Designate(ocd = OSGiConfigExamImpl.ServiceConfig.class )
public class OSGiConfigExamImpl implements OSGiConfigExam{

    @ObjectClassDefinition(name="MindTree - Job Recruitment",
            description = "OSGi for mindtree job recruitment")
    public @interface ServiceConfig {

        @AttributeDefinition(
                name = "Candidate Name",
                description = "Enter Candidate Name.",
                type = AttributeType.STRING)
        public String personName() default "Charles Babbage";

        @AttributeDefinition(
                name = "Candidate Interested",
                description = "are you interested with service agreement",
                type = AttributeType.BOOLEAN)
        boolean getCandidateInterested() default false;

        @AttributeDefinition(
                name = "Skills",
                description = "Add your skills",
                type = AttributeType.STRING
        )
        String[] getSkills() default {"python","c"};

        @AttributeDefinition(
                name = "Gender",
                description = "Select Gender.",
                options = {
                        @Option(label = "Male",value = "M"),
                        @Option(label = "Female",value = "F"),
                        @Option(label = "Other",value = "O")
                },
                type = AttributeType.STRING)
        String getGender() default "M";

    }

    private String personName;
    private boolean candidatedInterested;
    private String[] skills;
    private String gender;

    @Activate
    protected void activate(ServiceConfig serviceConfig){
    	personName=serviceConfig.personName();
    	candidatedInterested=serviceConfig.getCandidateInterested();
    	skills=serviceConfig.getSkills();
    	gender=serviceConfig.getGender();
    }

    @Override
    public String getPersonName() {
        return personName;
    }
    
    @Override
    public boolean isCandidatedInterested() {
        return candidatedInterested;
    }
    
    @Override
    public String[] getSkills() {
        return skills;
    }
    
    @Override
    public String getGender() {
        return gender;
    }
}