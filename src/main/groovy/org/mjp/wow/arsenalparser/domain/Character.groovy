package org.mjp.wow.arsenalparser.domain

import java.text.SimpleDateFormat as SDF

import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle

import  org.mjp.wow.arsenalparser.domain.stats.CharacterStats
import  org.mjp.wow.arsenalparser.domain.enums.EWowFractionType

class WoWCharacter {
    Date             downloadDate
    Date             lastUpdateArsenal

    String           name
    String           realm
    String           realmPool

    CharBasics       charBasics
    CharSkills       charSkills
    CharProfessions  charProfessions
    CharAchievements charAchievements
    String           charAchievementPoints
    CharEquipment    charEquipment

    WoWCharacter() {
        this.charBasics = new CharBasics()
        this.charSkills = new CharSkills()
        this.charProfessions = new CharProfessions()
        this.charAchievements = new CharAchievements()
        this.charEquipment = new CharEquipment()
    }

    def String toString() {
        def tempDownloadDate = this.downloadDate ? new SDF().format(this.downloadDate) : null
        def tempLastUpdateArsenal = this.lastUpdateArsenal ? new SDF().format(this.lastUpdateArsenal) : null

        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append('downloadDate', tempDownloadDate)
            .append('lastUpdateArsenal', tempLastUpdateArsenal)
            .append('name', this.name)
            .append('realm', this.realm)
            .append('realmPool', this.realmPool)
            .toString()
    }
}
