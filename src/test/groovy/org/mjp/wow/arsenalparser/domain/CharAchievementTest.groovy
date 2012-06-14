package org.mjp.wow.arsenalparser.domain

import java.text.SimpleDateFormat
        
import org.junit.Test

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class CharAchievementsTest {
    final def LOGGER = LoggerFactory.getLogger(this.class)
    final def TEST_TOSTRING_SIMPLE_PATTERN =
        'CharAchievements\\[achievement=' +
        'Achievement\\[category=<null>,subCategory=<null>,text=<null>,' +
        'date=<null>,imageId=<null>,location=<null>,points=<null>,' +
        'preAchievementId=<null>,postAchievementId=<null>\\],date=<null>\\]'
    final def TEST_TOSTRING_ADV_PATTERN =
        'CharAchievements\\[achievement=' +
        'Achievement\\[category=<null>,subCategory=<null>,text=<null>,' +
        'date=<null>,imageId=<null>,location=<null>,points=<null>,' +
        'preAchievementId=<null>,postAchievementId=<null>\\],' +
        'date=\\d{2}\\.\\d{2}\\.\\d{2} \\d{2}:\\d{2}\\]'
    
    @Test def void testToString() {
        def testCharAchievements = new CharAchievements() 
        
        LOGGER.debug('test pattern: {}', TEST_TOSTRING_SIMPLE_PATTERN)
        LOGGER.debug('test string:  {}', testCharAchievements.toString())
            
        LOGGER.debug('test:         {}', 
            testCharAchievements.toString() =~ /$TEST_TOSTRING_SIMPLE_PATTERN/) 
        assert testCharAchievements.toString() =~ /$TEST_TOSTRING_SIMPLE_PATTERN/
        
        testCharAchievements = new CharAchievements(date: new Date()) 
        
        LOGGER.debug('test pattern: {}', TEST_TOSTRING_ADV_PATTERN)
        LOGGER.debug('test string:  {}', testCharAchievements.toString())
            
        LOGGER.debug('test:         {}', 
            testCharAchievements.toString() =~ /$TEST_TOSTRING_ADV_PATTERN/) 
        assert testCharAchievements.toString() =~ /$TEST_TOSTRING_ADV_PATTERN/
    }
}
