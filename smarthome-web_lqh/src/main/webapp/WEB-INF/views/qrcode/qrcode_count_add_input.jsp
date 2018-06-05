<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><s:text name="二维码修改" /></title>
        <link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
        <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/tm.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery.textbox.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
    </head>
    <body>
        <form action="<s:url action="addInput" namespace="/qrcode/count"/>" method="post" id="formname" name="formname">
        <div class="main_section">
            <div class="all_aside">
                <div class="all_menu">
                    <a href="#" class="all_menu_a">二维码数量修改</a>
                </div>
            </div>
            <div class="main_outside">
                <div class="all_inside">
                    <table cellspacing="0"
                        class="all_tab_body all_tab_bodyq">
                        <tbody>

                            <tr>
                                <td>通话二维码数量</td>
                                <td>
                                	<input type="text" maxlength="20" name="qrcodeCountVO.sipCount" class="{phone:true}" value="<s:property value='qrcodeCountVO.sipCount'/>" />
                                	<input type="hidden" name="qrcodeCountVO.houseId" value="<s:property value='user.houseId'/>" />
                                	<input type="hidden" name="qrcodeCountVO.type" value="1" />
                                </td>
                                <td>开锁二维码数量</td>
                                <td><input type="text" maxlength="20" name="qrcodeCountVO.lockCount" class="{mobilephone:true}" value="<s:property value='qrcodeCountVO.sipCount'/>" /></td>
                            </tr>
                            
                        </tbody>
                    </table>
                    
                    <div class="all_tab_butb"><input type="submit" value="<s:text name='common.element.action.confirm.update' />" /></div>
                    <s:include value="/WEB-INF/views/housemgr/include_result_msg.jsp"/>
				</div>
			</div>
		</div>
	</form>
</body>
</html>
