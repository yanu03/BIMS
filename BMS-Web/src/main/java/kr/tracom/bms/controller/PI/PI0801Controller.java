package kr.tracom.bms.controller.PI;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.tracom.bms.domain.PI0801.PI0801Service;
import kr.tracom.cm.support.ControllerSupport;

@Controller
@Scope("request")
public class PI0801Controller extends ControllerSupport {
	
	@Autowired
	private PI0801Service pi0801Service;
	
	@RequestMapping("/pi/PI0801G0R0")
	public @ResponseBody Map<String, Object> PI0801G0R0() throws Exception {
		result.setData("dlt_BMS_INNER_LED_INFO", pi0801Service.PI0801G0R0());
		return result.getResult();
	}
	
	@RequestMapping("/pi/PI0801G1R0")
	public @ResponseBody Map<String, Object> PI0801G1R0() throws Exception {
		result.setData("dlt_BMS_VOC_INFO", pi0801Service.PI0801G1R0());
		return result.getResult();
	}
	
	@RequestMapping("/pi/PI0801G2R0")
	public @ResponseBody Map<String, Object> PI0801G2R0() throws Exception {
		result.setData("dlt_BMS_VOC_INFO2", pi0801Service.PI0801G2R0());
		return result.getResult();
	}	

	@RequestMapping("/pi/PI0801SHI0")
	public @ResponseBody Map<String, Object> PI0801SHI0() throws Exception {
		result.setData("dlt_searchitem", pi0801Service.PI0801SHI0());
		return result.getResult();
	}	
	
	@RequestMapping("/pi/PI0801G0S0")
	public @ResponseBody Map<String, Object> PI0801G0S0() throws Exception {
		Map map = pi0801Service.PI0801G0S0();
		result.setData("dma_result", map);
		return result.getResultSave();
	}	
	
	@RequestMapping("/pi/PI0801G0K0")
	public @ResponseBody Map<String, Object> PI0801G0K0() throws Exception {
		result.setData("dma_SEQ_BMS_INNER_LED_INFO_0", pi0801Service.PI0801G0K0());
		return result.getResult();
	}
	
	@RequestMapping("/pi/PI0801G1S0")
	public @ResponseBody Map<String, Object> PI0801G1S0() throws Exception{
		Map map = pi0801Service.PI0801G1S0();
		result.setData("dma_result", map);
		return result.getResultSave();
	}
	
	@RequestMapping("/pi/PI0801G1K0")
	public @ResponseBody Map<String, Object> PI0801G1K0() throws Exception{
		result.setData("dma_SEQ_BMS_VOC_INFO_0", pi0801Service.PI0801G1K0());
		return result.getResult();
	}	

	@RequestMapping("/pi/PI0801SHI1")
	public @ResponseBody Map<String, Object> PI0801SHI1() throws Exception {
		result.setData("dlt_searchitem", pi0801Service.PI0801SHI1());
		return result.getResult();
	}
	
	@RequestMapping("/pi/PI0801G2S0")
	public @ResponseBody Map<String, Object> PI0801G2S0() throws Exception{
		result.setData("result", pi0801Service.PI0801G2S0());
		return result.getResultSave();
	}
	
	@RequestMapping("/pi/PI0801SHI2")
	public @ResponseBody Map<String, Object> PI0801SHI2() throws Exception{
		result.setData("dlt_searchitem", pi0801Service.PI0801SHI2());
		return result.getResult();
	}
	
	@RequestMapping("/pi/PI0801G2K0")
	public @ResponseBody Map<String, Object> PI0801G2K0() throws Exception{
		result.setData("dma_SEQ_BMS_VOC_INFO_3", pi0801Service.PI0801G2K0());
		return result.getResult();
	}	
}
