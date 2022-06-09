package kr.tracom.bms.controller.PI;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.tracom.bms.domain.PI1100.PI1100Service;
import kr.tracom.cm.support.ControllerSupport;


@Controller
@Scope("request")
public class PI1100Controller extends ControllerSupport {

	@Autowired
	private PI1100Service pi1100Service;
	
	@RequestMapping("/pi/PI1100G0R0")
	public @ResponseBody Map<String, Object> PI1100G0R0() throws Exception {
		result.setData("dlt_BMS_TRAN_VDO_INFO", pi1100Service.PI1100G0R0());
		return result.getResult();
	}	

	@RequestMapping("/pi/PI1100G0K0")
	public @ResponseBody Map<String, Object> PI1100G0K0() throws Exception {
		result.setData("dma_SEQ_BMS_TRAN_VDO_INFO_0", pi1100Service.PI1100G0K0());
		return result.getResult();
	}
	
	@RequestMapping("/pi/PI1100G0S0")
	public @ResponseBody Map<String, Object> PI1100G0S0() throws Exception {
		Map map = pi1100Service.PI1100G0S0();
		result.setData("dma_result", map);
		return result.getResultSave();
	}
	
	@RequestMapping("/pi/PI1100SHI0")
	public @ResponseBody Map<String, Object> PI1100SHI0() throws Exception {
		result.setData("dlt_searchitem", pi1100Service.PI1100SHI0());
		return result.getResult();
	}
	
}
