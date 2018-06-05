
package com.biencloud.smarthome.web.systemgroup.vo;

import com.biencloud.smarthome.web.base.vo.BaseVO;

/**
 * 
 * 项目名称：smarthome-web-new 
 * 类名称：SystemGroupVO 
 * 类描述： 组织VO类
 * @author: kouy 
 * @version: 0.1
 * @date: 2012-6-12 下午2:40:18
 */
public class SystemGroupVO extends BaseVO {
	
	private static final long serialVersionUID = -7938534626614017320L;
	/** 标志最顶级节点 */
	public static final Integer Deep_Root=0;
	/** 标志下面有子节点 */
	public static final Short HashChild_YES=1;
	/** 小区固定深度 */
	public static final Short DEEP_COMMUNITY=5;
	/** 查询小区url标识 */
	public static final String SELECTCOM_FLAG="COM";

	private String createUser;//创建用户
	private Short deep;//深度
	private String groupName;//组织名称
	private Long groupNo;//组织编号
	private String groupParentNo;//父编号
    private Short hasChild;//是否有子节点
    protected String updateUser;//更新用户
    private String desc;//描述


    public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
     * Gets the value of the createUser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * Sets the value of the createUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreateUser(String value) {
        this.createUser = value;
    }

    /**
     * Gets the value of the deep property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getDeep() {
        return deep;
    }

    /**
     * Sets the value of the deep property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setDeep(Short value) {
        this.deep = value;
    }

    /**
     * Gets the value of the groupName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * Sets the value of the groupName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGroupName(String value) {
        this.groupName = value;
    }

    /**
     * Gets the value of the groupNo property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getGroupNo() {
        return groupNo;
    }

    /**
     * Sets the value of the groupNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setGroupNo(Long value) {
        this.groupNo = value;
    }

    /**
     * Gets the value of the groupParentNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGroupParentNo() {
        return groupParentNo;
    }

    /**
     * Sets the value of the groupParentNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGroupParentNo(String value) {
        this.groupParentNo = value;
    }

    /**
     * Gets the value of the hasChild property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getHasChild() {
        return hasChild;
    }

    /**
     * Sets the value of the hasChild property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setHasChild(Short value) {
        this.hasChild = value;
    }


    /**
     * Gets the value of the updateUser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * Sets the value of the updateUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpdateUser(String value) {
        this.updateUser = value;
    }

}
