package org.mjp.wow.arsenalparser.domain.stats

import org.junit.Test

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class CommonStatsTest {
    final def LOGGER = LoggerFactory.getLogger(this.class)
    final def TEST_TOSTRING_SIMPLE_PATTERN = 'CommonStats\\[mastery=<null>\\]'
    final def TEST_TOSTRING_ADV_PATTERN = 'CommonStats\\[mastery=0.0\\]'

    @Test def void testToString() {
        def testCommonStats = new CommonStats()

        LOGGER.debug('test pattern: {}', TEST_TOSTRING_SIMPLE_PATTERN)
        LOGGER.debug('test string:  {}', testCommonStats.toString())

        LOGGER.debug('test:         {}',
            testCommonStats.toString() =~ /$TEST_TOSTRING_SIMPLE_PATTERN/)
        assert testCommonStats.toString() =~ /$TEST_TOSTRING_SIMPLE_PATTERN/

        testCommonStats = new CommonStats(mastery: 0)

        LOGGER.debug('test pattern: {}', TEST_TOSTRING_ADV_PATTERN)
        LOGGER.debug('test string:  {}', testCommonStats.toString())

        LOGGER.debug('test:         {}',
            testCommonStats.toString() =~ /$TEST_TOSTRING_ADV_PATTERN/)

        assert testCommonStats.toString() =~ /$TEST_TOSTRING_ADV_PATTERN/
    }
}

