package kr.tracom.brt.domain.VH0602;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VH0602Mapper {
	
	public List<Map> VH0602G0R0(Map param);
	
}
