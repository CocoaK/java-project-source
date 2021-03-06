package com.smarthome.socket.wsservice.stub;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;

/**
 * This class was generated by the JAX-WS RI. JAX-WS RI 2.1.3-hudson-390-
 * Generated source version: 2.0
 * <p>
 * An example of how this class may be used:
 * 
 * <pre>
 * SmartHomeTcpServiceService service = new SmartHomeTcpServiceService();
 * SmartHomeTcpService portType = service.getSmartHomeTcpServicePort();
 * portType.deleteByEntity(...);
 * </pre>
 * 
 * </p>
 * 
 */
@WebServiceClient(name = "SmartHomeTcpServiceService", targetNamespace = "http://service.cxfservice.smarthome.biencloud.com/", wsdlLocation = "http://127.0.0.1:8080/smarthomeservice/services/pushService?wsdl")
public class SmartHomeTcpServiceService extends Service {

	private final static URL SMARTHOMETCPSERVICESERVICE_WSDL_LOCATION;
	private final static Logger logger = Logger
			.getLogger(com.smarthome.socket.wsservice.stub.SmartHomeTcpServiceService.class
					.getName());

	static {
		URL url = null;
		try {
			URL baseUrl;
			baseUrl = com.smarthome.socket.wsservice.stub.SmartHomeTcpServiceService.class
					.getResource(".");
			url = new URL(baseUrl,
					"http://127.0.0.1:8080/smarthomeservice/services/pushService?wsdl");
		} catch (MalformedURLException e) {
			logger.warning("Failed to create URL for the wsdl Location: 'http://127.0.0.1:8080/smarthomeservice/services/pushService?wsdl', retrying as a local file");
			logger.warning(e.getMessage());
		}
		SMARTHOMETCPSERVICESERVICE_WSDL_LOCATION = url;
	}

	public SmartHomeTcpServiceService(URL wsdlLocation, QName serviceName) {
		super(wsdlLocation, serviceName);
	}

	public SmartHomeTcpServiceService() {
		super(SMARTHOMETCPSERVICESERVICE_WSDL_LOCATION, new QName(
				"http://service.cxfservice.smarthome.biencloud.com/",
				"SmartHomeTcpServiceService"));
	}

	/**
	 * 
	 * @return returns SmartHomeTcpService
	 */
	@WebEndpoint(name = "SmartHomeTcpServicePort")
	public SmartHomeTcpService getSmartHomeTcpServicePort() {
		return super.getPort(new QName(
				"http://service.cxfservice.smarthome.biencloud.com/",
				"SmartHomeTcpServicePort"), SmartHomeTcpService.class);
	}

}
