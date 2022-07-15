package kr.tracom.bms.controller.PI;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.tracom.bms.domain.PI1104.PI1104Service;
import kr.tracom.cm.support.ControllerSupport;

@Controller
@Scope("request")
public class PI1104Controller extends ControllerSupport {

	@Autowired
	private PI1104Service pi1104Service;
	
	@RequestMapping("/pi/PI1104G0R0")
	public @ResponseBody Map<String, Object> PI1104G0R0() throws Exception {
		result.setData("dlt_BMS_TRAN_INFO", pi1104Service.PI1104G0R0());
		return result.getResult();
	}
	
	@RequestMapping("/pi/PI1104G1R0")
	public @ResponseBody Map<String, Object> PI1104G1R0() throws Exception {
		result.setData("dlt_VHC_MST", pi1104Service.PI1104G1R0());
		return result.getResult();
	}
	
	@RequestMapping("/pi/PI1104G1K0")
	public @ResponseBody Map<String, Object> PI0701G1K0() throws Exception {
		result.setData("dma_SEQ_BMS_DESTI_RSV_INFO_0", pi1104Service.PI1104G1K0());
		return result.getResult();
	}	
	
	@RequestMapping("/pi/PI1104G1R1")
	public @ResponseBody Map<String, Object> PI1104G1R1() throws Exception {
		result.setData("dlt_VHC_MST_MNG_LIST", pi1104Service.PI1104G1R1());
		return result.getResult();
	}
	
	@RequestMapping("/pi/PI1104G1S0")
	public @ResponseBody Map<String, Object> PI1104G1S0() throws Exception {
		Map map = pi1104Service.PI1104G1S0();
		result.setData("dma_result", map);
		return result.getResultSave();
	}
	
	@RequestMapping("/pi/PI1104G1U0")
	public @ResponseBody Map<String, Object> PI1104G1U0() throws Exception {
		Map map = pi1104Service.PI1104G1U0();
		result.setData("dma_result", map);
		return result.getResultSave();
	}
	
	@RequestMapping("/pi/PI1104SHI0")
	public @ResponseBody Map<String, Object> PI1104SHI0() throws Exception {
		result.setData("dlt_searchitem", pi1104Service.PI1104SHI0());
		return result.getResult();
	}
	
	@RequestMapping("/pi/PI1104G2R0")
	public @ResponseBody Map<String, Object> PI1104G2R0() throws Exception {
		result.setData("dlt_BMS_TRAN_RSV_RST_INFO", pi1104Service.PI1104G2R0());
		return result.getResult();
	}	
	
}
