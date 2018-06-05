package com.biencloud.smarthome.web.wsclient.stub.clientsample;

import com.biencloud.smarthome.web.wsclient.stub.*;

public class ClientSample {

	public static void main(String[] args) {
	        System.out.println("***********************");
	        System.out.println("Create Web Service Client...");
	        SmartHomePubServiceService service1 = new SmartHomePubServiceService();
	        System.out.println("Create Web Service...");
	        SmartHomePubService port1 = service1.getSmartHomePubServicePort();
	        System.out.println("Call Web Service Operation...");
	        System.out.println("Server said: " + port1.queryOwnerUnitDevice(null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryDistrictIds(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.saveInfoReceiver() is a void method!");
	        System.out.println("Server said: port1.saveChargeDetail() is a void method!");
	        System.out.println("Server said: port1.updateInfoReceiverDevice() is a void method!");
	        System.out.println("Server said: " + port1.queryPages(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.upgradeSoftware() is a void method!");
	        System.out.println("Server said: port1.updatePage() is a void method!");
	        System.out.println("Server said: " + port1.statisticsCharge(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.saveGateCardVisit() is a void method!");
	        System.out.println("Server said: " + port1.existIdCardForPaUser(null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.updateDistrictPropertyCompanyId() is a void method!");
	        System.out.println("Server said: " + port1.queryChargeCalModeForList(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryPushFinishForPaging(Integer.parseInt(args[0]),Integer.parseInt(args[1]),null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.updateOnlineFlag() is a void method!");
	        System.out.println("Server said: " + port1.queryGateCardsForPaging(null,Integer.parseInt(args[2]),Integer.parseInt(args[3])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryAreaData(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.querySceneDeviceByDeviceNoAndSceneId(null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.getSceneByRoomNo(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.stopAd() is a void method!");
	        System.out.println("Server said: " + port1.queryChargeTypeForPaging(null,Integer.parseInt(args[4]),Integer.parseInt(args[5])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryAddressBookForPaging(Integer.parseInt(args[6]),Integer.parseInt(args[7]),null,null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.getDistrictIdByGroupNo(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.addOwnerUser() is a void method!");
	        System.out.println("Server said: " + port1.queryPushForPagingByKeyValue(Integer.parseInt(args[8]),Integer.parseInt(args[9]),null,null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryLocalHdfsById(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.saveOrUpdateBuildingCellInfo() is a void method!");
	        System.out.println("Server said: port1.saveChargeMonetaryUnit() is a void method!");
	        System.out.println("Server said: " + port1.getGateCardVisitDetail(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.getOwnerUserDetail(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.querySceneDeviceByDeviceNo(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.hasHouse(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.updateStatus() is a void method!");
	        System.out.println("Server said: port1.deletePageContentById() is a void method!");
	        System.out.println("Server said: " + port1.countUsersByOnlineFlag(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryInfoReceiverForList(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.getScenes(null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.getBuildings(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryPushFinishForPagingByKeyValue(Integer.parseInt(args[10]),Integer.parseInt(args[11]),null,null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryInfoReceiverForPaging(null,Integer.parseInt(args[12]),Integer.parseInt(args[13])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.getSceneByDeviceNo(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.updateHouseSizeId() is a void method!");
	        System.out.println("Server said: " + port1.queryCallRecordForPaging(null,Integer.parseInt(args[14]),Integer.parseInt(args[15])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.updateGateCard() is a void method!");
	        System.out.println("Server said: " + port1.queryInfoReceiverDeviceForList(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryChargeCalUnitForPaging(null,Integer.parseInt(args[16]),Integer.parseInt(args[17])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.saveDevicePassword() is a void method!");
	        System.out.println("Server said: " + port1.queryAllAdSystems());
	        System.out.println("Server said: port1.updateSaUser() is a void method!");
	        System.out.println("Server said: " + port1.getSystemParamDetail(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryAdsForPaging(null,Integer.parseInt(args[18]),Integer.parseInt(args[19])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryChargeDataForList(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryRequestRepairForPaging(null,Integer.parseInt(args[20]),Integer.parseInt(args[21])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.saveChargeType() is a void method!");
	        System.out.println("Server said: " + port1.getNoReadReceiverCount(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryChargeCalModeForPaging(null,Integer.parseInt(args[22]),Integer.parseInt(args[23])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryAllAdTypes());
	        System.out.println("Server said: " + port1.saveCustomSize(null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.getInfoReceiverDevice(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.getGateCardDetail(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.getChargeCalMode(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryAlarmForPaging(null,Integer.parseInt(args[24]),Integer.parseInt(args[25])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.listAllAddressBookByDeviceNo(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.removeCallRecord() is a void method!");
	        System.out.println("Server said: " + port1.existHouseName(null,null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryGateCardByCardNo(null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryAlarmType(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.findCellSizeInfo(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryPageContent(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryInfoReceiverForIndex(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.publishTimingSoftware() is a void method!");
	        System.out.println("Server said: port1.updateChargeCalMode() is a void method!");
	        System.out.println("Server said: " + port1.queryChargeMonetaryUnitForPaging(null,Integer.parseInt(args[26]),Integer.parseInt(args[27])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.saveHousingDistrictRegionInfo() is a void method!");
	        System.out.println("Server said: " + port1.newHouseCodeNotRepeat(null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.publishChargeInfo(null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryInfoSendForPaging(null,Integer.parseInt(args[28]),Integer.parseInt(args[29])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.getAlarm(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.updateCellCodeNotRepeat(null,null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.removeDeviceById(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryMonitorDevice(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryWeatherById(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.saveOrUpdateRegionBuildingInfo() is a void method!");
	        System.out.println("Server said: " + port1.callRecordCount(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryDeviceByCode(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.savePropertyCompanyInfo(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.getCellHouseholdInfo(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryOperationLogForPaging(null,null,null,null,null,Integer.parseInt(args[30]),Integer.parseInt(args[31])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryMenusForPaging(null,Boolean.parseBoolean(args[32]),null,Integer.parseInt(args[33]),Integer.parseInt(args[34])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.getCompletePosition(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.getSceneByDeviceNoAndMode(null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryRssServerForPaging(null,Integer.parseInt(args[35]),Integer.parseInt(args[36])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.saveOrUpdateCellHouseholdInfo() is a void method!");
	        System.out.println("Server said: " + port1.deletePushByEntity(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.getHousingDistrictRegionInfo(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryPageForPaging(null,Integer.parseInt(args[37]),Integer.parseInt(args[38])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.hasRegion(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryChargeMonetaryUnitByParams(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.saveComponent() is a void method!");
	        System.out.println("Server said: " + port1.remoteUnlock(null,null,null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.addOwnerUsers() is a void method!");
	        System.out.println("Server said: " + port1.getCallRecordById(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.getRequestRepairCount(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.deleteSceneByDeviceNoAndSceneId(null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.addSaUser() is a void method!");
	        System.out.println("Server said: " + port1.queryFamilyDevice(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryLocalHdfsForPageByKeyValue(Integer.parseInt(args[39]),Integer.parseInt(args[40]),null,null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.delAlarm() is a void method!");
	        System.out.println("Server said: " + port1.existIdCardForOwnerUser(null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.saveRequestRepair() is a void method!");
	        System.out.println("Server said: port1.removeSoftware() is a void method!");
	        System.out.println("Server said: " + port1.existBuildingName(null,null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.getChargeMonetaryUnit(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.updateChargeTypeResult() is a void method!");
	        System.out.println("Server said: port1.removeAlarmType() is a void method!");
	        System.out.println("Server said: " + port1.findPropertyCompanyInfos(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryHousingDistrictRegionInfosForPaging(null,Integer.parseInt(args[41]),Integer.parseInt(args[42])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.getIsUsedSceneByRoomNo(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.addRole() is a void method!");
	        System.out.println("Server said: " + port1.getPropertyCompanyInfo(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.insertPush(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.saveSubnetIps(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryDeviceIpForPaging(null,Integer.parseInt(args[43]),Integer.parseInt(args[44])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryChargeCalUnitByParams(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryChargeDetailForPaging(null,Integer.parseInt(args[45]),Integer.parseInt(args[46])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.getDistricts(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryCallRecords(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryDiviceRegeditLogForPaging(null,Integer.parseInt(args[47]),Integer.parseInt(args[48])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.updateHousingDistrictRegionInfo() is a void method!");
	        System.out.println("Server said: " + port1.getChargeDetail(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryGateCardByDeviceCode(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.getOwnerAlarmCount(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.isHaveDevice(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.getRegions(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.getOperationLogById(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.isDeviceOnline(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.saveOrUpdateRegion(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.updateGateCardStatus() is a void method!");
	        System.out.println("Server said: " + port1.saveOrUpdateSceneDevice(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryDeviceIpByIp(null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.updateChargeData() is a void method!");
	        System.out.println("Server said: " + port1.getAdDetail(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryComplaintCount(null,Boolean.parseBoolean(args[49])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.querySaUsersForPaging(null,Integer.parseInt(args[50]),Integer.parseInt(args[51])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryAllDeviceTypes());
	        System.out.println("Server said: " + port1.getChargeData(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.sendSceneDeviceMonitorCommand(null,null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryHdfsTaskForPage(Integer.parseInt(args[52]),Integer.parseInt(args[53]),null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryDeviceNoForPaging(null,Integer.parseInt(args[54]),Integer.parseInt(args[55])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.getIdCardVisitDetail(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryMenusByRole(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.removeOwnerComplaint() is a void method!");
	        System.out.println("Server said: " + port1.queryComponentById(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.addPaUser() is a void method!");
	        System.out.println("Server said: " + port1.removeCellSizeById(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.getSceneDeviceByDeviceNo(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.getCityNameByDistrictId(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryHdfsTaskById(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.removeHouseById(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.removeBuildingById(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryRecentComplaint(null,Integer.parseInt(args[56]),Boolean.parseBoolean(args[57])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.getRoleDetail(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryAppDataLogForPaging(null,Integer.parseInt(args[58]),Integer.parseInt(args[59])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.getComplaint(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.removeGateCardVisit() is a void method!");
	        System.out.println("Server said: " + port1.getSaUserDetail(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.updateChargeType() is a void method!");
	        System.out.println("Server said: " + port1.getChargeCalUnit(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.getChargeType(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.updateMenu() is a void method!");
	        System.out.println("Server said: " + port1.queryDeviceByRoomNo(null,null,null,null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryOwnerComplaintForPaging(null,null,null,Integer.parseInt(args[60]),Integer.parseInt(args[61]),Boolean.parseBoolean(args[62])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.addAd() is a void method!");
	        System.out.println("Server said: " + port1.existCardNo(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.saveOrUpdateSystemGroup(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryChargeTypeResultForPaging(null,Integer.parseInt(args[63]),Integer.parseInt(args[64])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.deleteComponent() is a void method!");
	        System.out.println("Server said: port1.saveDiviceRegeditLog() is a void method!");
	        System.out.println("Server said: " + port1.deleteSystemGroupById(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryChargeTypeResultForList(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryInfoSendForIndex(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.delChargeCalMode() is a void method!");
	        System.out.println("Server said: " + port1.getInfoReceiver(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryRssServerById(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.countUsers(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.removeGateCard() is a void method!");
	        System.out.println("Server said: " + port1.queryOwnerUsersForPaging(null,Integer.parseInt(args[65]),Integer.parseInt(args[66])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.findPushFinishById(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.updateMenuStatus() is a void method!");
	        System.out.println("Server said: port1.updateOwnerUser() is a void method!");
	        System.out.println("Server said: " + port1.deviceCount(null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryWeatherForPaging(null,Integer.parseInt(args[67]),Integer.parseInt(args[68])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.saveChargeCalUnit() is a void method!");
	        System.out.println("Server said: " + port1.updateRegionCodeNotRepeat(null,null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.getRequestRepair(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryFileUploadLogForPaging(null,Integer.parseInt(args[69]),Integer.parseInt(args[70])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.saveInfoSend() is a void method!");
	        System.out.println("Server said: port1.updateChargeMonetaryUnit() is a void method!");
	        System.out.println("Server said: " + port1.getHousingDistrictInfo(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryPropertyComplaintForPaging(null,null,null,Integer.parseInt(args[71]),Integer.parseInt(args[72]),Boolean.parseBoolean(args[73])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.delChargeCalUnit() is a void method!");
	        System.out.println("Server said: " + port1.queryOperationLogPaging(null,Integer.parseInt(args[74]),Integer.parseInt(args[75])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.getSystemLogById(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.newCellCodeNotRepeat(null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.getAppDataLogById(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryReportDataByCityName(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.saveHousingDistrictInfo() is a void method!");
	        System.out.println("Server said: " + port1.queryPageById(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.getPaUserDetail(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryFileUploadLogForList(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.existRoleName(null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.newBuildingCodeNotRepeat(null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryInfoReceiverDeviceForPaging(null,Integer.parseInt(args[76]),Integer.parseInt(args[77])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryMenus(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.deletePushFinishById(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.updateChargeDetail() is a void method!");
	        System.out.println("Server said: " + port1.queryChargeTypeForList(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.updateHousingDistrictInfo() is a void method!");
	        System.out.println("Server said: port1.saveOrUpdateIdCardVisit() is a void method!");
	        System.out.println("Server said: " + port1.querySystemGroup(Integer.parseInt(args[78]),Integer.parseInt(args[79]),null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.removeAd() is a void method!");
	        System.out.println("Server said: " + port1.saveDevice(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.newRegionCodeNotRepeat(null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryPasswordByTargetDeviceNo(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.saveOrUpdateCellSizeInfo() is a void method!");
	        System.out.println("Server said: port1.delChargeMonetaryUnit() is a void method!");
	        System.out.println("Server said: " + port1.queryDeviceByRoomId(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.saveOrUpdateAlarm() is a void method!");
	        System.out.println("Server said: " + port1.getDiviceRegeditLog(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryIdCardVisitsForPaging(null,Integer.parseInt(args[80]),Integer.parseInt(args[81])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryRequestRepairForList(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryHousingDistrictInfosForPaging(null,Integer.parseInt(args[82]),Integer.parseInt(args[83])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.saveOrUpdateOperationLog() is a void method!");
	        System.out.println("Server said: " + port1.deletePushFinishByEntity(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.resetPassword() is a void method!");
	        System.out.println("Server said: " + port1.hasBuilding(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.updateChargeCalUnit() is a void method!");
	        System.out.println("Server said: port1.publishAd() is a void method!");
	        System.out.println("Server said: " + port1.queryDevices(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.saveCallRecord() is a void method!");
	        System.out.println("Server said: " + port1.queryChargeDetailForList(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.existIdCardForSaUser(null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.updateWeather() is a void method!");
	        System.out.println("Server said: " + port1.queryBuildingCellInfosForPaging(null,Integer.parseInt(args[84]),Integer.parseInt(args[85])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.saveInfoReceiverDevice() is a void method!");
	        System.out.println("Server said: " + port1.queryChargeCalModeByParams(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.saveOrUpdateAdressBook(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.addOwnerComplaint() is a void method!");
	        System.out.println("Server said: " + port1.queryDeviceById(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.removeRegionById(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.updateAd() is a void method!");
	        System.out.println("Server said: port1.updateRssServer() is a void method!");
	        System.out.println("Server said: " + port1.existRegionName(null,null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryAlarms(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.saveChargeTypeResult() is a void method!");
	        System.out.println("Server said: port1.updateComponent() is a void method!");
	        System.out.println("Server said: port1.saveOrUpdateAlarmType() is a void method!");
	        System.out.println("Server said: port1.addSoftware() is a void method!");
	        System.out.println("Server said: " + port1.deleteAdressBookById(null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.updatePassword() is a void method!");
	        System.out.println("Server said: " + port1.getFileUploadLog(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.existCellName(null,null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.updateHouseCodeNotRepeat(null,null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.getMenuDetail(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.saveChargeCalMode() is a void method!");
	        System.out.println("Server said: " + port1.hasCell(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.updateRole() is a void method!");
	        System.out.println("Server said: port1.saveAndPushDevicePassword() is a void method!");
	        System.out.println("Server said: port1.updateGateCardVisit() is a void method!");
	        System.out.println("Server said: " + port1.queryDevicesList(null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryIdCardVisits(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryLocalHdfsForPage(Integer.parseInt(args[86]),Integer.parseInt(args[87]),null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryAllGateCardVisits(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.removeComplaint() is a void method!");
	        System.out.println("Server said: port1.saveChargeData() is a void method!");
	        System.out.println("Server said: port1.delChargeData() is a void method!");
	        System.out.println("Server said: " + port1.getInfoSend(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryChargeMonetaryUnitForList(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.updateDevice(null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryClientLogById(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.removeIdCardVisit() is a void method!");
	        System.out.println("Server said: " + port1.queryInfoSendForList(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.sendProtectionAndRemovalMonitorCommand(null,null,null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.getAppDownloadAbsoluteUrl());
	        System.out.println("Server said: " + port1.getInfoCount(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.getLatestSoftware(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryCellHouseholdInfosForPaging(null,Integer.parseInt(args[88]),Integer.parseInt(args[89])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.querySystemLogForPaging(null,null,null,null,Integer.parseInt(args[90]),Integer.parseInt(args[91])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.resumeAd() is a void method!");
	        System.out.println("Server said: port1.notifyUpgradeSoftware() is a void method!");
	        System.out.println("Server said: " + port1.queryPushForPaging(Integer.parseInt(args[92]),Integer.parseInt(args[93]),null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.getLoginByLoginName(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.savePage() is a void method!");
	        System.out.println("Server said: port1.updatePropertyCompanyInfo() is a void method!");
	        System.out.println("Server said: " + port1.queryAdTargets(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryPropertyCompanyInfosForPaging(null,Integer.parseInt(args[94]),Integer.parseInt(args[95])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryPaUsersForPaging(null,Integer.parseInt(args[96]),Integer.parseInt(args[97])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.delInfoSend() is a void method!");
	        System.out.println("Server said: " + port1.getOwnerUserByHouseId(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryOwnerUnlockDevice(null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.getRegionBuildingInfo(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryRoles(null,null,null,null,Boolean.parseBoolean(args[98])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.delRequestRepair() is a void method!");
	        System.out.println("Server said: " + port1.getPropertyCompanyInfoByDeviceCode(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.delChargeType() is a void method!");
	        System.out.println("Server said: " + port1.removeCellById(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.getWebDownloadAbsoluteUrl());
	        System.out.println("Server said: " + port1.getExternalFileServerUrl());
	        System.out.println("Server said: " + port1.queryGateCardVisitsForPaging(null,Integer.parseInt(args[99]),Integer.parseInt(args[100])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.deleteAllPageContent() is a void method!");
	        System.out.println("Server said: port1.updateInfoSend() is a void method!");
	        System.out.println("Server said: port1.saveAlarm() is a void method!");
	        System.out.println("Server said: " + port1.sendSceneMonitorCommand(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.updateAlarm() is a void method!");
	        System.out.println("Server said: " + port1.logon(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.getBuildingCellInfo(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryRegionBuildingInfosForPaging(null,Integer.parseInt(args[101]),Integer.parseInt(args[102])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryRoomByDeviceNo(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.deleteSceneDeviceByDeviceNoAndDeviceId(null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryComplaintByDeviceCode(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.generatorDeviceNo());
	        System.out.println("Server said: " + port1.queryDevicePassword(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryChargeDataForPaging(null,Integer.parseInt(args[103]),Integer.parseInt(args[104])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.saveOrUpdateSystemLog() is a void method!");
	        System.out.println("Server said: port1.delDevicePassword() is a void method!");
	        System.out.println("Server said: port1.updateInfoReceiver() is a void method!");
	        System.out.println("Server said: " + port1.getChargeTypeResult(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.updateDeviceId(null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryAdLocations(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.savePageContent() is a void method!");
	        System.out.println("Server said: " + port1.getIsUsedSceneByDeviceNo(null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryHdfsTaskForPageByKeyValue(Integer.parseInt(args[105]),Integer.parseInt(args[106]),null,null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.getSoftwareDetail(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.findPushById(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.updateBuildingCodeNotRepeat(null,null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.addPropertyComplaint() is a void method!");
	        System.out.println("Server said: " + port1.getDistrictCount());
	        System.out.println("Server said: " + port1.queryDiviceRegeditLogForList(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.addGateCard() is a void method!");
	        System.out.println("Server said: " + port1.queryGateCardDevices(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.saveOrUpdateAppDataLog(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.updateRequestRepair() is a void method!");
	        System.out.println("Server said: " + port1.queryComponents(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryDevicePasswordById(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.delChargeTypeResult() is a void method!");
	        System.out.println("Server said: port1.delInfoReceiver() is a void method!");
	        System.out.println("Server said: port1.updateSystemParam() is a void method!");
	        System.out.println("Server said: port1.updatePaUser() is a void method!");
	        System.out.println("Server said: " + port1.queryRolesForPaging(null,null,null,null,Integer.parseInt(args[107]),Integer.parseInt(args[108])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryClientLogForPaging(Integer.parseInt(args[109]),Integer.parseInt(args[110]),null,null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.publishTimingAd() is a void method!");
	        System.out.println("Server said: port1.updateCellHouseholdInfoSomeProperty() is a void method!");
	        System.out.println("Server said: " + port1.querySystemParamsForPaging(null,Integer.parseInt(args[111]),Integer.parseInt(args[112])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.publishSoftware() is a void method!");
	        System.out.println("Server said: " + port1.queryPropertyDevice(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryAlarmTypeById(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryComponentForPaging(null,Integer.parseInt(args[113]),Integer.parseInt(args[114])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.approveSoftware() is a void method!");
	        System.out.println("Server said: port1.deletePage() is a void method!");
	        System.out.println("Server said: port1.delChargeDetail() is a void method!");
	        System.out.println("Server said: " + port1.queryAlarmById(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.deletePushById(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.updateComplaint() is a void method!");
	        System.out.println("Server said: port1.replySuggestion() is a void method!");
	        System.out.println("Server said: " + port1.getFileServerUrl());
	        System.out.println("Server said: port1.removeAlarm() is a void method!");
	        System.out.println("Server said: " + port1.saveOrUpdateScene(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port1.delInfoReceiverDevice() is a void method!");
	        System.out.println("Server said: port1.removeRssServer() is a void method!");
	        System.out.println("Server said: " + port1.querySoftwaresForPaging(null,null,null,Integer.parseInt(args[115]),Integer.parseInt(args[116])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.sendGetSceneCommand(null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryChargeCalUnitForList(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.findHouseholds(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryChargeTypeByParams(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.queryDeviceForPaging(null,Integer.parseInt(args[117]),Integer.parseInt(args[118])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Create Web Service...");
	        SmartHomePubService port2 = service1.getSmartHomePubServicePort();
	        System.out.println("Call Web Service Operation...");
	        System.out.println("Server said: " + port2.queryOwnerUnitDevice(null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryDistrictIds(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.saveInfoReceiver() is a void method!");
	        System.out.println("Server said: port2.saveChargeDetail() is a void method!");
	        System.out.println("Server said: port2.updateInfoReceiverDevice() is a void method!");
	        System.out.println("Server said: " + port2.queryPages(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.upgradeSoftware() is a void method!");
	        System.out.println("Server said: port2.updatePage() is a void method!");
	        System.out.println("Server said: " + port2.statisticsCharge(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.saveGateCardVisit() is a void method!");
	        System.out.println("Server said: " + port2.existIdCardForPaUser(null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.updateDistrictPropertyCompanyId() is a void method!");
	        System.out.println("Server said: " + port2.queryChargeCalModeForList(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryPushFinishForPaging(Integer.parseInt(args[119]),Integer.parseInt(args[120]),null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.updateOnlineFlag() is a void method!");
	        System.out.println("Server said: " + port2.queryGateCardsForPaging(null,Integer.parseInt(args[121]),Integer.parseInt(args[122])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryAreaData(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.querySceneDeviceByDeviceNoAndSceneId(null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.getSceneByRoomNo(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.stopAd() is a void method!");
	        System.out.println("Server said: " + port2.queryChargeTypeForPaging(null,Integer.parseInt(args[123]),Integer.parseInt(args[124])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryAddressBookForPaging(Integer.parseInt(args[125]),Integer.parseInt(args[126]),null,null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.getDistrictIdByGroupNo(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.addOwnerUser() is a void method!");
	        System.out.println("Server said: " + port2.queryPushForPagingByKeyValue(Integer.parseInt(args[127]),Integer.parseInt(args[128]),null,null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryLocalHdfsById(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.saveOrUpdateBuildingCellInfo() is a void method!");
	        System.out.println("Server said: port2.saveChargeMonetaryUnit() is a void method!");
	        System.out.println("Server said: " + port2.getGateCardVisitDetail(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.getOwnerUserDetail(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.querySceneDeviceByDeviceNo(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.hasHouse(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.updateStatus() is a void method!");
	        System.out.println("Server said: port2.deletePageContentById() is a void method!");
	        System.out.println("Server said: " + port2.countUsersByOnlineFlag(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryInfoReceiverForList(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.getScenes(null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.getBuildings(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryPushFinishForPagingByKeyValue(Integer.parseInt(args[129]),Integer.parseInt(args[130]),null,null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryInfoReceiverForPaging(null,Integer.parseInt(args[131]),Integer.parseInt(args[132])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.getSceneByDeviceNo(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.updateHouseSizeId() is a void method!");
	        System.out.println("Server said: " + port2.queryCallRecordForPaging(null,Integer.parseInt(args[133]),Integer.parseInt(args[134])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.updateGateCard() is a void method!");
	        System.out.println("Server said: " + port2.queryInfoReceiverDeviceForList(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryChargeCalUnitForPaging(null,Integer.parseInt(args[135]),Integer.parseInt(args[136])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.saveDevicePassword() is a void method!");
	        System.out.println("Server said: " + port2.queryAllAdSystems());
	        System.out.println("Server said: port2.updateSaUser() is a void method!");
	        System.out.println("Server said: " + port2.getSystemParamDetail(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryAdsForPaging(null,Integer.parseInt(args[137]),Integer.parseInt(args[138])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryChargeDataForList(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryRequestRepairForPaging(null,Integer.parseInt(args[139]),Integer.parseInt(args[140])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.saveChargeType() is a void method!");
	        System.out.println("Server said: " + port2.getNoReadReceiverCount(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryChargeCalModeForPaging(null,Integer.parseInt(args[141]),Integer.parseInt(args[142])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryAllAdTypes());
	        System.out.println("Server said: " + port2.saveCustomSize(null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.getInfoReceiverDevice(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.getGateCardDetail(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.getChargeCalMode(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryAlarmForPaging(null,Integer.parseInt(args[143]),Integer.parseInt(args[144])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.listAllAddressBookByDeviceNo(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.removeCallRecord() is a void method!");
	        System.out.println("Server said: " + port2.existHouseName(null,null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryGateCardByCardNo(null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryAlarmType(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.findCellSizeInfo(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryPageContent(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryInfoReceiverForIndex(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.publishTimingSoftware() is a void method!");
	        System.out.println("Server said: port2.updateChargeCalMode() is a void method!");
	        System.out.println("Server said: " + port2.queryChargeMonetaryUnitForPaging(null,Integer.parseInt(args[145]),Integer.parseInt(args[146])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.saveHousingDistrictRegionInfo() is a void method!");
	        System.out.println("Server said: " + port2.newHouseCodeNotRepeat(null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.publishChargeInfo(null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryInfoSendForPaging(null,Integer.parseInt(args[147]),Integer.parseInt(args[148])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.getAlarm(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.updateCellCodeNotRepeat(null,null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.removeDeviceById(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryMonitorDevice(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryWeatherById(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.saveOrUpdateRegionBuildingInfo() is a void method!");
	        System.out.println("Server said: " + port2.callRecordCount(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryDeviceByCode(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.savePropertyCompanyInfo(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.getCellHouseholdInfo(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryOperationLogForPaging(null,null,null,null,null,Integer.parseInt(args[149]),Integer.parseInt(args[150])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryMenusForPaging(null,Boolean.parseBoolean(args[151]),null,Integer.parseInt(args[152]),Integer.parseInt(args[153])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.getCompletePosition(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.getSceneByDeviceNoAndMode(null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryRssServerForPaging(null,Integer.parseInt(args[154]),Integer.parseInt(args[155])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.saveOrUpdateCellHouseholdInfo() is a void method!");
	        System.out.println("Server said: " + port2.deletePushByEntity(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.getHousingDistrictRegionInfo(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryPageForPaging(null,Integer.parseInt(args[156]),Integer.parseInt(args[157])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.hasRegion(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryChargeMonetaryUnitByParams(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.saveComponent() is a void method!");
	        System.out.println("Server said: " + port2.remoteUnlock(null,null,null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.addOwnerUsers() is a void method!");
	        System.out.println("Server said: " + port2.getCallRecordById(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.getRequestRepairCount(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.deleteSceneByDeviceNoAndSceneId(null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.addSaUser() is a void method!");
	        System.out.println("Server said: " + port2.queryFamilyDevice(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryLocalHdfsForPageByKeyValue(Integer.parseInt(args[158]),Integer.parseInt(args[159]),null,null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.delAlarm() is a void method!");
	        System.out.println("Server said: " + port2.existIdCardForOwnerUser(null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.saveRequestRepair() is a void method!");
	        System.out.println("Server said: port2.removeSoftware() is a void method!");
	        System.out.println("Server said: " + port2.existBuildingName(null,null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.getChargeMonetaryUnit(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.updateChargeTypeResult() is a void method!");
	        System.out.println("Server said: port2.removeAlarmType() is a void method!");
	        System.out.println("Server said: " + port2.findPropertyCompanyInfos(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryHousingDistrictRegionInfosForPaging(null,Integer.parseInt(args[160]),Integer.parseInt(args[161])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.getIsUsedSceneByRoomNo(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.addRole() is a void method!");
	        System.out.println("Server said: " + port2.getPropertyCompanyInfo(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.insertPush(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.saveSubnetIps(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryDeviceIpForPaging(null,Integer.parseInt(args[162]),Integer.parseInt(args[163])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryChargeCalUnitByParams(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryChargeDetailForPaging(null,Integer.parseInt(args[164]),Integer.parseInt(args[165])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.getDistricts(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryCallRecords(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryDiviceRegeditLogForPaging(null,Integer.parseInt(args[166]),Integer.parseInt(args[167])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.updateHousingDistrictRegionInfo() is a void method!");
	        System.out.println("Server said: " + port2.getChargeDetail(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryGateCardByDeviceCode(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.getOwnerAlarmCount(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.isHaveDevice(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.getRegions(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.getOperationLogById(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.isDeviceOnline(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.saveOrUpdateRegion(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.updateGateCardStatus() is a void method!");
	        System.out.println("Server said: " + port2.saveOrUpdateSceneDevice(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryDeviceIpByIp(null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.updateChargeData() is a void method!");
	        System.out.println("Server said: " + port2.getAdDetail(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryComplaintCount(null,Boolean.parseBoolean(args[168])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.querySaUsersForPaging(null,Integer.parseInt(args[169]),Integer.parseInt(args[170])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryAllDeviceTypes());
	        System.out.println("Server said: " + port2.getChargeData(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.sendSceneDeviceMonitorCommand(null,null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryHdfsTaskForPage(Integer.parseInt(args[171]),Integer.parseInt(args[172]),null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryDeviceNoForPaging(null,Integer.parseInt(args[173]),Integer.parseInt(args[174])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.getIdCardVisitDetail(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryMenusByRole(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.removeOwnerComplaint() is a void method!");
	        System.out.println("Server said: " + port2.queryComponentById(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.addPaUser() is a void method!");
	        System.out.println("Server said: " + port2.removeCellSizeById(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.getSceneDeviceByDeviceNo(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.getCityNameByDistrictId(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryHdfsTaskById(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.removeHouseById(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.removeBuildingById(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryRecentComplaint(null,Integer.parseInt(args[175]),Boolean.parseBoolean(args[176])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.getRoleDetail(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryAppDataLogForPaging(null,Integer.parseInt(args[177]),Integer.parseInt(args[178])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.getComplaint(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.removeGateCardVisit() is a void method!");
	        System.out.println("Server said: " + port2.getSaUserDetail(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.updateChargeType() is a void method!");
	        System.out.println("Server said: " + port2.getChargeCalUnit(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.getChargeType(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.updateMenu() is a void method!");
	        System.out.println("Server said: " + port2.queryDeviceByRoomNo(null,null,null,null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryOwnerComplaintForPaging(null,null,null,Integer.parseInt(args[179]),Integer.parseInt(args[180]),Boolean.parseBoolean(args[181])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.addAd() is a void method!");
	        System.out.println("Server said: " + port2.existCardNo(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.saveOrUpdateSystemGroup(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryChargeTypeResultForPaging(null,Integer.parseInt(args[182]),Integer.parseInt(args[183])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.deleteComponent() is a void method!");
	        System.out.println("Server said: port2.saveDiviceRegeditLog() is a void method!");
	        System.out.println("Server said: " + port2.deleteSystemGroupById(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryChargeTypeResultForList(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryInfoSendForIndex(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.delChargeCalMode() is a void method!");
	        System.out.println("Server said: " + port2.getInfoReceiver(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryRssServerById(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.countUsers(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.removeGateCard() is a void method!");
	        System.out.println("Server said: " + port2.queryOwnerUsersForPaging(null,Integer.parseInt(args[184]),Integer.parseInt(args[185])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.findPushFinishById(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.updateMenuStatus() is a void method!");
	        System.out.println("Server said: port2.updateOwnerUser() is a void method!");
	        System.out.println("Server said: " + port2.deviceCount(null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryWeatherForPaging(null,Integer.parseInt(args[186]),Integer.parseInt(args[187])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.saveChargeCalUnit() is a void method!");
	        System.out.println("Server said: " + port2.updateRegionCodeNotRepeat(null,null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.getRequestRepair(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryFileUploadLogForPaging(null,Integer.parseInt(args[188]),Integer.parseInt(args[189])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.saveInfoSend() is a void method!");
	        System.out.println("Server said: port2.updateChargeMonetaryUnit() is a void method!");
	        System.out.println("Server said: " + port2.getHousingDistrictInfo(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryPropertyComplaintForPaging(null,null,null,Integer.parseInt(args[190]),Integer.parseInt(args[191]),Boolean.parseBoolean(args[192])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.delChargeCalUnit() is a void method!");
	        System.out.println("Server said: " + port2.queryOperationLogPaging(null,Integer.parseInt(args[193]),Integer.parseInt(args[194])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.getSystemLogById(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.newCellCodeNotRepeat(null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.getAppDataLogById(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryReportDataByCityName(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.saveHousingDistrictInfo() is a void method!");
	        System.out.println("Server said: " + port2.queryPageById(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.getPaUserDetail(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryFileUploadLogForList(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.existRoleName(null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.newBuildingCodeNotRepeat(null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryInfoReceiverDeviceForPaging(null,Integer.parseInt(args[195]),Integer.parseInt(args[196])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryMenus(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.deletePushFinishById(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.updateChargeDetail() is a void method!");
	        System.out.println("Server said: " + port2.queryChargeTypeForList(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.updateHousingDistrictInfo() is a void method!");
	        System.out.println("Server said: port2.saveOrUpdateIdCardVisit() is a void method!");
	        System.out.println("Server said: " + port2.querySystemGroup(Integer.parseInt(args[197]),Integer.parseInt(args[198]),null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.removeAd() is a void method!");
	        System.out.println("Server said: " + port2.saveDevice(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.newRegionCodeNotRepeat(null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryPasswordByTargetDeviceNo(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.saveOrUpdateCellSizeInfo() is a void method!");
	        System.out.println("Server said: port2.delChargeMonetaryUnit() is a void method!");
	        System.out.println("Server said: " + port2.queryDeviceByRoomId(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.saveOrUpdateAlarm() is a void method!");
	        System.out.println("Server said: " + port2.getDiviceRegeditLog(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryIdCardVisitsForPaging(null,Integer.parseInt(args[199]),Integer.parseInt(args[200])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryRequestRepairForList(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryHousingDistrictInfosForPaging(null,Integer.parseInt(args[201]),Integer.parseInt(args[202])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.saveOrUpdateOperationLog() is a void method!");
	        System.out.println("Server said: " + port2.deletePushFinishByEntity(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.resetPassword() is a void method!");
	        System.out.println("Server said: " + port2.hasBuilding(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.updateChargeCalUnit() is a void method!");
	        System.out.println("Server said: port2.publishAd() is a void method!");
	        System.out.println("Server said: " + port2.queryDevices(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.saveCallRecord() is a void method!");
	        System.out.println("Server said: " + port2.queryChargeDetailForList(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.existIdCardForSaUser(null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.updateWeather() is a void method!");
	        System.out.println("Server said: " + port2.queryBuildingCellInfosForPaging(null,Integer.parseInt(args[203]),Integer.parseInt(args[204])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.saveInfoReceiverDevice() is a void method!");
	        System.out.println("Server said: " + port2.queryChargeCalModeByParams(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.saveOrUpdateAdressBook(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.addOwnerComplaint() is a void method!");
	        System.out.println("Server said: " + port2.queryDeviceById(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.removeRegionById(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.updateAd() is a void method!");
	        System.out.println("Server said: port2.updateRssServer() is a void method!");
	        System.out.println("Server said: " + port2.existRegionName(null,null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryAlarms(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.saveChargeTypeResult() is a void method!");
	        System.out.println("Server said: port2.updateComponent() is a void method!");
	        System.out.println("Server said: port2.saveOrUpdateAlarmType() is a void method!");
	        System.out.println("Server said: port2.addSoftware() is a void method!");
	        System.out.println("Server said: " + port2.deleteAdressBookById(null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.updatePassword() is a void method!");
	        System.out.println("Server said: " + port2.getFileUploadLog(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.existCellName(null,null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.updateHouseCodeNotRepeat(null,null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.getMenuDetail(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.saveChargeCalMode() is a void method!");
	        System.out.println("Server said: " + port2.hasCell(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.updateRole() is a void method!");
	        System.out.println("Server said: port2.saveAndPushDevicePassword() is a void method!");
	        System.out.println("Server said: port2.updateGateCardVisit() is a void method!");
	        System.out.println("Server said: " + port2.queryDevicesList(null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryIdCardVisits(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryLocalHdfsForPage(Integer.parseInt(args[205]),Integer.parseInt(args[206]),null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryAllGateCardVisits(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.removeComplaint() is a void method!");
	        System.out.println("Server said: port2.saveChargeData() is a void method!");
	        System.out.println("Server said: port2.delChargeData() is a void method!");
	        System.out.println("Server said: " + port2.getInfoSend(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryChargeMonetaryUnitForList(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.updateDevice(null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryClientLogById(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.removeIdCardVisit() is a void method!");
	        System.out.println("Server said: " + port2.queryInfoSendForList(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.sendProtectionAndRemovalMonitorCommand(null,null,null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.getAppDownloadAbsoluteUrl());
	        System.out.println("Server said: " + port2.getInfoCount(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.getLatestSoftware(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryCellHouseholdInfosForPaging(null,Integer.parseInt(args[207]),Integer.parseInt(args[208])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.querySystemLogForPaging(null,null,null,null,Integer.parseInt(args[209]),Integer.parseInt(args[210])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.resumeAd() is a void method!");
	        System.out.println("Server said: port2.notifyUpgradeSoftware() is a void method!");
	        System.out.println("Server said: " + port2.queryPushForPaging(Integer.parseInt(args[211]),Integer.parseInt(args[212]),null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.getLoginByLoginName(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.savePage() is a void method!");
	        System.out.println("Server said: port2.updatePropertyCompanyInfo() is a void method!");
	        System.out.println("Server said: " + port2.queryAdTargets(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryPropertyCompanyInfosForPaging(null,Integer.parseInt(args[213]),Integer.parseInt(args[214])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryPaUsersForPaging(null,Integer.parseInt(args[215]),Integer.parseInt(args[216])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.delInfoSend() is a void method!");
	        System.out.println("Server said: " + port2.getOwnerUserByHouseId(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryOwnerUnlockDevice(null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.getRegionBuildingInfo(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryRoles(null,null,null,null,Boolean.parseBoolean(args[217])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.delRequestRepair() is a void method!");
	        System.out.println("Server said: " + port2.getPropertyCompanyInfoByDeviceCode(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.delChargeType() is a void method!");
	        System.out.println("Server said: " + port2.removeCellById(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.getWebDownloadAbsoluteUrl());
	        System.out.println("Server said: " + port2.getExternalFileServerUrl());
	        System.out.println("Server said: " + port2.queryGateCardVisitsForPaging(null,Integer.parseInt(args[218]),Integer.parseInt(args[219])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.deleteAllPageContent() is a void method!");
	        System.out.println("Server said: port2.updateInfoSend() is a void method!");
	        System.out.println("Server said: port2.saveAlarm() is a void method!");
	        System.out.println("Server said: " + port2.sendSceneMonitorCommand(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.updateAlarm() is a void method!");
	        System.out.println("Server said: " + port2.logon(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.getBuildingCellInfo(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryRegionBuildingInfosForPaging(null,Integer.parseInt(args[220]),Integer.parseInt(args[221])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryRoomByDeviceNo(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.deleteSceneDeviceByDeviceNoAndDeviceId(null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryComplaintByDeviceCode(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.generatorDeviceNo());
	        System.out.println("Server said: " + port2.queryDevicePassword(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryChargeDataForPaging(null,Integer.parseInt(args[222]),Integer.parseInt(args[223])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.saveOrUpdateSystemLog() is a void method!");
	        System.out.println("Server said: port2.delDevicePassword() is a void method!");
	        System.out.println("Server said: port2.updateInfoReceiver() is a void method!");
	        System.out.println("Server said: " + port2.getChargeTypeResult(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.updateDeviceId(null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryAdLocations(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.savePageContent() is a void method!");
	        System.out.println("Server said: " + port2.getIsUsedSceneByDeviceNo(null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryHdfsTaskForPageByKeyValue(Integer.parseInt(args[224]),Integer.parseInt(args[225]),null,null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.getSoftwareDetail(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.findPushById(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.updateBuildingCodeNotRepeat(null,null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.addPropertyComplaint() is a void method!");
	        System.out.println("Server said: " + port2.getDistrictCount());
	        System.out.println("Server said: " + port2.queryDiviceRegeditLogForList(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.addGateCard() is a void method!");
	        System.out.println("Server said: " + port2.queryGateCardDevices(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.saveOrUpdateAppDataLog(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.updateRequestRepair() is a void method!");
	        System.out.println("Server said: " + port2.queryComponents(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryDevicePasswordById(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.delChargeTypeResult() is a void method!");
	        System.out.println("Server said: port2.delInfoReceiver() is a void method!");
	        System.out.println("Server said: port2.updateSystemParam() is a void method!");
	        System.out.println("Server said: port2.updatePaUser() is a void method!");
	        System.out.println("Server said: " + port2.queryRolesForPaging(null,null,null,null,Integer.parseInt(args[226]),Integer.parseInt(args[227])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryClientLogForPaging(Integer.parseInt(args[228]),Integer.parseInt(args[229]),null,null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.publishTimingAd() is a void method!");
	        System.out.println("Server said: port2.updateCellHouseholdInfoSomeProperty() is a void method!");
	        System.out.println("Server said: " + port2.querySystemParamsForPaging(null,Integer.parseInt(args[230]),Integer.parseInt(args[231])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.publishSoftware() is a void method!");
	        System.out.println("Server said: " + port2.queryPropertyDevice(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryAlarmTypeById(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryComponentForPaging(null,Integer.parseInt(args[232]),Integer.parseInt(args[233])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.approveSoftware() is a void method!");
	        System.out.println("Server said: port2.deletePage() is a void method!");
	        System.out.println("Server said: port2.delChargeDetail() is a void method!");
	        System.out.println("Server said: " + port2.queryAlarmById(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.deletePushById(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.updateComplaint() is a void method!");
	        System.out.println("Server said: port2.replySuggestion() is a void method!");
	        System.out.println("Server said: " + port2.getFileServerUrl());
	        System.out.println("Server said: port2.removeAlarm() is a void method!");
	        System.out.println("Server said: " + port2.saveOrUpdateScene(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: port2.delInfoReceiverDevice() is a void method!");
	        System.out.println("Server said: port2.removeRssServer() is a void method!");
	        System.out.println("Server said: " + port2.querySoftwaresForPaging(null,null,null,Integer.parseInt(args[234]),Integer.parseInt(args[235])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.sendGetSceneCommand(null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryChargeCalUnitForList(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.findHouseholds(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryChargeTypeByParams(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.queryDeviceForPaging(null,Integer.parseInt(args[236]),Integer.parseInt(args[237])));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("***********************");
	        System.out.println("Call Over!");
	}
}
