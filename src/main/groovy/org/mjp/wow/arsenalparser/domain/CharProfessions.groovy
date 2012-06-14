package org.mjp.wow.arsenalparser.domain

import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle

class CharProfessions {
    String  firstPrimProfession
    String  secondPrimProfession
    Integer firstPrimProfSkillLevel
    Integer secondPrimProfSkillLevel
    Integer cookingProfSkillLevel
    Integer fishingProfSkillLevel
    Integer firstaidProfSkillLevel
    Integer archeologyProfSkillLevel
    
    def String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("firstPrimProfession", this.firstPrimProfession)
            .append("secondPrimProfession", this.secondPrimProfession)
            .append("firstPrimProfSkillLevel", this.firstPrimProfSkillLevel)
            .append("secondPrimProfSkillLevel", this.secondPrimProfSkillLevel)
            .append("cookingProfSkillLevel", this.cookingProfSkillLevel)
            .append("fishingProfSkillLevel", this.fishingProfSkillLevel)
            .append("firstaidProfSkillLevel", this.firstaidProfSkillLevel)
            .append("archeologyProfSkillLevel", this.archeologyProfSkillLevel)
            .toString()
    }
}
