
package com.biencloud.smarthome.web.charge.vo;

import com.biencloud.smarthome.web.base.vo.BaseVO;
/**
 * 
 * 项目名称：smarthome-web-new 
 * 类名称：ChargeTypeResultVO 
 * 类描述： 收费项目结果实体
 * @author: kouy 
 * @version: 0.1
 * @date: 2012-11-27 下午2:30:43
 */
public class ChargeTypeResultVO  extends BaseVO{

	private static final long serialVersionUID = 1L;
	private String actualTotal;//实际使用量
    private String calUnit;//计算单位
    private Long chargeDataId;//收费数据ID
    private Long chargeDetailId;//收费清单ID
    private String endTotal;//结束使用量
    private Long id;
    private String playMoney;//支付总金额
    private String remark;//描述
    private String standard;//收费标准
    private String startTotal;//起始使用量
	private String chargeTypeName;//收费项目名称
	private String calMode;//计算模式

	
    public String getChargeTypeName() {
		return chargeTypeName;
	}

	public void setChargeTypeName(String chargeTypeName) {
		this.chargeTypeName = chargeTypeName;
	}

	public String getCalMode() {
		return calMode;
	}

	public void setCalMode(String calMode) {
		this.calMode = calMode;
	}

	public String getActualTotal() {
		return actualTotal;
	}

	public void setActualTotal(String actualTotal) {
		this.actualTotal = actualTotal;
	}

	public String getCalUnit() {
		return calUnit;
	}

	public void setCalUnit(String calUnit) {
		this.calUnit = calUnit;
	}

	public Long getChargeDataId() {
		return chargeDataId;
	}

	public void setChargeDataId(Long chargeDataId) {
		this.chargeDataId = chargeDataId;
	}

	public Long getChargeDetailId() {
		return chargeDetailId;
	}

	public void setChargeDetailId(Long chargeDetailId) {
		this.chargeDetailId = chargeDetailId;
	}

	public String getEndTotal() {
		return endTotal;
	}

	public void setEndTotal(String endTotal) {
		this.endTotal = endTotal;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlayMoney() {
		return playMoney;
	}

	public void setPlayMoney(String playMoney) {
		this.playMoney = playMoney;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public String getStartTotal() {
		return startTotal;
	}

	public void setStartTotal(String startTotal) {
		this.startTotal = startTotal;
	}
	
	



}
