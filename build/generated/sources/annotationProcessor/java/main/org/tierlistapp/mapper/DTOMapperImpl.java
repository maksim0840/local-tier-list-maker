package org.tierlistapp.mapper;

import java.time.Instant;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import org.tierlistapp.dto.TierListDTO;
import org.tierlistapp.entity.TierList;
import org.tierlistapp.entity.TierMap;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-10-02T19:06:38+0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-9.0.0.jar, environment: Java 21.0.10 (Microsoft)"
)
@Component
public class DTOMapperImpl implements DTOMapper {

    @Override
    public TierListDTO toTierListDTO(TierList tierList) {
        if ( tierList == null ) {
            return null;
        }

        String id = null;
        String userId = null;
        TierMap tierMap = null;
        Instant createdAt = null;
        Instant updatedAt = null;

        id = tierList.getId();
        userId = tierList.getUserId();
        tierMap = tierList.getTierMap();
        createdAt = tierList.getCreatedAt();
        updatedAt = tierList.getUpdatedAt();

        TierListDTO tierListDTO = new TierListDTO( id, userId, tierMap, createdAt, updatedAt );

        return tierListDTO;
    }

    @Override
    public TierList toTierListEntity(TierListDTO tierListDTO) {
        if ( tierListDTO == null ) {
            return null;
        }

        TierList tierList = new TierList();

        tierList.setId( tierListDTO.id() );
        tierList.setUserId( tierListDTO.userId() );
        tierList.setTierMap( tierListDTO.tierMap() );
        tierList.setCreatedAt( tierListDTO.createdAt() );
        tierList.setUpdatedAt( tierListDTO.updatedAt() );

        return tierList;
    }
}
