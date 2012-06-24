package org.mjp.wow.arsenalparser.parser

import java.text.SimpleDateFormat as SDF

import org.junit.After
import org.junit.Before
import org.junit.Test
import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertTrue

import org.slf4j.LoggerFactory

import org.mjp.wow.arsenalparser.domain.enums.EWowClassType
import org.mjp.wow.arsenalparser.domain.enums.EWowFractionType
import org.mjp.wow.arsenalparser.domain.enums.EWowRaceType

class CharacterParserTest {
    final def LOGGER = LoggerFactory.getLogger(this.class)

    final def TEST_URL = 'data/http/eu.battle.net/wow/de/character/' +
        'malorne/nadiem/simple/index.html'

    final TEST_CLASS_LIST = ['guild', 'achievements', 'best tip']
    final TEST_CLASS_MAP = ['guild' : 'Empire', 'achievements' : '10350', 'best tip' : '401']

    def testSourceParser
    def testCharParser

    def startTestDate

    @Before def void initTest() {
        this.startTestDate = new Date()

        this.testSourceParser = new SourceParser()

        this.testSourceParser.parseSource('file:' + TEST_URL)

        this.testCharParser = new CharacterParser(
            divElementList: testSourceParser.selectDivElements(),
            spanElementList: testSourceParser.selectSpanElements(),
            liElementList: testSourceParser.selectLiElements(),
            aElementList: testSourceParser.selectAElements())
    }

    @After def void shutdownTest() {
        this.testSourceParser = null
        this.testCharParser = null
    }

    @Test def void testInitParser() {
        testCharParser.initParse()

        LOGGER.debug('a list:          {}', testCharParser.aElementList.size())
        LOGGER.debug('a class map:     {}', testCharParser.aElementsClassMap.size())
        assert 159 == testCharParser.aElementList.size()
        assert 18  == testCharParser.aElementsClassMap.size()
        LOGGER.debug('div list:        {}', testCharParser.divElementList.size())
        LOGGER.debug('div class map:   {}', testCharParser.divElementsClassMap.size())
        assert 260 == testCharParser.divElementList.size()
        assert 68 == testCharParser.divElementsClassMap.size()
        LOGGER.debug('span list:       {}', testCharParser.spanElementList.size())
        LOGGER.debug('span class map:  {}', testCharParser.spanElementsClassMap.size())
        assert 280 == testCharParser.spanElementList.size()
        assert 19  == testCharParser.spanElementsClassMap.size()
        LOGGER.debug('li list:         {}', testCharParser.liElementList.size())
        LOGGER.debug('li class map:    {}', testCharParser.liElementsClassMap.size())
        LOGGER.debug('li dataid map:   {}', testCharParser.liElementsDataidMap.size())
        assert 122 == testCharParser.liElementList.size()
        assert 28  == testCharParser.liElementsClassMap.size()
        assert 40  == testCharParser.liElementsDataidMap.size()
    }

    @Test def void testParseCharacter() {
        testCharParser.initParse()
        def wowChar = testCharParser.parseCharacter()

        for (classKey in TEST_CLASS_LIST) {
            LOGGER.debug(String.format('%-12s -> %s', classKey,
                testCharParser.divElementsClassMap[classKey].content.textExtractor.toString()))

            assert testCharParser.divElementsClassMap.containsKey(classKey)
            assert TEST_CLASS_MAP[classKey] ==
                testCharParser.divElementsClassMap[classKey].content.textExtractor.toString()
        }

        assert 'Empire'             == wowChar.charBasics.guild
        assert 'Juniorprofessorin'  == wowChar.charBasics.title
        assert '10350'              == wowChar.charAchievementPoints
        assert 401                  == wowChar.charEquipment.maximumAverageItemLevel
        assert 401                  == wowChar.charEquipment.actualAverageItemLevel
    }

    @Test def void testParseMainCharacterData() {
        this.testCharParser.initParse()
        def wowChar = this.testCharParser.parseMainCharacterData()
        def endTestDate = new Date()

        LOGGER.info('character:         {}', wowChar.toString())
        LOGGER.info('start test:        {}', new SDF('hh:mm:ss,SSS').format(this.startTestDate))
        LOGGER.info('dwnld time:        {}', new SDF('hh:mm:ss,SSS').format(wowChar.downloadDate))
        LOGGER.info('end test:          {}', new SDF('hh:mm:ss,SSS').format(endTestDate))
        LOGGER.info('test duration, ms: {}', (endTestDate.time - this.startTestDate.time))

        // WoWCharacter[downloadDate=02.06.12 15:43,
        // lastUpdateArsenal=15.04.12 00:00,name=Nadiem,realm=Malorne,
        // realmPool=Vengeance / Rache]
        assert this.startTestDate   <  wowChar.downloadDate
        assert endTestDate          >  wowChar.downloadDate
        assert '15.04.12 00:00'     == new SDF().format(wowChar.lastUpdateArsenal)
        assert 'Nadiem'             == wowChar.name
        assert 'Malorne'            == wowChar.realm
        assert 'Vengeance / Rache'  == wowChar.realmPool
    }

    @Test def void testParseBasicCharacterData() {
        this.testCharParser.initParse()
        def charBasics = this.testCharParser.parseBasicCharacterData()

        LOGGER.info('character basics: {}', charBasics.toString())

        // CharBasics[wowClass=Schurke,race=Gnom,level=85,guild=Empire,
        // title=Juniorprofessorin,fraction=Allianz]
        assert EWowClassType.ROGUE          == charBasics.classType
        assert EWowRaceType.GNOME           == charBasics.raceType
        assert 85                           == charBasics.level
        assert 'Empire'                     == charBasics.guild
        assert 'Juniorprofessorin'          == charBasics.title
        assert EWowFractionType.ALLIANCE    == charBasics.fractionType
    }

    @Test def void testGetBasicStats() {
        this.testCharParser.initParse()
        def basicStats = this.testCharParser.getBasicStats()

        LOGGER.info('character basic stats: {}', basicStats.toString())

        // BasicStats[stamina=7743,agility=6108,strength=137,intellect=69,
        // spirit=91]
        assert 7743 == basicStats.stamina
        assert 6108 == basicStats.agility
        assert 137  == basicStats.strength
        assert 69   == basicStats.intellect
        assert 91   == basicStats.spirit
    }

    @Test def void testGetCommonStats() {
        this.testCharParser.initParse()
        def commonStats = this.testCharParser.getCommonStats()

        LOGGER.info('character common stats: {}', commonStats.toString())

        // CommonStats[mastery=19.25]
        assert 19.25    == commonStats.mastery
    }

    @Test def void testGetMeleeStats() {
        this.testCharParser.initParse()
        def meleeStats = this.testCharParser.getMeleeStats()

        LOGGER.info('character melee stats: {}', meleeStats.toString())

        // MeleeStats[meleeAttackpower=12683,meleeDamage=2952-3807,
        // meleeDps=1940.0/970.0,meleeSpeed=1.74/1.36,expertise=9/9,
        // meleeHaste=3.32%,meleeHit=+4.25%,meleeCrit=24.2%]
        assert 12683    == meleeStats.meleeAttackpower
        assert 2952     == meleeStats.minMeleeDamage
        assert 3807     == meleeStats.maxMeleeDamage
        assert 1940.0   == meleeStats.mainhandMeleeDps
        assert 970.0    == meleeStats.offhandMeleeDps
        assert 1.74     == meleeStats.mainhandMeleeSpeed
        assert 1.36     == meleeStats.offhandMeleeSpeed
        assert 9        == meleeStats.mainhandExpertise
        assert 9        == meleeStats.offhandExpertise
        assert 3.32     == meleeStats.meleeHaste
        assert 4.25     == meleeStats.meleeHit
        assert 24.2     == meleeStats.meleeCrit
    }

    @Test def void testGetRangedStats() {
        this.testCharParser.initParse()
        def rangedStats = this.testCharParser.getRangedStats()

        LOGGER.info('character ranged stats: {}', rangedStats.toString())

        // RangedStats[rangedAttackpower=6373,rangedDamage=2214–2890,
        // rangedDps=1387.9,rangedSpeed=1.84%,rangedHaste=3.32%,
        // rangedHit=+4.25%,rangedCrit=24.2%]
        assert 6373     == rangedStats.rangedAttackpower
        assert 2214     == rangedStats.minRangedDamage
        assert 2890     == rangedStats.maxRangedDamage
        assert 1387.9   == rangedStats.rangedDps
        assert 1.84     == rangedStats.rangedSpeed
        assert 3.32     == rangedStats.rangedHaste
        assert 4.25     == rangedStats.rangedHit
        assert 24.2     == rangedStats.rangedCrit
    }

    @Test def void testGetSpellStats() {
        this.testCharParser.initParse()
        def spellStats = this.testCharParser.getSpellStats()

        LOGGER.info('character spell stats: {}', spellStats.toString())

        // SpellStats[spellPower=59,spellHaste=3,32%,spellHit=+4,99%,spellCrit=5,68%,
        // spellPenetration=195,manaRegen=--,combatRegen=--]
        assert 59       == spellStats.spellPower
        assert '3,32%'  == spellStats.spellHaste
        assert '+4,99%' == spellStats.spellHit
        assert '5,68%'  == spellStats.spellCrit
        assert '195'    == spellStats.spellPenetration
        assert '--'     == spellStats.manaRegen
        assert '--'     == spellStats.combatRegen
    }

    @Test def void testGetDefenseStats() {
        this.testCharParser.initParse()
        def defenseStats = this.testCharParser.getDefenseStats()

        LOGGER.info('character defense stats: {}', defenseStats.toString())

        // DefenseStats[armor=12470,dodge=21.84%,parry=5.0%,block=0.0%,
        // resilience=4090]
        assert 12470    == defenseStats.armor
        assert 21.84    == defenseStats.dodge
        assert 5.0      == defenseStats.parry
        assert 0.0      == defenseStats.block
        assert 4090     == defenseStats.resilience
    }
}

