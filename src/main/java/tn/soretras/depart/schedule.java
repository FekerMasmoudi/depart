package tn.soretras.depart;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import tn.soretras.depart.service.ExternalApiService;
import tn.soretras.depart.service.dto.ExternalApiDTO;

@Component
@Configuration
public class schedule {

    @Autowired
    private ExternalApiService serviceExternalApi;

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
    DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
    DateTimeFormatter dtf3 = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");

    DateTimeFormatter dtimef = DateTimeFormatter.ofPattern("ddMMyyyy");
    DateTimeFormatter dtimef1 = DateTimeFormatter.ofPattern("MMddyyyy");
    LocalDateTime now = LocalDateTime.now();

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Scheduled(cron = "5 * * * * *", zone = "Africa/Tunis")
    public void testCons() throws Exception {
        String url = "http://197.3.5.106:9280/ords/ti/exploitation/";
        ResponseEntity<String> resultEntityExApi = restTemplate().getForEntity(url + "ExternalApi", String.class, Map.of("id", "1"));
        ObjectMapper objmap = new ObjectMapper(null, null, null);

        //Get api for save ExternalApi into Mongodb
        JsonNode jsexapi = objmap.readTree(resultEntityExApi.getBody());
        List<JsonNode> listjsexapi = jsexapi.findParents("keycomp");
        System.out.println("Hello to cons api ExternalApi : \n" + jsexapi);
        for (int i = 0; i < listjsexapi.size(); i++) {
            ExternalApiDTO objexapi = new ExternalApiDTO();
            objexapi = objmap.treeToValue(listjsexapi.get(i), ExternalApiDTO.class);
            objexapi.setId(listjsexapi.get(i).get("keycomp").asText());
            serviceExternalApi.save(objexapi);
            if (objexapi.getStatus().equals("PUBLISHED") == true) {
                getAllEntity gae = new getAllEntity();
            }
        }
    }
}
