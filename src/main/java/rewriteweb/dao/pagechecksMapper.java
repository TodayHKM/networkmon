package rewriteweb.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import rewriteweb.bean.pagechecks;
import rewriteweb.bean.pagechecksExample;

public interface pagechecksMapper {
    long countByExample(pagechecksExample example);

    int deleteByExample(pagechecksExample example);

    int insert(pagechecks record);

    int insertSelective(pagechecks record);

    List<pagechecks> selectByExample(pagechecksExample example);

    int updateByExampleSelective(@Param("record") pagechecks record, @Param("example") pagechecksExample example);

    int updateByExample(@Param("record") pagechecks record, @Param("example") pagechecksExample example);
}