package org.mjp.wow.arsenalparser.domain

import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle

class Equipment {
    String  name
    Integer itemLevel
    Integer position
    String proccEffect
    Socket[] socketList
    
    def String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("name", this.name)
            .append("itemLevel", this.itemLevel)
            .append("position", this.position)
            .append("proccEffect", this.proccEffect)
            .append("socketList", this.socketList)
            .toString()
    }
}
