package org.mjp.wow.arsenalparser.domain.enums

import org.junit.Test

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class EWowClassTypeTest {
    final LOGGER = LoggerFactory.getLogger(this.class)

    @Test def void testGetClassType() {
        assert EWowClassType.ROGUE == EWowClassType.getClassType('Schurke')
        assert EWowClassType.ROGUE == EWowClassType.getClassType('Schurkin')
        assert EWowClassType.WARLOCK == EWowClassType.getClassType('Hexenmeister')
        assert EWowClassType.WARLOCK == EWowClassType.getClassType('Hexenmeisterin')
        assert EWowClassType.DEATHKNIGHT == EWowClassType.getClassType('Todesritter')

        LOGGER.debug('class type: {}', EWowClassType.getClassType('Schurke'))
        LOGGER.debug('class type: {}', EWowClassType.getClassType('Schurkin'))
        LOGGER.debug('class type: {}', EWowClassType.getClassType('Hexenmeister'))
        LOGGER.debug('class type: {}', EWowClassType.getClassType('Hexenmeisterin'))
        LOGGER.debug('class type: {}', EWowClassType.getClassType('Todesritter'))
    }
}

