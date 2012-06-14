package org.mjp.wow.arsenalparser.domain

import org.junit.Test

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class CharProfessionsTest {
    final def LOGGER = LoggerFactory.getLogger(this.class)
    final def TEST_TOSTRING_SIMPLE_PATTERN =
        'CharProfessions\\[firstPrimProfession=<null>,' +
        'secondPrimProfession=<null>,' +
        'firstPrimProfSkillLevel=<null>,secondPrimProfSkillLevel=<null>,' +
        'cookingProfSkillLevel=<null>,fishingProfSkillLevel=<null>,' +
        'firstaidProfSkillLevel=<null>,archeologyProfSkillLevel=<null>\\]'
    final def TEST_TOSTRING_ADV_PATTERN =
        'CharProfessions\\[firstPrimProfession=firstPrimProfession,' +
        'secondPrimProfession=secondPrimProfession,' +
        'firstPrimProfSkillLevel=100,secondPrimProfSkillLevel=100,' +
        'cookingProfSkillLevel=100,fishingProfSkillLevel=100,' +
        'firstaidProfSkillLevel=100,archeologyProfSkillLevel=100\\]'

    @Test def void testToString() {
        def testCharProfessions = new CharProfessions()
        
        LOGGER.debug('test pattern: {}', TEST_TOSTRING_SIMPLE_PATTERN)
        LOGGER.debug('test string:  {}', testCharProfessions.toString())
            
        LOGGER.debug('test:         {}', 
            testCharProfessions.toString() =~ /$TEST_TOSTRING_SIMPLE_PATTERN/)
        assert testCharProfessions.toString() =~ /$TEST_TOSTRING_SIMPLE_PATTERN/
        
        testCharProfessions = new CharProfessions(
            firstPrimProfession: 'firstPrimProfession',
            secondPrimProfession: 'secondPrimProfession',
            firstPrimProfSkillLevel: 100, secondPrimProfSkillLevel: 100,
            cookingProfSkillLevel: 100, fishingProfSkillLevel: 100, 
            firstaidProfSkillLevel: 100, archeologyProfSkillLevel: 100)
        
        LOGGER.debug('test pattern: {}', TEST_TOSTRING_ADV_PATTERN)
        LOGGER.debug('test string:  {}', testCharProfessions.toString())
            
        LOGGER.debug('test:         {}', 
            testCharProfessions.toString() =~ /$TEST_TOSTRING_ADV_PATTERN/)
        assert testCharProfessions.toString() =~ /$TEST_TOSTRING_ADV_PATTERN/
    }
}
