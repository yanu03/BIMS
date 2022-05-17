package kr.tracom.bms.controller.VD;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.tracom.bms.domain.VD0204.VD0204Service;
import kr.tracom.cm.support.ControllerSupport;

@Controller
@Scope("request")
public class VD0204Controller extends ControllerSupport {

	@Autowired
	private VD0204Service vd0204Service;
	
	@RequestMapping("/vd/VD0204G0R0")
	public @ResponseBody Map<String, Object> VD0204G0R0() throws Exception {
		result.setData("dlt_BMS_DVC_COND_PARAM_INFO", vd0204Service.VD0204G0R0());
		return result.getResult();
	}
	
	@RequestMapping("/vd/VD0204G0R1")
	public @ResponseBody Map<String, Object> VD0204G0R1() throws Exception {
		result.setData("dlt_PARAM_KIND", vd0204Service.VD0204G0R1());
		return result.getResult();
	}
	
	@RequestMapping("/vd/VD0204G0R2")
	public @ResponseBody Map<String, Object> VD0204G0R2() throws Exception {
		result.setData("dlt_CTL_PARAM_KIND", vd0204Service.VD0204G0R2());
		return result.getResult();
	}
	
	@RequestMapping("/vd/VD0204SHI0")
	public @ResponseBody Map<String, Object> VD0204SHI0() throws Exception {
		result.setData("dlt_searchitem", vd0204Service.VD0204SHI0());
		return result.getResult();
	}
	
	@RequestMapping("/vd/VD0204SHI1")
	public @ResponseBody Map<String, Object> VD0204SHI1() throws Exception {
		result.setData("dlt_searchitem2", vd0204Service.VD0204SHI1());
		return result.getResult();
	}
	
	@RequestMapping("/vd/VD0204G1R0")
	public @ResponseBody Map<String, Object> VD0204G1R0() throws Exception {
		result.setData("dlt_PLF_IMP_VER_INFO", vd0204Service.VD0204G1R0());
		return result.getResult();
	}
	
	@RequestMapping("/vd/VD0204G2R0")
	public @ResponseBody Map<String, Object> VD0204G2R0() throws Exception {
		result.setData("dlt_BMS_DVC_TAG_COND_PARAM_INFO", vd0204Service.VD0204G2R0());
		return result.getResult();
	}
	
	@RequestMapping("/vd/VD0204G2R1")
	public @ResponseBody Map<String, Object> VD0204G2R1() throws Exception {
		result.setData("dlt_TAG_PARAM_KIND", vd0204Service.VD0204G2R1());
		return result.getResult();
	}
	
	@RequestMapping("/vd/VD0204G2S0")
	public @ResponseBody Map<String, Object> VD0204G2S0() throws Exception {
		Map map = vd0204Service.VD0204G2S0();
		result.setData("dma_result", map);
		return result.getResultSave();
	}
	
}
