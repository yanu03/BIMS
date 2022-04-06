package kr.tracom.brt.controller.VH;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.tracom.brt.domain.VH0605.VH0605Service;
import kr.tracom.cm.support.ControllerSupport;

@Controller
@Scope("request")
public class VH0605Controller extends ControllerSupport {

	@Autowired
	private VH0605Service vh0605Service;

	@RequestMapping("/vh/VH0605G0R0")
	public @ResponseBody Map<String, Object> VH0605G0R0() throws Exception {
		result.setData("dlt_BMS_CRS_SIGOPER_DTL_HIS", vh0605Service.VH0605G0R0());
		return result.getResult();
	}
	
}
