package org.mjp.wow.arsenalparser.domain.stats

import org.junit.Test

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class RangedStatsTest {
    final def LOGGER = LoggerFactory.getLogger(this.class)
    final def TEST_TOSTRING_SIMPLE_PATTERN =
        'RangedStats\\[rangedAttackpower=<null>,rangedDamage=null-null,' +
        'rangedDps=<null>,rangedSpeed=null%,rangedHaste=null%,' +
        'rangedHit=\\+null%,rangedCrit=null%\\]'
    final def TEST_TOSTRING_ADV_PATTERN =
        'RangedStats\\[rangedAttackpower=0,rangedDamage=1-2,rangedDps=0.0,' +
        'rangedSpeed=0.0%,rangedHaste=0.0%,rangedHit=\\+0.0%,rangedCrit=0.0%\\]'

    @Test def void testToString() {
        def testRangedStats = new RangedStats()

        LOGGER.debug('test pattern: {}', TEST_TOSTRING_SIMPLE_PATTERN)
        LOGGER.debug('test string:  {}', testRangedStats.toString())

        LOGGER.debug('test:         {}',
            testRangedStats.toString() =~ /$TEST_TOSTRING_SIMPLE_PATTERN/)

        assert testRangedStats.toString() =~ /$TEST_TOSTRING_SIMPLE_PATTERN/

        testRangedStats = new RangedStats(rangedAttackpower: 0,
            minRangedDamage: 1, maxRangedDamage: 2, rangedDps: 0, rangedSpeed: 0,
            rangedHaste: 0, rangedHit: 0, rangedCrit: 0)

        LOGGER.debug('test pattern: {}', TEST_TOSTRING_ADV_PATTERN)
        LOGGER.debug('test string:  {}', testRangedStats.toString())

        LOGGER.debug('test:         {}',
            testRangedStats.toString() =~ /$TEST_TOSTRING_ADV_PATTERN/)

        assert testRangedStats.toString() =~ /$TEST_TOSTRING_ADV_PATTERN/
    }
}

