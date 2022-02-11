package kr.tracom.bms.controller.FM;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.tracom.bms.domain.FM0204.FM0204Service;
import kr.tracom.cm.support.ControllerSupport;

@Controller
@Scope("request")
public class FM0204Controller extends ControllerSupport {

	@Autowired
	private FM0204Service FM0204Service;
	
	@RequestMapping("/fm/FM0204G0R0")
	public @ResponseBody Map<String, Object> FM0204G0R0() throws Exception {
		result.setData("dlt_BMS_DVC_COND_PARAM_INFO", FM0204Service.FM0204G0R0());
		return result.getResult();
	}
	
	@RequestMapping("/fm/FM0204G0R1")
	public @ResponseBody Map<String, Object> FM0204G0R1() throws Exception {
		result.setData("dlt_PARAM_KIND", FM0204Service.FM0204G0R1());
		return result.getResult();
	}
	
	@RequestMapping("/fm/FM0204G0R2")
	public @ResponseBody Map<String, Object> FM0204G0R2() throws Exception {
		result.setData("dlt_CTL_PARAM_KIND", FM0204Service.FM0204G0R2());
		return result.getResult();
	}
	
	@RequestMapping("/fm/FM0204SHI0")
	public @ResponseBody Map<String, Object> FM0204SHI0() throws Exception {
		result.setData("dlt_searchitem", FM0204Service.FM0204SHI0());
		return result.getResult();
	}
	
	@RequestMapping("/fm/FM0204SHI1")
	public @ResponseBody Map<String, Object> FM0204SHI1() throws Exception {
		result.setData("dlt_searchitem2", FM0204Service.FM0204SHI1());
		return result.getResult();
	}
	
	@RequestMapping("/fm/FM0204G1R0")
	public @ResponseBody Map<String, Object> FM0204G1R0() throws Exception {
		result.setData("dlt_MGR_PROC_HIS", FM0204Service.FM0204G1R0());
		return result.getResult();
	}
	
	@RequestMapping("/fm/FM0204G2R0")
	public @ResponseBody Map<String, Object> FM0204G2R0() throws Exception {
		result.setData("dlt_BMS_DVC_TAG_COND_PARAM_INFO", FM0204Service.FM0204G2R0());
		return result.getResult();
	}
	
	@RequestMapping("/fm/FM0204G2R1")
	public @ResponseBody Map<String, Object> FM0204G2R1() throws Exception {
		result.setData("dlt_TAG_PARAM_KIND", FM0204Service.FM0204G2R1());
		return result.getResult();
	}
	
	@RequestMapping("/fm/FM0204G2S0")
	public @ResponseBody Map<String, Object> FM0204G2S0() throws Exception {
		Map map = FM0204Service.FM0204G2S0();
		result.setData("dma_result", map);
		return result.getResultSave();
	}
	
}
