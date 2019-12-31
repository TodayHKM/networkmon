package rewriteweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rewriteweb.bean.task;
import rewriteweb.dao.taskMapper;

import java.util.List;

@Service
public class taskService {
    @Autowired
    taskMapper taskMapper;

    public List<task>getAll(){
        return taskMapper.selectByExample(null);
    }
    public List<task>gettaskcz0(){
        return taskMapper.selectBycz0();
    }
    public void updatetask(String rwinput ,String ljinput){
        taskMapper.updatetask(rwinput,ljinput);
    }
    public  void inserttask(String rwinput ,String ljinput){taskMapper.insert( rwinput , ljinput);}
    public void updatetask0(){
        taskMapper.updatetask0();
    }
    public void updatetask1(){
        taskMapper.updatetask1();
    }
}
