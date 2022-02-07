package kr.tracom.cm.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.tracom.cm.domain.Partner.PartnerService;
import kr.tracom.cm.support.ControllerSupport;


@Controller
@Scope("request")
public class PartnerController extends ControllerSupport {

	@Autowired
	private PartnerService partnerService;

	@RequestMapping("/partner/selectPartnerItem")
	public @ResponseBody Map<String, Object> selectRepRoutItem() throws Exception {
		result.setData("dlt_partnerItem", partnerService.selectPartnerItem());
		return result.getResult();
	}

	@RequestMapping("/partner/selectPartnerList")
	public @ResponseBody Map<String, Object> selectRepRoutList() throws Exception {
		result.setData("dlt_BMS_PARTNER_MST", partnerService.selectPartnerList());
		return result.getResult();
	}
	

}
