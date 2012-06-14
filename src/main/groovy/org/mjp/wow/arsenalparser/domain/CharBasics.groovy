package org.mjp.wow.arsenalparser.domain

import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle

import org.mjp.wow.arsenalparser.domain.enums.EWowClassType
import org.mjp.wow.arsenalparser.domain.enums.EWowFractionType
import org.mjp.wow.arsenalparser.domain.enums.EWowRaceType

class CharBasics {
    EWowClassType    classType
    EWowRaceType     raceType
    Integer          level
    String           guild
    String           title
    EWowFractionType fractionType

    def String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append('classType', this.classType)
            .append('raceType', this.raceType)
            .append('level', this.level)
            .append('guild', this.guild)
            .append('title', this.title)
            .append('fractionType', this.fractionType)
            .toString()
    }
}
