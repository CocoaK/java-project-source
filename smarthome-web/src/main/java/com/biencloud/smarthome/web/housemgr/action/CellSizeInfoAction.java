package com.biencloud.smarthome.web.housemgr.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.biencloud.smarthome.web.common.util.Constants;
import com.biencloud.smarthome.web.common.util.FileUploadUtil;
import com.biencloud.smarthome.web.common.vo.UploadResult;
import com.biencloud.smarthome.web.housemgr.service.IBuildingCellInfoService;
import com.biencloud.smarthome.web.housemgr.service.ICellHouseholdInfoService;
import com.biencloud.smarthome.web.housemgr.service.ICellSizeInfoService;
import com.biencloud.smarthome.web.housemgr.util.UploadUrlUtil;
import com.biencloud.smarthome.web.housemgr.vo.BuildingCellInfoVo;
import com.biencloud.smarthome.web.housemgr.vo.CellHouseholdInfoVo;
import com.biencloud.smarthome.web.housemgr.vo.CellSizeInfoVo;
import com.biencloud.smarthome.web.housemgr.vo.RoomVo;
import com.biencloud.smarthome.web.user.service.IOwnerUserService;
import com.biencloud.smarthome.web.user.vo.OwnerUserVO;

/**
 * 户型Action
 * 
 * @author jsun  
 * @since 1.0 2012-5-24
 */
@SuppressWarnings({"serial", "rawtypes", "unchecked"})
public class CellSizeInfoAction extends HouseMgrBaseAction<CellSizeInfoVo> {
	private static final String KEY_UPLOAD_RESULT = "isSuccess";
	private static final String KEY_UPLOAD_DOWNLOAD_PATHS = "downloadPaths";
	private static final String KEY_UPLOAD_ERROR_MSG = "errorMsg";
	
	private static final String DEFAULT_PLAN_SIZE = "2";
	
	private String cellId;
	private BuildingCellInfoVo cell;
	
	private static final String[] ROOM_COUNTS = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
	
	private String generateCount = DEFAULT_PLAN_SIZE;
	
	/**
	 * 户型有x房x厅
	 */
	private String[] roomCount;
	private String[] hallCount;

	private CellSizeInfoVo size;
	
	/**
	 * 户型图
	 */
	private File[] plan;
	private String[] planFileName;

	private String[] generateRoomCount;
	private String[] roomName;
	private File[] roomPlan;
	private String[] roomPlanFileName;

	private boolean promptFlag;
	private boolean successFlag;
	private boolean errorFlag;
	
	private boolean exceedFileSizeFlag;
	
	private List<CellSizeInfoVo> sizeList;

	private String sizeId;
	
	private CellHouseholdInfoVo house;

	private IBuildingCellInfoService buildingCellInfoService;
	private ICellSizeInfoService cellSizeInfoService;
	private UploadUrlUtil uploadUrlUtil;
	private IOwnerUserService ownerUserService;
	private ICellHouseholdInfoService cellHouseholdInfoService;
	
	/**
	 * 生成户型
	 * 
	 * @return
	 * @throws Exception
	 */
	public String generateSizeInput() throws Exception {
		cell = buildingCellInfoService.get(cellId);
		setPicExt();
		setRequestAttribute("roomCounts", ROOM_COUNTS);
		return SUCCESS;
	}

	/**
	 * 提交需要生成的户型信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String generateSize() throws Exception {
		cellId = cell.getId();
		generateCount = DEFAULT_PLAN_SIZE;
		
		List<String> roomNameList = Arrays.asList(roomName);
		
		Map<String, Object> planResult = uploadFiles(planFileName, plan);
		if(!postUploadResult(planResult))
			return generateSizeInput();
		
		Map<String, Object> roomResult = uploadFiles(roomPlanFileName, roomPlan);
		if(!postUploadResult(roomResult))
			return generateSizeInput();
		
		List<String> downloadPlanPaths = (List)planResult.get(KEY_UPLOAD_DOWNLOAD_PATHS);
		List<String> downloadRoomPaths = (List)roomResult.get(KEY_UPLOAD_DOWNLOAD_PATHS);
		
		for (int i = 0, length = roomCount.length; i < length; i++) {
			CellSizeInfoVo size = new CellSizeInfoVo();
			size.setRoom(roomCount[i]);
			size.setHall(hallCount[i]);
			size.setTHmBuildingCellInfo(cell);
			size.setPlan(downloadPlanPaths.get(i));
			size.setCreateUserId(getLoginName());
			List<RoomVo> rooms = buildRooms(i, roomNameList, downloadRoomPaths);
			size.setRooms(rooms);
			cellSizeInfoService.saveOrUpdate(size);
		}

		successFlag = true;		
		return generateSizeInput();
	}

	/**
	 * 查看单元所有户型信息
	 * 
	 * @return
	 */
	public String viewCellSize() {
		BuildingCellInfoVo cell = new BuildingCellInfoVo();
		cell.setId(cellId);
		CellSizeInfoVo size = new CellSizeInfoVo();
		size.setTHmBuildingCellInfo(cell);

		sizeList = cellSizeInfoService.find(size);
		return SUCCESS;
	}

	/**
	 * 删除户型信息。
	 * @return
	 */
	public String removeCellSize() {
		int result = cellSizeInfoService.remove(sizeId);
		if (result == Constants.RESULT_SUCCESS)
			successFlag = true;
		else
			successFlag = false;
		
		viewCellSize();
		setPromptFlag(true);
		return SUCCESS;
	}

	/**
	 * 获取户型信息进行自定义。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String customSizeInput() throws Exception {
		initHouse();
		size = house.getTHmCellSizeInfo();
		orderRooms(size.getRooms());
		generateRoomCount = new String[1];
		generateRoomCount[0] = String.valueOf(size.getRooms().size());
//		generateRoomCount[0] = String.valueOf(
//				Integer.parseInt(size.getRoom()) + Integer.parseInt(size.getHall()));		
		setPicExt();
//		setRequestAttribute("roomCounts", ROOM_COUNTS);
		return SUCCESS;
	}
	
	/**
	 * 保存自定义户型信息。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String customSize() throws Exception {
		initHouse();
		
//		UploadResult planResult = uploadFile(planFileName[0], plan[0]);
//		if(!planResult.isSuccess()){
//			addActionError(planResult.getErrorMsg());
//			setErrorFlag(true);
//			return customSizeInput();
//		}
		
		Map<String, Object> roomResult = uploadFiles(roomPlanFileName, roomPlan);
		if(!postUploadResult(roomResult))
			return customSizeInput();
		
		List<String> downloadRoomPaths = (List)roomResult.get(KEY_UPLOAD_DOWNLOAD_PATHS);
		
		List<String> roomNameList = Arrays.asList(roomName);
		
		size.setId(house.getTHmCellSizeInfo().getId());
		size.setCreateUserId(getLoginName());
		size.setTHmBuildingCellInfo(house.getTHmBuildingCellInfo());
//		size.setPlan(planResult.getDownloadRelativePath());
		List<RoomVo> rooms = buildRooms(0, roomNameList, downloadRoomPaths);
		size.setRooms(rooms);
		// 新增自定义户型
		cellSizeInfoService.saveCustomSize(size, house.getId());
		
		successFlag = true;

		return customSizeInput();
	}

	
	//获取业主的房号信息
	private void initHouse() {
		OwnerUserVO ownerUser = ownerUserService.getOwnerUserDetail(getUserId());
		house = cellHouseholdInfoService.get(ownerUser.getHouseId());			
	}

	//房间排序
	private void orderRooms(List<RoomVo> rooms){
		if(rooms == null || rooms.isEmpty())
			return;
			
		Collections.sort(rooms, 
			new Comparator<RoomVo>() {
				@Override
				public int compare(RoomVo r1, RoomVo r2) {
					return (r1.getOrderIndex() - r2.getOrderIndex());
				}
			});
	}
	
	//上传失败则设置相应的错误信息并返回上传是否成功
	private boolean postUploadResult(Map<String, Object> result){
		if(Boolean.TRUE.equals(result.get(KEY_UPLOAD_RESULT)))
			return true;
			
		addActionError((String)result.get(KEY_UPLOAD_ERROR_MSG));
		setErrorFlag(true);
		return false;
	}
		
	/*
	 * 构建房间信息。
	 */
	private List<RoomVo> buildRooms(int index, 
			List<String> roomNameList, List<String> downloadRoomPaths){
		int roomCount = Integer.parseInt(generateRoomCount[index]);
		List<RoomVo> rooms = new ArrayList<RoomVo>(roomCount);
		for (int i = 0; i < roomCount; i++) {
			RoomVo room = new RoomVo();
			room.setOrderIndex(i);
			room.setName(roomNameList.get(i));
			// 将文件上传到文件服务器上, 获得相对URL地址
			room.setPlan(downloadRoomPaths.get(i));
			rooms.add(room);
		}
		return rooms;
	}

	/*
	 * 顺序上传文件，如果有一个上传失败则直接返回失败结果，否则返回成功结果。<br/>
	 * 1、结果标志，key： "isSuccess"，value：布尔值，true表示成功，false表示失败；<br/>
	 * 2、下载路径（成功情况），key： "downloadPaths"，value：List<String>；<br/>
	 * 3、错误信息（失败失败），key： "errorMsg"，value：String类型；<br/> 
	 */
	private Map<String, Object> uploadFiles(String[] fileNames, File[] files) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		if(fileNames == null || fileNames.length < 1 
				|| files == null || files.length < 1 
				|| fileNames.length != files.length){
			result.put(KEY_UPLOAD_RESULT, false);
			result.put(KEY_UPLOAD_ERROR_MSG, getText("error.uploadfile.failure"));
			return result;
		}
		
		List<String> downloadPaths = new ArrayList<String>();
		UploadResult ur = null;
		for (int idx = 0, size = fileNames.length; idx < size; idx++) {
			String fileName = fileNames[idx];
			//拼接新文件名:xxx-1221212112.xxx
			String newFileName = FileUploadUtil.renameFileName(fileName);
			ur = uploadFile(newFileName, files[idx]);
			if(!ur.isSuccess()){
				result.put(KEY_UPLOAD_RESULT, false);
				result.put(KEY_UPLOAD_ERROR_MSG, ur.getErrorMsg());
				return result;
			}
			
			downloadPaths.add(ur.getDownloadRelativePath());
		}
		
		result.put(KEY_UPLOAD_RESULT, true);
		result.put(KEY_UPLOAD_DOWNLOAD_PATHS, downloadPaths);
		
		return result;
	}


    public String getCellId() {
		return cellId;
	}
	public void setCellId(String cellId) {
		this.cellId = cellId;
	}
	
	public BuildingCellInfoVo getCell() {
		return cell;
	}
	public void setCell(BuildingCellInfoVo cell) {
		this.cell = cell;
	}
	
	public boolean isPromptFlag() {
		return promptFlag;
	}
	public void setPromptFlag(boolean promptFlag) {
		this.promptFlag = promptFlag;
	}

	public boolean isSuccessFlag() {
		return successFlag;
	}
	public void setSuccessFlag(boolean successFlag) {
		this.successFlag = successFlag;
	}
	
	public IBuildingCellInfoService getBuildingCellInfoService() {
		return buildingCellInfoService;
	}
	public void setBuildingCellInfoService(IBuildingCellInfoService buildingCellInfoService) {
		this.buildingCellInfoService = buildingCellInfoService;
	}
	
	public String[] getRoomCount() {
		return roomCount;
	}
	public void setRoomCount(String[] roomCount) {
		this.roomCount = roomCount;
	}
	
	public String[] getHallCount() {
		return hallCount;
	}
	public void setHallCount(String[] hallCount) {
		this.hallCount = hallCount;
	}
	
	public File[] getPlan() {
		return plan;
	}
	public void setPlan(File[] plan) {
		this.plan = plan;
	}
	
	public String[] getPlanFileName() {
		return planFileName;
	}
	public void setPlanFileName(String[] planFileName) {
		this.planFileName = planFileName;
	}
	
	public ICellSizeInfoService getCellSizeInfoService() {
		return cellSizeInfoService;
	}
	public void setCellSizeInfoService(ICellSizeInfoService cellSizeInfoService) {
		this.cellSizeInfoService = cellSizeInfoService;
	}
	
	public List<CellSizeInfoVo> getSizeList() {
		return sizeList;
	}
	public void setSizeList(List<CellSizeInfoVo> sizeList) {
		this.sizeList = sizeList;
	}
	
	public String getSizeId() {
		return sizeId;
	}
	public void setSizeId(String sizeId) {
		this.sizeId = sizeId;
	}
	
	public UploadUrlUtil getUploadUrlUtil() {
		return uploadUrlUtil;
	}
	public void setUploadUrlUtil(UploadUrlUtil uploadUrlUtil) {
		this.uploadUrlUtil = uploadUrlUtil;
	}
	
	public String[] getRoomName() {
		return roomName;
	}
	public void setRoomName(String[] roomName) {
		this.roomName = roomName;
	}
	
	public File[] getRoomPlan() {
		return roomPlan;
	}
	public void setRoomPlan(File[] roomPlan) {
		this.roomPlan = roomPlan;
	}
	
	public String[] getRoomPlanFileName() {
		return roomPlanFileName;
	}
	public void setRoomPlanFileName(String[] roomPlanFileName) {
		this.roomPlanFileName = roomPlanFileName;
	}
	
	public String[] getGenerateRoomCount() {
		return generateRoomCount;
	}
	public void setGenerateRoomCount(String[] generateRoomCount) {
		this.generateRoomCount = generateRoomCount;
	}
	
	public IOwnerUserService getOwnerUserService() {
		return ownerUserService;
	}
	public void setOwnerUserService(IOwnerUserService ownerUserService) {
		this.ownerUserService = ownerUserService;
	}
	
	public CellHouseholdInfoVo getHouse() {
		return house;
	}
	public void setHouse(CellHouseholdInfoVo house) {
		this.house = house;
	}
	
	public ICellHouseholdInfoService getCellHouseholdInfoService() {
		return cellHouseholdInfoService;
	}
	public void setCellHouseholdInfoService(ICellHouseholdInfoService cellHouseholdInfoService) {
		this.cellHouseholdInfoService = cellHouseholdInfoService;
	}

	public boolean isErrorFlag() {
		return errorFlag;
	}
	public void setErrorFlag(boolean errorFlag) {
		this.errorFlag = errorFlag;
	}

	public String getGenerateCount() {
		return generateCount;
	}
	public void setGenerateCount(String generateCount) {
		this.generateCount = generateCount;
	}

	public CellSizeInfoVo getSize() {
		return size;
	}
	public void setSize(CellSizeInfoVo size) {
		this.size = size;
	}

	public boolean getExceedFileSizeFlag() {
		return exceedFileSizeFlag;
	}
	public void setExceedFileSizeFlag(boolean exceedFileSizeFlag) {
		this.exceedFileSizeFlag = exceedFileSizeFlag;
	}
}
