package com.biencloud.smarthome.service.rest.handler;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 将错误信息转换为Map类型的对象。
 * @author <a href="mailto:Qian.Keane@gmail.com">keane</a>
 * @since 1.0 2013-9-20
 * @see RestErrorConverter
 */
@SuppressWarnings("rawtypes")
public class MapRestErrorConverter implements RestErrorConverter<Map> {

    private static final String DEFAULT_CODE_KEY = "code";
    private static final String DEFAULT_MESSAGE_KEY = "message";
    private static final String DEFAULT_DATA_KEY = "data";

    private String codeKey = DEFAULT_CODE_KEY;
    private String messageKey = DEFAULT_MESSAGE_KEY;
    private String dataKey = DEFAULT_DATA_KEY;

    public Map convert(RestError re) {
        Map<String, Object> m = createMap();
        m.put(getCodeKey(), re.getCode());

        String message = re.getMessage();
        if (message != null) {
            m.put(getMessageKey(), message);
        }

        String devMsg = re.getData();
        if (devMsg != null) {
            m.put(getDataKey(), devMsg);
        }

        return m;
    }

    protected Map<String,Object> createMap() {
        return new LinkedHashMap<String, Object>();
    }

    public String getCodeKey() {
        return codeKey;
    }

    public void setCodeKey(String codeKey) {
        this.codeKey = codeKey;
    }

    public String getMessageKey() {
        return messageKey;
    }

    public void setMessageKey(String messageKey) {
        this.messageKey = messageKey;
    }

	public String getDataKey() {
		return dataKey;
	}

	public void setDataKey(String dataKey) {
		this.dataKey = dataKey;
	}
    
}
