package org.mjp.wow.arsenalparser.domain.stats

import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle

/**
 * Common stats of a character.
 */
class CommonStats {
    Double mastery

    def String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append('mastery', this.mastery)
            .toString()
    }
}

