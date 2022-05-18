package kr.tracom.brt.controller.ST;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.tracom.brt.domain.ST0204.ST0204Service;
import kr.tracom.cm.support.ControllerSupport;

@Controller
@Scope("request")
public class ST0204Controller extends ControllerSupport {

	@Autowired
	private ST0204Service st0204Service;
	
	@RequestMapping("/st/ST0204G0R0")
	public @ResponseBody Map<String, Object> ST0204G0R0() throws Exception {
		result.setData("dlt_BMS_ROUT_MST", st0204Service.ST0204G0R0());
		return result.getResult();
	}
	
	@RequestMapping("/st/ST0204G1R0")
	public @ResponseBody Map<String, Object> ST0204G1R0() throws Exception {
		result.setData("dlt_BRT_ROUT_AVER_OPER_SP_STAT", st0204Service.ST0204G1R0());
		result.setData("dlt_BRT_ROUT_AVER_OPER_SP_STAT_2", st0204Service.ST0204G1R1());
		return result.getResult();
	}
	
}
