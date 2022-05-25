package kr.tracom.brt.controller.VH;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.tracom.brt.domain.VH0106.VH0106Service;
import kr.tracom.cm.support.ControllerSupport;

@Controller
@Scope("request")
public class VH0106Controller extends ControllerSupport {

	@Autowired
	private VH0106Service vh0106Service;

	@RequestMapping("/vh/VH0106G0R0")
	public @ResponseBody Map<String, Object> VH0106G0R0() throws Exception {
		result.setData("dlt_BRT_OPER_END_SPOT_HIS", vh0106Service.VH0106G0R0());
		return result.getResult();
	}
	
	
}
