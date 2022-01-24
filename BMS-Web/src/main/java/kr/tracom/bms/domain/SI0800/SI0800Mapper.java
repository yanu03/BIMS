package kr.tracom.bms.domain.SI0800;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SI0800Mapper {
	
	public List<Map> SI0800G0R0(Map param);
	public List SI0800SHI0();
	public int SI0800G0I0(Map param);
	public int SI0800G0D0(Map param);
	public int SI0800G0U0(Map param);
}