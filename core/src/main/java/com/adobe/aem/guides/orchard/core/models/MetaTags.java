package com.adobe.aem.guides.orchard.core.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface MetaTags {
	public HashMap<String, String> getMetaOverride();
	public HashMap<String, String> getNewMeta();
}