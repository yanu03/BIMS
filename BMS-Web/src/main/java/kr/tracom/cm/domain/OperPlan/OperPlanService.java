package kr.tracom.cm.domain.OperPlan;

import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.tracom.cm.domain.Common.CommonMapper;
import kr.tracom.cm.support.ServiceSupport;
import kr.tracom.cm.support.exception.MessageException;
import kr.tracom.util.CommonUtil;
import kr.tracom.util.Constants;
import kr.tracom.util.Constants.OperPlanCalc;
import kr.tracom.util.DateUtil;
import kr.tracom.util.Result;


@Service
public class OperPlanService extends ServiceSupport {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@Autowired
	private OperPlanMapper operPlanMapper;
	
	@Autowired
	private CommonMapper commonMapper;
	
	public void insertSimpleOperPlan(Map data) throws Exception {
		String wayYn = CommonUtil.notEmpty(data.get("WAY_YN"))?(String)data.get("WAY_YN"):"";
		String holiYn = CommonUtil.notEmpty(data.get("HOLI_YN"))?(String)data.get("HOLI_YN"):"";
		
		if("Y".equals(wayYn)) {				
			data.put("WAY_DIV", Constants.WayDivs.WD001); //상행
			data.put("DAY_DIV", Constants.DayDivs.DY001); //평일
			
			operPlanMapper.insertSimpleOperPlan(data);
			
			if("Y".equals(holiYn)) {
				data.put("DAY_DIV", Constants.DayDivs.DY002); //휴일
				operPlanMapper.insertSimpleOperPlan(data);
			}
			
			data.put("WAY_DIV", Constants.WayDivs.WD002); //하행
			data.put("DAY_DIV", Constants.DayDivs.DY001); //평일

			//하행일때 정류소 위치 변경
			String stSttnId = (String)data.get("ST_STTN_ID");
			//String stSttnNm = (String)data.get("ST_STTN_NM");
			String edSttnId = (String)data.get("ED_STTN_ID");
			//String edSttnNm = (String)data.get("ED_STTN_NM");
			data.put("ST_STTN_ID", edSttnId); 
			//data.put("ST_STTN_NM", edSttnNm);
			data.put("ED_STTN_ID", stSttnId);
			//data.put("ED_STTN_NM", stSttnNm);
			
			operPlanMapper.insertSimpleOperPlan(data);
			
			if("Y".equals(holiYn)) {
				data.put("DAY_DIV", Constants.DayDivs.DY002); //휴일
				operPlanMapper.insertSimpleOperPlan(data);
			}
		}
		else {
			data.put("DAY_DIV", Constants.DayDivs.DY001); //평일
			data.put("WAY_DIV", Constants.WayDivs.WD005); //없음
			operPlanMapper.insertSimpleOperPlan(data);
			
			
			if("Y".equals(holiYn)) {
				data.put("DAY_DIV", Constants.DayDivs.DY002); //휴일
				operPlanMapper.insertSimpleOperPlan(data);
			}
		}
	}
	
	public List<Map<String, Object>> selectOperPlanRoutList() throws Exception {
		
		Map<String, Object> param = getSimpleDataMap("dma_sub_search");
		
		Map<String, Object> operPlanMst = getSimpleDataMap("dma_BRT_OPER_PL_MST");
		
		List<Map<String, Object>> returnList = new ArrayList<>();
		
		param.put("CO_CD", Constants.SYS_INFO);
		param.put("DL_CD", Constants.SY012 );
		List<Map<String, Object>>  list = commonMapper.selectCommonDtlList(param);

		int averageSttnStopTm = Integer.parseInt((String) list.get(0).get("TXT_VAL1"));

		//Map<String, Object> operPlanMst = operPlanMapper.selectOperPlanMst(param);
		List<Map<String, Object>> sttnPeakInfoList = operPlanMapper.selectSttnPeakInfoOfOperPlan( param);
		List<Map<String, Object>> routOperPlan = new ArrayList<>();
		String curRoutId = "";
		String totalStopTmPeak = "00:00:00";
		String totalStopTmNonePeak = "00:00:00";
		//double totalRoutLen = 0;
		double routLen = 0;
		int routRunSec = 0;
		for(int i = 0; i < sttnPeakInfoList.size()-1; i++) {
			if(i==0) {
				routLen = CommonUtil.decimalToDouble(sttnPeakInfoList.get(i).get("ROUT_LEN"));
				continue;
			}
			/*if(curRoutId.equals((String)sttnPeakInfoList.get(i).get("ROUT_ID"))==false){
				routLen = CommonUtil.decimalToDouble(sttnPeakInfoList.get(i).get("ROUT_LEN"));
				totalRoutLen += routLen;
				Map<String, Object> map = new HashMap<String, Object>();
				
				curRoutId = (String)sttnPeakInfoList.get(i).get("ROUT_ID");
				map.put("ROUT_ID", curRoutId);
				map.put("TOTAL_STOP_TM_PEAK", totalStopTmPeak);
				map.put("TOTAL_STOP_TM_NONE_PEAK", totalStopTmNonePeak);
				totalStopTmPeak = "00:00:00";
				totalStopTmNonePeak = "00:00:00";
			}
			*/
			
			int stopTmPeak = CommonUtil.empty(sttnPeakInfoList.get(i).get("STOP_TM_PEAK"))?averageSttnStopTm:CommonUtil.decimalToInt(sttnPeakInfoList.get(i).get("STOP_TM_PEAK"));
			int stopTmNonePeak = CommonUtil.empty(sttnPeakInfoList.get(i).get("STOP_TM_NONE_PEAK"))?averageSttnStopTm:CommonUtil.decimalToInt(sttnPeakInfoList.get(i).get("STOP_TM_NONE_PEAK"));
			totalStopTmPeak = DateUtil.addSeconds(totalStopTmPeak, "HH:mm:ss", stopTmPeak);
			totalStopTmNonePeak = DateUtil.addSeconds(totalStopTmNonePeak, "HH:mm:ss", stopTmNonePeak);
		}
		routRunSec = (int)(routLen/(Constants.VHC_MAX_SPEED*1000/3600)); //노선 주행 시간
		
		int operSn = 1;
		
		//Date routStTm = DateUtil.getDate((String)operPlanMst.get("FST_TM")+":00","HH:mm:ss");	
		//Date amPeakStTm = DateUtil.getDate((String)operPlanMst.get("AM_PEAK_ST_TM")+":00","HH:mm:ss");	
		//Date pmPeakStTm = DateUtil.getDate((String)operPlanMst.get("PM_PEAK_ST_TM")+":00","HH:mm:ss");	
		String routStTm = CommonUtil.empty(operPlanMst.get("FST_TM"))?"00:00:00":(String)operPlanMst.get("FST_TM")+":00";
		String routLstTm = CommonUtil.empty(operPlanMst.get("LST_TM"))?"00:00:00":(String)operPlanMst.get("LST_TM")+":00";
		String amPeakStTm = CommonUtil.empty(operPlanMst.get("AM_PEAK_ST_TM"))?"00:00:00":(String)operPlanMst.get("AM_PEAK_ST_TM")+":00";
		String amPeakEdTm = CommonUtil.empty(operPlanMst.get("AM_PEAK_ED_TM"))?"00:00:00":(String)operPlanMst.get("AM_PEAK_ED_TM")+":00";
		String pmPeakStTm = CommonUtil.empty(operPlanMst.get("PM_PEAK_ST_TM"))?"00:00:00":(String)operPlanMst.get("PM_PEAK_ST_TM")+":00";
		String pmPeakEdTm = CommonUtil.empty(operPlanMst.get("PM_PEAK_ED_TM"))?"00:00:00":(String)operPlanMst.get("PM_PEAK_ED_TM")+":00";
		int amPeak = (int) (CommonUtil.empty(operPlanMst.get("AM_PEAK"))?0:DateUtil.minuteToSecond((String)operPlanMst.get("AM_PEAK")));
		int pmPeak = (int) (CommonUtil.empty(operPlanMst.get("PM_PEAK"))?0:DateUtil.minuteToSecond((String)operPlanMst.get("PM_PEAK")));
		int nonePeak = (int) (CommonUtil.empty(operPlanMst.get("NONE_PEAK"))?0:DateUtil.minuteToSecond((String)operPlanMst.get("NONE_PEAK")));
		
		int operIntvSec = 0; //운행간격시간(초)
		int minStopSec = 0; //최소정차시간(초)
		String routEdTm = "00:00:00";
		
		while(operSn<1000) {
			
			if(operSn==1) {
				minStopSec = (int) (DateUtil.timeToSecond(totalStopTmPeak));
			}
			else if(DateUtil.diffSeconds(routStTm, amPeakStTm,"HH:mm:ss")>=0 && DateUtil.diffSeconds(routStTm, amPeakEdTm,"HH:mm:ss")<=0) {
				operIntvSec = amPeak; 
				minStopSec = (int) (DateUtil.timeToSecond(totalStopTmPeak));
			}
			else if(DateUtil.diffSeconds(routStTm, pmPeakStTm,"HH:mm:ss")>=0 && DateUtil.diffSeconds(routStTm, pmPeakEdTm,"HH:mm:ss")<=0) {
				operIntvSec = pmPeak; 
				minStopSec = (int) (DateUtil.timeToSecond(totalStopTmNonePeak));
			}
			else {
				operIntvSec = nonePeak; 
				minStopSec = (int) (DateUtil.timeToSecond(totalStopTmPeak));
			}
			
			routStTm = DateUtil.addSeconds2(routStTm, "HH:mm:ss", operIntvSec); 
			
			int routOperSec = routRunSec + minStopSec;
			routEdTm = DateUtil.addSeconds2(routStTm, "HH:mm:ss", routOperSec); 
			
			if(DateUtil.diffSeconds(routStTm, routLstTm,"HH:mm:ss")>=0) {
				break;
			}
			Map<String, Object> reuturnMap = new HashMap<String, Object>();
			reuturnMap.put("REP_ROUT_ID", param.get("REP_ROUT_ID"));
			reuturnMap.put("DAY_DIV", param.get("DAY_DIV"));
			reuturnMap.put("WAY_DIV", param.get("WAY_DIV"));
			reuturnMap.put("OPER_SN",operSn);
			reuturnMap.put("ROUT_ST_TM",routStTm.substring(0, 5));
			reuturnMap.put("ROUT_ED_TM",routEdTm.substring(0, 5));
			//reuturnMap.put("OPER_INTV", DateUtil.secondToTime(operIntvSec).substring(0, 5));
			//reuturnMap.put("OPER_TM", DateUtil.secondToTime(routOperSec).substring(0, 5)); 
			returnList.add(reuturnMap);
			operSn ++;
			
		}
		
		return returnList;
	}
	
	
	public List<Map<String, Object>> selectOperPlanRout() throws Exception {
		Map<String, Object> map = getSimpleDataMap("dma_search");
		return operPlanMapper.selectOperPlanRout(map);
	}
	
	public List<Map<String, Object>> selectOperPlanRoutAsc() throws Exception {
		Map<String, Object> map = getSimpleDataMap("dma_sub_search");
		map.put("WAY_DIV", "WD001");
		return operPlanMapper.selectOperPlanRout(map);
	}


	public List<Map<String, Object>> selectOperAllocPlanNode() throws Exception {
		Map<String, Object> map = getSimpleDataMap("dma_sub_search");
		return operPlanMapper.selectOperAllocPlanNode(map);
	}
	
	public List<Map<String, Object>> selectOperAllocRealNode() throws Exception {
		Map<String, Object> map = getSimpleDataMap("dma_sub_search");
		return operPlanMapper.selectOperAllocRealNode(map);
	}


	public List<Map<String, Object>> selectOperPlanRoutDesc() throws Exception {
		Map<String, Object> map = getSimpleDataMap("dma_sub_search");
		map.put("WAY_DIV", "WD002");
		return operPlanMapper.selectOperPlanRout(map);
	}
	
	public List<Map<String, Object>> selectCourseList() throws Exception {
		Map<String, Object> map = getSimpleDataMap("dma_search");
		return operPlanMapper.selectCourseList(map);
	}
	
	public List<Map<String, Object>> selectOperAllocPlanCourseList() throws Exception {
		Map<String, Object> map = getSimpleDataMap("dma_sub_search");
		return operPlanMapper.selectOperAllocPlanCourseList(map);
	}
	
	public List<Map<String, Object>> selectCourseDtlList() throws Exception {
		Map<String, Object> map = getSimpleDataMap("dma_sub_search");
		String temp[] = map.get("COR_IDS").toString().replace("[","").replace("]","").replace(" ","").split(",");
		map.put("COR_IDS", temp);
		return operPlanMapper.selectCourseDtlList(map);
	}

	public List<Map<String, Object>> selectTargetCourseDtlList() throws Exception {
		Map<String, Object> map = getSimpleDataMap("dma_sub_search");
		String temp[] = map.get("COR_IDS").toString().replace("[","").replace("]","").replace(" ","").split(",");
		
		Map<String, Object> param = new HashMap<String, Object>();
		List<Map<String, Object>>  list  =new ArrayList<>();
		
		for(int i=0; i<temp.length; i++ ) {
			param.put("COR_ID",temp[i]);
			list.addAll(operPlanMapper.selectCourseDtlList(param));
		}
		return list;
	}

}
