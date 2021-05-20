package com.adobe.aem.guides.orchard.core.services;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component(service = DemoServiceB.class,immediate = true)
public class DemoServiceBImpl implements DemoServiceB {
    private static final Logger LOG= LoggerFactory.getLogger(DemoServiceBImpl.class);

    /*--------Start Tutorial #30--------*/
    @Reference(target = "(component.name=com.adobe.aem.guides.orchard.core.services.MultiServiceBImpl)")
    MultiService multiService;

    public String getNameWithReference(){
        return "Response coming from  "+multiService.getName();
    }
    /*--------End Tutorial #30--------*/
    /*--------Start Tutorial #29--------*/
    @Reference
    DemoService demoService;

    @Override
    public List<String> getPages(){
        List<String> listPages = new ArrayList<String>();
        LOG.info("hey i am from service B");
        try {
            Iterator<Page> pages=demoService.getPages();
            while (pages.hasNext()) {
                listPages.add(pages.next().getTitle());
            }
            
            return listPages;
        } catch (Exception e) {
            LOG.info("\n  Exception {} ",e.getMessage());
        }
        return null;
    }
    /*--------End Tutorial #29--------*/
}