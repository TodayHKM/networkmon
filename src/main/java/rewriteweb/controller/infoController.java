package rewriteweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import rewriteweb.bean.checktask;
import rewriteweb.bean.info;
import rewriteweb.service.checktaskService;
import rewriteweb.service.infoService;

import java.util.List;

    @Controller
    @RequestMapping(value = "/rewriteweb")
    public class infoController {
        @Autowired
        infoService infoService;

        @RequestMapping("/info")
        @ResponseBody
        public List getInfoWithJson(Model model){
            List<info> infos = infoService.getInfo();

            return infos;
        }
    }

