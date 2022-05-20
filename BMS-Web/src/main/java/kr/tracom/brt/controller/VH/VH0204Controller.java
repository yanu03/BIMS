package kr.tracom.brt.controller.VH;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.tracom.brt.domain.VH0204.VH0204Service;
import kr.tracom.cm.support.ControllerSupport;

@Controller
@Scope("request")
public class VH0204Controller extends ControllerSupport {

	@Autowired
	private VH0204Service vh0204Service;

	@RequestMapping("/vh/VH0204G0R0")
	public @ResponseBody Map<String, Object> VH0204G0R0() throws Exception {
		result.setData("dlt_BMS_ROUT_HIS", vh0204Service.VH0204G0R0());
		return result.getResult();
	}
	
	
}
