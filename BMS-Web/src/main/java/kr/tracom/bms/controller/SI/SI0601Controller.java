package kr.tracom.bms.controller.SI;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.tracom.bms.domain.SI0601.SI0601Service;
import kr.tracom.cm.support.ControllerSupport;

@Controller
@Scope("request")
public class SI0601Controller extends ControllerSupport{

	@Autowired
	private SI0601Service si0102Service;
	
	@RequestMapping("/si/SI0601G0R0")
	public @ResponseBody Map<String, Object> SI0601G0R0() throws Exception {
		result.setData("dlt_BMS_PARTNER_MST", si0102Service.SI0601G0R0());
		return result.getResult();
	}
	
	@RequestMapping("/si/SI0601G0K0")
	public @ResponseBody Map<String, Object> SI0601G0R1() throws Exception {
		result.setData("dma_SEQ_BMS_PARTNER_MST_0", si0102Service.SI0601G0K0());
		return result.getResult();
	}

	@RequestMapping("/si/SI0601SHI0")
	public @ResponseBody Map<String, Object> SI0601G0R2() throws Exception {
		result.setData("dlt_searchItem", si0102Service.SI0601SHI0());
		return result.getResult();
	}
	
	@RequestMapping("/si/SI0601G0S0")
	public @ResponseBody Map<String, Object> SI0601G0S0() throws Exception {
		Map map = si0102Service.SI0601G0S0();
		result.setData("dma_result", map);
		return result.getResultSave();
	}	
	
}
