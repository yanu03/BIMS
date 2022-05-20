package kr.tracom.brt.controller.VH;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.tracom.brt.domain.VH0203.VH0203Service;
import kr.tracom.cm.support.ControllerSupport;

@Controller
@Scope("request")
public class VH0203Controller extends ControllerSupport {

	@Autowired
	private VH0203Service vh0203Service;

	@RequestMapping("/vh/VH0203G0R0")
	public @ResponseBody Map<String, Object> VH0203G0R0() throws Exception {
		result.setData("dlt_BMS_ROUT_HIS", vh0203Service.VH0203G0R0());
		return result.getResult();
	}
	
	
}
