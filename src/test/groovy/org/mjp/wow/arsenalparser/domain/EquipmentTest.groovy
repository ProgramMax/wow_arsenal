package org.mjp.wow.arsenalparser.domain

import org.junit.Test

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class EquipmentTest {
    final def LOGGER = LoggerFactory.getLogger(this.class)
    final def TEST_TOSTRING_SIMPLE_PATTERN =
        'Equipment\\[name=<null>,itemLevel=<null>,position=<null>,proccEffect=<null>,' +
        'socketList=<null>\\]'
    final def TEST_TOSTRING_ADV_PATTERN =
        'Equipment\\[name=name,itemLevel=300,position=14,proccEffect=procc,' +
        'socketList=\\{\\}\\]'
    
    @Test def void testToString() {
        def testEquipment = new Equipment()
        
        LOGGER.debug('test pattern: {}', TEST_TOSTRING_SIMPLE_PATTERN)
        LOGGER.debug('test string:  {}', testEquipment.toString())
            
        LOGGER.debug('test:         {}', 
            testEquipment.toString() =~ /$TEST_TOSTRING_SIMPLE_PATTERN/)
        assert testEquipment.toString() =~ /$TEST_TOSTRING_SIMPLE_PATTERN/
        
        testEquipment = new Equipment(
            name: 'name', itemLevel: 300, position: 14,
            proccEffect: 'procc', socketList: [])
        
        LOGGER.debug('test pattern: {}', TEST_TOSTRING_ADV_PATTERN)
        LOGGER.debug('test string:  {}', testEquipment.toString())
            
        LOGGER.debug('test:         {}', 
            testEquipment.toString() =~ /$TEST_TOSTRING_ADV_PATTERN/)
        assert testEquipment.toString() =~ /$TEST_TOSTRING_ADV_PATTERN/
    }
}
