package org.mjp.wow.arsenalparser.domain.stats

import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle

/**
 * Defense stats of a character.
 */
class DefenseStats {
    Integer armor
    Double dodge
    Double parry
    Double block
    Integer resilience

    def String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append('armor', this.armor)
            .append('dodge', this.dodge + '%')
            .append('parry', this.parry + '%')
            .append('block', this.block + '%')
            .append('resilience', this.resilience)
            .toString()
    }
}

