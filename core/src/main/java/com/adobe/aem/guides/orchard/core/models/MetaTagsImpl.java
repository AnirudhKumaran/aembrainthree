package com.adobe.aem.guides.orchard.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.*;
import org.apache.sling.models.annotations.injectorspecific.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

import javax.inject.Inject;
import java.util.HashMap;
import javax.jcr.Node;
import javax.jcr.NodeIterator;

import java.util.*;

@Model(adaptables = SlingHttpServletRequest.class,
        adapters = MetaTags.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)


public class MetaTagsImpl implements MetaTags{
    private static final Logger LOG = LoggerFactory.getLogger(MetaTagsImpl.class);
    
    private HashMap<String , String> metaOverride =new HashMap<String ,String>() ;
    private HashMap<String , String> newMeta =new HashMap<String ,String>() ;

	@Override
	public HashMap<String, String> getMetaOverride() {
		// TODO Auto-generated method stub
		return metaOverride;
	}

	@Override
	public HashMap<String, String> getNewMeta() {
		// TODO Auto-generated method stub
		return newMeta;
	}
    
}