package com.adobe.aem.guides.orchard.core.servlets;

import com.day.cq.commons.jcr.JcrConstants;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.request.RequestParameter;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

@Component(service = Servlet.class)
@SlingServletResourceTypes(
        methods = {HttpConstants.METHOD_GET,HttpConstants.METHOD_POST},
        resourceTypes = "orchard/components/structure/page",
        selectors = {"sitemap","test"},
        extensions = {"txt","xml"}
)
public class SiteMapServlet extends SlingAllMethodsServlet {
    private static final Logger LOG = LoggerFactory.getLogger(SiteMapServlet.class);

    @Override
    protected void doGet(final SlingHttpServletRequest req,
                         final SlingHttpServletResponse resp) throws ServletException, IOException {
        String requestText = req.getParameter("cid");
    	final Resource resource = req.getResource();
        resp.setContentType("text/plain");
        resp.getWriter().write("Page Title = " + resource.getValueMap().get(JcrConstants.JCR_TITLE));
        resp.getWriter().write("Hello World " + requestText==null?"":requestText);
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
