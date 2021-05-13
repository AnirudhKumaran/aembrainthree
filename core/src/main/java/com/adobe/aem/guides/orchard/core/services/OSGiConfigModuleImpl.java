package com.adobe.aem.guides.orchard.core.services;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;

import com.adobe.aem.guides.orchard.core.config.GeeksOSGiConfig;

@Component(service = OSGiConfigModule.class,immediate = true)
@Designate(ocd = GeeksOSGiConfig.class)
public class OSGiConfigModuleImpl implements OSGiConfigModule{

    private int serviceId;
    private String serviceName;
    private String serviceURL;

    @Activate
    protected void activate(GeeksOSGiConfig geeksOSGiConfig){
        serviceId=geeksOSGiConfig.serviceID();
        serviceName=geeksOSGiConfig.serviceName();
        serviceURL=geeksOSGiConfig.serviceURL();
    }
    @Override
    public int getServiceId() {
        return serviceId;
    }
    @Override
    public String getServiceName() {
        return serviceName;
    }
    @Override
    public String getServiceURL() {
        return serviceURL;
    }
}
