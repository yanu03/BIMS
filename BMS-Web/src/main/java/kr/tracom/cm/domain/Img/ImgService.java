package kr.tracom.cm.domain.Img;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.tracom.cm.support.ServiceSupport;


@Service
public class ImgService extends ServiceSupport {

	@Autowired
	private ImgMapper imgMapper;
	
	public List<Map<String, Object>> selectImgPath() throws Exception {
		
		return imgMapper.selectImgPath();
	}
	
}
