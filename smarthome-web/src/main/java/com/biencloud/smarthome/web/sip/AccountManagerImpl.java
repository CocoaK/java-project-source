package com.biencloud.smarthome.web.sip;

import gov.nist.javax.sip.clientauthutils.AccountManager;
import gov.nist.javax.sip.clientauthutils.UserCredentials;

import javax.sip.ClientTransaction;

public class AccountManagerImpl implements AccountManager {
    

    public UserCredentials getCredentials(ClientTransaction challengedTransaction, String realm) {
       return new UserCredentialsImpl("1009","192.168.0.107","e10adc3949ba59abbe56e057f20f883e");
    }

}
