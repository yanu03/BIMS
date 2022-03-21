package kr.tracom.bms.controller.SI;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.tracom.bms.domain.SI0408.SI0408Service;
import kr.tracom.cm.support.ControllerSupport;

@Controller
@Scope("request")
public class SI0408Controller extends ControllerSupport {
	
	@Autowired
	private SI0408Service SI0408Service;
		
	@RequestMapping("/si/SI0408G1R0")
	public @ResponseBody Map<String, Object> SI0408G1R0() throws Exception {
		result.setData("BMS_ROUT_NODE_DISP_CMPSTN", SI0408Service.SI0408G1R0());
		return result.getResult();
	}
	
	@RequestMapping("/si/SI0408G1S0")
	public @ResponseBody Map<String, Object> SI0408G1S0() throws Exception {
		Map map = SI0408Service.SI0408G1S0();
		result.setData("dma_result", map);
		return result.getResultSave();
	}	
}
