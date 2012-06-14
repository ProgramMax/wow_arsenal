package org.mjp.wow.arsenalparser.domain.enums

import org.apache.commons.lang3.text.WordUtils

enum EWowFractionType {
    ALLIANCE,
    HORDE,
    UNKNOWN

    final static BUNDLE = ResourceBundle.getBundle('fraction')

    def static EWowFractionType getFractionType(final raceType) {
        def fractionType

        switch (raceType) {
            case EWowRaceType.GNOME:
            case EWowRaceType.HUMAN:
            case EWowRaceType.DWARF:
            case EWowRaceType.WORGE:
            case EWowRaceType.NIGHTELF:
            case EWowRaceType.DRAENEI:
                fractionType = ALLIANCE
                break

            case EWowRaceType.UNDEAD:
            case EWowRaceType.BLOODELF:
            case EWowRaceType.GOBLIN:
            case EWowRaceType.ORC:
            case EWowRaceType.TROLL:
            case EWowRaceType.TAURE:
                fractionType = HORDE
                break

            default:
                fractionType = UNKNOWN
                break
        }

        return fractionType
    }

    def String toString() {
        return BUNDLE.getString(this.name())
    }
}

