package org.mjp.wow.arsenalparser.domain

import org.junit.Test

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import org.mjp.wow.arsenalparser.domain.enums.EWowClassType
import org.mjp.wow.arsenalparser.domain.enums.EWowFractionType
import org.mjp.wow.arsenalparser.domain.enums.EWowRaceType

class CharBasicsTest {
    final def LOGGER = LoggerFactory.getLogger(this.class)
    final def TEST_TOSTRING_SIMPLE_PATTERN =
        'CharBasics\\[classType=<null>,raceType=<null>,level=<null>,guild=<null>,' +
        'title=<null>,fractionType=<null>\\]'
    final def TEST_TOSTRING_ADV_PATTERN =
        'CharBasics\\[classType=Schurke,raceType=Gnom,level=85,guild=guild,' +
        'title=title,fractionType=Allianz\\]'

    @Test def void testToString() {
        def testCharBasics = new CharBasics()

        LOGGER.debug('test pattern: {}', TEST_TOSTRING_SIMPLE_PATTERN)
        LOGGER.debug('test string:  {}', testCharBasics.toString())

        LOGGER.debug('test:         {}', testCharBasics.toString() =~ /$TEST_TOSTRING_SIMPLE_PATTERN/)
        assert testCharBasics.toString() =~ /$TEST_TOSTRING_SIMPLE_PATTERN/

        testCharBasics = new CharBasics(classType: EWowClassType.ROGUE, raceType: EWowRaceType.GNOME,
            level: 85, guild: 'guild', title: 'title', fractionType: EWowFractionType.ALLIANCE)

        LOGGER.debug('test pattern: {}', TEST_TOSTRING_ADV_PATTERN)
        LOGGER.debug('test string:  {}', testCharBasics.toString())

        LOGGER.debug('test:         {}', testCharBasics.toString() =~ /$TEST_TOSTRING_ADV_PATTERN/)
        assert testCharBasics.toString() =~ /$TEST_TOSTRING_ADV_PATTERN/
    }
}

