package com.biencloud.smarthome.web.rest.handler;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;


/**
 * Rest请求异常解析类，支持国际化。
 * 
 * @since 1.0 2013-9-20
 * @see RestErrorResolver
 * @see MessageSourceAware
 * @see InitializingBean
 */
public class DefaultRestErrorResolver implements RestErrorResolver, MessageSourceAware, InitializingBean {

    public static final String DEFAULT_EXCEPTION_MESSAGE_VALUE = "_exmsg";
    public static final String DEFAULT_MESSAGE_VALUE = "_msg";

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private Map<String, RestError> exceptionMappings = Collections.emptyMap();

    private Map<String, String> exceptionMappingDefinitions = Collections.emptyMap();

    private MessageSource messageSource;
    private LocaleResolver localeResolver;

    private String defaultMoreInfoUrl;
    private boolean defaultEmptyCodeToStatus;
    private String defaultDeveloperMessage;

    public DefaultRestErrorResolver() {
        this.defaultEmptyCodeToStatus = true;
        this.defaultDeveloperMessage = DEFAULT_EXCEPTION_MESSAGE_VALUE;
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public void setLocaleResolver(LocaleResolver resolver) {
        this.localeResolver = resolver;
    }

    public void setExceptionMappingDefinitions(Map<String, String> exceptionMappingDefinitions) {
        this.exceptionMappingDefinitions = exceptionMappingDefinitions;
    }

    public void setDefaultMoreInfoUrl(String defaultMoreInfoUrl) {
        this.defaultMoreInfoUrl = defaultMoreInfoUrl;
    }

    public void setDefaultEmptyCodeToStatus(boolean defaultEmptyCodeToStatus) {
        this.defaultEmptyCodeToStatus = defaultEmptyCodeToStatus;
    }

    public void setDefaultDeveloperMessage(String defaultDeveloperMessage) {
        this.defaultDeveloperMessage = defaultDeveloperMessage;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        //populate with some defaults:
        Map<String, String> definitions = createDefaultExceptionMappingDefinitions();

        //add in user-specified mappings (will override defaults as necessary):
        if (this.exceptionMappingDefinitions != null && !this.exceptionMappingDefinitions.isEmpty()) {
            definitions.putAll(this.exceptionMappingDefinitions);
        }

        this.exceptionMappings = toRestErrors(definitions);
    }

    protected final Map<String,String> createDefaultExceptionMappingDefinitions() {

        Map<String,String> m = new LinkedHashMap<String, String>();

        // 400
        applyDef(m, HttpMessageNotReadableException.class, HttpStatus.BAD_REQUEST);
        applyDef(m, MissingServletRequestParameterException.class, HttpStatus.BAD_REQUEST);
        applyDef(m, TypeMismatchException.class, HttpStatus.BAD_REQUEST);
        applyDef(m, "javax.validation.ValidationException", HttpStatus.BAD_REQUEST);

        // 404
        applyDef(m, NoSuchRequestHandlingMethodException.class, HttpStatus.NOT_FOUND);
        applyDef(m, "org.hibernate.ObjectNotFoundException", HttpStatus.NOT_FOUND);

        // 405
        applyDef(m, HttpRequestMethodNotSupportedException.class, HttpStatus.METHOD_NOT_ALLOWED);

        // 406
        applyDef(m, HttpMediaTypeNotAcceptableException.class, HttpStatus.NOT_ACCEPTABLE);

        // 409
        //can't use the class directly here as it may not be an available dependency:
        applyDef(m, "org.springframework.dao.DataIntegrityViolationException", HttpStatus.CONFLICT);

        // 415
        applyDef(m, HttpMediaTypeNotSupportedException.class, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        
        // 500
        //applyDef(m, UnknownError.class, HttpStatus.INTERNAL_SERVER_ERROR);
        
        //applyDef(m, AuthorizationException.class, HttpStatus.NON_AUTHORITATIVE_INFORMATION,10);
        
        //applyDef(m, NoImplClassException.class, HttpStatus.INTERNAL_SERVER_ERROR,11);

        return m;
    }

    private void applyDef(Map<String,String> m, 
    		@SuppressWarnings("rawtypes")Class clazz, HttpStatus status) {
        applyDef(m, clazz.getName(), status);
    }

    private void applyDef(Map<String,String> m, String key, HttpStatus status) {
        m.put(key, definitionFor(status));
    }

    private String definitionFor(HttpStatus status) {
        return status.value() + ", " + DEFAULT_EXCEPTION_MESSAGE_VALUE;
    }
    
    
    private void applyDef(Map<String,String> m, 
    		@SuppressWarnings("rawtypes")Class clazz, HttpStatus status,int code) {
        applyDef(m, clazz.getName(), status,code);
    }

    private void applyDef(Map<String,String> m, String key, HttpStatus status,int code) {
        m.put(key, definitionFor(status,code));
    }

    private String definitionFor(HttpStatus status,int code) {
        return status.value() + ", " + DEFAULT_EXCEPTION_MESSAGE_VALUE + ", code=" + code;
    }


    @Override
    public RestError resolveError(ServletWebRequest request, Object handler, Exception ex) {

        RestError template = getRestErrorTemplate(ex);
        if (template == null) {
            return null;
        }

        //RestError.Builder builder = new RestError.Builder();
        RestError restError = new RestError();
        restError.setStatus(getStatusValue(template, request, ex));
        restError.setCode(getCode(template, request, ex));
      //  restError.setMoreInfoUrl(getMoreInfoUrl(template, request, ex));
        restError.setThrowable(ex);

        String msg = getMessage(template, request, ex);
        if (msg != null) {
            restError.setMessage(msg);
        }
//        msg = getDeveloperMessage(template, request, ex);
//        if (msg != null) {
//            restError.setDeveloperMessage(msg);
//        }

        return restError;
    }

    protected int getStatusValue(RestError template, ServletWebRequest request, Exception ex) {
        return template.getStatus();
    }

    protected int getCode(RestError template, ServletWebRequest request, Exception ex) {
        int code = template.getCode();
        if ( code <= 0 && defaultEmptyCodeToStatus) {
            code = getStatusValue(template, request, ex);
        }
        return code;
    }

//    protected String getMoreInfoUrl(RestError template, ServletWebRequest request, Exception ex) {
//        String moreInfoUrl = template.getMoreInfoUrl();
//        if (moreInfoUrl == null) {
//            moreInfoUrl = this.defaultMoreInfoUrl;
//        }
//        return moreInfoUrl;
//    }

    protected String getMessage(RestError template, ServletWebRequest request, Exception ex) {
        return getMessage(template.getMessage(), request, ex);
    }

//    protected String getDeveloperMessage(RestError template, ServletWebRequest request, Exception ex) {
//        String devMsg = template.getDeveloperMessage();
//        if (devMsg == null && defaultDeveloperMessage != null) {
//            devMsg = defaultDeveloperMessage;
//        }
//        if (DEFAULT_MESSAGE_VALUE.equals(devMsg)) {
//            devMsg = template.getMessage();
//        }
//        return getMessage(devMsg, request, ex);
//    }

    /**
     * Returns the response status message to return to the client, or {@code null} if no
     * status message should be returned.
     *
     * @return the response status message to return to the client, or {@code null} if no
     *         status message should be returned.
     */
    protected String getMessage(String msg, ServletWebRequest webRequest, Exception ex) {

        if (msg != null) {
            if (msg.equalsIgnoreCase("null") || msg.equalsIgnoreCase("off")) {
                return null;
            }
            if (msg.equalsIgnoreCase(DEFAULT_EXCEPTION_MESSAGE_VALUE)) {
                msg = ex.getMessage();
            }
            if (messageSource != null) {
                Locale locale = null;
                if (localeResolver != null) {
                    locale = localeResolver.resolveLocale(webRequest.getRequest());
                }
                msg = messageSource.getMessage(msg, null, msg, locale);
            }
        }

        return msg;
    }

    /**
     * Returns the config-time 'template' RestError instance configured for the specified Exception, or
     * {@code null} if a match was not found.
     * <p/>
     * The config-time template is used as the basis for the RestError constructed at runtime.
     * @param ex
     * @return the template to use for the RestError instance to be constructed.
     */
    private RestError getRestErrorTemplate(Exception ex) {
        Map<String, RestError> mappings = this.exceptionMappings;
        if (CollectionUtils.isEmpty(mappings)) {
            return null;
        }
        RestError template = null;
        String dominantMapping = null;
        int deepest = Integer.MAX_VALUE;
        for (Map.Entry<String, RestError> entry : mappings.entrySet()) {
            String key = entry.getKey();
            int depth = getDepth(key, ex);
            if (depth >= 0 && depth < deepest) {
                deepest = depth;
                dominantMapping = key;
                template = entry.getValue();
            }
        }
        if (template != null && logger.isDebugEnabled()) {
            logger.debug("Resolving to RestError template '" + template + "' for exception of type [" + ex.getClass().getName() +
                    "], based on exception mapping [" + dominantMapping + "]");
        }
        return template;
    }

    /**
     * Return the depth to the superclass matching.
     * <p>0 means ex matches exactly. Returns -1 if there's no match.
     * Otherwise, returns depth. Lowest depth wins.
     */
    protected int getDepth(String exceptionMapping, Exception ex) {
        return getDepth(exceptionMapping, ex.getClass(), 0);
    }

    private int getDepth(String exceptionMapping, 
    		@SuppressWarnings("rawtypes") Class exceptionClass, int depth) {
        if (exceptionClass.getName().contains(exceptionMapping)) {
            // Found it!
            return depth;
        }
        // If we've gone as far as we can go and haven't found it...
        if (exceptionClass.equals(Throwable.class)) {
            return -1;
        }
        return getDepth(exceptionMapping, exceptionClass.getSuperclass(), depth + 1);
    }


    protected Map<String, RestError> toRestErrors(Map<String, String> smap) {
        if (CollectionUtils.isEmpty(smap)) {
            return Collections.emptyMap();
        }

        Map<String, RestError> map = new LinkedHashMap<String, RestError>(smap.size());

        for (Map.Entry<String, String> entry : smap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            RestError template = toRestError(value);
            map.put(key, template);
        }

        return map;
    }

    protected RestError toRestError(String exceptionConfig) {
        String[] values = StringUtils.commaDelimitedListToStringArray(exceptionConfig);
        if (values == null || values.length == 0) {
            throw new IllegalStateException("Invalid config mapping.  Exception names must map to a string configuration.");
        }

        //RestError.Builder builder = new RestError.Builder();
        
        RestError restError = new RestError();

        boolean statusSet = false;
        boolean codeSet = false;
        boolean msgSet = false;
        boolean devMsgSet = false;
        boolean moreInfoSet = false;

        for (String value : values) {

            String trimmedVal = StringUtils.trimWhitespace(value);

            //check to see if the value is an explicitly named key/value pair:
            String[] pair = StringUtils.split(trimmedVal, "=");
            if (pair != null) {
                //explicit attribute set:
                String pairKey = StringUtils.trimWhitespace(pair[0]);
                if (!StringUtils.hasText(pairKey)) {
                    pairKey = null;
                }
                String pairValue = StringUtils.trimWhitespace(pair[1]);
                if (!StringUtils.hasText(pairValue)) {
                    pairValue = null;
                }
                if ("status".equalsIgnoreCase(pairKey)) {
                    int statusCode = getRequiredInt(pairKey, pairValue);
                    restError.setStatus(statusCode);
                    statusSet = true;
                } else if ("code".equalsIgnoreCase(pairKey)) {
                    int code = getRequiredInt(pairKey, pairValue);
                    restError.setCode(code);
                    codeSet = true;
                } else if ("msg".equalsIgnoreCase(pairKey)) {
                    restError.setMessage(pairValue);
                    msgSet = true;
             //   } else if ("devMsg".equalsIgnoreCase(pairKey)) {
             //       restError.setDeveloperMessage(pairValue);
             //       devMsgSet = true;
                } else if ("infoUrl".equalsIgnoreCase(pairKey)) {
           //         restError.setMoreInfoUrl(pairValue);
           //         moreInfoSet = true;
                }
            } else {
                //not a key/value pair - use heuristics to determine what value is being set:
                int val;
                if (!statusSet) {
                    val = getInt("status", trimmedVal);
                    if (val > 0) {
                        restError.setStatus(val);
                        statusSet = true;
                        continue;
                    }
                }
                if (!codeSet) {
                    val = getInt("code", trimmedVal);
                    if (val > 0) {
                        restError.setCode(val);
                        codeSet = true;
                        continue;
                    }
                }
//                if (!moreInfoSet && trimmedVal.toLowerCase().startsWith("http")) {
//                    restError.setMoreInfoUrl(trimmedVal);
//                    moreInfoSet = true;
//                    continue;
//                }
                if (!msgSet) {
                    restError.setMessage(trimmedVal);
                    msgSet = true;
                    continue;
                }
//                if (!devMsgSet) {
//                    restError.setDeveloperMessage(trimmedVal);
//                    devMsgSet = true;
//                    continue;
//                }
//                if (!moreInfoSet) {
//                    restError.setMoreInfoUrl(trimmedVal);
//                    moreInfoSet = true;
//                    //noinspection UnnecessaryContinue
//                    continue;
//                }
            }
        }

        return restError;
    }

    private static int getRequiredInt(String key, String value) {
        try {
            int anInt = Integer.valueOf(value);
            return Math.max(-1, anInt);
        } catch (NumberFormatException e) {
            String msg = "Configuration element '" + key + "' requires an integer value.  The value " +
                    "specified: " + value;
            throw new IllegalArgumentException(msg, e);
        }
    }

    private static int getInt(String key, String value) {
        try {
            return getRequiredInt(key, value);
        } catch (IllegalArgumentException iae) {
            return 0;
        }
    }
}
