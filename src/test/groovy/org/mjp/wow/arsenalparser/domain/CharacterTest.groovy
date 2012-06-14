package org.mjp.wow.arsenalparser.domain

import org.junit.Test

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import  org.mjp.wow.arsenalparser.domain.enums.EWowFractionType

class WoWCharacterTest {
    final def LOGGER = LoggerFactory.getLogger(this.class)
    final def TEST_TOSTRING_SIMPLE_PATTERN =
        'WoWCharacter\\[downloadDate=<null>,lastUpdateArsenal=<null>,name=<null>,' +
        'realm=<null>,realmPool=<null>\\]'
    final def TEST_TOSTRING_ADV_PATTERN =
        'WoWCharacter\\[downloadDate=\\d{2}\\.\\d{2}\\.\\d{2} \\d{2}:\\d{2},' +
        'lastUpdateArsenal=\\d{2}\\.\\d{2}\\.\\d{2} \\d{2}:\\d{2},' +
        'name=name,realm=realm,realmPool=realmPool\\]'

    @Test def void testToString() {
        def testWoWCharacter = new WoWCharacter()

        LOGGER.debug('test pattern: {}', TEST_TOSTRING_SIMPLE_PATTERN)
        LOGGER.debug('test string:  {}', testWoWCharacter.toString())

        LOGGER.debug('test:         {}',
            testWoWCharacter.toString() =~ /$TEST_TOSTRING_SIMPLE_PATTERN/)

        assert testWoWCharacter.toString() =~ /$TEST_TOSTRING_SIMPLE_PATTERN/

        testWoWCharacter = new WoWCharacter(
            downloadDate: new Date(), lastUpdateArsenal: new Date(), name: 'name',
            realm: 'realm', realmPool: 'realmPool')

        LOGGER.debug('test pattern: {}', TEST_TOSTRING_ADV_PATTERN)
        LOGGER.debug('test string:  {}', testWoWCharacter.toString())

        LOGGER.debug('test:         {}',
            testWoWCharacter.toString() =~ /$TEST_TOSTRING_ADV_PATTERN/)

        assert testWoWCharacter.toString() =~ /$TEST_TOSTRING_ADV_PATTERN/
    }
}
