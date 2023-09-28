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
import tn.soretras.depart.repository.DeprotatRepository;
import tn.soretras.depart.service.AffectagentService;
import tn.soretras.depart.service.AgenceService;
import tn.soretras.depart.service.BonTvxService;
import tn.soretras.depart.service.CentVehicService;
import tn.soretras.depart.service.CenterService;
import tn.soretras.depart.service.DepartService;
import tn.soretras.depart.service.DeprotatService;
import tn.soretras.depart.service.DisplaybusService;
import tn.soretras.depart.service.DrabsenService;
import tn.soretras.depart.service.DrtypabService;
import tn.soretras.depart.service.GroupeService;
import tn.soretras.depart.service.ItineraireService;
import tn.soretras.depart.service.LigneService;
import tn.soretras.depart.service.MachineService;
import tn.soretras.depart.service.ModifService;
import tn.soretras.depart.service.MotifaService;
import tn.soretras.depart.service.MotifchservService;
import tn.soretras.depart.service.PeriodeService;
import tn.soretras.depart.service.RhAgentService;
import tn.soretras.depart.service.RotRservService;
import tn.soretras.depart.service.ServiceRotService;
import tn.soretras.depart.service.StationService;
import tn.soretras.depart.service.TraficService;
import tn.soretras.depart.service.TypStatService;
import tn.soretras.depart.service.dto.AffectagentDTO;
import tn.soretras.depart.service.dto.AgenceDTO;
import tn.soretras.depart.service.dto.BonTvxDTO;
import tn.soretras.depart.service.dto.CentVehicDTO;
import tn.soretras.depart.service.dto.CenterDTO;
import tn.soretras.depart.service.dto.DepartDTO;
import tn.soretras.depart.service.dto.DeprotatDTO;
import tn.soretras.depart.service.dto.DisplaybusDTO;
import tn.soretras.depart.service.dto.DrabsenDTO;
import tn.soretras.depart.service.dto.DrtypabDTO;
import tn.soretras.depart.service.dto.GroupeDTO;
import tn.soretras.depart.service.dto.ItineraireDTO;
import tn.soretras.depart.service.dto.LigneDTO;
import tn.soretras.depart.service.dto.MachineDTO;
import tn.soretras.depart.service.dto.ModifDTO;
import tn.soretras.depart.service.dto.MotifaDTO;
import tn.soretras.depart.service.dto.MotifchservDTO;
import tn.soretras.depart.service.dto.PeriodeDTO;
import tn.soretras.depart.service.dto.RhAgentDTO;
import tn.soretras.depart.service.dto.RotRservDTO;
import tn.soretras.depart.service.dto.ServiceRotDTO;
import tn.soretras.depart.service.dto.StationDTO;
import tn.soretras.depart.service.dto.TraficDTO;
import tn.soretras.depart.service.dto.TypStatDTO;
import tn.soretras.depart.service.mapper.DeprotatMapper;

@Component
@Configuration
public class getAllEntity {

    @Autowired
    private CenterService serviceCenter;

    @Autowired
    private AffectagentService serviceAffectagent;

    @Autowired
    private GroupeService serviceGroupe;

    @Autowired
    private StationService serviceStation;

    @Autowired
    private PeriodeService servicePeriode;

    @Autowired
    private ItineraireService serviceItineraire;

    @Autowired
    private MotifaService serviceMotifa;

    @Autowired
    private LigneService serviceLigne;

    @Autowired
    private DrtypabService serviceDrtypab;

    @Autowired
    private AgenceService serviceAgence;

    @Autowired
    private RhAgentService serviceRhAgent;

    @Autowired
    private CentVehicService serviceCentVehic;

    @Autowired
    private MachineService serviceMachine;

    @Autowired
    private BonTvxService serviceBonTvx;

    @Autowired
    private TraficService serviceTrafic;

    @Autowired
    private DepartService serviceDepart;

    @Autowired
    private DeprotatService serviceDeprotat;

    @Autowired
    private DeprotatRepository repositoryDeprotat;

    @Autowired
    private DeprotatMapper deprotatMapper;

    @Autowired
    private ModifService serviceModif;

    @Autowired
    private DrabsenService serviceDrabsen;

    @Autowired
    private MotifchservService serviceMotifchserv;

    @Autowired
    private DisplaybusService serviceDisplaybus;

    @Autowired
    private ServiceRotService serviceServiceRot;

    @Autowired
    private TypStatService serviceTypStat;

    @Autowired
    private RotRservService rotrservService;

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
        ResponseEntity<String> resulEntity = restTemplate().getForEntity(url + "center", String.class, Map.of("id", "1"));
        ResponseEntity<String> resulEntity2 = restTemplate().getForEntity(url + "affecagent", String.class, Map.of("id", "1"));
        ResponseEntity<String> resulEntity3 = restTemplate().getForEntity(url + "groupe", String.class, Map.of("id", "1"));
        ResponseEntity<String> resultEntity4 = restTemplate().getForEntity(url + "station", String.class, Map.of("id", "1"));
        ResponseEntity<String> resultEntity5 = restTemplate().getForEntity(url + "periode", String.class, Map.of("id", "1"));
        ResponseEntity<String> resultEntity6 = restTemplate().getForEntity(url + "itineraire", String.class, Map.of("id", "1"));
        ResponseEntity<String> resultEntity7 = restTemplate().getForEntity(url + "motifa", String.class, Map.of("id", "1"));
        ResponseEntity<String> resultEntity8 = restTemplate().getForEntity(url + "ligne", String.class, Map.of("id", "1"));
        ResponseEntity<String> resultEntity9 = restTemplate().getForEntity(url + "drtypab", String.class, Map.of("id", "1"));
        ResponseEntity<String> resultEntity10 = restTemplate().getForEntity(url + "agency", String.class, Map.of("id", "1"));
        ResponseEntity<String> resultEntity11 = restTemplate().getForEntity(url + "rhagent", String.class, Map.of("id", "1"));
        ResponseEntity<String> resultEntity12 = restTemplate().getForEntity(url + "centvehic", String.class, Map.of("id", "1"));
        ResponseEntity<String> resultEntity13 = restTemplate().getForEntity(url + "machine", String.class, Map.of("id", "1"));
        ResponseEntity<String> resultEntity14 = restTemplate().getForEntity(url + "bontvx", String.class, Map.of("id", "1"));
        ResponseEntity<String> resultEntity15 = restTemplate().getForEntity(url + "trafic", String.class, Map.of("id", "1"));
        //System.out.println("****date now***** \n"+dtimef.format(now));

        ResponseEntity<String> resultEntity16 = restTemplate()
            .getForEntity(url + "dapart_voyage/" + dtimef.format(now), String.class, Map.of("id", "1"));
        ResponseEntity<String> resultEntity17 = restTemplate()
            .getForEntity(url + "deprotat_voyage/" + dtimef.format(now), String.class, Map.of("id", "1"));
        ResponseEntity<String> resultEntity18 = restTemplate()
            .getForEntity(url + "modif/" + dtimef.format(now), String.class, Map.of("id", "1"));
        ResponseEntity<String> resultEntity19 = restTemplate()
            .getForEntity(url + "drabsen/" + dtimef1.format(now), String.class, Map.of("id", "1"));
        ResponseEntity<String> resultEntity20 = restTemplate().getForEntity(url + "motifchserv", String.class, Map.of("id", "1"));
        ResponseEntity<String> resultEntity21 = restTemplate().getForEntity(url + "AFFICHEURS_BUS_AR_FR", String.class, Map.of("id", "1"));
        ResponseEntity<String> resultEntity22 = restTemplate().getForEntity(url + "servicerot", String.class, Map.of("id", "1"));
        ResponseEntity<String> resultEntity23 = restTemplate().getForEntity(url + "typstat", String.class, Map.of("id", "1"));
        ResponseEntity<String> resultEntity24 = restTemplate().getForEntity(url + "rotrserv", String.class, Map.of("id", "1"));

        ObjectMapper objmap = new ObjectMapper(null, null, null);

        //Get api for save Center into Mongodb
        JsonNode js19 = objmap.readTree(resulEntity.getBody());
        List<JsonNode> listjs19 = js19.findParents("deccent");
        System.out.println("Hello to cons api Center : \n" + js19);
        for (int c = 0; c < listjs19.size(); c++) {
            CenterDTO obj19 = new CenterDTO();
            obj19 = objmap.treeToValue(listjs19.get(c), CenterDTO.class);
            obj19.setId(listjs19.get(c).get("deccent").asText());
            serviceCenter.save(obj19);
        }
        //Get api for save Drtypab into Mongodb
        JsonNode jsedrty = objmap.readTree(resultEntity9.getBody());
        List<JsonNode> listjsdrty = jsedrty.findParents("cdtypab");
        for (int j = 0; j < listjsdrty.size(); j++) {
            DrtypabDTO objdrty = new DrtypabDTO();
            objdrty = objmap.treeToValue(listjsdrty.get(j), DrtypabDTO.class);
            objdrty.setId(listjsdrty.get(j).get("cdtypab").asText());
            serviceDrtypab.save(objdrty);
        }

        //Get api for save ServiceRot into Mongodb
        JsonNode jsser = objmap.readTree(resultEntity22.getBody());
        List<JsonNode> listjsser = jsser.findParents("deccent");
        for (int k = 0; k < listjsser.size(); k++) {
            ServiceRotDTO objser = new ServiceRotDTO();
            objser = objmap.treeToValue(listjsser.get(k), ServiceRotDTO.class);
            objser.setId(listjsser.get(k).get("id").asText() + "_" + k);
            serviceServiceRot.save(objser);
        }

        //Get api for save Affectagent into Mongodb
        JsonNode jseaffecag = objmap.readTree(resulEntity2.getBody());
        List<JsonNode> listjsaffecag = jseaffecag.findParents("deccent");
        System.out.println("Hello to cons api AffectAgent : \n" + jseaffecag);
        for (int l = 0; l < listjsaffecag.size(); l++) {
            AffectagentDTO objaffecag = new AffectagentDTO();
            objaffecag = objmap.treeToValue(listjsaffecag.get(l), AffectagentDTO.class);
            objaffecag.setId(listjsaffecag.get(l).get("deccent").asText() + l);
            serviceAffectagent.save(objaffecag);
        }

        //Get api for save Ligne into Mongodb
        JsonNode jslig = objmap.readTree(resultEntity8.getBody());
        List<JsonNode> listjslig = jslig.findParents("deccent");
        System.out.println("Hello to cons api Ligne : \n" + jslig);
        for (int m = 0; m < listjslig.size(); m++) {
            LigneDTO objlig = new LigneDTO();
            objlig = objmap.treeToValue(listjslig.get(m), LigneDTO.class);
            objlig.setId(listjslig.get(m).get("id").asText());
            serviceLigne.save(objlig);
        }

        //Get api for save Groupe into Mongodb
        JsonNode jsgrp = objmap.readTree(resulEntity3.getBody());
        List<JsonNode> listjsgrp = jsgrp.findParents("deccent");
        System.out.println("Hello to cons api Groupe : \n" + jsgrp);
        for (int n = 0; n < listjsgrp.size(); n++) {
            GroupeDTO objgrp = new GroupeDTO();
            objgrp = objmap.treeToValue(listjsgrp.get(n), GroupeDTO.class);
            objgrp.setId(listjsgrp.get(n).get("deccent").asText() + n);
            serviceGroupe.save(objgrp);
        }

        //Get api for save Itineraire into Mongodb
        JsonNode jsiti = objmap.readTree(resultEntity6.getBody());
        List<JsonNode> listjsiti = jsiti.findParents("deccent");
        System.out.println("Hello to cons api Itineraire : \n" + jsiti);
        for (int it = 0; it < listjsiti.size(); it++) {
            ItineraireDTO objiti = new ItineraireDTO();
            objiti = objmap.treeToValue(listjsiti.get(it), ItineraireDTO.class);
            objiti.setId(listjsiti.get(it).get("deccent").asText() + it);
            serviceItineraire.save(objiti);
        }

        //Get api for save Station into Mongodb
        JsonNode jssta = objmap.readTree(resultEntity4.getBody());
        List<JsonNode> listjssta = jssta.findParents("decstat");
        System.out.println("Hello to cons api Station : \n" + jssta);
        for (int st = 0; st < listjssta.size(); st++) {
            StationDTO objsta = new StationDTO();
            objsta = objmap.treeToValue(listjssta.get(st), StationDTO.class);
            objsta.setId(listjssta.get(st).get("decstat").asText());
            serviceStation.save(objsta);
        }

        //Get api for save Periode into Mongodb
        JsonNode jsper = objmap.readTree(resultEntity5.getBody());
        List<JsonNode> listjsper = jsper.findParents("decoper");
        System.out.println("Hello to cons api Periode : \n" + jsper);
        for (int p = 0; p < listjsper.size(); p++) {
            PeriodeDTO objper = new PeriodeDTO();
            objper = objmap.treeToValue(listjsper.get(p), PeriodeDTO.class);
            objper.setId(listjsper.get(p).get("decoper").asText());
            servicePeriode.save(objper);
        }

        //Get api for save Motifa into Mongodb
        JsonNode jsmot = objmap.readTree(resultEntity7.getBody());
        List<JsonNode> listjsmot = jsmot.findParents("id");
        System.out.println("Hello to cons api Motifa : \n" + jsmot);
        for (int mt = 0; mt < listjsmot.size(); mt++) {
            MotifaDTO objmot = new MotifaDTO();
            objmot = objmap.treeToValue(listjsmot.get(mt), MotifaDTO.class);
            objmot.setId(listjsmot.get(mt).get("id").asText());
            serviceMotifa.save(objmot);
        }

        //Get api for save Agence into Mongodb
        JsonNode jsage = objmap.readTree(resultEntity10.getBody());
        List<JsonNode> listjsage = jsage.findParents("id");
        System.out.println("Hello to cons api Agence : \n" + jsage);
        for (int ag = 0; ag < listjsage.size(); ag++) {
            AgenceDTO objage = new AgenceDTO();
            objage = objmap.treeToValue(listjsage.get(ag), AgenceDTO.class);
            objage.setId(listjsage.get(ag).get("id").asText());
            serviceAgence.save(objage);
        }

        //Get api for save RhAgent into Mongodb
        JsonNode jsrha = objmap.readTree(resultEntity11.getBody());
        List<JsonNode> listjsrha = jsrha.findParents("id");
        System.out.println("Hello to cons api RhAgent : \n" + jsrha);
        for (int rh = 0; rh < listjsrha.size(); rh++) {
            RhAgentDTO objrha = new RhAgentDTO();
            objrha = objmap.treeToValue(listjsrha.get(rh), RhAgentDTO.class);
            objrha.setId(listjsrha.get(rh).get("id").asText());
            serviceRhAgent.save(objrha);
        }

        //Get api for save CentVehic into Mongodb
        JsonNode jscentv = objmap.readTree(resultEntity12.getBody());
        List<JsonNode> listjscentv = jscentv.findParents("cdmac");
        System.out.println("Hello to cons api CentVehic : \n" + jscentv);
        for (int cv = 0; cv < listjscentv.size(); cv++) {
            CentVehicDTO objcentv = new CentVehicDTO();
            objcentv = objmap.treeToValue(listjscentv.get(cv), CentVehicDTO.class);
            objcentv.setId(listjscentv.get(cv).get("cdmac").asText() + "_" + cv);
            serviceCentVehic.save(objcentv);
        }

        //Get api for save Machine into Mongodb
        JsonNode jsmach = objmap.readTree(resultEntity13.getBody());
        List<JsonNode> listjsmach = jsmach.findParents("cdmac");
        System.out.println("Hello to cons api Machine : \n" + jsmach);
        for (int ma = 0; ma < listjsmach.size(); ma++) {
            MachineDTO objmach = new MachineDTO();
            objmach = objmap.treeToValue(listjsmach.get(ma), MachineDTO.class);
            objmach.setId(listjsmach.get(ma).get("cdmac").asText() + "_" + ma);
            serviceMachine.save(objmach);
        }

        //Get api for save BonTvx into Mongodb
        JsonNode jsbontvx = objmap.readTree(resultEntity14.getBody());
        List<JsonNode> listjsbontvx = jsbontvx.findParents("num_bt");
        System.out.println("Hello to cons api BonTvx : \n" + jsbontvx);
        for (int b = 0; b < listjsbontvx.size(); b++) {
            BonTvxDTO objbontvx = new BonTvxDTO();
            objbontvx = objmap.treeToValue(listjsbontvx.get(b), BonTvxDTO.class);
            objbontvx.setId(listjsbontvx.get(b).get("num_bt").asText() + "_" + b);
            serviceBonTvx.save(objbontvx);
        }

        //Get api for save Trafic into Mongodb
        JsonNode jstrafic = objmap.readTree(resultEntity15.getBody());
        List<JsonNode> listjstrafic = jstrafic.findParents("deccent");
        System.out.println("Hello to cons api Trafic : \n" + jstrafic);
        for (int tr = 0; tr < listjstrafic.size(); tr++) {
            TraficDTO objtrafic = new TraficDTO();
            objtrafic = objmap.treeToValue(listjstrafic.get(tr), TraficDTO.class);
            objtrafic.setId(listjstrafic.get(tr).get("deccent").asText() + "_" + tr);
            serviceTrafic.save(objtrafic);
        }

        //Get api for save Deprotat into Mongodb
        JsonNode jsdeprotat = objmap.readTree(resultEntity17.getBody());
        List<JsonNode> listjsdeprotat = jsdeprotat.findParents("id");
        System.out.println("Hello to cons api Deprotat : \n" + jsdeprotat);
        for (int dep = 0; dep < listjsdeprotat.size(); dep++) {
            DeprotatDTO objdeprotat = new DeprotatDTO();
            objdeprotat = objmap.treeToValue(listjsdeprotat.get(dep), DeprotatDTO.class);
            objdeprotat.setId(listjsdeprotat.get(dep).get("id").asText());
            serviceDeprotat.save(objdeprotat);
        }

        //Get api for save Depart into Mongodb
        JsonNode jsdepart = objmap.readTree(resultEntity16.getBody());
        List<JsonNode> listjsdepart = jsdepart.findParents("id");
        System.out.println("Hello to cons api Depart : \n" + jsdepart);
        for (int de = 0; de < listjsdepart.size(); de++) {
            DepartDTO objdepart = new DepartDTO();
            objdepart = objmap.treeToValue(listjsdepart.get(de), DepartDTO.class);
            objdepart.setId_apex(listjsdepart.get(de).get("id").asInt());
            //objdepart.setDeprotats(repositoryDeprotat.findById(listjsdepart.get(de).get("id_apex").asText()).map(deprotatMapper::toDto).orElse(null));
            serviceDepart.save(objdepart);
        }

        //Get api for save Modif into Mongodb
        JsonNode jsmod = objmap.readTree(resultEntity18.getBody());
        List<JsonNode> listjsmod = jsmod.findParents("deccent");
        System.out.println("Hello to cons api Modif : \n" + jsmod);
        for (int mo = 0; mo < listjsmod.size(); mo++) {
            ModifDTO objmod = new ModifDTO();
            objmod = objmap.treeToValue(listjsmod.get(mo), ModifDTO.class);
            objmod.setId(listjsmod.get(mo).get("deccent").asText() + "_" + mo);
            serviceModif.save(objmod);
        }

        //Get api for save Drabsen into Mongodb
        JsonNode jsdra = objmap.readTree(resultEntity19.getBody());
        List<JsonNode> listjsdra = jsdra.findParents("cdtypab");
        System.out.println("Hello to cons api Drabsen : \n" + jsdra);
        for (int dr = 0; dr < listjsdra.size(); dr++) {
            DrabsenDTO objdra = new DrabsenDTO();
            objdra = objmap.treeToValue(listjsdra.get(dr), DrabsenDTO.class);
            objdra.setId(listjsdra.get(dr).get("cdtypab").asText() + "_" + dr);
            serviceDrabsen.save(objdra);
        }

        //Get api for save Motifchserv into Mongodb
        JsonNode jsmotchserv = objmap.readTree(resultEntity20.getBody());
        List<JsonNode> listjsmotchserv = jsmotchserv.findParents("id");
        System.out.println("Hello to cons api Motifchserv : \n" + jsmotchserv);
        for (int mc = 0; mc < listjsmotchserv.size(); mc++) {
            MotifchservDTO objmotchserv = new MotifchservDTO();
            objmotchserv = objmap.treeToValue(listjsmotchserv.get(mc), MotifchservDTO.class);
            objmotchserv.setId(listjsmotchserv.get(mc).get("id").asText());
            serviceMotifchserv.save(objmotchserv);
        }

        //Get api for save Afficheur_Bus_Ar_Fr into Mongodb
        JsonNode jsaffbus = objmap.readTree(resultEntity21.getBody());
        List<JsonNode> listjsaffbus = jsaffbus.findParents("num_appel");
        System.out.println("Hello to cons api Afficheur_Bus_Ar_Fr : \n" + jsaffbus);
        for (int af = 0; af < listjsaffbus.size(); af++) {
            DisplaybusDTO objaffbus = new DisplaybusDTO();
            objaffbus = objmap.treeToValue(listjsaffbus.get(af), DisplaybusDTO.class);
            objaffbus.setId(listjsaffbus.get(af).get("num_appel").asText());
            serviceDisplaybus.save(objaffbus);
        }

        //Get api for save TypStat into Mongodb
        JsonNode jstypstat = objmap.readTree(resultEntity23.getBody());
        List<JsonNode> listjstypstat = jstypstat.findParents("dectyst");
        System.out.println("Hello to cons api TypStat : \n" + jstypstat);
        for (int ty = 0; ty < listjstypstat.size(); ty++) {
            TypStatDTO objtypstat = new TypStatDTO();
            objtypstat = objmap.treeToValue(listjstypstat.get(ty), TypStatDTO.class);
            objtypstat.setId(listjstypstat.get(ty).get("dectyst").asText());
            serviceTypStat.save(objtypstat);
        }

        //Get api for save RotRserv into Mongodb
        JsonNode jsrotrserv = objmap.readTree(resultEntity24.getBody());
        List<JsonNode> listjsrotrserv = jsrotrserv.findParents("deccent");
        System.out.println("Hello to cons api RotRserv : \n" + jsrotrserv);
        for (int rs = 0; rs < listjsrotrserv.size(); rs++) {
            RotRservDTO objrorserv = new RotRservDTO();
            objrorserv = objmap.treeToValue(listjsrotrserv.get(rs), RotRservDTO.class);
            objrorserv.setId(listjsrotrserv.get(rs).get("id").asText() + "_" + rs);
            rotrservService.save(objrorserv);
        }
    }
}
