package org.mjp.wow.arsenalparser.domain

import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle

import  org.mjp.wow.arsenalparser.domain.stats.CharacterStats

class CharEquipment {
    Map<String, Equipment> equipmentMap
    Integer actualAverageItemLevel
    Integer maximumAverageItemLevel
    CharacterStats charStats
    
    CharEquipment() {
    }
    
    def String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append('equipmentMap', this.equipmentMap)
            .append('actualAverageItemLevel', this.actualAverageItemLevel)
            .append('maximumAverageItemLevel', this.maximumAverageItemLevel)
            .append('charStats', this.charStats)
            .toString()
    }
}

