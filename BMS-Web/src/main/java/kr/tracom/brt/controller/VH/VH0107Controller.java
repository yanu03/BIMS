package kr.tracom.brt.controller.VH;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.tracom.brt.domain.VH0107.VH0107Service;
import kr.tracom.cm.support.ControllerSupport;

@Controller
@Scope("request")
public class VH0107Controller extends ControllerSupport {

	@Autowired
	private VH0107Service vh0107Service;

	@RequestMapping("/vh/VH0107G0R0")
	public @ResponseBody Map<String, Object> VH0107G0R0() throws Exception {
		result.setData("dlt_BRT_STTN_STOP_HIS", vh0107Service.VH0107G0R0());
		return result.getResult();
	}
	
	
}
