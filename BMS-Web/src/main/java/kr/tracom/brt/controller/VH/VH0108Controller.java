package kr.tracom.brt.controller.VH;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.tracom.brt.domain.VH0108.VH0108Service;
import kr.tracom.cm.support.ControllerSupport;

@Controller
@Scope("request")
public class VH0108Controller extends ControllerSupport {
	
	@Autowired
	private VH0108Service VH0108Service;
	
	@RequestMapping("/vi/VH0108G0R0")
	public @ResponseBody Map<String, Object> VH0108G0R0() throws Exception {
		result.setData("dlt_BRT_OPER_VIOLT_HIS", VH0108Service.VH0108G0R0());
		return result.getResult();
	}
	
	@RequestMapping("/vi/VH0108SHI0")
	public @ResponseBody Map<String, Object> VH0108SHI0() throws Exception {
		result.setData("dlt_searchitem", VH0108Service.VH0108SHI0());
		return result.getResult();
	}	
	
}
