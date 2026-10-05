package org.tierlistapp.dto.api.response;

import org.tierlistapp.entity.TierMap;

public record TierListShortApiResponse(
        String id,
        TierMap tierMap
) {}
