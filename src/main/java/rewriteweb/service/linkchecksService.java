package rewriteweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rewriteweb.bean.info;
import rewriteweb.bean.linkchecks;
import rewriteweb.dao.linkchecksMapper;

import java.util.List;

@Service
public class linkchecksService {

    @Autowired
    linkchecksMapper linkchecksMapper;

    public List<linkchecks>getAll(){
        return linkchecksMapper.selectByExample(null);
    }
    public List<linkchecks> getlinkchecksinfo() {
        return linkchecksMapper.selectlinkchecksinfo();
    }

}
