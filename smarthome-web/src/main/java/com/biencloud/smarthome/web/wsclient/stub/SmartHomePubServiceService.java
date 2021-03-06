package com.biencloud.smarthome.web.wsclient.stub;

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
* <pre>
* SmartHomePubServiceService service = new SmartHomePubServiceService();
* SmartHomePubService portType = service.getSmartHomePubServicePort();
* portType.queryOwnerUnitDevice(...);
* </pre>
* </p>
* <p>
* An example of how this class may be used:
* <pre>
* SmartHomePubServiceService service = new SmartHomePubServiceService();
* SmartHomePubService portType = service.getSmartHomePubServicePort();
* portType.queryOwnerUnitDevice(...);
* </pre>
* </p>
* <p>
* An example of how this class may be used:
* <pre>
* SmartHomePubServiceService service = new SmartHomePubServiceService();
* SmartHomePubService portType = service.getSmartHomePubServicePort();
* portType.queryOwnerUnitDevice(...);
* </pre>
* </p>
 * <p>
 * An example of how this class may be used:
 * 
 * <pre>
 * SmartHomePubServiceService service = new SmartHomePubServiceService();
 * SmartHomePubService portType = service.getSmartHomePubServicePort();
 * portType.queryOwnerUnitDevice(...);
 * </pre>
 * 
 * </p>
 * 
 */
@WebServiceClient(name = "SmartHomePubServiceService", targetNamespace = "http://service.cxfservice.smarthome.biencloud.com/", wsdlLocation = "http://127.0.0.1:8080/smarthomeservice/services/smartHomeService?wsdl")
public class SmartHomePubServiceService extends Service {

	private final static URL SMARTHOMEPUBSERVICESERVICE_WSDL_LOCATION;
	private final static Logger logger = Logger
			.getLogger(com.biencloud.smarthome.web.wsclient.stub.SmartHomePubServiceService.class
					.getName());

	static {
		URL url = null;
		try {
			URL baseUrl;
			baseUrl = com.biencloud.smarthome.web.wsclient.stub.SmartHomePubServiceService.class
					.getResource(".");
			url = new URL(baseUrl,
					"http://127.0.0.1:8080/smarthomeservice/services/smartHomeService?wsdl");
		} catch (MalformedURLException e) {
			logger.warning("Failed to create URL for the wsdl Location: 'http://127.0.0.1:8080/smarthomeservice/services/smartHomeService?wsdl', retrying as a local file");
			logger.warning(e.getMessage());
		}
		SMARTHOMEPUBSERVICESERVICE_WSDL_LOCATION = url;
	}

	public SmartHomePubServiceService(URL wsdlLocation, QName serviceName) {
		super(wsdlLocation, serviceName);
	}

	public SmartHomePubServiceService() {
		super(SMARTHOMEPUBSERVICESERVICE_WSDL_LOCATION, new QName(
				"http://service.cxfservice.smarthome.biencloud.com/",
				"SmartHomePubServiceService"));
	}

	/**
	 * 
	 * @return returns SmartHomePubService
	 */
	@WebEndpoint(name = "SmartHomePubServicePort")
	public SmartHomePubService getSmartHomePubServicePort() {
		return super.getPort(new QName(
				"http://service.cxfservice.smarthome.biencloud.com/",
				"SmartHomePubServicePort"), SmartHomePubService.class);
	}

}
