package com.biencloud.smarthome.web.qrcode.action;

import java.io.OutputStream;
import java.util.List;
import com.biencloud.smarthome.web.base.action.BaseAction;
import com.biencloud.smarthome.web.common.util.Constants;
import com.biencloud.smarthome.web.common.vo.ResultEntity;
import com.biencloud.smarthome.web.housemgr.service.ICellHouseholdInfoService;
import com.biencloud.smarthome.web.housemgr.vo.CellHouseholdInfoVo;
import com.biencloud.smarthome.web.qrcode.service.IQrcodeService;
import com.biencloud.smarthome.web.qrcode.vo.QrcodeVO;
import com.biencloud.smarthome.web.sip.VO.SipRegister;
import com.biencloud.smarthome.web.sip.service.ISipRegisterService;

@SuppressWarnings("serial")
public class QrcodeAction extends BaseAction<QrcodeAction>{
	
	private QrcodeVO qrcodeVO;
	private IQrcodeService qrcodeService;
	private ICellHouseholdInfoService cellHouseholdInfoService;
	private List<QrcodeVO> qrcodeVOs;
	private OutputStream qrcodeOs;
	private Integer sipCount;
	private Integer lockCount;
	private ResultEntity<String> re;
	private ISipRegisterService sipRegisterService;
	private SipRegister sipRegister;
	
	public String queryList() throws Exception{
		if(qrcodeVO==null){
			return null;
		}
		qrcodeVOs = qrcodeService.queryList(qrcodeVO);
		return SUCCESS;
	}
	
	public String printQrcode() throws Exception{
		qrcodeOs = qrcodeService.qrcodeOutputStream(qrcodeVO,super.getResponse().getOutputStream());
		return null;
	}
	
	//添加二维码输入界面
	public String addInput() throws Exception{
		return SUCCESS;
	}
	
	//查询请求二维码
	public String queryReqList() throws Exception{
		if(qrcodeVO==null){
			qrcodeVO = new QrcodeVO();
		}
		qrcodeVO.setStatus(Constants.QRCODE_STATUS_REQUEST);
		qrcodeVOs = qrcodeService.queryList(qrcodeVO);
		return SUCCESS;
	}
	
	//申请二维码输入界面
	public String addReqInput() throws Exception{
		return SUCCESS;
	}
	
	//添加二维码
	public String add() throws Exception{
		add(qrcodeVO,sipCount,lockCount);
		String houseId = qrcodeVO.getHouseId();
		qrcodeVO = new QrcodeVO();
		qrcodeVO.setHouseId(houseId);
		return queryList();
	}
	
	//修改并注册二维码
	public String update() throws Exception{
		//sipRegister = new SipRegister();
		if(sipRegister!=null){
			sipRegister.setPassword(qrcodeVO.getPwd());
		}
		ResultEntity<SipRegister> sip = sipRegisterService.create(sipRegister);
		SipRegister sr = sip.getData();
		if(sr!=null){
			qrcodeVO.setSipUid(sr.getUsername());
			qrcodeVO.setPwd(sr.getPassword());
		}
		qrcodeVO.setStatus(Constants.QRCODE_STATUS_VALID);
		re = qrcodeService.update(qrcodeVO);
		
		return SUCCESS;
	}
	
	//删除二维码
	public String deleteSip() throws Exception{
		qrcodeService.delete(qrcodeVO);
		if(qrcodeVO!=null){
			qrcodeVO.setSipUid(null);
		}
		return queryList();
	}
	
	public String addAndPrint()throws Exception{
		
		qrcodeOs = qrcodeService.qrcodeOutputStream(qrcodeVO,super.getResponse().getOutputStream());
		return null;
	}
	
	//申请二维码输入界面
		public String addAllReqInput() throws Exception{
			return SUCCESS;
		}
	
	//添加整栋楼的二维码
	public String addAllBuildingQrcode() throws Exception{
//		List<CellHouseholdInfoVo> houses = null;
//		if(qrcodeVO!=null){
//			CellHouseholdInfoVo house = cellHouseholdInfoService.;
//			if(house!=null){
//				houses = cellHouseholdInfoService.find(house.getTHmBuildingCellInfo().getTHmRegionBuildingInfo().getTHmHousingDistrictRegionInfo().getHousingDistrictInfo().getId(),
//						house.getTHmBuildingCellInfo().getTHmRegionBuildingInfo().getName(), house.getTHmBuildingCellInfo().getName(), null, null);
//			}
//			
//		}
//		houses = cellHouseholdInfoService.queryListByCellId("19");
//		if(houses!=null){
//			for(CellHouseholdInfoVo houseVo : houses){
//				QrcodeVO vo = new QrcodeVO();
//				vo.setHouseId(houseVo.getId());
//				vo.setDoorSipid("3001");
//				add(vo,3,null);
//			}
//		}
//			
//		
		return SUCCESS;
	}
	
	void add(QrcodeVO qrcodeVO,Integer sipCount,Integer lockCount) throws Exception{
		if(sipCount!=null && sipCount>0){
			for(int i=0;i<sipCount;i++){
				//qrcodeVO.setStatus(Constants.QRCODE_STATUS_REQUEST);
				qrcodeVO.setStatus(Constants.QRCODE_STATUS_VALID);
				qrcodeVO.setType(Constants.QRCODE_TYPE_TALK);
				ResultEntity<SipRegister> sip = sipRegisterService.create(sipRegister);
				SipRegister sr = sip.getData();
				if(sr!=null){
					qrcodeVO.setSipUid(sr.getUsername());
					qrcodeVO.setPwd(sr.getPassword());
				}
				qrcodeService.add(qrcodeVO);
			}
		}
		if(lockCount!=null && lockCount>0){
			for(int i=0;i<sipCount;i++){
				//qrcodeVO.setStatus(Constants.QRCODE_STATUS_REQUEST);
				qrcodeVO.setStatus(Constants.QRCODE_STATUS_VALID);
				qrcodeVO.setType(Constants.QRCODE_TYPE_LOCK);
				ResultEntity<SipRegister> sip = sipRegisterService.create(sipRegister);
				SipRegister sr = sip.getData();
				if(sr!=null){
					qrcodeVO.setSipUid(sr.getUsername());
					qrcodeVO.setPwd(sr.getPassword());
				}
				qrcodeService.add(qrcodeVO);
			}
		}
	}
	
	public QrcodeVO getQrcodeVO() {
		return qrcodeVO;
	}
	public void setQrcodeVO(QrcodeVO qrcodeVO) {
		this.qrcodeVO = qrcodeVO;
	}
	public IQrcodeService getQrcodeService() {
		return qrcodeService;
	}
	public void setQrcodeService(IQrcodeService qrcodeService) {
		this.qrcodeService = qrcodeService;
	}
	public List<QrcodeVO> getQrcodeVOs() {
		return qrcodeVOs;
	}
	public void setQrcodeVOs(List<QrcodeVO> qrcodeVOs) {
		this.qrcodeVOs = qrcodeVOs;
	}

	public OutputStream getQrcodeOs() {
		return qrcodeOs;
	}
	public void setQrcodeOs(OutputStream qrcodeOs) {
		this.qrcodeOs = qrcodeOs;
	}

	public Integer getSipCount() {
		return sipCount;
	}

	public void setSipCount(Integer sipCount) {
		this.sipCount = sipCount;
	}

	public Integer getLockCount() {
		return lockCount;
	}

	public void setLockCount(Integer lockCount) {
		this.lockCount = lockCount;
	}

	public ResultEntity<String> getRe() {
		return re;
	}

	public void setRe(ResultEntity<String> re) {
		this.re = re;
	}

	public ICellHouseholdInfoService getCellHouseholdInfoService() {
		return cellHouseholdInfoService;
	}

	public void setCellHouseholdInfoService(
			ICellHouseholdInfoService cellHouseholdInfoService) {
		this.cellHouseholdInfoService = cellHouseholdInfoService;
	}

	public ISipRegisterService getSipRegisterService() {
		return sipRegisterService;
	}

	public void setSipRegisterService(ISipRegisterService sipRegisterService) {
		this.sipRegisterService = sipRegisterService;
	}

	public SipRegister getSipRegister() {
		return sipRegister;
	}

	public void setSipRegister(SipRegister sipRegister) {
		this.sipRegister = sipRegister;
	}

}
