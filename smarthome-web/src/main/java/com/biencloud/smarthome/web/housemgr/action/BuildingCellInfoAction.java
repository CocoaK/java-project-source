package com.biencloud.smarthome.web.housemgr.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.biencloud.smarthome.web.base.action.BaseAction;
import com.biencloud.smarthome.web.common.action.ActionUtils;
import com.biencloud.smarthome.web.common.util.Constants;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.housemgr.service.IBuildingCellInfoService;
import com.biencloud.smarthome.web.housemgr.service.ICellSizeInfoService;
import com.biencloud.smarthome.web.housemgr.service.IRegionBuildingInfoService;
import com.biencloud.smarthome.web.housemgr.vo.BuildingCellInfoVo;
import com.biencloud.smarthome.web.housemgr.vo.CellSizeInfoVo;
import com.biencloud.smarthome.web.housemgr.vo.HousingDistrictInfoVo;
import com.biencloud.smarthome.web.housemgr.vo.HousingDistrictRegionInfoVo;
import com.biencloud.smarthome.web.housemgr.vo.RegionBuildingInfoVo;
import com.biencloud.smarthome.web.housemgr.vo.RoomVo;

/**
 * 单元信息Action
 * 
 * @author jsun
 * @since 1.0 2012-5-15
 */
@SuppressWarnings("serial")
public class BuildingCellInfoAction extends BaseAction<BuildingCellInfoVo> {
	private String cellId;

	private BuildingCellInfoVo cell;

	private String regionId;
	private String buildingId;
	private RegionBuildingInfoVo building;
	
	/**
	 * 手动输入的多个楼宇名称
	 */
	private String[] cellNames;
	private String[] cellCodes;
	
	/**
	 * 根据单元名称模糊查询
	 */
	private String cellName;
	
	private IBuildingCellInfoService buildingCellInfoService;
	private IRegionBuildingInfoService regionBuildingInfoService;
	private ICellSizeInfoService cellSizeInfoService;

	private boolean successFlag;
	private boolean fromRemove;
	
	/**
	 * 新增/修改单元时编号是否没有重复
	 */
	private boolean notRepeat;
	
	private boolean existCellName;
	
	/**
	 * 查询单元列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String queryList() throws Exception {
		PagingVO<BuildingCellInfoVo> page = getPage();
		if(page == null)
			page = new PagingVO<BuildingCellInfoVo>();

		HousingDistrictRegionInfoVo region = new HousingDistrictRegionInfoVo();
		region.setId(regionId);
		HousingDistrictInfoVo district = new HousingDistrictInfoVo();
		district.setId(getDistrictId());
		region.setHousingDistrictInfo(district);

		RegionBuildingInfoVo building = new RegionBuildingInfoVo();
		building.setId(buildingId);
		building.setTHmHousingDistrictRegionInfo(region);

		BuildingCellInfoVo cell = new BuildingCellInfoVo();
		cell.setName(cellName);
		cell.setTHmRegionBuildingInfo(building);

		PagingVO<BuildingCellInfoVo> pagingVO = buildingCellInfoService
				.queryBuildingCellInfosForPaging(cell, page.getPageNum(),
						page.getPageSize());

		setPage(pagingVO);
		if(ActionUtils.judgContainsValue(getRequest(), "print")) return "print";
		else return SUCCESS;
	}

	/**
	 * 生成单元
	 * 
	 * @return
	 * @throws Exception
	 */
	public String generateCellsInput() throws Exception {
		building = regionBuildingInfoService.get(buildingId);
		return SUCCESS;
	}

	/**
	 * 提交需要生成的单元
	 * 
	 * @return
	 * @throws Exception
	 */
	public String generateCells() throws Exception {
		building.setId(buildingId);

		List<BuildingCellInfoVo> cells = manualGenerate(building);

		for (int i = 0, length = cells.size(); i < length; i++) {
			BuildingCellInfoVo cellInfo = buildingCellInfoService.saveOrUpdateVo(cells.get(i));
			saveDefaultCellSize(cellInfo);
		}

		successFlag = true;
		return generateCellsInput();
	}

	/**
	 * 判断单元编码是否不存在（新增情况）。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String newCodeNotRepeat() throws Exception {
		notRepeat = buildingCellInfoService.newCodeNotRepeat(padCode(cellCodes[0]), buildingId);
		return SUCCESS;
	}

	/**
	 * 判断单元编码是否不存在（更新情况）。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String updateCodeNotRepeat() throws Exception {
		notRepeat = buildingCellInfoService.updateCodeNotRepeat(cellId, padCode(cellCodes[0]), buildingId);
		return SUCCESS;
	}
	
	/**
	 * 判断单元名称是否已经存在。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String existCellName() throws Exception {
		if(buildingCellInfoService.existCellName(buildingId, cellId, cellNames[0]))
			setExistCellName(true);
		return SUCCESS;
	}

	/**
	 * 获取单元信息进行更新。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String updateInput() throws Exception {
		cell = buildingCellInfoService.get(cellId);
		return SUCCESS;
	}

	/**
	 * 更新单元信息。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String update() throws Exception {
		cell.setCode(padCode(cell.getCode()));
		buildingCellInfoService.saveOrUpdate(cell);

		setSuccessFlag(true);
		cellId = cell.getId();
		return SUCCESS;
	}

	/**
	 * 删除单元信息。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String remove() throws Exception {
		fromRemove = true;
		int result = buildingCellInfoService.remove(cellId);
		if (result == Constants.RESULT_SUCCESS)
			successFlag = true;
		else
			successFlag = false;
		return queryList();
	}
	
	
	//手工填写单元信息用于生成单元
	private List<BuildingCellInfoVo> manualGenerate(RegionBuildingInfoVo building) {
		List<BuildingCellInfoVo> cells = new ArrayList<BuildingCellInfoVo>();
		for (int i = 0, length = cellNames.length; i < length; i++) {
			BuildingCellInfoVo cell = new BuildingCellInfoVo();
			cell.setName(cellNames[i]);
			cell.setTHmRegionBuildingInfo(building);
			cell.setCode(padCode(cellCodes[i]));

			cells.add(cell);
		}
		return cells;
	}
	
	private String padCode(String cellCode) {
		// 保证编码一定是2位的数字, 数位不够的前面补0, 例如编码是1, 则补全为01
		return StringUtils.leftPad(cellCode, 2, "0");
	}
	
	//生成和保存默认户型
	public void saveDefaultCellSize(BuildingCellInfoVo cell){
		CellSizeInfoVo size = new CellSizeInfoVo();
		size.setRoom("2");
		size.setHall("1");
		size.setTHmBuildingCellInfo(cell);
		size.setPlan("/images/house/housePlan.jpg");
		size.setCreateUserId(getLoginName());
		size.setTHmBuildingCellInfo(cell);
		RoomVo room1 = new RoomVo();
		RoomVo room2 = new RoomVo();
		room1.setOrderIndex(0);
		room1.setName("房间1");
		// 将文件上传到文件服务器上, 获得相对URL地址
		room1.setPlan("/images/house/room1.jpg");
		room2.setOrderIndex(1);
		room2.setName("房间2");
		room2.setPlan("/images/house/room2.jpg");
		List<RoomVo> rooms = new ArrayList<RoomVo>();
		rooms.add(room1);
		rooms.add(room2);
		size.setRooms(rooms);
		cellSizeInfoService.saveOrUpdate(size);
	}
	
	public IBuildingCellInfoService getBuildingCellInfoService() {
		return buildingCellInfoService;
	}
	public void setBuildingCellInfoService(
			IBuildingCellInfoService buildingCellInfoService) {
		this.buildingCellInfoService = buildingCellInfoService;
	}
	
	public String getRegionId() {
		return regionId;
	}
	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}
	
	public String getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}
	
	public IRegionBuildingInfoService getRegionBuildingInfoService() {
		return regionBuildingInfoService;
	}
	public void setRegionBuildingInfoService(IRegionBuildingInfoService regionBuildingInfoService) {
		this.regionBuildingInfoService = regionBuildingInfoService;
	}
	
	public RegionBuildingInfoVo getBuilding() {
		return building;
	}
	public void setBuilding(RegionBuildingInfoVo building) {
		this.building = building;
	}
	
	public boolean isSuccessFlag() {
		return successFlag;
	}
	public void setSuccessFlag(boolean successFlag) {
		this.successFlag = successFlag;
	}
	
	public String[] getCellNames() {
		return cellNames;
	}
	public void setCellNames(String[] cellNames) {
		this.cellNames = cellNames;
	}
	
	public String getCellName() {
		return cellName;
	}
	public void setCellName(String cellName) {
		this.cellName = cellName;
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
	
	public String[] getCellCodes() {
		return cellCodes;
	}
	public void setCellCodes(String[] cellCodes) {
		this.cellCodes = cellCodes;
	}
	
	public boolean isNotRepeat() {
		return notRepeat;
	}
	public void setNotRepeat(boolean notRepeat) {
		this.notRepeat = notRepeat;
	}
	
	public boolean isExistCellName() {
		return existCellName;
	}
	public void setExistCellName(boolean existCellName) {
		this.existCellName = existCellName;
	}

	public boolean isFromRemove() {
		return fromRemove;
	}
	public void setFromRemove(boolean fromRemove) {
		this.fromRemove = fromRemove;
	}

	public ICellSizeInfoService getCellSizeInfoService() {
		return cellSizeInfoService;
	}

	public void setCellSizeInfoService(ICellSizeInfoService cellSizeInfoService) {
		this.cellSizeInfoService = cellSizeInfoService;
	}
}
