package kr.tracom.bms.controller.FM;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.tracom.bms.domain.FM0101.FM0101Service;
import kr.tracom.cm.support.ControllerSupport;

@Controller
@Scope("request")
public class FM0101Controller extends ControllerSupport {

	@Autowired
	private FM0101Service FM0101Service;
	
	@RequestMapping("/fm/FM0101SHI1")
	public @ResponseBody Map<String, Object> FM0101SHI1() throws Exception {
		result.setData("dlt_searchitem2", FM0101Service.FM0101SHI1());
		return result.getResult();
	}
	
	@RequestMapping("/fm/FM0101SHI2")
	public @ResponseBody Map<String, Object> FM0101SHI2() throws Exception {
		result.setData("dlt_searchitem3", FM0101Service.FM0101SHI2());
		return result.getResult();
	}
	
	@RequestMapping("/fm/FM0101G1R0")
	public @ResponseBody Map<String, Object> FM0101G1R0() throws Exception {
		result.setData("dlt_BMS_FCLT_INFO", FM0101Service.FM0101G1R0());
		return result.getResult();
	}
	
	@RequestMapping("/fm/FM0101G2R0")
	public @ResponseBody Map<String, Object> FM0101G2R0() throws Exception {
		result.setData("dlt_BMS_FCLT_PARAM_CFG_INFO", FM0101Service.FM0101G2R0());
		return result.getResult();
	}
	
	@RequestMapping("/fm/FM0101G2S0")
	public @ResponseBody Map<String, Object> FM0101G2S0() throws Exception {
		Map map = FM0101Service.FM0101G2S0();
		result.setData("dma_result", map);
		return result.getResultSave();
	}
	
	@RequestMapping("/fm/FM0101P0R0")
	public @ResponseBody Map<String, Object> FM0101P0R0() throws Exception {
		result.setData("dlt_BMS_FCLT_PARAM_CFG_INFO", FM0101Service.FM0101P0R0());
		return result.getResult();
	}
	
	@RequestMapping("/fm/FM0101P0SH")
	public @ResponseBody Map<String, Object> FM0101P0SH() throws Exception {
		result.setData("dlt_searchitem", FM0101Service.FM0101P0SH());
		return result.getResult();
	}
	
	@RequestMapping("/fm/FM0101G3R0")
	public @ResponseBody Map<String, Object> FM0101G3R0() throws Exception {
		result.setData("dlt_BMS_FCLT_SCH_INFO", FM0101Service.FM0101G3R0());
		return result.getResult();
	}
	
	@RequestMapping("/fm/FM0101G3S0")
	public @ResponseBody Map<String, Object> FM0101G3S0() throws Exception {
		Map map = FM0101Service.FM0101G3S0();
		result.setData("dma_result", map);
		return result.getResultSave();
	}	
	
}
