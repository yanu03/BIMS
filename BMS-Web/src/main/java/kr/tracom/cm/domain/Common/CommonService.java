package kr.tracom.cm.domain.Common;

import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import kr.tracom.bms.ftp.FTPHandler;
import kr.tracom.cm.support.ServiceSupport;


@Service
public class CommonService extends ServiceSupport {

	@Autowired
	private CommonMapper commonMapper;

	@Autowired
	FTPHandler ftpHandler;
	
	@Value("${fileupload.up.directory}")
	private String UPLOAD_DIR;

	@Value("${fileupload.code.directory}")
	private String UPLOAD_CODE_DIR;
	
	@Value("${fileupload.base.path}")
	private String UPLOAD_BASE_DIR;

	/**
	 * 헤더메뉴, 사이드메뉴 조회 (로그인 사용자에게 권한이 있는 메뉴만 조회함)
	 * 
	 * @param param 사용자 로그인 ID가 저장된 맵 객체
	 */

	public List selectMenuList(Map param) throws Exception {
		return commonMapper.selectMenuList(param);
	}
	
	/**
	 * 사용자별 프로그램 권한 리스트 조회 (로그인 사용자에게 권한이 있는 프로그램 권한만 조회함)
	 * 
	 * @param param 사용자 로그인 ID가 저장된  맵 객체
	 */
	public List selectProgramAuthorityList(Map param) throws Exception {
		return commonMapper.selectProgramAuthorityList(param);
	}	
	

	/**
	 * 공통그룹 조회
	 * 
	 * @param param Client 전달한 데이터 맵 객체
	 */

	public List selectCommonCo() throws Exception {
		return commonMapper.selectCommonCo(getSimpleDataMap("dma_search"));
	}

	/**
	 * 모든 공통코드 조회
	 * 
	 * @param param Client 전달한 데이터 맵 객체
	 */

	public List selectCommonDtlAll() throws Exception {
		return commonMapper.selectCommonDtl();
	}

	/**
	 * 공통코드 조회
	 * 
	 * @param param Client 전달한 데이터 맵 객체
	 */

	public List selectCommonDtlList() throws Exception {
		return commonMapper.selectCommonDtlList(getSimpleDataMap("dma_commonCO"));
	}
	
	public List selectCommonDtlImg() throws Exception {
		return commonMapper.selectCommonDtlImg(getSimpleDataMap("dma_search"));
	}

	/**
	 * 공통코드 조회
	 * 
	 * @param param Client 전달한 데이터 맵 객체
	 */

	public List<Map> selectCodeList() throws Exception {

		String[] selectCodeList;
		Map param = getSimpleDataMap("dma_commonDtl");
		String CO_CD = (String) param.get("CO_CD");


		selectCodeList = CO_CD.split(",");
		param.put("CODE", selectCodeList);
		return commonMapper.selectCodeList(param);
	}
	
	/**
	 * 공통코드 조회
	 * 
	 * @param param Client 전달한 데이터 맵 객체
	 */

	public List<Map> selectCodeList2() throws Exception {

		String[] selectCodeList;
		String[] selectCodeList2;
		Map param = getSimpleDataMap("dma_commonDtl");
		String CO_CD = (String) param.get("CO_CD");
		String DL_CD = (String) param.get("DL_CD");


		selectCodeList = CO_CD.split(",");
		selectCodeList2 = DL_CD.split(",");
		param.put("CODE", selectCodeList);
		param.put("DLCD", selectCodeList2);
		return commonMapper.selectCodeList2(param);
	}

	/**
	 * 시스템코드 조회
	 * 
	 * @param param Client 전달한 데이터 맵 객체
	 */

	public List<Map> selectSystemList() throws Exception {
		
		Map param = getSimpleDataMap("dma_systemCode");
		String systemCd = (String) param.get("SYSTEM_CD");

		String[] systemArr = systemCd.split(",");
		param.put("SYSTEM", systemArr);
		return commonMapper.selectSystemList(param);
	}
	
	
	/**
	 * 공통관리 조회(검색어)
	 * 
	 * @param param Client 전달한 데이터 맵 객체
	 */

	public List selectCommonSearchItem() throws Exception {
		return commonMapper.selectCommonSearchItem();
	}

	/**
	 * 여러 건의 코드 그룹 데이터를 변경(등록, 수정, 삭제)한다.
	 * 
	 * @param param Client 전달한 데이터 맵 객체
	 */

	public Map saveCodeCoList() throws Exception {

		int iCnt = 0;
		int uCnt = 0;
		int dCnt = 0;
		List param = getSimpleList("dlt_commonCo");
		for (int i = 0; i < param.size(); i++) {
			Map data = (Map) param.get(i);
			String rowStatus = (String) data.get("rowStatus");
			if (rowStatus.equals("C")) {
				iCnt += commonMapper.insertCommonCo(data);
			} else if (rowStatus.equals("U")) {
				uCnt += commonMapper.updateCommonCo(data);
			} else if (rowStatus.equals("D")) {
				dCnt += commonMapper.deleteCommonCo(data);
			}
		}
		Map result = new HashMap();
		result.put("STATUS", "S");
		result.put("ICNT", String.valueOf(iCnt));
		result.put("UCNT", String.valueOf(uCnt));
		result.put("DCNT", String.valueOf(dCnt));
		return result;

	}

	/**
	 * 여러 건의 코드 데이터를 변경(등록, 수정, 삭제)한다.
	 * 
	 * @param param Client 전달한 데이터 맵 객체
	 */

	public Map saveCodeDtlList() throws Exception {

		int iCnt = 0;
		int uCnt = 0;
		int dCnt = 0;
		List param = getSimpleList("dlt_commonDtl");
		Map paramMap = getSimpleDataMap("dma_commonCO");
		String CO_CD = (String) paramMap.get("CO_CD");
		
		for (int i = 0; i < param.size(); i++) {
			Map data = (Map) param.get(i);
			data.put("CO_CD", CO_CD);
			String rowStatus = (String) data.get("rowStatus");
			if (rowStatus.equals("C")) {
				String imgPathNm = data.get("IMG_PATH").toString()+data.get("IMG_NM").toString();
				data.put("IMG_PATH", imgPathNm);
				iCnt += commonMapper.insertCommonDtl(data);
				if((data.get("IMG_NM")!=null)&&(data.get("IMG_NM").toString().isEmpty()==false)) {
					
					//doMoveFile(UPLOAD_DIR,UPLOAD_CODE_DIR,data.get("IMG_NM").toString(),data.get("CO_CD").toString()+data.get("DL_CD").toString()+data.get("IMG_NM").toString()+".png");
					doMoveFile(UPLOAD_DIR,UPLOAD_CODE_DIR,data.get("IMG_NM").toString(),data.get("CO_CD").toString()+data.get("DL_CD").toString()+data.get("IMG_NM").toString());
					
					commonMapper.insertCommonDtlImgPath(data);
					
		    		/*  2020-09-29 추가
		    		 *  설명: .jpg 이미지와 CERTI 이미지가 없을 경우 운전자 단말기에서 로그인이 되지 않음. 따라서 아래 코드 추가함
		    		 */
					/*String imgFileName = data.get("DRV_ID").toString();
					String pngExtName = ".png";
					doCopyFile("common/employee/", "common/employee/", imgFileName+pngExtName, imgFileName+".jpg");
					doCopyFile("common/employee/", "common/employee/", imgFileName+pngExtName, imgFileName+"_CERTI.jpg");*/
					
					//ftp sync
					//ftpHandler.uploadSI0300();
					
				}
			} else if (rowStatus.equals("U")) {
				String imgPathNm = data.get("IMG_PATH").toString()+data.get("IMG_NM").toString();
				data.put("IMG_PATH", imgPathNm);
				uCnt += commonMapper.updateCommonDtl(data);
				if(data.get("IMG_PATH_ORI").toString().isEmpty() && data.get("IMG_PATH").toString().isEmpty() == false) { //예전 이미지 경로가 없다면 insert
					if(data.get("DL_CD").toString().equals("FK007") == false) { //스크린도어가 아닐떄
						doMoveFile(UPLOAD_DIR,UPLOAD_CODE_DIR,data.get("IMG_NM").toString(),data.get("CO_CD").toString()+data.get("DL_CD").toString()+data.get("IMG_NM").toString());
						commonMapper.insertCommonDtlImgPath(data);
					}else {
						int numVal5 = Integer.parseInt(data.get("NUM_VAL5").toString());
						int imgNum = 1;
						for(int x = 0; x < numVal5; x++) {
							String oriFilePath = "C:\\sbrt-web\\fileUpload\\up\\" + data.get("IMG_NM").toString();
							String[] imgNm = data.get("IMG_NM").toString().split("\\.");
							String copyFilePath = "C:\\sbrt-web\\fileUpload\\up\\" + imgNm[0] + imgNum + "." +imgNm[1];
							
							//파일객체생성
					        File oriFile = new File(oriFilePath);
					        //복사파일객체생성
					        File copyFile = new File(copyFilePath);
					        
					        try {
					            
					            FileInputStream fis = new FileInputStream(oriFile); //읽을파일
					            FileOutputStream fos = new FileOutputStream(copyFile); //복사할파일
					            
					            int fileByte = 0; 
					            // fis.read()가 -1 이면 파일을 다 읽은것
					            while((fileByte = fis.read()) != -1) {
					                fos.write(fileByte);
					            }
					            //자원사용종료
					            fis.close();
					            fos.close();
					            
					        } catch (FileNotFoundException e) {
					            // TODO Auto-generated catch block
					            e.printStackTrace();
					        } catch (IOException e) {
					            // TODO Auto-generated catch block
					            e.printStackTrace();
					        }
							
							doMoveFile(UPLOAD_DIR,UPLOAD_CODE_DIR,imgNm[0]+imgNum+"."+imgNm[1],data.get("CO_CD").toString()+data.get("DL_CD").toString()+imgNm[0]+imgNum+"."+imgNm[1]);
							HashMap<String, String> scrnDoorData = new HashMap<String, String>(data);
							scrnDoorData.put("IMG_PATH", UPLOAD_BASE_DIR+UPLOAD_CODE_DIR+data.get("CO_CD").toString()+data.get("DL_CD").toString()+imgNm[0]+imgNum+"."+imgNm[1]);
							scrnDoorData.put("IMG_NM", imgNm[0]+imgNum+"."+imgNm[1]);
							commonMapper.insertCommonDtlImgPath(scrnDoorData);
							
							imgNum++;
						}
						
					}
					
					/*  2020-09-29 추가
		    		 *  설명: .jpg 이미지와 CERTI 이미지가 없을 경우 운전자 단말기에서 로그인이 되지 않음. 따라서 아래 코드 추가함
		    		 */
					/*String imgFileName = data.get("DRV_ID").toString();
					String pngExtName = ".png";
					doCopyFile("common/employee/", "common/employee/", imgFileName+pngExtName, imgFileName+".jpg");
					doCopyFile("common/employee/", "common/employee/", imgFileName+pngExtName, imgFileName+"_CERTI.jpg");*/
					
					//ftp sync
					//ftpHandler.uploadSI0300();
					
				}
				
				if(data.get("IMG_PATH_ORI").toString().isEmpty() == false && data.get("IMG_PATH").equals(data.get("IMG_PATH_ORI")) == false) { //예전 이미지 경로와 현재 이미지 경로가 다르다면 update
					if(data.get("DL_CD").toString().equals("FK007") == false) { //스크린도어가 아닐떄
						doMoveFile(UPLOAD_DIR,UPLOAD_CODE_DIR,data.get("IMG_NM").toString(),data.get("CO_CD").toString()+data.get("DL_CD").toString()+data.get("IMG_NM").toString());
						//doMoveFile(UPLOAD_DIR,UPLOAD_CODE_DIR,data.get("IMG_NM").toString(),data.get("CO_CD").toString()+data.get("DL_CD").toString()+data.get("IMG_NM").toString()+".png");
						commonMapper.updateCommonDtlImgPath(data);
					}else {
						int numVal5 = Integer.parseInt(data.get("NUM_VAL5").toString());
						int imgNum = 1;
						for(int x = 0; x < numVal5; x++) {
							String oriFilePath = "C:\\sbrt-web\\fileUpload\\up\\" + data.get("IMG_NM").toString();
							String[] imgNm = data.get("IMG_NM").toString().split("\\.");
							String copyFilePath = "C:\\sbrt-web\\fileUpload\\up\\" + imgNm[0] + imgNum + "." +imgNm[1];
							
							//파일객체생성
					        File oriFile = new File(oriFilePath);
					        //복사파일객체생성
					        File copyFile = new File(copyFilePath);
					        
					        try {
					            
					            FileInputStream fis = new FileInputStream(oriFile); //읽을파일
					            FileOutputStream fos = new FileOutputStream(copyFile); //복사할파일
					            
					            int fileByte = 0; 
					            // fis.read()가 -1 이면 파일을 다 읽은것
					            while((fileByte = fis.read()) != -1) {
					                fos.write(fileByte);
					            }
					            //자원사용종료
					            fis.close();
					            fos.close();
					            
					        } catch (FileNotFoundException e) {
					            // TODO Auto-generated catch block
					            e.printStackTrace();
					        } catch (IOException e) {
					            // TODO Auto-generated catch block
					            e.printStackTrace();
					        }
							
							doMoveFile(UPLOAD_DIR, UPLOAD_CODE_DIR, imgNm[0]+imgNum+"."+imgNm[1], data.get("CO_CD").toString()+data.get("DL_CD").toString()+imgNm[0]+imgNum+"."+imgNm[1]);
							HashMap<String, String> scrnDoorData = new HashMap<String, String>(data);
							scrnDoorData.put("IMG_PATH", UPLOAD_BASE_DIR+UPLOAD_CODE_DIR+data.get("CO_CD").toString()+data.get("DL_CD").toString()+imgNm[0]+imgNum+"."+imgNm[1]);
							
							//String[] oriImgNm = data.get("IMG_PATH_ORI").toString().split(Integer.toString(imgNum)+"\\.");
							int idx = data.get("IMG_PATH_ORI").toString().indexOf(".");
							int nextIdx = idx - 1;
							String imgPathOri = data.get("IMG_PATH_ORI").toString().substring(0, nextIdx);
							String imgPathOriTpye = data.get("IMG_PATH_ORI").toString().substring(idx);
							
							scrnDoorData.put("IMG_PATH_ORI", imgPathOri + imgNum + imgPathOriTpye);
							scrnDoorData.put("IMG_NM", imgNm[0]+imgNum+"."+imgNm[1]);
							commonMapper.updateCommonDtlImgPath(scrnDoorData);
							
							imgNum++;
						}
						
					}
					
				}
				
			} else if (rowStatus.equals("D")) {
				dCnt += commonMapper.deleteCommonDtl(data);
				
				/*if((data.get("IMG_PATH")!=null)&&(data.get("IMG_PATH").toString().isEmpty()==false)) {
					Files.delete((Path) data.get("IMG_PATH"));
				}*/
			}
		}
		Map result = new HashMap();
		result.put("STATUS", "S");
		result.put("ICNT", String.valueOf(iCnt));
		result.put("UCNT", String.valueOf(uCnt));
		result.put("DCNT", String.valueOf(dCnt));
		return result;

	}

	/**
	 * 메뉴관리정보 삭제시 하위의 메뉴 접근 데이터를 변경(등록, 수정, 삭제)한다.
	 * 
	 * @param param Client 전달한 데이터 리스트 객체
	 */

	public Map saveCodeCoListAll() throws Exception {

		int iCnt_grp = 0; // 등록한 그룹코드 건수
		int iCnt_code = 0; // 등록한 세부코드 건수
		int uCnt_grp = 0; // 수정한 그룹코드 건수
		int uCnt_code = 0; // 수정한 세부코드 건수
		int dCnt_grp = 0; // 삭제한 그룹코드 건수
		int dCnt_code = 0; // 삭제한 세부코드 건수
		List paramCodeCo = getSimpleList("dlt_commonCo");
		List paramCodeDtl = getSimpleList("dlt_commonDtl");
		for (int i = 0; i < paramCodeCo.size(); i++) {
			Map dataGrp = (Map) paramCodeCo.get(i);
			String rowStatusGrp = (String) dataGrp.get("rowStatus");
			if (rowStatusGrp.equals("C")) {
				iCnt_grp += commonMapper.insertCommonCo(dataGrp);

				for (int j = 0; j < paramCodeDtl.size(); j++) {
					Map dataGrpCode = (Map) paramCodeDtl.get(j);
					String rowStatusMenuAuth = (String) dataGrpCode.get("rowStatus");
					if (rowStatusMenuAuth.equals("C")) {
						iCnt_code += commonMapper.insertCommonDtl(dataGrpCode);
					}
				}
			} else if (rowStatusGrp.equals("U")) {
				for (int j = 0; j < paramCodeDtl.size(); j++) {
					Map dataGrpCode = (Map) paramCodeDtl.get(j);
					String rowStatusMenuAuth = (String) dataGrpCode.get("rowStatus");
					if (rowStatusMenuAuth.equals("C")) {
						iCnt_code += commonMapper.insertCommonDtl(dataGrpCode);
					} else if (rowStatusMenuAuth.equals("U")) {
						uCnt_code += commonMapper.updateCommonDtl(dataGrpCode);
					} else if (rowStatusMenuAuth.equals("D")) {
						dCnt_code += commonMapper.deleteCommonDtl(dataGrpCode);
					}
				}
				uCnt_grp += commonMapper.updateCommonCo(dataGrp);
			} else if (rowStatusGrp.equals("D")) {
				commonMapper.deleteCommonDtlAll(dataGrp); // 하위 코드 정보는 전체 삭제
				dCnt_grp += commonMapper.deleteCommonCo(dataGrp);
			}
			for (int j = 0; j < paramCodeDtl.size(); j++) {
				Map dataGrpCode = (Map) paramCodeDtl.get(j);
				String rowStatusMenuAuth = (String) dataGrpCode.get("rowStatus");
				if (rowStatusMenuAuth.equals("D")) {
					dCnt_code += commonMapper.deleteCommonDtl(dataGrpCode);
				}
			}
		}
		
		Map result = new HashMap();
		result.put("STATUS", "S");
		result.put("ICNT_GRP", String.valueOf(iCnt_grp));
		result.put("ICNT_CODE", String.valueOf(iCnt_code));
		result.put("UCNT_GRP", String.valueOf(uCnt_grp));
		result.put("UCNT_CODE", String.valueOf(uCnt_code));
		result.put("DCNT_GRP", String.valueOf(dCnt_grp));
		result.put("DCNT_CODE", String.valueOf(dCnt_code));
		return result;
	}

	
}
