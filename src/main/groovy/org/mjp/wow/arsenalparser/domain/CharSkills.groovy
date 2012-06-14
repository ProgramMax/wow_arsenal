package org.mjp.wow.arsenalparser.domain

import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle

class CharSkills {
    String[] skillList
    String[] role
    Integer activeSkill

    def String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("skillList", this.skillList)
            .append("role", this.role)
            .append("activeSkill", activeSkill)
            .toString()
    }
}
