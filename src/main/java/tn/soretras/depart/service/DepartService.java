package tn.soretras.depart.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tn.soretras.depart.domain.Depart;
import tn.soretras.depart.repository.DepartRepository;
import tn.soretras.depart.repository.DeprotatRepository;
import tn.soretras.depart.service.dto.DepartDTO;
import tn.soretras.depart.service.dto.DeprotatDTO;
import tn.soretras.depart.service.mapper.DepartMapper;
import tn.soretras.depart.service.mapper.DeprotatMapper;

/**
 * Service Implementation for managing {@link Depart}.
 */
@Service
public class DepartService {

    private final Logger log = LoggerFactory.getLogger(DepartService.class);

    private final DepartRepository departRepository;

    private final DepartMapper departMapper;

    private final DeprotatRepository deprotatRepository;
    private final DeprotatMapper deprotatMapper;

    public DepartService(
        DepartRepository departRepository,
        DepartMapper departMapper,
        DeprotatRepository deprotatRepository,
        DeprotatMapper deprotatMapper
    ) {
        this.departRepository = departRepository;
        this.departMapper = departMapper;
        this.deprotatRepository = deprotatRepository;
        this.deprotatMapper = deprotatMapper;
    }

    /**
     * Save a depart.
     *
     * @param departDTO the entity to save.
     * @return the persisted entity.
     */
    public DepartDTO save(DepartDTO departDTO) {
        log.debug("Request to save Depart : {}", departDTO);
        Depart depart = departMapper.toEntity(departDTO);
        depart = departRepository.save(depart);
        return departMapper.toDto(depart);
    }

    /**
     * Update a depart.
     *
     * @param departDTO the entity to save.
     * @return the persisted entity.
     */
    public DepartDTO update(DepartDTO departDTO) {
        log.debug("Request to update Depart : {}", departDTO);
        Depart depart = departMapper.toEntity(departDTO);
        depart = departRepository.save(depart);
        return departMapper.toDto(depart);
    }

    /**
     * Partially update a depart.
     *
     * @param departDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DepartDTO> partialUpdate(DepartDTO departDTO) {
        log.debug("Request to partially update Depart : {}", departDTO);

        return departRepository
            .findById(departDTO.getId())
            .map(existingDepart -> {
                departMapper.partialUpdate(existingDepart, departDTO);

                return existingDepart;
            })
            .map(departRepository::save)
            .map(departMapper::toDto);
    }

    /**
     * Get all the departs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<DepartDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Departs");
        //return departRepository.findAll(pageable).map(departMapper::toDto);

        Page<DepartDTO> dpdto = departRepository.findAll(pageable).map(departMapper::toDto);
        List<DepartDTO> listDep = new ArrayList<>();
        if (dpdto != null && dpdto.hasContent()) {
            listDep = dpdto.getContent();
            for (int i = 0; i < listDep.size(); i++) {
                Set<DeprotatDTO> setdep = listDep.get(i).getDeprotats();
                Set<DeprotatDTO> setdepf = new HashSet<DeprotatDTO>();
                Iterator<DeprotatDTO> itdep = setdep.iterator();
                while (itdep.hasNext()) {
                    DeprotatDTO deprdto = (DeprotatDTO) itdep.next();
                    String iddeprot = deprdto.getId_apex().toString();
                    System.out.println("show data used------------------------>" + iddeprot);
                    Optional<Object> opobj = deprotatRepository.findById(iddeprot).map(deprotatMapper::toDto);
                    setdepf.add((DeprotatDTO) opobj.get());

                    listDep.get(i).setDeprotats(setdepf);
                    log.debug("Request to get array deprotat", setdepf);
                }
            }
            Long totalpages = (long) listDep.size();
            @SuppressWarnings("unused")
            Page<DepartDTO> pageddto = new PageImpl<DepartDTO>(listDep, pageable, totalpages);
        }

        return dpdto;
    }

    /**
     * Get one depart by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<DepartDTO> findOne(String id) {
        log.debug("Request to get Depart : {}", id);
        return departRepository.findById(id).map(departMapper::toDto);
    }

    /**
     * Delete the depart by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete Depart : {}", id);
        departRepository.deleteById(id);
    }
}
