package rewriteweb.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import rewriteweb.bean.warns;
import rewriteweb.service.warnsService;

import java.util.List;

@Controller
@RequestMapping(value = "/rewriteweb")
public class warnsController {
    @Autowired
    warnsService warnsService;

    @RequestMapping("/warns")
    @ResponseBody
    public List getWarnsWithJson(){
        List<warns>warns = warnsService.getAll();
        return warns;
    }

}
