package kr.tracom.brt.controller.ST;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.tracom.brt.domain.ST0504.ST0504Service;
import kr.tracom.cm.support.ControllerSupport;

@Controller
@Scope("request")
public class ST0504Controller extends ControllerSupport {

	@Autowired
	private ST0504Service st0504Service;
	
	@RequestMapping("/st/ST0504G0R0")
	public @ResponseBody Map<String, Object> ST0504G0R0() throws Exception {
		result.setData("dlt_BRT_FCLT_STAT", st0504Service.ST0504G0R0());
		result.setData("dlt_BRT_FCLT_STAT_2", st0504Service.ST0504G0R1());
		return result.getResult();
	}
	
	@RequestMapping("/st/ST0504G1R0")
	public @ResponseBody Map<String, Object> ST0504G1R0() throws Exception {
		result.setData("dlt_BRT_FCLT_STAT_C1", st0504Service.ST0504G1R0());
		result.setData("dlt_BRT_FCLT_STAT_P1", st0504Service.ST0504G1R1());
		return result.getResult();
	}
	
}
