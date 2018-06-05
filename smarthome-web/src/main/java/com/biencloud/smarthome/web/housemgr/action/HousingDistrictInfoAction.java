package com.biencloud.smarthome.web.housemgr.action;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import com.biencloud.smarthome.web.common.util.Constants;
import com.biencloud.smarthome.web.common.util.FileUploadUtil;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.common.vo.UploadResult;
import com.biencloud.smarthome.web.housemgr.service.IHousingDistrictInfoService;
import com.biencloud.smarthome.web.housemgr.util.UploadUrlUtil;
import com.biencloud.smarthome.web.housemgr.vo.HousingDistrictInfoVo;

/**
 * 小区Action
 * 
 * @author jsun
 * @since 1.0 2012-5-14
 */
@SuppressWarnings("serial")
public class HousingDistrictInfoAction extends HouseMgrBaseAction<HousingDistrictInfoVo> {
	private String housingDistrictInfoId;
	private String housingDistrictName;
	private boolean successFlag;
	private boolean errorFlag;
	private boolean exceedFileSizeFlag;
	
	private File floorPlan;
	private String floorPlanFileName;
	
	private HousingDistrictInfoVo housingDistrictInfo;
	
	private List<HousingDistrictInfoVo> districtVos;

	private IHousingDistrictInfoService housingDistrictInfoService;
	
	private UploadUrlUtil uploadUrlUtil;

	
	/**
	 * 查询小区列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String queryList() throws Exception {
		PagingVO<HousingDistrictInfoVo> page = getPage();
		if(page == null)
			page = new PagingVO<HousingDistrictInfoVo>();

		HousingDistrictInfoVo vo = new HousingDistrictInfoVo();
		vo.setName(housingDistrictName);

		PagingVO<HousingDistrictInfoVo> pagingVO = housingDistrictInfoService
				.queryHousingDistrictInfosForPaging(vo, page.getPageNum(),
						page.getPageSize());

		setPage(pagingVO);
		return SUCCESS;
	}

	/**
	 * 查询符合条件的所有小区信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getDistricts() throws Exception {
		HousingDistrictInfoVo vo = new HousingDistrictInfoVo();
		vo.setId(getDistrictId());
		districtVos = housingDistrictInfoService.getDistricts(vo);

		return SUCCESS;
	}

	/**
	 * 根据小区名称模糊查询小区信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getDistrictsByName() throws Exception {
		HousingDistrictInfoVo vo = new HousingDistrictInfoVo();
		vo.setName(housingDistrictName);
		districtVos = housingDistrictInfoService.getDistricts(vo);

		return SUCCESS;
	}
	
	/**
	 * 查看小区详细信息。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String viewDetail() throws Exception {
		// 从登陆模块取出用户归属的小区
		housingDistrictInfoId = getDistrictId();

		if (housingDistrictInfoId != null) {
			setHousingDistrictInfo(getHousingDistrictInfoService()
					.getHousingDistrictInfo(housingDistrictInfoId));
		}
		return SUCCESS;
	}

	/**
	 * 获取小区信息进行更新。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String updateInput() throws Exception {
		HousingDistrictInfoVo hdi = getHousingDistrictInfoService().getHousingDistrictInfo(
				housingDistrictInfoId);
		
		if(errorFlag){
			if(housingDistrictInfo != null){
				hdi.setDistrict(housingDistrictInfo.getDistrict());
				hdi.setPropertyCompanyAddress(housingDistrictInfo.getPropertyCompanyAddress());
				hdi.setConstructionArea(housingDistrictInfo.getConstructionArea());
				hdi.setDeveloper(housingDistrictInfo.getDeveloper());
			}
		}
		
		setHousingDistrictInfo(hdi);
		setPicExt();
		setPlanResolution();
		return SUCCESS;
	}

	/**
	 * 更新小区信息。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String update() throws Exception {
		if (StringUtils.isBlank(housingDistrictInfo.getConstructionArea())) {
			housingDistrictInfo.setConstructionArea("0");
		}

		housingDistrictInfoId = housingDistrictInfo.getId();
		
		if (floorPlan != null && floorPlanFileName!=null) {
			//拼接新文件名:xxx-1221212112.xxx
			String newFileName = FileUploadUtil.renameFileName(floorPlanFileName);
			UploadResult result = uploadFile(newFileName, floorPlan);
			if(!result.isSuccess()){
				setErrorFlag(true);
				addFieldError("floorPlan", result.getErrorMsg());
				return updateInput();
			}
			
			housingDistrictInfo.setFloorPlan(result.getDownloadRelativePath());
		}

		// 保证编码一定是4位的数字, 数位不够的前面补0, 例如编码是1, 则补全为0001
		String padCode = StringUtils.leftPad(housingDistrictInfo.getCode(), 4, "0");
		housingDistrictInfo.setCode(padCode);
        housingDistrictInfoService.update(housingDistrictInfo);
		setSuccessFlag(true);
		return updateInput();
	}
	
	//查询位置坐标
	public String queryPosition() throws Exception{
		HousingDistrictInfoVo districtInfo = null;
		if(housingDistrictInfo!=null && StringUtils.isNotBlank(housingDistrictInfo.getCode())){
			List<HousingDistrictInfoVo> districtInfos = housingDistrictInfoService.getDistricts(housingDistrictInfo);
			if(districtInfos!=null && districtInfos.size()>0){
				districtInfo = districtInfos.get(0);
			}
		}
		housingDistrictInfo = districtInfo;
		return SUCCESS;
	}
	
	@Override
	protected UploadResult uploadFile(String fileName, File file)
			throws Exception {
		String imageFormat = getSysParamService().getParamValue(Constants.PARAM_CODE_WEB_IMAGE_FORMAT);
		String fileExt = StringUtils.substring(fileName, fileName.lastIndexOf(".") + 1);
		if(!ArrayUtils.contains(StringUtils.split(imageFormat, ','), fileExt)){
			UploadResult result = new UploadResult();
			result.setRetFlag(Constants.FILE_UPLOAD_FAILURE);
			result.setErrorMsg(getText("error.uploadfile.format"));
			return result;
		}
		
		String maxResolution = getSysParamService().getParamValue(
				Constants.PARAM_PLAN_MAX_RESOLUTION);		
		String[] widthHeight = StringUtils.split(maxResolution, ',');
		BufferedImage image = ImageIO.read(floorPlan);
		if(image.getWidth() > Integer.parseInt(widthHeight[0]) 
				|| image.getHeight() > Integer.parseInt(widthHeight[1])){
			UploadResult result = new UploadResult();
			result.setRetFlag(Constants.FILE_UPLOAD_FAILURE);
			result.setErrorMsg(getText("error.file.resolution.too.large"));
			return result;
		}
		
		return super.uploadFile(fileName, file);
	}

	
	//设置小区平面图的分辨率到请求范围里
	private void setPlanResolution() throws Exception {
		setRequestAttribute("maxResolution", StringUtils.replace(getSysParamService().getParamValue(
				Constants.PARAM_PLAN_MAX_RESOLUTION), ",", " x "));
	}
	
	
	public String getHousingDistrictInfoId() {
		return housingDistrictInfoId;
	}
	public void setHousingDistrictInfoId(String housingDistrictInfoId) {
		this.housingDistrictInfoId = housingDistrictInfoId;
	}
	
	public IHousingDistrictInfoService getHousingDistrictInfoService() {
		return housingDistrictInfoService;
	}
	public void setHousingDistrictInfoService(
			IHousingDistrictInfoService housingDistrictInfoService) {
		this.housingDistrictInfoService = housingDistrictInfoService;
	}
	
	public HousingDistrictInfoVo getHousingDistrictInfo() {
		return housingDistrictInfo;
	}
	public void setHousingDistrictInfo(HousingDistrictInfoVo housingDistrictInfo) {
		this.housingDistrictInfo = housingDistrictInfo;
	}
	
	public String getHousingDistrictName() {
		return housingDistrictName;
	}
	public void setHousingDistrictName(String housingDistrictName) {
		this.housingDistrictName = housingDistrictName;
	}
	
	public boolean isSuccessFlag() {
		return successFlag;
	}
	public void setSuccessFlag(boolean successFlag) {
		this.successFlag = successFlag;
	}
	
	public boolean isErrorFlag() {
		return errorFlag;
	}
	public void setErrorFlag(boolean errorFlag) {
		this.errorFlag = errorFlag;
	}

	public boolean isExceedFileSizeFlag() {
		return exceedFileSizeFlag;
	}
	public void setExceedFileSizeFlag(boolean exceedFileSizeFlag) {
		this.exceedFileSizeFlag = exceedFileSizeFlag;
	}

	public File getFloorPlan() {
		return floorPlan;
	}
	public void setFloorPlan(File floorPlan) {
		this.floorPlan = floorPlan;
	}
	
	public String getFloorPlanFileName() {
		return floorPlanFileName;
	}
	public void setFloorPlanFileName(String floorPlanFileName) {
		this.floorPlanFileName = floorPlanFileName;
	}
	
	public List<HousingDistrictInfoVo> getDistrictVos() {
		return districtVos;
	}
	public void setDistrictVos(List<HousingDistrictInfoVo> districtVos) {
		this.districtVos = districtVos;
	}
	
	public UploadUrlUtil getUploadUrlUtil() {
		return uploadUrlUtil;
	}
	public void setUploadUrlUtil(UploadUrlUtil uploadUrlUtil) {
		this.uploadUrlUtil = uploadUrlUtil;
	}
}
