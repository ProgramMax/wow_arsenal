package org.mjp.wow.arsenalparser.domain.stats

import org.junit.Test

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class DefenseStatsTest {
    final def LOGGER = LoggerFactory.getLogger(this.class)
    final def TEST_TOSTRING_SIMPLE_PATTERN =
        'DefenseStats\\[armor=<null>,dodge=null%,parry=null%,block=null%,' +
        'resilience=<null>\\]'
    final def TEST_TOSTRING_ADV_PATTERN =
        'DefenseStats\\[armor=1000,dodge=10.5%,parry=10.5%,block=10.5%,' +
        'resilience=1000\\]'

    @Test def void testToString() {
        def testDefenseStats = new DefenseStats()

        LOGGER.debug('test pattern: {}', TEST_TOSTRING_SIMPLE_PATTERN)
        LOGGER.debug('test string:  {}', testDefenseStats.toString())

        LOGGER.debug('test:         {}',
            testDefenseStats.toString() =~ /$TEST_TOSTRING_SIMPLE_PATTERN/)
        assert testDefenseStats.toString() =~ /$TEST_TOSTRING_SIMPLE_PATTERN/

        testDefenseStats = new DefenseStats(armor: 1000, dodge: 10.5,
            parry: 10.5, block: 10.5, resilience: 1000)

        LOGGER.debug('test pattern: {}', TEST_TOSTRING_SIMPLE_PATTERN)
        LOGGER.debug('test string:  {}', testDefenseStats.toString())

        LOGGER.debug('test:         {}',
            testDefenseStats.toString() =~ /$TEST_TOSTRING_SIMPLE_PATTERN/)

        assert testDefenseStats.toString() =~ /$TEST_TOSTRING_ADV_PATTERN/
    }
}

