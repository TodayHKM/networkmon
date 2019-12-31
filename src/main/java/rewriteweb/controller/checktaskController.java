package rewriteweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import rewriteweb.bean.checktask;
import rewriteweb.service.checktaskService;

import java.util.List;

@Controller
@RequestMapping(value = "/rewriteweb")
public class checktaskController {
    @Autowired
    checktaskService checktaskService;

    @RequestMapping("/checktasks")
    @ResponseBody
    public List getChecktaskWithJson(Model model){
        List<checktask> checktasks = checktaskService.getAll();

        return checktasks;
    }
}
