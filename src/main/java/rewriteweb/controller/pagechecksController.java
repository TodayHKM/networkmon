package rewriteweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import rewriteweb.bean.pagechecks;
import rewriteweb.service.pagechecksService;

import java.util.List;

@Controller
@RequestMapping(value = "/rewriteweb")
public class pagechecksController {

    @Autowired
    pagechecksService pagechecksService;

    @RequestMapping("/pagechecks")
    @ResponseBody
    public List getPagechecksWithJson(){
        List<pagechecks> pagechecks = pagechecksService.getAll();

        return  pagechecks;
    }
}
