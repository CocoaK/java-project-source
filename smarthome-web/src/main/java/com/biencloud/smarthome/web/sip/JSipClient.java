package com.biencloud.smarthome.web.sip;

import gov.nist.javax.sip.SipStackExt;
import gov.nist.javax.sip.clientauthutils.AuthenticationHelper;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.sip.ClientTransaction;
import javax.sip.Dialog;
import javax.sip.DialogTerminatedEvent;
import javax.sip.IOExceptionEvent;
import javax.sip.InvalidArgumentException;
import javax.sip.ListeningPoint;
import javax.sip.RequestEvent;
import javax.sip.ResponseEvent;
import javax.sip.SipException;
import javax.sip.SipFactory;
import javax.sip.SipListener;
import javax.sip.SipProvider;
import javax.sip.SipStack;
import javax.sip.TimeoutEvent;
import javax.sip.TransactionTerminatedEvent;
import javax.sip.address.Address;
import javax.sip.address.AddressFactory;
import javax.sip.address.SipURI;
import javax.sip.header.CSeqHeader;
import javax.sip.header.CallIdHeader;
import javax.sip.header.ContactHeader;
import javax.sip.header.ContentTypeHeader;
import javax.sip.header.FromHeader;
import javax.sip.header.Header;
import javax.sip.header.HeaderFactory;
import javax.sip.header.MaxForwardsHeader;
import javax.sip.header.ToHeader;
import javax.sip.header.ViaHeader;
import javax.sip.message.MessageFactory;
import javax.sip.message.Request;
import javax.sip.message.Response;

//import com.tlsmart.smartbox.utils.Constants;

/**
 * 
 */
public class JSipClient implements SipListener {

	private int counter;
	

	/**
	 * Creates new form SipClient
	 */
	public JSipClient() {
		initComponents();
	}

	protected static final String SERVER_ADDRESS = "218.87.109.116"; // The server IP address.
	
	private static final String SIP_SEND_USER = "1010";

	protected static final int TI_PORT = 5060;

	protected static final int RI_PORT = 6050;
	
	
	private ClientTransaction inviteTid;
	private int invco;
	private AuthenticationHelper authenticationHelper;
	
	// Objects used to communicate to the JAIN SIP API.
	SipFactory sipFactory; // Used to access the SIP API.
	SipStack sipStack; // The SIP stack.
	SipProvider sipProvider; // Used to send SIP messages.
	MessageFactory messageFactory; // Used to create SIP message factory.
	HeaderFactory headerFactory; // Used to create SIP headers.
	AddressFactory addressFactory; // Used to create SIP URIs.
	ListeningPoint listeningPoint; // SIP listening IP address/port.
	Properties properties; // Other properties.

	// Objects keeping local configuration.
	String ip = this.getLocalAddress(); // The local IP address.	
	// String server_ip = "sip.tonglismart.com";
	int port = RI_PORT; // The local port.
	String protocol = "udp"; // The local protocol (UDP).
	int tag = (new Random()).nextInt(); // The local tag.
	Address contactAddress; // The contact address.
	ContactHeader contactHeader; // The contact header.
	
	//test account
	//private String to = "sip:1009@" + Constants.SIP_SERVER_ADDRESS + ":5060";
	
	protected static final String EXTENSION_HDR = "Status-Extension";

	/**
	 * Returns a properties object containing all RI settings. The result from
	 * this method is passed to the SipFactory when creating the RI Stack
	 */
	public static Properties getRiProperties(boolean autoDialog) {
		// TODO collect all system properties
		// prefixed javax.sip.tck.ri and add them to the local
		// properties object

		Properties properties = new Properties();

		// IP_ADDRESS is deprecated as of jsip 1.2.
		// Each listening point associated with a stack has its own IP address.
		properties.setProperty("javax.sip.STACK_NAME", "RiStack");
		// properties.setProperty("gov.nist.javax.sip.TRACE_LEVEL", "0");

		properties.setProperty("gov.nist.javax.sip.TRACE_LEVEL", "32");
		properties.setProperty("gov.nist.javax.sip.DEBUG_LOG",
				"logs/riDebugLog.txt");
		properties.setProperty("gov.nist.javax.sip.SERVER_LOG",
				"logs/riMessageLog.txt");

		// JvB: Most TCK tests dont work well with automatic dialog support
		// enabled
		// Disable it for the moment
		properties.setProperty("javax.sip.AUTOMATIC_DIALOG_SUPPORT",
				autoDialog ? "ON" : "OFF");

		// JvB: for testing of ACK to non-2xx
		properties.setProperty(
				"gov.nist.javax.sip.PASS_INVITE_NON_2XX_ACK_TO_LISTENER",
				"true");
		// For testing sending of stateless null keepalive messages.
		// @see test.tck.msgflow.SipProviderTest.testSendNullRequest
		properties.setProperty("javax.sip.OUTBOUND_PROXY", SERVER_ADDRESS + ":"
				+ TI_PORT + "/udp");

		return properties;
	}

	public void iniStack() {
		try {
			// Get the local IP address.
			// this.ip = InetAddress.getLocalHost().getHostAddress();
			// Create the SIP factory and set the path name.
			this.sipFactory = SipFactory.getInstance();
			this.sipFactory.setPathName("gov.nist");
			// Create and set the SIP stack properties.
			this.properties = getRiProperties(true);
			// Create the SIP stack.
			// this.sipStack = this.sipFactory.createSipStack(this.properties);
			this.sipStack = this.sipFactory.createSipStack(properties);
			// Create the SIP message factory.
			this.messageFactory = this.sipFactory.createMessageFactory();
			// Create the SIP header factory.
			this.headerFactory = this.sipFactory.createHeaderFactory();
			// Create the SIP address factory.
			this.addressFactory = this.sipFactory.createAddressFactory();
			// Create the SIP listening point and bind it to the local IP
			// address, port and protocol.
			this.listeningPoint = this.sipStack.createListeningPoint(this.ip,
					this.port, this.protocol);
			// Create the SIP provider.
			this.sipProvider = this.sipStack
					.createSipProvider(this.listeningPoint);
			// Add our application as a SIP listener.
			this.sipProvider.addSipListener(this);
			// Create the contact address used for all SIP messages.
			this.contactAddress = this.addressFactory.createAddress("sip:"
					+ this.ip + ":" + this.port);
			// Create the contact header used for all SIP messages.
			this.contactHeader = this.headerFactory
					.createContactHeader(contactAddress);
			// Display the local IP address and port in the text area.
			printMessage("Local address: " + this.ip + ":" + this.port
					+ "\n");
		} catch (Exception e) {
			e.printStackTrace();
			//System.exit(-1);
		}
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	private void initComponents() {
		//System.out.println("----------------------------Welcome Sip client---------------------------------------------");
		iniStack();
		//onInvite(sendTo,message);
//		onRegisterStateless();

	}// </editor-fold>//GEN-END:initComponents

	private void onOpen(java.awt.event.WindowEvent evt) {// GEN-FIRST:event_onOpen
		// A method called when you open your application.
	}// GEN-LAST:event_onOpen

	private void onRegisterStateless() {// GEN-FIRST:event_onRegisterStateless
		// A method called when you click on the "Reg (SL)" button.

		try {
			// Get the destination address from the text field.
//			Request request = createRequest(Request.REGISTER, to, null, null,null);
			
			
			Request request = createRequest(Request.REGISTER, addressFactory,
					headerFactory, messageFactory, "1009", "1009", 
					null,null, null);			

			inviteTid = sipProvider.getNewClientTransaction(request);
			inviteTid.sendRequest();

			printMessage("Request sent:\n" + request.toString()
					+ "\n\n");
		} catch (Exception e) {
			// If an error occurred, display the error.
			e.printStackTrace();
			printMessage("Request sent failed: " + e.getMessage()
					+ "\n");
		}

	}// GEN-LAST:event_onRegisterStateless

	private void onRegisterStatefull() {// GEN-FIRST:event_onRegisterStatefull
		// A method called when you click on the "Reg (SF)" button.
	}// GEN-LAST:event_onRegisterStatefull

	public void onInvite(String dst,String content) {// GEN-FIRST:event_onInvite
		// A method called when you click on the "Invite" button.
		try {
//			Request request = createRequest(Request.MESSAGE, to, "text","plain", "Hello world!");

			Request request = createRiInviteRequest("text","plain", dst, content);
			
			inviteTid = sipProvider.getNewClientTransaction(request);
			inviteTid.sendRequest();

			printMessage("[onInvite] Request sent:\n" + request.toString()
					+ "\n\n");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}// GEN-LAST:event_onInvite

	private void onBye(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_onBye
		// A method called when you click on the "Bye" button.
	}// GEN-LAST:event_onBye

	public Request createRequest(String method, String to, String contentType,
			String contentSubType, String content) {
		try {
			Address addressTo = this.addressFactory.createAddress(to);
			// Create the request URI for the SIP message.
			javax.sip.address.URI requestURI = addressTo.getURI();

			// Create the SIP message headers.

			// The "Via" headers.
			ArrayList viaHeaders = new ArrayList();
			ViaHeader viaHeader = this.headerFactory.createViaHeader(this.ip,
					this.port, "udp", null);
			viaHeaders.add(viaHeader);
			// The "Max-Forwards" header.
			MaxForwardsHeader maxForwardsHeader = this.headerFactory
					.createMaxForwardsHeader(70);
			// The "Call-Id" header.
			CallIdHeader callIdHeader = this.sipProvider.getNewCallId();
			// The "CSeq" header.
			CSeqHeader cSeqHeader = this.headerFactory.createCSeqHeader(1L,
					method);
			// The "From" header.
			FromHeader fromHeader = this.headerFactory.createFromHeader(
					addressTo, String.valueOf(this.tag));
			// The "To" header.
			ToHeader toHeader = this.headerFactory.createToHeader(addressTo,
					null);

			// Create the request.
			Request request = this.messageFactory.createRequest(requestURI,
					method, callIdHeader, cSeqHeader, fromHeader, toHeader,
					viaHeaders, maxForwardsHeader);
			// Add the "Contact" header to the request.
			if (content == null)
				request.addHeader(contactHeader);

			if (contentType != null && contentSubType != null
					&& content != null) {
				ContentTypeHeader contentTypeHdr = headerFactory
						.createContentTypeHeader(contentType, contentSubType);
				request.setContent(content, contentTypeHdr);
			}

			return request;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param to
	 * @param message
	 * @throws ParseException
	 * @throws InvalidArgumentException
	 * @throws SipException
	 */
	public void sendMessage(String to, String message) throws ParseException,
			InvalidArgumentException, SipException {
		try {
			// Get the destination address from the text field.
			Address addressTo = this.addressFactory.createAddress(to);
			// Create the request URI for the SIP message.
			javax.sip.address.URI requestURI = addressTo.getURI();

			// Create the SIP message headers.

			// The "Via" headers.
			ArrayList viaHeaders = new ArrayList();
			ViaHeader viaHeader = this.headerFactory.createViaHeader(this.ip,
					this.port, "udp", null);
			viaHeaders.add(viaHeader);
			// The "Max-Forwards" header.
			MaxForwardsHeader maxForwardsHeader = this.headerFactory
					.createMaxForwardsHeader(70);
			// The "Call-Id" header.
			CallIdHeader callIdHeader = this.sipProvider.getNewCallId();
			// The "CSeq" header.
			CSeqHeader cSeqHeader = this.headerFactory.createCSeqHeader(1L,
					"REGISTER");
			// The "From" header.
			FromHeader fromHeader = this.headerFactory.createFromHeader(
					addressTo, String.valueOf(this.tag));
			// The "To" header.
			ToHeader toHeader = this.headerFactory.createToHeader(addressTo,
					null);

			// Create the REGISTER request.
			Request request = this.messageFactory.createRequest(requestURI,
					Request.MESSAGE, callIdHeader, cSeqHeader, fromHeader,
					toHeader, viaHeaders, maxForwardsHeader);
			// Add the "Contact" header to the request.
			request.addHeader(contactHeader);

			ContentTypeHeader contentTypeHeader = headerFactory
					.createContentTypeHeader("text", "plain");
			request.setContent(message, contentTypeHeader);

			ClientTransaction tran = sipProvider
					.getNewClientTransaction(request);
			tran.sendRequest();

			// Display the message in the text area.
			printMessage("Request sent:\n" + request.toString()
					+ "\n\n");
		} catch (Exception e) {
			// If an error occurred, display the error.
			printMessage("Request sent failed: " + e.getMessage()
					+ "\n");
		}

	}

	/**
	 * @param method
	 * @param addressFactory
	 * @param headerFactory
	 * @param messageFactory
	 * @param src
	 * @param dst
	 * @param contentType
	 * @param contentSubType
	 * @param content
	 * @return
	 * @throws Exception
	 */
	protected Request createRequest(String method,
			AddressFactory addressFactory, HeaderFactory headerFactory,
			MessageFactory messageFactory, String src, String dst,
			String contentType, String contentSubType, String content)
			throws Exception {
		SipURI srcSipURI = addressFactory.createSipURI(src, SERVER_ADDRESS);
		srcSipURI.setPort(ListeningPoint.PORT_5060);
		srcSipURI.setTransportParam(ListeningPoint.UDP);
		
		SipURI dstSipURI = addressFactory.createSipURI(dst, SERVER_ADDRESS);
		srcSipURI.setPort(ListeningPoint.PORT_5060);
		srcSipURI.setTransportParam(ListeningPoint.UDP);		

		CallIdHeader callId = sipProvider.getNewCallId();
		// callId = headerFactory.createCallIdHeader( callId.getCallId() );

		// CSeq
		CSeqHeader cSeq = headerFactory.createCSeqHeader(1L, method);

		// From
		Address fromAddress = addressFactory.createAddress(srcSipURI);

		FromHeader from = headerFactory.createFromHeader(fromAddress,
				Integer.toString(sipProvider.hashCode()));
		// To
		Address toAddress = addressFactory.createAddress(dstSipURI);
		ToHeader to = headerFactory.createToHeader(toAddress, null);
		// Contact
//		ContactHeader contact = headerFactory.createContactHeader(fromAddress);

		List via = new LinkedList();
		ViaHeader viaHeader = headerFactory.createViaHeader(this.ip,this.port, "udp",
				// BUG: Use proper RFC3261 branch ID
				"z9hG4bK" + Long.toString(System.currentTimeMillis())
		// branch id
				);
		via.add(viaHeader);
		MaxForwardsHeader maxForwards = headerFactory
				.createMaxForwardsHeader(70);

		Request request = messageFactory.createRequest(dstSipURI, method,
				callId, cSeq, from, to, via, maxForwards);
		request.addHeader(this.contactHeader);

		if (contentType != null && contentSubType != null && content != null) {
			ContentTypeHeader contentTypeHdr = headerFactory
					.createContentTypeHeader(contentType, contentSubType);
			request.setContent(content, contentTypeHdr);
		}

		// pass the headerFactory - issue17 by larryb@dev.java.net
		addStatus(headerFactory, request);
		return request;
	}

	/**
	 * Creates an invite request object using the RI. This invite request is
	 * meant to be sent to the TI
	 * 
	 * @param contentType
	 *            if the content parameter is not null then this is its content
	 *            type.
	 * @param contentSubType
	 *            if the content parameter is not null then this is its content
	 *            sub type.
	 * @param content
	 *            if the request is to have any content then this parameter is
	 *            used to set it. Th content parameter is to be left to null if
	 *            the request won't have any content.
	 * @return an RI->TI invite request
	 * @throws TckInternalError
	 *             if anything should gou wrong.
	 */
	protected Request createRiInviteRequest(String contentType,
			String contentSubType, String dst, String content) {
		try {
			Request request = createRequest(Request.MESSAGE, addressFactory,
					headerFactory, messageFactory, SIP_SEND_USER, dst, contentType,
					contentSubType, content);
			return request;
		} catch (Throwable exc) {
			exc.printStackTrace();
		}
		return null;
	}



	// issue 17 on dev.java.net specify the headerFactory to use
	// report and fix thereof larryb@dev.java.net
	protected void addStatus(HeaderFactory headerFactory, Request request) {
		try {
			Header extension = headerFactory.createHeader(EXTENSION_HDR,
					new Integer(counter++).toString());
			request.addHeader(extension);
		} catch (ParseException ex) {
			// do nothing
		}
	}
	

	@Override
	public void processRequest(RequestEvent requestEvent) {
		// A method called when you receive a SIP request.
		 requestEvent.getServerTransaction();
		System.out.println("Sending ------------------------------------------------------------------------------------------");
		System.out.println("[]:"+requestEvent.toString());
	}

	@Override
	public void processResponse(ResponseEvent responseEvent) {
		// A method called when you receive a SIP request.
		// Get the response.
		Response response = responseEvent.getResponse();
		try {
			// Display the response message in the text area.
			//printMessage("\nReceived response: " + response.toString());

			ClientTransaction tid = responseEvent.getClientTransaction();
			CSeqHeader cseq = (CSeqHeader) response.getHeader(CSeqHeader.NAME);
			if (response.getStatusCode() == Response.OK) {
				if (cseq.getMethod().equals(Request.REGISTER)) {
					System.out.println("regist ACK OK");
					//onInvite();
				} else if (cseq.getMethod().equals(Request.INVITE)) {
					Dialog dialog = inviteTid.getDialog();
					Request ackRequest = dialog.createAck(cseq.getSeqNumber());
					dialog.sendAck(ackRequest);
					System.out.println("Sending ACK");
				} else if (cseq.getMethod().equals(Request.MESSAGE)) {
					System.out.println("Send OK !");
				} else if (cseq.getMethod().equals(Request.CANCEL)) {
					System.out.println("Sending BYE -- cancel went in too late !!");
				}
			} else if (response.getStatusCode() == Response.PROXY_AUTHENTICATION_REQUIRED
					|| response.getStatusCode() == Response.UNAUTHORIZED) {
				authenticationHelper = ((SipStackExt) sipStack)
						.getAuthenticationHelper(new AccountManagerImpl(),
								headerFactory);
				inviteTid = authenticationHelper.handleChallenge(response, tid,
						sipProvider, 5);
				inviteTid.getRequest().addHeader(
						headerFactory.createExpiresHeader(3600));
				inviteTid.getRequest().setMethod(Request.REGISTER);
				inviteTid.sendRequest();
				invco++;
				printMessage("[processResponse] Request sent:\n" + inviteTid.getRequest().toString() + "\n\n");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.exit(0);
		}
	}

	@Override
	public void processTimeout(TimeoutEvent timeoutEvent) {
		printMessage("\nReceived no response,time out: ");
	}

	@Override
	public void processIOException(IOExceptionEvent exceptionEvent) {
		// A method called when a SIP operation results in an I/O error.
	}

	@Override
	public void processTransactionTerminated(
			TransactionTerminatedEvent transactionTerminatedEvent) {
		// A method called when a SIP transaction terminates.
	}

	@Override
	public void processDialogTerminated(
			DialogTerminatedEvent dialogTerminatedEvent) {
		// A method called when a SIP dialog terminates.
	}
	
	public void printMessage(Object obj){
		System.out.println(obj);
	}
	//停止
	public void stopStack(){
		sipStack.stop();
	}
	
	private String getLocalAddress(){
		InetAddress addr = null;
		try {
			addr = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(addr == null){
			return null;
		}
		return addr.getHostAddress();
	}
	/**
	 * @param args
	 *            the command line arguments
	 * @throws InterruptedException 
	 */
	public static void main(String args[]) throws InterruptedException {
		JSipClient client = new JSipClient();
		client.onInvite("1009", "hello");
		client.stopStack();
	}
	
}
