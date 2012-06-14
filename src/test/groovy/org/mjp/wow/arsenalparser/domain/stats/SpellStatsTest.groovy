package org.mjp.wow.arsenalparser.domain.stats

import org.junit.Test

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class SpellStatsTest {
    final def LOGGER = LoggerFactory.getLogger(this.class)
    final def TEST_TOSTRING_SIMPLE_PATTERN =
        'SpellStats\\[spellPower=<null>,spellHaste=<null>,' +
        'spellHit=<null>,spellCrit=<null>,spellPenetration=<null>,' +
        'manaRegen=<null>,combatRegen=<null>\\]'
    final def TEST_TOSTRING_ADV_PATTERN =
        'SpellStats\\[spellPower=0,spellHaste=0,spellHit=0,' +
        'spellCrit=0,spellPenetration=0,manaRegen=0,combatRegen=0\\]'
    
    @Test def void testToString() {
        def testSpellStats = new SpellStats()
        
        LOGGER.debug('test pattern: {}', TEST_TOSTRING_SIMPLE_PATTERN)
        LOGGER.debug('test string:  {}', testSpellStats.toString())
            
        LOGGER.debug('test:         {}', 
            testSpellStats.toString() =~ /$TEST_TOSTRING_SIMPLE_PATTERN/)
        assert testSpellStats.toString() =~ /$TEST_TOSTRING_SIMPLE_PATTERN/

        testSpellStats = new SpellStats(spellPower: 0,
            spellHaste: '0', spellHit: '0', spellCrit: '0',
            spellPenetration: '0',  manaRegen: '0', combatRegen: '0')
        
        LOGGER.debug('test pattern: {}', TEST_TOSTRING_ADV_PATTERN)
        LOGGER.debug('test string:  {}', testSpellStats.toString())
            
        LOGGER.debug('test:         {}',
            testSpellStats.toString() =~ /$TEST_TOSTRING_ADV_PATTERN/)
        assert testSpellStats.toString() =~ /$TEST_TOSTRING_ADV_PATTERN/
    }
}

