package kr.tracom.brt.controller.VH;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.tracom.brt.domain.VH0502.VH0502Service;
import kr.tracom.cm.support.ControllerSupport;

@Controller
@Scope("request")
public class VH0502Controller extends ControllerSupport {

	@Autowired
	private VH0502Service vh0502Service;

	@RequestMapping("/vh/VH0502G0R0")
	public @ResponseBody Map<String, Object> VH0502G0R0() throws Exception {
		result.setData("dlt_BMS_API_CALL_HIS", vh0502Service.VH0502G0R0());
		return result.getResult();
	}
	
}
