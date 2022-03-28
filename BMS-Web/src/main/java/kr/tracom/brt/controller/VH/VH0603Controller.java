package kr.tracom.brt.controller.VH;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.tracom.brt.domain.VH0603.VH0603Service;
import kr.tracom.cm.support.ControllerSupport;

@Controller
@Scope("request")
public class VH0603Controller extends ControllerSupport {

	@Autowired
	private VH0603Service vh0603Service;

	@RequestMapping("/vh/VH0603G0R0")
	public @ResponseBody Map<String, Object> VH0603G0R0() throws Exception {
		result.setData("dlt_BMS_CRS_SIGOPER_PHASE_INFO_HIS", vh0603Service.VH0603G0R0());
		return result.getResult();
	}
	
}
