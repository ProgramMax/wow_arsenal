package org.mjp.wow.arsenalparser.domain.stats

import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle

/**
 * Spell stats of a character.
 */
class SpellStats {
   Integer spellPower
   String spellHaste
   String spellHit
   String spellCrit
   String spellPenetration
   String manaRegen
   String combatRegen
    
    def String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append('spellPower', this.spellPower)
            .append('spellHaste', this.spellHaste)
            .append('spellHit', this.spellHit)
            .append('spellCrit', this.spellCrit)
            .append('spellPenetration', this.spellPenetration)
            .append('manaRegen', this.manaRegen)
            .append('combatRegen', this.combatRegen)
            .toString()
    }
}

