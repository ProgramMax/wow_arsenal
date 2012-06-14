package org.mjp.wow.arsenalparser.domain.enums

enum EWowRaceType {
    // WoW Alliance Races
    GNOME,
    HUMAN,
    DWARF,
    WORGE,
    NIGHTELF,
    DRAENEI,

    // WoW Horde Races
    UNDEAD,
    BLOODELF,
    GOBLIN,
    ORC,
    TROLL,
    TAURE,

    // Unknown Races
    UNKNOWN

    final static BUNDLE = ResourceBundle.getBundle('race')

    def static EWowRaceType getRaceType(final raceName) {
        def raceType

        switch (raceName.toLowerCase()) {
            case BUNDLE.getString(GNOME.name()).toLowerCase():
                raceType = GNOME
                break
            case BUNDLE.getString(HUMAN.name()).toLowerCase():
                raceType = HUMAN
                break
            case BUNDLE.getString(DWARF.name()).toLowerCase():
                raceType = DWARF
                break
            case BUNDLE.getString(WORGE.name()).toLowerCase():
                raceType = WORGE
                break
            case BUNDLE.getString(NIGHTELF.name()).toLowerCase():
                raceType = NIGHTELF
                break
            case BUNDLE.getString(DRAENEI.name()).toLowerCase():
                raceType = DRAENEI
                break

            case BUNDLE.getString(UNDEAD.name()).toLowerCase():
                raceType = UNDEAD
                break
            case BUNDLE.getString(BLOODELF.name()).toLowerCase():
                raceType = BLOODELF
                break
            case BUNDLE.getString(GOBLIN.name()).toLowerCase():
                raceType = GOBLIN
                break
            case BUNDLE.getString(ORC.name()).toLowerCase():
                raceType = ORC
                break
            case BUNDLE.getString(TROLL.name()).toLowerCase():
                raceType = TROLL
                break
            case BUNDLE.getString(TAURE.name()).toLowerCase():
                raceType = TAURE
                break

            default:
                raceType = UNKNOWN
                break
        }

        return raceType
    }

    def String toString() {
        return BUNDLE.getString(this.name())
    }
}

