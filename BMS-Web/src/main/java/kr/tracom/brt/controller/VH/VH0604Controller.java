package kr.tracom.brt.controller.VH;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.tracom.brt.domain.VH0604.VH0604Service;
import kr.tracom.cm.support.ControllerSupport;

@Controller
@Scope("request")
public class VH0604Controller extends ControllerSupport {

	@Autowired
	private VH0604Service vh0604Service;

	@RequestMapping("/vh/VH0604G0R0")
	public @ResponseBody Map<String, Object> VH0604G0R0() throws Exception {
		result.setData("dlt_BMS_CRS_SIGOPER_TRF_HIS", vh0604Service.VH0604G0R0());
		return result.getResult();
	}
	
}
