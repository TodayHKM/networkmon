package rewriteweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import rewriteweb.bean.linkchecks;
import rewriteweb.dao.linkchecksMapper;
import rewriteweb.service.linkchecksService;

import java.util.List;

@Controller
@RequestMapping(value = "/rewriteweb")
public class linkchecksController {

    @Autowired
    linkchecksService linkchecksService;

    @RequestMapping("/linkchecks")
    @ResponseBody
    public List getLinkchecksWithJson(){
        List<linkchecks> linkchecks = linkchecksService.getAll();

        return  linkchecks;
    }

    @RequestMapping("/linkchecksinfo")
    @ResponseBody
    public List getlinkchecksinfo(){
        List<linkchecks> linkchecksinfo = linkchecksService.getlinkchecksinfo();

        return  linkchecksinfo;
    }


}
