
var btnCom = {
		// 버튼
		TYPE : {
			SINGLE_GRID : "SINGLE_GRID", 
			DUAL_GRID : "DUAL_GRID",
			SINGLE_GRID_FORM : "SINGLE_GRID_FORM", 
			DUAL_GRID_FORM : "DUAL_GRID_FORM" 
		},
		// 버튼
		BTN : {
			SEARCH : { nm : "SEARCH", value : "SCH_AH", class : "search", str : "조회", cbFnc:{}}, //검색
			ADD : { nm : "ADD", value : "SAV_AH", class : "add", str : "추가", cbFnc:{}}, //추가
			DEL : { nm : "DEL", value : "SAV_AH", class : "del", str : "삭제", cbFnc:{}}, //삭제
			CNL : { nm : "CNL", value : "SAV_AH", class : "clr", str : "취소", cbFnc:{}}, //취소
			SAVE : { nm : "SAVE", value : "SAV_AH", class : "save", str : "저장", cbFnc:{}}, //저장
			EXL_I : { nm : "EXL_I", value : "IEX_AH", class : "exli", str : "엑셀업로드", cbFnc:{}}, //엑셀업로드
			EXL : { nm : "EXL", value : "EXL_AH", class : "exl", str : "엑셀", cbFnc:{}}, //엑셀다운로드
			EXL_F : { nm : "EXL_F", value : "GEX_AH", class : "exlf", str : "엑셀양식", cbFnc:{}}, //엑셀양식
			RESERV : { nm : "RESERV", value : "FN3_AH", class : "reserv", str : "예약", cbFnc:{}}, //예약
			PLAY : { nm : "PLAY", value : "FN4_AH", class : "play", str : "실행", cbFnc:{}}, //실행
			CONFIRM_YN : { nm : "CONFIRM_YN", value : "FN5_AH", class : "confirmyn", str : "확정", cbFnc:{}}, //확정
			SETTING : { nm : "SETTING", value : "FN6_AH", class : "setting", str : "설정", cbFnc:{}}, //설정
			INIT : { nm : "INIT", value : "FN7_AH", class : "init", str : "초기화", cbFnc:{}}, //초기화
			HELP : { nm : "HELP", value : "HELP_AH", class : "help", str : "도움말", cbFnc:{}}, //도움말
			CLOSE : { nm : "CLOSE", value : "AUTH_CHECK", class : "close", str : "닫기", cbFnc:{}} //닫기
		}
};

btnCom.setMainBtn2 = function(wfm_mainBtn,type, autoOpt, usrOpt) {

	 var test = com.getParameter();
	var programAuthority = gcm.CUR_PROGRAM_AUTH;

	if(programAuthority.AUTH_CHECK != 'Y')return;
	
	for(var i in btnCom.BTN){
		try {
			var item = btnCom.BTN[i];
			if(eval("programAuthority."+item.value) == "Y"){
				var tmpParentIdx = wfm_mainBtn.getWindow().btn_main_generator.insertChild();
				var mainBtn = wfm_mainBtn.getWindow().btn_main_generator.getChild(tmpParentIdx, "btn_main");
				var str = item.str;
				mainBtn.setValue(str);
				mainBtn.addClass(item.class);
				if (typeof eval("usrOpt."+i) === "function") {
					mainBtn.bind("onclick", eval("usrOpt."+i));
				}
				else{
					if( type == btnCom.TYPE.SINGLE_GRID){
						var main = autoOpt.Main;
						if(i == btnCom.BTN.SEARCH.nm){
							item.cbFnc = function(){
								com.searchGrid(main.grid, main.srchSbm , main.savSbm);
							}
						}
						else if(i == btnCom.BTN.ADD.nm){
							item.cbFnc = function(){
								$p.executeSubmission(main.keySbm);
							}
						}
						else if(i == btnCom.BTN.DEL.nm){
							item.cbFnc = function(){
								com.delGrid(main.grid);
							}
						}
						else if(i==btnCom.BTN.CNL.nm){
							item.cbFnc = function(){
								com.clearGrid(main.grid);
							}
						}
						else if(i==btnCom.BTN.SAVE.nm){
							item.cbFnc = function(){
								com.saveGrid(main.grid, main.savSbm);
							}
						}
						else if(i==btnCom.BTN.EXL_I.nm){
							item.cbFnc = function(){
								com.exlUploadGrid(main.grid);
							}
						}
						else if(i==btnCom.BTN.EXL.nm){
							item.cbFnc = function(){
								com.exlGrid(main.grid);
							}
						}
						else if(i==btnCom.BTN.EXL_F.nm){
							item.cbFnc = function(){
								com.exlFrmGrid(main.grid);
							}
						}
						else if(i==btnCom.BTN.CLOSE.nm){
							item.cbFnc = function(){
								com.closeTab(main.grid);
							}
						}
					}
				}
				mainBtn.bind("onclick", item.cbFnc);
			}
		} catch (e) {
			
		}
	}
	return btnCom.BTN;
};


btnCom.setMainBtn = function(btnOptions, generator) {

	var programAuthority = gcm.CUR_PROGRAM_AUTH;
	
	if(programAuthority.AUTH_CHECK != 'Y')return;
	
	for(var i=0; i<btnOptions.length; i++){
		try {
			var item = btnOptions[i];
			if(eval("programAuthority."+item.auth.value) == "Y"){
				var tmpParentIdx = generator.insertChild();
				var mainBtn = generator.getChild(tmpParentIdx, "btn_main");
				var str = "";
				if(item.userStr !== "undefined" && item.userStr!="" && item.userStr != null){
					str = item.userStr;
				}
				else{
					str = item.auth.str;
				}
					mainBtn.setValue(str);
				mainBtn.addClass(item.auth.class);
				if (typeof item.cbFnc === "function") {
					mainBtn.bind("onclick", item.cbFnc); 
				}
			}
		} catch (e) {
			
		}
	}
}
