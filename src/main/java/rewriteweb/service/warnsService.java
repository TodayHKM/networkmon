package rewriteweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rewriteweb.bean.warns;
import rewriteweb.dao.warnsMapper;

import java.util.List;

@Service
public class warnsService {
    @Autowired
    warnsMapper warnsMapper;

    public List<warns>getAll(){
        return warnsMapper.selectByExample(null);
    }

}
