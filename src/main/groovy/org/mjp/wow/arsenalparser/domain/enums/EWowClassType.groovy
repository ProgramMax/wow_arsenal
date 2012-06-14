package org.mjp.wow.arsenalparser.domain.enums

import org.apache.commons.lang3.text.WordUtils

enum EWowClassType {
    ROGUE,
    PALADIN,
    MONK,
    MAGE,
    WARLOCK,
    WARRIOR,
    SHAMAN,
    DRUID,
    PRIEST,
    DEATHKNIGHT,
    HUNTER,
    UNKNOWN

    final static BUNDLE = ResourceBundle.getBundle('class')

    def static EWowClassType getClassType(final className) {
        def classType

        def lcClassName = className.toLowerCase()
        def rogueCNRegexp = BUNDLE.getString(ROGUE.name() + '_regexp')
        def paladinCNRegexp = BUNDLE.getString(PALADIN.name() + '_regexp')
        def monkCNRegexp = BUNDLE.getString(MONK.name() + '_regexp')
        def mageCNRegexp = BUNDLE.getString(MAGE.name() + '_regexp')
        def warlockCNRegexp = BUNDLE.getString(WARLOCK.name() + '_regexp')
        def warriorCNRegexp = BUNDLE.getString(WARRIOR.name() + '_regexp')
        def shamanCNRegexp = BUNDLE.getString(SHAMAN.name() + '_regexp')
        def priestCNRegexp = BUNDLE.getString(PRIEST.name() + '_regexp')
        def deathknightCNRegexp = BUNDLE.getString(DEATHKNIGHT.name() + '_regexp')
        def hunterCNRegexp = BUNDLE.getString(HUNTER.name() + '_regexp')

        switch (lcClassName) {
            case ~/$rogueCNRegexp/:
                classType = ROGUE
                break
            case ~/$paladinCNRegexp/:
                classType = PALADIN
                break
            case ~/$monkCNRegexp/:
                classType = MONK
                break
            case ~/$mageCNRegexp/:
                classType = MAGE
                break
            case ~/$warlockCNRegexp/:
                classType = WARLOCK
                break
            case ~/$warriorCNRegexp/:
                classType = WARRIOR
                break
            case ~/$shamanCNRegexp/:
                classType = SHAMAN
                break
            case ~/$priestCNRegexp/:
                classType = PRIEST
                break
            case ~/$deathknightCNRegexp/:
                classType = DEATHKNIGHT
                break
            case ~/$hunterCNRegexp/:
                classType = HUNTER
                break

            default:
                classType = UNKNOWN
        }

        return classType
    }

    def String toString() {
        return BUNDLE.getString(this.name())
    }
}

