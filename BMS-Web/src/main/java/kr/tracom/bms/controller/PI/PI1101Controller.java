package kr.tracom.bms.controller.PI;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.tracom.bms.domain.PI1101.PI1101Service;
import kr.tracom.cm.support.ControllerSupport;

@Controller
@Scope("request")
public class PI1101Controller extends ControllerSupport {

	@Autowired
	private PI1101Service pi1101Service;
	
	@RequestMapping("/pi/PI1101G0R0")
	public @ResponseBody Map<String, Object> PI1101G0R0() throws Exception {
		result.setData("dlt_VDO_ORGA_INFO", pi1101Service.PI1101G0R0());
		return result.getResult();
	}
	
	@RequestMapping("/pi/PI1101G0K0")
	public @ResponseBody Map<String, Object> PI1101G0R1() throws Exception {
		result.setData("dma_SEQ_BMS_VDO_ORGA_INFO_0", pi1101Service.PI1101G0K0());
		return result.getResult();
	}

	@RequestMapping("/pi/PI1101SHI0")
	public @ResponseBody Map<String, Object> PI1101G0R2() throws Exception {
		result.setData("dlt_searchitem", pi1101Service.PI1101SHI0());
		return result.getResult();
	}
	
	@RequestMapping("/pi/PI1101G1R0")
	public @ResponseBody Map<String, Object> PI1101G1R0() throws Exception {
		result.setData("dlt_VDO_INFO", pi1101Service.PI1101G1R0());
		return result.getResult();
	}
	
	@RequestMapping("/pi/PI1101G0S0")
	public @ResponseBody Map<String, Object> PI1101G0S0() throws Exception {
		Map map = pi1101Service.PI1101G0S0();
		result.setData("dma_result", map);
		return result.getResultSave();
	}
	
	@RequestMapping("/pi/PI1101G2R0")
	public @ResponseBody Map<String, Object> PI1101G2R0() throws Exception {
		result.setData("dlt_VDO_ORGA_LIST", pi1101Service.PI1101G2R0());
		return result.getResult();
	}	
	
	@RequestMapping("/pi/PI1101G2S0")
	public @ResponseBody Map<String, Object> PI1101G2S0() throws Exception {
		Map map = pi1101Service.PI1101G2S0();
		result.setData("dma_result", map);
		return result.getResultSave();
	}
	
}
