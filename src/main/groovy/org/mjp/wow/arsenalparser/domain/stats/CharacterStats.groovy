package org.mjp.wow.arsenalparser.domain.stats

import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle

/**
 * Stats of a character.
 */
class CharacterStats {
    BasicStats   basicStats
    CommonStats  commonStats
    MeleeStats   meleeStats
    RangedStats  rangedStats
    SpellStats   spellStats
    DefenseStats defenseStats

    def String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append('basicStats', this.basicStats)
            .append('commonStats', this.commonStats)
            .append('meleeStats', this.meleeStats)
            .append('rangedStats', this.rangedStats)
            .append('spellStats', this.spellStats)
            .append('defenseStats', this.defenseStats)
            .toString()
    }
}

