package rewriteweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import rewriteweb.bean.specialurls;
import rewriteweb.service.specialurlsService;

import java.util.List;

@Controller
@RequestMapping(value = "/rewriteweb")
public class specialurlsController {
    @Autowired
    specialurlsService specialurlsService;

    @RequestMapping("/specialurls")
    @ResponseBody
    public List getSpecialurlsWithJson(){
        List<specialurls> specialurls = specialurlsService.getAll();
        return specialurls;
    }
}
