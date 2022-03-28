package kr.tracom.cm.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.tracom.cm.domain.Crs.CrsService;
import kr.tracom.cm.support.ControllerSupport;

@Controller
@Scope("request")
public class CrsController extends ControllerSupport {

	@Autowired
	private CrsService crsService;
	
	@RequestMapping("/crs/selectCrsItem")
	public @ResponseBody Map<String, Object> selectCrsItem() throws Exception {
		result.setData("dlt_crsItem", crsService.selectCrsItem());
		return result.getResult();
	}
	
	@RequestMapping("/crs/selectGrpCrsItem")
	public @ResponseBody Map<String, Object> selectGrpCrsItem() throws Exception {
		result.setData("dlt_grpCrsItem", crsService.selectGrpCrsItem());
		return result.getResult();
	}	
	
}
