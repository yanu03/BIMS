package kr.tracom.brt.controller.ST;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.tracom.brt.domain.ST0600.ST0600Service;
import kr.tracom.cm.support.ControllerSupport;

@Controller
@Scope("request")
public class ST0600Controller extends ControllerSupport {

	@Autowired
	private ST0600Service st0600Service;
	
	@RequestMapping("/st/ST0600G0R0")
	public @ResponseBody Map<String, Object> ST0600G0R0() throws Exception {
		result.setData("dlt_BRT_OPER_DSPTCH_STAT", st0600Service.ST0600G0R0());
		result.setData("dlt_BRT_OPER_DSPTCH_STAT_2", st0600Service.ST0600G0R1());
		return result.getResult();
	}
	
	@RequestMapping("/st/ST0600G1R0")
	public @ResponseBody Map<String, Object> ST0600G1R0() throws Exception {
		result.setData("dlt_BRT_OPER_DSPTCH_STAT_C1", st0600Service.ST0600G1R0());
		result.setData("dlt_BRT_OPER_DSPTCH_STAT_P1", st0600Service.ST0600G1R1());
		return result.getResult();
	}
	
}
