/**
 * SDKServiceLocator.java
 * <p>
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cn.exclusive.b2m.eucp.sdkhttp;

public class SDKServiceLocator extends org.apache.axis.client.Service implements SDKService {

    // Use to get a proxy class for SDKService
    private String SDKService_address = "http://sdk229ws.eucp.b2m.cn:8080/sdk/SDKService?wsdl";
    // The WSDD service name defaults to the port name.
    private String SDKServiceWSDDServiceName = "SDKService";
    private java.util.HashSet ports = null;

    public SDKServiceLocator() {
    }

    // private java.lang.String SDKService_address =
    // "http://116.58.219.223:8080/sdk/SDKService?wsdl";

    // private java.lang.String SDKService_address =
    // PropertyResourceBundle.getBundle("config").getString("uri");

    public SDKServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SDKServiceLocator(String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    public String getSDKServiceAddress() {
        return SDKService_address;
    }

    public String getSDKServiceWSDDServiceName() {
        return SDKServiceWSDDServiceName;
    }

    public void setSDKServiceWSDDServiceName(String name) {
        SDKServiceWSDDServiceName = name;
    }

    public SDKClient getSDKService() throws javax.xml.rpc.ServiceException {
        java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SDKService_address);
            System.out.println("SDKService_address==========" + SDKService_address);
        } catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSDKService(endpoint);
    }

    public SDKClient getSDKService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            cn.exclusive.b2m.eucp.sdkhttp.SDKServiceBindingStub _stub = new cn.exclusive.b2m.eucp.sdkhttp.SDKServiceBindingStub(portAddress, this);
            _stub.setPortName(getSDKServiceWSDDServiceName());
            return _stub;
        } catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSDKServiceEndpointAddress(String address) {
        SDKService_address = address;
    }

    /**
     * For the given interface, get the stub implementation. If this service has
     * no port for the given interface, then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (SDKClient.class.isAssignableFrom(serviceEndpointInterface)) {
                cn.exclusive.b2m.eucp.sdkhttp.SDKServiceBindingStub _stub = new cn.exclusive.b2m.eucp.sdkhttp.SDKServiceBindingStub(new java.net.URL(SDKService_address), this);
                _stub.setPortName(getSDKServiceWSDDServiceName());
                return _stub;
            }
        } catch (Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation. If this service has
     * no port for the given interface, then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        String inputPortName = portName.getLocalPart();
        if ("SDKService".equals(inputPortName)) {
            return getSDKService();
        } else {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://sdkhttp.eucp.b2m.cn/", "SDKService");
    }

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://sdkhttp.eucp.b2m.cn/", "SDKService"));
        }
        return ports.iterator();
    }

    /**
     * Set the endpoint address for the specified port name.
     */
    public void setEndpointAddress(String portName, String address) throws javax.xml.rpc.ServiceException {

        if ("SDKService".equals(portName)) {
            setSDKServiceEndpointAddress(address);
        } else { // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
     * Set the endpoint address for the specified port name.
     */
    public void setEndpointAddress(javax.xml.namespace.QName portName, String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
