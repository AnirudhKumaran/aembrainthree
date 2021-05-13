package com.adobe.aem.guides.orchard.core.services;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.day.cq.wcm.api.Page;

public interface DemoService {
    public Iterator<Page> getPages();
    public List<Map<String,String>> getReadCSV();
    public String getStrCSV();
}