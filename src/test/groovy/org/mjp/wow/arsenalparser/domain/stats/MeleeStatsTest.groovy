package org.mjp.wow.arsenalparser.domain.stats

import org.junit.Test

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class MeleeStatsTest {
    final def LOGGER = LoggerFactory.getLogger(this.class)
    final def TEST_TOSTRING_SIMPLE_PATTERN =
        'MeleeStats\\[meleeAttackpower=<null>,meleeDamage=null-null,' +
        'meleeDps=null/null,meleeSpeed=null/null,expertise=null/null,' +
        'meleeHaste=null%,meleeHit=\\+null%,meleeCrit=null%\\]'
    final def TEST_TOSTRING_ADV_PATTERN =
        'MeleeStats\\[meleeAttackpower=0,meleeDamage=1-2,meleeDps=2.0/1.0,' +
        'meleeSpeed=1.0/0.5,expertise=5/5,meleeHaste=0.0%,meleeHit=\\+0.0%,meleeCrit=0.0%\\]'

    @Test def void testToString() {
        def testMeleeStats = new MeleeStats()

        LOGGER.debug('test pattern: {}', TEST_TOSTRING_SIMPLE_PATTERN)
        LOGGER.debug('test string:  {}', testMeleeStats.toString())

        LOGGER.debug('test:         {}',
            testMeleeStats.toString() =~ /$TEST_TOSTRING_SIMPLE_PATTERN/)
        assert testMeleeStats.toString() =~ /$TEST_TOSTRING_SIMPLE_PATTERN/

        testMeleeStats = new MeleeStats(meleeAttackpower: 0,
            minMeleeDamage: 1, maxMeleeDamage: 2,
            mainhandMeleeDps: 2, offhandMeleeDps: 1,
            mainhandMeleeSpeed: 1.0, offhandMeleeSpeed: 0.5,
            mainhandExpertise: 5, offhandExpertise: 5,
            meleeHaste: 0, meleeHit: 0, meleeCrit: 0)

        LOGGER.debug('test pattern: {}', TEST_TOSTRING_ADV_PATTERN)
        LOGGER.debug('test string:  {}', testMeleeStats.toString())

        LOGGER.debug('test:         {}',
            testMeleeStats.toString() =~ /$TEST_TOSTRING_ADV_PATTERN/)
        assert testMeleeStats.toString() =~ /$TEST_TOSTRING_ADV_PATTERN/
    }
}

