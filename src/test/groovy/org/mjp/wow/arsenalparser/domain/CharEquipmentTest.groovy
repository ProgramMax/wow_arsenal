package org.mjp.wow.arsenalparser.domain

import org.junit.Test

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class CharEquipmentTest {
    final def LOGGER = LoggerFactory.getLogger(this.class)
    final def TEST_TOSTRING_SIMPLE_PATTERN =
        'CharEquipment\\[equipmentMap=<null>,actualAverageItemLevel=<null>,' +
        'maximumAverageItemLevel=<null>,charStats=<null>\\]'
    final def TEST_TOSTRING_ADV_PATTERN =
        'CharEquipment\\[equipmentMap=\\{\\},actualAverageItemLevel=100,' +
        'maximumAverageItemLevel=200,charStats=<null>\\]'
    
    @Test def void testToString() {
        def testCharEquipment = new CharEquipment()
        
        LOGGER.debug('test pattern: {}', TEST_TOSTRING_SIMPLE_PATTERN)
        LOGGER.debug('test string:  {}', testCharEquipment.toString())
            
        LOGGER.debug('test:         {}', 
            testCharEquipment.toString() =~ /$TEST_TOSTRING_SIMPLE_PATTERN/)
        assert testCharEquipment.toString() =~ /$TEST_TOSTRING_SIMPLE_PATTERN/
        
        testCharEquipment = new CharEquipment(
            equipmentMap: [:], actualAverageItemLevel: 100,
            maximumAverageItemLevel: 200, charStats: null)
        
        LOGGER.debug('test pattern: {}', TEST_TOSTRING_ADV_PATTERN)
        LOGGER.debug('test string:  {}', testCharEquipment.toString())
            
        LOGGER.debug('test:         {}', 
            testCharEquipment.toString() =~ /$TEST_TOSTRING_ADV_PATTERN/)
        assert testCharEquipment.toString() =~ /$TEST_TOSTRING_ADV_PATTERN/
    }
}
