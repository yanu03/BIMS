package kr.tracom.brt.controller.ST;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.tracom.brt.domain.ST0805.ST0805Service;
import kr.tracom.cm.support.ControllerSupport;

@Controller
@Scope("request")
public class ST0805Controller extends ControllerSupport {

	@Autowired
	private ST0805Service st0805Service;
	
	@RequestMapping("/st/ST0805G0R0")
	public @ResponseBody Map<String, Object> ST0805G0R0() throws Exception {
		result.setData("dlt_BMS_ROUT_MST", st0805Service.ST0805G0R0());
		return result.getResult();
	}
	
	@RequestMapping("/st/ST0805G1R0")
	public @ResponseBody Map<String, Object> ST0805G1R0() throws Exception {
		result.setData("dlt_BRT_STTN_PNCTLTY_STAT", st0805Service.ST0805G1R0());
		return result.getResult();
	}
	
	@RequestMapping("/st/ST0805G1R1")
	public @ResponseBody Map<String, Object> ST0805G1R1() throws Exception {
		result.setData("dlt_BRT_STTN_PNCTLTY_STAT_PIVOT_2", st0805Service.ST0805G1R1());
		return result.getResult();
	}
	
	@RequestMapping("/st/ST0805G2R0")
	public @ResponseBody Map<String, Object> ST0805G2R0() throws Exception {
		result.setData("dlt_BRT_STTN_PNCTLTY_STAT_PIVOT", st0805Service.ST0805G2R0());
		return result.getResult();
	}
	
	@RequestMapping("/st/selectStatHItem")
	public @ResponseBody Map<String, Object> selectStatHItem() throws Exception {
		result.setData("dlt_StatHItem", st0805Service.selectStatHItem());
		return result.getResult();
	}
}
