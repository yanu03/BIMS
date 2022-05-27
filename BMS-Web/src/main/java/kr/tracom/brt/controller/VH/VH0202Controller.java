package kr.tracom.brt.controller.VH;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.tracom.brt.domain.VH0202.VH0202Service;
import kr.tracom.cm.support.ControllerSupport;

@Controller
@Scope("request")
public class VH0202Controller extends ControllerSupport {

	@Autowired
	private VH0202Service vh0202Service;

	@RequestMapping("/vh/VH0202G0R0")
	public @ResponseBody Map<String, Object> VH0202G0R0() throws Exception {
		result.setData("dlt_BMS_LINK_HIS", vh0202Service.VH0202G0R0());
		return result.getResult();
	}
	
	
}
