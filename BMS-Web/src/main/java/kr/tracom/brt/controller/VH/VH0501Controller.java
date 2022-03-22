package kr.tracom.brt.controller.VH;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.tracom.brt.domain.VH0501.VH0501Service;
import kr.tracom.cm.support.ControllerSupport;

@Controller
@Scope("request")
public class VH0501Controller extends ControllerSupport {

	@Autowired
	private VH0501Service vh0501Service;
	
	@RequestMapping("/vh/VH0501G0R0")
	public @ResponseBody Map<String, Object> VH0501G0R0() throws Exception {
		result.setData("dlt_BMS_INTG_HIS", vh0501Service.VH0501G0R0());
		return result.getResult();
	}	
	
	@RequestMapping("/vh/selectIntgNmItem")
	public @ResponseBody Map<String, Object> selectIntgNmItem() throws Exception {
		result.setData("dlt_intgNmItem", vh0501Service.selectIntgNmItem());
		return result.getResult();
	}
	
	@RequestMapping("/vh/selectIntgStsItem")
	public @ResponseBody Map<String, Object> selectIntgStsItem() throws Exception {
		result.setData("dlt_intgStsItem", vh0501Service.selectIntgStsItem());
		return result.getResult();
	}	
	
}
