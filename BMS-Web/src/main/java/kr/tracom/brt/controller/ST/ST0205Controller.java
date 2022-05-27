package kr.tracom.brt.controller.ST;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.tracom.brt.domain.ST0205.ST0205Service;
import kr.tracom.cm.support.ControllerSupport;

@Controller
@Scope("request")
public class ST0205Controller extends ControllerSupport {

	@Autowired
	private ST0205Service st0205Service;
	
	@RequestMapping("/st/ST0205G0R0")
	public @ResponseBody Map<String, Object> ST0205G0R0() throws Exception {
		result.setData("dlt_BRT_ROUT_AVER_OPER_SP_STAT", st0205Service.ST0205G0R0());
		result.setData("dlt_BRT_ROUT_AVER_OPER_SP_STAT_2", st0205Service.ST0205G0R1());
		return result.getResult();
	}
	
	@RequestMapping("/st/ST0205G1R0")
	public @ResponseBody Map<String, Object> ST0205G1R0() throws Exception {
		result.setData("dlt_BRT_ROUT_AVER_OPER_SP_STAT_C1", st0205Service.ST0205G1R0());
		result.setData("dlt_BRT_ROUT_AVER_OPER_SP_STAT_P1", st0205Service.ST0205G1R1());
		return result.getResult();
	}
	
}
