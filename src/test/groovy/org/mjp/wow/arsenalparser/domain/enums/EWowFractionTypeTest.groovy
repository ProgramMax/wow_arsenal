package org.mjp.wow.arsenalparser.domain.enums

import org.junit.Test

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class EWowFractionTypeTest {
    final LOGGER = LoggerFactory.getLogger(this.class)

    @Test def void testGetFraction() {
        assert EWowFractionType.ALLIANCE == EWowFractionType.getFractionType(EWowRaceType.GNOME)
        assert EWowFractionType.ALLIANCE == EWowFractionType.getFractionType(EWowRaceType.DRAENEI)
        assert EWowFractionType.HORDE == EWowFractionType.getFractionType(EWowRaceType.UNDEAD)
        assert EWowFractionType.HORDE == EWowFractionType.getFractionType(EWowRaceType.TAURE)

        LOGGER.debug('fraction test: {}', EWowFractionType.getFractionType(EWowRaceType.GNOME))
        LOGGER.debug('fraction test: {}', EWowFractionType.getFractionType(EWowRaceType.DRAENEI))
        LOGGER.debug('fraction test: {}', EWowFractionType.getFractionType(EWowRaceType.UNDEAD))
        LOGGER.debug('fraction test: {}', EWowFractionType.getFractionType(EWowRaceType.TAURE))

        assert EWowFractionType.UNKNOWN == EWowFractionType.getFractionType(EWowRaceType.UNKNOWN)
    }
}

