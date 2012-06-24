package org.mjp.wow.arsenalparser.domain.stats

import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle

/**
 * Ranged stats of a character.
 */
class RangedStats {
   Integer rangedAttackpower
   Integer minRangedDamage
   Integer maxRangedDamage
   Double  rangedDps
   Double  rangedSpeed
   Double  rangedHaste
   Double  rangedHit
   Double  rangedCrit

   def String toString() {
       return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
           .append('rangedAttackpower', this.rangedAttackpower)
           .append('rangedDamage', String.format('%s-%s', minRangedDamage, maxRangedDamage))
           .append('rangedDps', this.rangedDps)
           .append('rangedSpeed', String.format('%s%%', this.rangedSpeed))
           .append('rangedHaste', String.format('%s%%', this.rangedHaste))
           .append('rangedHit', String.format('+%s%%', this.rangedHit))
           .append('rangedCrit', String.format('%s%%', this.rangedCrit))
           .toString()
   }
}

