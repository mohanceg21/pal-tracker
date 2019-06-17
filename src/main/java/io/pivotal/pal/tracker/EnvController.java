package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EnvController {

    public String port;
    public String memLimit;
    public String cfInstIndex;
    public String cfInstAdd;

    public EnvController(@Value("${PORT:8080}") String port,@Value("${MEMORY_LIMIT:1G}") String memLimit,@Value("${CF_INSTANCE_INDEX:NOT SET}") String cfInstIndex,@Value("${CF_INSTANCE_ADDR:NOT SET}") String cfInstAdd){
        this.port = port;
        this.memLimit = memLimit;
        this.cfInstIndex = cfInstIndex;
        this.cfInstAdd = cfInstAdd;
    }

    @GetMapping("/env")
    public Map<String, String> getEnv(){
        Map<String, String> envDetails= new HashMap();
        envDetails.put("PORT",port);
        envDetails.put("MEMORY_LIMIT",memLimit);
        envDetails.put("CF_INSTANCE_INDEX",cfInstIndex);
        envDetails.put("CF_INSTANCE_ADDR",cfInstAdd);
        return envDetails;
    }

}
