package org.tierlistapp.service;

import org.springframework.stereotype.Service;
import org.tierlistapp.entity.TierList;
import org.tierlistapp.dto.TierListDTO;
import org.tierlistapp.entity.TierMap;
import org.tierlistapp.exception.AccessDeniedException;
import org.tierlistapp.exception.NotFoundException;
import org.tierlistapp.mapper.DTOMapper;
import org.tierlistapp.repository.TierListRepository;

import java.util.Map;
import java.util.Objects;

@Service
public class TierListService {
    private final TierListRepository tierListRepository;
    private final DTOMapper dtoMapper;

    public TierListService(TierListRepository tierListRepository, DTOMapper dtoMapper) {
        this.tierListRepository = tierListRepository;
        this.dtoMapper = dtoMapper;
    }

    public TierListDTO create(String userId, TierMap tierMap) {
        TierList tierList = new TierList(userId, tierMap);
        tierList = tierListRepository.save(tierList);
        return dtoMapper.toTierListDTO(tierList);
    }

    public void delete(String id) {
        TierList tierList = getRaw(id);
        tierListRepository.delete(tierList);
    }

    public void deleteByIdAndUserId(String id, String userId) {
        TierList tierList = getRawByIdAndUserId(id, userId);
        tierListRepository.delete(tierList);
    }

    public TierListDTO updateJson(String id, TierMap newTierMap) {
        TierList tierList = getRaw(id);
        tierList.setTierMap(newTierMap);
        tierList =  tierListRepository.save(tierList);
        return dtoMapper.toTierListDTO(tierList);
    }

    public TierListDTO updateJsonByIdAndUserId(String id, String userId, TierMap newTierMap) {
        TierList tierList = getRawByIdAndUserId(id, userId);
        tierList.setTierMap(newTierMap);
        tierList =  tierListRepository.save(tierList);
        return dtoMapper.toTierListDTO(tierList);
    }

    public TierListDTO getById(String id) {
        TierList tierList = getRaw(id);
        return dtoMapper.toTierListDTO(tierList);
    }

    public TierListDTO getByIdAndUserId(String id, String userId) {
        TierList tierList = getRawByIdAndUserId(id, userId);
        return dtoMapper.toTierListDTO(tierList);
    }

    private TierList getRaw(String id) {
        return tierListRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Unknown TierList id")
        );
    }

    private TierList getRawByIdAndUserId(String id, String userId) {
        TierList tierList = tierListRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Unknown TierList id")
        );
        if (!Objects.equals(tierList.getUserId(), userId)) {
            throw new AccessDeniedException("Incorrect TierList owner");
        }
        return tierList;
    }
}
