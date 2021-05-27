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
        adapters = Product.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)


public class ProductImpl implements Product{
    private static final Logger LOG = LoggerFactory.getLogger(ProductImpl.class);

    @ValueMapValue
    private String btitle;
    
    @ValueMapValue
    private float bprice;
    
    @ValueMapValue
    private String bdesc;
    
    @ValueMapValue
	private String fileReference;
    
    @ValueMapValue
    private String aname;
    
    @ValueMapValue
    @Default(values = "en")
    private String sellang;

	@Override
	public String getBookTitle() {
		// TODO Auto-generated method stub
		return btitle;
	}

	@Override
	public float getBookPrice() {
		// TODO Auto-generated method stub
		return bprice;
	}

	@Override
	public String getBookDescription() {
		// TODO Auto-generated method stub
		return bdesc;
	}

	@Override
	public String getAuthorName() {
		// TODO Auto-generated method stub
		return aname;
	}

	@Override
	public String getBookLangauge() {
		// TODO Auto-generated method stub
		return sellang;
	}

	public String getFileReference() {
		return fileReference;
	}
    
}