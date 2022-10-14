package kr.tracom.brt.controller.ST;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.tracom.brt.domain.ST0201.ST0201Service;
import kr.tracom.cm.support.ControllerSupport;

@Controller
@Scope("request")
public class ST0201Controller extends ControllerSupport {

	@Autowired
	private ST0201Service st0201Service;
	
	@RequestMapping("/st/ST0201G0R0")
	public @ResponseBody Map<String, Object> ST0201G0R0() throws Exception {
		result.setData("dlt_BMS_ROUT_NODE_CMPSTN", st0201Service.ST0201G0R0());
		return result.getResult();
	}
	
	@RequestMapping("/st/ST0201G1R0")
	public @ResponseBody Map<String, Object> ST0201G1R0() throws Exception {
		result.setData("dlt_BRT_ROUT_STTN_LINK_STAT", st0201Service.ST0201G1R0());
		return result.getResult();
	}
	
	@RequestMapping("/st/ST0201G1R1")
	public @ResponseBody Map<String, Object> ST0201G1R1() throws Exception {
		result.setData("dlt_BRT_ROUT_STTN_LINK_STAT_2", st0201Service.ST0201G1R1());
		return result.getResult();
	}
	
}
