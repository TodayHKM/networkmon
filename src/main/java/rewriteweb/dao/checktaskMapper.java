package rewriteweb.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import rewriteweb.bean.checktask;
import rewriteweb.bean.checktaskExample;

public interface checktaskMapper {
    long countByExample(checktaskExample example);

    int deleteByExample(checktaskExample example);

    int insert(checktask record);

    int insertSelective(checktask record);

    List<checktask> selectByExample(checktaskExample example);

    int updateByExampleSelective(@Param("record") checktask record, @Param("example") checktaskExample example);

    int updateByExample(@Param("record") checktask record, @Param("example") checktaskExample example);
}