package org.mjp.wow.arsenalparser.domain

import java.text.SimpleDateFormat as SDF

import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle

class CharAchievements {
    Achievement achievement
    Date    date
    
    CharAchievements() {
        this.achievement = new Achievement()
    }
    
    def String toString() {
        def tempDate = date ? new SDF().format(this.date) : null
        
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append('achievement', this.achievement)
            .append('date', tempDate)
            .toString()
    }
}
