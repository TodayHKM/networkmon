package rewriteweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class pageController {

    @RequestMapping(value = "/checkLinkPage")
    public String returncheckLink(){
        return "/checkLink";
    }


    @RequestMapping(value = "/contentPage")
    public String returncontent(){
        return "/content";
    }

    @RequestMapping(value = "/ljwb")
    public String returnljwb(){
        return "/ljwb";
    }

}
