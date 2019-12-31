package rewriteweb.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import rewriteweb.bean.specialurls;
import rewriteweb.bean.specialurlsExample;

public interface specialurlsMapper {
    long countByExample(specialurlsExample example);

    int deleteByExample(specialurlsExample example);

    int insert(specialurls record);

    int insertSelective(specialurls record);

    List<specialurls> selectByExample(specialurlsExample example);

    int updateByExampleSelective(@Param("record") specialurls record, @Param("example") specialurlsExample example);

    int updateByExample(@Param("record") specialurls record, @Param("example") specialurlsExample example);
}