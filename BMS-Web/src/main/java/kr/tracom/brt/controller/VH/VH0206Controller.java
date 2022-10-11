package kr.tracom.brt.controller.VH;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.tracom.brt.domain.VH0206.VH0206Service;
import kr.tracom.cm.support.ControllerSupport;

@Controller
@Scope("request")
public class VH0206Controller extends ControllerSupport {

	@Autowired
	private VH0206Service vh0206Service;

	@RequestMapping("/vh/VH0206G0R0")
	public @ResponseBody Map<String, Object> VH0206G0R0() throws Exception {
		result.setData("dlt_BMS_DVC_COND_PARAM_INFO_HIS", vh0206Service.VH0206G0R0());
		return result.getResult();
	}
	
	@RequestMapping("/vh/selectDvcItem")
	public @ResponseBody Map<String, Object> selectDvcItem() throws Exception {
		result.setData("dlt_DvcItem", vh0206Service.selectDvcItem());
		return result.getResult();
	}
	
	@RequestMapping("/vh/selectDvcParamItem")
	public @ResponseBody Map<String, Object> selectDvcParamItem() throws Exception {
		result.setData("dlt_DvcParamItem", vh0206Service.selectDvcParamItem());
		return result.getResult();
	}
	
	
}
