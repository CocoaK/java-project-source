package com.smarthome.socket.wsservice.stub;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the com.smarthome.socket.wsservice.stub
 * package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

	private final static QName _SaveOrUpdateDevice_QNAME = new QName(
			"http://service.cxfservice.smarthome.biencloud.com/",
			"saveOrUpdateDevice");
	private final static QName _SaveClientLogResponse_QNAME = new QName(
			"http://service.cxfservice.smarthome.biencloud.com/",
			"saveClientLogResponse");
	private final static QName _SaveOrUpdateDeviceResponse_QNAME = new QName(
			"http://service.cxfservice.smarthome.biencloud.com/",
			"saveOrUpdateDeviceResponse");
	private final static QName _DeleteByEntityResponse_QNAME = new QName(
			"http://service.cxfservice.smarthome.biencloud.com/",
			"deleteByEntityResponse");
	private final static QName _InsertPushFinish_QNAME = new QName(
			"http://service.cxfservice.smarthome.biencloud.com/",
			"insertPushFinish");
	private final static QName _DeviceOutLine_QNAME = new QName(
			"http://service.cxfservice.smarthome.biencloud.com/",
			"deviceOutLine");
	private final static QName _InsertPushFinishResponse_QNAME = new QName(
			"http://service.cxfservice.smarthome.biencloud.com/",
			"insertPushFinishResponse");
	private final static QName _QueryCronTimeUpdate_QNAME = new QName(
			"http://service.cxfservice.smarthome.biencloud.com/",
			"queryCronTimeUpdate");
	private final static QName _UpdateDeviceStatusForAll_QNAME = new QName(
			"http://service.cxfservice.smarthome.biencloud.com/",
			"updateDeviceStatusForAll");
	private final static QName _FindByIdResponse_QNAME = new QName(
			"http://service.cxfservice.smarthome.biencloud.com/",
			"findByIdResponse");
	private final static QName _DeleteById_QNAME = new QName(
			"http://service.cxfservice.smarthome.biencloud.com/", "deleteById");
	private final static QName _ListPushByClientIDForMapResponse_QNAME = new QName(
			"http://service.cxfservice.smarthome.biencloud.com/",
			"listPushByClientIDForMapResponse");
	private final static QName _ListPush_QNAME = new QName(
			"http://service.cxfservice.smarthome.biencloud.com/", "listPush");
	private final static QName _DeviceOutLineResponse_QNAME = new QName(
			"http://service.cxfservice.smarthome.biencloud.com/",
			"deviceOutLineResponse");
	private final static QName _ListPushByClientIdResponse_QNAME = new QName(
			"http://service.cxfservice.smarthome.biencloud.com/",
			"listPushByClientIdResponse");
	private final static QName _UpdateDeviceStatusByMac_QNAME = new QName(
			"http://service.cxfservice.smarthome.biencloud.com/",
			"updateDeviceStatusByMac");
	private final static QName _FindById_QNAME = new QName(
			"http://service.cxfservice.smarthome.biencloud.com/", "findById");
	private final static QName _ListPushByClientIDForMap_QNAME = new QName(
			"http://service.cxfservice.smarthome.biencloud.com/",
			"listPushByClientIDForMap");
	private final static QName _DeleteByIdResponse_QNAME = new QName(
			"http://service.cxfservice.smarthome.biencloud.com/",
			"deleteByIdResponse");
	private final static QName _DeleteByEntity_QNAME = new QName(
			"http://service.cxfservice.smarthome.biencloud.com/",
			"deleteByEntity");
	private final static QName _UpdateDeviceStatusByMacResponse_QNAME = new QName(
			"http://service.cxfservice.smarthome.biencloud.com/",
			"updateDeviceStatusByMacResponse");
	private final static QName _QueryCronTimeUpdateResponse_QNAME = new QName(
			"http://service.cxfservice.smarthome.biencloud.com/",
			"queryCronTimeUpdateResponse");
	private final static QName _ListPushResponse_QNAME = new QName(
			"http://service.cxfservice.smarthome.biencloud.com/",
			"listPushResponse");
	private final static QName _UpdateDeviceStatusForAllResponse_QNAME = new QName(
			"http://service.cxfservice.smarthome.biencloud.com/",
			"updateDeviceStatusForAllResponse");
	private final static QName _SaveClientLog_QNAME = new QName(
			"http://service.cxfservice.smarthome.biencloud.com/",
			"saveClientLog");
	private final static QName _ListPushByClientId_QNAME = new QName(
			"http://service.cxfservice.smarthome.biencloud.com/",
			"listPushByClientId");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package: com.smarthome.socket.wsservice.stub
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link UpdateDeviceStatusByMacResponse }
	 * 
	 */
	public UpdateDeviceStatusByMacResponse createUpdateDeviceStatusByMacResponse() {
		return new UpdateDeviceStatusByMacResponse();
	}

	/**
	 * Create an instance of {@link AppVO }
	 * 
	 */
	public AppVO createAppVO() {
		return new AppVO();
	}

	/**
	 * Create an instance of {@link PushMapConvertor }
	 * 
	 */
	public PushMapConvertor createPushMapConvertor() {
		return new PushMapConvertor();
	}

	/**
	 * Create an instance of {@link DeleteById }
	 * 
	 */
	public DeleteById createDeleteById() {
		return new DeleteById();
	}

	/**
	 * Create an instance of {@link MapEntry }
	 * 
	 */
	public MapEntry createMapEntry() {
		return new MapEntry();
	}

	/**
	 * Create an instance of {@link QueryCronTimeUpdate }
	 * 
	 */
	public QueryCronTimeUpdate createQueryCronTimeUpdate() {
		return new QueryCronTimeUpdate();
	}

	/**
	 * Create an instance of {@link SaveClientLogResponse }
	 * 
	 */
	public SaveClientLogResponse createSaveClientLogResponse() {
		return new SaveClientLogResponse();
	}

	/**
	 * Create an instance of {@link DeleteByEntityResponse }
	 * 
	 */
	public DeleteByEntityResponse createDeleteByEntityResponse() {
		return new DeleteByEntityResponse();
	}

	/**
	 * Create an instance of {@link DeviceOutLineResponse }
	 * 
	 */
	public DeviceOutLineResponse createDeviceOutLineResponse() {
		return new DeviceOutLineResponse();
	}

	/**
	 * Create an instance of {@link QueryCronTimeUpdateResponse }
	 * 
	 */
	public QueryCronTimeUpdateResponse createQueryCronTimeUpdateResponse() {
		return new QueryCronTimeUpdateResponse();
	}

	/**
	 * Create an instance of {@link SaveOrUpdateDeviceResponse }
	 * 
	 */
	public SaveOrUpdateDeviceResponse createSaveOrUpdateDeviceResponse() {
		return new SaveOrUpdateDeviceResponse();
	}

	/**
	 * Create an instance of {@link FindById }
	 * 
	 */
	public FindById createFindById() {
		return new FindById();
	}

	/**
	 * Create an instance of {@link ListPushByClientIDForMap }
	 * 
	 */
	public ListPushByClientIDForMap createListPushByClientIDForMap() {
		return new ListPushByClientIDForMap();
	}

	/**
	 * Create an instance of {@link InsertPushFinishResponse }
	 * 
	 */
	public InsertPushFinishResponse createInsertPushFinishResponse() {
		return new InsertPushFinishResponse();
	}

	/**
	 * Create an instance of {@link ListPushByClientIDForMapResponse }
	 * 
	 */
	public ListPushByClientIDForMapResponse createListPushByClientIDForMapResponse() {
		return new ListPushByClientIDForMapResponse();
	}

	/**
	 * Create an instance of {@link Push }
	 * 
	 */
	public Push createPush() {
		return new Push();
	}

	/**
	 * Create an instance of {@link ListPushByClientId }
	 * 
	 */
	public ListPushByClientId createListPushByClientId() {
		return new ListPushByClientId();
	}

	/**
	 * Create an instance of {@link ListPush }
	 * 
	 */
	public ListPush createListPush() {
		return new ListPush();
	}

	/**
	 * Create an instance of {@link DeleteByEntity }
	 * 
	 */
	public DeleteByEntity createDeleteByEntity() {
		return new DeleteByEntity();
	}

	/**
	 * Create an instance of {@link SaveOrUpdateDevice }
	 * 
	 */
	public SaveOrUpdateDevice createSaveOrUpdateDevice() {
		return new SaveOrUpdateDevice();
	}

	/**
	 * Create an instance of {@link AppLoginVO }
	 * 
	 */
	public AppLoginVO createAppLoginVO() {
		return new AppLoginVO();
	}

	/**
	 * Create an instance of {@link SaveClientLog }
	 * 
	 */
	public SaveClientLog createSaveClientLog() {
		return new SaveClientLog();
	}

	/**
	 * Create an instance of {@link UpdateDeviceStatusByMac }
	 * 
	 */
	public UpdateDeviceStatusByMac createUpdateDeviceStatusByMac() {
		return new UpdateDeviceStatusByMac();
	}

	/**
	 * Create an instance of {@link ListPushByClientIdResponse }
	 * 
	 */
	public ListPushByClientIdResponse createListPushByClientIdResponse() {
		return new ListPushByClientIdResponse();
	}

	/**
	 * Create an instance of {@link FindByIdResponse }
	 * 
	 */
	public FindByIdResponse createFindByIdResponse() {
		return new FindByIdResponse();
	}

	/**
	 * Create an instance of {@link DeleteByIdResponse }
	 * 
	 */
	public DeleteByIdResponse createDeleteByIdResponse() {
		return new DeleteByIdResponse();
	}

	/**
	 * Create an instance of {@link UpdateDeviceStatusForAll }
	 * 
	 */
	public UpdateDeviceStatusForAll createUpdateDeviceStatusForAll() {
		return new UpdateDeviceStatusForAll();
	}

	/**
	 * Create an instance of {@link ClientLog }
	 * 
	 */
	public ClientLog createClientLog() {
		return new ClientLog();
	}

	/**
	 * Create an instance of {@link DeviceOutLine }
	 * 
	 */
	public DeviceOutLine createDeviceOutLine() {
		return new DeviceOutLine();
	}

	/**
	 * Create an instance of {@link ListPushResponse }
	 * 
	 */
	public ListPushResponse createListPushResponse() {
		return new ListPushResponse();
	}

	/**
	 * Create an instance of {@link InsertPushFinish }
	 * 
	 */
	public InsertPushFinish createInsertPushFinish() {
		return new InsertPushFinish();
	}

	/**
	 * Create an instance of {@link UpdateDeviceStatusForAllResponse }
	 * 
	 */
	public UpdateDeviceStatusForAllResponse createUpdateDeviceStatusForAllResponse() {
		return new UpdateDeviceStatusForAllResponse();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link SaveOrUpdateDevice }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service.cxfservice.smarthome.biencloud.com/", name = "saveOrUpdateDevice")
	public JAXBElement<SaveOrUpdateDevice> createSaveOrUpdateDevice(
			SaveOrUpdateDevice value) {
		return new JAXBElement<SaveOrUpdateDevice>(_SaveOrUpdateDevice_QNAME,
				SaveOrUpdateDevice.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link SaveClientLogResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service.cxfservice.smarthome.biencloud.com/", name = "saveClientLogResponse")
	public JAXBElement<SaveClientLogResponse> createSaveClientLogResponse(
			SaveClientLogResponse value) {
		return new JAXBElement<SaveClientLogResponse>(
				_SaveClientLogResponse_QNAME, SaveClientLogResponse.class,
				null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link SaveOrUpdateDeviceResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service.cxfservice.smarthome.biencloud.com/", name = "saveOrUpdateDeviceResponse")
	public JAXBElement<SaveOrUpdateDeviceResponse> createSaveOrUpdateDeviceResponse(
			SaveOrUpdateDeviceResponse value) {
		return new JAXBElement<SaveOrUpdateDeviceResponse>(
				_SaveOrUpdateDeviceResponse_QNAME,
				SaveOrUpdateDeviceResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link DeleteByEntityResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service.cxfservice.smarthome.biencloud.com/", name = "deleteByEntityResponse")
	public JAXBElement<DeleteByEntityResponse> createDeleteByEntityResponse(
			DeleteByEntityResponse value) {
		return new JAXBElement<DeleteByEntityResponse>(
				_DeleteByEntityResponse_QNAME, DeleteByEntityResponse.class,
				null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link InsertPushFinish }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service.cxfservice.smarthome.biencloud.com/", name = "insertPushFinish")
	public JAXBElement<InsertPushFinish> createInsertPushFinish(
			InsertPushFinish value) {
		return new JAXBElement<InsertPushFinish>(_InsertPushFinish_QNAME,
				InsertPushFinish.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link DeviceOutLine }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service.cxfservice.smarthome.biencloud.com/", name = "deviceOutLine")
	public JAXBElement<DeviceOutLine> createDeviceOutLine(DeviceOutLine value) {
		return new JAXBElement<DeviceOutLine>(_DeviceOutLine_QNAME,
				DeviceOutLine.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link InsertPushFinishResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service.cxfservice.smarthome.biencloud.com/", name = "insertPushFinishResponse")
	public JAXBElement<InsertPushFinishResponse> createInsertPushFinishResponse(
			InsertPushFinishResponse value) {
		return new JAXBElement<InsertPushFinishResponse>(
				_InsertPushFinishResponse_QNAME,
				InsertPushFinishResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link QueryCronTimeUpdate }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service.cxfservice.smarthome.biencloud.com/", name = "queryCronTimeUpdate")
	public JAXBElement<QueryCronTimeUpdate> createQueryCronTimeUpdate(
			QueryCronTimeUpdate value) {
		return new JAXBElement<QueryCronTimeUpdate>(_QueryCronTimeUpdate_QNAME,
				QueryCronTimeUpdate.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link UpdateDeviceStatusForAll }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service.cxfservice.smarthome.biencloud.com/", name = "updateDeviceStatusForAll")
	public JAXBElement<UpdateDeviceStatusForAll> createUpdateDeviceStatusForAll(
			UpdateDeviceStatusForAll value) {
		return new JAXBElement<UpdateDeviceStatusForAll>(
				_UpdateDeviceStatusForAll_QNAME,
				UpdateDeviceStatusForAll.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link FindByIdResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service.cxfservice.smarthome.biencloud.com/", name = "findByIdResponse")
	public JAXBElement<FindByIdResponse> createFindByIdResponse(
			FindByIdResponse value) {
		return new JAXBElement<FindByIdResponse>(_FindByIdResponse_QNAME,
				FindByIdResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link DeleteById }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service.cxfservice.smarthome.biencloud.com/", name = "deleteById")
	public JAXBElement<DeleteById> createDeleteById(DeleteById value) {
		return new JAXBElement<DeleteById>(_DeleteById_QNAME, DeleteById.class,
				null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link ListPushByClientIDForMapResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service.cxfservice.smarthome.biencloud.com/", name = "listPushByClientIDForMapResponse")
	public JAXBElement<ListPushByClientIDForMapResponse> createListPushByClientIDForMapResponse(
			ListPushByClientIDForMapResponse value) {
		return new JAXBElement<ListPushByClientIDForMapResponse>(
				_ListPushByClientIDForMapResponse_QNAME,
				ListPushByClientIDForMapResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link ListPush }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service.cxfservice.smarthome.biencloud.com/", name = "listPush")
	public JAXBElement<ListPush> createListPush(ListPush value) {
		return new JAXBElement<ListPush>(_ListPush_QNAME, ListPush.class, null,
				value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link DeviceOutLineResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service.cxfservice.smarthome.biencloud.com/", name = "deviceOutLineResponse")
	public JAXBElement<DeviceOutLineResponse> createDeviceOutLineResponse(
			DeviceOutLineResponse value) {
		return new JAXBElement<DeviceOutLineResponse>(
				_DeviceOutLineResponse_QNAME, DeviceOutLineResponse.class,
				null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link ListPushByClientIdResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service.cxfservice.smarthome.biencloud.com/", name = "listPushByClientIdResponse")
	public JAXBElement<ListPushByClientIdResponse> createListPushByClientIdResponse(
			ListPushByClientIdResponse value) {
		return new JAXBElement<ListPushByClientIdResponse>(
				_ListPushByClientIdResponse_QNAME,
				ListPushByClientIdResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link UpdateDeviceStatusByMac }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service.cxfservice.smarthome.biencloud.com/", name = "updateDeviceStatusByMac")
	public JAXBElement<UpdateDeviceStatusByMac> createUpdateDeviceStatusByMac(
			UpdateDeviceStatusByMac value) {
		return new JAXBElement<UpdateDeviceStatusByMac>(
				_UpdateDeviceStatusByMac_QNAME, UpdateDeviceStatusByMac.class,
				null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link FindById }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service.cxfservice.smarthome.biencloud.com/", name = "findById")
	public JAXBElement<FindById> createFindById(FindById value) {
		return new JAXBElement<FindById>(_FindById_QNAME, FindById.class, null,
				value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link ListPushByClientIDForMap }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service.cxfservice.smarthome.biencloud.com/", name = "listPushByClientIDForMap")
	public JAXBElement<ListPushByClientIDForMap> createListPushByClientIDForMap(
			ListPushByClientIDForMap value) {
		return new JAXBElement<ListPushByClientIDForMap>(
				_ListPushByClientIDForMap_QNAME,
				ListPushByClientIDForMap.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link DeleteByIdResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service.cxfservice.smarthome.biencloud.com/", name = "deleteByIdResponse")
	public JAXBElement<DeleteByIdResponse> createDeleteByIdResponse(
			DeleteByIdResponse value) {
		return new JAXBElement<DeleteByIdResponse>(_DeleteByIdResponse_QNAME,
				DeleteByIdResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link DeleteByEntity }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service.cxfservice.smarthome.biencloud.com/", name = "deleteByEntity")
	public JAXBElement<DeleteByEntity> createDeleteByEntity(DeleteByEntity value) {
		return new JAXBElement<DeleteByEntity>(_DeleteByEntity_QNAME,
				DeleteByEntity.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link UpdateDeviceStatusByMacResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service.cxfservice.smarthome.biencloud.com/", name = "updateDeviceStatusByMacResponse")
	public JAXBElement<UpdateDeviceStatusByMacResponse> createUpdateDeviceStatusByMacResponse(
			UpdateDeviceStatusByMacResponse value) {
		return new JAXBElement<UpdateDeviceStatusByMacResponse>(
				_UpdateDeviceStatusByMacResponse_QNAME,
				UpdateDeviceStatusByMacResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link QueryCronTimeUpdateResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service.cxfservice.smarthome.biencloud.com/", name = "queryCronTimeUpdateResponse")
	public JAXBElement<QueryCronTimeUpdateResponse> createQueryCronTimeUpdateResponse(
			QueryCronTimeUpdateResponse value) {
		return new JAXBElement<QueryCronTimeUpdateResponse>(
				_QueryCronTimeUpdateResponse_QNAME,
				QueryCronTimeUpdateResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link ListPushResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service.cxfservice.smarthome.biencloud.com/", name = "listPushResponse")
	public JAXBElement<ListPushResponse> createListPushResponse(
			ListPushResponse value) {
		return new JAXBElement<ListPushResponse>(_ListPushResponse_QNAME,
				ListPushResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link UpdateDeviceStatusForAllResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service.cxfservice.smarthome.biencloud.com/", name = "updateDeviceStatusForAllResponse")
	public JAXBElement<UpdateDeviceStatusForAllResponse> createUpdateDeviceStatusForAllResponse(
			UpdateDeviceStatusForAllResponse value) {
		return new JAXBElement<UpdateDeviceStatusForAllResponse>(
				_UpdateDeviceStatusForAllResponse_QNAME,
				UpdateDeviceStatusForAllResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link SaveClientLog }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service.cxfservice.smarthome.biencloud.com/", name = "saveClientLog")
	public JAXBElement<SaveClientLog> createSaveClientLog(SaveClientLog value) {
		return new JAXBElement<SaveClientLog>(_SaveClientLog_QNAME,
				SaveClientLog.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link ListPushByClientId }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service.cxfservice.smarthome.biencloud.com/", name = "listPushByClientId")
	public JAXBElement<ListPushByClientId> createListPushByClientId(
			ListPushByClientId value) {
		return new JAXBElement<ListPushByClientId>(_ListPushByClientId_QNAME,
				ListPushByClientId.class, null, value);
	}

}
