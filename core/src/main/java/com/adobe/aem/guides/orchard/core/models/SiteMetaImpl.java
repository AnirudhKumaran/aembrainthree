package com.adobe.aem.guides.orchard.core.models;

import com.adobe.cq.sightly.WCMUsePojo;
import java.util.HashMap;
import javax.jcr.Node;
import javax.jcr.NodeIterator;

//extends WCMUsePojo

public class SiteMetaImpl implements SiteMeta { 

 private HashMap<String , String> metaOverride =new HashMap<String ,String>() ;
 private HashMap<String , String> newMeta =new HashMap<String ,String>() ;

 /*@Override
 public void activate() throws Exception {
 Node currentNode = getResource().adaptTo(Node.class);
 if(currentNode.hasNode("overRideMetadata")){
 Node overRideMetadataNode = currentNode.getNode("overRideMetadata");
 NodeIterator ni =  overRideMetadataNode.getNodes();
 while (ni.hasNext()) { 
 Node child = (Node)ni.nextNode();    
 metaOverride.put(child.getProperty("metaname").getString(),child.getProperty("metavalue").getString());
 }
 } 

 if(currentNode.hasNode("newMetadata")){
 Node newMetadataNode = currentNode.getNode("newMetadata");
 NodeIterator ni =  newMetadataNode.getNodes();
 while (ni.hasNext()) {       
 Node child = (Node)ni.nextNode(); 
 newMeta.put(child.getProperty("metaname").getString(),child.getProperty("metavalue").getString());
 } 
 } 
 }*/

@Override
public HashMap<String, String> getMetaOverride() {
	// TODO Auto-generated method stub
	return metaOverride;
}

@Override
public HashMap<String, String> getNewMeta() {
	// TODO Auto-generated method stub
	return newMeta;
}
 
}
