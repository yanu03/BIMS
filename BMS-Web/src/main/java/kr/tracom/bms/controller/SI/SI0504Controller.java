package kr.tracom.bms.controller.SI;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.tracom.bms.domain.SI0504.SI0504Service;
import kr.tracom.cm.support.ControllerSupport;

@Controller
@Scope("request")
public class SI0504Controller extends ControllerSupport {
	
	@Autowired
	private SI0504Service si0504Service;
	
	@RequestMapping("/si/SI0504G0R0")
	public @ResponseBody Map<String, Object> SI0504G0R0() throws Exception {
		result.setData("dlt_BMS_ROUT_MST", si0504Service.SI0504G0R0());
		return result.getResult();
	}
	
	@RequestMapping("/si/SI0504G0K0")
	public @ResponseBody Map<String, Object> SI0504G0R1() throws Exception {
		result.setData("dma_SEQ_BMS_ROUT_MST_0", si0504Service.SI0504G0K0());
		return result.getResult();
	}
	
	@RequestMapping("/si/SI0504SHI0")
	public @ResponseBody Map<String, Object> SI0504G0R2() throws Exception {
		result.setData("dlt_searchitem", si0504Service.SI0504SHI0());
		return result.getResult();
	}
	
	@RequestMapping("/si/SI0504G1R0")
	public @ResponseBody Map<String, Object> SI0504G1R0() throws Exception {
		result.setData("dlt_BMS_TRANSCOMP_MST", si0504Service.SI0504G1R0());
		return result.getResult();
	}
	
	@RequestMapping("/si/SI0504G0S0")
	public @ResponseBody Map<String, Object> SI0504G0S0() throws Exception {
		Map map = si0504Service.SI0504G0S0();
		result.setData("dma_result", map);
		return result.getResultSave();
	}	
	
	@RequestMapping("/si/SI0504G1S0")
	public @ResponseBody Map<String, Object> SI0504G1S0() throws Exception {
		Map map = si0504Service.SI0504G1S0();
		result.setData("dma_result", map);
		return result.getResultSave();
	}	
	
	@RequestMapping("/si/SI0504P0R0")
	public @ResponseBody Map<String, Object> SI0504P0R0() throws Exception {
		result.setData("dlt_BMS_TRANSCOMP_MST", si0504Service.SI0504P0R0());
		return result.getResult();
	}
	
	@RequestMapping("/si/SI0504P1R0")
	public @ResponseBody Map<String, Object> SI0504P1R0() throws Exception {
		result.setData("dlt_BMS_REP_ROUT_MST", si0504Service.SI0504P1R0());
		return result.getResult();
	}
	
	@RequestMapping("/si/SI0504P1K0")
	public @ResponseBody Map<String, Object> SI0504P1K0() throws Exception {
		result.setData("dma_SEQ_BMS_REP_ROUT_MST_0", si0504Service.SI0504P1K0());
		return result.getResult();
	}
	
	@RequestMapping("/si/SI0504P1S0")
	public @ResponseBody Map<String, Object> SI0504P1S0() throws Exception {
		Map map = si0504Service.SI0504P1S0();
		result.setData("dma_result", map);
		return result.getResultSave();
	}
	
	@RequestMapping("/si/SI0504P2R0")
	public @ResponseBody Map<String, Object> SI0504P2R0() throws Exception {
		result.setData("dlt_BMS_STTN_MST", si0504Service.SI0504P2R0());
		return result.getResult();
	}
	
	@RequestMapping("/si/SI0504P3R0")
	public @ResponseBody Map<String, Object> SI0504P3R0() throws Exception {
		result.setData("dlt_BMS_TRANSCOMP_MST", si0504Service.SI0504P3R0());
		return result.getResult();
	}
	

}
