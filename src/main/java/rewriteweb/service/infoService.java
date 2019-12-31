package rewriteweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rewriteweb.bean.checktask;
import rewriteweb.bean.info;
import rewriteweb.dao.checktaskMapper;
import rewriteweb.dao.infoMapper;

import java.util.List;


@Service
public class infoService {

    @Autowired
    infoMapper infoMapper;

    public List<info> getInfo() {
        return infoMapper.selectinfo();
    }

//    public checktask getchecktask(Integer ID) {
//        checktask checktask = checktaskMapper.se
//    }
}
