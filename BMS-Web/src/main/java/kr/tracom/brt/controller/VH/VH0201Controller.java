package kr.tracom.brt.controller.VH;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.tracom.brt.domain.VH0201.VH0201Service;
import kr.tracom.cm.support.ControllerSupport;

@Controller
@Scope("request")
public class VH0201Controller extends ControllerSupport {

	@Autowired
	private VH0201Service vh0201Service;

	@RequestMapping("/vh/VH0201G0R0")
	public @ResponseBody Map<String, Object> VH0201G0R0() throws Exception {
		result.setData("dlt_BMS_CRS_HIS", vh0201Service.VH0201G0R0());
		return result.getResult();
	}
	
	
}
