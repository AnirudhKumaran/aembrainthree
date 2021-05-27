package com.adobe.aem.guides.orchard.core.models;

import java.util.List;
import java.util.Map;

public interface Product {
    
    String getBookTitle();
    float getBookPrice();
    String getBookDescription();
    String getAuthorName();
    String getBookLangauge();
    String getFileReference();
}