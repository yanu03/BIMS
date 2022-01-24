package kr.tracom.bms.controller.VD;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.tracom.bms.domain.VD0101.VD0101Service;
import kr.tracom.cm.support.ControllerSupport;

@Controller
@Scope("request")
public class VD0101Controller extends ControllerSupport {

	@Autowired
	private VD0101Service vd0101Service;
	
	@RequestMapping("/vd/VD0101G0R0")
	public @ResponseBody Map<String, Object> VD0101G0R0() throws Exception {
		result.setData("dlt_BMS_VHC_MST", vd0101Service.VD0101G0R0());
		return result.getResult();
	}
	
	@RequestMapping("/vd/VD0101SHI0")
	public @ResponseBody Map<String, Object> VD0101SHI0() throws Exception {
		result.setData("dlt_searchitem", vd0101Service.VD0101SHI0());
		return result.getResult();
	}
	
	@RequestMapping("/vd/VD0101SHI1")
	public @ResponseBody Map<String, Object> VD0101SHI1() throws Exception {
		result.setData("dlt_searchitem2", vd0101Service.VD0101SHI1());
		return result.getResult();
	}
	
	@RequestMapping("/vd/VD0101G1R0")
	public @ResponseBody Map<String, Object> VD0101G1R0() throws Exception {
		result.setData("dlt_BMS_DVC_INFO", vd0101Service.VD0101G1R0());
		return result.getResult();
	}
	
	@RequestMapping("/vd/VD0101G2R0")
	public @ResponseBody Map<String, Object> VD0101G2R0() throws Exception {
		result.setData("dlt_BMS_DVC_PARAM_CFG_INFO", vd0101Service.VD0101G2R0());
		return result.getResult();
	}
	
	@RequestMapping("/vd/VD0101G2S0")
	public @ResponseBody Map<String, Object> VD0101G2S0() throws Exception {
		Map map = vd0101Service.VD0101G2S0();
		result.setData("dma_result", map);
		return result.getResultSave();
	}
	
	@RequestMapping("/vd/VD0101P0R0")
	public @ResponseBody Map<String, Object> VD0101P0R0() throws Exception {
		result.setData("dlt_BMS_DVC_PARAM_CFG_INFO", vd0101Service.VD0101P0R0());
		return result.getResult();
	}
	
	@RequestMapping("/vd/VD0101P0SH")
	public @ResponseBody Map<String, Object> VD0101P0SH() throws Exception {
		result.setData("dlt_searchitem", vd0101Service.VD0101P0SH());
		return result.getResult();
	}
	
}
