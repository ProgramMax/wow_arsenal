package org.mjp.wow.arsenalparser.domain

import java.text.SimpleDateFormat

import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle

class Achievement {
    String  category
    String  subCategory
    String  text
    Date    date
    Integer imageId
    String  location
    String  points
    Integer preAchievementId
    Integer postAchievementId
    
    def String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append('category', this.category)
            .append('subCategory', this.subCategory)
            .append('text', this.text)
            .append('date', this.date)
            .append('imageId', this.imageId)
            .append('location', this.location)
            .append('points', this.points)
            .append('preAchievementId', this.preAchievementId)
            .append('postAchievementId', this.postAchievementId)
            .toString()
    }
}
