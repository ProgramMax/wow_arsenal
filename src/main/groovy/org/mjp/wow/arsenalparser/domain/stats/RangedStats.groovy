package org.mjp.wow.arsenalparser.domain.stats

import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle

/**
 * Ranged stats of a character.
 */
class RangedStats {
   Integer rangedAttackpower
   String  rangedDamage
   Double  rangedDps
   Double  rangedSpeed
   Double  rangedHaste
   Double  rangedHit
   Double  rangedCrit

    def String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append('rangedAttackpower', this.rangedAttackpower)
            .append('rangedDamage', this.rangedDamage)
            .append('rangedDps', this.rangedDps)
            .append('rangedSpeed', this.rangedSpeed + '%')
            .append('rangedHaste', this.rangedHaste + '%')
            .append('rangedHit', '+' + this.rangedHit + '%')
            .append('rangedCrit', this.rangedCrit + '%')
            .toString()
    }
}

