package kr.tracom.brt.controller.VH;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.tracom.brt.domain.VH0601.VH0601Service;
import kr.tracom.cm.support.ControllerSupport;

@Controller
@Scope("request")
public class VH0601Controller extends ControllerSupport {

	@Autowired
	private VH0601Service vh0601Service;

	@RequestMapping("/vh/VH0601G0R0")
	public @ResponseBody Map<String, Object> VH0601G0R0() throws Exception {
		result.setData("dlt_BMS_CRS_GRP_SIGOPER_HIS", vh0601Service.VH0601G0R0());
		return result.getResult();
	}
	
}
