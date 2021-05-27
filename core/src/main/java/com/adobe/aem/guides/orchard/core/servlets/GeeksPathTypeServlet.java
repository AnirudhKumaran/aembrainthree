package com.adobe.aem.guides.orchard.core.servlets;

import com.day.cq.commons.jcr.JcrConstants;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.request.RequestParameter;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import  java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.lang3.time.FastDateFormat;


@Component(service = Servlet.class)
@SlingServletPaths(
        value = {"/bin/pages","/geeks/pages"}
)
public class GeeksPathTypeServlet extends SlingAllMethodsServlet {
    private static final Logger LOG = LoggerFactory.getLogger(GeeksPathTypeServlet.class);
    private static final FastDateFormat DATE_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd");
    @Override
    protected void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse resp) throws ServletException, IOException {
        final ResourceResolver resourceResolver = req.getResourceResolver();
        Page page = resourceResolver.adaptTo(PageManager.class).getPage("/content/orchard/en");
        
        resp.setContentType("text/xml;charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        writer.append("<urlset xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">");
        
        Iterator<Page> childPages = page.listChildren();
        
        while (childPages.hasNext()) {
        	Page childPage = childPages.next();
        	final ValueMap properties = childPage.getProperties();
        	Calendar cal = childPage.getLastModified();
        	writer.append("<url>");
        	writer.append("<loc>").append(childPage.getPath().toString()).append("</loc>");
        	writer.append("<lastmod>").append(DATE_FORMAT.format(cal)).append("</lastmod>");
        	//writer.append("<changefreq>").append(properties.get("changefreq").toString()).append("</changefreq>");
        	//writer.append("<priority>").append(properties.get("priority").toString()).append("</priority>");
        	writer.append("</url>");
        }
        
        
        writer.append("</urlset>");
    }

    @Override
    protected void doPost(SlingHttpServletRequest req, SlingHttpServletResponse resp)
            throws ServletException, IOException {
        try {
            LOG.info("\n ------------------------STARTED POST-------------------------");
            List<RequestParameter> requestParameterList=req.getRequestParameterList();
            for(RequestParameter requestParameter : requestParameterList){
                LOG.info("\n ==PARAMETERS===>  {} : {} ",requestParameter.getName(),requestParameter.getString());
            }
        }catch (Exception e){
            LOG.info("\n ERROR IN REQUEST {} ",e.getMessage());
        }
        resp.getWriter().write("======FORM SUBMITTED========");

    }
}

/*JSONArray pagesArray = new JSONArray();
try {
    Iterator<Page> childPages = page.listChildren();
    while (childPages.hasNext()) {
        Page childPage = childPages.next();
        JSONObject pageObject = new JSONObject();
        pageObject.put(childPage.getTitle(), childPage.getPath().toString());
        pagesArray.put(pageObject);
    }
} catch (JSONException e) {
    LOG.info("\n ERROR {} ", e.getMessage());
}

resp.setContentType("application/json");
resp.getWriter().write(pagesArray.toString());*/