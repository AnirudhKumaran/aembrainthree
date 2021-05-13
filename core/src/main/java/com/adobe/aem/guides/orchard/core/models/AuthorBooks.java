package com.adobe.aem.guides.orchard.core.models;

import java.util.List;
import java.util.Map;

public interface AuthorBooks {

    String getAuthorName();

    List<String> getAuthorBooks();

    List<Map<String,String>> getBookDetailsWithMap();

    List<MultifieldHelper> getBookDetailsWithBean();

    List<MultifieldHelper> getBookDetailsWithNastedMultifield();
}
