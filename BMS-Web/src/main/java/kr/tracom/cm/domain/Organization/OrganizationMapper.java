package kr.tracom.cm.domain.Organization;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrganizationMapper {

	// 조직기본관리 조회
	public List<Map> selectOrganizaion(Map param);

	public List<Map> selectOrganizaionSearchItem();

	public List<Map> selectOrganizaionBasicList(Map param);

	// 조직기본관리 C, U, D
	public int insertOrganizaionBasic(Map param);

	public int updateOrganizaionBasic(Map param);

	public int deleteOrganizaionBasic(Map param);
	
	public Map selectOrganizaionKey();

}
