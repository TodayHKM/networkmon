package rewriteweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rewriteweb.bean.checktask;
import rewriteweb.dao.checktaskMapper;

import java.util.List;


@Service
public class checktaskService {

    @Autowired
    checktaskMapper checktaskMapper;

    public List<checktask> getAll() {
        return checktaskMapper.selectByExample(null);
    }

//    public checktask getchecktask(Integer ID) {
//        checktask checktask = checktaskMapper.se
//    }
}
