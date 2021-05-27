package com.adobe.aem.guides.orchard.core.servlets;

import com.day.cq.commons.jcr.JcrConstants;
import com.day.cq.dam.api.Asset;
import com.day.cq.dam.api.Rendition;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.day.cq.wcm.api.WCMException;

import org.apache.commons.io.IOUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.request.RequestParameter;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


@Component(service = Servlet.class)
@SlingServletPaths(
        value = {"/bin/createpages","/geeks/pages"}
)
public class AnirudhPTS extends SlingAllMethodsServlet {
    private static final Logger LOG = LoggerFactory.getLogger(AnirudhPTS.class);

    @Override
    protected void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse resp) throws ServletException, IOException {

    	resp.setContentType("text/plain");
    	resp.getWriter().write("Servlet Started\n");
        final ResourceResolver resourceResolver = req.getResourceResolver();
        PageManager pageManager = resourceResolver.adaptTo(PageManager.class);
        
        String filename = "";
        filename = req.getParameter("filename");
        filename+=".csv";
        
        if(filename==null || filename.equals("")) {
        	resp.getWriter().write("filename should be provided in the url");
        	return;
        }
        
        String path = "/content/dam/orchard/"+filename;
        
        Resource res = resourceResolver.getResource(path);
	    Asset asset = res.adaptTo(Asset.class);
	    Rendition rendition = asset.getOriginal();		    
	    InputStream inputStream = rendition.adaptTo(InputStream.class);
	    String inputString = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
	    String[] arrOfStr = inputString.split("\n");
        for(int i=1;i<arrOfStr.length;i++){
        	String[] indValues = arrOfStr[i].split(",");
            
        	String PARENT_PATH = indValues[0];
            String PAGE_NAME = indValues[1];
            String WHICH_TEMPLATE = indValues[2];
            String PAGE_TITLE = indValues[3];
            String PAGE_DESCR = indValues[4];
        	
            try {
    			Page page = pageManager.create(PARENT_PATH, PAGE_NAME, WHICH_TEMPLATE, PAGE_TITLE);
    			String fileDestination = PARENT_PATH + "/" + PAGE_NAME + "/jcr:content";
    			Resource pageResource = resourceResolver.getResource(fileDestination);
    			Node myNode = pageResource.adaptTo(Node.class);
    			myNode.setProperty("jcr:description",PAGE_DESCR);
    			Session session = resourceResolver.adaptTo(Session.class);
    			session.save();
    			if (page != null) {
    				resp.getWriter().write("Page created : "+i+"\n");
                }
    			//resp.getWriter().write("Page created : "+i+"\n");
    		} catch (RepositoryException | WCMException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        }
        
        
        resp.getWriter().write("Servlet Done");
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
