package org.mjp.wow.arsenalparser.domain.stats

import org.junit.Test

class BasicStatsTest {
    final def TEST_TOSTRING_SIMPLE_PATTERN =
        'BasicStats\\[stamina=<null>,agility=<null>,strength=<null>,' +
        'intellect=<null>,spirit=<null>\\]'
    final def TEST_TOSTRING_ADV_PATTERN =
        'BasicStats\\[stamina=10,agility=10,strength=1,intellect=1,spirit=1\\]'
    
    @Test def void testToString() {
        def testBasicStats = new BasicStats()
        
        assert testBasicStats.toString() =~ /$TEST_TOSTRING_SIMPLE_PATTERN/
        
        testBasicStats = new BasicStats(stamina: 10,
            agility: 10, strength: 1, intellect: 1, spirit: 1)
            
        assert testBasicStats.toString() =~ /$TEST_TOSTRING_ADV_PATTERN/
    }
}

