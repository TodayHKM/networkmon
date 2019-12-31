package linkcrawler.dao;

import java.util.UUID;

public class uuid {

    //得到32位的uuid
    public static String getUUID32(){
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }
}
