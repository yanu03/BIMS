package kr.tracom.bms.controller.PI;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.tracom.bms.domain.PI1103.PI1103Service;
import kr.tracom.cm.support.ControllerSupport;

@Controller
@Scope("request")
public class PI1103Controller extends ControllerSupport {

	@Autowired
	private PI1103Service pi1103Service;
	
	@RequestMapping("/pi/PI1103G0R0")
	public @ResponseBody Map<String, Object> PI1103G0R0() throws Exception {
		result.setData("dlt_BMS_ED_INFO", pi1103Service.PI1103G0R0());
		return result.getResult();
	}
	
	@RequestMapping("/pi/PI1103G0K0")
	public @ResponseBody Map<String, Object> PI1103G0K0() throws Exception {
		result.setData("dma_SEQ_BMS_ED_INFO_0", pi1103Service.PI1103G0K0());
		return result.getResult();
	}
	
	@RequestMapping("/pi/PI1103G0S0")
	public @ResponseBody Map<String, Object> PI1103G0S0() throws Exception {
		Map map = pi1103Service.PI1103G0S0();
		result.setData("dma_result", map);
		return result.getResultSave();
	}
	
	@RequestMapping("/pi/PI1103SHI0")
	public @ResponseBody Map<String, Object> PI1103SHI0() throws Exception {
		result.setData("dlt_searchitem", pi1103Service.PI1103SHI0());
		return result.getResult();
	}
}
