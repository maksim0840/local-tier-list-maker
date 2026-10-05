package org.tierlistapp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TierMap {
    private String name;
    private String description;
    private String img;
    private List<TierRow> rows;
    private List<TierItem> unallocatedItems;
}
