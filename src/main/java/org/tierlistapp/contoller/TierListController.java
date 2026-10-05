package org.tierlistapp.contoller;

import org.springframework.web.bind.annotation.*;
import org.tierlistapp.dto.TierListDTO;
import org.tierlistapp.dto.api.response.TierListShortApiResponse;
import org.tierlistapp.entity.TierMap;
import org.tierlistapp.service.TierListService;

@RestController
@RequestMapping("/api/tier-list")
public class TierListController {

    private final TierListService tierListService;

    public TierListController(TierListService tierListService) {
        this.tierListService = tierListService;
    }

    @PutMapping("/save")
    public TierListShortApiResponse saveTierList(@RequestBody TierMap tierMap) {
        String userId = "user_id_0000_test";

        TierListDTO tierListDTO = tierListService.create(userId, tierMap);
        return new TierListShortApiResponse(tierListDTO.id(), tierListDTO.tierMap());
    }

    @PutMapping("/update/{id}")
    public TierListShortApiResponse updateTierList(@RequestParam String id, @RequestBody TierMap tierMap) {
        String userId = "user_id_0000_test";

        TierListDTO tierListDTO = tierListService.updateJsonByIdAndUserId(id, userId, tierMap);
        return new TierListShortApiResponse(tierListDTO.id(), tierListDTO.tierMap());
    }

    @DeleteMapping("/{id}")
    public void deleteTierList(@PathVariable String id) {
        String userId = "user_id_0000_test";

        tierListService.deleteByIdAndUserId(id, userId);
    }
}
