package kr.tracom.brt.controller.ST;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.tracom.brt.domain.ST0804.ST0804Service;
import kr.tracom.cm.support.ControllerSupport;

@Controller
@Scope("request")
public class ST0804Controller extends ControllerSupport {

	@Autowired
	private ST0804Service st0804Service;
	
	@RequestMapping("/st/ST0804G0R0")
	public @ResponseBody Map<String, Object> ST0804G0R0() throws Exception {
		result.setData("dlt_BRT_OPER_PNCTLTY_STAT", st0804Service.ST0804G0R0());
		result.setData("dlt_BRT_OPER_PNCTLTY_STAT_2", st0804Service.ST0804G0R1());
		return result.getResult();
	}
	
	@RequestMapping("/st/ST0804G1R0")
	public @ResponseBody Map<String, Object> ST0804G1R0() throws Exception {
		result.setData("dlt_BRT_OPER_PNCTLTY_STAT_C1", st0804Service.ST0804G1R0());
		result.setData("dlt_BRT_OPER_PNCTLTY_STAT_P1", st0804Service.ST0804G1R1());
		return result.getResult();
	}
	
}
