package tn.soretras.depart;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
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
import tn.soretras.depart.domain.Affectagent;
import tn.soretras.depart.domain.Agence;
import tn.soretras.depart.domain.BonTvx;
import tn.soretras.depart.domain.CentVehic;
import tn.soretras.depart.domain.Center;
import tn.soretras.depart.domain.Depart;
import tn.soretras.depart.domain.Deprotat;
import tn.soretras.depart.domain.Displaybus;
import tn.soretras.depart.domain.Drabsen;
import tn.soretras.depart.domain.Drtypab;
import tn.soretras.depart.domain.ExternalApi;
import tn.soretras.depart.domain.Groupe;
import tn.soretras.depart.domain.Itineraire;
import tn.soretras.depart.domain.Ligne;
import tn.soretras.depart.domain.Machine;
import tn.soretras.depart.domain.Modif;
import tn.soretras.depart.domain.Motifa;
import tn.soretras.depart.domain.Motifchserv;
import tn.soretras.depart.domain.Periode;
import tn.soretras.depart.domain.RhAgent;
import tn.soretras.depart.domain.Station;
import tn.soretras.depart.domain.Trafic;
import tn.soretras.depart.repository.AffectagentRepository;
import tn.soretras.depart.repository.AgenceRepository;
import tn.soretras.depart.repository.BonTvxRepository;
import tn.soretras.depart.repository.CentVehicRepository;
import tn.soretras.depart.repository.CenterRepository;
import tn.soretras.depart.repository.DepartRepository;
import tn.soretras.depart.repository.DeprotatRepository;
import tn.soretras.depart.repository.DisplaybusRepository;
import tn.soretras.depart.repository.DrabsenRepository;
import tn.soretras.depart.repository.DrtypabRepository;
import tn.soretras.depart.repository.ExternalApiRepository;
import tn.soretras.depart.repository.GroupeRepository;
import tn.soretras.depart.repository.ItineraireRepository;
import tn.soretras.depart.repository.LigneRepository;
import tn.soretras.depart.repository.MachineRepository;
import tn.soretras.depart.repository.ModifRepository;
import tn.soretras.depart.repository.MotifaRepository;
import tn.soretras.depart.repository.MotifchservRepository;
import tn.soretras.depart.repository.PeriodeRepository;
import tn.soretras.depart.repository.RhAgentRepository;
import tn.soretras.depart.repository.StationRepository;
import tn.soretras.depart.repository.TraficRepository;

@Component
@Configuration
public class schedule {

    @Autowired
    private CenterRepository repositoryCenter;

    @Autowired
    private AffectagentRepository repositoryAffectagent;

    @Autowired
    private GroupeRepository repositoryGroupe;

    @Autowired
    private StationRepository repositoryStation;

    @Autowired
    private PeriodeRepository repositoryPeriode;

    @Autowired
    private ItineraireRepository repositoryItineraire;

    @Autowired
    private MotifaRepository repositoryMotifa;

    @Autowired
    private LigneRepository repositoryLigne;

    @Autowired
    private DrtypabRepository repositoryDrtypab;

    @Autowired
    private AgenceRepository repositoryAgence;

    @Autowired
    private RhAgentRepository repositoryRhAgent;

    @Autowired
    private CentVehicRepository repositoryCentVehic;

    @Autowired
    private MachineRepository repositoryMachine;

    @Autowired
    private BonTvxRepository repositoryBonTvx;

    @Autowired
    private TraficRepository repositoryTrafic;

    @Autowired
    private DepartRepository repositoryDepart;

    @Autowired
    private DeprotatRepository repositoryDeprotat;

    @Autowired
    private ModifRepository repositoryModif;

    @Autowired
    private DrabsenRepository repositoryDrabsen;

    @Autowired
    private ExternalApiRepository repositoryExternalApi;

    @Autowired
    private MotifchservRepository repositoryMotifchserv;

    @Autowired
    private DisplaybusRepository repositoryDisplaybus;

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
        ResponseEntity<String> resultEntityExApi = restTemplate().getForEntity(url + "ExternalApi", String.class, Map.of("id", "1"));
        ResponseEntity<String> resultEntity20 = restTemplate().getForEntity(url + "motifchserv", String.class, Map.of("id", "1"));
        ResponseEntity<String> resultEntity21 = restTemplate().getForEntity(url + "AFFICHEURS_BUS_AR_FR", String.class, Map.of("id", "1"));

        ObjectMapper objmap = new ObjectMapper(null, null, null);

        JsonNode js = objmap.readTree(resulEntity.getBody());
        List<JsonNode> listjs = js.findParents("deccent");
        System.out.println("Hello to cons api center : \n" + js);

        for (int i = 0; i < listjs.size(); i++) {
            //JsonNode jsnode = js.findParents("deccent").get(0).get("deccent");

            repositoryCenter.save(
                new Center(
                    Integer.toString(i, i + 1),
                    listjs.get(i).get("deccent").asInt(0),
                    listjs.get(i).get("delcent").asText(null),
                    listjs.get(i).get("deadrce").asText(null),
                    listjs.get(i).get("deobser").asText(null)
                )
            );
            //listcenter.add(e);
        }
        JsonNode js1 = objmap.readTree(resulEntity2.getBody());
        List<JsonNode> listjs1 = js1.findParents("deccent");
        //JsonNode jsnode1 = js1.findParents("deccent").get(0).get("deccent");
        System.out.println("Hello to cons api affectagent : \n" + js1);
        for (int i = 0; i < listjs1.size(); i++) {
            repositoryAffectagent.save(
                new Affectagent(
                    Integer.toString(i, i + 1),
                    listjs1.get(i).get("deccent").asInt(0),
                    listjs1.get(i).get("decagenc").asInt(0),
                    listjs1.get(i).get("decserv").asInt(0),
                    listjs1.get(i).get("decoper").asText(null),
                    listjs1.get(i).get("decsean").asText(null),
                    listjs1.get(i).get("cdmois").asInt(0),
                    listjs1.get(i).get("cdsocie").asText("null"),
                    listjs1.get(i).get("decexer").asInt(0),
                    listjs1.get(i).get("matric").asInt(0),
                    listjs1.get(i).get("matric2").asInt(0),
                    listjs1.get(i).get("cdmac").asText("null")
                )
            );
            //System.out.println("val i : \n" + test1 .size());

        }
        JsonNode js2 = objmap.readTree(resulEntity3.getBody());
        List<JsonNode> listjs2 = js2.findParents("decagenc");
        System.out.println("Hello to cons api groupe : \n" + js2);
        for (int i = 0; i < listjs2.size(); i++) {
            repositoryGroupe.save(
                new Groupe(
                    Integer.toString(i, i + 1),
                    listjs2.get(i).get("deccent").asInt(i),
                    listjs2.get(i).get("decagenc").asInt(i),
                    listjs2.get(i).get("codgrp").asInt(i),
                    listjs2.get(i).get("libgrp").asText(null),
                    listjs2.get(i).get("dectyli").asText(null),
                    listjs2.get(i).get("libgrp_fr").asText(null)
                )
            );
        }
        JsonNode js3 = objmap.readTree(resultEntity4.getBody());
        List<JsonNode> listjs3 = js3.findParents("decstat");
        System.out.println("Hello to cons api station : \n" + js3);
        for (int i = 0; i < listjs3.size(); i++) {
            repositoryStation.save(
                new Station(
                    Integer.toString(i, i + 1),
                    listjs3.get(i).get("decstat").asText(null),
                    listjs3.get(i).get("dectyst").asText(null),
                    listjs3.get(i).get("decrout").asText(null),
                    listjs3.get(i).get("delstat").asText(null),
                    listjs3.get(i).get("delstat_fr").asText(null),
                    listjs3.get(i).get("lattitude").asText(null),
                    listjs3.get(i).get("longitude").asText(null),
                    listjs3.get(i).get("valide").asText(null)
                )
            );
        }

        JsonNode js4 = objmap.readTree(resultEntity5.getBody());
        List<JsonNode> listjs4 = js4.findParents("decoper");
        System.out.println("Hello to cons api periode : \n" + js4);

        for (int i = 0; i < listjs4.size(); i++) {
            repositoryPeriode.save(
                new Periode(
                    Integer.toString(i, i + 1),
                    listjs4.get(i).get("decoper").asText(null),
                    listjs4.get(i).get("denoper").asText(null),
                    listjs4.get(i).get("primaire").asText(null),
                    listjs4.get(i).get("start_date").asText(null),
                    listjs4.get(i).get("end_date").asText(null)
                )
            );
        }

        JsonNode js5 = objmap.readTree(resultEntity6.getBody());
        List<JsonNode> listjs5 = js5.findParents("deccent");
        System.out.println("Hello to cons api itineraire : \n" + js5);

        for (int i = 0; i < listjs5.size(); i++) {
            repositoryItineraire.save(
                new Itineraire(
                    Integer.toString(i, i + 1),
                    listjs5.get(i).get("deccent").asInt(i),
                    listjs5.get(i).get("decagenc").asInt(i),
                    listjs5.get(i).get("denumli").asText(null),
                    listjs5.get(i).get("decstat").asText(null),
                    listjs5.get(i).get("denumlg").asInt(i),
                    listjs5.get(i).get("dekmsta").asDouble(i),
                    listjs5.get(i).get("dedurtr").asInt(i),
                    listjs5.get(i).get("deescale").asInt(i),
                    listjs5.get(i).get("embra").asText(null),
                    listjs5.get(i).get("section").asInt(i),
                    listjs5.get(i).get("sens").asText(null),
                    listjs5.get(i).get("dectyst").asText(null)
                )
            );
        }

        JsonNode js6 = objmap.readTree(resultEntity7.getBody());
        List<JsonNode> listjs6 = js6.findParents("decmotif");
        System.out.println("Hello to cons api motifa : \n" + js6);

        for (int i = 0; i < listjs6.size(); i++) {
            repositoryMotifa.save(
                new Motifa(Integer.toString(i, i + 1), listjs6.get(i).get("decmotif").asInt(i), listjs6.get(i).get("libmotif").asText(null))
            );
        }

        JsonNode js7 = objmap.readTree(resultEntity8.getBody());
        List<JsonNode> listjs7 = js7.findParents("deccent");
        System.out.println("Hello to cons api ligne : \n" + js7);

        for (int i = 0; i < listjs7.size(); i++) {
            repositoryLigne.save(
                new Ligne(
                    Integer.toString(i, i + 1),
                    listjs7.get(i).get("deccent").asInt(0),
                    listjs7.get(i).get("decagenc").asInt(0),
                    listjs7.get(i).get("denumli").asText(null),
                    listjs7.get(i).get("dectyli").asText(null),
                    listjs7.get(i).get("dectyta").asText(null),
                    listjs7.get(i).get("denomli").asText(null),
                    listjs7.get(i).get("dectyeq").asText(null),
                    listjs7.get(i).get("denbrkm").asDouble(0),
                    listjs7.get(i).get("detparc").asText(null),
                    listjs7.get(i).get("dedural").asInt(0),
                    listjs7.get(i).get("dedurrt").asInt(0),
                    listjs7.get(i).get("detrjva").asInt(0),
                    listjs7.get(i).get("detrjvr").asInt(0),
                    listjs7.get(i).get("depiste").asDouble(0),
                    listjs7.get(i).get("stat_lig").asText(null),
                    listjs7.get(i).get("lig").asText(null),
                    listjs7.get(i).get("lig1").asInt(0),
                    listjs7.get(i).get("valide").asText(null),
                    listjs7.get(i).get("denumli2").asText(null),
                    listjs7.get(i).get("kml").binaryValue(),
                    null,
                    listjs7.get(i).get("description").asText(null),
                    listjs7.get(i).get("mim_type").asText(null),
                    listjs7.get(i).get("file_name").asText(null),
                    listjs7.get(i).get("char_set").asText(null),
                    LocalDate.parse(listjs7.get(i).get("last_update").asText("2033-12-16T23:00:00Z"), dtf)
                )
            );
        }

        JsonNode js8 = objmap.readTree(resultEntity9.getBody());
        List<JsonNode> listjs8 = js8.findParents("cdtypab");
        System.out.println("Hello to cons api drtypab : \n" + js8);

        for (int i = 0; i < listjs8.size(); i++) {
            repositoryDrtypab.save(
                new Drtypab(
                    Integer.toString(i, i + 1),
                    listjs8.get(i).get("cdtypab").asText(null),
                    listjs8.get(i).get("lbtypab").asText(null),
                    listjs8.get(i).get("dabs_jt").asText(null),
                    listjs8.get(i).get("dabs_jp").asText(null)
                )
            );
        }

        JsonNode js9 = objmap.readTree(resultEntity10.getBody());
        List<JsonNode> listjs9 = js9.findParents("deccent");
        System.out.println("Hello to cons api agency : \n" + js9);

        for (int i = 0; i < listjs9.size(); i++) {
            repositoryAgence.save(
                new Agence(
                    Integer.toString(i, i + 1),
                    listjs9.get(i).get("deccent").asInt(i),
                    listjs9.get(i).get("decagenc").asInt(i),
                    listjs9.get(i).get("delagenc").asText(null),
                    listjs9.get(i).get("default_agenc").asText(null)
                )
            );
        }

        JsonNode js10 = objmap.readTree(resultEntity11.getBody());
        List<JsonNode> listjs10 = js10.findParents("matric");
        System.out.println("Hello to cons api rhagent : \n" + js10);

        for (int i = 0; i < listjs10.size(); i++) {
            repositoryRhAgent.save(
                new RhAgent(
                    Integer.toString(i, i + 1),
                    listjs10.get(i).get("matric").asInt(i),
                    listjs10.get(i).get("decjour").asText(null),
                    LocalDate.parse(listjs10.get(i).get("dat_eff_rh").asText("2033-12-16T23:00:00Z"), dtf),
                    listjs10.get(i).get("decoper").asText(null),
                    listjs10.get(i).get("deccent").asInt(0),
                    listjs10.get(i).get("decagenc").asInt(0)
                )
            );
        }

        JsonNode js11 = objmap.readTree(resultEntity12.getBody());
        List<JsonNode> listjs11 = js11.findParents("cdmac");
        System.out.println("Hello to cons api centvehic : \n" + js11);

        for (int i = 0; i < listjs11.size(); i++) {
            repositoryCentVehic.save(
                new CentVehic(
                    Integer.toString(i, i + 1),
                    listjs11.get(i).get("cdmac").asText(null),
                    LocalDate.parse(listjs11.get(i).get("dat_eff").asText(null), dtf),
                    listjs11.get(i).get("deccent").asInt(0),
                    listjs11.get(i).get("decagenc").asInt(0)
                )
            );
        }

        JsonNode js12 = objmap.readTree(resultEntity13.getBody());
        List<JsonNode> listjs12 = js12.findParents("cdmac");
        System.out.println("Hello to cons api machine : \n" + js12);

        for (int i = 0; i < listjs12.size(); i++) {
            repositoryMachine.save(
                new Machine(
                    Integer.toString(i, i + 1),
                    listjs12.get(i).get("cdmac").asText(null),
                    listjs12.get(i).get("cdmod").asText(null),
                    listjs12.get(i).get("cdmarque").asText(null),
                    listjs12.get(i).get("lbmac").asText(null),
                    listjs12.get(i).get("ref_mac").asText(null),
                    listjs12.get(i).get("serie").asText(null),
                    LocalDate.parse(listjs12.get(i).get("dat_fab").asText("2033-12-16T23:00:00Z"), dtf),
                    LocalDate.parse(listjs12.get(i).get("dat_acq").asText("2033-12-16T23:00:00Z"), dtf),
                    LocalDate.parse(listjs12.get(i).get("dat_mes").asText("2033-12-16T23:00:00Z"), dtf),
                    listjs12.get(i).get("val_acq").asInt(0),
                    listjs12.get(i).get("obs").asText(null),
                    listjs12.get(i).get("numplan").asText(null),
                    listjs12.get(i).get("cdlipro").asText(null),
                    listjs12.get(i).get("immat").asText(null),
                    listjs12.get(i).get("marque").asText(null),
                    listjs12.get(i).get("type_v").asText(null),
                    listjs12.get(i).get("num_ser").asText(null),
                    listjs12.get(i).get("puiss").asText(null),
                    listjs12.get(i).get("nrj").asText(null),
                    listjs12.get(i).get("genre").asText(null),
                    listjs12.get(i).get("cylind").asInt(0),
                    listjs12.get(i).get("pds_vid").asInt(0),
                    listjs12.get(i).get("charge").asInt(0),
                    listjs12.get(i).get("plc_ass").asInt(0),
                    listjs12.get(i).get("plc_deb").asInt(0),
                    listjs12.get(i).get("cpt").asInt(0),
                    listjs12.get(i).get("cpt_mnt").asInt(0),
                    listjs12.get(i).get("actif").asInt(0),
                    LocalDate.parse(listjs12.get(i).get("dat_act").asText("2033-12-16T23:00:00Z"), dtf),
                    listjs12.get(i).get("cdcatvh").asText(null),
                    listjs12.get(i).get("taux").asInt(0),
                    listjs12.get(i).get("km_moy").asInt(0),
                    listjs12.get(i).get("codstat").asInt(0),
                    listjs12.get(i).get("edition").asText(null),
                    listjs12.get(i).get("val_assur").asInt(0),
                    listjs12.get(i).get("val_amort").asInt(0),
                    listjs12.get(i).get("consom_model").asInt(0),
                    listjs12.get(i).get("decetat").asText(null),
                    listjs12.get(i).get("codtypvoit").asText(null),
                    listjs12.get(i).get("cdtyp").asText(null),
                    listjs12.get(i).get("cdnat").asInt(0),
                    listjs12.get(i).get("typbv").asText(null),
                    listjs12.get(i).get("cdtypbv").asText(null),
                    listjs12.get(i).get("pneu").asText(null),
                    listjs12.get(i).get("gps").asText(null),
                    listjs12.get(i).get("marque_bv").asText(null),
                    listjs12.get(i).get("typ_boite").asText(null)
                )
            );
        }

        JsonNode js13 = objmap.readTree(resultEntity14.getBody());
        List<JsonNode> listjs13 = js13.findParents("cdexerc");
        System.out.println("Hello to cons api bontvx : \n" + js13);

        for (int i = 0; i < listjs13.size(); i++) {
            repositoryBonTvx.save(
                new BonTvx(
                    Integer.toString(i, i + 1),
                    listjs13.get(i).get("cdexerc").asInt(0),
                    listjs13.get(i).get("num_bt").asInt(0),
                    listjs13.get(i).get("cdtier").asText(null),
                    listjs13.get(i).get("cdmac").asText(null),
                    listjs13.get(i).get("mac_cdmac").asText(null),
                    listjs13.get(i).get("cdserv").asText(null),
                    listjs13.get(i).get("decagen").asInt(0),
                    listjs13.get(i).get("dra_decagen").asInt(0),
                    listjs13.get(i).get("cdorga").asText(null),
                    listjs13.get(i).get("ref_bt").asText(null),
                    LocalDate.parse(listjs13.get(i).get("dat_bt").asText("2033-12-16T23:00:00Z"), dtf),
                    LocalDate.parse(listjs13.get(i).get("dat_dt").asText("2033-12-16T23:00:00Z"), dtf),
                    LocalDate.parse(listjs13.get(i).get("dat_ft").asText("2033-12-16T23:00:00Z"), dtf),
                    listjs13.get(i).get("vld").asText(null),
                    listjs13.get(i).get("typ_tvx").asText(null),
                    LocalDate.parse(listjs13.get(i).get("heurdb").asText("2033-12-16T23:00:00Z"), dtf),
                    LocalDate.parse(listjs13.get(i).get("heurfi").asText("2033-12-16T23:00:00Z"), dtf),
                    listjs13.get(i).get("observ").asText(null),
                    LocalDate.parse(listjs13.get(i).get("dat_srt").asText("2033-12-16T23:00:00Z"), dtf),
                    LocalDate.parse(listjs13.get(i).get("heursr").asText("2033-12-16T23:00:00Z"), dtf),
                    listjs13.get(i).get("obs_test").asText(null),
                    listjs13.get(i).get("index_dep").asInt(0),
                    listjs13.get(i).get("index_arr").asInt(0),
                    listjs13.get(i).get("immat_ex").asText(null),
                    listjs13.get(i).get("nom_chauff").asText(null),
                    listjs13.get(i).get("num_permis").asText(null),
                    listjs13.get(i).get("etab").asText(null),
                    listjs13.get(i).get("compteur").asInt(0),
                    listjs13.get(i).get("cpt_org").asInt(0),
                    listjs13.get(i).get("cdtyptr").asText(null),
                    listjs13.get(i).get("decstat").asText(null),
                    listjs13.get(i).get("testeur").asInt(0),
                    listjs13.get(i).get("motif_dep").asText(null),
                    listjs13.get(i).get("cdtypmnt").asText(null),
                    LocalDate.parse(listjs13.get(i).get("dat_sor_prev").asText("2033-12-16T23:00:00Z"), dtf),
                    LocalDate.parse(listjs13.get(i).get("dat_mnq_du").asText("2033-12-16T23:00:00Z"), dtf),
                    LocalDate.parse(listjs13.get(i).get("dat_mnq_au").asText("2033-12-16T23:00:00Z"), dtf),
                    LocalDate.parse(listjs13.get(i).get("dat_ent_ant").asText("2033-12-16T23:00:00Z"), dtf),
                    listjs13.get(i).get("codstat").asText(null),
                    LocalDate.parse(listjs13.get(i).get("dat_vld").asText("2033-12-16T23:00:00Z"), dtf),
                    listjs13.get(i).get("observ1").asText(null),
                    listjs13.get(i).get("testeur1").asInt(0),
                    listjs13.get(i).get("valid_ag").asInt(0),
                    LocalDate.parse(listjs13.get(i).get("dat_sais").asText("2033-12-16T23:00:00Z"), dtf)
                )
            );
        }

        JsonNode js14 = objmap.readTree(resultEntity15.getBody());
        List<JsonNode> listjs14 = js14.findParents("deccent");
        System.out.println("Hello to cons api trafic : \n" + js14);

        for (int i = 0; i < listjs14.size(); i++) {
            repositoryTrafic.save(
                new Trafic(
                    Integer.toString(i, i + 1),
                    listjs14.get(i).get("deccent").asInt(0),
                    listjs14.get(i).get("decagenc").asInt(0),
                    LocalDate.parse(listjs14.get(i).get("dedated").asText("2033-12-16T23:00:00Z"), dtf),
                    listjs14.get(i).get("ancien").asInt(0),
                    listjs14.get(i).get("vld_trafic").asText(null),
                    listjs14.get(i).get("clo_trafic").asText(null)
                )
            );
        }

        JsonNode js15 = objmap.readTree(resultEntity16.getBody());
        List<JsonNode> listjs15 = js15.findParents("deccent");
        System.out.println("Hello to cons api depart : \n" + js15);

        for (int i = 0; i < listjs15.size(); i++) {
            repositoryDepart.save(
                new Depart(
                    listjs15.get(i).get("id_apex").asText(null),
                    listjs15.get(i).get("deccent").asInt(0),
                    listjs15.get(i).get("decagenc").asInt(0),
                    listjs15.get(i).get("decserv").asInt(0),
                    listjs15.get(i).get("decoper").asText(null),
                    listjs15.get(i).get("decsean").asText(null),
                    LocalDate.parse(listjs15.get(i).get("dedated").asText("2033-12-16T23:00:00Z"), dtf),
                    listjs15.get(i).get("denumdp").asInt(0),
                    listjs15.get(i).get("matric").asInt(0),
                    listjs15.get(i).get("matric1").asInt(0),
                    listjs15.get(i).get("cdmac").asInt(0),
                    LocalDate.parse(listjs15.get(i).get("deheups").asText("16/12/2033 23:00"), dtf1),
                    LocalDate.parse(listjs15.get(i).get("deheufs").asText("16/12/2033 23:00"), dtf1),
                    listjs15.get(i).get("denbrro").asInt(0),
                    LocalDate.parse(listjs15.get(i).get("deheuaa").asText("16/12/2033 23:00"), dtf1),
                    LocalDate.parse(listjs15.get(i).get("deheudr").asText("16/12/2033 23:00"), dtf1),
                    LocalDate.parse(listjs15.get(i).get("deheupd").asText("16/12/2033 23:00"), dtf1),
                    LocalDate.parse(listjs15.get(i).get("deampli").asText("16/12/2033 23:00"), dtf1),
                    listjs15.get(i).get("obs_ind").asText(null),
                    listjs15.get(i).get("vld_roul").asText(null),
                    listjs15.get(i).get("deetat").asText(null),
                    listjs15.get(i).get("deannul").asText(null),
                    listjs15.get(i).get("decclot").asText(null),
                    listjs15.get(i).get("execute").asText(null),
                    listjs15.get(i).get("motif_a").asText(null),
                    listjs15.get(i).get("observ").asText(null),
                    listjs15.get(i).get("recettes").floatValue(),
                    listjs15.get(i).get("nbre_voy").asInt(0),
                    listjs15.get(i).get("decmotifch").asInt(0),
                    listjs15.get(i).get("decmotifre").asInt(0),
                    listjs15.get(i).get("cd1").asInt(0),
                    listjs15.get(i).get("cd2").asInt(0),
                    listjs15.get(i).get("cd3").asInt(0),
                    listjs15.get(i).get("decmotifcha").asInt(0),
                    listjs15.get(i).get("decmotifrea").asInt(0)
                )
            );
        }

        JsonNode js16 = objmap.readTree(resultEntity17.getBody());
        List<JsonNode> listjs16 = js16.findParents("deccent");
        System.out.println("Hello to cons api deprotat : \n" + js16);

        for (int i = 0; i < listjs16.size(); i++) {
            repositoryDeprotat.save(
                new Deprotat(
                    Integer.toString(i, i + 1),
                    listjs16.get(i).get("deccent").asInt(0),
                    listjs16.get(i).get("decagenc").asInt(0),
                    LocalDate.parse(listjs16.get(i).get("dedated").asText("2033-12-16T23:00:00Z"), dtf),
                    listjs16.get(i).get("denumdp").asInt(0),
                    listjs16.get(i).get("decserv").asInt(0),
                    listjs16.get(i).get("decoper").asText(null),
                    listjs16.get(i).get("decsean").asText(null),
                    listjs16.get(i).get("numrotat").asInt(0),
                    listjs16.get(i).get("lig_deccent").asInt(0),
                    listjs16.get(i).get("lig_decagenc").asInt(0),
                    listjs16.get(i).get("denumli").asText(null),
                    listjs16.get(i).get("decstat").asText(null),
                    listjs16.get(i).get("decsta1").asText(null),
                    listjs16.get(i).get("matric").asInt(0),
                    listjs16.get(i).get("matric1").asInt(0),
                    listjs16.get(i).get("cdmac").asText(null),
                    LocalDate.parse(listjs16.get(i).get("h_depart_e").asText("2033-12-16T23:00:00Z"), dtf),
                    LocalDate.parse(listjs16.get(i).get("h_retour_e").asText("2033-12-16T23:00:00Z"), dtf),
                    LocalDate.parse(listjs16.get(i).get("h_arrall_e").asText("2033-12-16T23:00:00Z"), dtf),
                    LocalDate.parse(listjs16.get(i).get("h_arrret_e").asText("2033-12-16T23:00:00Z"), dtf),
                    listjs16.get(i).get("r_annul").asText(null),
                    listjs16.get(i).get("km").asDouble(0),
                    listjs16.get(i).get("motif_a").asInt(0),
                    listjs16.get(i).get("observ").asText(null),
                    listjs16.get(i).get("recettes_voy").asInt(0),
                    listjs16.get(i).get("nbre_voy").asInt(0),
                    listjs16.get(i).get("paye").asInt(0),
                    listjs16.get(i).get("cd1").asInt(0),
                    listjs16.get(i).get("cd2").asInt(0),
                    listjs16.get(i).get("cd3").asInt(0),
                    listjs16.get(i).get("decmotifcha").asInt(0),
                    listjs16.get(i).get("decmotifrea").asInt(0),
                    listjs16.get(i).get("id_apex").asInt(),
                    listjs16.get(i).get("plus_moins").asText(null),
                    listjs16.get(i).get("a").asText(null),
                    listjs16.get(i).get("r").asText(null)
                )
            );
        }

        JsonNode js17 = objmap.readTree(resultEntity18.getBody());
        List<JsonNode> listjs17 = js17.findParents("deccent");
        System.out.println("Hello to cons api modif : \n" + js17);

        for (int i = 0; i < listjs17.size(); i++) {
            repositoryModif.save(
                new Modif(
                    Integer.toString(i, i + 1),
                    listjs17.get(i).get("deccent").asInt(0),
                    listjs17.get(i).get("decagenc").asInt(0),
                    LocalDate.parse(listjs17.get(i).get("dedated").asText("2033-12-16T23:00:00Z"), dtf),
                    listjs17.get(i).get("denumdp").asInt(0),
                    listjs17.get(i).get("decserv").asInt(0),
                    listjs17.get(i).get("decoper").asText(null),
                    listjs17.get(i).get("decsean").asText(null),
                    listjs17.get(i).get("numrotat").asInt(0),
                    listjs17.get(i).get("matric").asInt(0),
                    listjs17.get(i).get("cd1").asInt(0),
                    listjs17.get(i).get("decmotif").asInt(0),
                    LocalDate.parse(listjs17.get(i).get("heur").asText("2033-12-16T23:00:00Z"), dtf),
                    listjs17.get(i).get("chre").asText(null),
                    listjs17.get(i).get("typ").asText(null)
                )
            );
        }

        JsonNode js18 = objmap.readTree(resultEntity19.getBody());
        List<JsonNode> listjs18 = js18.findParents("cdtypab");
        System.out.println("Hello to cons api drabsen : \n" + js18);

        for (int i = 0; i < listjs18.size(); i++) {
            repositoryDrabsen.save(
                new Drabsen(
                    Integer.toString(i, i + 1),
                    listjs18.get(i).get("cdtypab").asText(null),
                    listjs18.get(i).get("matric").asInt(0),
                    LocalDate.parse(listjs18.get(i).get("dat_abs").asText("2033-12-16T23:00:00Z"), dtf),
                    listjs18.get(i).get("num_abs").asInt(0),
                    listjs18.get(i).get("nbr_abs").asInt(0),
                    listjs18.get(i).get("valid_abs").asInt(0),
                    listjs18.get(i).get("observa_abs").asText(null),
                    listjs18.get(i).get("cd1").asInt(0),
                    listjs18.get(i).get("cd2").asInt(0),
                    listjs18.get(i).get("cd3").asInt(0)
                )
            );
        }
        /*  JsonNode js19 = objmap.readTree(resultEntityExApi.getBody());
        List<JsonNode> listjs19 = js19.findParents("keycomp");
        System.out.println("Hello to cons api ExternalApi : \n" + js19);

        for (int i = 0; i < listjs19.size(); i++) {
        repositoryExternalApi.save(new ExternalApi(
            
            listjs19.get(i).get("keycomp").asText(null),
            listjs19.get(i).get("id").asInt(0),
            listjs19.get(i).get("name").asText(null),
            listjs19.get(i).get("status").asText(null),
        ));

        } */

        JsonNode js20 = objmap.readTree(resultEntity20.getBody());
        List<JsonNode> listjs20 = js20.findParents("decmotif");
        System.out.println("Hello to cons api Motifchserv : \n" + js20);

        for (int i = 0; i < listjs20.size(); i++) {
            repositoryMotifchserv.save(
                new Motifchserv(
                    Integer.toString(i, i + 1),
                    listjs20.get(i).get("decmotif").asInt(i),
                    listjs20.get(i).get("delmotif").asText(null),
                    listjs20.get(i).get("x").asText(null),
                    listjs20.get(i).get("vs").asText(null)
                )
            );
        }

        JsonNode js21 = objmap.readTree(resultEntity21.getBody());
        List<JsonNode> listjs21 = js21.findParents("num_appel");
        System.out.println("Hello to cons api Afficheur_Bus_Ar_Fr : \n" + js21);
        repositoryDisplaybus.deleteAll();
        for (int i = 0; i < listjs21.size(); i++) {
            repositoryDisplaybus.save(
                new Displaybus(
                    listjs21.get(i).get("num_appel").asText(),
                    listjs21.get(i).get("lang").asText(),
                    listjs21.get(i).get("vehicule").asText(),
                    listjs21.get(i).get("num_appel").asInt(),
                    listjs21.get(i).get("detail_ligne").asText(),
                    listjs21.get(i).get("ligne").asText(),
                    listjs21.get(i).get("direction").asText(),
                    listjs21.get(i).get("denumli").asText(),
                    listjs21.get(i).get("deltyli").asText()
                )
            );
        }
    }

    public void restTables() {
        /*repositoryCenter.deleteAll();
        repositoryAffectagent.deleteAll();
        repositoryGroupe.deleteAll();
        repositoryStation.deleteAll();
        repositoryPeriode.deleteAll();
        repositoryItineraire.deleteAll();
        repositoryMotifa.deleteAll();
        repositoryLigne.deleteAll();
        repositoryDrtypab.deleteAll();
        repositoryAgence.deleteAll();
        repositoryRhAgent.deleteAll();
        repositoryCentVehic.deleteAll();
        repositoryMachine.deleteAll();
        repositoryBonTvx.deleteAll();
        repositoryTrafic.deleteAll();
        repositoryDepart.deleteAll();
        repositoryDeprotat.deleteAll();
        repositoryModif.deleteAll();
        repositoryDrabsen.deleteAll();
        repositoryMotifchserv.deleteAll();*/

    }
}
