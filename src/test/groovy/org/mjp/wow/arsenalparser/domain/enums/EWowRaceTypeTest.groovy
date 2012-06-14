package org.mjp.wow.arsenalparser.domain.enums

import org.junit.Test

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class EWowRaceTypeTest {
    final LOGGER = LoggerFactory.getLogger(this.class)

    @Test def void testGetRaceType() {
        assert EWowRaceType.GNOME == EWowRaceType.getRaceType('Gnom')

        LOGGER.debug('race type:     {}', EWowRaceType.getRaceType('Gnom'))
    }
}

