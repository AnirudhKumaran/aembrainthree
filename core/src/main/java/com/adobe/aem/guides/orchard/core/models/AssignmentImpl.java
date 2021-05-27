package com.adobe.aem.guides.orchard.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.*;
import org.apache.sling.models.annotations.injectorspecific.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.*;

@Model(adaptables = SlingHttpServletRequest.class,
        adapters = Assignment.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)


public class AssignmentImpl implements Assignment{
    private static final Logger LOG = LoggerFactory.getLogger(AssignmentImpl.class);
    
    @Inject
    @Via("resource")
    private boolean coding;


    @ValueMapValue
    private List<String> language;

    @ValueMapValue
    @Default(values = "c")
    private String selitem;

    @Override
    public List<String> getLanguages() {
        if (language != null) {
            return new ArrayList<String>(language);
        } else {
            return Collections.emptyList();
        }
    }

	@Override
	public boolean getLoveCoding() {
		// TODO Auto-generated method stub
		return coding;
	}

	@Override
	public String getSelectedLanguage() {
		// TODO Auto-generated method stub
		return selitem;
	}

    
}