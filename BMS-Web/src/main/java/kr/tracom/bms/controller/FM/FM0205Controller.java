package kr.tracom.bms.controller.FM;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.tracom.bms.domain.FM0205.FM0205Service;
import kr.tracom.cm.support.ControllerSupport;

@Controller
@Scope("request")
public class FM0205Controller extends ControllerSupport {

	@Autowired
	private FM0205Service FM0205Service;
	
	@RequestMapping("/fm/FM0205G0R0")
	public @ResponseBody Map<String, Object> FM0205G0R0() throws Exception {
		result.setData("dlt_BMS_FCLT_COND_PARAM_INFO", FM0205Service.FM0205G0R0());
		return result.getResult();
	}
	
	@RequestMapping("/fm/FM0205G0R1")
	public @ResponseBody Map<String, Object> FM0205G0R1() throws Exception {
		result.setData("dlt_PARAM_KIND", FM0205Service.FM0205G0R1());
		return result.getResult();
	}
	
	@RequestMapping("/fm/FM0205G0R2")
	public @ResponseBody Map<String, Object> FM0205G0R2() throws Exception {
		result.setData("dlt_CTL_PARAM_KIND", FM0205Service.FM0205G0R2());
		return result.getResult();
	}
	
	@RequestMapping("/fm/FM0205G1R0")
	public @ResponseBody Map<String, Object> FM0205G1R0() throws Exception {
		result.setData("dlt_PLF_IMP_VER_INFO", FM0205Service.FM0205G1R0());
		return result.getResult();
	}
	
	@RequestMapping("/fm/FM0205G2R0")
	public @ResponseBody Map<String, Object> FM0205G2R0() throws Exception {
		result.setData("dlt_BMS_FCLT_AIR_COND_PARAM_INFO", FM0205Service.FM0205G2R0());
		return result.getResult();
	}
	
	@RequestMapping("/fm/FM0205G2R1")
	public @ResponseBody Map<String, Object> FM0205G2R1() throws Exception {
		result.setData("dlt_AIR_PARAM_KIND", FM0205Service.FM0205G2R1());
		return result.getResult();
	}
	
	@RequestMapping("/fm/FM0205G2R2")
	public @ResponseBody Map<String, Object> FM0205G2R2() throws Exception {
		result.setData("dlt_CTL_PARAM_KIND", FM0205Service.FM0205G2R2());
		return result.getResult();
	}
	
	@RequestMapping("/fm/FM0205P0R0")
	public @ResponseBody Map<String, Object> FM0205P0R0() throws Exception {
		result.setData("dlt_BMS_FCLT_COND_PARAM_INFO_HIS", FM0205Service.FM0205P0R0());
		return result.getResult();
	}
	
	//에어컨 제어
	/*@RequestMapping("/fm/airconControl")
	public @ResponseBody Map<String, Object> airconControl() throws Exception {
		FM0205Service.airconControl();
		return result.getResult();
	}*/
	
	
}
