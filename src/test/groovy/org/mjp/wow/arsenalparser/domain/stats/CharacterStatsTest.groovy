package org.mjp.wow.arsenalparser.domain.stats

import org.junit.Test

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class CharacterStatsTest {
    final def LOGGER = LoggerFactory.getLogger(this.class)
    final def TEST_TOSTRING_SIMPLE_PATTERN =
        'CharacterStats\\[basicStats=<null>,commonStats=<null>,' +
        'meleeStats=<null>,rangedStats=<null>,spellStats=<null>,' +
        'defenseStats=<null>]'
    
    @Test def void testToString() {
        def testCharacterStats = new CharacterStats()
        
        LOGGER.debug('test pattern: {}', TEST_TOSTRING_SIMPLE_PATTERN)
        LOGGER.debug('test string:  {}', testCharacterStats.toString())
            
        LOGGER.debug('test:         {}', 
            testCharacterStats.toString() =~ /$TEST_TOSTRING_SIMPLE_PATTERN/)            
        assert testCharacterStats.toString() =~ /$TEST_TOSTRING_SIMPLE_PATTERN/
    }
}

