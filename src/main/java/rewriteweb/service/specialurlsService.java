package rewriteweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rewriteweb.bean.specialurls;
import rewriteweb.dao.specialurlsMapper;

import java.util.List;

@Service
public class specialurlsService {
    @Autowired
    specialurlsMapper specialurlsMapper;

    public List<specialurls>getAll(){
        return specialurlsMapper.selectByExample(null);
    }

}
