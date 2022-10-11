package kr.tracom.brt.controller.VH;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.tracom.brt.domain.VH0205.VH0205Service;
import kr.tracom.cm.support.ControllerSupport;

@Controller
@Scope("request")
public class VH0205Controller extends ControllerSupport {

	@Autowired
	private VH0205Service vh0205Service;

	@RequestMapping("/vh/VH0205G0R0")
	public @ResponseBody Map<String, Object> VH0205G0R0() throws Exception {
		result.setData("dlt_BMS_FCLT_COND_PARAM_INFO_HIS", vh0205Service.VH0205G0R0());
		return result.getResult();
	}
	
	@RequestMapping("/vh/selectFcltItem")
	public @ResponseBody Map<String, Object> selectFcltItem() throws Exception {
		result.setData("dlt_FcltItem", vh0205Service.selectFcltItem());
		return result.getResult();
	}
	
	@RequestMapping("/vh/selectParamItem")
	public @ResponseBody Map<String, Object> selectParamItem() throws Exception {
		result.setData("dlt_ParamItem", vh0205Service.selectParamItem());
		return result.getResult();
	}
	
	
}
