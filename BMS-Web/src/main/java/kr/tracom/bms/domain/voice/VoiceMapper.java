package kr.tracom.bms.domain.voice;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VoiceMapper {
	int checkVoiceOrganization(String vocId);
	List<Map<String, Object>> selectTtsHelp();
}
