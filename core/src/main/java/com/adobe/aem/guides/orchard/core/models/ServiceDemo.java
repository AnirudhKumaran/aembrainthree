package com.adobe.aem.guides.orchard.core.models;

import com.day.cq.wcm.api.Page;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public interface ServiceDemo {

    public Iterator<Page> getPagesList();
    public List<String> getPageTitleList();

    public List<Map<String,String>> getValueCSV();
    public String getStrCSV();
    
    public String getNameFromService();
    public String getNameFromServiceB();
    public String getNameWithReference();
}
