/**
 * 화면 초기 로딩
 * @lastUpdate 2016.08.28 
 * @author tracom
 * @since 2016.08.28
 */
scwin.initMainLoad = function() {
	$p.top().scwin.commonDtlList = [];
	$p.top().scwin.systemCodeList = [];
	wfm_header.getWindow().scwin.fn_getInitData();
};


/**
 * WindowContainer의 닫기 이벤트
 * @lastUpdate 2016.08.28
 * @param <String> windowTitle
 * @author tracom
 * @since 2016.08.28
 * @example
 */
scwin.closeAction = function(windowTitle) {
	if (windowTitle == "메인") {
		return false;
	}
	return true;
};

/**
 * header menu 생성
 */
scwin.setHeaderMenu = function() {
	wfm_header.getWindow().scwin.setGenerator(); //메뉴 생성
	wfm_header.getWindow().scwin.setMenuCss(); //메뉴 css 적용
}

scwin.getLayoutId = function() {
	if (typeof $p.top().$p.getComponentById("tac_layout") === "object") {
		return "T";
	} else if (typeof $p.top().$p.getComponentById("wdc_main") === "object") {
		return "M";
	}
	return null;
};

scwin.setResultMessage = function(resultData) {
	if (typeof wfm_footer !== "undefined") {
		if(resultData.statusCode=='SAVE')com.alert(resultData.message);
		else if(resultData.statusCode=='E')com.alert(resultData.message);
		/*
		var messageObj = wfm_footer.getWindow().spn_message;
		var curCode = messageObj.getUserData("tmpStatusCode");
	
		if (curCode) {
			messageObj.removeClass(curCode);
		}
		
		if (resultData.statusCode) {
			messageObj.addClass(resultData.statusCode);
		}
		
		//if(resultData.statusCode=='SAVE')com.alert(resultData.message);
		messageObj.setUserData("tmpStatusCode", resultData.statusCode);
		messageObj.setValue(resultData.message);
		wfm_footer.getWindow().grp_message.setUserData("message", resultData);
		wfm_footer.getWindow().scwin.spn_message_onclick(); 
		*/
	}
}

scwin.isMobileSize = function() {
	var size = {
		width: window.innerWidth || document.body.clientWidth,
		height: window.innerHeight || document.body.clientHeight
	};
	
	if (size.width <= 1024) {
		return true;
	} else {
		return false;
	}
}
