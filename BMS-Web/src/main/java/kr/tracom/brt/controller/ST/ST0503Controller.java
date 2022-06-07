package kr.tracom.brt.controller.ST;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.tracom.brt.domain.ST0503.ST0503Service;
import kr.tracom.cm.support.ControllerSupport;

@Controller
@Scope("request")
public class ST0503Controller extends ControllerSupport {

	@Autowired
	private ST0503Service st0503Service;
	
	@RequestMapping("/st/ST0503G0R0")
	public @ResponseBody Map<String, Object> ST0503G0R0() throws Exception {
		result.setData("dlt_BRT_DVC_STAT", st0503Service.ST0503G0R0());
		result.setData("dlt_BRT_DVC_STAT_2", st0503Service.ST0503G0R1());
		return result.getResult();
	}
	
	@RequestMapping("/st/ST0503G1R0")
	public @ResponseBody Map<String, Object> ST0503G1R0() throws Exception {
		result.setData("dlt_BRT_DVC_STAT_C1", st0503Service.ST0503G1R0());
		result.setData("dlt_BRT_DVC_STAT_P1", st0503Service.ST0503G1R1());
		return result.getResult();
	}
	
}
