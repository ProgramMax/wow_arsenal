package org.mjp.wow.arsenalparser.domain.stats

import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle

import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Melee stats of a character.
 */
class MeleeStats {
    final def LOGGER = LoggerFactory.getLogger(this.class)

    Integer meleeAttackpower
    Integer minMeleeDamage
    Integer maxMeleeDamage
    Double  mainhandMeleeDps
    Double  offhandMeleeDps
    Double  mainhandMeleeSpeed
    Double  offhandMeleeSpeed
    Integer mainhandExpertise
    Integer offhandExpertise
    Double  meleeHaste
    Double  meleeHit
    Double  meleeCrit

   // MeleeStats[meleeAttackpower=12683,meleeDamage=2952–3807,meleeDps=1940,0/970,0,
   // meleeSpeed=1,74/1,36,expertise=9/9,meleeHaste=3.32%,meleeHit=+4.25%,meleeCrit=24.2%]
   def String toString() {
       return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
           .append('meleeAttackpower', this.meleeAttackpower)
           .append('meleeDamage', String.format('%s-%s', minMeleeDamage, maxMeleeDamage))
           .append('meleeDps', String.format('%s/%s', mainhandMeleeDps, offhandMeleeDps))
           .append('meleeSpeed', String.format('%s/%s', mainhandMeleeSpeed, offhandMeleeSpeed))
           .append('expertise', String.format('%s/%s', mainhandExpertise, offhandExpertise))
           .append('meleeHaste', String.format('%s%%', this.meleeHaste))
           .append('meleeHit', String.format('+%s%%', this.meleeHit))
           .append('meleeCrit', String.format('%s%%', this.meleeCrit))
           .toString()
   }
}

