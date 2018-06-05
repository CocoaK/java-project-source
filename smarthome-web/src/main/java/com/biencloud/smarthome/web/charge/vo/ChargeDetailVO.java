
package com.biencloud.smarthome.web.charge.vo;

import java.util.List;

import com.biencloud.smarthome.web.base.vo.BaseVO;
/**
 * 
 * 项目名称：smarthome-web-new 
 * 类名称：ChargeDetailVO 
 * 类描述：  收费清单VO类
 * @author: kouy 
 * @version: 0.1
 * @date: 2012-6-12 上午10:35:49
 */
public class ChargeDetailVO extends BaseVO {

	private static final long serialVersionUID = 731730738179820035L;
	/** 表示未缴费  */
	public static final Integer FeeStatusNO=2;
	/** 表示已缴费  */
	public static final Integer FeeStatusYES=1;
    private ChargeDataVO chargeData;//收费数据
    private String chargeStatus;//缴费状态
    private String chargeTime;//收费时间
	private String chargeStartTime;//收费开始时间
	private String chargeEndTime;//收费结束时间
    private Long id;
    private Long infoId;//已发送催费信息次数
    private String printSn;//打印流水号
    private String remark;//描述
    private List<ChargeTypeResultVO> chargeTypeResults;
    
    public List<ChargeTypeResultVO> getChargeTypeResults() {
		return chargeTypeResults;
	}

	public void setChargeTypeResults(List<ChargeTypeResultVO> chargeTypeResults) {
		this.chargeTypeResults = chargeTypeResults;
	}

	public ChargeDataVO getChargeData() {
		return chargeData;
	}

	public void setChargeData(ChargeDataVO chargeData) {
		this.chargeData = chargeData;
	}

	public String getChargeStatus() {
		return chargeStatus;
	}

	public void setChargeStatus(String chargeStatus) {
		this.chargeStatus = chargeStatus;
	}

	public String getChargeTime() {
		return chargeTime;
	}

	public void setChargeTime(String chargeTime) {
		this.chargeTime = chargeTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getInfoId() {
		return infoId;
	}

	public void setInfoId(Long infoId) {
		this.infoId = infoId;
	}

	public String getPrintSn() {
		return printSn;
	}

	public void setPrintSn(String printSn) {
		this.printSn = printSn;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getChargeStartTime() {
		return chargeStartTime;
	}

	public void setChargeStartTime(String chargeStartTime) {
		this.chargeStartTime = chargeStartTime;
	}

	public String getChargeEndTime() {
		return chargeEndTime;
	}

	public void setChargeEndTime(String chargeEndTime) {
		this.chargeEndTime = chargeEndTime;
	}
	
	
	


}
