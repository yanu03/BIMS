//노선 맵용 전역변수
var routMap = {
	mapInfo : [],
	NODE_TYPE : {
		CROSS : "NT001",
		BUSSTOP : "NT002",
		NORMAL : "NT003",
		GARAGE : "NT004",
		VERTEX : "NT005",
		SOUND : "NT006",
		SIGNAL : "NT008",
		DISP : "NT009"
	},
	MAX_NODE_CNT : 800,
	LIMIT_SPEED : 50,
	MIN_LEVEL : 1,
	MAX_LEVEL : 7
}




var RoutMAP = function(){
	this.map = {};
	this.polylines = [];
	this.polygons = [];
	this.markers = [];
	this.markers_user = [];
	this.infoWindow = null;
	this.infoArr = [];
	this.overlay = null;
	this.overArr = [];
	// 반경 표시용 원 배열
	this.circles = []; 
	// 노드 드로잉 배열
	this.nodes = [];
	this.draggable = false;
	this.dragging = false; //드래그 중인지
	this.isChange = false;
	this.dispCheck = "";
	this.nodeChangeCb = {};
	this.isMove = true;
	this.selectedMarker = null;
	this.selectedIndex = -1;
	this.oldSelectedIndex = -1;
	this.linkMode = false;
	this.mockLinkMode = false;
	this.busMarkers = [];
	this.busOverlay = null;
	this.busOverArr = [];
	this.busClickOverlay = null;
	this.busClickOverArr = [];
	this.selectedBusMarker = null;
	this.isSound = false;
	this.isShowCrs = "on";
	this.isShowNormal = "on";
	this.isShowVertex = "on";
	this.isShowSignal = "on";
	this.isShowRoad = "on";
	this.isShowAbnormal = "on";
	this.isShowSound = "on";
	this.isShowIncdnt = "";
	this.isShowViolt = "";
	this.onMarkerClick = {};
	this.clickOverlay = null;
	this.clickOverArr = [];
	this.rightClickOverlay = null;
	this.dsptchOverlay = null;
	this.dsptchOverArr = [];
	this.eventOverlay = null;
	this.eventOverArr = [];
	this.isDsptch = "off";
	this.divDsptch = "";
	this.isEvent = "off";
	this.divEvent = "";
	this.isOverLayHidden = false;
	this.isFirst = true;
}

/**
 * 노선 맵 초기화

 * @param mapId : 생성할 map Id
 * @param options : 맵의 옵션
 */
routMap.initMap = function(mapId,options) {
	
	var mapContainer = document.getElementById(mapId);
	var mapOptions = {
		center : new kakao.maps.LatLng(36.502212, 127.256300),
		disableDoubleClickZoom: true,
		level : 5,
		minLevel : routMap.MIN_LEVEL,
		maxLevel : routMap.MAX_LEVEL
	};
	
	if(typeof options.level !== "undefined") mapOptions.level = options.level;
	
    mapContainer.style.width = options.width;
    mapContainer.style.height = options.height; 
	
	var map = new kakao.maps.Map(mapContainer, mapOptions); //지도 생성 및 객체 리턴

	if(options.onClick) {
		kakao.maps.event.addListener(map, 'click', function(e) {
			
			options.onClick(e);
		});
	} 
	
	else {
		//map.addListener("click", onClick);
	}

	routMap.mapInfo[mapId] = new RoutMAP();
	routMap.mapInfo[mapId].map=map;
	routMap.mapInfo[mapId].nodeChangeCb = options.nodeChangeCb;
	routMap.mapInfo[mapId].draggable=options.draggable;
	routMap.mapInfo[mapId].isChange=options.isChange;
	routMap.mapInfo[mapId].linkMode=options.linkMode;
	routMap.mapInfo[mapId].mockLinkMode=options.mockLinkMode;
	routMap.mapInfo[mapId].isSound=options.isSound;
	routMap.mapInfo[mapId].onMarkerClick=options.onMarkerClick;
	routMap.mapInfo[mapId].isOverLayHidden=options.isOverLayHidden;
	
	//routMap.initDisplay(mapId);

	return map;
}

/**
 * 노선 맵 복제

 * @param mapId : 복제 map Id
 * @param options : 맵의 옵션
 * @param map : 복제 대상 map
 */
routMap.cronMap = function(mapId,options, map) {
	
//	var mapContainer = document.getElementById(mapId);
//	var mapOptions = {
//		center : new kakao.maps.LatLng(36.502212, 127.256300),
//		disableDoubleClickZoom: true,
//		level : 5
//	};
//	
//    mapContainer.style.width = options.width;
//    mapContainer.style.height = options.height; 
	

	if(options.onClick) {
		kakao.maps.event.addListener(map, 'click', function(e) {
			
			options.onClick(e);
		});
	} else {
		//map.addListener("click", onClick);
	}

	routMap.mapInfo[mapId] = new RoutMAP();
	routMap.mapInfo[mapId].map=map;
	routMap.mapInfo[mapId].nodeChangeCb = options.nodeChangeCb;
	routMap.mapInfo[mapId].draggable=options.draggable;
	routMap.mapInfo[mapId].linkMode=options.linkMode;
	routMap.mapInfo[mapId].isSound=options.isSound;
	routMap.mapInfo[mapId].onMarkerClick=options.onMarkerClick;
	routMap.mapInfo[mapId].isOverLayHidden=options.isOverLayHidden;
	
	//routMap.initDisplay(mapId);

	return map;
}

/**
 * 노선 맵 초기화

 * @param mapId : 생성할 map Id
 * @param options : 맵의 옵션
 */
routMap.initMap2 = function(mapId,options) {
	
	var mapContainer = document.getElementById(mapId);
	var mapOptions = {
			center : new kakao.maps.LatLng(36.502212, 127.256300),
			disableDoubleClickZoom: true,
			level : 2
	};
	
	mapContainer.style.width = options.width;
	mapContainer.style.height = options.height; 
	
	var map = new kakao.maps.Map(mapContainer, mapOptions); //지도 생성 및 객체 리턴
	
	if(options.onClick) {
		kakao.maps.event.addListener(map, 'click', function(e) {
			
			options.onClick(e);
		});
	} else {
		//map.addListener("click", onClick);
	}
	
	routMap.mapInfo[mapId] = new RoutMAP();
	routMap.mapInfo[mapId].map=map;
	routMap.mapInfo[mapId].nodeChangeCb = options.nodeChangeCb;
	routMap.mapInfo[mapId].draggable=options.draggable;
	routMap.mapInfo[mapId].linkMode=options.linkMode;
	routMap.mapInfo[mapId].isSound=options.isSound;
	routMap.mapInfo[mapId].isOverLayHidden=options.isOverLayHidden;
	
	//routMap.initDisplay(mapId);
	
	return map;
} 

routMap.destroy = function(mapId) {
	if(com.isEmpty(routMap.mapInfo[mapId].map)==false)
		routMap.mapInfo[mapId].map.destroy();
}
/**
 * 특정 위/경도 중심으로 맵 센터 이동

 * @param mapId : 대상 map Id
 * @param lat : 위도
 * @param lon : 경도
 */
routMap.moveMap = function(mapId, lat, lon) {
	if(routMap.mapInfo[mapId].isMove){
		routMap.mapInfo[mapId].map.setCenter(new kakao.maps.LatLng(lat, lon));
	}
}

/**
 * 마커 전체 삭제

 * @param mapId : 대상 map Id
 */
routMap.removeMarkers = function(mapId) {
	if(routMap.mapInfo[mapId].markers != null && routMap.mapInfo[mapId].markers.length != 0) {
		for (var i = 0; i < routMap.mapInfo[mapId].markers.length; i++) {
			routMap.mapInfo[mapId].markers[i].setMap(null);
		}
	}
	
	routMap.mapInfo[mapId].markers = [];
}

/*routMap.removeMarkers2 = function(mapId) {
	if(routMap.mapInfo[mapId].markers != null && routMap.mapInfo[mapId].markers.length != 0) {
		for (var i = 0; i < routMap.mapInfo[mapId].markers.length; i++) {
			routMap.mapInfo[mapId].markers[i].setMap(null);
		}
	}
	
	routMap.mapInfo[mapId].markers = [];
}*/

/**
 * 버스 마커 전체 삭제

 * @param mapId : 대상 map Id
 */
routMap.removeBusMarkers = function(mapId) {
	if(routMap.mapInfo[mapId].busMarkers != null && routMap.mapInfo[mapId].busMarkers.length != 0) {
		for (var i = 0; i < routMap.mapInfo[mapId].busMarkers.length; i++) {
			routMap.mapInfo[mapId].busMarkers[i].setMap(null);
		}
	}
	
	routMap.mapInfo[mapId].busMarkers = [];
}

/**
 * 특정 index의 버스 마커 삭제

 * @param mapId : 대상 map Id
 * @param index : 대상 버스 마커 index
 */
routMap.removeIndexBusMarker = function(mapId,index) {
	if(routMap.mapInfo[mapId].busMarkers != null && routMap.mapInfo[mapId].busMarkers.length != 0) {
		routMap.mapInfo[mapId].busMarkers[index].setMap(null);
	}
}

/**
 * 사용 안함 전체 user 마커 삭제

 * @param mapId : 대상 map Id
 */
/****/
routMap.removeMarkers_user = function(mapId) {
	for (var i = 0; i < routMap.mapInfo[mapId].markers_user.length; i++) {
		routMap.mapInfo[mapId].markers_user[i].setMap(null);
	}
	routMap.mapInfo[mapId].markers_user = [];
}


/**
 * 마커그리기

 * @param mapId : 대상 map Id
 * @param lat : 위도
 * @param lon : 경도
 */
routMap.mapMarker = function(mapId, lat, lng){
	routMap.removeMarkers(mapId);
	
	var marker = new kakao.maps.Marker({
		position: new kakao.maps.LatLng(lat, lng), //Marker의 중심좌표 설정.
	});
	marker.setMap(routMap.mapInfo[mapId].map); //Marker가 표시될 Map 설정..
	routMap.mapInfo[mapId].map.setCenter(new kakao.maps.LatLng(lat,lng));
	routMap.mapInfo[mapId].markers.push(marker);
}

/**
 * 배열 선그리기

 * @param mapId : 대상 map Id
 * @param lat_arr : 위도 배열
 * @param lng_arr : 경도 배열
 * @param color : 선 색
 */
routMap.drawLineArr = function(mapId, lat_arr, lng_arr, color){
	var path = [];
	for(var i=0; i < lat_arr.length; i++){
		path.push(new kakao.maps.LatLng(lat_arr[i], lng_arr[i]));
	}
	
	var polyline = new kakao.maps.Polyline({
		path: path,
		strokeColor: color, // 라인 색상
		strokeWeight: 7, // 라인 두게
		strokeStyle:'solid',
		strokeOpacity: 0.8
	});
	
	polyline.setMap(routMap.mapInfo[mapId].map);
	routMap.mapInfo[mapId].polylines.push(polyline);
}

/**
 * 선그리기

 * @param mapId : 대상 map Id
 * @param first : 시작 위/경도
 * @param last : 종료 위/경도
 * @param color : 선 색
 */
routMap.drawLine = function(mapId, first, last, color, eventKinds, clickEvent, rowIndex, dataCheckFunc){
	var path = [];
	path.push(new kakao.maps.LatLng(first.GPS_Y, first.GPS_X));
	path.push(new kakao.maps.LatLng(last.GPS_Y, last.GPS_X));
	
	var dataCheck = false;
	if(typeof dataCheckFunc != "undefined") {
		dataCheck = dataCheckFunc(dataCheck, rowIndex);
		if(dataCheck && color != "#1e90ff") {
			color = "#FF005E";
		}
	}
	
	var polyline = new kakao.maps.Polyline({
		path: path,
		strokeColor: color, // 라인 색상
		strokeWeight: 5, // 라인 두께
		strokeStyle:'solid',
		strokeOpacity: 0.8,
		//endArrow: true
	});

	if(typeof eventKinds != "undefined") {
		
		kakao.maps.event.addListener(polyline, 'mouseover', function(mouseEvent) {
			if(eventKinds == "addSubGrid") {
				polyline.setOptions({
					strokeColor : "#cd6c15"
				});
			}			
		});
		
		kakao.maps.event.addListener(polyline, 'mouseout', function(mouseEvent) {
			
			if(eventKinds == "addSubGrid") {
				polyline.setOptions({
					strokeColor : color
				});
			}
			
		});
		
		kakao.maps.event.addListener(polyline, 'mousemove', function(mouseEvent) {
			
		});
		
	    kakao.maps.event.addListener(polyline, 'mousedown', function(mouseEvent) {
	    	
	    });
		
		kakao.maps.event.addListener(polyline, 'click', function(mouseEvent) {
			if(!dataCheck) {
				clickEvent(rowIndex);
			}
		});	
		
	}
	polyline.setMap(routMap.mapInfo[mapId].map);
	
	routMap.mapInfo[mapId].polylines.push(polyline);
}

/**
 * 선삭제

 * @param mapId : 대상 map Id
 */
routMap.deleteLine = function(mapId){
	if(routMap.mapInfo[mapId].polylines != null && routMap.mapInfo[mapId].polylines.length != 0) {
		for (var i = 0; i < routMap.mapInfo[mapId].polylines.length; i++) {
			routMap.mapInfo[mapId].polylines[i].setMap(null);
		}
	}
	
	routMap.mapInfo[mapId].polylines = [];
}

/**
 * 다각형삭제

 * @param mapId : 대상 map Id
 */
routMap.deletePolygon = function(mapId){
	if(routMap.mapInfo[mapId].polygons != null && routMap.mapInfo[mapId].polygons.length != 0) {
		for (var i = 0; i < routMap.mapInfo[mapId].polygons.length; i++) {
			routMap.mapInfo[mapId].polygons[i].setMap(null);
		}
	}
	
	routMap.mapInfo[mapId].polygons = [];
}

/**
 * 마커여러개추가

 * @param mapId : 대상 map Id
 * @param lat_arr : 위도 배열
 * @param lng_arr : 경도 배열
 * @param id_arr :id 배열
 */
routMap.addMarkers = function(mapId, lat_arr, lng_arr, id_arr) {
	for(var i=0; i < lat_arr.length; i++){
		var marker = new kakao.maps.Marker({
			position: new kakao.maps.LatLng(lat_arr[i], lng_arr[i]), //Marker의 중심좌표 설정.
			//label: id_arr[i]//, //Marker의 라벨.
			//label: "<span style='background-color: #46414E; color:white; padding: 3px;'>" + id_arr[i] + "</span>"
			//icon:"http://tmapapi.sktelecom.com//resources/images/common/pin_car.png"
		});
		marker.setMap(routMap.mapInfo[mapId].map); //Marker가 표시될 Map 설정.
		routMap.mapInfo[mapId].markers.push(marker);
	}
}

/**
 * 지도상의 노드 클릭시 호출되는 함수

 * @param mapId : 대상 map Id
 * @param idx : 클릭한 index
 */
routMap.nodeChange = function(mapId, idx){
	routMap.mapInfo[mapId].nodeChangeCb(idx);
}

/**
 * 지도상의 마우스 커서 세팅

 * @param mapId : 대상 map Id
 * @param cursor : 마우스 커서
 */
routMap.setMapCursor = function(mapId, cursor){
	routMap.mapInfo[mapId].map.setCursor(cursor);
}

/**
 * 노드마커 추가

 * @param mapId : 대상 map Id
 * @param data : 그리드의 data
 * @param grid : 대상 그리드
 * @param idx : 마커의 index
 * @param focusIdx : 포커스 index
 * @param idx : 마커의 index
 * @param isOverLayHidden : 마커내의 overlay 숨기기 여부
 */
routMap.addMarkerInter = function(mapId, data, grid, idx, focusIdx, isOverLayHidden) {
	// 마커 이미지의 이미지 크기 입니다
	var imageSize = new kakao.maps.Size(19, 28);  
	var markerImage = null;
	var markerOverImage = null;
	var markerSelImage = null;
	
	var zIndex= -1;
	if(data.NODE_TYPE == routMap.NODE_TYPE.CROSS){
		zIndex = 1;
		imageSize = new kakao.maps.Size(19, 19);
		markerImage = new kakao.maps.MarkerImage("/cm/images/tmap/cross.png", imageSize);
		markerSelImage = new kakao.maps.MarkerImage("/cm/images/tmap/cross_selected.png", imageSize);
	}
	else if(data.NODE_TYPE == routMap.NODE_TYPE.BUSSTOP) {
		zIndex = 2;
		imageSize = new kakao.maps.Size(25, 24);
		markerImage = new kakao.maps.MarkerImage("/cm/images/tmap/busstop.png", imageSize);
		markerSelImage = new kakao.maps.MarkerImage("/cm/images/tmap/busstop_selected.png", imageSize);
	}
	else if(data.NODE_TYPE == routMap.NODE_TYPE.VERTEX){
		imageSize = new kakao.maps.Size(12, 12); 
		markerImage = new kakao.maps.MarkerImage("/cm/images/tmap/vertex.png", imageSize);
		markerSelImage = new kakao.maps.MarkerImage("/cm/images/tmap/vertex_selected.png", imageSize);
	}
	else if(data.NODE_TYPE == routMap.NODE_TYPE.DISP){
		imageSize = new kakao.maps.Size(12, 12); 
		markerImage = new kakao.maps.MarkerImage("/cm/images/tmap/vertex.png", imageSize);
		markerSelImage = new kakao.maps.MarkerImage("/cm/images/tmap/vertex_selected.png", imageSize);
	}
	else if(data.NODE_TYPE == routMap.NODE_TYPE.SIGNAL){
		imageSize = new kakao.maps.Size(20, 12); 
		markerImage = new kakao.maps.MarkerImage("/cm/images/tmap/light_green.png", imageSize);
		markerSelImage = new kakao.maps.MarkerImage("/cm/images/tmap/light_red.png", imageSize);
	}	
	else if(data.NODE_TYPE == routMap.NODE_TYPE.SOUND){
		imageSize = new kakao.maps.Size(15, 20); 
		markerImage = new kakao.maps.MarkerImage("/cm/images/tmap/voice_node.png", imageSize);
		markerSelImage = new kakao.maps.MarkerImage("/cm/images/tmap/voice_node_selected.png", imageSize);
	}
	else {
		imageSize = new kakao.maps.Size(19, 28); 
		markerImage = new kakao.maps.MarkerImage("/cm/images/tmap/road_trans.png", imageSize);
		markerSelImage = new kakao.maps.MarkerImage("/cm/images/tmap/road_selected.png", imageSize);
	}
	
	var marker = null;
	if(idx==focusIdx) {
		zIndex = 3;
		// 마커 이미지를 생성합니다    
		marker = new kakao.maps.Marker({
			position : new kakao.maps.LatLng(data.GPS_Y, data.GPS_X), // Marker의 중심좌표 // 설정.
			//title : data.label, // Marker의 라벨.
			image : markerSelImage,
			draggable : data.draggable,
			zIndex: zIndex
		});
		routMap.mapInfo[mapId].selectedMarker = marker;
	}
	else {
		// 마커 이미지를 생성합니다    
		marker = new kakao.maps.Marker({
			position : new kakao.maps.LatLng(data.GPS_Y, data.GPS_X), // Marker의 중심좌표 // 설정.
			//title : data.label, // Marker의 라벨.
			image : markerImage,
			draggable : data.draggable,
			zIndex: zIndex
		});
	}

	marker.normalImage = markerImage;


	var infoWindow = null;
		
	var overlay = null;
	if(data.NODE_TYPE != routMap.NODE_TYPE.BUSSTOP&&data.NODE_TYPE != routMap.NODE_TYPE.CROSS){
//		normal 노드 오버레이 수정하였습니다. (0121 양현우)
//		var msg = "<div class = 'customoverlay'>"
			var msg = "<div class = 'customoverlay normal'>"
					+ "<span class = 'map_title' style=''>" + data.NODE_NM + "</span>" 
					+ "</div>";	
					//+  "<span class = '' style='font-size: 12px; margin-left:2px; margin-bottom:2px; display:block;'>"+ data.GPS_Y + "," + data.GPS_X +"</span>"
		
		//infoWindow = new kakao.maps.InfoWindow({
		//	content: msg, //Popup 표시될 text
		//	zIndex : 4
		//});
		overlay = new kakao.maps.CustomOverlay({
			content: msg,
			position: marker.getPosition(),
			zIndex : 4
		});
		
	}
	else {
		var msg = "";
		if(data.NODE_TYPE == routMap.NODE_TYPE.BUSSTOP){
			 msg = "<div class = 'customoverlay busstop'>";
		}
		else if(data.NODE_TYPE == routMap.NODE_TYPE.CROSS){
			msg = "<div class = 'customoverlay cross'>";
		}
		if(data.isChange){
			
			if(data.NODE_TYPE == routMap.NODE_TYPE.CROSS){
				msg += "<span class = 'map_title' style=''>" + data.NODE_NM
					+ "&nbsp;<a href='javascript:void(0);' onclick='routMap.nodeChange(\"" + mapId+"\","+ idx + ");' style='color:#90f4f9' target='_self'>변경</a> </span>"
					+ "</div>";	
			}
			else {
				msg += "<span class = 'map_title' style=''>" + data.NODE_NM
				+ "&nbsp;<a href='javascript:void(0);' onclick='routMap.nodeChange(\"" + mapId+"\","+ idx + ");' style='color:blue' target='_self'>변경</a> </span>"
				+ "</div>";	
			}
				//+ "<span class = '' style='font-size: 12px; margin-left:2px; margin-bottom:2px; display:block;'>"+ data.GPS_Y + "," + data.GPS_X +"</span>"
		}
		else {
			msg += "<span class = 'map_title' style=''>" + data.NODE_NM + "</span>"
				+ "</div>";
				//+ "<span class = '' style='font-size: 12px; margin-left:2px; margin-bottom:2px; display:block;'>"+ data.GPS_Y + "," + data.GPS_X +"</span>"
		 }
		
		/*infoWindow = new kakao.maps.InfoWindow({
		content: msg, //Popup 표시될 text
		zIndex : zIndex
		});
		
		infoWindow.open(routMap.mapInfo[mapId].map, marker);*/
		
		overlay = new kakao.maps.CustomOverlay({
			content: msg,
			map: routMap.mapInfo[mapId].map,
			position: marker.getPosition(),
			zIndex : zIndex
		});
	}
	//routMap.mapInfo[mapId].infoWindow.setMap(routMap.mapInfo[mapId].map); 
	routMap.mapInfo[mapId].overlay = overlay;
	routMap.mapInfo[mapId].overArr.push(routMap.mapInfo[mapId].overlay);
	
	// 마커에 click 이벤트를 등록합니다
	kakao.maps.event.addListener(marker, 'click', function() {
		//infoWindow.close();

		if(routMap.mapInfo[mapId].dragging){
			data.click({
				marker: marker,
				nodeId: data.NODE_ID,
				index: data.index
			});
			//routMap.focusNode(mapId, grid,idx);
			
			routMap.mapInfo[mapId].isMove = false;
			/*routMap.mapInfo[mapId].oldSelectedIndex = routMap.mapInfo[mapId].selectedIndex; 
			routMap.mapInfo[mapId].selectedIndex = idx;*/
			
			grid.setFocusedCell(data.realIndex,"NODE_ID");
			return;
		}
		// 클릭된 마커가 없고, click 마커가 클릭된 마커가 아니면
		// 마커의 이미지를 클릭 이미지로 변경합니다
		if (!routMap.mapInfo[mapId].selectedMarker
				|| routMap.mapInfo[mapId].selectedMarker !== marker) {
			
			// 클릭된 마커 객체가 null이 아니면
			// 클릭된 마커의 이미지를 기본 이미지로 변경하고
			!!routMap.mapInfo[mapId].selectedMarker
					&& routMap.mapInfo[mapId].selectedMarker
							.setImage(routMap.mapInfo[mapId].selectedMarker.normalImage);
	
			// 현재 클릭된 마커의 이미지는 클릭 이미지로 변경합니다
			marker.setImage(markerSelImage);
		}
		
		
		//routMap.mapInfo[mapId].markers[routMap.mapInfo[mapId].selectedIndex].setImage(routMap.mapInfo[mapId].selectedMarker.normalImage);
		
		// 클릭된 마커를 현재 클릭된 마커 객체로 설정합니다
		routMap.mapInfo[mapId].selectedMarker = marker;

		//routMap.focusNode(mapId, grid,idx);
		
		marker.setZIndex(3);
		routMap.mapInfo[mapId].isMove = false;
		/*routMap.mapInfo[mapId].selectedIndex = idx;*/
		grid.setFocusedCell(data.realIndex,"NODE_ID");
	});
	
	kakao.maps.event.addListener(marker, 'dragstart', function() {
		overlay.setMap(null);
		//infoWindow.close();
		routMap.mapInfo[mapId].dragging = true;
	});
	
	kakao.maps.event.addListener(marker, 'dragend', function() {
		
	});
	
	if((data.NODE_TYPE != routMap.NODE_TYPE.CROSS)&&(data.NODE_TYPE != routMap.NODE_TYPE.BUSSTOP)||isOverLayHidden){
		overlay.setMap(null);
		kakao.maps.event.addListener(marker, 'mouseover', routMap.makeOverListener(routMap.mapInfo[mapId].map, marker, overlay));
		kakao.maps.event.addListener(marker, 'mouseout', routMap.makeOutListener(routMap.mapInfo[mapId],marker,overlay,markerImage));
	}
	
	marker.setMap(routMap.mapInfo[mapId].map); //Marker가 표시될 Map 설정.
	routMap.mapInfo[mapId].markers.push(marker);
}


/**
 * 사운드 노드마커 추가

 * @param mapId : 대상 map Id
 * @param data : 그리드의 data
 * @param grid : 대상 그리드
 * @param idx : 마커의 index
 * @param focusIdx : 포커스 index
 * @param idx : 마커의 index
 */
routMap.addSoundMarkerInter = function(mapId, data, grid, idx, focusIdx) {
	// 마커 이미지의 이미지 크기 입니다
	var imageSize = new kakao.maps.Size(19, 28); 
	var markerImage = null;
	var markerOverImage = null;
	var markerSelImage = null;
	
	var zIndex= -1;
	if(data.NODE_TYPE == routMap.NODE_TYPE.CROSS){
		zIndex = 1;
		imageSize = new kakao.maps.Size(19, 19);
		markerImage = new kakao.maps.MarkerImage("/cm/images/tmap/cross.png", imageSize);
		markerSelImage = new kakao.maps.MarkerImage("/cm/images/tmap/cross_selected.png", imageSize);
	}
	else if(data.NODE_TYPE == routMap.NODE_TYPE.BUSSTOP) {
		zIndex = 2;
		imageSize = new kakao.maps.Size(25, 24);
		markerImage = new kakao.maps.MarkerImage("/cm/images/tmap/busstop.png", imageSize);
		markerSelImage = new kakao.maps.MarkerImage("/cm/images/tmap/busstop_selected.png", imageSize);
	}
	else if(data.NODE_TYPE == routMap.NODE_TYPE.VERTEX){
		imageSize = new kakao.maps.Size(12, 12); 
		markerImage = new kakao.maps.MarkerImage("/cm/images/tmap/vertex.png", imageSize);
		markerSelImage = new kakao.maps.MarkerImage("/cm/images/tmap/vertex_selected.png", imageSize);
	}
	else if(data.NODE_TYPE == routMap.NODE_TYPE.SIGNAL){
		imageSize = new kakao.maps.Size(12, 12); 
		markerImage = new kakao.maps.MarkerImage("/cm/images/tmap/vertex.png", imageSize);
		markerSelImage = new kakao.maps.MarkerImage("/cm/images/tmap/vertex_selected.png", imageSize);
	}	
	else if(data.NODE_TYPE == routMap.NODE_TYPE.SOUND){
		markerImage = new kakao.maps.MarkerImage("/cm/images/tmap/voice_node.png", imageSize);
		markerSelImage = new kakao.maps.MarkerImage("/cm/images/tmap/voice_node_selected.png", imageSize);
	}
	else if(data.NODE_TYPE == routMap.NODE_TYPE.NORMAL){
		imageSize = new kakao.maps.Size(12, 12); 
		markerImage = new kakao.maps.MarkerImage("/cm/images/tmap/vertex.png", imageSize);
		markerSelImage = new kakao.maps.MarkerImage("/cm/images/tmap/vertex_selected.png", imageSize);
	}
	else {
		markerImage = new kakao.maps.MarkerImage("/cm/images/tmap/road_trans.png", imageSize);
		markerSelImage = new kakao.maps.MarkerImage("/cm/images/tmap/road_selected.png", imageSize);
	}
	
	var marker = null;
	if(idx==focusIdx) {
		zIndex = 3;
		// 마커 이미지를 생성합니다    
		marker = new kakao.maps.Marker({
			position : new kakao.maps.LatLng(data.GPS_Y, data.GPS_X), // Marker의 중심좌표 // 설정.
			//title : data.label, // Marker의 라벨.
			image : markerSelImage,
			draggable : data.draggable,
			zIndex: zIndex
		});
		routMap.mapInfo[mapId].selectedMarker = marker;
	}
	else {
		// 마커 이미지를 생성합니다    
		marker = new kakao.maps.Marker({
			position : new kakao.maps.LatLng(data.GPS_Y, data.GPS_X), // Marker의 중심좌표 // 설정.
			//title : data.label, // Marker의 라벨.
			image : markerImage,
			draggable : data.draggable,
			zIndex: zIndex
		});
	}

	marker.normalImage = markerImage;


	var infoWindow = null;
		
	var overlay = null;
	if(data.NODE_TYPE != routMap.NODE_TYPE.BUSSTOP&&data.NODE_TYPE != routMap.NODE_TYPE.CROSS&&data.NODE_TYPE != routMap.NODE_TYPE.SOUND){
		var msg = "<div class = 'customoverlay'>"
					+ "<span class = 'map_title' style=''>" + data.NODE_NM + "</span>" 
					+ "</div>";	
					//+  "<span class = '' style='font-size: 12px; margin-left:2px; margin-bottom:2px; display:block;'>"+ data.GPS_Y + "," + data.GPS_X +"</span>"
		
		//infoWindow = new kakao.maps.InfoWindow({
		//	content: msg, //Popup 표시될 text
		//	zIndex : 4
		//});
		overlay = new kakao.maps.CustomOverlay({
			content: msg,
			position: marker.getPosition(),
			zIndex : 4
		});
		
	}
	else {
		var msg = "";
		if(data.NODE_TYPE == routMap.NODE_TYPE.SOUND){
			 msg = "<div class = 'customoverlay sound'>";
		}
		else if(data.NODE_TYPE == routMap.NODE_TYPE.BUSSTOP){
			 msg = "<div class = 'customoverlay busstop'>";
		}
		else if(data.NODE_TYPE == routMap.NODE_TYPE.CROSS){
			msg = "<div class = 'customoverlay cross'>";
		}
		if(data.draggable){
			
			msg += "<span class = 'map_title' style=''>" + data.NODE_NM
				+ "&nbsp;<a href='javascript:void(0);' onclick='routMap.nodeChange(\"" + mapId+"\","+ idx + ");' style='color:blue' target='_self'>변경</a> </span>"
				+ "</div>";	
				//+ "<span class = '' style='font-size: 12px; margin-left:2px; margin-bottom:2px; display:block;'>"+ data.GPS_Y + "," + data.GPS_X +"</span>"
		}
		else {
			msg += "<span class = 'map_title' style=''>" + data.NODE_NM + "</span>"
				+ "</div>";
				//+ "<span class = '' style='font-size: 12px; margin-left:2px; margin-bottom:2px; display:block;'>"+ data.GPS_Y + "," + data.GPS_X +"</span>"
		 }
		
		/*infoWindow = new kakao.maps.InfoWindow({
		content: msg, //Popup 표시될 text
		zIndex : zIndex
		});
		
		infoWindow.open(routMap.mapInfo[mapId].map, marker);*/
		
		overlay = new kakao.maps.CustomOverlay({
			content: msg,
			map: routMap.mapInfo[mapId].map,
			position: marker.getPosition(),
			zIndex : zIndex
		});
	}
	//routMap.mapInfo[mapId].infoWindow.setMap(routMap.mapInfo[mapId].map); 
	routMap.mapInfo[mapId].overlay = overlay;
	routMap.mapInfo[mapId].overArr.push(routMap.mapInfo[mapId].overlay);
	
	if(data.draggable){
		kakao.maps.event.addListener(marker, 'dblclick', function(mapId,idx) {
			
			routMap.nodeChange(mapId,idx);
		});
	}
	
	// 마커에 click 이벤트를 등록합니다
	kakao.maps.event.addListener(marker, 'click', function() {
		//infoWindow.close();

		if(routMap.mapInfo[mapId].dragging){
			data.click({
				marker: marker,
				nodeId: data.NODE_ID,
				index: data.index
			});
			//routMap.focusNode(mapId, grid,idx);
			
			routMap.mapInfo[mapId].isMove = false;
			/*routMap.mapInfo[mapId].oldSelectedIndex = routMap.mapInfo[mapId].selectedIndex;
			routMap.mapInfo[mapId].selectedIndex = idx;*/
			grid.setFocusedCell(data.realIndex,"NODE_ID");
			return;
		}
		// 클릭된 마커가 없고, click 마커가 클릭된 마커가 아니면
		// 마커의 이미지를 클릭 이미지로 변경합니다
		if (!routMap.mapInfo[mapId].selectedMarker
				|| routMap.mapInfo[mapId].selectedMarker !== marker) {
			
			// 클릭된 마커 객체가 null이 아니면
			// 클릭된 마커의 이미지를 기본 이미지로 변경하고
			!!routMap.mapInfo[mapId].selectedMarker
					&& routMap.mapInfo[mapId].selectedMarker
							.setImage(routMap.mapInfo[mapId].selectedMarker.normalImage);
	
			// 현재 클릭된 마커의 이미지는 클릭 이미지로 변경합니다
			marker.setImage(markerSelImage);
		}
		
		
		//routMap.mapInfo[mapId].markers[routMap.mapInfo[mapId].selectedIndex].setImage(routMap.mapInfo[mapId].selectedMarker.normalImage);
		
		// 클릭된 마커를 현재 클릭된 마커 객체로 설정합니다
		routMap.mapInfo[mapId].selectedMarker = marker;

		marker.setZIndex(3);
		//routMap.focusNode(mapId, grid,idx);
		
		routMap.mapInfo[mapId].isMove = false;
		/*routMap.mapInfo[mapId].selectedIndex = idx;*/
		grid.setFocusedCell(data.realIndex,"NODE_ID");
	});
	
	if(data.NODE_TYPE == routMap.NODE_TYPE.SOUND){
		kakao.maps.event.addListener(marker, 'dragstart', function() {
			overlay.setMap(null);
			//infoWindow.close();
			routMap.mapInfo[mapId].dragging = true;
		});
		
		kakao.maps.event.addListener(marker, 'dragend', function() {
			
		});
	}
	if((data.NODE_TYPE != routMap.NODE_TYPE.CROSS)&&(data.NODE_TYPE != routMap.NODE_TYPE.BUSSTOP)&&(data.NODE_TYPE != routMap.NODE_TYPE.SOUND)){
		kakao.maps.event.addListener(marker, 'mouseover', routMap.makeOverListener(routMap.mapInfo[mapId].map, marker, overlay));
		kakao.maps.event.addListener(marker, 'mouseout', routMap.makeOutListener(routMap.mapInfo[mapId],marker,overlay,markerImage));
	}
	
	marker.setMap(routMap.mapInfo[mapId].map); //Marker가 표시될 Map 설정.
	routMap.mapInfo[mapId].markers.push(marker);
}

/**
 * 마커 마우스 over 시 호출되는 콜백 함수 : overlay을 출력함

 * @param mapId : 대상 map 
 * @param marker : 대상 마커
 * @param overlay : 마커위에 출력할 overlay
 * @param markerSelImage : 선택된 마커 이미지
 */
routMap.makeOverListener = function(map, marker, overlay, markerSelImage) {
	return function() {
		if(markerSelImage) {
			if(marker.getImage() != markerSelImage){
				overlay.setMap(map);
			}
		}
		else {
			overlay.setMap(map);
		}
		//infoWindow.open(map, marker);
	};
}

/**
 * 마커 마우스 out 시 호출되는 콜백 함수 : overlay을 삭제함

 * @param mapId : 대상 map 
 * @param marker : 대상 마커
 * @param overlay : 삭제할 overlay
 * @param markerImage : 기본 마커 이미지
 * @param markerSelImage : 선택된 마커 이미지
 */
routMap.makeOutListener = function(mapInfo,marker,overlay,markerImage,markerSelImage) {
	return function() {
		if(markerSelImage) {
			if(marker.getImage() != markerSelImage){
				overlay.setMap(null);
			}
		}
		else{
			overlay.setMap(null);
		}
		//infoWindow.close();
        // 클릭된 마커가 없고, mouseout된 마커가 클릭된 마커가 아니면
        // 마커의 이미지를 기본 이미지로 변경합니다
        /*if (!mapInfo.selectedMarker || mapInfo.selectedMarker !== marker) {
            marker.setImage(markerImage);
        }*/
	};
}

/**
 * 사용 안함 통통튀는 마커 생성

 * @param mapId : 대상 map id
 * @param lat : 위도
 * @param lng : 경도
 * @param id : 마커 타이틀
 */
routMap.addMarkerAni = function(mapId, lat, lng, id) {
	var aniType = kakao.maps.MarkerOptions.ANIMATE_BOUNCE;
	var coordIdx = 0;

	//routMap.removeMarkers(); // 지도에 새로 등록하기 위해 모든 마커를 지우는 함수입니다.
      
	
	var func = function() {
		//Marker 객체 생성.
		var marker = new kakao.maps.Marker({
			position: new kakao.maps.LatLng(lat, lng), //Marker의 중심좌표 설정.
			draggable: routMap.mapInfo[mapId].draggable, //Marker의 드래그 가능 여부.
			animation: aniType, //Marker 애니메이션.
			animationLength: 500, //애니메이션 길이.
			//label: "정류소", //Marker의 라벨.
			title: id //Marker 타이틀.
		});
		marker.setMap(routMap.mapInfo[mapId].map); //Marker가 표시될 Map 설정.
		routMap.mapInfo[mapId].markers.push(marker);

		
	}
	// 일정 시간 간격으로 마커를 생성하는 함수를 실행합니다
	setTimeout(func, 300);
}

/**
 * 버스마커 표시

 * @param mapId : 대상 map id
 * @param data : 그리드의 data
 * @param idx : 마커의 index
 * @param focusIdx : 포커스 index
 * @param busGrid : 버스 그리드
 */
routMap.showBusMarker = function(mapId, data, idx, focusIdx, busGrid) {
	// 마커 이미지의 이미지 크기 입니다
	var imageSize = new kakao.maps.Size(35, 35); 
	var markerImage = null;
	var markerOverImage = null;
	var markerSelImage = null;
	
	var zIndex= 5;
	if(data.VHC_KIND == "VHK01"){
		markerImage = new kakao.maps.MarkerImage("/cm/images/tmap/bus_red.png", imageSize);
		markerSelImage = new kakao.maps.MarkerImage("/cm/images/tmap/bus_red_selected.png", imageSize);
	}

	else {
		markerImage = new kakao.maps.MarkerImage("/cm/images/tmap/bus_red.png", imageSize);
		markerSelImage = new kakao.maps.MarkerImage("/cm/images/tmap/bus_red_selected.png", imageSize);
	}
	
	var marker = null;
	if(idx==focusIdx) {
		zIndex = 6;
		// 마커 이미지를 생성합니다    
		marker = new kakao.maps.Marker({
			position : new kakao.maps.LatLng(data.GPS_Y, data.GPS_X), // Marker의 중심좌표 // 설정.
			//title : data.label, // Marker의 라벨.
			image : markerSelImage,
			draggable : data.draggable,
			zIndex: zIndex
		});
		routMap.mapInfo[mapId].selectedBusMarker = marker;
	}
	else {
		// 마커 이미지를 생성합니다    
		marker = new kakao.maps.Marker({
			position : new kakao.maps.LatLng(data.GPS_Y, data.GPS_X), // Marker의 중심좌표 // 설정.
			//title : data.label, // Marker의 라벨.
			image : markerImage,
			draggable : data.draggable,
			zIndex: zIndex
		});
	}

	marker.normalImage = markerImage;
	
	var overlayName = null;
	var overlay = null;
	
	if (typeof data.VHC_NO != "undefined") {
		overlayName = data.VHC_NO
	} else if (typeof data.RPC_VHC_NO != "undefined") {
		overlayName = data.RPC_VHC_NO		
	}
	
	
	var msg = "<div class = 'busoverlay'>"
			+ "<span class = 'map_title' style=''>" + overlayName + "</span>"
			+ "</div>";
	
	if(typeof data.BUS_STS != "undefined" && data.BUS_STS == "BS002") {
		msg = "<div class = 'busoverlay conderror'>"
			+ "<span class = 'map_title' style=''>" + overlayName + "</span>"
			+ "</div>";			
	}
	
	
	overlay = new kakao.maps.CustomOverlay({
		content: msg,
		position: marker.getPosition(),
		zIndex : zIndex
	});
	//routMap.mapInfo[mapId].infoWindow.setMap(routMap.mapInfo[mapId].map); 
	routMap.mapInfo[mapId].busOverlay = overlay;

	
	if(idx<routMap.mapInfo[mapId].busOverArr.length){
		routMap.mapInfo[mapId].busOverArr[idx] = routMap.mapInfo[mapId].busOverlay;
	}
	else{
		routMap.mapInfo[mapId].busOverArr.push(routMap.mapInfo[mapId].busOverlay);
	}
	if(idx == focusIdx) {
		routMap.mapInfo[mapId].busOverArr[focusIdx].setMap(routMap.mapInfo[mapId].map);
	}
	
	if(idx!=focusIdx) {
		kakao.maps.event.addListener(marker, 'mouseover', routMap.makeOverListener(routMap.mapInfo[mapId].map, marker, overlay));
		kakao.maps.event.addListener(marker, 'mouseout', routMap.makeOutListener(routMap.mapInfo[mapId],marker,overlay,markerImage));
	}
	
	marker.setMap(routMap.mapInfo[mapId].map); //Marker가 표시될 Map 설정.
	if(idx<routMap.mapInfo[mapId].busMarkers.length){
		routMap.mapInfo[mapId].busMarkers[idx] = marker;
	}
	else{
		routMap.mapInfo[mapId].busMarkers.push(marker);
	}
	
	
	// 마커에 click 이벤트를 등록합니다
	// 여기부터 클릭 이벤트 수정해야함
	kakao.maps.event.addListener(marker, 'click', function() {
		
		// 클릭된 마커가 없고, click 마커가 클릭된 마커가 아니면
		// 마커의 이미지를 클릭 이미지로 변경합니다
		if (!routMap.mapInfo[mapId].selectedMarker
				|| routMap.mapInfo[mapId].selectedMarker !== marker) {
			
			// 클릭된 마커 객체가 null이 아니면
			// 클릭된 마커의 이미지를 기본 이미지로 변경하고
			!!routMap.mapInfo[mapId].selectedMarker
					&& routMap.mapInfo[mapId].selectedMarker
							.setImage(routMap.mapInfo[mapId].selectedMarker.normalImage);
	
			// 현재 클릭된 마커의 이미지는 클릭 이미지로 변경합니다
			marker.setImage(markerSelImage);
		}
		
		// 클릭된 마커를 현재 클릭된 마커 객체로 설정합니다
		routMap.mapInfo[mapId].selectedMarker = marker;
		marker.setZIndex(3);
		//routMap.mapInfo[mapId].isMove = false;
		//routMap.mapInfo[mapId].selectedIndex = idx;
		
		if(typeof busGrid != "undefined") {
			//routMap.focusNode(mapId, busGrid,idx);
			busGrid.setFocusedCell(idx,"VHC_ID");
		}
		
		routMap.mapInfo[mapId].onMarkerClick(idx, mapId);		
	});			
	
	
}

/**
 * 버스마커 표시 : 마우스 오른쪽 클릭 처리 추가함

 * @param mapId : 대상 map id
 * @param data : 그리드의 data
 * @param idx : 마커의 index
 * @param focusIdx : 포커스 index
 * @param busGrid : 버스 그리드
 */
routMap.showBusMarkerClickOverlay = function(mapId, data, idx, focusIdx, busGrid, gridChk, socketVhcId) {
	// 마커 이미지의 이미지 크기 입니다
	var imageSize = new kakao.maps.Size(35, 35); 
	var markerImage = null;
	var markerOverImage = null;
	var markerSelImage = null;
	var zIndex= 5;
	if(data.VHC_KIND == "VHK01"){
		markerImage = new kakao.maps.MarkerImage("/cm/images/tmap/bus_red.png", imageSize);
		markerSelImage = new kakao.maps.MarkerImage("/cm/images/tmap/bus_red_selected.png", imageSize);
	}
	
	else {
		markerImage = new kakao.maps.MarkerImage("/cm/images/tmap/bus_red.png", imageSize);
		markerSelImage = new kakao.maps.MarkerImage("/cm/images/tmap/bus_red_selected.png", imageSize);
	}
	
	var marker = null;
	
	if(idx==focusIdx) {
		zIndex = 6;
		if (typeof data.VHC_ID != "undefined") {
			zIndex = 9999;	
		}
		// 마커 이미지를 생성합니다    
		marker = new kakao.maps.Marker({
			position : new kakao.maps.LatLng(data.GPS_Y, data.GPS_X), // Marker의 중심좌표 // 설정.
			//title : data.label, // Marker의 라벨.
			image : markerSelImage,
			draggable : data.draggable,
			zIndex: zIndex
		});
		routMap.mapInfo[mapId].selectedBusMarker = marker;
	}
	else {
		// 마커 이미지를 생성합니다    
		marker = new kakao.maps.Marker({
			position : new kakao.maps.LatLng(data.GPS_Y, data.GPS_X), // Marker의 중심좌표 // 설정.
			//title : data.label, // Marker의 라벨.
			image : markerImage,
			draggable : data.draggable,
			zIndex: zIndex
		});
	}
		
	marker.normalImage = markerImage;
	
	//오버레이 관련
	//디스패치 오버레이가 아니면

	routMap.showBubbleOverlay(mapId, data, marker, idx, focusIdx);
		//routMap.showClickBusOverlay(mapId, data, idx, focusIdx, marker);
	
	if(routMap.mapInfo[mapId].isDsptch == "on") {
		
		//그리드 체크 기능을 통한 디스패치 표시
		if(typeof(gridChk) != "undefined" && gridChk == "Y") {
			if (typeof(data.DSPTCH_YN) != "undefined") {
				if (data.DSPTCH_YN = "1" && socketVhcId == data.VHC_ID)
					routMap.showDsptchOverlay(mapId, data, idx, focusIdx, marker);			
			}
		}
		
		//focus된 차량만 디스패치 표시
		else {
			if(idx == focusIdx) {
				routMap.showDsptchOverlay(mapId, data, idx, focusIdx, marker);
			}
		}
		
	}
	
	if(routMap.mapInfo[mapId].isEvent == "on") {
		
		if(typeof(gridChk) != "undefined" && gridChk == "Y") {
			if (typeof(data.EVT_YN) != "undefined") {
				if (data.EVT_YN = "1" && socketVhcId == data.VHC_ID)
					routMap.showEventOverlay(mapId, data, idx, focusIdx, marker);		
			}			
		}
		
		else {
			if(idx == focusIdx) {
				routMap.showEventOverlay(mapId, data, idx, focusIdx, marker);
			}
		}
		
	}
	
	marker.setMap(routMap.mapInfo[mapId].map); //Marker가 표시될 Map 설정.
	if(idx<routMap.mapInfo[mapId].busMarkers.length){
		routMap.mapInfo[mapId].busMarkers[idx] = marker;
	}
	else{
		routMap.mapInfo[mapId].busMarkers.push(marker);
	}
	
	
	// 마커에 click 이벤트를 등록합니다
	// 여기부터 클릭 이벤트 수정해야함
	kakao.maps.event.addListener(marker, 'click', function() {
		routMap.removeRightClickOverlay(mapId);
		// 클릭된 마커가 없고, click 마커가 클릭된 마커가 아니면
		// 마커의 이미지를 클릭 이미지로 변경합니다
		if (!routMap.mapInfo[mapId].selectedMarker
				|| routMap.mapInfo[mapId].selectedMarker !== marker) {
			
			// 클릭된 마커 객체가 null이 아니면
			// 클릭된 마커의 이미지를 기본 이미지로 변경하고
			!!routMap.mapInfo[mapId].selectedMarker
			&& routMap.mapInfo[mapId].selectedMarker
			.setImage(routMap.mapInfo[mapId].selectedMarker.normalImage);
			
			// 현재 클릭된 마커의 이미지는 클릭 이미지로 변경합니다
			marker.setImage(markerSelImage);
		}
		
		// 클릭된 마커를 현재 클릭된 마커 객체로 설정합니다
		routMap.mapInfo[mapId].selectedMarker = marker;
		marker.setZIndex(3);
		//routMap.mapInfo[mapId].isMove = false;
		//routMap.mapInfo[mapId].selectedIndex = idx;
		
		if(typeof busGrid != "undefined") {
			//routMap.focusNode(mapId, busGrid,idx);
			busGrid.setFocusedCell(idx,"VHC_NO");
		}
		
		routMap.mapInfo[mapId].onMarkerClick(idx, mapId);		
	});			
	
	
	kakao.maps.event.addListener(marker, 'rightclick', function() {
//		var position2 = new kakao.maps.LatLng(36.482456, 127.297272);
//		routMap.mapInfo[mapId].busOverArr[idx].setPosition(position2);
		routMap.removeRightClickOverlay(mapId);
		
		var rightClickOverlay = null;
		var rightClickMsg = "";
	
		rightClickMsg += '<div class="rightclickoverlay" style="cursor: default;">'
		rightClickMsg += '<div class="contextWrap">'
		rightClickMsg += '    <a data-id="here" href="javascript:void(0)" class="busInfo" onclick="">'
		rightClickMsg += '	            <span class="text">버스 상세정보</span>'
		rightClickMsg += '     </a>'
		rightClickMsg += '      <a data-id="newplace" href="javascript:void(0)" class="dispatchMessage">'
		rightClickMsg += '         <span class="text">메시지 전송</span>'
		rightClickMsg += '      </a>'
        rightClickMsg += '  	</div>	'
        rightClickMsg += '  </div>	'
		
		rightClickOverlay = new kakao.maps.CustomOverlay({
			content: rightClickMsg,
			position: marker.getPosition(),
			zIndex : 999
		});		
	
		routMap.mapInfo[mapId].rightClickOverlay = rightClickOverlay;
		rightClickOverlay.setMap(routMap.mapInfo[mapId].map);	
		
		if(typeof busGrid != "undefined") {
			busGrid.setFocusedCell(idx,"VHC_NO");
		}		
		
	}); //end rightclick
	
}

/**
 * 버스마커 표시 : 버스마커 이번트 처리 안되는 경우에만 사용

 * @param mapId : 대상 map id
 * @param data : 그리드의 data
 * @param idx : 마커의 index
 * @param focusIdx : 포커스 index
 * @param busGrid : 버스 그리드
 */
routMap.showBusMarkerNotEvent = function(mapId, data, idx, focusIdx, busGrid) {
	// 마커 이미지의 이미지 크기 입니다
	var imageSize = new kakao.maps.Size(35, 35); 
	var markerImage = null;
	var markerOverImage = null;
	var markerSelImage = null;
	
	var zIndex= 5;
	if(data.VHC_KIND == "VHK01"){
		markerImage = new kakao.maps.MarkerImage("/cm/images/tmap/bus_red.png", imageSize);
		markerSelImage = new kakao.maps.MarkerImage("/cm/images/tmap/bus_red_selected.png", imageSize);
	}

	else {
		markerImage = new kakao.maps.MarkerImage("/cm/images/tmap/bus_red.png", imageSize);
		markerSelImage = new kakao.maps.MarkerImage("/cm/images/tmap/bus_red_selected.png", imageSize);
	}
	
	var marker = null;
	if(idx==focusIdx) {
		zIndex = 6;
		// 마커 이미지를 생성합니다    
		marker = new kakao.maps.Marker({
			position : new kakao.maps.LatLng(data.GPS_Y, data.GPS_X), // Marker의 중심좌표 // 설정.
			//title : data.label, // Marker의 라벨.
			image : markerSelImage,
			draggable : data.draggable,
			zIndex: zIndex
		});
		routMap.mapInfo[mapId].selectedBusMarker = marker;
	}
	else {
		// 마커 이미지를 생성합니다    
		marker = new kakao.maps.Marker({
			position : new kakao.maps.LatLng(data.GPS_Y, data.GPS_X), // Marker의 중심좌표 // 설정.
			//title : data.label, // Marker의 라벨.
			image : markerImage,
			draggable : data.draggable,
			zIndex: zIndex
		});
	}

	marker.normalImage = markerImage;
	
	var overlayName = null;
	var overlay = null;
	
	if (typeof data.VHC_NO != "undefined") {
		overlayName = data.VHC_NO
	} else if (typeof data.RPC_VHC_NO != "undefined") {
		overlayName = data.RPC_VHC_NO		
	}
	
	
	var msg = "<div class = 'busoverlay'>"
			+ "<span class = 'map_title' style=''>" + overlayName + "</span>"
			+ "</div>";
	
	if(typeof data.BUS_STS != "undefined" && data.BUS_STS == "BS002") {
		msg = "<div class = 'busoverlay conderror'>"
			+ "<span class = 'map_title' style=''>" + overlayName + "</span>"
			+ "</div>";			
	}
	
	
	overlay = new kakao.maps.CustomOverlay({
		content: msg,
		position: marker.getPosition(),
		zIndex : zIndex
	});
	//routMap.mapInfo[mapId].infoWindow.setMap(routMap.mapInfo[mapId].map); 
	routMap.mapInfo[mapId].busOverlay = overlay;

	
	if(idx<routMap.mapInfo[mapId].busOverArr.length){
		routMap.mapInfo[mapId].busOverArr[idx] = routMap.mapInfo[mapId].busOverlay;
	}
	else{
		routMap.mapInfo[mapId].busOverArr.push(routMap.mapInfo[mapId].busOverlay);
	}
	if(idx == focusIdx) {
		routMap.mapInfo[mapId].busOverArr[focusIdx].setMap(routMap.mapInfo[mapId].map);
	}
	
	marker.setMap(routMap.mapInfo[mapId].map); //Marker가 표시될 Map 설정.
	if(idx<routMap.mapInfo[mapId].busMarkers.length){
		routMap.mapInfo[mapId].busMarkers[idx] = marker;
	}
	else{
		routMap.mapInfo[mapId].busMarkers.push(marker);
	}
}

/**
 * 말풍선overlay 표시

 * @param mapId : 대상 map id
 * @param data : 그리드의 data
 * @param marker : 대상마커
 * @param idx : 마커의 index
 * @param focusIdx : 포커스 index
 */
routMap.showBubbleOverlay = function(mapId, data, marker, idx, focusIdx) {
	var zIndex= 2;
	var overlayName = null;
	var overlay = null;
	var msg = "";
	var wayDiv = "";
	
	if(data.WAY_DIV == "WD001")	wayDiv = "_상";
	else if(data.WAY_DIV == "WD002") wayDiv = "_하";
	else if(data.WAY_DIV == "WD003") wayDiv = "_왕복";
	else if(data.WAY_DIV == "WD004") wayDiv = "_순환";
	
	//정류소 or 교차로
	if (typeof data.VHC_ID == "undefined") {
		if(data.NODE_TYPE == routMap.NODE_TYPE.BUSSTOP){
			 msg = "<div class = 'customoverlay busstop'>";
			 
			 //장치상태 에러일때
			 if(data.COND_ERROR == 'CE001') {
				 msg = "";
				 msg = "<div class = 'customoverlay conderror busstop'>";
			 }
		}
		
		else if(data.NODE_TYPE == routMap.NODE_TYPE.CROSS){
			msg = "<div class = 'customoverlay cross'>";
			
			 if(data.COND_ERROR == 'CE001') {
				 msg = "";
				 msg = "<div class = 'customoverlay conderror busstop'>";
			 }		
		}
		
		msg += "<span class = 'map_title' style=''>" + data.NODE_NM + wayDiv + "</span>"
			+ "</div>";
		
	}
	
	//버스
	else {
		overlayName = "번호없음";
		if (typeof data.VHC_NO != "undefined") {
			overlayName = data.VHC_NO
		} else if (typeof data.RPC_VHC_NO != "undefined") {
			overlayName = data.RPC_VHC_NO		
		}
		msg = "<div class = 'busoverlay'>"
			+ "<span class = 'map_title' style=''>" + overlayName + "</span>"
			+ "</div>";
	}
	
	
	overlay = new kakao.maps.CustomOverlay({
		content: msg,
		position: marker.getPosition(),
		zIndex : zIndex
	});
	if (typeof data.VHC_ID == "undefined" && data.NODE_TYPE =="NT002") {
		overlay.setMap(routMap.mapInfo[mapId].map);
	}
	
	else {
		if(idx==focusIdx) {
			//overlay.setZIndex(10000);
			//routMap.mapInfo[mapId].overArr[focusIdx].setMap(routMap.mapInfo[mapId].map);
			overlay.setMap(routMap.mapInfo[mapId].map);
		}
		
		if(idx!=focusIdx) {
			kakao.maps.event.addListener(marker, 'mouseover', routMap.makeOverListener(routMap.mapInfo[mapId].map, null, overlay));
			kakao.maps.event.addListener(marker, 'mouseout', routMap.makeOutListener(routMap.mapInfo[mapId],null,overlay,null));
		}		
	}

	
	if (typeof data.VHC_ID == "undefined") {
		routMap.mapInfo[mapId].overlay = overlay;
		routMap.mapInfo[mapId].overArr.push(routMap.mapInfo[mapId].overlay);		
	}
	
	else {
		
		routMap.mapInfo[mapId].busOverlay = overlay;
		
		if(idx<routMap.mapInfo[mapId].busOverArr.length){
			routMap.mapInfo[mapId].busOverArr[idx] = routMap.mapInfo[mapId].busOverlay;
		}
		else{
			routMap.mapInfo[mapId].busOverArr.push(routMap.mapInfo[mapId].busOverlay);
			
		}
	}
	
}

/**
 * 사용안함 클릭overlay 표시

 * @param mapId : 대상 map id
 * @param data : 그리드의 data
 * @param idx : 마커의 index
 * @param focusIdx : 포커스 index
 * @param marker : 대상마커
 * @param markerImage : 마커이미지
 */
routMap.showClickOverlay = function(mapId, data, idx, focusIdx, marker, markerImage) {
	var infoWindow = null;

	var overlay = null;
	
	var clickMsg = "";
	
	clickMsg += '<div class="sttnInfopopup clickoverlay" id="sttnInfoPopup" style="position: absolute;"><div class="map_layer bustraffic" style="left: 0px;top: 10px;z-index:10000000;">'
	clickMsg += '<a href="javascript:void(0)" id="popup-closer" class="close"><span class="blind">닫기</span></a>'
	clickMsg += '<div id="popup-content">'
	clickMsg += '<div class="tit"><span style="margin-right: 40px; word-wrap:break-word; white-space: normal;"><strong>'+data.NODE_NM+'</strong></span></div>' 
	clickMsg += '<div class="content">' 
	clickMsg += '<div class="trafficInfor">' 
	clickMsg += '<table class="tby03">' 
	clickMsg += '<caption>버스운행정보</caption>' 
	clickMsg += '<colgroup>' 
	clickMsg += '<col style="width:*">' 
	clickMsg += '<col style="width:19%">' 
	clickMsg += '<col style="width:13%">' 
	clickMsg += '<col style="width:19%">' 
	clickMsg += '<col style="width:13%">' 
	clickMsg += '</colgroup>' 
	clickMsg += '<tbody id="overlay_tbody">'
		
	clickMsg += '<tr>' 
	clickMsg += '<th style="font-size: 12px; text-align: center; padding: 5px; width: 100%">' 
	clickMsg += '<a href="javascript:void(0)" onclick="fn_showBusLine(&quot;118900006&quot;);" style="color: black">'
	clickMsg += '<strong> 데이터 없음'
	clickMsg += '</strong>' 
	clickMsg += '<br>' 
	clickMsg += '</a>' 
	clickMsg += '</th>' 
	clickMsg += '</tr>'			

	clickMsg += '</tbody>' 
	clickMsg += '</table>' 
	clickMsg += '</div>' 
	clickMsg += '</div> </div>'
	clickMsg += '</div></div>'	
	
	if(idx==focusIdx) {
		
		//정류소
		if(data.NODE_TYPE == 'NT002') {
			clickOverlay = new kakao.maps.CustomOverlay({
				content: clickMsg,
				position: marker.getPosition(),
				zIndex : 2
			});
		}
			
		
		//교차로
		else if(data.NODE_TYPE == 'NT001') {
			clickOverlay = new kakao.maps.CustomOverlay({
				content: clickMsg,
				position: marker.getPosition(),
				zIndex : 2
			});
		}
		
		
	}
	else {
		
		if(data.NODE_TYPE == 'NT002') {
			clickOverlay = new kakao.maps.CustomOverlay({
				content: clickMsg,
				position: marker.getPosition(),
				zIndex : 2
			});
		}
		
		
		else if(data.NODE_TYPE == 'NT001') {
			clickOverlay = new kakao.maps.CustomOverlay({
				content: clickMsg,
				position: marker.getPosition(),
				zIndex : 2
			});			
		}
		
		
	}
	
	routMap.mapInfo[mapId].clickOverlay = clickOverlay;
	routMap.mapInfo[mapId].clickOverArr.push(routMap.mapInfo[mapId].clickOverlay);
	
	if(idx==focusIdx) {
		if(markerImage != null){
			clickOverlay.setMap(routMap.mapInfo[mapId].map);
		}
	}
	
	if(markerImage != null)
		marker.setMap(routMap.mapInfo[mapId].map); //Marker가 표시될 Map 설정.
	
	routMap.mapInfo[mapId].markers.push(marker);
}

routMap.showClickBusOverlay = function(mapId, data, idx, focusIdx, marker) {
	var zIndex = 5;
	
	var clickMsg = "";
	var oper_sts = "";
	var node_type = "";
	
	if (typeof data.OPER_STS != "undefined") {
		if (data.OPER_STS == "OS001") {
			oper_sts = "정상운행";
		}
		else if (data.OPER_STS == "OS002") {
			oper_sts = "운행종료";
		}
		else if (data.OPER_STS == "OS003") {
			oper_sts = "운행취소";
		}
	}
	
	if (typeof data.NODE_TYPE != "undefined") {
		if (data.NODE_TYPE == "NT001") {
			node_type = "교차로";
		}
		else if (data.NODE_TYPE == "NT002") {
			node_type = "정류소";
		}
		else if (data.NODE_TYPE == "NT003") {
			node_type = "일반노드";
		}
	}	
	
	clickMsg += '<div class="busInfoPopup clickoverlay" id="busInfoPopup" style="position: absolute;"><div class="map_layer bustraffic" style="left: 0px;top: 10px;z-index:10000000;">'
	clickMsg += '<a href="javascript:void(0)" id="busInfo-closer" class="close"><span class="blind">닫기</span></a>'
	clickMsg += '<div id="popup-content">'
	clickMsg += '<div class="tit"><span style="margin-right: 40px; word-wrap:break-word; white-space: normal;"><strong>'+data.VHC_NO+'</strong></span></div>' 
	clickMsg += '<div class="content">' 
	clickMsg += '<div class="trafficInfor">' 
	clickMsg += '<table class="tby03">' 
	clickMsg += '<caption>버스정보</caption>' 
	clickMsg += '<colgroup>' 
	clickMsg += '<col style="width:*">' 
	clickMsg += '<col style="width:19%">' 
	clickMsg += '<col style="width:13%">' 
	clickMsg += '<col style="width:19%">' 
	clickMsg += '<col style="width:13%">' 
	clickMsg += '<col style="width:19%">' 
	clickMsg += '<col style="width:13%">' 
	clickMsg += '<col style="width:19%">' 
	clickMsg += '<col style="width:13%">' 
	clickMsg += '</colgroup>' 
	clickMsg += '<tbody id="overlay_tbody">' 
	clickMsg += '<tr> '
	clickMsg += '<th style="font-size: 10px;">운행상태</th>' 
	clickMsg += '<td style="font-size: 10px; padding: 5px;" colspan="4" id="">'
	clickMsg +=  '<div style="width: 80px">'+oper_sts+'</div></td>' 
	clickMsg += '<th style="font-size: 10px;">다음노드유형</th>' 
	clickMsg += '<td style="font-size: 10px; padding: 5px;" colspan="4" id="">'
	clickMsg +=  '<div style="width: 80px">'+node_type+'</div></td>' 
	clickMsg += '</tr>'
	clickMsg += '<tr>'
	clickMsg += '<th style="font-size: 10px;">현재속도</th>' 
	clickMsg += '<td style="font-size: 10px; padding: 5px;" colspan="4" id="">'
	clickMsg +=  '<div style="width: 80px">'+data.CUR_SPD+'km/h</div></td>' 
	clickMsg += '<th style="font-size: 10px;">다음노드명</th>' 
	clickMsg += '<td style="font-size: 10px; padding: 5px;" colspan="4" id="">'
	clickMsg +=  '<div style="width: 80px">'+data.NEXT_NODE_NM+'</div></td>' 
	clickMsg += '</tr>'
	clickMsg += '<tr>'
	clickMsg += '<th style="font-size: 10px;">현재정차시간</th>' 
	clickMsg += '<td style="font-size: 10px; padding: 5px;" colspan="4" id="">'
	clickMsg +=  '<div style="width: 80px">'+data.CUR_STOP_TM+'초</div></td>' 
	clickMsg += '<th style="font-size: 10px;">이전장소</th>' 
	clickMsg += '<td style="font-size: 10px; padding: 5px;" colspan="4" id="">'
	clickMsg +=  '<div style="width: 80px">'+data.PRV_PLCE_NM+'</div></td>' 	
	clickMsg += '</tr>'
	clickMsg += '</tbody>' 
	clickMsg += '</table>' 
	clickMsg += '</div> </div>'
	clickMsg += '</div></div>'	
	
	clickOverlay = new kakao.maps.CustomOverlay({
		content: clickMsg,
		position: marker.getPosition(),
		zIndex : zIndex
	});
	
	routMap.mapInfo[mapId].busClickOverlay = clickOverlay;
	
	if(idx<routMap.mapInfo[mapId].busOverArr.length){
		routMap.mapInfo[mapId].busClickOverArr[idx] = routMap.mapInfo[mapId].busClickOverlay;
	}
	else{
		routMap.mapInfo[mapId].busClickOverArr.push(routMap.mapInfo[mapId].busClickOverlay);
	}
	
	if(idx == focusIdx) {
		clickOverlay.setMap(routMap.mapInfo[mapId].map);
	}
	
}

/**
 * 디스패치overlay 표시

 * @param mapId : 대상 map id
 * @param data : 그리드의 data
 * @param idx : 마커의 index
 * @param focusIdx : 포커스 index
 * @param marker : 대상마커
 */
routMap.showDsptchOverlay = function(mapId, data, idx, focusIdx, marker) {
	var zIndex = 100000;
	var showMessage = "";
	var min = "";
	var sec = "";
	if(Math.abs(parseInt(data.MESSAGE) >= 60)) {
		min = Math.abs(parseInt(data.MESSAGE/60)) + "분 ";
	}
		sec = Math.abs(parseInt(data.MESSAGE%60)) + "초 ";

	if (routMap.mapInfo[mapId].divDsptch == "DP001" || routMap.mapInfo[mapId].divDsptch == "DP002") {
		if (routMap.mapInfo[mapId].divDsptch == "DP001") {
			showMessage = data.MESSAGE;
		}	
		else if (routMap.mapInfo[mapId].divDsptch == "DP002") {
/*			if(parseInt(data.MESSAGE) > 0) {
				showMessage = min+ sec+ "느립니다.";
			} else if(parseInt(data.MESSAGE) < 0){
				showMessage = min+ sec +"빠릅니다.";
			} else if(parseInt(data.MESSAGE) == 0) {
				showMessage = "정상 운행중입니다.";
			}*/
			var dsptchKind = data.DSPTCH_KIND;
			if(dsptchKind == "DK001") {
				showMessage = "정상 운행중입니다.";
			}
			else if(dsptchKind == "DK002") {
				showMessage = min + sec + "느립니다."; 
			}
			else if(dsptchKind == "DK003") {
				showMessage = min + sec + "빠릅니다."; 
			}			
			
		}	
		var dsptchMsg = "";
		
		dsptchMsg += '<div class="dsptchMessagePopup clickoverlay" id="busInfoPopup" style="position: absolute;"><div class="map_layer bustraffic" style="left: 0px;top: 10px;z-index:10000000;">'
		dsptchMsg += '<a href="javascript:void(0)" id="busInfo-closer" class="close"><span class="blind">닫기</span></a>'
		dsptchMsg += '<div id="popup-content">'
		dsptchMsg += '<div class="tit"><span style="margin-right: 40px; word-wrap:break-word; white-space: normal;"><strong>'+data.VHC_NO+'</strong></span></div>' 
		dsptchMsg += '<div class="content">' 
		dsptchMsg += '<div class="trafficInfor">' 
		dsptchMsg += '<table class="tby03">' 
		dsptchMsg += '<caption>디스패치메시지</caption>' 
		dsptchMsg += '<colgroup>' 
		dsptchMsg += '<col style="width:*">' 
		dsptchMsg += '<col style="width:19%">' 
		dsptchMsg += '<col style="width:13%">' 
		dsptchMsg += '<col style="width:19%">' 
		dsptchMsg += '<col style="width:13%">' 
		dsptchMsg += '</colgroup>' 
		dsptchMsg += '<tbody id="overlay_tbody">' 
		dsptchMsg += '<tr> '
		dsptchMsg += '<th style="font-size: 10px;">메시지내용</th>' 
		dsptchMsg += '<td style="font-size: 10px; padding: 5px;" colspan="4" id="">'
		dsptchMsg +=  '<div style="white-space: normal;">'+showMessage+'</div></td>' 
		dsptchMsg += '</tr>'
		dsptchMsg += '</tbody>' 
		dsptchMsg += '</table>' 
		dsptchMsg += '</div> </div>'
		dsptchMsg += '</div></div>'	
		
		dsptchOverlay = new kakao.maps.CustomOverlay({
			content: dsptchMsg,
			position: marker.getPosition(),
			zIndex : zIndex
		});
		
		routMap.mapInfo[mapId].dsptchOverlay = dsptchOverlay;
		/*if(idx<routMap.mapInfo[mapId].busOverArr.length){
			routMap.mapInfo[mapId].dsptchOverArr[idx] = routMap.mapInfo[mapId].dsptchOverlay;
		}
		else{
			routMap.mapInfo[mapId].dsptchOverArr.push(routMap.mapInfo[mapId].dsptchOverlay);
		}*/
		//routMap.mapInfo[mapId].dsptchOverArr.push(routMap.mapInfo[mapId].dsptchOverlay);
		routMap.mapInfo[mapId].dsptchOverArr[0] = routMap.mapInfo[mapId].dsptchOverlay;
		dsptchOverlay.setMap(routMap.mapInfo[mapId].map);	
	}		
		
	else if (routMap.mapInfo[mapId].divDsptch == "DP003") {
		showMessage = min+ sec +"정차하세요.";
		$("#stopMessage").text(showMessage);
	}
	
	routMap.mapInfo[mapId].isDsptch = "off";
	
	if(routMap.mapInfo[mapId].dsptchOverArr.length != 0 && routMap.mapInfo[mapId].eventOverArr.length == 0) {
		setTimeout(function() {
			if(routMap.mapInfo[mapId].dsptchOverArr.length != 0) {
				routMap.mapInfo[mapId].dsptchOverArr[0].setMap(null);
				routMap.mapInfo[mapId].dsptchOverArr[0] = null;
			}
		},5000);
	} 
	else if(routMap.mapInfo[mapId].dsptchOverArr.length != 0 && routMap.mapInfo[mapId].eventOverArr.length != 0) {
		$(".busInfoPopup").hide();
		setTimeout(function() {
			$(".busInfoPopup").show();
			if(routMap.mapInfo[mapId].dsptchOverArr.length != 0) {
				routMap.mapInfo[mapId].dsptchOverArr[0].setMap(null);
				routMap.mapInfo[mapId].dsptchOverArr[0] = null;
			}			
		},2000);		
	}
	
}

/**
 * 이벤트overlay 표시

 * @param mapId : 대상 map id
 * @param data : 그리드의 data
 * @param idx : 마커의 index
 * @param focusIdx : 포커스 index
 * @param marker : 대상마커
 */
routMap.showEventOverlay = function(mapId, data, idx, focusIdx, marker) {
	var zIndex = 100000;
	var eventMsg = "";
	var stopTime = 0;
	var nodeType = "";
	var nextNodeType = "";
	var min = "";
	var sec = "";
	
	if(data.NODE_TYPE == "NT001") {
		nodeType = "교차로";
	} else if(data.NODE_TYPE == "NT002"){
		nodeType = "정류소";
	}
	
	if(data.NEXT_NODE_TYPE == "NT001") {
		nextNodeType = "교차로"; 
	} else if(data.NEXT_NODE_TYPE == "NT002") {
		nextNodeType = "정류소";
	}
	
	if (routMap.mapInfo[mapId].divEvent == "ET001") {
		eventMsg += '<div class="busInfoPopup clickoverlay" id="busInfoPopup" style="position: absolute;"><div class="map_layer bustraffic" style="left: 0px;top: 10px;z-index:10000000;">'
		eventMsg += '<a href="javascript:void(0)" id="busInfo-closer" class="close"><span class="blind">닫기</span></a>'
		eventMsg += '<div id="popup-content">'
		eventMsg += '<div class="tit"><span id="event_type" style="margin-right: 40px; word-wrap:break-word; white-space: normal;"><strong>'+data.EVT_TYPE+'</strong></span></div>' 
		eventMsg += '<div class="content">' 
		eventMsg += '<div class="trafficInfor">' 
		eventMsg += '<table class="tby03">' 
		eventMsg += '<caption>이벤트정보</caption>' 
		eventMsg += '<colgroup>' 
		eventMsg += '<col style="width:*">' 
		eventMsg += '<col style="width:19%">' 
		eventMsg += '<col style="width:13%">' 
		eventMsg += '<col style="width:19%">' 
		eventMsg += '<col style="width:13%">' 
		eventMsg += '<col style="width:19%">' 
		eventMsg += '<col style="width:13%">' 
		eventMsg += '<col style="width:19%">' 
		eventMsg += '<col style="width:13%">' 
		eventMsg += '</colgroup>' 
		eventMsg += '<tbody id="overlay_tbody">' 
		eventMsg += '<tr>'
		eventMsg += '<th style="font-size: 10px;">현재 '+nodeType+'</th>' 
		eventMsg += '<td style="font-size: 10px; padding: 5px; white-space: break-spaces;" colspan="4" id="">'
		eventMsg +=  '<div style="width: 80px">'+data.NODE_NM+'</div></td>' 
		eventMsg += '<th style="font-size: 10px;">다음 '+nextNodeType+'</th>' 
		eventMsg += '<td style="font-size: 10px; padding: 5px; white-space: break-spaces;" colspan="4" id="">'
		eventMsg +=  '<div style="width: 80px">'+data.NEXT_NODE_NM+'</div></td>' 
		eventMsg += '</tr>'
		eventMsg += '<tr>'
		eventMsg += '<th style="font-size: 10px;">현재정차시간</th>' 
		eventMsg += '<td style="font-size: 10px; padding: 5px;" colspan="8" id="">'
		eventMsg +=  '<div id="cur_stop_tm" style="width: 80px">0초</div></td>' 
		eventMsg += '</tr>'
		eventMsg += '<tr>'
		eventMsg += '<th style="font-size: 10px;">정차메시지</th>' 
		eventMsg += '<td style="font-size: 10px; padding: 5px;" colspan="8" id="">'
		eventMsg +=  '<div id="stopMessage" style="width: 80px"></div></td>' 
		eventMsg += '</tr>'
		eventMsg += '</tbody>' 
		eventMsg += '</table>' 
		eventMsg += '</div> </div>'
		eventMsg += '</div></div>'
			
		addStopTime = setInterval(function() {
			stopTime++;
			
			if(stopTime >= 60) {
				min = parseInt((stopTime/60))+"분 ";
			}
			sec = parseInt((stopTime%60))+"초";
			
			var total = min+sec;
			
			$("#cur_stop_tm").text(total);
		}, 1000);
	}
	
	else {
		if (routMap.mapInfo[mapId].eventOverArr[0] != null) {
			if(routMap.mapInfo[mapId].dsptchOverArr.length != 0) {
				$(".dsptchMessagePopup").hide();
			}
			
		/*	if(routMap.mapInfo[mapId].divEvent == "ET001"){
				$("#event_type").text(data.EVT_TYPE);
			}*/
			
			setTimeout(function() {
				clearInterval(addStopTime);
				if (routMap.mapInfo[mapId].eventOverArr[0] != null) {
					routMap.mapInfo[mapId].eventOverArr[0].setMap(null);
					routMap.mapInfo[mapId].eventOverArr[0] = null;		
				}
			},1500);
			
		}		
	}
	
/*	else if (routMap.mapInfo[mapId].divEvent == "ET002") {
		if (routMap.mapInfo[mapId].eventOverArr[0] != null) {
			
			$("#event_type").text(data.EVT_TYPE);
			
			setTimeout(function() {
				clearInterval(addStopTime);
				routMap.mapInfo[mapId].eventOverArr[0].setMap(null);
				routMap.mapInfo[mapId].eventOverArr[0] = null;		
			},1500);
			
		}
	}
	
	//그 외 이벤트
	else if (routMap.mapInfo[mapId].divEvent == "ETC") {
		if (routMap.mapInfo[mapId].eventOverArr[0] != null) {
			routMap.mapInfo[mapId].eventOverArr[0].setMap(null);
			routMap.mapInfo[mapId].eventOverArr[0] = null;
		}
	}*/
	
	//도착 이벤트
	if (routMap.mapInfo[mapId].divEvent == "ET001") {
		eventOverlay = new kakao.maps.CustomOverlay({
			content: eventMsg,
			position: marker.getPosition(),
			zIndex : zIndex
		});
		
		routMap.mapInfo[mapId].eventOverlay = eventOverlay;
		/*if(idx<routMap.mapInfo[mapId].eventOverArr.length){
			routMap.mapInfo[mapId].eventOverArr[idx] = routMap.mapInfo[mapId].eventOverlay;
		}
		else{
			routMap.mapInfo[mapId].eventOverArr.push(routMap.mapInfo[mapId].eventOverlay);
		}*/
		//routMap.mapInfo[mapId].eventOverArr.push(routMap.mapInfo[mapId].eventOverlay);
		routMap.mapInfo[mapId].eventOverArr[0] = routMap.mapInfo[mapId].eventOverlay;
		eventOverlay.setMap(routMap.mapInfo[mapId].map);
		
		routMap.mapInfo[mapId].isEvent = "off";
		routMap.mapInfo[mapId].divEvent = "";
		
		/*setTimeout(function() {
			routMap.mapInfo[mapId].eventOverArr[0].setMap(null);
			routMap.mapInfo[mapId].eventOverArr[0] = null;
		},5000);*/
		
	}
}

/**
 * 범례 SHOW 메소드

 * @param mapId : 대상 map id
 * @param list : data list
 * @param focusIdx : 포커스 index
 * @param grid : 대상 grid
 * @param etcOnOff : 'on'시 일반노드, 음성노드 show
 */
routMap.showCategory = function(mapId, list, focusIdx, grid, etcOnOff) {
	$("#"+mapId).find("#category_"+ mapId).empty();
	
	// 좌상단 카테고리 관련
	//var categoryContent = null;
	var categoryClass = null;
	var categoryName = null;
	
	var categoryContentCross = null;
	var categoryContentNormal = null;
	var categoryContentVertex = null;
	var categoryContentRoad = null;
	var categoryContentVoice = null;
	var categoryContentAbnormal = null;
	
	var crossCheck = 0;
	var normalCheck = 0;
	var vertexCheck = 0;
	var roadCheck = 0;
	var voiceCheck = 0;
	var abnormalCheck = 0; 
	
	var classOnOff = null;
	
	if(list != null && list.length != 0) {
		for(var i = 0; i < list.length; i++) {
			
			if(crossCheck == 0 && list[i].NODE_TYPE == routMap.NODE_TYPE.CROSS){
				categoryClass = "crossimg";
				categoryName = "교차로";	
				categoryContentCross =	'<li class="'+ routMap.mapInfo[mapId].isShowCrs +'">' ;
				categoryContentCross +=	'<span class="' +categoryClass +'"></span>';
				categoryContentCross +=	categoryName;
				categoryContentCross +=	'</li>';   
				crossCheck = 1;
			}
			else if(normalCheck == 0 && list[i].NODE_TYPE == routMap.NODE_TYPE.BUSSTOP){
				categoryClass = "normal_busimg";
				categoryName = "정류소";
				categoryContentNormal =	'<li class="'+ routMap.mapInfo[mapId].isShowNormal +'">' ;
				categoryContentNormal +=	'<span class="' +categoryClass +'"></span>';
				categoryContentNormal +=	categoryName;
				categoryContentNormal +=	'</li>';
				normalCheck = 1;
			}
			else if(vertexCheck == 0 && list[i].NODE_TYPE == routMap.NODE_TYPE.VERTEX){
				categoryClass = "verteximg";
				categoryName = "경로";			
				categoryContentVertex =	'<li class="'+ routMap.mapInfo[mapId].isShowVertex +'">' ;
				categoryContentVertex +=	'<span class="' +categoryClass +'"></span>';
				categoryContentVertex +=	categoryName;
				categoryContentVertex +=	'</li>';
				vertexCheck = 1;
			}
			else if(typeof etcOnOff !="undefined" && etcOnOff =="on" &&voiceCheck == 0 && list[i].NODE_TYPE == routMap.NODE_TYPE.SOUND){
				categoryClass = "voiceimg"
				categoryName = "음성";			
				categoryContentVoice =	'<li class="'+ routMap.mapInfo[mapId].isShowSound +'">' ;
				categoryContentVoice +=	'<span class="' +categoryClass +'"></span>';
				categoryContentVoice +=	categoryName;
				categoryContentVoice +=	'</li>';
				voiceCheck = 1;
			}
			
			else if(abnormalCheck == 0 && typeof list[i].COND_ERROR != "undefined" && (list[i].COND_ERROR == "CE001") ) {
				categoryClass = "abnormal_busimg"
				categoryName = "고장";
				categoryContentAbnormal =	'<li class="'+ routMap.mapInfo[mapId].isShowAbnormal +'">' ;
				categoryContentAbnormal +=	'<span class="' +categoryClass +'"></span>';
				categoryContentAbnormal +=	categoryName;
				categoryContentAbnormal +=	'</li>';
				abnormalCheck = 1;
			}
			else if(typeof etcOnOff !="undefined" && etcOnOff =="on" &&roadCheck == 0 && list[i].NODE_TYPE == routMap.NODE_TYPE.NORMAL){
				categoryClass = "roadimg";	
				categoryName = "일반노드";
				categoryContentRoad =	'<li class="'+ routMap.mapInfo[mapId].isShowNode +'">' ;
				categoryContentRoad +=	'<span class="' +categoryClass +'"></span>';
				categoryContentRoad +=	categoryName;
				categoryContentRoad +=	'</li>';
				roadCheck = 1;
			}
		} // end for
		
		if(normalCheck == 1) {
			$("#"+mapId).find("#category_"+ mapId).append(categoryContentNormal);			
		}
		if(abnormalCheck == 1) {
			$("#"+mapId).find("#category_"+ mapId).append(categoryContentAbnormal);			
		}
		if(crossCheck == 1) {
			$("#"+mapId).find("#category_"+ mapId).append(categoryContentCross);			
		}
		if(voiceCheck == 1) {
			$("#"+mapId).find("#category_"+ mapId).append(categoryContentVoice);			
		}
		if(roadCheck == 1) {
			$("#"+mapId).find("#category_"+ mapId).append(categoryContentRoad);			
		}
		if(vertexCheck == 1) {
			$("#"+mapId).find("#category_"+ mapId).append(categoryContentVertex);			
		}
		
	} //end if
	
	//클릭 이벤트
	$("#"+mapId).find("li").click( function() {
		routMap.initNode(mapId);
	    var className = this.className;
	    var category = $("#"+mapId).find("#category_"+ mapId);
	    var spanId = $(this).find("span");
	    var spanClassName = spanId.attr("class");
		//placeOverlay.setMap(null);
		
	    //활성화 되어있을 때
		if (className === 'on') {
		    currCategory = '';
		    this.className = '';
		    
	    	if (spanClassName == "crossimg") {
	    		routMap.mapInfo[mapId].isShowCrs = "";
	    	}
	    	//정상 정류소
	    	else if(spanClassName == "normal_busimg") {
	    		routMap.mapInfo[mapId].isShowNormal = "";
	    	}
	    	else if(spanClassName == "roadimg") {
	    		routMap.mapInfo[mapId].isShowRoad = "";
	    	}
	    	else if(spanClassName == "voicimg") {
	    		routMap.mapInfo[mapId].isShowSound = "";
	    	}
	    	//비정상 정류소
	    	else if(spanClassName == "abnormal_busimg") {
	    		routMap.mapInfo[mapId].isShowAbnormal = "";
	    	}
	    			    
		    
		    //routMap.initNode(mapId);
		    //routMap.removeMarkers2(mapId);
		    
		    //removeMarker();
		    
		} else {
		    this.className = 'on';
		    	if (spanClassName == "crossimg") {
		    		routMap.mapInfo[mapId].isShowCrs = "on";
		    	}
		    	else if(spanClassName == "normal_busimg") {
		    		routMap.mapInfo[mapId].isShowNormal = "on";		    		
		    	}
		    	else if(spanClassName == "roadimg") {
		    		routMap.mapInfo[mapId].isShowRoad = "on";		    		
		    	}
		    	else if(spanClassName == "voicimg") {
		    		routMap.mapInfo[mapId].isShowSound = "on";		    		
		    	}
		    	else if(spanClassName == "abnormal_busimg") {
		    		routMap.mapInfo[mapId].isShowAbnormal = "on";	    		
		    	}
		    	
		}	
		for(var i=0; i<list.length; i++){
			routMap.showMarker(mapId, list[i], i, focusIdx, grid);		
		}
		
	}); //end click
} //showCategory


// MO0101M01 전용입니다
/**
 * 범례 SHOW 메소드

 * @param mapId : 대상 map id
 * @param list : data list
 * @param focusIdx : 포커스 index
 * @param grid : 대상 grid
 * @param etcOnOff : 'on'시 일반노드, 음성노드 show
 */
routMap.showCategory2 = function(mapId, sttnMapId, crsMapId, sttnList, crsList, focusIdx, grid) {
	$("#"+mapId).find("#category_"+ mapId).empty();
	
	// 좌상단 카테고리 관련
	//var categoryContent = null;
	var categoryClass = null;
	var categoryName = null;
	
	var categoryContentCross = null;
	var categoryContentNormal = null;
	var categoryContentAbnormal = null;
	
	var crossCheck = 0;
	var normalCheck = 0;
	var abnormalCheck = 0; 
	
	var classOnOff = null;
	categoryClass = "crossimg";
	categoryName = "교차로";	
	categoryContentCross =	'<li class="'+ routMap.mapInfo[crsMapId].isShowCrs +'">' ;
	categoryContentCross +=	'<span class="' +categoryClass +'"></span>';
	categoryContentCross +=	categoryName;
	categoryContentCross +=	'</li>';   
	crossCheck = 1;
	
	categoryClass = "normal_busimg";
	categoryName = "정류소";
	categoryContentNormal =	'<li class="'+ routMap.mapInfo[sttnMapId].isShowNormal +'">' ;
	categoryContentNormal +=	'<span class="' +categoryClass +'"></span>';
	categoryContentNormal +=	categoryName;
	categoryContentNormal +=	'</li>';
	normalCheck = 1;
	
	categoryClass = "abnormal_busimg"
	categoryName = "고장정류소";
	categoryContentAbnormal =	'<li class="'+ routMap.mapInfo[sttnMapId].isShowAbnormal +'">' ;
	categoryContentAbnormal +=	'<span class="' +categoryClass +'"></span>';
	categoryContentAbnormal +=	categoryName;
	categoryContentAbnormal +=	'</li>';
	abnormalCheck = 1;
	
	$("#"+mapId).find("#category_"+ mapId).append(categoryContentNormal);
	$("#"+mapId).find("#category_"+ mapId).append(categoryContentAbnormal);	
	$("#"+mapId).find("#category_"+ mapId).append(categoryContentCross);
	
	//클릭 이벤트
	$("#"+mapId).find("li").click( function() {
		var className = this.className;
		var category = $("#"+mapId).find("#category_"+ mapId);
		var spanId = $(this).find("span");
		var spanClassName = spanId.attr("class");
		
		var categoryMapId = null;
		
		//활성화 되어있을 때
		if (className === 'on') {
			currCategory = '';
			this.className = '';
			
			if (spanClassName == "crossimg") {
				if (MO0101T2.hide) {
					MO0101T0.hide();
					MO0101T1.hide();
					MO0101T2.show();
				}
				
				routMap.initNode(crsMapId);
				routMap.mapInfo[crsMapId].isShowCrs = "";
				for(var i=0; i<crsList.length; i++){
					routMap.showMarker(crsMapId, crsList[i], i, focusIdx, grid);		
				}				
			}
			//정상 정류소
			else if(spanClassName == "normal_busimg") {
				if (MO0101T1.hide) {
					MO0101T0.hide();
					MO0101T1.show();
					MO0101T2.hide();
				}
				
				routMap.initNode(sttnMapId);
				routMap.mapInfo[sttnMapId].isShowNormal = "";
				for(var i=0; i<sttnList.length; i++){
					routMap.showMarker(sttnMapId, sttnList[i], i, focusIdx, grid);		
				}						
			}
			//비정상 정류소
			else if(spanClassName == "abnormal_busimg") {
				routMap.initNode(sttnMapId);
				routMap.mapInfo[sttnMapId].isShowAbnormal = "";
				for(var i=0; i<sttnList.length; i++){
					routMap.showMarker(sttnMapId, sttnList[i], i, focusIdx, grid);		
				}						
			}
			
			
			//routMap.initNode(mapId);
			//routMap.removeMarkers2(mapId);
			
			//removeMarker();
			
		} else {
			this.className = 'on';
			if (spanClassName == "crossimg") {
				routMap.initNode(crsMapId);
				routMap.mapInfo[crsMapId].isShowCrs = "on";
				for(var i=0; i<crsList.length; i++){
					routMap.showMarker(crsMapId, crsList[i], i, focusIdx, grid);		
				}					
			}
			else if(spanClassName == "normal_busimg") {
				routMap.initNode(sttnMapId);
				routMap.mapInfo[sttnMapId].isShowNormal = "on";	
				for(var i=0; i<sttnList.length; i++){
					routMap.showMarker(sttnMapId, sttnList[i], i, focusIdx, grid);		
				}					
			}
			else if(spanClassName == "abnormal_busimg") {
				routMap.initNode(sttnMapId);
				routMap.mapInfo[sttnMapId].isShowAbnormal = "on";
				for(var i=0; i<sttnList.length; i++){
					routMap.showMarker(sttnMapId, sttnList[i], i, focusIdx, grid);		
				}					
			}
			
		}	
		
	}); //end click
} //showCategory2

/**
 *  마커 보이기

 * @param mapId : 대상 map id
 * @param list : data list
 * @param focusIdx : 포커스 index
 * @param grid : 대상 grid
 * @param etcOnOff : 'on'시 일반노드, 음성노드 show
 */
routMap.showMarker = function(mapId, data, idx, focusIdx, grid) {
	// 마커 이미지의 이미지 크기 입니다
	var imageSize = new kakao.maps.Size(19, 28); 
	var markerImage = null;
	var markerOverImage = null;
	var markerSelImage = null;

	
	var zIndex= -1;
	if(routMap.mapInfo[mapId].isShowCrs == "on" && data.NODE_TYPE == routMap.NODE_TYPE.CROSS){
		zIndex = 1;
		imageSize = new kakao.maps.Size(19, 19);
		markerImage = new kakao.maps.MarkerImage("/cm/images/tmap/cross.png", imageSize);
		markerSelImage = new kakao.maps.MarkerImage("/cm/images/tmap/cross_selected.png", imageSize);
	}
	else if(routMap.mapInfo[mapId].isShowNormal == "on" && data.NODE_TYPE == routMap.NODE_TYPE.BUSSTOP && 
			(typeof data.COND_ERROR == "undefined"||data.COND_ERROR == "CE002") ) {
		zIndex = 2;
		imageSize = new kakao.maps.Size(25, 24);
		markerImage = new kakao.maps.MarkerImage("/cm/images/tmap/busstop.png", imageSize);
		markerSelImage = new kakao.maps.MarkerImage("/cm/images/tmap/busstop_selected.png", imageSize);
	}
	else if(routMap.mapInfo[mapId].isShowVertex == "on" &&data.NODE_TYPE == routMap.NODE_TYPE.VERTEX){
		imageSize = new kakao.maps.Size(12, 12);
		markerImage = new kakao.maps.MarkerImage("/cm/images/tmap/vertex.png", imageSize);
		markerSelImage = new kakao.maps.MarkerImage("/cm/images/tmap/vertex_selected.png", imageSize);
	}
	else if(routMap.mapInfo[mapId].isShowSignal == "on" &&data.NODE_TYPE == routMap.NODE_TYPE.SIGNAL){
		imageSize = new kakao.maps.Size(12, 12);
		markerImage = new kakao.maps.MarkerImage("/cm/images/tmap/vertex.png", imageSize);
		markerSelImage = new kakao.maps.MarkerImage("/cm/images/tmap/vertex_selected.png", imageSize);
	}
	else if (routMap.mapInfo[mapId].isShowNode == "on" && data.Node_TYPE == routMap.NODE_TYPE.NORMAL){
		markerImage = new kakao.maps.MarkerImage("/cm/images/tmap/road_trans.png", imageSize);
		markerSelImage = new kakao.maps.MarkerImage("/cm/images/tmap/road_selected.png", imageSize);
	}
	
	else if(typeof data.COND_ERROR != "undefined" && routMap.mapInfo[mapId].isShowAbnormal == "on" &&(data.COND_ERROR == "CE001")) {
		imageSize = new kakao.maps.Size(25, 24);
		markerImage = new kakao.maps.MarkerImage("/cm/images/tmap/busstop_black.png", imageSize);		
		markerSelImage = new kakao.maps.MarkerImage("/cm/images/tmap/busstop_black.png", imageSize);

	}
	else if (routMap.mapInfo[mapId].isShowIncdnt == "on"){
		imageSize = new kakao.maps.Size(19, 19);
		markerImage = new kakao.maps.MarkerImage("/cm/images/tmap/incdnt.png", imageSize);
		markerSelImage = new kakao.maps.MarkerImage("/cm/images/tmap/incdnt_selected.png", imageSize);
	}
	else if (routMap.mapInfo[mapId].isShowViolt == "on"){
		imageSize = new kakao.maps.Size(19, 19);
		markerImage = new kakao.maps.MarkerImage("/cm/images/tmap/violt.png", imageSize);
		markerSelImage = new kakao.maps.MarkerImage("/cm/images/tmap/violt_selected.png", imageSize);
	}
		
	var marker = null;
	
	if(idx==focusIdx) {
		zIndex = 3;
		// 마커 이미지를 생성합니다    
		marker = new kakao.maps.Marker({
			position : new kakao.maps.LatLng(data.GPS_Y, data.GPS_X), // Marker의 중심좌표 // 설정.
			//title : data.label, // Marker의 라벨.
			image : markerSelImage,
			draggable : data.draggable,
			zIndex: zIndex
		});
		routMap.mapInfo[mapId].selectedMarker = marker;
	}
	else {
		// 마커 이미지를 생성합니다    
		marker = new kakao.maps.Marker({
			position : new kakao.maps.LatLng(data.GPS_Y, data.GPS_X), // Marker의 중심좌표 // 설정.
			//title : data.label, // Marker의 라벨.
			image : markerImage,
			draggable : data.draggable,
			zIndex: zIndex
		});
	}

	marker.normalImage = markerImage;


	var infoWindow = null;

	var overlay = null;
	var msg = "";
	if(data.NODE_TYPE == routMap.NODE_TYPE.BUSSTOP){
		 msg = "<div class = 'customoverlay busstop'>";
		 
		 //장치상태 에러일때
		 if(data.COND_ERROR == 'CE001') {
			 msg = "";
			 msg = "<div class = 'customoverlay conderror busstop'>";
		 }
	}
	else if(data.NODE_TYPE == routMap.NODE_TYPE.CROSS){
		msg = "<div class = 'customoverlay cross'>";
		
		 if(data.COND_ERROR == 'CE001') {
			 msg = "";
			 msg = "<div class = 'customoverlay conderror busstop'>";
		 }		
	}
	
	else if(typeof data.INCDNT_TYPE != "undefined" ) {
		msg = "<div class = 'customoverlay incdnt'>";
	}
	
	else if(typeof data.VIOLT_TYPE != "undefined") {
		msg = "<div class = 'customoverlay vilot'>";
	}
	
	if(data.draggable){
		
		msg += "<span class = 'map_title' style=''>" + data.NODE_NM
			+ "&nbsp;<a href='javascript:void(0);' onclick='routMap.nodeChange(\"" + mapId+"\","+ idx + ");' style='color:blue' target='_self'>변경</a> </span>"
			+ "</div>";	
			//+ "<span class = '' style='font-size: 12px; margin-left:2px; margin-bottom:2px; display:block;'>"+ data.GPS_Y + "," + data.GPS_X +"</span>"
	}
	
	else if (typeof data.NODE_NM != "undefined") {
		msg += "<span class = 'map_title' style=''>" + data.NODE_NM + "</span>"
			+ "</div>";
			//+ "<span class = '' style='font-size: 12px; margin-left:2px; margin-bottom:2px; display:block;'>"+ data.GPS_Y + "," + data.GPS_X +"</span>"
	 }
	
	else if(typeof data.INCDNT_TYPE != "undefined" ) {
		var incdntType = data.INCDNT_TYPE;
		var showType = "";
		
		switch(incdntType) {
			case "IC001":
				showType = "사고";
				break;
			case "IC002":
				showType = "낙하";
				break;
			case "IC003":
				showType = "고장";
				break;
			case "IC004":
				showType = "기타";
				break;
		}
		
		msg += "<span class = 'map_title' style=''>" + showType + "</span>"
		+ "</div>";
	}
	
	else if(typeof data.VIOLT_TYPE != "undefined" ) {
		var violtType = data.VIOLT_TYPE;
		var showType = "";
		
		switch(violtType) {
		case "VL001":
			showType = "급가속";
			break;
		case "VL002":
			showType = "급감속";
			break;
		case "VL003":
			showType = "과속";
			break;
		case "VL004":
			showType = "무정차통과";
			break;
		case "VL005":
			showType = "개문주행";
			break;
		case "VL006":
			showType = "급출발";
			break;
		case "VL007":
			showType = "노선이탈";
			break;
		case "VL008":
			showType = "급정지";
			break;
		}
		
		msg += "<span class = 'map_title' style=''>" + showType + "</span>"
		+ "</div>";
	}
	
	if(idx==focusIdx) {
		overlay = new kakao.maps.CustomOverlay({
			content: msg,
			position: marker.getPosition(),
			zIndex : zIndex
		});
	}
	else {
		overlay = new kakao.maps.CustomOverlay({
			content: msg,
			position: marker.getPosition(),
			zIndex : 4
		});
		
	}
	// 마커에 click 이벤트를 등록합니다
	kakao.maps.event.addListener(marker, 'click', function() {
		//infoWindow.close();
		// 클릭된 마커가 없고, click 마커가 클릭된 마커가 아니면
		// 마커의 이미지를 클릭 이미지로 변경합니다
		if (!routMap.mapInfo[mapId].selectedMarker
				|| routMap.mapInfo[mapId].selectedMarker !== marker) {
			
			// 클릭된 마커 객체가 null이 아니면
			// 클릭된 마커의 이미지를 기본 이미지로 변경하고
			!!routMap.mapInfo[mapId].selectedMarker
					&& routMap.mapInfo[mapId].selectedMarker
							.setImage(routMap.mapInfo[mapId].selectedMarker.normalImage);
			
			// 현재 클릭된 마커의 이미지는 클릭 이미지로 변경합니다
			marker.setImage(markerSelImage);
		}
		
		
		//routMap.mapInfo[mapId].markers[routMap.mapInfo[mapId].selectedIndex].setImage(routMap.mapInfo[mapId].selectedMarker.normalImage);
		
		// 클릭된 마커를 현재 클릭된 마커 객체로 설정합니다
		routMap.mapInfo[mapId].selectedMarker = marker;
		marker.setZIndex(3);
		//routMap.mapInfo[mapId].isMove = false;
		//routMap.mapInfo[mapId].overArr[routMap.mapInfo[mapId].selectedIndex].setMap(null);
		//routMap.mapInfo[mapId].oldSelectedIndex = routMap.mapInfo[mapId].selectedIndex;
		//routMap.mapInfo[mapId].selectedIndex = idx;
		if(typeof grid != "undefined") {
			if(typeof data.NODE_NM != "undefined"){
				//routMap.focusNode(mapId, grid,idx);
				grid.setFocusedCell(idx,"NODE_ID");
			}
			else if(typeof data.INCDNT_TYPE != "undefined") {
				//routMap.focusNode(mapId, grid,idx);
				grid.setFocusedCell(idx,"ROUT_NM");
			}
			else if(typeof data.VIOLT_TYPE != "undefined") {
				//routMap.focusNode(mapId, grid,idx);
				grid.setFocusedCell(idx,"ROUT_NM");
			}
		}
	});
	
	kakao.maps.event.addListener(marker, 'rightclick', function() {
		routMap.removeRightClickOverlay(mapId);
		
		var rightClickOverlay = null;
		var rightClickMsg = "";
		var focusCell = "";
		
		switch(data.NODE_TYPE) {
			case routMap.NODE_TYPE.BUSSTOP:
				rightClickMsg += '<div class="rightclickoverlay" style="cursor: default;">'
				rightClickMsg += '<div class="contextWrap">'
				rightClickMsg += '    <a data-id="here" href="javascript:void(0)" class="sttnInfo" onclick="">'
				rightClickMsg += '	            <span class="text">정류소 상태정보</span>'
				rightClickMsg += '     </a>'
		        rightClickMsg += '  	</div>	'
		        rightClickMsg += '  </div>	';
		        focusCell = "VHC_NO";
				break;
		}
		
		rightClickOverlay = new kakao.maps.CustomOverlay({
			content: rightClickMsg,
			position: marker.getPosition(),
			zIndex : 999
		});		
	
		routMap.mapInfo[mapId].rightClickOverlay = rightClickOverlay;
		rightClickOverlay.setMap(routMap.mapInfo[mapId].map);	
		
		if(typeof grid != "undefined") {
			grid.setFocusedCell(idx, focusCell);
		}		
		
	}); //end rightclick
	
	/*kakao.maps.event.addListener(marker, 'dragstart', function() {
		overlay.setMap(null);
		//infoWindow.close();
		routMap.mapInfo[mapId].dragging = true;
	});
	
	kakao.maps.event.addListener(marker, 'dragend', function() {
		
	});	*/
	//routMap.mapInfo[mapId].infoWindow.setMap(routMap.mapInfo[mapId].map); 
	routMap.mapInfo[mapId].overlay = overlay;
	routMap.mapInfo[mapId].overArr.push(routMap.mapInfo[mapId].overlay);
	
	if(idx==focusIdx) {
		if(markerImage != null){
			//routMap.mapInfo[mapId].overArr[focusIdx].setMap(routMap.mapInfo[mapId].map);
			overlay.setMap(routMap.mapInfo[mapId].map);
		}
	}
	else {
		kakao.maps.event.addListener(marker, 'mouseover', routMap.makeOverListener(routMap.mapInfo[mapId].map, marker, overlay));
		kakao.maps.event.addListener(marker, 'mouseout', routMap.makeOutListener(routMap.mapInfo[mapId],marker,overlay,markerImage));
	}
	
	if(markerImage != null)
		marker.setMap(routMap.mapInfo[mapId].map); //Marker가 표시될 Map 설정.
	
	routMap.mapInfo[mapId].markers.push(marker);
}

/**
 *  그리드 없이 마커 보이기

 * @param mapId : 대상 map id
 * @param list : data list
 */
routMap.showMarkerNoGrid= function(mapId, data) {
	// 마커 이미지의 이미지 크기 입니다
	var imageSize = new kakao.maps.Size(19, 28); 
	var markerImage = null;
	var markerOverImage = null;
	var markerSelImage = null;

	
	var zIndex= -1;
	if(routMap.mapInfo[mapId].isShowCrs == "on" && data.NODE_TYPE == routMap.NODE_TYPE.CROSS){
		zIndex = 1;
		imageSize = new kakao.maps.Size(19, 19);
		markerImage = new kakao.maps.MarkerImage("/cm/images/tmap/cross.png", imageSize);
		markerSelImage = new kakao.maps.MarkerImage("/cm/images/tmap/cross_selected.png", imageSize);
	}
	else if(routMap.mapInfo[mapId].isShowNormal == "on" && data.NODE_TYPE == routMap.NODE_TYPE.BUSSTOP && 
			(typeof data.COND_ERROR == "undefined"||data.COND_ERROR == "CE002") ) {
		zIndex = 2;
		imageSize = new kakao.maps.Size(25, 24);
		markerImage = new kakao.maps.MarkerImage("/cm/images/tmap/busstop.png", imageSize);
		markerSelImage = new kakao.maps.MarkerImage("/cm/images/tmap/busstop_selected.png", imageSize);
	}
	else if(routMap.mapInfo[mapId].isShowVertex == "on" &&data.NODE_TYPE == routMap.NODE_TYPE.VERTEX){
		imageSize = new kakao.maps.Size(12, 12);
		markerImage = new kakao.maps.MarkerImage("/cm/images/tmap/vertex.png", imageSize);
		markerSelImage = new kakao.maps.MarkerImage("/cm/images/tmap/vertex_selected.png", imageSize);
	}
	else if(routMap.mapInfo[mapId].isShowSignal == "on" &&data.NODE_TYPE == routMap.NODE_TYPE.SIGNAL){
		imageSize = new kakao.maps.Size(12, 12);
		markerImage = new kakao.maps.MarkerImage("/cm/images/tmap/vertex.png", imageSize);
		markerSelImage = new kakao.maps.MarkerImage("/cm/images/tmap/vertex_selected.png", imageSize);
	}
	else if (routMap.mapInfo[mapId].isShowNode == "on" && data.Node_TYPE == routMap.NODE_TYPE.NORMAL){
		markerImage = new kakao.maps.MarkerImage("/cm/images/tmap/road_trans.png", imageSize);
		markerSelImage = new kakao.maps.MarkerImage("/cm/images/tmap/road_selected.png", imageSize);
	}
	
	else if(typeof data.COND_ERROR != "undefined" && routMap.mapInfo[mapId].isShowAbnormal == "on" &&(data.COND_ERROR == "CE001")) {
		imageSize = new kakao.maps.Size(25, 24);
		markerImage = new kakao.maps.MarkerImage("/cm/images/tmap/busstop_black.png", imageSize);		
		markerSelImage = new kakao.maps.MarkerImage("/cm/images/tmap/busstop_black.png", imageSize);

	}
	else if (routMap.mapInfo[mapId].isShowIncdnt == "on"){
		markerImage = new kakao.maps.MarkerImage("/cm/images/tmap/incdnt.png", imageSize);
		markerSelImage = new kakao.maps.MarkerImage("/cm/images/tmap/incdnt_selected.png", imageSize);
	}
	else if (routMap.mapInfo[mapId].isShowViolt == "on"){
		markerImage = new kakao.maps.MarkerImage("/cm/images/tmap/violt.png", imageSize);
		markerSelImage = new kakao.maps.MarkerImage("/cm/images/tmap/violt_selected.png", imageSize);
	}
		
	var marker = null;
	
	/*if(idx==focusIdx) {
		zIndex = 3;
		// 마커 이미지를 생성합니다    
		marker = new kakao.maps.Marker({
			position : new kakao.maps.LatLng(data.GPS_Y, data.GPS_X), // Marker의 중심좌표 // 설정.
			//title : data.label, // Marker의 라벨.
			image : markerSelImage,
			draggable : data.draggable,
			zIndex: zIndex
		});
		routMap.mapInfo[mapId].selectedMarker = marker;
	}
	else {
		// 마커 이미지를 생성합니다    
		marker = new kakao.maps.Marker({
			position : new kakao.maps.LatLng(data.GPS_Y, data.GPS_X), // Marker의 중심좌표 // 설정.
			//title : data.label, // Marker의 라벨.
			image : markerImage,
			draggable : data.draggable,
			zIndex: zIndex
		});
	}
	*/

	// 마커 이미지를 생성합니다    
	marker = new kakao.maps.Marker({
		position : new kakao.maps.LatLng(data.GPS_Y, data.GPS_X), // Marker의 중심좌표 // 설정.
		//title : data.label, // Marker의 라벨.
		image : markerImage,
		draggable : data.draggable,
		zIndex: zIndex
	});	
	marker.normalImage = markerImage;


	var infoWindow = null;

	var overlay = null;
	var msg = "";
	if(data.NODE_TYPE == routMap.NODE_TYPE.BUSSTOP){
		 msg = "<div class = 'customoverlay busstop'>";
		 
		 //장치상태 에러일때
		 if(data.COND_ERROR == 'CE001') {
			 msg = "";
			 msg = "<div class = 'customoverlay conderror busstop'>";
		 }
	}
	else if(data.NODE_TYPE == routMap.NODE_TYPE.CROSS){
		msg = "<div class = 'customoverlay cross'>";
		
		 if(data.COND_ERROR == 'CE001') {
			 msg = "";
			 msg = "<div class = 'customoverlay conderror busstop'>";
		 }		
	}
	
	else if(typeof data.INCDNT_TYPE != "undefined" ) {
		msg = "<div class = 'customoverlay incdnt'>";
	}
	
	else if(typeof data.VIOLT_TYPE != "undefined") {
		msg = "<div class = 'customoverlay vilot'>";
	}
	
	if(data.draggable){
		
		msg += "<span class = 'map_title' style=''>" + data.NODE_NM
			+ "&nbsp;<a href='javascript:void(0);' onclick='routMap.nodeChange(\"" + mapId+"\"," + ");' style='color:blue' target='_self'>변경</a> </span>"
			+ "</div>";	
	}
	
	else if (typeof data.NODE_NM != "undefined") {
		msg += "<span class = 'map_title' style=''>" + data.NODE_NM + "</span>"
			+ "</div>";
	 }
	
	else if(typeof data.INCDNT_TYPE != "undefined" ) {
		var incdntType = data.INCDNT_TYPE;
		var showType = "";
		
		switch(incdntType) {
			case "IC001":
				showType = "사고";
				break;
			case "IC002":
				showType = "낙하";
				break;
			case "IC003":
				showType = "고장";
				break;
			case "IC004":
				showType = "기타";
				break;
		}
		
		msg += "<span class = 'map_title' style=''>" + showType + "</span>"
		+ "</div>";
	}
	
	else if(typeof data.VIOLT_TYPE != "undefined" ) {
		var violtType = data.VIOLT_TYPE;
		var showType = "";
		
		switch(violtType) {
		case "VL001":
			showType = "급가속";
			break;
		case "VL002":
			showType = "급감속";
			break;
		case "VL003":
			showType = "과속";
			break;
		case "VL004":
			showType = "무정차통과";
			break;
		case "VL005":
			showType = "개문주행";
			break;
		case "VL006":
			showType = "급출발";
			break;
		case "VL007":
			showType = "노선이탈";
			break;
		case "VL008":
			showType = "급정지";
			break;
		}
		
		msg += "<span class = 'map_title' style=''>" + showType + "</span>"
		+ "</div>";
	}
	
//	if(idx==focusIdx) {
//		overlay = new kakao.maps.CustomOverlay({
//			content: msg,
//			position: marker.getPosition(),
//			zIndex : zIndex
//		});
//	}
//	else {
//		overlay = new kakao.maps.CustomOverlay({
//			content: msg,
//			position: marker.getPosition(),
//			zIndex : 4
//		});
//	}
	
	overlay = new kakao.maps.CustomOverlay({
		content: msg,
		position: marker.getPosition(),
		zIndex : 4	
	});
	
	// 마커에 click 이벤트를 등록합니다
	kakao.maps.event.addListener(marker, 'click', function() {
		// 클릭된 마커가 없고, click 마커가 클릭된 마커가 아니면
		// 마커의 이미지를 클릭 이미지로 변경합니다
		if (!routMap.mapInfo[mapId].selectedMarker
				|| routMap.mapInfo[mapId].selectedMarker !== marker) {
			// 클릭된 마커 객체가 null이 아니면
			// 클릭된 마커의 이미지를 기본 이미지로 변경하고
			!!routMap.mapInfo[mapId].selectedMarker && routMap.mapInfo[mapId].selectedMarker
			.setImage(routMap.mapInfo[mapId].selectedMarker.normalImage);
			routMap.mapInfo[mapId].selectedMarker = marker;
			// 현재 클릭된 마커의 이미지는 클릭 이미지로 변경합니다
			marker.setImage(markerSelImage);
			
			// 기존 오버레이를 모두 삭제합니다.
			routMap.removeAllOverlay(mapId);
			
			// 클릭된 마커의 오버레이를 화면에 표시합니다.
			routMap.mapInfo[mapId].overlay = overlay;
			routMap.mapInfo[mapId].overArr.push(routMap.mapInfo[mapId].overlay);
			overlay.setMap(routMap.mapInfo[mapId].map);
		}
		
		//클릭된 마커가 기존 클릭된 마커라면
		else if (routMap.mapInfo[mapId].selectedMarker == marker) {
			marker.setImage(markerImage);
		}
		
		// 클릭된 마커를 현재 클릭된 마커 객체로 설정합니다
		routMap.mapInfo[mapId].selectedMarker = marker;
		marker.setZIndex(3);
		
//		routMap.mapInfo[mapId].oldSelectedIndex = routMap.mapInfo[mapId].selectedIndex;
//		routMap.mapInfo[mapId].selectedIndex = idx;
//		if(typeof grid != "undefined") {
//			if(typeof data.NODE_NM != "undefined"){
//				grid.setFocusedCell(idx,"NODE_ID");
//			}
//			else if(typeof data.INCDNT_TYPE != "undefined") {
//				grid.setFocusedCell(idx,"ROUT_NM");
//			}
//			else if(typeof data.VIOLT_TYPE != "undefined") {
//				grid.setFocusedCell(idx,"ROUT_NM");
//			}
//		}
		
	});
	
	routMap.mapInfo[mapId].overlay = overlay;
	routMap.mapInfo[mapId].overArr.push(routMap.mapInfo[mapId].overlay);
	
//	if(idx==focusIdx) {
//		if(markerImage != null){
//			overlay.setMap(routMap.mapInfo[mapId].map);
//		}
//	}
//	else {
//		kakao.maps.event.addListener(marker, 'mouseover', routMap.makeOverListener(routMap.mapInfo[mapId].map, marker, overlay));
//		kakao.maps.event.addListener(marker, 'mouseout', routMap.makeOutListener(routMap.mapInfo[mapId],marker,overlay,markerImage));
//	}
	//클릭된 마커가 아닐때만 mouseover와 mouseout이 작동합니다.
	kakao.maps.event.addListener(marker, 'mouseover', routMap.makeOverListener(routMap.mapInfo[mapId].map, marker, overlay, markerSelImage));
	kakao.maps.event.addListener(marker, 'mouseout', routMap.makeOutListener(routMap.mapInfo[mapId],marker,overlay,markerImage, markerSelImage));	
	
	
	if(markerImage != null)
		marker.setMap(routMap.mapInfo[mapId].map); //Marker가 표시될 Map 설정.
	
	routMap.mapInfo[mapId].markers.push(marker);	
	
}  




/**
 *  마커 보이기
 * @param mapId : 대상 map id
 * @param list : data list
 * @param idx : 마커 index
 */
routMap.showMarker2 = function(mapId, data, idx) {

	// 마커 이미지의 이미지 크기 입니다
	var imageSize = new kakao.maps.Size(19, 28);
	var markerImage = null;
	var markerOverImage = null;
	var markerSelImage = null;
	
	var zIndex= -1;
	if(routMap.mapInfo[mapId].isShowCrs == "on" && data.NODE_TYPE == routMap.NODE_TYPE.CROSS){
		zIndex = 1;
		imageSize = new kakao.maps.Size(19, 19);
		markerImage = new kakao.maps.MarkerImage("/cm/images/tmap/cross.png", imageSize);
		markerSelImage = new kakao.maps.MarkerImage("/cm/images/tmap/cross_selected.png", imageSize);
	}
	else if(routMap.mapInfo[mapId].isShowNormal == "on" && data.NODE_TYPE == routMap.NODE_TYPE.BUSSTOP && 
			(typeof data.COND_ERROR == "undefined"||data.COND_ERROR == "CE002") ) {
		zIndex = 2;
		imageSize = new kakao.maps.Size(25, 24);
		markerImage = new kakao.maps.MarkerImage("/cm/images/tmap/busstop.png", imageSize);
		markerSelImage = new kakao.maps.MarkerImage("/cm/images/tmap/busstop_selected.png", imageSize);
	}
	else if(routMap.mapInfo[mapId].isShowVertex == "on" &&data.NODE_TYPE == routMap.NODE_TYPE.VERTEX){
		imageSize = new kakao.maps.Size(12, 12); 
		markerImage = new kakao.maps.MarkerImage("/cm/images/tmap/vertex.png", imageSize);
		markerSelImage = new kakao.maps.MarkerImage("/cm/images/tmap/vertex_selected.png", imageSize);
	}
	else if(routMap.mapInfo[mapId].isShowSignal == "on" &&data.NODE_TYPE == routMap.NODE_TYPE.SIGNAL){
		imageSize = new kakao.maps.Size(12, 12); 
		markerImage = new kakao.maps.MarkerImage("/cm/images/tmap/vertex.png", imageSize);
		markerSelImage = new kakao.maps.MarkerImage("/cm/images/tmap/vertex_selected.png", imageSize);
	}
	else if (routMap.mapInfo[mapId].isShowNode == "on" && data.Node_TYPE == routMap.NODE_TYPE.NORMAL){
		markerImage = new kakao.maps.MarkerImage("/cm/images/tmap/road_trans.png", imageSize);
		markerSelImage = new kakao.maps.MarkerImage("/cm/images/tmap/road_selected.png", imageSize);
	}
	
	else if(typeof data.COND_ERROR != "undefined" && routMap.mapInfo[mapId].isShowAbnormal == "on" &&data.COND_ERROR == "CE001") {
		imageSize = new kakao.maps.Size(25, 24);
		markerImage = new kakao.maps.MarkerImage("/cm/images/tmap/busstop_black.png", imageSize);		
		markerSelImage = new kakao.maps.MarkerImage("/cm/images/tmap/busstop_black.png", imageSize);

	}
	
	var marker = null;
	
	if (markerImage == null) {
		return false;
	}
	// 마커 이미지를 생성합니다    
	marker = new kakao.maps.Marker({
		position : new kakao.maps.LatLng(data.GPS_Y, data.GPS_X), // Marker의 중심좌표 // 설정.
		//title : data.label, // Marker의 라벨.
		image : markerImage,
		draggable : data.draggable,
		zIndex: zIndex
	});


	marker.normalImage = markerImage;


	var infoWindow = null;

	var overlay = null;
	var msg = "";
	if(data.NODE_TYPE == routMap.NODE_TYPE.BUSSTOP){
		 msg = "<div class = 'customoverlay busstop'>";
		 
		 //장치상태 에러일때
		 if(data.COND_ERROR == 'CE001') {
			 msg = "";
			 msg = "<div class = 'customoverlay conderror busstop'>";
		 }
	}
	else if(data.NODE_TYPE == routMap.NODE_TYPE.CROSS){
		msg = "<div class = 'customoverlay cross'>";
		
		 if(data.COND_ERROR == 'CE001') {
			 msg = "";
			 msg = "<div class = 'customoverlay conderror busstop'>";
		 }		
	}
	if(data.draggable){
		
		msg += "<span class = 'map_title' style=''>" + data.NODE_NM
			+ "&nbsp;<a href='javascript:void(0);' onclick='routMap.nodeChange(\"" + mapId+"\","+ idx + ");' style='color:blue' target='_self'>변경</a> </span>"
			+ "</div>";	
			//+ "<span class = '' style='font-size: 12px; margin-left:2px; margin-bottom:2px; display:block;'>"+ data.GPS_Y + "," + data.GPS_X +"</span>"
	}
	else {
		msg += "<span class = 'map_title' style=''>" + data.NODE_NM + "</span>"
			+ "</div>";
			//+ "<span class = '' style='font-size: 12px; margin-left:2px; margin-bottom:2px; display:block;'>"+ data.GPS_Y + "," + data.GPS_X +"</span>"
	 }
	
	overlay = new kakao.maps.CustomOverlay({
		content: msg,
		position: marker.getPosition(),
		zIndex : 4
	});
	
	// 마커에 click 이벤트를 등록합니다
	kakao.maps.event.addListener(marker, 'click', function() {
		// 클릭된 마커가 없고, click 마커가 클릭된 마커가 아니면
		// 마커의 이미지를 클릭 이미지로 변경합니다
		if (!routMap.mapInfo[mapId].selectedMarker
				|| routMap.mapInfo[mapId].selectedMarker !== marker) {
			
			// 클릭된 마커 객체가 null이 아니면
			// 클릭된 마커의 이미지를 기본 이미지로 변경하고
			!!routMap.mapInfo[mapId].selectedMarker
					&& routMap.mapInfo[mapId].selectedMarker
							.setImage(routMap.mapInfo[mapId].selectedMarker.normalImage);
			
			// 현재 클릭된 마커의 이미지는 클릭 이미지로 변경합니다
			marker.setImage(markerSelImage);
		}
		
		
		//routMap.mapInfo[mapId].markers[routMap.mapInfo[mapId].selectedIndex].setImage(routMap.mapInfo[mapId].selectedMarker.normalImage);
		
		// 클릭된 마커를 현재 클릭된 마커 객체로 설정합니다
		routMap.mapInfo[mapId].selectedMarker = marker;
		marker.setZIndex(3);
		routMap.mapInfo[mapId].isMove = false;
		//routMap.mapInfo[mapId].overArr[routMap.mapInfo[mapId].selectedIndex].setMap(null);
		//routMap.mapInfo[mapId].oldSelectedIndex = routMap.mapInfo[mapId].selectedIndex;
		//routMap.mapInfo[mapId].selectedIndex = idx;
		if(typeof grid != "undefined") {
			//routMap.focusNode(mapId, grid,idx);
			grid.setFocusedCell(idx,"NODE_ID");
		}
	});	
	
	kakao.maps.event.addListener(marker, 'mouseover', routMap.makeOverListener(routMap.mapInfo[mapId].map, marker, overlay));
	kakao.maps.event.addListener(marker, 'mouseout', routMap.makeOutListener(routMap.mapInfo[mapId],marker,overlay,markerImage));
	
	marker.setMap(routMap.mapInfo[mapId].map); //Marker가 표시될 Map 설정.
	routMap.mapInfo[mapId].markers.push(marker);
}

/**
 *  탭이 있을 경우 마커 보이기

 * @param mapId : 대상 map id
 * @param list : data list
 * @param focusIdx : 포커스 index
 * @param grid : 대상 grid
 * @param etcOnOff : 'on'시 일반노드, 음성노드 show
 */
routMap.showMarkerTab = function(mapId, data, idx, focusIdx, grid) {
	// 마커 이미지의 이미지 크기 입니다
	var imageSize = new kakao.maps.Size(24, 35); 
	var markerImage = null;
	var markerOverImage = null;
	var markerSelImage = null;

	
	var zIndex= -1;
	if(routMap.mapInfo[mapId].isShowCrs == "on" && data.NODE_TYPE == routMap.NODE_TYPE.CROSS){
		zIndex = 4;
		imageSize = new kakao.maps.Size(19, 19);
		markerImage = new kakao.maps.MarkerImage("/cm/images/tmap/cross.png", imageSize);
		markerSelImage = new kakao.maps.MarkerImage("/cm/images/tmap/cross_selected.png", imageSize);
	}
	else if(routMap.mapInfo[mapId].isShowNormal == "on" && data.NODE_TYPE == routMap.NODE_TYPE.BUSSTOP && 
			(typeof data.COND_ERROR == "undefined"||data.COND_ERROR == "CE002") ) {
		zIndex = 4;
		imageSize = new kakao.maps.Size(25, 24);
		markerImage = new kakao.maps.MarkerImage("/cm/images/tmap/busstop.png", imageSize);
		markerSelImage = new kakao.maps.MarkerImage("/cm/images/tmap/busstop_selected.png", imageSize);
	}
	else if(typeof data.COND_ERROR != "undefined" && routMap.mapInfo[mapId].isShowAbnormal == "on" &&(data.COND_ERROR == "CE001")) {
		zIndex = 4;
		imageSize = new kakao.maps.Size(25, 24);
		markerImage = new kakao.maps.MarkerImage("/cm/images/tmap/busstop_black.png", imageSize);		
		markerSelImage = new kakao.maps.MarkerImage("/cm/images/tmap/busstop_black.png", imageSize);

	}
	
	var marker = null;
	
	if(idx==focusIdx) {
		zIndex = 4;
		// 마커 이미지를 생성합니다    
		marker = new kakao.maps.Marker({
			position : new kakao.maps.LatLng(data.GPS_Y, data.GPS_X), // Marker의 중심좌표 // 설정.
			//title : data.label, // Marker의 라벨.
			image : markerSelImage,
			draggable : data.draggable,
			zIndex: zIndex
		});
		routMap.mapInfo[mapId].selectedMarker = marker;
	}
	else {
		// 마커 이미지를 생성합니다    
		marker = new kakao.maps.Marker({
			position : new kakao.maps.LatLng(data.GPS_Y, data.GPS_X), // Marker의 중심좌표 // 설정.
			//title : data.label, // Marker의 라벨.
			image : markerImage,
			draggable : data.draggable,
			zIndex: zIndex
		});
	}

	marker.normalImage = markerImage;
	routMap.showBubbleOverlay(mapId, data, marker, idx, focusIdx);
	//routMap.showClickOverlay(mapId, data, idx, focusIdx, marker, markerImage);
	


	
	// 마커에 click 이벤트를 등록합니다
	kakao.maps.event.addListener(marker, 'click', function() {
		//일단 하드코딩 문제 생길시 수정 필요
//		routMap.removeRightClickOverlay("map_MO0101_vehicle");
//		routMap.removeAllOverlay("map_MO0101_sttn");		
		routMap.removeAllOverlay("map_MO0101_crs");		
		
		if (!routMap.mapInfo[mapId].selectedMarker
				|| routMap.mapInfo[mapId].selectedMarker !== marker) {
			
			// 클릭된 마커 객체가 null이 아니면
			// 클릭된 마커의 이미지를 기본 이미지로 변경하고
			!!routMap.mapInfo[mapId].selectedMarker
					&& routMap.mapInfo[mapId].selectedMarker
							.setImage(routMap.mapInfo[mapId].selectedMarker.normalImage);
			
			// 현재 클릭된 마커의 이미지는 클릭 이미지로 변경합니다
			marker.setImage(markerSelImage);
		}
		
		// 클릭된 마커를 현재 클릭된 마커 객체로 설정합니다
		routMap.mapInfo[mapId].selectedMarker = marker;
		marker.setZIndex(3);
		//routMap.mapInfo[mapId].oldSelectedIndex = routMap.mapInfo[mapId].selectedIndex;
		//routMap.mapInfo[mapId].selectedIndex = idx;
		if(grid != null && typeof grid != "undefined") {
			//routMap.focusNode(mapId, grid,idx);
			grid.setFocusedCell(idx,"NODE_ID");
		}
		
		routMap.mapInfo[mapId].onMarkerClick(idx, mapId);
	});
	
	if(markerImage != null)
		marker.setMap(routMap.mapInfo[mapId].map); //Marker가 표시될 Map 설정.
	
	routMap.mapInfo[mapId].markers.push(marker);
}

/**
 *  신호마커표시

 * @param mapId : 대상 map id
 * @param baseData : db 데이터
 * @param socketData : 소켓으로 전송받은 데이터
 */
routMap.showSigMarker = function(mapId, baseData, socketData) {
	var imageSize = new kakao.maps.Size(32, 15); 
	//var imageSize = new kakao.maps.Size(30, 20); 
	var markerImage = null;	
	var Zindex = -1;
	
	if(routMap.mapInfo[mapId].isShowCrs == "on"){
		
		zIndex = 3;
		markerImage = new kakao.maps.MarkerImage("/cm/images/tmap/light_red.png", imageSize);
		if(socketData != null && typeof socketData != "undefined"){
			var phaseNoArr = baseData.PHASE_NO.split(',');
			
			for(i in phaseNoArr) {
				if(socketData.CRS_ID == baseData.CRS_ID && socketData.PHASE_NO == phaseNoArr[i]){
					markerImage = new kakao.maps.MarkerImage("/cm/images/tmap/light_green.png", imageSize);
				}
			}
		}
		
		
		
		//if(data.NODE_TYPE == routMap.NODE_TYPE.VERTEX) {
		//}
	}	
	
	var marker = null;
	marker = new kakao.maps.Marker({
		position : new kakao.maps.LatLng(baseData.GPS_Y, baseData.GPS_X), // Marker의 중심좌표 // 설정.
		image : markerImage,
		zIndex: 3
	});	
	
	if(markerImage != null)
		marker.setMap(routMap.mapInfo[mapId].map); //Marker가 표시될 Map 설정.
	
	routMap.mapInfo[mapId].markers.push(marker)	
}

/**
 *  마커 만 보이기 : 이벤트 없음.. 해당 함수에 click이나 이벤트 처리하면 안됨
 * @param mapId : 대상 map id
 * @param data : 그리드의 data
 * @param idx : 마커 index
 * @param focusIdx : 포커스 index
 * @param grid : 대상 그리드
 */
routMap.showOnlyMarker = function(mapId, data, idx, focusIdx, grid) {
	// 마커 이미지의 이미지 크기 입니다
	var imageSize = new kakao.maps.Size(19, 28); 
	var markerImage = null;
	var markerOverImage = null;
	var markerSelImage = null;

	
	var zIndex= -1;
	if(routMap.mapInfo[mapId].isShowCrs == "on" && data.NODE_TYPE == routMap.NODE_TYPE.CROSS){
		zIndex = 1;
		imageSize = new kakao.maps.Size(19, 19);
		markerImage = new kakao.maps.MarkerImage("/cm/images/tmap/cross.png", imageSize);
		markerSelImage = new kakao.maps.MarkerImage("/cm/images/tmap/cross_selected.png", imageSize);
	}
	else if(routMap.mapInfo[mapId].isShowNormal == "on" && data.NODE_TYPE == routMap.NODE_TYPE.BUSSTOP && 
			(typeof data.COND_ERROR == "undefined"||data.COND_ERROR == "CE002") ) {
		zIndex = 2;
		imageSize = new kakao.maps.Size(25, 24);
		markerImage = new kakao.maps.MarkerImage("/cm/images/tmap/busstop.png", imageSize);
		markerSelImage = new kakao.maps.MarkerImage("/cm/images/tmap/busstop_selected.png", imageSize);
	}
	else if(routMap.mapInfo[mapId].isShowVertex == "on" &&data.NODE_TYPE == routMap.NODE_TYPE.VERTEX){
		imageSize = new kakao.maps.Size(12, 12);
		markerImage = new kakao.maps.MarkerImage("/cm/images/tmap/vertex.png", imageSize);
		markerSelImage = new kakao.maps.MarkerImage("/cm/images/tmap/vertex_selected.png", imageSize);
	}
	else if(routMap.mapInfo[mapId].isShowSignal == "on" &&data.NODE_TYPE == routMap.NODE_TYPE.SIGNAL){
		imageSize = new kakao.maps.Size(12, 12);
		markerImage = new kakao.maps.MarkerImage("/cm/images/tmap/vertex.png", imageSize);
		markerSelImage = new kakao.maps.MarkerImage("/cm/images/tmap/vertex_selected.png", imageSize);
	}	
	else if (routMap.mapInfo[mapId].isShowNode == "on" && data.Node_TYPE == routMap.NODE_TYPE.NORMAL){
		markerImage = new kakao.maps.MarkerImage("/cm/images/tmap/road_trans.png", imageSize);
		markerSelImage = new kakao.maps.MarkerImage("/cm/images/tmap/road_selected.png", imageSize);
	}
	
	else if(typeof data.COND_ERROR != "undefined" && routMap.mapInfo[mapId].isShowAbnormal == "on" &&(data.COND_ERROR == "CE001")) {
		imageSize = new kakao.maps.Size(25, 24);
		markerImage = new kakao.maps.MarkerImage("/cm/images/tmap/busstop_black.png", imageSize);		
		markerSelImage = new kakao.maps.MarkerImage("/cm/images/tmap/busstop_black.png", imageSize);

	}
	
		
	var marker = null;
	
	if(idx==focusIdx) {
		zIndex = 3;
		// 마커 이미지를 생성합니다    
		marker = new kakao.maps.Marker({
			position : new kakao.maps.LatLng(data.GPS_Y, data.GPS_X), // Marker의 중심좌표 // 설정.
			//title : data.label, // Marker의 라벨.
			image : markerSelImage,
			draggable : data.draggable,
			zIndex: zIndex
		});
		routMap.mapInfo[mapId].selectedMarker = marker;
	}
	else {
		// 마커 이미지를 생성합니다    
		marker = new kakao.maps.Marker({
			position : new kakao.maps.LatLng(data.GPS_Y, data.GPS_X), // Marker의 중심좌표 // 설정.
			//title : data.label, // Marker의 라벨.
			image : markerImage,
			draggable : data.draggable,
			zIndex: zIndex
		});
	}

	marker.normalImage = markerImage;


	var infoWindow = null;

	var overlay = null;
	var msg = "";
	if(data.NODE_TYPE == routMap.NODE_TYPE.BUSSTOP){
		 msg = "<div class = 'customoverlay busstop'>";
		 
		 //장치상태 에러일때
		 if(data.COND_ERROR == 'CE001') {
			 msg = "";
			 msg = "<div class = 'customoverlay conderror busstop'>";
		 }
	}
	else if(data.NODE_TYPE == routMap.NODE_TYPE.CROSS){
		msg = "<div class = 'customoverlay cross'>";
		
		 if(data.COND_ERROR == 'CE001') {
			 msg = "";
			 msg = "<div class = 'customoverlay conderror busstop'>";
		 }		
	}
	msg += "<span class = 'map_title' style=''>" + data.NODE_NM + "</span>" + "</div>";

	if(idx==focusIdx) {
		overlay = new kakao.maps.CustomOverlay({
			content: msg,
			position: marker.getPosition(),
			zIndex : zIndex
		});
	}
	else {
		overlay = new kakao.maps.CustomOverlay({
			content: msg,
			position: marker.getPosition(),
			zIndex : 4
		});
		
	}
	
	routMap.mapInfo[mapId].overlay = overlay;
	routMap.mapInfo[mapId].overArr.push(routMap.mapInfo[mapId].overlay);
	
	if(idx==focusIdx) {
		if(markerImage != null){
			//routMap.mapInfo[mapId].overArr[focusIdx].setMap(routMap.mapInfo[mapId].map);
			overlay.setMap(routMap.mapInfo[mapId].map);
		}
	}
	else {
		// 마커에 click 이벤트를 등록합니다
		kakao.maps.event.addListener(marker, 'click',routMap.makeOutListener(routMap.mapInfo[mapId],marker,overlay,markerImage));
		kakao.maps.event.addListener(marker, 'mouseover', routMap.makeOverListener(routMap.mapInfo[mapId].map, marker, overlay));
		kakao.maps.event.addListener(marker, 'mouseout', routMap.makeOutListener(routMap.mapInfo[mapId],marker,overlay,markerImage));
	}
	
	if(markerImage != null)
		marker.setMap(routMap.mapInfo[mapId].map); //Marker가 표시될 Map 설정.
	
	routMap.mapInfo[mapId].markers.push(marker);
}

/**
 *  지도위 팝업 생성
 * @param mapId : 대상 map id
 * @param lat : 위도
 * @param lng : 경도
 * @param msg : 팝업 메시지
 */
routMap.popUp = function(mapId,lat, lng, msg){

	var content =
		"<div class = 'popUp'>" +
		"<span class = 'popUp' style='font-weight:bold;'>" + msg + "</span>" + 
		"<span class = 'popUp' style='font-size: 12px; margin-left:2px; margin-bottom:2px; display:block;'>"+ lat + "," + lng +"</span>"
		+ "</div>";
				

	routMap.mapInfo[mapId].infoWindow = new kakao.maps.InfoWindow({
		position: new kakao.maps.LatLng(lat, lng), //Popup 이 표출될 맵 좌표
		content: content //Popup 표시될 text
	});
	
	//routMap.mapInfo[mapId].infoWindow.setMap(routMap.mapInfo[mapId].map); 
	routMap.mapInfo[mapId].infoArr.push(routMap.mapInfo[mapId].infoWindow);
}

/**
 *  팝업 전체 삭제
 * @param mapId : 대상 map id
 */
routMap.removeAllInfoWindow = function(mapId){
	if(routMap.mapInfo[mapId].infoArr != null){
		for(var i=0; i<routMap.mapInfo[mapId].infoArr.length; i++){
			routMap.mapInfo[mapId].infoArr[i].close();
			routMap.mapInfo[mapId].infoArr[i] = null;
		}
		routMap.mapInfo[mapId].infoArr = [];
	}
}

/**
 *  모든 overlay 삭제
 * @param mapId : 대상 map id
 */
routMap.removeAllOverlay = function(mapId){
	if(routMap.mapInfo[mapId].overArr != null){
		for(var i=0; i<routMap.mapInfo[mapId].overArr.length; i++){
			routMap.mapInfo[mapId].overArr[i].setMap(null);
			routMap.mapInfo[mapId].overArr[i] = null;
		}
		routMap.mapInfo[mapId].overArr = [];
	}
	
	if(routMap.mapInfo[mapId].clickOverArr != null) {
		for(var i=0; i<routMap.mapInfo[mapId].clickOverArr.length; i++){
			routMap.mapInfo[mapId].clickOverArr[i].setMap(null);
			routMap.mapInfo[mapId].clickOverArr[i] = null;
		}
		routMap.mapInfo[mapId].clickOverArr = [];
	} 
}

/**
 *  모든 버스overlay 삭제
 * @param mapId : 대상 map id
 */
routMap.removeAllBusOverlay = function(mapId){
	if(routMap.mapInfo[mapId].busOverArr.length != 0){
		for(var i=0; i<routMap.mapInfo[mapId].busOverArr.length; i++){
			routMap.mapInfo[mapId].busOverArr[i].setMap(null);
			routMap.mapInfo[mapId].busOverArr[i] = null;
		}
		routMap.mapInfo[mapId].busOverArr = [];
	}
	
	if(routMap.mapInfo[mapId].busClickOverArr.length != 0){
		for(var i=0; i<routMap.mapInfo[mapId].busClickOverArr.length; i++){
			routMap.mapInfo[mapId].busClickOverArr[i].setMap(null);
			routMap.mapInfo[mapId].busClickOverArr[i] = null;
		}
		routMap.mapInfo[mapId].busClickOverArr = [];
	}
	
	if(routMap.mapInfo[mapId].dsptchOverArr.length != 0){
		for(var i=0; i<routMap.mapInfo[mapId].dsptchOverArr.length; i++){
			if(routMap.mapInfo[mapId].dsptchOverArr[i] != null) {
				routMap.mapInfo[mapId].dsptchOverArr[i].setMap(null);
				routMap.mapInfo[mapId].dsptchOverArr[i] = null;
			}
		}
		routMap.mapInfo[mapId].dsptchOverArr = [];
	}
}

/**
 *  특정 index의 버스overlay 삭제
 * @param mapId : 대상 map id
 * @param index : 특정 index
 */
routMap.removeIndexBusOverlay = function(mapId,index){
	if(routMap.mapInfo[mapId].busOverArr != null&&routMap.mapInfo[mapId].busOverArr.length!=0&&routMap.mapInfo[mapId].busOverArr[index]!=null){
		routMap.mapInfo[mapId].busOverArr[index].setMap(null);
		routMap.mapInfo[mapId].busOverArr[index] = null;
	}
	
	if(routMap.mapInfo[mapId].busClickOverArr != null&&routMap.mapInfo[mapId].busClickOverArr.length!=0&&routMap.mapInfo[mapId].busClickOverArr[index]!=null){
		routMap.mapInfo[mapId].busClickOverArr[index].setMap(null);
		routMap.mapInfo[mapId].busClickOverArr[index] = null;
	}
}

/**
 *  오른쪽 click overlay 삭제
 * @param mapId : 대상 map id
 */
routMap.removeRightClickOverlay = function(mapId){
	if(routMap.mapInfo[mapId].rightClickOverlay != null) {
		routMap.mapInfo[mapId].rightClickOverlay.setMap(null);
	}
}

/**
 *  마커 추가
 * @param mapId : 대상 map id
 * @param data : 추가할 마커 정보
 */
routMap.addMarker = function(mapId, data) {
	
	var marker = new kakao.maps.Marker({
		position: new kakao.maps.LatLng(data.GPS_Y, data.GPS_X), // Marker의 중심좌표 설정.
		label: data.label, // Marker의 라벨.
		map: routMap.mapInfo[mapId].map,
		icon: data.icon,
		draggable: data.draggable,
	});
	
	marker.setMap(routMap.mapInfo[mapId].map); //Marker가 표시될 Map 설정.
	
	if(data.click) {
		marker.addListener("click", function(e) {
			data.click({
				marker: marker,
				nodeId: data.nodeId,
				index: data.index
			});
		});
	}
	
	routMap.mapInfo[mapId].markers.push(marker);
}

/**
 *  모든 원 삭제
 * @param mapId : 대상 map id
 */
routMap.deleteCircle = function(mapId) {
	if( routMap.mapInfo[mapId].circles != null &&  routMap.mapInfo[mapId].circles.length != 0) {
		for(var i = 0; i <  routMap.mapInfo[mapId].circles.length; i++) {
			if(typeof  routMap.mapInfo[mapId].circles[i].setMap !== "undefined") 
				 routMap.mapInfo[mapId].circles[i].setMap(null);
		}
		 routMap.mapInfo[mapId].circles = [];
	}
}

/**
 *  모든 노드 삭제
 * @param mapId : 대상 map id
 */
routMap.deleteNode = function(mapId) {
	if( routMap.mapInfo[mapId].nodes != null &&  routMap.mapInfo[mapId].nodes.length != 0) {
		for(var i = 0; i < routMap.mapInfo[mapId].nodes.length; i++) {
			if(typeof routMap.mapInfo[mapId].nodes[i].setMap !== "undefined") 
				routMap.mapInfo[mapId].nodes[i].setMap(null);
		}
		routMap.mapInfo[mapId].nodes = [];
	}
}

/**
 *  특정 위치에 원 그리기
 * @param mapId : 대상 map id
 * @param lat : 위도
 * @param lon : 경도
 * @param radius : 반경
 */
routMap.getDrawingCircle = function(mapId, lat, lon, radius) {
	var circle = new kakao.maps.Circle({
		center: new kakao.maps.LatLng(lat, lon),
		radius: radius,
		strokeColor: "#A872EE",
		strokeWeight: 2,
		fillColor: "#A872EE",
		fillOpacity: 0.2
	});
	
	circle.setMap(routMap.mapInfo[mapId].map);
	
	return circle;
}

/**
 *  특정 위치에 노드의 작은 원(반경 4) 그리기
 * @param mapId : 대상 map id
 * @param lat : 위도
 * @param lon : 경도
 */
routMap.getDrawingNode = function(mapId, lat, lon) {
	var node = new kakao.maps.Circle({
		center: new kakao.maps.LatLng(lat, lon),
		radius: 4,
		strokeColor: "#FF005E",
		strokeWeight: 3,
		fillColor: "#FFFFFF",
		fillOpacity: 1
	});
	
	// 지도에 원을 표시합니다 
	node.setMap(routMap.mapInfo[mapId].map);
	
	return node;
}

/**
 *  노선 노드 상의 가장 가까운 index를 찾아 return함
 * @param lat : 위도
 * @param lon : 경도
 * @param routeData : 노선 데이터
 * @param curIndex : 현재 index
 */
routMap.returnInsertRouteInfo = function(lat, lon, routeData, curIndex) {
	var min = 10000000;
	var minIndex = null;
	
	for(var i = 0; i < routeData.length - 1; i++) {
		if(i==curIndex)continue;
		
		var plusVal = 1;
		if((i + 1)==curIndex)plusVal=2;
			
		var result = getDistanceToLine(
			lat,
			lon,
			routeData[i].GPS_Y,
			routeData[i].GPS_X,
			routeData[i+plusVal].GPS_Y,
			routeData[i+plusVal].GPS_X
		)
		
		if(result.distance) {
			if(min > result.distance) {
				min = result.distance;
				minIndex = i;
			}
		}
	}
	
	if(minIndex == null) {
		return false;
	} else {
		var insertIndex = minIndex + 1;
		
		return {
			index: insertIndex
		};
	}
}

/**
 *  클릭으로 노드 경로 추가함
 * @param mapId : 대상 map id
 * @param grid : 대상 그리드
 * @param routeId : 대상 노선ID
 * @param e : 클릭한 event 정보
 */
routMap.addPathByClick = function(mapId,grid,routeId,e){
	
	if(com.getGridViewDataList(grid).length >= routMap.MAX_NODE_CNT){
		//com.alert ("더이상 추가할 수 없습니다.");
		return false;
	}
	
	
	var lonlat = e.latLng;
	
	var idx = grid.getFocusedRowIndex();
	
	if(idx==-1)
		idx = com.getGridViewDataList(grid).insertRow();
	else
		idx = com.getGridViewDataList(grid).insertRow(idx);

	var today = new Date();
	var data = {
	
			ROUT_ID: routeId,
			NODE_SN: idx,
			NODE_NM: /*routNm + */"노드_" + util.getCurrentDate().substring(4),
			NODE_TYPE: routMap.NODE_TYPE.NORMAL,
			GPS_Y: util.getDispGps(lonlat.Ma,7),
			GPS_X: util.getDispGps(lonlat.La,7),
			draggable:routMap.mapInfo[mapId].draggable
			};

	com.getGridViewDataList(grid).setRowJSON(idx, data, true);
	
	//routeData = com.getGridDispJsonData(grid);
	routMap.drawRoute(mapId, grid, idx);
}

/**
 *  클릭으로 버텍스 경로 추가함
 * @param mapId : 대상 map id
 * @param grid : 대상 그리드
 * @param routeId : 대상 노선ID
 * @param e : 클릭한 event 정보
 */
routMap.addVertexByClick = function(mapId,grid,routeId,e){
	
	var routeData = com.getGridDispJsonData(grid);
	if(routeData.length >= routMap.MAX_NODE_CNT){
		//com.alert ("더이상 추가할 수 없습니다.");
		return false;
	}
	
	var lonlat = e.latLng;
	var min = 10000000;
	var minIndex = null;

	for(var i = 0; i < routeData.length - 1; i++) {
		var result = getDistanceToLine(
			lonlat.Ma,
			lonlat.La,
			routeData[i].GPS_Y,
			routeData[i].GPS_X,
			routeData[i + 1].GPS_Y,
			routeData[i + 1].GPS_X
		)
		
		if(result.distance) {
			if(min > result.distance) {
				min = result.distance;
				minIndex = i;
			}
		}
	}
	
	if(minIndex == null) {
		com.alert("선택할 수 없는 좌표입니다. 경로를 먼저 입력하세요");
	} else {
		var idx = minIndex + 1;;
		
		idx = com.getGridViewDataList(grid).insertRow(idx);
	
		var today = new Date();
		var data = {
		
				ROUT_ID: routeId,
				NODE_SN: idx,
				NODE_NM: /*routNm + */"버텍스_" + util.getCurrentDate().substring(4),
				NODE_TYPE: routMap.NODE_TYPE.VERTEX,
				GPS_Y: util.getDispGps(lonlat.Ma,7),
				GPS_X: util.getDispGps(lonlat.La,7),
				draggable:routMap.mapInfo[mapId].draggable
				};
	
		com.getGridViewDataList(grid).setRowJSON(idx, data, true);
		
		//routeData = com.getGridDispJsonData(grid);
		routMap.drawRoute(mapId, grid, idx);
		grid.setFocusedCell(idx,"NODE_ID");
	}
}

/**
 *  클릭으로 화면용 버텍스 경로 추가함
 * @param mapId : 대상 map id
 * @param grid : 대상 그리드
 * @param routeId : 대상 노선ID
 * @param e : 클릭한 event 정보
 */
routMap.addDispVertexByClick = function(mapId,grid,routeId,e){
	
	var routeData = com.getGridDispJsonData(grid);
	if(routeData.length >= routMap.MAX_NODE_CNT){
		//com.alert ("더이상 추가할 수 없습니다.");
		return false;
	}
	
	var lonlat = e.latLng;
	var min = 10000000;
	var minIndex = null;
	var nodeSn = 0;
	for(var i = 0; i < routeData.length - 1; i++) {
		var result = getDistanceToLine(
			lonlat.Ma,
			lonlat.La,
			routeData[i].GPS_Y,
			routeData[i].GPS_X,
			routeData[i + 1].GPS_Y,
			routeData[i + 1].GPS_X
		)
		
		if(result.distance) {
			if(min > result.distance) {
				nodeSn = routeData[i].NODE_SN;
				min = result.distance;
				minIndex = i;
			}
		}
	}
	
	if(minIndex == null) {
		com.alert("선택할 수 없는 좌표입니다. 경로를 먼저 입력하세요");
	} else {
		var idx = minIndex + 1;;
		
		idx = com.getGridViewDataList(grid).insertRow(idx);
	
		var today = new Date();
		var data = {};
		if(idx>0){
			data = {
				ROUT_ID: routeId,
				NODE_SN: nodeSn,
				NODE_NM: /*routNm + */"DP_" + util.getCurrentDate().substring(4),
				NODE_TYPE: routMap.NODE_TYPE.DISP,
				GPS_Y: util.getDispGps(lonlat.Ma,7),
				GPS_X: util.getDispGps(lonlat.La,7),
				LINK_ID:  routeData[idx-1].LINK_ID,
				ST_GPS_X: routeData[idx-1].ST_GPS_X,
				ST_GPS_Y: routeData[idx-1].ST_GPS_Y,
				ED_GPS_X: routeData[idx-1].ED_GPS_X,
				ED_GPS_Y: routeData[idx-1].ED_GPS_Y,
				draggable:routMap.mapInfo[mapId].draggable
			};
		}
		else {
			data = {
				ROUT_ID: routeId,
				NODE_SN: nodeSn,
				NODE_NM: /*routNm + */"DP_" + util.getCurrentDate().substring(4),
				NODE_TYPE: routMap.NODE_TYPE.DISP,
				GPS_Y: util.getDispGps(lonlat.Ma,7),
				GPS_X: util.getDispGps(lonlat.La,7),
				draggable:routMap.mapInfo[mapId].draggable
			};
		}
		com.getGridViewDataList(grid).setRowJSON(idx, data, true);
		
		var j = 0;
		for(var i = 0; i < com.getGridViewDataList(grid).getRowCount(); i++){
			if(nodeSn == com.getGridViewDataList(grid).getCellData(i, "NODE_SN")){
				
				if(j==0){
					com.getGridViewDataList(grid).modifyRowStatus(i, "C");
				}
				else {
					com.getGridViewDataList(grid).setCellData(i, "NODE_CHILD_SN",j);
				}
				j++;
			}
		}
		
		//routeData = com.getGridDispJsonData(grid);
		routMap.drawRoute(mapId, grid, idx);
		grid.setFocusedCell(idx,"NODE_ID");
	}
}

/**
 *  클릭으로 정류소 경로 추가함
 * @param mapId : 대상 map id
 * @param grid : 대상 그리드
 * @param routeId : 대상 노선ID
 * @param e : 클릭한 event 정보
 */
routMap.addSttnByClick = function(mapId,grid,routeId,e){
	
	var idx = -1;
	var routeData = com.getGridDispJsonData(grid);
	if(routeData.length >= routMap.MAX_NODE_CNT){
		//com.alert ("더이상 추가할 수 없습니다.");
		return idx;
	}
	
	var lonlat = e.latLng;
	var min = 10000000;
	var minIndex = null;

	for(var i = 0; i < routeData.length - 1; i++) {
		var result = getDistanceToLine(
			lonlat.Ma,
			lonlat.La,
			routeData[i].GPS_Y,
			routeData[i].GPS_X,
			routeData[i + 1].GPS_Y,
			routeData[i + 1].GPS_X
		)
		
		if(result.distance) {
			if(min > result.distance) {
				min = result.distance;
				minIndex = i;
			}
		}
	}
	
	if(minIndex == null) {
		com.alert("선택할 수 없는 좌표입니다. 경로를 먼저 입력하세요");
	} else {
		idx = minIndex + 1;;
		
		idx = com.getGridViewDataList(grid).insertRow(idx);
	
		var today = new Date();
		var data = {
		
				ROUT_ID: routeId,
				NODE_SN: idx,
				NODE_NM: /*routNm + */"정류소_" + util.getCurrentDate().substring(4),
				NODE_TYPE: routMap.NODE_TYPE.BUSSTOP,
				GPS_Y: util.getDispGps(lonlat.Ma,7),
				GPS_X: util.getDispGps(lonlat.La,7),
				draggable:routMap.mapInfo[mapId].draggable
				};
	
		com.getGridViewDataList(grid).setRowJSON(idx, data, true);
		grid.setFocusedCell(idx,"NODE_ID");
		//routeData = com.getGridDispJsonData(grid);
		//routMap.drawRoute(mapId, grid, idx);
	}
	return idx;
}

/**
 *  클릭으로 교차로 경로 추가함
 * @param mapId : 대상 map id
 * @param grid : 대상 그리드
 * @param routeId : 대상 노선ID
 * @param e : 클릭한 event 정보
 */
routMap.addCrossByClick = function(mapId,grid,routeId,e){
	var idx = -1;
	var routeData = com.getGridDispJsonData(grid);
	if(routeData.length >= routMap.MAX_NODE_CNT){
		//com.alert ("더이상 추가할 수 없습니다.");
		return idx;
	}
	
	var lonlat = e.latLng;
	var min = 10000000;
	var minIndex = null;

	for(var i = 0; i < routeData.length - 1; i++) {
		var result = getDistanceToLine(
			lonlat.Ma,
			lonlat.La,
			routeData[i].GPS_Y,
			routeData[i].GPS_X,
			routeData[i + 1].GPS_Y,
			routeData[i + 1].GPS_X
		)
		
		if(result.distance) {
			if(min > result.distance) {
				min = result.distance;
				minIndex = i;
			}
		}
	}
	
	if(minIndex == null) {
		com.alert("선택할 수 없는 좌표입니다. 경로를 먼저 입력하세요");
	} else {
		idx = minIndex + 1;;
		
		idx = com.getGridViewDataList(grid).insertRow(idx);
	
		var today = new Date();
		var data = {
		
				ROUT_ID: routeId,
				NODE_SN: idx,
				NODE_NM: /*routNm + */"교차로_" + util.getCurrentDate().substring(4),
				NODE_TYPE: routMap.NODE_TYPE.CROSS,
				GPS_Y: util.getDispGps(lonlat.Ma,7),
				GPS_X: util.getDispGps(lonlat.La,7),
				draggable:routMap.mapInfo[mapId].draggable
				};
	
		com.getGridViewDataList(grid).setRowJSON(idx, data, true);
		grid.setFocusedCell(idx,"NODE_ID");
		//routeData = com.getGridDispJsonData(grid);
		//routMap.drawRoute(mapId, grid, idx);
	}
	return idx;
}


/**
 *  클릭으로 사운도 위치 추가함
 * @param mapId : 대상 map id
 * @param grid : 대상 그리드
 * @param routeId : 대상 노선ID
 * @param e : 클릭한 event 정보
 */
routMap.addSoundByClick = function(mapId,grid,routeId,e){
	var idx = -1;
	var routeData = com.getGridDispJsonData(grid);
	if(routeData.length >= routMap.MAX_NODE_CNT){
		//com.alert ("더이상 추가할 수 없습니다.");
		return idx;
	}
	
	var lonlat = e.latLng;
	var min = 10000000;
	var minIndex = null;

	for(var i = 0; i < routeData.length - 1; i++) {
		var result = getDistanceToLine(
			lonlat.Ma,
			lonlat.La,
			routeData[i].GPS_Y,
			routeData[i].GPS_X,
			routeData[i + 1].GPS_Y,
			routeData[i + 1].GPS_X
		)
		
		if(result.distance) {
			if(min > result.distance) {
				min = result.distance;
				minIndex = i;
			}
		}
	}
	
	if(minIndex == null) {
		com.alert("선택할 수 없는 좌표입니다. 경로를 먼저 입력하세요");
	} else {
		idx = minIndex + 1;;
		
		idx = com.getGridViewDataList(grid).insertRow(idx);
	
		var today = new Date();
		var data = {
		
				ROUT_ID: routeId,
				NODE_SN: idx,
				NODE_NM: /*routNm + */"사운드_" + util.getCurrentDate().substring(4),
				NODE_TYPE: routMap.NODE_TYPE.SOUND,
				GPS_Y: util.getDispGps(lonlat.Ma,7),
				GPS_X: util.getDispGps(lonlat.La,7),
				draggable:routMap.mapInfo[mapId].draggable
				};
	
		com.getGridViewDataList(grid).setRowJSON(idx, data, true);
		
		//routeData = com.getGridDispJsonData(grid);
		//routMap.drawRoute(mapId, grid, idx);
	}
	return idx;
}

/**
 *  클릭으로 교차로 위치 추가함
 * @param mapId : 대상 map id
 * @param grid : 대상 그리드
 * @param routeId : 대상 노선ID
 * @param e : 클릭한 event 정보
 */
routMap.addGrgByClick = function(mapId,grid,routeId,e){
	var idx = -1;
	var routeData = com.getGridDispJsonData(grid);
	if(routeData.length >= routMap.MAX_NODE_CNT){
		//com.alert ("더이상 추가할 수 없습니다.");
		return idx;
	}
	
	var lonlat = e.latLng;
	var min = 10000000;
/*	var minIndex = null;

	for(var i = 0; i < routeData.length - 1; i++) {
		var result = getDistanceToLine(
			lonlat.Ma,
			lonlat.La,
			routeData[i].GPS_Y,
			routeData[i].GPS_X,
			routeData[i + 1].GPS_Y,
			routeData[i + 1].GPS_X
		)
		
		if(result.distance) {
			if(min > result.distance) {
				min = result.distance;
				minIndex = i;
			}
		}
	}
	
	if(minIndex == null) {
		com.alert("선택할 수 없는 좌표입니다. 경로를 먼저 입력하세요");
	} */
//	else {
	//	idx = minIndex + 1;
		idx = grid.getFocusedRowIndex();
		idx = com.getGridViewDataList(grid).insertRow(idx);
		var today = new Date();
		var data = {
		
				ROUT_ID: routeId,
				NODE_SN: idx,
				NODE_NM: /*routNm + */"차고지_" + util.getCurrentDate().substring(4),
				NODE_TYPE: routMap.NODE_TYPE.GARAGE,
				GPS_Y: util.getDispGps(lonlat.Ma,7),
				GPS_X: util.getDispGps(lonlat.La,7),
				draggable:routMap.mapInfo[mapId].draggable
				};
	
		com.getGridViewDataList(grid).setRowJSON(idx, data, true);
		grid.setFocusedCell(idx,"NODE_ID");
		//routeData = com.getGridDispJsonData(grid);
		//routMap.drawRoute(mapId, grid, idx);
//	}
	return idx;
}

/**
 *  노드 포커스
 * @param mapId : 대상 map id
 * @param grid : 대상 그리드
 * @param focusIdx : 포커스 index
 */
routMap.focusNode = function(mapId, grid,focusIdx){

	focusIdx = com.getGridDispIndex(grid,focusIdx);
	if(mapId == "map_AL0101" || mapId == "map_FM0201"){
		routMap.showNode(mapId, com.getGridDispJsonData(grid), focusIdx);
	}
	else {
		//if(routMap.mapInfo[mapId].selectedIndex == focusIdx) return;
		routMap.mapInfo[mapId].oldSelectedIndex = routMap.mapInfo[mapId].selectedIndex;
		routMap.mapInfo[mapId].selectedIndex = focusIdx;
		
		/*if(routMap.mapInfo[mapId].isFirst){
			routMap.mapInfo[mapId].isFirst = false;
		}
		else {
			routMap.mapInfo[mapId].map.setLevel(0);
		}*/
		if(routMap.mapInfo[mapId].isSound){
			routMap.drawSound(mapId, grid, focusIdx);
		}
		else {
			routMap.drawRoute(mapId, grid, focusIdx);
		}
	}
	routMap.mapInfo[mapId].isMove = true;
}

/**
 * 지도에 데이터리스트의 모든 노드 추가
 * @param mapId : 대상 map id
 * @param grid : 대상 그리드
 * @param dataList : grid의 데이터리스트
 * @param routeId : 노선id
 * @param area : 권역
 */
routMap.insertNodeAll = function(mapId, grid,dataList,routeId,area){
	
	for (i = 0; i < dataList.length; i++) {
		routMap.addNode(mapId, grid,dataList[i],routeId,area);
	}
	routMap.drawRoute(mapId, grid, -1);
}

/**
 * 지도에 데이터리스트의 모든 표준 노드 추가
 * @param mapId : 대상 map id
 * @param grid : 대상 그리드
 * @param dataList : grid의 데이터리스트
 * @param routeId : 노선id
 * @param area : 권역
 */
routMap.insertStdNodeAll = function(mapId, grid,dataList,routeId,area){
	
	for (i = 0; i < dataList.length; i++) {
		routMap.addStdNode(mapId, grid,dataList[i],routeId,area);
	}
	routMap.drawRoute(mapId, grid, -1);
}

/**
 * 노드 추가
 * @param mapId : 대상 map id
 * @param grid : 대상 그리드
 * @param data : 추가할 데이터
 * @param routeId : 노선id
 * @param area : 권역
 */
routMap.addNode = function(mapId, grid, data, routeId,area) {
	var routeData = com.getGridDispJsonData(grid);
	if(com.getGridViewDataList(grid).length >= routMap.MAX_NODE_CNT){
		//com.alert("더이상 추가할 수 없습니다.");
		return false;
	}
	
	var min = 10000000;
	var minIndex = null;
	if(routeData==null||routeData.length==0||routeData.length==1){
		var temp = {
				ROUT_ID: routeId,
				NODE_ID: data.NODE_ID,
				GPS_Y: data.GPS_Y,
				GPS_X: data.GPS_X,
				NODE_NM: data.NODE_NM,
				NODE_TYPE: data.NODE_TYPE,
				STTN_NO: data.STTN_NO,
				STTN_ID: data.STTN_ID,
				AREA: area
			};
		
		var idx = com.getGridViewDataList(grid).insertRow();
		com.getGridViewDataList(grid).setRowJSON(idx, temp, true);
		routeData = com.getGridDispJsonData(grid);
	}
	else {
		for(var i = 0; i < routeData.length - 1; i++) {
			var result = getDistanceToLine(
				data.GPS_Y,
				data.GPS_X,
				routeData[i].GPS_Y,
				routeData[i].GPS_X,
				routeData[i + 1].GPS_Y,
				routeData[i + 1].GPS_X
			)
			if(result==false){
				var temp = {
						ROUT_ID: routeId,
						NODE_ID: data.NODE_ID,
						GPS_Y: data.GPS_Y,
						GPS_X: data.GPS_X,
						NODE_NM: data.NODE_NM,
						NODE_TYPE: data.NODE_TYPE,
						STTN_NO: data.STTN_NO,
						STTN_ID: data.STTN_ID,
						AREA: area
					};
				
				var idx = com.getGridViewDataList(grid).insertRow();
				com.getGridViewDataList(grid).setRowJSON(idx, temp, true);
				//routeData.splice(idx, 0, temp);
				return;
			}
			if(result.distance) {
				if(min > result.distance) {
					min = result.distance;
					minIndex = i;
				}
			}
		}
		
		if(minIndex == null) {
			//alert.alert("선택할 수 없는 좌표입니다. 경로를 먼저 입력하세요");
		} else {
			//isNewData = true;
			//var seq = 0;
			//seq = routeData[minIndex].seq + (routeData[minIndex + 1].seq - routeData[minIndex].seq) / 2;				
			var insertIndex = minIndex + 1;
			
			var temp = {
					ROUT_ID: routeId,
					NODE_ID: data.NODE_ID,
					GPS_Y: data.GPS_Y,
					GPS_X: data.GPS_X,
					NODE_NM: data.NODE_NM,
					NODE_TYPE: data.NODE_TYPE,
					STTN_NO: data.STTN_NO,
					STTN_ID: data.STTN_ID,
					AREA: area
				};
			
			//routeData.splice(insertIndex, 0, temp);
			
			var idx = com.getGridViewDataList(grid).insertRow(insertIndex);
			com.getGridViewDataList(grid).setRowJSON(idx, temp, true);
			
		}
	}
	
}

/**
 * 표준노드 추가
 * @param mapId : 대상 map id
 * @param grid : 대상 그리드
 * @param data : 추가할 데이터
 * @param routeId : 노선id
 * @param area : 권역
 */
routMap.addStdNode = function(mapId, grid, data, routeId,area) {
	var routeData = com.getGridDispJsonData(grid);
	if(com.getGridViewDataList(grid).length >= routMap.MAX_NODE_CNT){
		//com.alert("더이상 추가할 수 없습니다.");
		return false;
	}
	
	var min = 10000000;
	var minIndex = null;
	if(routeData==null||routeData.length==0||routeData.length==1){
		var temp = {
				ROUT_ID: routeId,
				MOCK_NODE_ID: data.MOCK_NODE_ID,
				MOCK_LINK_ID: data.MOCK_LINK_ID,
				GPS_Y: data.GPS_Y,
				GPS_X: data.GPS_X,
				NODE_NM: data.NODE_NM,
				NODE_TYPE: data.NODE_TYPE,
				AREA: area
			};
		
		var idx = com.getGridViewDataList(grid).insertRow();
		com.getGridViewDataList(grid).setRowJSON(idx, temp, true);
		routeData = com.getGridDispJsonData(grid);
	}
	else {
		for(var i = 0; i < routeData.length - 1; i++) {
			var result = getDistanceToLine(
				data.GPS_Y,
				data.GPS_X,
				routeData[i].GPS_Y,
				routeData[i].GPS_X,
				routeData[i + 1].GPS_Y,
				routeData[i + 1].GPS_X
			)
			if(result==false){
				var temp = {
						ROUT_ID: routeId,
						MOCK_NODE_ID: data.MOCK_NODE_ID,
						MOCK_LINK_ID: data.MOCK_LINK_ID,
						GPS_Y: data.GPS_Y,
						GPS_X: data.GPS_X,
						NODE_NM: data.NODE_NM,
						NODE_TYPE: data.NODE_TYPE,
						AREA: area
					};
				
				var idx = com.getGridViewDataList(grid).insertRow();
				com.getGridViewDataList(grid).setRowJSON(idx, temp, true);
				//routeData.splice(idx, 0, temp);
				return;
			}
			if(result.distance) {
				if(min > result.distance) {
					min = result.distance;
					minIndex = i;
				}
			}
		}
		
		if(minIndex == null) {
			//alert.alert("선택할 수 없는 좌표입니다. 경로를 먼저 입력하세요");
		} else {
			//isNewData = true;
			//var seq = 0;
			//seq = routeData[minIndex].seq + (routeData[minIndex + 1].seq - routeData[minIndex].seq) / 2;				
			var insertIndex = minIndex + 1;
			
			var temp = {
					ROUT_ID: routeId,
					MOCK_NODE_ID: data.MOCK_NODE_ID,
					MOCK_LINK_ID: data.MOCK_LINK_ID,
					GPS_Y: data.GPS_Y,
					GPS_X: data.GPS_X,
					NODE_NM: data.NODE_NM,
					NODE_TYPE: data.NODE_TYPE,
					AREA: area
				};
			
			//routeData.splice(insertIndex, 0, temp);
			
			var idx = com.getGridViewDataList(grid).insertRow(insertIndex);
			com.getGridViewDataList(grid).setRowJSON(idx, temp, true);
			
		}
	}
	
}

/**
 * 노선에서 노드 이동
 * @param mapId : 대상 map id
 * @param grid : 대상 그리드
 * @param e : 클릭한 event 정보
 */
routMap.moveRoute = function(mapId, grid, e){
	var routeData = com.getGridDispJsonData(grid);
	var point = e.marker.getPosition();
	
	var val = false;
	
	if(e.index == 0 || e.index == routeData.length-1){
		val = true;
	}
	else {
		val = routMap.returnInsertRouteInfo(point.Ma, point.La,routeData,e.index);
	}
	
	if(val) {
		var data = com.getGridViewDataList(grid);
		data.setCellData(e.index, "GPS_Y", util.getDispGps(point.Ma,7));
		data.setCellData(e.index, "GPS_X", util.getDispGps(point.La,7));
		
		/*var nodeChildSn = data.getCellData(e.index, "NODE_CHILD_SN");;
		if(com.isEmpty(nodeChildSn)==false && nodeChildSn==-1){
			data.setCellData(e.index, "NODE_CHILD_SN", 0);
		}*/
		
		if(routMap.mapInfo[mapId].isSound){
			routMap.drawSound(mapId, grid, e.index);
		}
		else {
			routMap.drawRoute(mapId, grid, e.index);
		}

	} else {
		routMap.drawRoute(mapId, grid, e.index);
	}
}
/*routMap.moveRoute = function(mapId, grid, e){
	var routeData = com.getGridDispJsonData(grid);
	var point = e.marker.getPosition();
	var node = $.extend(true, {}, routeData[e.index]);
	//routeData.splice(e.index, 1);
	
	var val = false;
	
	if(e.index == 0 || e.index == routeData.length-1){
		val = true;
	}
	else {
		val = routMap.returnInsertRouteInfo(point.Ma, point.La,routeData,e.index);
	}
	
	if(val) {
		var temp = {
			NODE_ID: e.nodeId,
			GPS_Y: util.getDispGps(point.Ma,7),
			GPS_X: util.getDispGps(point.La,7),
		};
		
		temp = $.extend(true, node, temp);
		
		//routeData.splice(val.index, 0, temp);
		
		var data = com.getGridViewDataList(grid);
		
		data.removeRow(e.index);
		
		if(com.isEmpty(temp.NODE_CHILD_SN)==false && temp.NODE_CHILD_SN==-1){
			temp.NODE_CHILD_SN = 0;
		}
		
		var idx = 0;
		if(val==true){
			idx = data.insertRow(e.index);
			data.setRowJSON(idx, temp, true);
		}
		else {
			idx = data.insertRow(val.index);
			data.setRowJSON(idx, temp, true);
		}
		
		if(routMap.mapInfo[mapId].isSound){
			routMap.drawSound(mapId, grid, idx);
		}
		else {
			routMap.drawRoute(mapId, grid, idx);
		}
		//routeData.sort(function (a,b){ return a.seq - b.seq });
		//fnObj.gridView1.setData(routeData);
		
		//ACTIONS.dispatch(ACTIONS.DRAW_ROUTE);
	} else {
		//com.alert("선택할 수 없는 좌표입니다.");
		
		routMap.drawRoute(mapId, grid, e.index);
	}
}*/

/**
 * 그리드 정보로 노선에서 노드 그리기
 * @param mapId : 대상 map id
 * @param grid : 대상 그리드
 * @param focusIdx : 포커서 index
 */
routMap.drawRoute = function(mapId, grid, focusIdx) {
	
	var list = com.getGridDispJsonData(grid);

	if(routMap.mapInfo[mapId].linkMode){
		if(list.length>0){
			var data = list[list.length-1];
			var temp = {
				NODE_ID: data.ED_NODE_ID,
				GPS_Y: data.ED_GPS_Y,
				GPS_X: data.ED_GPS_X,
				NODE_NM: data.ED_NODE_NM,
				NODE_TYPE: data.ED_NODE_TYPE
			};
			list.push(temp);
		}
	}
	routMap.initDisplay(mapId);
	
	if(list != null && list.length != 0) {
		var oldMornStd = "";
		for(var i = 0; i < list.length; i++) {
			
			/**드래그이벤트**/
			if(routMap.mapInfo[mapId].draggable){
				list[i].click = function(e) {
					routMap.mapInfo[mapId].dragging = false;
					routMap.moveRoute(mapId,grid,e);
				};
			}
			
			list[i].index = i;
			
			/**드래그이벤트**/
			list[i].draggable = routMap.mapInfo[mapId].draggable;
			
			/*if(list[i].NODE_TYPE != routMap.NODE_TYPE.VERTEX){
				routMap.mapInfo[mapId].nodes.push(routMap.getDrawingNode(mapId,list[i].GPS_Y, list[i].GPS_X));
			}*/
			
			// 노드 타입이 버스 정류소 마커 표시
			
			var isOverLayHidden = routMap.mapInfo[mapId].isOverLayHidden;
			if(list[i].NODE_TYPE == routMap.NODE_TYPE.BUSSTOP && routMap.mapInfo[mapId].dispCheck.indexOf(routMap.NODE_TYPE.BUSSTOP)>=0) {
				routMap.addMarkerInter(mapId, list[i], grid, i, focusIdx, isOverLayHidden);
			}
			else if(list[i].NODE_TYPE == routMap.NODE_TYPE.CROSS && routMap.mapInfo[mapId].dispCheck.indexOf(routMap.NODE_TYPE.CROSS)>=0) {
				routMap.addMarkerInter(mapId, list[i], grid, i, focusIdx, isOverLayHidden);
			}
			else if(list[i].NODE_TYPE == routMap.NODE_TYPE.VERTEX && routMap.mapInfo[mapId].dispCheck.indexOf(routMap.NODE_TYPE.VERTEX)>=0) {
				routMap.addMarkerInter(mapId, list[i], grid, i, focusIdx, isOverLayHidden);
			}
			else if(list[i].NODE_TYPE == routMap.NODE_TYPE.DISP && routMap.mapInfo[mapId].dispCheck.indexOf(routMap.NODE_TYPE.DISP)>=0) {
				routMap.addMarkerInter(mapId, list[i], grid, i, focusIdx, isOverLayHidden);
			}
			else if(list[i].NODE_TYPE == routMap.NODE_TYPE.SOUND /*&& routMap.mapInfo[mapId].dispCheck.indexOf(routMap.NODE_TYPE.SOUND)>=0*/) {
				routMap.addMarkerInter(mapId, list[i], grid, i, focusIdx, isOverLayHidden);
			}			
			else if(list[i].NODE_TYPE == routMap.NODE_TYPE.SIGNAL && routMap.mapInfo[mapId].dispCheck.indexOf(routMap.NODE_TYPE.SIGNAL)>=0) {
				routMap.addMarkerInter(mapId, list[i], grid, i, focusIdx, isOverLayHidden);
			}
			// 아닐 경우(일반 노드) 네모 박스 표시
			else if(list[i].NODE_TYPE == routMap.NODE_TYPE.NORMAL && routMap.mapInfo[mapId].dispCheck.indexOf(routMap.NODE_TYPE.NORMAL)>=0) {
				routMap.addMarkerInter(mapId, list[i], grid, i, focusIdx, isOverLayHidden);
			}
			
			if(i < list.length -1){
				var mornStd = list[i].MORN_STD;
				if(com.isEmpty(mornStd)){
					mornStd = oldMornStd;
				}
				else {
					oldMornStd = list[i].MORN_STD;
				}
				
				var color = "#3396ff";
				if(mornStd=='MS002'){
					color = "#cd6c15";
				}
				else if(mornStd=='MS003'){
					color = "#FF005E";
				}

				routMap.drawLine(mapId, list[i], list[i+1], color);
			}
		}
		routMap.mapInfo[mapId].dragging = false;

		
		if(list.length>0){
			if(focusIdx!=-1){
				routMap.moveMap(mapId, list[focusIdx].GPS_Y, list[focusIdx].GPS_X);
			}
			else {
				routMap.moveMap(mapId, list[parseInt(list.length/2)].GPS_Y, list[parseInt(list.length/2)].GPS_X);
			}
		}
	}
}

//교차로 제외 라인 표기
routMap.drawRouteExceptCross = function(mapId, grid, focusIdx) {
 
	var list = com.getGridDispJsonData(grid);

	if(routMap.mapInfo[mapId].linkMode){
		if(list.length>0){
			var data = list[list.length-1];
			var temp = {
				NODE_ID: data.ED_NODE_ID,
				GPS_Y: data.ED_GPS_Y,
				GPS_X: data.ED_GPS_X,
				NODE_NM: data.ED_NODE_NM,
				NODE_TYPE: data.ED_NODE_TYPE
			};
			list.push(temp);
		}
	}
	routMap.initDisplay(mapId);
	
	if(list != null && list.length != 0) {
		var oldMornStd = "";
		for(var i = 0; i < list.length; i++) {
			
			/**드래그이벤트**/
			if(routMap.mapInfo[mapId].draggable){
				list[i].click = function(e) {
					routMap.mapInfo[mapId].dragging = false;
					routMap.moveRoute(mapId,grid,e);
				};
			}
			
			list[i].index = i;
			
			/**드래그이벤트**/
			list[i].draggable = routMap.mapInfo[mapId].draggable;
			
			/*if(list[i].NODE_TYPE != routMap.NODE_TYPE.VERTEX){
				routMap.mapInfo[mapId].nodes.push(routMap.getDrawingNode(mapId,list[i].GPS_Y, list[i].GPS_X));
			}*/
			
			// 노드 타입이 버스 정류소 마커 표시
			
			var isOverLayHidden = routMap.mapInfo[mapId].isOverLayHidden;
			if(list[i].NODE_TYPE == routMap.NODE_TYPE.BUSSTOP && routMap.mapInfo[mapId].dispCheck.indexOf(routMap.NODE_TYPE.BUSSTOP)>=0) {
				routMap.addMarkerInter(mapId, list[i], grid, i, focusIdx, isOverLayHidden);
			}
			else if(list[i].NODE_TYPE == routMap.NODE_TYPE.CROSS && routMap.mapInfo[mapId].dispCheck.indexOf(routMap.NODE_TYPE.CROSS)>=0) {
				routMap.addMarkerInter(mapId, list[i], grid, i, focusIdx, isOverLayHidden);
			}
			else if(list[i].NODE_TYPE == routMap.NODE_TYPE.VERTEX && routMap.mapInfo[mapId].dispCheck.indexOf(routMap.NODE_TYPE.VERTEX)>=0) {
				routMap.addMarkerInter(mapId, list[i], grid, i, focusIdx, isOverLayHidden);
			}
			else if(list[i].NODE_TYPE == routMap.NODE_TYPE.DISP && routMap.mapInfo[mapId].dispCheck.indexOf(routMap.NODE_TYPE.DISP)>=0) {
				routMap.addMarkerInter(mapId, list[i], grid, i, focusIdx, isOverLayHidden);
			}
			else if(list[i].NODE_TYPE == routMap.NODE_TYPE.SOUND /*&& routMap.mapInfo[mapId].dispCheck.indexOf(routMap.NODE_TYPE.SOUND)>=0*/) {
				routMap.addMarkerInter(mapId, list[i], grid, i, focusIdx, isOverLayHidden);
			}			
			else if(list[i].NODE_TYPE == routMap.NODE_TYPE.SIGNAL && routMap.mapInfo[mapId].dispCheck.indexOf(routMap.NODE_TYPE.SIGNAL)>=0) {
				routMap.addMarkerInter(mapId, list[i], grid, i, focusIdx, isOverLayHidden);
			}
			// 아닐 경우(일반 노드) 네모 박스 표시
			else if(list[i].NODE_TYPE == routMap.NODE_TYPE.NORMAL && routMap.mapInfo[mapId].dispCheck.indexOf(routMap.NODE_TYPE.NORMAL)>=0) {
				routMap.addMarkerInter(mapId, list[i], grid, i, focusIdx, isOverLayHidden);
			}
			
			if(i < list.length -1){
				var mornStd = list[i].MORN_STD;
				if(com.isEmpty(mornStd)){
					mornStd = oldMornStd;
				}
				else {
					oldMornStd = list[i].MORN_STD;
				}
				
				var color = "#3396ff";
				if(mornStd=='MS002'){
					color = "#cd6c15";
				}
				else if(mornStd=='MS003'){
					color = "#FF005E";
				}
				if(list[i].NODE_TYPE != routMap.NODE_TYPE.CROSS&&list[i+1].NODE_TYPE != routMap.NODE_TYPE.CROSS){
					routMap.drawLine(mapId, list[i], list[i+1], color);
				}
				else if(list[i].NODE_TYPE == routMap.NODE_TYPE.CROSS){
					if(list[i+1].NODE_TYPE == routMap.NODE_TYPE.CROSS){
						routMap.drawLine(mapId, list[i-1], list[i+2], color);
					}
					else routMap.drawLine(mapId, list[i-1], list[i+1], color);
				}
				/*else if(list[i+1].NODE_TYPE == routMap.NODE_TYPE.CROSS){
					routMap.drawLine(mapId, list[i], list[i+2], color);
				}*/
			}
		}
		routMap.mapInfo[mapId].dragging = false;

		
		if(list.length>0){
			if(focusIdx!=-1){
				routMap.moveMap(mapId, list[focusIdx].GPS_Y, list[focusIdx].GPS_X);
			}
			else {
				routMap.moveMap(mapId, list[parseInt(list.length/2)].GPS_Y, list[parseInt(list.length/2)].GPS_X);
			}
		}
	}
}

/**
 * 데이터 리스트로 노선에서 노드 그리기
 * @param mapId : 대상 map id
 * @param list : 대상 데이터 리스트
 * @param focusIdx : 포커서 index
 */
routMap.drawRoute2 = function(mapId, list, focusIdx) {
	if(routMap.mapInfo[mapId].linkMode){
		if(list.length>0){
			var data = list[list.length-1];
			var temp = {
					NODE_ID: data.ED_NODE_ID,
					GPS_Y: data.ED_GPS_Y,
					GPS_X: data.ED_GPS_X,
					NODE_NM: data.ED_NODE_NM,
					NODE_TYPE: data.ED_NODE_TYPE
			};
			list.push(temp);	
		}
	}
	routMap.initDisplay(mapId);
	
	if(list != null && list.length != 0) {
		var oldMornStd = "";
		for(var i = 0; i < list.length; i++) {
			
			/**드래그이벤트**/
			if(routMap.mapInfo[mapId].draggable){
				list[i].click = function(e) {
					routMap.mapInfo[mapId].dragging = false;
					routMap.moveRoute(mapId,grid,e);
				};
			}
			
			list[i].index = i;

			/**드래그이벤트**/
			list[i].draggable = routMap.mapInfo[mapId].draggable;
			
			/*if(list[i].NODE_TYPE != routMap.NODE_TYPE.VERTEX){
				routMap.mapInfo[mapId].nodes.push(routMap.getDrawingNode(mapId,list[i].GPS_Y, list[i].GPS_X));
			}*/
			// 노드 타입이 버스 정류소 마커 표시
			if(list[i].NODE_TYPE == routMap.NODE_TYPE.BUSSTOP && routMap.mapInfo[mapId].dispCheck.indexOf(routMap.NODE_TYPE.BUSSTOP)>=0) {
				routMap.addMarkerInter(mapId, list[i], grid, i, focusIdx);
			}
			else if(list[i].NODE_TYPE == routMap.NODE_TYPE.CROSS && routMap.mapInfo[mapId].dispCheck.indexOf(routMap.NODE_TYPE.CROSS)>=0) {
				routMap.addMarkerInter(mapId, list[i], grid, i, focusIdx);
			}
			else if(list[i].NODE_TYPE == routMap.NODE_TYPE.VERTEX && routMap.mapInfo[mapId].dispCheck.indexOf(routMap.NODE_TYPE.VERTEX)>=0) {
				routMap.addMarkerInter(mapId, list[i], grid, i, focusIdx);
			}
			// 아닐 경우(일반 노드) 네모 박스 표시
			else if(list[i].NODE_TYPE == routMap.NODE_TYPE.NORMAL && routMap.mapInfo[mapId].dispCheck.indexOf(routMap.NODE_TYPE.NORMAL)>=0) {
				routMap.addMarkerInter(mapId, list[i], grid, i, focusIdx);
			}
			
			if(i < list.length -1){
				var mornStd = list[i].MORN_STD;
				if(com.isEmpty(mornStd)){
					mornStd = oldMornStd;
				}
				else {
					oldMornStd = list[i].MORN_STD;
				}
				
				var color = "#3396ff";
				if(mornStd=='MS002'){
					color = "#cd6c15";
				}
				else if(mornStd=='MS003'){
					color = "#FF005E";
				}
				
				routMap.drawLine(mapId, list[i], list[i+1], color);
			}
		}
		routMap.mapInfo[mapId].dragging = false;
		
		if(list.length>0){
			if(focusIdx!=-1){
				routMap.moveMap(mapId, list[focusIdx].GPS_Y, list[focusIdx].GPS_X);
			}
			else {
				routMap.moveMap(mapId, list[parseInt(list.length/2)].GPS_Y, list[parseInt(list.length/2)].GPS_X);
			}
		}
	}
}

routMap.drawMockLink = function(mapId, grid, list, focusIdx, clickEvent, dataCheckFunc) {
	/*if(list.length>0){
		var data = list[list.length-1];
		var temp = {
			NODE_ID: data.T_NODE,
			GPS_Y: data.T_GPS_Y,
			GPS_X: data.T_GPS_X,
			NODE_NM: data.T_NODE_NAME,
			NODE_TYPE: data.T_NODE_TYPE
		};
		list.push(temp);
	}*/
	routMap.initDisplay(mapId);
	
	if(list != null && list.getDataLength() != 0) {
		var oldMornStd = "";
		for(var i = 0; i < list.getDataLength(); i++) {
			
			/**드래그이벤트**/
			if(routMap.mapInfo[mapId].draggable){
				list[i].click = function(e) {
					routMap.mapInfo[mapId].dragging = false;
					routMap.moveRoute(mapId,grid,e);
				};
			}
			
			list.getRowJSON(i).index = i;
			
			/**드래그이벤트**/
			list.getRowJSON(i).draggable = routMap.mapInfo[mapId].draggable;
			
//			if(list[i].NODE_TYPE == routMap.NODE_TYPE.BUSSTOP && routMap.mapInfo[mapId].dispCheck.indexOf(routMap.NODE_TYPE.BUSSTOP)>=0) {
//				routMap.addMarkerInter(mapId, list[i], grid, i, focusIdx);
//			}
			routMap.addMarkerInter(mapId, list.getRowJSON(i), grid, i, focusIdx, true);
			
			if(i < list.getDataLength() -1){
				
				var color = "#5b5b5b";
//				if(mornStd=='MS002'){
//					color = "#cd6c15";
//				}
//				else if(mornStd=='MS003'){
//					color = "#FF005E";
//				}
				var data = list.getRowJSON(i);
				
				var start = {
						NODE_ID: data.F_NODE,
						GPS_Y: data.F_GPS_Y,
						GPS_X: data.F_GPS_X,
						NODE_NM: data.F_NODE_NAME,
						NODE_TYPE: data.F_NODE_TYPE
					};
				
				var end = {
					NODE_ID: data.T_NODE,
					GPS_Y: data.T_GPS_Y,
					GPS_X: data.T_GPS_X,
					NODE_NM: data.T_NODE_NAME,
					NODE_TYPE: data.T_NODE_TYPE
				};
				
				//focus와 같으면 파란색으로
				if(focusIdx == i) {
					color = "#1e90ff";
				}
				routMap.drawLine(mapId, start, end, color, "addSubGrid", clickEvent, i, dataCheckFunc);
			}
		}
		routMap.mapInfo[mapId].dragging = false;

		
//		if(list.length>0){
//			if(focusIdx!=-1){
//				routMap.moveMap(mapId, list[focusIdx].GPS_Y, list[focusIdx].GPS_X);
//			}
//			else {
//				routMap.moveMap(mapId, list[parseInt(list.length/2)].GPS_Y, list[parseInt(list.length/2)].GPS_X);
//			}
//		}
	}
}
/**
 * 그리드로 노선에서 사운드 노드 그리기
 * @param mapId : 대상 map id
 * @param grid : 대상 그리드
 * @param focusIdx : 포커서 index
 */
routMap.drawSound = function(mapId, grid, focusIdx) {
	
	var list = com.getGridDispJsonData(grid);

	if(routMap.mapInfo[mapId].linkMode){
		if(list.length>0){
			var data = list[list.length-1];
			var temp = {
				NODE_ID: data.ED_NODE_ID,
				GPS_Y: data.ED_GPS_Y,
				GPS_X: data.ED_GPS_X,
				NODE_NM: data.ED_NODE_NM,
				NODE_TYPE: data.ED_NODE_TYPE
			};
			list.push(temp);
		}
	}
	routMap.initDisplay(mapId);
	
	if(list != null && list.length != 0) {
		var oldMornStd = "";
		for(var i = 0; i < list.length; i++) {
			
			/**드래그이벤트**/
			if(routMap.mapInfo[mapId].draggable && list[i].NODE_TYPE == routMap.NODE_TYPE.SOUND){
				list[i].click = function(e) {
					routMap.mapInfo[mapId].dragging = false;
					routMap.moveRoute(mapId,grid,e);
				};
			}
			
			list[i].index = i;
			
			if(list[i].NODE_TYPE == routMap.NODE_TYPE.SOUND) {
				
				if( list[i].ALL_PLAY_TM != null && list[i].ALL_PLAY_TM != 0) {
						var radius = Math.round((routMap.LIMIT_SPEED / 3600 * 1000) * list[i].ALL_PLAY_TM);
						
						routMap.mapInfo[mapId].circles.push(routMap.getDrawingCircle(mapId, list[i].GPS_Y, list[i].GPS_X, radius));
				}
			}
			/*else if(list[i].NODE_TYPE != routMap.NODE_TYPE.VERTEX){
				routMap.mapInfo[mapId].nodes.push(routMap.getDrawingNode(mapId,list[i].GPS_Y, list[i].GPS_X));
			}*/
			
			// 노드 타입이 사운드, 버스, 정류소 마커 표시
			if(list[i].NODE_TYPE == routMap.NODE_TYPE.SOUND) {
				list[i].draggable = routMap.mapInfo[mapId].draggable;
				routMap.addSoundMarkerInter(mapId, list[i], grid, i, focusIdx);
			}
			else if(list[i].NODE_TYPE == routMap.NODE_TYPE.BUSSTOP) {
				list[i].draggable = false;
				routMap.addSoundMarkerInter(mapId, list[i], grid, i, focusIdx);
			}
			else if(list[i].NODE_TYPE == routMap.NODE_TYPE.CROSS) {
				list[i].draggable = false;
				routMap.addSoundMarkerInter(mapId, list[i], grid, i, focusIdx);
			}
			else if(list[i].NODE_TYPE == routMap.NODE_TYPE.NORMAL) {
				routMap.addSoundMarkerInter(mapId, list[i], grid, i, focusIdx);
			}
			

			if(i < list.length -1){
				var mornStd = list[i].MORN_STD;
				if(com.isEmpty(mornStd)){
					mornStd = oldMornStd;
				}
				else {
					oldMornStd = list[i].MORN_STD;
				}
				
				var color = "#3396ff";
				if(mornStd=='MS002'){
					color = "#cd6c15";
				}
				else if(mornStd=='MS003'){
					color = "#FF005E";
				}

				routMap.drawLine(mapId, list[i], list[i+1], color);
			}
		}
		routMap.mapInfo[mapId].dragging = false;

		
		if(list.length>0){
			if(focusIdx!=-1){
				routMap.moveMap(mapId, list[focusIdx].GPS_Y, list[focusIdx].GPS_X);
			}
			else {
				routMap.moveMap(mapId, list[parseInt(list.length/2)].GPS_Y, list[parseInt(list.length/2)].GPS_X);
			}
		}
	}
}

/**
 * 그리드로 노선에서 노드 표시
 * @param mapId : 대상 map id
 * @param list : 대상 데이터 리스트
 * @param id : 노드 id
 * @param type : 노드 유형
 */
routMap.showRoute = function(mapId, list, id, type) {

	var focusIdx = -1;
	routMap.initDisplay(mapId);
	if(list != null && list.length != 0) {
		var oldMornStd = "";
		for(var i = 0; i < list.length; i++) {
			list[i].index = i;
			
			/**드래그이벤트**/
			list[i].draggable = routMap.mapInfo[mapId].draggable;
			
/*			if(list[i].NODE_TYPE != routMap.NODE_TYPE.VERTEX){
				routMap.mapInfo[mapId].nodes.push(routMap.getDrawingNode(mapId,list[i].GPS_Y, list[i].GPS_X));
			}*/
			
			if(type == "BUSSTOP" && list[i].STTN_ID == id){
				focusIdx = i;
				if((list[i].NODE_TYPE != routMap.NODE_TYPE.NORMAL) &&(list[i].NODE_TYPE != routMap.NODE_TYPE.VERTEX))
					routMap.showOnlyMarker(mapId, list[i], i, focusIdx);
			}
			else if(type == "CROSS" && list[i].CRS_ID == id){
				focusIdx = i;
				if((list[i].NODE_TYPE != routMap.NODE_TYPE.NORMAL) &&(list[i].NODE_TYPE != routMap.NODE_TYPE.VERTEX))
					routMap.showOnlyMarker(mapId, list[i], i, focusIdx);
			}
			else if(type != "ONLYLINE"){
				if((list[i].NODE_TYPE != routMap.NODE_TYPE.NORMAL) &&(list[i].NODE_TYPE != routMap.NODE_TYPE.VERTEX))
					routMap.showOnlyMarker(mapId, list[i], i, focusIdx);
			}
			
			if(i < list.length -1){
				var mornStd = list[i].MORN_STD;
				if(com.isEmpty(mornStd)){
					mornStd = oldMornStd;
				}
				else {
					oldMornStd = list[i].MORN_STD;
				}
				
				var color = "#3396ff";
				if(mornStd=='MS002'){
					color = "#cd6c15";
				}
				else if(mornStd=='MS003'){
					color = "#FF005E";
				}
				
				if(list[i].ROUT_ID == list[i+1].ROUT_ID) //동일 노선끼리만 선 연결 되도록 함
					routMap.drawLine(mapId, list[i], list[i+1], color);
			}
		}

		if(list.length>0){
			if(focusIdx!=-1){
				routMap.moveMap(mapId, list[focusIdx].GPS_Y, list[focusIdx].GPS_X);
			}
			else {
				routMap.moveMap(mapId, list[parseInt(list.length/2)].GPS_Y, list[parseInt(list.length/2)].GPS_X);
			}
		}
	}
}

/**
 * 그리드로 노선에서 노드 표시
 * @param mapId : 대상 map id
 * @param list : 대상 데이터 리스트
 * @param id : 노드 id
 * @param type : 노드 유형
 */
routMap.showRoute2 = function(mapId, list, focusIdx, grid) {
	routMap.initDisplay(mapId);
	if(list != null && list.length != 0) {
		var oldMornStd = "";
		for(var i = 0; i < list.length; i++) {
			list[i].index = i;
			
			/**드래그이벤트**/
			list[i].draggable = routMap.mapInfo[mapId].draggable;
			
			/*if(list[i].NODE_TYPE != routMap.NODE_TYPE.VERTEX){
				routMap.mapInfo[mapId].nodes.push(routMap.getDrawingNode(mapId,list[i].GPS_Y, list[i].GPS_X));
			}*/
			
			if((list[i].NODE_TYPE != routMap.NODE_TYPE.NORMAL) &&(list[i].NODE_TYPE != routMap.NODE_TYPE.VERTEX))
				routMap.showOnlyMarker(mapId, list[i], i, focusIdx, grid);
			
			if(i < list.length -1){
				var mornStd = list[i].MORN_STD;
				if(com.isEmpty(mornStd)){
					mornStd = oldMornStd;
				}
				else {
					oldMornStd = list[i].MORN_STD;
				}
				
				var color = "#3396ff";
				if(mornStd=='MS002'){
					color = "#cd6c15";
				}
				else if(mornStd=='MS003'){
					color = "#FF005E";
				}
				
				if(list[i].ROUT_ID == list[i+1].ROUT_ID) //동일 노선끼리만 선 연결 되도록 함
					routMap.drawLine(mapId, list[i], list[i+1], color);
			}
		}

		if(list.length>0){
			if(focusIdx!=-1){
				routMap.moveMap(mapId, list[focusIdx].GPS_Y, list[focusIdx].GPS_X);
			}
			else {
				routMap.moveMap(mapId, list[parseInt(list.length/2)].GPS_Y, list[parseInt(list.length/2)].GPS_X);
			}
		}
	}
}

/**
 * 노드 표시
 * @param mapId : 대상 map id
 * @param list : 대상 데이터 리스트
 * @param focusIdx : 포커스 index
 * @param grid : 대상 그리드
 */
routMap.showNode = function(mapId, list, focusIdx, grid) {
	routMap.initNode(mapId);
	if(list != null && list.length != 0) {
		for(var i = 0; i < list.length; i++) {
			list[i].index = i;
			
			/**드래그이벤트**/
			list[i].draggable = routMap.mapInfo[mapId].draggable;
			
			if((list[i].NODE_TYPE != routMap.NODE_TYPE.NORMAL) &&(list[i].NODE_TYPE != routMap.NODE_TYPE.VERTEX))
				routMap.showMarker(mapId, list[i], i, focusIdx, grid);
		}

		if(list.length>0){
			if(focusIdx!=-1){
				routMap.moveMap(mapId, list[focusIdx].GPS_Y, list[focusIdx].GPS_X);
			}
			else {
				routMap.moveMap(mapId, list[parseInt(list.length/2)].GPS_Y, list[parseInt(list.length/2)].GPS_X);
			}
		}
	}
}

/**
 * 마커목록 표시
 * @param mapId : 대상 map id
 * @param list : 대상 데이터 리스트
 * @param focusIdx : 포커스 index
 * @param grid : 대상 그리드
 */
routMap.showMarkerList = function(mapId, list, focusIdx, grid) {
	routMap.initNode(mapId);
	if(list != null && list.length != 0) {
		for(var i = 0; i < list.length; i++) {
			list[i].index = i;
			
			routMap.showMarker(mapId, list[i], i, focusIdx, grid); 
		}
		
		if(list.length>0){
			if(focusIdx!=-1){
				routMap.moveMap(mapId, list[focusIdx].GPS_Y, list[focusIdx].GPS_X);
			}
			else {
				routMap.moveMap(mapId, list[parseInt(list.length/2)].GPS_Y, list[parseInt(list.length/2)].GPS_X);
			}
		}
	}
}

/**
 * 그리드 없이 마커목록 표시
 * @param mapId : 대상 map id
 * @param list : 대상 데이터 리스트
 */
routMap.showMarkerListNoGrid = function(mapId, list) {
	routMap.initNode(mapId);
	if(list != null && list.length != 0) {
		for(var i = 0; i < list.length; i++) {
			list[i].index = i;
			routMap.showMarkerNoGrid(mapId, list[i]); 
		}
		
		
//	  if(list.length>0){
//			if(focusIdx!=-1){
//				routMap.moveMap(mapId, list[focusIdx].GPS_Y, list[focusIdx].GPS_X);
//			}
//			else {
//				routMap.moveMap(mapId, list[parseInt(list.length/2)].GPS_Y, list[parseInt(list.length/2)].GPS_X);
//			}
//		}
		
	}
}

/**
 * 탭이 있을때 마커목록 표시
 * @param mapId : 대상 map id
 * @param list : 대상 데이터 리스트
 * @param focusIdx : 포커스 index
 * @param grid : 대상 그리드
 */
routMap.showMarkerListTab = function(mapId, list, focusIdx, grid) {
	routMap.initNode(mapId);
	
	if(list != null && list.length != 0) {
		for(var i = 0; i < list.length; i++) {
			list[i].index = i;
			
			routMap.showMarkerTab(mapId, list[i], i, focusIdx, grid); 
		}
		
		if(list.length>0){
			if(focusIdx!=-1){
				routMap.moveMap(mapId, list[focusIdx].GPS_Y, list[focusIdx].GPS_X);
			}
			else {
				routMap.moveMap(mapId, list[parseInt(list.length/2)].GPS_Y, list[parseInt(list.length/2)].GPS_X);
			}
		}
	}
}

/**
 * 차 표시
 * @param mapId : 대상 map id
 * @param list : 대상 데이터 리스트
 * @param vhc_id : 차량 ID
 * @param grid : 대상 그리드
 */
routMap.showVehicle = function(mapId, list, vhc_id, grid) {

	var focusIdx = -1;
	routMap.initBus(mapId);
	
	if(list != null && list.length != 0) {
		for(var i = 0; i < list.length; i++) {
			list[i].index = i;
			
			/**드래그이벤트**/
			list[i].draggable = routMap.mapInfo[mapId].draggable;
			
			
			if(list[i].VHC_ID == vhc_id){
				focusIdx = i;
				routMap.showBusMarker(mapId, list[i], i, focusIdx, grid);
			}
			else {
				routMap.showBusMarker(mapId, list[i], i, focusIdx, grid);
			}
			
		}

		if(list.length>0){
			if(focusIdx!=-1){
				routMap.moveMap(mapId, list[focusIdx].GPS_Y, list[focusIdx].GPS_X);
			}
			else {
				routMap.moveMap(mapId, list[parseInt(list.length/2)].GPS_Y, list[parseInt(list.length/2)].GPS_X);
			}
		}
	}
}


/**
 * 웹소켓 차량 정보 지도에 표시
 * @param mapId : 대상 map id
 * @param json : 전달된 차량 데이터
 * @param cur_vhc_id : 현재차량 ID
 * @param index : 차량과 매핑된 index
 * @param focusIdx : 포커스된 index
 */
routMap.showVehicle2 = function(mapId, json, cur_vhc_id, grid, index, focusIdx) {
	//주석 빼기
    routMap.initIndexBus(mapId,index);


	/**드래그이벤트**/
	json.draggable = routMap.mapInfo[mapId].draggable;
	
	
	if(json.VHC_ID == cur_vhc_id){
		routMap.showBusMarker(mapId, json, index, focusIdx, grid);
	}
	else if(json.GRP_VHC_ID == cur_vhc_id){
		routMap.showBusMarker(mapId, json, index, focusIdx, grid);
	}
	else {
		routMap.showBusMarker(mapId, json, index, focusIdx, grid);
	}
	if(json != null){
		if(focusIdx!=-1 && focusIdx == index){
			routMap.moveMap(mapId, json.GPS_Y, json.GPS_X);
		}
	}
}

/**
 * 차 표시 : 이벤트 처리 안됨
 * @param mapId : 대상 map id
 * @param list : 대상 데이터 리스트
 * @param vhc_id : 차량 ID
 * @param grid : 대상 그리드
 */
routMap.showVehicleNotEvent = function(mapId, list, vhc_id, grid) {

	var focusIdx = -1;
	routMap.initBus(mapId);
	
	if(list != null && list.length != 0) {
		for(var i = 0; i < list.length; i++) {
			list[i].index = i;
			
			/**드래그이벤트**/
			list[i].draggable = routMap.mapInfo[mapId].draggable;
			
			
			if(list[i].VHC_ID == vhc_id){
				focusIdx = i;
				routMap.showBusMarkerNotEvent(mapId, list[i], i, focusIdx, grid);
			}
			else {
				routMap.showBusMarkerNotEvent(mapId, list[i], i, focusIdx, grid);
			}
			
		}

		if(list.length>0){
			if(focusIdx!=-1){
				routMap.moveMap(mapId, list[focusIdx].GPS_Y, list[focusIdx].GPS_X);
			}
			else {
				routMap.moveMap(mapId, list[parseInt(list.length/2)].GPS_Y, list[parseInt(list.length/2)].GPS_X);
			}
		}
	}
}

/**
 * 차 표시 : 클릭overlay 동작
 * @param mapId : 대상 map id
 * @param json : 전달된 차량 데이터
 * @param cur_vhc_id : 현재차량 ID
 * @param index : 차량과 매핑된 index
 * @param focusIdx : 포커스된 index
 */
routMap.showVehicleClickOverlay2 = function(mapId, json, cur_vhc_id, grid, index, focusIdx) {
	//주석 빼기
	routMap.initIndexBus(mapId,index);
	
	
	/**드래그이벤트**/
	json.draggable = routMap.mapInfo[mapId].draggable;
	
	
	if(json.VHC_ID == cur_vhc_id){
		routMap.showBusMarkerClickOverlay(mapId, json, index, focusIdx, grid);
	}
	else if(json.GRP_VHC_ID == cur_vhc_id){
		routMap.showBusMarkerClickOverlay(mapId, json, index, focusIdx, grid);
	}
	else {
		routMap.showBusMarkerClickOverlay(mapId, json, index, focusIdx, grid);
	}
	if(json != null){
		if(focusIdx!=-1 && focusIdx == index){
			routMap.moveMap(mapId, json.GPS_Y, json.GPS_X);
		}
	}
}

/**
 * 차 이동
 * @param mapId : 대상 map id
 * @param json : 전달된 데이터
 * @param index : 차량과 매핑된 index
 * @param focusIdx : 포커스된 index
 */
routMap.moveVehicle = function(mapId, json, index, focusIdx) {
	var latLng = new kakao.maps.LatLng(json.GPS_Y, json.GPS_X);
	
	routMap.mapInfo[mapId].busMarkers[index].setPosition(latLng);
	routMap.mapInfo[mapId].busOverArr[index].setPosition(latLng);
	
	if(routMap.mapInfo[mapId].dsptchOverArr[0] != null) {
		routMap.mapInfo[mapId].dsptchOverArr[0].setPosition(latLng);
	}
	
	if(routMap.mapInfo[mapId].eventOverArr[0] != null) {
		routMap.mapInfo[mapId].eventOverArr[0].setPosition(latLng);
	}
	
	if(json != null){
		if(focusIdx!=-1 && focusIdx == index){
			routMap.moveMap(mapId, json.GPS_Y, json.GPS_X);
		}
	}	
}

/**
 * 클릭시 차 위치 이동
 * @param mapId : 대상 map id
 * @param json : 웹소켓을 통해 전달된 데이터
 * @param curIndex : 현재 index
 * @param e : 클릭한 event 정보
 */
routMap.changeLocVehicleByClick = function(mapId, grid, curIndex, e){
	var data = com.getGridViewDataList(grid);
	var lonlat = e.latLng;

	data.setCellData(curIndex, "GPS_Y", util.getDispGps(lonlat.Ma,7));
	data.setCellData(curIndex, "GPS_X", util.getDispGps(lonlat.La,7));
	var jsonObj = data.getRowJSON(curIndex);
	
	routMap.showVehicle3(mapId, jsonObj, grid, 0);
}

/**
 * 차 표시
 * @param mapId : 대상 map id
 * @param json : 전달된 차량데이터
 * @param grid : 대상 그리드
 */
routMap.showVehicle3 = function(mapId, json, grid) {
	
	//주석 빼기
    routMap.initIndexBus(mapId,0);


	/**드래그이벤트**/
	json.draggable = routMap.mapInfo[mapId].draggable;
	
	routMap.showBusMarker(mapId, json, 0, 0, grid);

	if(json != null){
		routMap.moveMap(mapId, json.GPS_Y, json.GPS_X);
	}
}


/**
 * 차량 표시 : overlay 클릭시 처리
 * @param mapId : 대상 map id
 * @param list : 대상 데이터 목록
 * @param vhc_id : 차량 id
 * @param grid : 대상 그리드
 */
routMap.showVehicleClickOverlay = function(mapId, list, vhc_id, grid, gridChk, socketVhcId) {
	var focusIdx = -1;
	
	routMap.initBus(mapId);

	if(list != null && list.length != 0) {
		for(var i = 0; i < list.length; i++) {
			list[i].index = i;
			
			/**드래그이벤트**/
			list[i].draggable = routMap.mapInfo[mapId].draggable;
			
			
			if(list[i].VHC_ID == vhc_id){
				focusIdx = i;
				routMap.showBusMarkerClickOverlay(mapId, list[i], i, focusIdx, grid, gridChk, socketVhcId);
			}
			else {
				routMap.showBusMarkerClickOverlay(mapId, list[i], i, focusIdx, grid, gridChk, socketVhcId);
			}
			
		}

		if(list.length>0){
			if(focusIdx!=-1){
				routMap.moveMap(mapId, list[focusIdx].GPS_Y, list[focusIdx].GPS_X);
			}
			else {
				routMap.moveMap(mapId, list[parseInt(list.length/2)].GPS_Y, list[parseInt(list.length/2)].GPS_X);
			}
		}
	}
}

/**
 * 클릭시 다각형(차고지) 추가
 * @param mapId : 대상 map id
 * @param list : 대상 데이터 목록
 * @param vhc_id : 차량 id
 * @param grid : 대상 그리드
 */
routMap.addPolygonByClick = function(mapId, data, grgId, grgNm, e){
	var lonlat = e.latLng;
	
	var idx = data.insertRow();
	if(idx > 29) {
		com.alert("차고지 포인트는 30개를 초과할 수 없습니다.")
		data.deleteRow();
	}
	
	else {
		var today = new Date();
		var tmp = {
				GRG_ID: grgId,
				SN: idx+1,
				GPS_Y: util.getDispGps(lonlat.Ma,7),
				GPS_X: util.getDispGps(lonlat.La,7)
		};
		
		data.setRowJSON(idx, tmp, true);
		routMap.drawPolygon(mapId, data);
	}
}

/**
 * 다각형(차고지) 그리기
 * @param mapId : 대상 map id
 * @param data : 대상 데이터
 * @param name : 차고지 명
 */
routMap.drawPolygon = function(mapId, data, name) {
	

	routMap.initDisplay(mapId);
	
	var list = 	com.getGridDispJsonData2(data);
	var path = [];
	
	//routMap.removeMarkers();
	//routMap.deleteLine();
	//routMap.deleteCircle();
	//routMap.deleteNode();

	//첫번째 메뉴 세팅
	if(list != null && list.length != 0) {
		for(var i = 0; i < list.length; i++) {
			path.push(new kakao.maps.LatLng(list[i].GPS_Y, list[i].GPS_X));

			list[i].index = i;

			routMap.mapInfo[mapId].nodes.push(routMap.getDrawingNode(mapId, list[i].GPS_Y, list[i].GPS_X));
		}

		// 지도에 표시할 다각형을 생성합니다
		var polygon = new kakao.maps.Polygon({
			path:path, // 그려질 다각형의 좌표 배열입니다
			strokeWeight: 3, // 선의 두께입니다
			strokeColor: '#39DE2A', // 선의 색깔입니다
			strokeOpacity: 0.8, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
			strokeStyle: 'solid', // 선의 스타일입니다
			fillColor: '#A2FF99', // 채우기 색깔입니다
			fillOpacity: 0.7 // 채우기 불투명도 입니다
		});
		
		// 지도에 다각형을 표시합니다
		polygon.setMap(routMap.mapInfo[mapId].map);
		routMap.mapInfo[mapId].polygons.push(polygon);
		
		if(typeof name !== "undefined"){
			
			var center = routMap.mapInfo[mapId].map.getCenter(); 
			var content = '<div class="map_info">' + 
				'<div class="map_title">' + '차고지 : ' + name + '</div>' +
				'<div class="size">총 면적 : 약 ' + Math.floor(polygon.getArea()) + ' m<sup>2</sup></area>' +
				'</div>';
			routMap.mapInfo[mapId].infoWindow = new kakao.maps.InfoWindow({removable: true});
			routMap.mapInfo[mapId].infoWindow.setContent(content); 
			routMap.mapInfo[mapId].infoWindow.setPosition(center); 
			routMap.mapInfo[mapId].infoWindow.setMap(routMap.mapInfo[mapId].map);
			routMap.mapInfo[mapId].infoArr.push(routMap.mapInfo[mapId].infoWindow);
		}
		
		// 다각형에 마우스오버 이벤트가 발생했을 때 변경할 채우기 옵션입니다
		var mouseoverOption = { 
			fillColor: '#EFFFED', // 채우기 색깔입니다
			fillOpacity: 0.8 // 채우기 불투명도 입니다        
		};

		// 다각형에 마우스아웃 이벤트가 발생했을 때 변경할 채우기 옵션입니다
		var mouseoutOption = {
			fillColor: '#A2FF99', // 채우기 색깔입니다 
			fillOpacity: 0.7 // 채우기 불투명도 입니다        
		};

		// 다각형에 마우스오버 이벤트를 등록합니다
		kakao.maps.event.addListener(polygon, 'mouseover', function() { 

		// 다각형의 채우기 옵션을 변경합니다
			polygon.setOptions(mouseoverOption);

		}); 

		kakao.maps.event.addListener(polygon, 'mouseout', function() { 

			// 다각형의 채우기 옵션을 변경합니다
			polygon.setOptions(mouseoutOption);
		
		}); 

		// 다각형에 마우스다운 이벤트를 등록합니다
		var downCount = 0;
		kakao.maps.event.addListener(polygon, 'mousedown', function() { 
		}); 
		
		// 다각형에 click 이벤트를 등록하고 이벤트가 발생하면 다각형의 이름과 면적을 인포윈도우에 표시합니다
		kakao.maps.event.addListener(polygon, 'click', function(mouseEvent) {
			routMap.mapInfo[mapId].infowindow.open(routMap.mapInfo[mapId].map);
			
		});
	}
}

/**
 * 맵초기화
 * @param mapId : 대상 map id
 */
routMap.initDisplay = function(mapId){
	
	routMap.removeAllInfoWindow(mapId);
	routMap.removeAllOverlay(mapId);
	routMap.removeMarkers(mapId);
	routMap.deleteLine(mapId);
	routMap.deleteCircle(mapId);
	routMap.deleteNode(mapId);
	routMap.deletePolygon(mapId);
}

/**
 * 노드(overlay, 마커)초기화
 * @param mapId : 대상 map id
 */
routMap.initNode = function(mapId){
	routMap.removeAllOverlay(mapId);
	routMap.removeMarkers(mapId);
}

/**
 * 버스(overlay, 마커)초기화
 * @param mapId : 대상 map id
 */
routMap.initBus = function(mapId){
	routMap.removeAllBusOverlay(mapId);
	routMap.removeBusMarkers(mapId);
}

/**
 * 특정 index의 bus overlay, bus marker 삭제
 * @param mapId : 대상 map id
 * @param index : 삭제할 index
 */
routMap.initIndexBus = function(mapId,index){
	routMap.removeIndexBusOverlay(mapId,index);
	routMap.removeIndexBusMarker(mapId,index);
}

/**
 * map 정보 초기화
 * @param mapId : 대상 map id
 */
routMap.initMapInfo = function(mapId){
	routMap.initDisplay(mapId);
	routMap.initBus(mapId);
	routMap.mapInfo[mapId].polylines = [];
	routMap.mapInfo[mapId].markers = [];
	routMap.mapInfo[mapId].markers_user = [];
	routMap.mapInfo[mapId].infoWindow = null;
	routMap.mapInfo[mapId].infoArr = [];
	routMap.mapInfo[mapId].overlay = null;
	routMap.mapInfo[mapId].overArr = [];
	// 반경 표시용 원 배열
	routMap.mapInfo[mapId].circles = []; 
	// 노드 드로잉 배열
	routMap.mapInfo[mapId].nodes = [];
	routMap.mapInfo[mapId].dragging = false; //드래그 중인지
	routMap.mapInfo[mapId].selectedMarker = null;
	routMap.mapInfo[mapId].selectedIndex = -1;
}

/**
 * 모든 overlay 초기화
 * @param mapId : 대상 map id
 */
routMap.initOverlay = function(mapId) {
	routMap.removeAllOverlay(mapId);	
}

/**
 * 노드 타입 세팅
 * @param mapId : 대상 map id
 * @param dispCheck : 체크할 disp 타입
 */
routMap.setDispCheck = function(mapId, dispCheck) {
	routMap.mapInfo[mapId].dispCheck = dispCheck;
}


/**
 * 맵 타입 지정
 * @param mapId : 대상 map id
 * @param type : 맵 타입
 */
routMap.addOverMapType = function(mapId, type) {
   routMap.mapInfo[mapId].map.addOverlayMapTypeId(type);  
}

/**
 * 맵 타입 삭제
 * @param mapId : 대상 map id
 * @param type : 맵 타입
 */
routMap.removeOverMapType = function(mapId, type) {
	routMap.mapInfo[mapId].map.removeOverlayMapTypeId(type);
}

/**
 * 링크 정보 표시
 * @param mapId : 대상 map id
 * @param list : 링크 리스트
 */
routMap.showCommuMap = function(mapId, list) {
	//routMap.initDisplay(mapId);
	if(list != null && list.length != 0) {
		for(var i = 0; i < list.length; i++) {
			list[i].index = i;
			
			if(i < list.length -1){
				//원활
				var color = "#4CAF50";
				
				//서행
				if(list[i].AVRG_SPD > 15 && list[i].AVRG_SPD < 25){
					color = "#FFC107";
				}
				
				//정체
				else if(list[i].AVRG_SPD <= 15){
					color = "#F44336";
				}
				
				var path = [];
				path.push(new kakao.maps.LatLng(list[i].ST_GPS_Y, list[i].ST_GPS_X));
				path.push(new kakao.maps.LatLng(list[i].ED_GPS_Y, list[i].ED_GPS_X));
				
				var polyline = new kakao.maps.Polyline({
					path: path,
					strokeColor: color, // 라인 색상
					strokeWeight: 5, // 라인 두께
					strokeStyle:'solid',
					strokeOpacity: 0.8
				});
				
				polyline.setMap(routMap.mapInfo[mapId].map);
				
				routMap.mapInfo[mapId].polylines.push(polyline);
			}
			
		}
		
	}
}

routMap.addressSearch = function(mapId, address) {
	var geocoder = new kakao.maps.services.Geocoder();
	var coords = null;
	// 주소로 좌표를 검색합니다
	geocoder.addressSearch(
		address,
		function(result, status) {
	
			// 정상적으로 검색이 완료됐으면
			if (status === kakao.maps.services.Status.OK) {
	
				coords = new kakao.maps.LatLng(result[0].y,
						result[0].x);
	
	
				// 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
				routMap.mapInfo[mapId].map.setCenter(coords);
			}
		}); 
	
}

routMap.setCenter = function(mapId, lat, lon) {
	routMap.mapInfo[mapId].map.setCenter(new kakao.maps.LatLng(lat, lon));
}

/*
routMap.showLink = function(mapId, list) {
	//routMap.initDisplay(mapId);
	if(list != null && list.length != 0) {
		for(var i = 0; i < list.length; i++) {
			list[i].index = i;
			
			if(i < list.length -1){
				//원활
				//var color = "#4CAF50";
				var color = "#F44336";
				
//				if(list[i].AVRG_SPD > 15 && list[i].AVRG_SPD < 25){
//					color = "#FFC107";
//				}
//				
//				else if(list[i].AVRG_SPD <= 15){
//					color = "#F44336";
//				}
				
				var path = [];
				path.push(new kakao.maps.LatLng(list[i].F_GPS_Y, list[i].F_GPS_X));
				path.push(new kakao.maps.LatLng(list[i].T_GPS_Y, list[i].T_GPS_X));
				
				var polyline = new kakao.maps.Polyline({
					path: path,
					strokeColor: color, // 라인 색상
					strokeWeight: 5, // 라인 두께
					strokeStyle:'solid',
					strokeOpacity: 0.8
				});

				
			    kakao.maps.event.addListener(polyline, 'mouseover', function(mouseEvent) {
			    	
			    });
			    
			    kakao.maps.event.addListener(polyline, 'mouseout', function(mouseEvent) {
			    	
			    });
			    
			    kakao.maps.event.addListener(polyline, 'mousemove', function(mouseEvent) {
			    	
			    });
			    
//			    kakao.maps.event.addListener(polyline, 'mousedown', function(mouseEvent) {
//			    	
//			    });
			    
			    kakao.maps.event.addListener(polyline, 'click', function(mouseEvent) {
			    	var aa = mouseEvent;
			    	
			    	
			    });
				
				polyline.setMap(routMap.mapInfo[mapId].map);
				
				routMap.mapInfo[mapId].polylines.push(polyline);
			}
			
		}
		
	}
}*/

/**
 * 두 지점간의 거리 계산
 * @param x1 : 시작 위도
 * @param y1 : 시작 경도
 * @param x2 : 종료 위도
 * @param y2 : 종료 경도
 * @return int : 계산된 거리 값
 */
function getDistanceBetween(x1, y1, x2, y2) {
	let kEarthRadiusKms = 6376.5;
    
    var lat1_rad = y1 * (Math.PI / 180.0);
    var lng1_rad = x1 * (Math.PI / 180.0);
    var lat2_rad = y2 * (Math.PI / 180.0);
    var lng2_rad = x2 * (Math.PI / 180.0);

    var lat_gap = lat2_rad - lat1_rad;
    var lng_gap = lng2_rad - lng1_rad;
    
    var mid_val = Math.pow(Math.sin(lat_gap / 2.0), 2.0) +
                     Math.cos(lat1_rad) * 
                     Math.cos(lat2_rad) *
                     Math.pow(Math.sin(lng_gap / 2.0), 2.0);

    var circle_distance = 2.0 * Math.atan2(Math.sqrt(mid_val), Math.sqrt(1.0 - mid_val));
    var distance = kEarthRadiusKms * circle_distance * 1000; 
    
    return distance; 
}

/**
 * 한점이 직선상에 직교좌표를 생성한 좌표를 반환
 * @param x : 비교할 위도
 * @param y : 비교할 경도
 * @param x1 : 시작 위도
 * @param y1 : 시작 경도
 * @param x2 : 종료 위도
 * @param y2 : 종료 경도
 * @return point : 직교하는 좌표
 */
function getPointToLine(x, y, x1, y1, x2, y2) {
	var isValid = false;
	var point;
	
	if(y1 == y2 && x1 == y2)
		y1 -= 0.00001;
	var U = ((y - y1) * (y2 - y1)) + ((x - x1) * (x2 - x1));
	var Udenom = Math.pow(y2 - y1, 2) + Math.pow(x2 - x1, 2);
	
	U /= Udenom;
	
	var y = y1 + (U * (y2 - y1));
	var x = x1 + (U * (x2 - x1));
	point =  {
		x: x,
		y: y
	};
	
	var minx, maxx, miny, maxy;
	
	minx = Math.min(y1, y2);
	maxx = Math.max(y1, y2);
	
	miny = Math.min(x1, x2);
	maxy = Math.max(x1, x2);
	
	isValid = (point.y >= minx && point.y <= maxx) && (point.x >= miny && point.x <= maxy);
	
	return isValid ? point : null;
}


/**
 * 한점이 직선에 직교좌표를 생성하고 거리를 계산
 * @param x : 비교할 위도
 * @param y : 비교할 경도
 * @param x1 : 시작 위도
 * @param y1 : 시작 경도
 * @param x2 : 종료 위도
 * @param y2 : 종료 경도
 * @return int : 계산된 거리 값
 */
function getDistanceToLine(x, y, x1, y1, x2, y2) {
	var point = getPointToLine(x, y, x1, y1, x2, y2);
	
	if(point == null) {
		return false;
	} else {
		var distance = getDistanceBetween(x, y, point.x, point.y);
		return {
			point: point,
			distance: distance
		}
	}
}

function getPointToLine2(x, y, x1, y1, x2, y2) {
	var point;
	
	if(y1 == y2 && x1 == y2)
		y1 -= 0.00001;
	var U = ((y - y1) * (y2 - y1)) + ((x - x1) * (x2 - x1));
	var Udenom = Math.pow(y2 - y1, 2) + Math.pow(x2 - x1, 2);
	
	U /= Udenom;
	
	var y = y1 + (U * (y2 - y1));
	var x = x1 + (U * (x2 - x1));
	point =  {
		x: x,
		y: y
	};
	
	var minx, maxx, miny, maxy;
	
	minx = Math.min(y1, y2);
	maxx = Math.max(y1, y2);
	
	miny = Math.min(x1, x2);
	maxy = Math.max(x1, x2);
	
	
	return point;
}

function getDistanceToLine2(x, y, x1, y1, x2, y2) {
	var point = getPointToLine2(x, y, x1, y1, x2, y2);
	
	if(point == null) {
		return false;
	} else {
		var distance = getDistanceBetween(x, y, point.x, point.y);
		return {
			point: point,
			distance: distance
		}
	}
}
