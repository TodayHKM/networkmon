package rewriteweb.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import rewriteweb.bean.info;
import rewriteweb.bean.linkchecks;
import rewriteweb.bean.linkchecksExample;

public interface linkchecksMapper {
    long countByExample(linkchecksExample example);

    int deleteByExample(linkchecksExample example);

    int insert(linkchecks record);

    int insertSelective(linkchecks record);

    List<linkchecks> selectByExample(linkchecksExample example);

    int updateByExampleSelective(@Param("record") linkchecks record, @Param("example") linkchecksExample example);

    int updateByExample(@Param("record") linkchecks record, @Param("example") linkchecksExample example);

    List<linkchecks> selectlinkchecksinfo();

}