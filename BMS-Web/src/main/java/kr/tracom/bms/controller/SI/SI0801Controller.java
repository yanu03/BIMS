package kr.tracom.bms.controller.SI;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.tracom.bms.domain.SI0801.SI0801Service;
import kr.tracom.cm.support.ControllerSupport;


@Controller
@Scope("request")
public class SI0801Controller extends ControllerSupport {

	@Autowired
	private SI0801Service si0801Service;
	
	@RequestMapping("/si/SI0801G0R0")
	public @ResponseBody Map<String, Object> SI0801G0R0() throws Exception {
		result.setData("dlt_BMS_FCLT_PARAM_CFG_INFO", si0801Service.SI0801G0R0());
		return result.getResult();
	}
	
	@RequestMapping("/si/SI0801SHI0")
	public @ResponseBody Map<String, Object> SI0801SHI0() throws Exception {
		result.setData("dlt_searchitem", si0801Service.SI0801SHI0());
		return result.getResult();
	}
	
	@RequestMapping("/si/SI0801G0S0")
	public @ResponseBody Map<String, Object> SI0801G0S0() throws Exception{
		Map map = si0801Service.SI0801G0S0();
		result.setData("dma_result", map);
		return result.getResultSave();
	}
	
	@RequestMapping("/si/SI0801G1R0")
	public @ResponseBody Map<String, Object> SI0801G1R0() throws Exception {
		result.setData("dlt_BMS_FCLT_SCH_CFG_INFO", si0801Service.SI0801G1R0());
		return result.getResult();
	}
	
	@RequestMapping("/si/SI0801G1S0")
	public @ResponseBody Map<String, Object> SI0801G1S0() throws Exception{
		Map map = si0801Service.SI0801G1S0();
		result.setData("dma_result", map);
		return result.getResultSave();
	}
	
	
}
