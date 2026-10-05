package org.tierlistapp.mapper;

import org.tierlistapp.dto.StoredFileDTO;
import org.tierlistapp.entity.StoredFile;
import org.tierlistapp.entity.TierList;
import org.tierlistapp.dto.TierListDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DTOMapper {

    TierListDTO toTierListDTO(TierList tierList);

    TierList toTierListEntity(TierListDTO tierListDTO);

    StoredFileDTO toStoredFileDTO(StoredFile storedFileDTO);

    StoredFile toStoredFileEntity(StoredFileDTO storedFile);

}
