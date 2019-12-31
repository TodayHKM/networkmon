package rewriteweb.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import rewriteweb.bean.task;
import rewriteweb.bean.taskExample;

public interface taskMapper {
    long countByExample(taskExample example);

    int deleteByExample(taskExample example);

    int insert(@Param("rwinput") String rwinput,@Param("ljinput") String ljinput);

    int insertSelective(task record);

    List<task> selectByExample(taskExample example);

    int updateByExampleSelective(@Param("record") task record, @Param("example") taskExample example);

    int updateByExample(@Param("record") task record, @Param("example") taskExample example);
    List<task> selectBycz0();

    int updatetask(@Param("rwinput") String rwinput,@Param("ljinput") String ljinput);

    int updatetask0();
    int updatetask1();
}