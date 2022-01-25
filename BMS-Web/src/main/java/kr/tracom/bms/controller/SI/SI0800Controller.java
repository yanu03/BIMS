package kr.tracom.bms.controller.SI;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.tracom.bms.domain.SI0800.SI0800Service;
import kr.tracom.cm.support.ControllerSupport;


@Controller
@Scope("request")
public class SI0800Controller extends ControllerSupport {

	@Autowired
	private SI0800Service si0800Service;
	
	@RequestMapping("/si/SI0800G0R0")
	public @ResponseBody Map<String, Object> SI0800G0R0() throws Exception {
		result.setData("dlt_BMS_DVC_PARAM_CFG_INFO", si0800Service.SI0800G0R0());
		return result.getResult();
	}
	
	@RequestMapping("/si/SI0800SHI0")
	public @ResponseBody Map<String, Object> SI0800SHI0() throws Exception {
		result.setData("dlt_searchitem", si0800Service.SI0800SHI0());
		return result.getResult();
	}
	
	@RequestMapping("/si/SI0800G0S0")
	public @ResponseBody Map<String, Object> SI0800G0S0() throws Exception{
		Map map = si0800Service.SI0800G0S0();
		result.setData("dma_result", map);
		return result.getResultSave();
	}
	
	
}
