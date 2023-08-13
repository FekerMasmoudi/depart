package tn.soretras.depart;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import tn.soretras.depart.domain.Affectagent;
import tn.soretras.depart.domain.Center;
import tn.soretras.depart.repository.AffectagentRepository;
import tn.soretras.depart.repository.CenterRepository;

@Component
@Configuration
public class schedule {

    @Autowired
    private CenterRepository repositoryCenter;

    @Autowired
    private AffectagentRepository repositoryAffectagent;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Scheduled(cron = "1 * * * * *")
    public void testCons() throws Exception {
        ResponseEntity<String> resulEntity = restTemplate()
            .getForEntity("http://197.3.5.106:9280/ords/ti/exploitation/center", String.class, Map.of("id", "1"));
        ResponseEntity<String> resulEntity2 = restTemplate()
            .getForEntity("http://197.3.5.106:9280/ords/ti/exploitation/affecagent", String.class, Map.of("id", "1"));
        ArrayList<Center> listcenter = new ArrayList<Center>();
        ObjectMapper objmap = new ObjectMapper(null, null, null);
        for (int i = 0; i < listcenter.size(); i++) {
            JsonNode js = objmap.readTree(resulEntity.getBody());

            //JsonNode jsnode = js.findParents("deccent").get(0).get("deccent");

            repositoryCenter.save(
                new Center(
                    "1",
                    js.findParents("deccent").get(i).get("deccent").asInt(0),
                    js.findParents("delcent").get(i).get("delcent").asText(null),
                    null,
                    null
                )
            );
            System.out.println("Hello to cons api center : \n" + js);
        }
        JsonNode js1 = objmap.readTree(resulEntity2.getBody());
        //JsonNode jsnode1 = js1.findParents("deccent").get(0).get("deccent");
        repositoryAffectagent.save(
            new Affectagent(
                "1",
                js1.findParents("deccent").get(0).get("deccent").asInt(0),
                js1.findParents("decagenc").get(0).get("decagenc").asInt(0),
                js1.findParents("decserv").get(0).get("decserv").asInt(0),
                js1.findParents("decoper").get(0).get("decoper").asText(null),
                js1.findParents("decsean").get(0).get("decsean").asText(null),
                js1.findParents("cdmois").get(0).get("cdmois").asInt(0),
                js1.findParents("cdsocie").get(0).get("cdsocie").asText("null"),
                js1.findParents("decexer").get(0).get("decexer").asInt(0),
                js1.findParents("matric").get(0).get("matric").asInt(0),
                js1.findParents("matric2").get(0).get("matric2").asInt(0),
                js1.findParents("cdmac").get(0).get("cdmac").asText("null")
            )
        );

        System.out.println("Hello to cons api affectagent : \n" + js1);
        // }
    }
}
