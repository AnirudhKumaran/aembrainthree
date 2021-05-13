package com.adobe.aem.guides.orchard.core.services;

import com.adobe.aem.guides.orchard.core.utils.ResolverUtil;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.google.common.io.CharStreams;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.dam.api.Asset;
import com.day.cq.dam.api.AssetManager;
import com.day.cq.dam.api.Rendition;
import org.osgi.service.component.annotations.Reference;

//import org.apache.poi.xslf.usermodel.XMLSlideShow;
//import org.apache.poi.POIXMLProperties.*;
//import org.apache.poi.xslf.usermodel.*;


import java.nio.charset.StandardCharsets;


import org.apache.commons.io.IOUtils;

import org.apache.sling.api.resource.LoginException;

import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.jcr.api.SlingRepository;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.*;

@Component(service = DemoService.class,immediate = true)
public class DemoServiceAImpl implements DemoService {
    private static final Logger LOG= LoggerFactory.getLogger(DemoServiceAImpl.class);

    @Reference
    private ResourceResolverFactory resourceResolverFactory;

    @Reference
    private SlingRepository slingRepository;

    @Activate
    public void activate(ComponentContext componentContext){
        LOG.info("\n ==============INSIDE ACTIVATE================");
        LOG.info("\n {} = {} ",componentContext.getBundleContext().getBundle().getBundleId(),componentContext.getBundleContext().getBundle().getSymbolicName());
    }

    @Deactivate
    public void deactivate(ComponentContext componentContext){
        LOG.info("\n ==============INSIDE DEACTIVATE================");
    }

    @Modified
    public void modified(ComponentContext componentContext){
        LOG.info("\n ==============INSIDE MODIFIED================");
    }

    @Override
    public Iterator<Page>  getPages(){
        try {
            ResourceResolver resourceResolver= ResolverUtil.newResolver(resourceResolverFactory);
            PageManager pageManager=resourceResolver.adaptTo(PageManager.class);
            Page page=pageManager.getPage("/content/orchard/en");
            Iterator<Page> pages=page.listChildren();
            return pages;
        } catch (LoginException e) {
            LOG.info("\n Login Exception {} ",e.getMessage());
        }
        return null;
    }

	@Override
	public List<Map<String,String>> getReadCSV() {
		// TODO Auto-generated method stub
		LOG.info("\n File reading started");
		List<Map<String, String>> bookDetailsMap=new ArrayList<>();
		ResourceResolver resourceResolver;
		try {
			resourceResolver = ResolverUtil.newResolver(resourceResolverFactory);
			Resource res = resourceResolver.getResource("/content/dam/orchard/credentials.csv");
		    Asset asset = res.adaptTo(Asset.class);
		    Rendition rendition = asset.getOriginal();		    
		    InputStream inputStream = rendition.adaptTo(InputStream.class);
		    String inputString = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
		    String[] arrOfStr = inputString.split("\n");
	        for(int i=1;i<arrOfStr.length;i++){
	        	String[] indValues = arrOfStr[i].split(",");
	        	Map<String,String> bookMap=new HashMap<>();
	        	bookMap.put("service",indValues[0]);
	        	bookMap.put("name",indValues[1]);
	        	bookMap.put("username",indValues[2]);
	        	bookMap.put("password",indValues[3]);
	        	bookMap.put("hostname",indValues[4]);
	        	bookMap.put("port",indValues[5]);
	        	bookMap.put("security",indValues[6]);
	        }
		    LOG.info("\n File reading finished");
		} catch (LoginException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bookDetailsMap;
	}

	@Override
	public String getStrCSV() {
		// TODO Auto-generated method stub
		ResourceResolver resourceResolver;
		String str = "";
		try {
			resourceResolver = ResolverUtil.newResolver(resourceResolverFactory);
			Resource res = resourceResolver.getResource("/content/dam/orchard/credentials.csv");
		    Asset asset = res.adaptTo(Asset.class);
		    Rendition rendition = asset.getOriginal();		    
		    InputStream inputStream = rendition.adaptTo(InputStream.class);
		    String inputString = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
		    str = inputString;
		    LOG.info("\n File reading finished");
		} catch (LoginException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
}