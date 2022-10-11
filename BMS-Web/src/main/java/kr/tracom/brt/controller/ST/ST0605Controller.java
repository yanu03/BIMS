package kr.tracom.brt.controller.ST;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.tracom.brt.domain.ST0605.ST0605Service;
import kr.tracom.cm.support.ControllerSupport;

@Controller
@Scope("request")
public class ST0605Controller extends ControllerSupport{
	
	@Autowired
	private ST0605Service ST0605Service;
	
	@RequestMapping("/st/ST0605G0R0")
	public @ResponseBody Map<String, Object> ST0605G0R0() throws Exception {
		result.setData("dlt_BMS_ROUT_MST", ST0605Service.ST0605G0R0());
		return result.getResult();
	}
	
	@RequestMapping("/st/ST0605SHI0")
	public @ResponseBody Map<String, Object> ST0605SHI0() throws Exception {
		result.setData("dlt_searchitem", ST0605Service.ST0605SHI0());
		return result.getResult();
	}
	
	@RequestMapping("/st/ST0605SHI1")
	public @ResponseBody Map<String, Object> ST0605SHI1() throws Exception {
		result.setData("dlt_searchitem2", ST0605Service.ST0605SHI1());
		return result.getResult();
	}
	
	@RequestMapping("/st/ST0605G1R0")
	public @ResponseBody Map<String, Object> ST0605G1R0() throws Exception {
		result.setData("dlt_BRT_OPER_ALLOC_PL_NODE_INFO", ST0605Service.ST0605G1R0());
		result.setData("dlt_BRT_OPER_ALLOC_PL_NODE_CNT", ST0605Service.ST0605G1CNT());
		return result.getResult();
	}
	
	@RequestMapping("/st/ST0605G0P1")
	public @ResponseBody Map<String, Object> ST0605G0P1() throws Exception {
		result.setData("dlt_BRT_OPER_ALLOC_PL_NODE_CNT", ST0605Service.ST0605G1CNT());
		return result.getResult();
	}
	
}
