package org.mjp.wow.arsenalparser.domain.enums

import org.junit.Test

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class EWowProfessionTypeTest {
    final static LOGGER = LoggerFactory.getLogger(this.class)

    @Test def void testGetProfessionType() {
        LOGGER.debug('profession type: {}', EWowProfessionType.TAILORING)
        LOGGER.debug('profession type: {}', EWowProfessionType.getProfessionType('Schneiderei'))

        assert EWowProfessionType.TAILORING == EWowProfessionType.getProfessionType('Schneiderei')

        LOGGER.debug('profession type: {}', EWowProfessionType.getProfessionType('Schneiderei'))
    }
}

