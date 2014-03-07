package org.mjp.wow.arsenalparser.parser

import org.junit.Test
import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertTrue

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ParserTest {
    final static def LOGGER = LoggerFactory.getLogger(ParserTest.class)

    final static def TEST_URL = 'data/http/eu.battle.net/wow/de/character/' +
        'malorne/nadiem/simple/index.html'

    final TEST_LINK_LIST = ['Nadiem @ Malorne', 'Nadiem', 'Empire', 'Gnom',
        'Meucheln', 'Schurkin', '10350']
    final TEST_LINK_MAP = [
        'Nadiem @ Malorne'
            : 'http://eu.battle.net/wow/de/character/malorne/nadiem/simple',
        'Nadiem'
            : 'http://eu.battle.net/wow/de/character/malorne/Nadiem/',
        'Empire'
            : 'http://eu.battle.net/wow/de/guild/malorne/Empire/?character=nadiem',
        'Gnom'     : 'http://eu.battle.net/wow/de/game/race/gnome',
        'Meucheln'
            : 'http://eu.battle.net/wow/de/character/malorne/Nadiem/talent/',
        'Schurkin' : 'http://eu.battle.net/wow/de/game/class/rogue',
        '10350'
            : 'http://eu.battle.net/wow/de/character/malorne/Nadiem/achievement']

    @Test def void testSelectLinkElements() {
        def testSourceParser = new SourceParser()

        testSourceParser.parseSource('file:' + TEST_URL)
        testSourceParser.selectLinkElements()

        assert 159 == testSourceParser.linkList.size()
        assert 108 == testSourceParser.linkMap.size()

        LOGGER.info('link list size: {}', testSourceParser.linkList.size())
        LOGGER.info('link map size:  {}', testSourceParser.linkMap.size())
        for (link in TEST_LINK_LIST) {
            assert testSourceParser.linkList.contains(link)
            assert testSourceParser.linkMap.containsKey(link)

            LOGGER.debug('{} -> {}', link, testSourceParser.linkMap[link])
            assert testSourceParser.linkMap[link] == TEST_LINK_MAP[link]
        }
    }

    @Test def void testSelectDivElements() {
        def testSourceParser = new SourceParser()

        testSourceParser.parseSource('file:' + TEST_URL)
        def divElementList = testSourceParser.selectDivElements()

        LOGGER.info('div list size: {}', divElementList.size())
        assert 260 == divElementList.size()
    }

    @Test def void testSelectSpanElements() {
        def testSourceParser = new SourceParser()

        testSourceParser.parseSource('file:' + TEST_URL)
        def spanElementList = testSourceParser.selectSpanElements()

        LOGGER.info('span list size: {}', spanElementList.size())
        assert 280 == spanElementList.size()
    }

    @Test def void testSelectLiElements() {
        def testSourceParser = new SourceParser()

        testSourceParser.parseSource('file:' + TEST_URL)
        def liElementList = testSourceParser.selectLiElements()

        LOGGER.info('li list size: {}', liElementList.size())
        assert 122 == liElementList.size()
    }
}
