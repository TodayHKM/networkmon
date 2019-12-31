package rewriteweb.dao;

import org.apache.ibatis.annotations.Param;
import rewriteweb.bean.info;
import rewriteweb.bean.checktaskExample;

import java.util.List;

public interface infoMapper {


    List<info> selectinfo();

    }