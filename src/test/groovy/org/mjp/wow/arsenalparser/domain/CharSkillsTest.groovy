package org.mjp.wow.arsenalparser.domain

import org.junit.Test

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class CharSkillsTest {
    final def LOGGER = LoggerFactory.getLogger(this.class)
    final def TEST_TOSTRING_SIMPLE_PATTERN =
        'CharSkills\\[skillList=<null>,role=<null>,activeSkill=<null>\\]'
    final def TEST_TOSTRING_ADV_PATTERN =
        'CharSkills\\[skillList=\\{Assassination,Assassination\\},' +
        'role=\\{meleedps,meleedps\\},activeSkill=0\\]'

    @Test def void testToString() {
        def testCharSkill = new CharSkills()

        LOGGER.debug('test pattern: {}', TEST_TOSTRING_SIMPLE_PATTERN)
        LOGGER.debug('test string:  {}', testCharSkill.toString())

        LOGGER.debug('test:         {}',
            testCharSkill.toString() =~ /$TEST_TOSTRING_SIMPLE_PATTERN/)
        assert testCharSkill.toString() =~ /$TEST_TOSTRING_SIMPLE_PATTERN/

        testCharSkill = new CharSkills(
            skillList: ['Assassination', 'Assassination'], activeSkill: 0,
            role: ['meleedps', 'meleedps'])

        LOGGER.debug('test pattern: {}', TEST_TOSTRING_ADV_PATTERN)
        LOGGER.debug('test string:  {}', testCharSkill.toString())

        LOGGER.debug('test:         {}',
            testCharSkill.toString() =~ /$TEST_TOSTRING_ADV_PATTERN/)
        assert testCharSkill.toString() =~ /$TEST_TOSTRING_ADV_PATTERN/
    }
}

