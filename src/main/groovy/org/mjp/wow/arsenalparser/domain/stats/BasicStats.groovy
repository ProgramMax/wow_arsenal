package org.mjp.wow.arsenalparser.domain.stats

import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle

/**
 * Basic stats of a character.
 */
class BasicStats {
   Integer stamina
   Integer agility
   Integer strength
   Integer intellect
   Integer spirit
    
    def String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append('stamina', this.stamina)
            .append('agility', this.agility)
            .append('strength', this.strength)
            .append('intellect', this.intellect)
            .append('spirit', this.spirit)
            .toString()
    }
}

