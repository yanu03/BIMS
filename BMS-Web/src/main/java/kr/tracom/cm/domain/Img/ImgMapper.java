package kr.tracom.cm.domain.Img;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ImgMapper {
	
	public List<Map<String, Object>> selectImgPath();
	
}
