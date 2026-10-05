package org.tierlistapp.service;

import org.springframework.stereotype.Service;
import org.tierlistapp.dto.StoredFileDTO;
import org.tierlistapp.dto.TierListDTO;
import org.tierlistapp.entity.StoredFile;
import org.tierlistapp.entity.TierList;
import org.tierlistapp.entity.TierMap;
import org.tierlistapp.exception.AccessDeniedException;
import org.tierlistapp.exception.NotFoundException;
import org.tierlistapp.mapper.DTOMapper;
import org.tierlistapp.repository.StoredFileRepository;

import java.util.Objects;

@Service
public class StoredFileService {

    private final StoredFileRepository storedFileRepository;
    private final DTOMapper dtoMapper;

    public StoredFileService(StoredFileRepository storedFileRepository, DTOMapper dtoMapper) {
        this.storedFileRepository = storedFileRepository;
        this.dtoMapper = dtoMapper;
    }

    public StoredFileDTO create(String ownerId, String uploadedFileName, String objectKey) {
        StoredFile storedFile = new StoredFile(ownerId, uploadedFileName, objectKey);
        storedFile = storedFileRepository.save(storedFile);
        return dtoMapper.toStoredFileDTO(storedFile);
    }

    public void delete(String id) {
        StoredFile storedFile = getRaw(id);
        storedFileRepository.delete(storedFile);
    }

    public void deleteByIdAndOwnerId(String id, String ownerId) {
        StoredFile storedFile = getRawByIdAndOwnerId(id, ownerId);
        storedFileRepository.delete(storedFile);
    }

    public StoredFileDTO getById(String id) {
        StoredFile storedFile = getRaw(id);
        return dtoMapper.toStoredFileDTO(storedFile);
    }

    public StoredFileDTO getByIdAndOwnerId(String id, String ownerId) {
        StoredFile storedFile = getRawByIdAndOwnerId(id, ownerId);
        return dtoMapper.toStoredFileDTO(storedFile);
    }

    private StoredFile getRaw(String id) {
        return storedFileRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Unknown StoredFile id")
        );
    }

    private StoredFile getRawByIdAndOwnerId(String id, String ownerId) {
        StoredFile storedFile = storedFileRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Unknown StoredFile id")
        );
        if (!Objects.equals(storedFile.getOwnerId(), ownerId)) {
            throw new AccessDeniedException("Invalid StoredFile owner");
        }
        return storedFile;
    }
}
