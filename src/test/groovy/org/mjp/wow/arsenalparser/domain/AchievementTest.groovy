package org.mjp.wow.arsenalparser.domain

import org.junit.Test

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class AchievementTest {
    final def LOGGER = LoggerFactory.getLogger(this.class)
    final def TEST_TOSTRING_SIMPLE_PATTERN =
        'Achievement\\[category=<null>,subCategory=<null>,' +
        'text=<null>,date=<null>,imageId=<null>,location=<null>,points=<null>,' +
        'preAchievementId=<null>,postAchievementId=<null>\\]'
    final def TEST_TOSTRING_ADV_PATTERN =
        'Achievement\\[category=category,subCategory=subCategory,' +
        'text=text,date=<null>,imageId=0,location=location,points=0,' +
        'preAchievementId=0,postAchievementId=0\\]'
    
    @Test def void testToString() {
        def testAchievement = new Achievement()
        
        LOGGER.debug('test pattern: {}', TEST_TOSTRING_SIMPLE_PATTERN)
        LOGGER.debug('test string:  {}', testAchievement.toString())
            
        LOGGER.debug('test:         {}', 
            testAchievement.toString() =~ /$TEST_TOSTRING_SIMPLE_PATTERN/)
        assert testAchievement.toString() =~ /$TEST_TOSTRING_SIMPLE_PATTERN/
        
        testAchievement = new Achievement(
            category: 'category', subCategory: 'subCategory', text: 'text',
            date: null, imageId: 0, location: 'location', points: 0,
            preAchievementId: 0, postAchievementId: 0)
        
        LOGGER.debug('test pattern: {}', TEST_TOSTRING_ADV_PATTERN)
        LOGGER.debug('test string:  {}', testAchievement.toString())
            
        LOGGER.debug('test:         {}', 
            testAchievement.toString() =~ /$TEST_TOSTRING_ADV_PATTERN/)
        assert testAchievement.toString() =~ /$TEST_TOSTRING_ADV_PATTERN/
    }
}
