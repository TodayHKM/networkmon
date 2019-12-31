package rewriteweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import rewriteweb.bean.specialurls;
import rewriteweb.bean.task;
import rewriteweb.service.taskService;

import java.util.List;

@Controller
@RequestMapping(value = "/rewriteweb")
public class taskController {
    @Autowired
    taskService taskService;

    @RequestMapping("/tasks")
    @ResponseBody
    public List getTaskWithJson(){
        List<task>task = taskService.getAll();
        return  task;
    }
    @RequestMapping("/taskscz0")
    @ResponseBody
    public List getTaskcz0WithJson(){
        List<task>task = taskService.gettaskcz0();
        return  task;
    }
    @RequestMapping("/updateTask")
    @ResponseBody
    public void updatetask(String rwinput,String ljinput){
        taskService.updatetask(rwinput,ljinput);
    }

    @RequestMapping("/insertTask")
    @ResponseBody
    public void inserttask(String rwinput,String ljinput){
        System.out.println(rwinput);
        System.out.println(ljinput);
        taskService.inserttask(rwinput,ljinput);
    }

    @RequestMapping("/updateTask0")
    @ResponseBody
    public void updatetask0(){
        taskService.updatetask0();
    }

    @RequestMapping("/updateTask1")
    @ResponseBody
    public void updatetask1(){
        taskService.updatetask1();
    }

}
