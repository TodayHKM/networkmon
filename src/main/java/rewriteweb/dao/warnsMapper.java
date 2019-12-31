package rewriteweb.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import rewriteweb.bean.warns;
import rewriteweb.bean.warnsExample;

public interface warnsMapper {
    long countByExample(warnsExample example);

    int deleteByExample(warnsExample example);

    int insert(warns record);

    int insertSelective(warns record);

    List<warns> selectByExample(warnsExample example);

    int updateByExampleSelective(@Param("record") warns record, @Param("example") warnsExample example);

    int updateByExample(@Param("record") warns record, @Param("example") warnsExample example);
}