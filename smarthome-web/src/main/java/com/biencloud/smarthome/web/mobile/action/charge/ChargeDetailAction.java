package com.biencloud.smarthome.web.mobile.action.charge;

import java.util.List;

import com.biencloud.smarthome.web.base.action.BaseAction;
import com.biencloud.smarthome.web.charge.service.IChargeDetailService;
import com.biencloud.smarthome.web.charge.vo.ChargeDataVO;
import com.biencloud.smarthome.web.charge.vo.ChargeDetailVO;
import com.biencloud.smarthome.web.charge.vo.ChargeTypeResultVO;
import com.biencloud.smarthome.web.common.util.Constants;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.housemgr.vo.CellHouseholdInfoVo;
import com.biencloud.smarthome.web.user.vo.OwnerUserVO;

/**
 * 
 * 项目名称：smarthome-web-new 类名称：ChargeDetailAction 类描述： 收费清单管理类
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-6-12 上午11:03:07
 */
public class ChargeDetailAction extends BaseAction<ChargeDetailVO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private IChargeDetailService chargeDetailService;

	private ChargeDetailVO chargeDetail;

	private List<ChargeTypeResultVO> chargeTypeResultList;

	/**
	 * 
	 * 方法的描述: 收费清单列表
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-6-12 上午11:03:14
	 * @return
	 */
	public String queryList() throws Exception {
		PagingVO<ChargeDetailVO> page = getPage();
		if (page == null)
			page = new PagingVO<ChargeDetailVO>();
		if (chargeDetail == null)
			chargeDetail = new ChargeDetailVO();
		ChargeDataVO cdVo = chargeDetail.getChargeData();
		if (cdVo == null)
			cdVo = new ChargeDataVO();
		if (Constants.LOGIN_USER_TYPE_OWNER.equals(getUserType())) {
			CellHouseholdInfoVo chiVo = cdVo.getCellHouseholdInfo();
			if (chiVo == null)
				chiVo = new CellHouseholdInfoVo();
			OwnerUserVO ouVo = new OwnerUserVO();
			ouVo.setUserId(getUserId());
			chiVo.setOwner(ouVo);
			cdVo.setCellHouseholdInfo(chiVo);
		}
		chargeDetail.setChargeData(cdVo);
		PagingVO<ChargeDetailVO> pagingVO = chargeDetailService.queryChargeDetailVOForPaging(chargeDetail, page.getPageNum(), page.getPageSize());
		setPage(pagingVO);
		return "list";
	}

	/**
	 * 
	 * 方法的描述: 查找单个收费清单
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-6-12 上午11:03:41
	 * @return
	 */
	public String findById() throws Exception {
		String id = getRequest().getParameter("requestId");
		chargeDetail = chargeDetailService.getChargeDetailVO(id);
		chargeTypeResultList = chargeDetail.getChargeTypeResults();
		return "detail";
	}

	public IChargeDetailService getChargeDetailService() {
		return chargeDetailService;
	}

	public void setChargeDetailService(IChargeDetailService chargeDetailService) {
		this.chargeDetailService = chargeDetailService;
	}

	public ChargeDetailVO getChargeDetail() {
		return chargeDetail;
	}

	public void setChargeDetail(ChargeDetailVO chargeDetail) {
		this.chargeDetail = chargeDetail;
	}

	public List<ChargeTypeResultVO> getChargeTypeResultList() {
		return chargeTypeResultList;
	}

	public void setChargeTypeResultList(List<ChargeTypeResultVO> chargeTypeResultList) {
		this.chargeTypeResultList = chargeTypeResultList;
	}

}
