package kr.tracom.bms.controller.PI;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.tracom.bms.domain.PI1102.PI1102Service;
import kr.tracom.cm.support.ControllerSupport;

@Controller
@Scope("request")
public class PI1102Controller extends ControllerSupport {

	@Autowired
	private PI1102Service pi1102Service;
	
	@RequestMapping("/pi/PI1102G0R0")
	public @ResponseBody Map<String, Object> PI1102G0R0() throws Exception {
		result.setData("dlt_VDO_ORGA_INFO", pi1102Service.PI1102G0R0());
		return result.getResult();
	}
	
	@RequestMapping("/pi/PI1102G1R0")
	public @ResponseBody Map<String, Object> PI1102G1R0() throws Exception {
		result.setData("dlt_DVC_INFO", pi1102Service.PI1102G1R0());
		return result.getResult();
	}
	
	@RequestMapping("/pi/PI1102G2R0")
	public @ResponseBody Map<String, Object> PI1102G2R0() throws Exception {
		result.setData("dlt_BMS_VDO_RSV_RST_INFO", pi1102Service.PI1102G2R0());
		return result.getResult();
	}
	
	@RequestMapping("/pi/PI1102G1S0")
	public @ResponseBody Map<String, Object> PI1102G1S0() throws Exception {
		Map map = pi1102Service.PI1102G1S0();
		result.setData("dma_result", map);
		return result.getResultSave();
	}
	
	@RequestMapping("/pi/PI1102G1U0")
	public @ResponseBody Map<String, Object> PI1102G1D0() throws Exception {
		Map map = pi1102Service.PI1102G1U0();
		result.setData("dma_result", map);
		return result.getResultSave();
	}
	
	@RequestMapping("/pi/PI1102SHI0")
	public @ResponseBody Map<String, Object> PI1102SHI0() throws Exception {
		result.setData("dlt_searchitem", pi1102Service.PI1102SHI0());
		return result.getResult();
	}
	
}
