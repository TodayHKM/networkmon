package rewriteweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rewriteweb.bean.pagechecks;
import rewriteweb.dao.pagechecksMapper;

import java.util.List;

@Service
public class pagechecksService {

    @Autowired
    pagechecksMapper  pagechecksMapper;
    public List<pagechecks>getAll(){
        return pagechecksMapper.selectByExample(null);
    }
}
