<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
				<div id="commandSucceed" class="layer none">
                    <div>
                        <p><s:text name="common.system.info"/></p>
                        <ul>
                            <li>
                                <s:text name="common.element.action.operationSuccess"/>
                            </li>
                            <li>
                                <a href="#" onclick="$('#commandSucceed').hide();"><s:text name="common.element.action.confirm"/></a>
                            </li>
                        </ul>
                    </div>
                </div>
                <div id="commandFail" class="layer none">
                    <div>
                        <p><s:text name="common.system.info"/></p>
                        <ul>
                            <li>
                                <s:text name="common.element.action.operationfailed"/>
                            </li>
                            <li>
                                <a href="#" onclick="$('#commandFail').hide();"><s:text name="common.element.action.retry"/></a>
                            </li>
                        </ul>
                    </div>
                </div>
                <div id="waitData" class="layer none">
                    <div>
                        <p><s:text name="common.system.info"/></p>
                        <ul>
                            <li><s:text name="common.page.waiting"/></li>
                            <li><a href="<s:url action="remoteIndex"/>" ><s:text name="common.element.action.return" /></a></li>
                        </ul>
                    </div>
                </div>
                
                <div id="noSceneDeviceData" class="layer none">
                    <div>
                        <p><s:text name="common.system.info"/></p>
                        <ul>
                            <li>
                                <s:text name="common.page.nodata"/>
                            </li>
                            <li>
                                <a href="#" onclick="$('#noSceneDeviceData').hide();"><s:text name="common.element.action.confirm"/></a>
                            </li>
                        </ul>
                    </div>
                </div>