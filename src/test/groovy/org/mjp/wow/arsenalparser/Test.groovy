package org.mjp.wow.arsenalparser

import net.htmlparser.jericho.*

import org.junit.Test
import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertTrue

import ch.qos.logback.classic.LoggerContext
import ch.qos.logback.core.util.StatusPrinter

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import org.mjp.wow.arsenalparser.domain.WoWCharacter

class DownloaderTest {
    final def LOGGER = LoggerFactory.getLogger(this.class)

    public def void testDownloader() {
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        StatusPrinter.print(lc);

        Downloader dwnld = new Downloader(url: 'data/index.html')

        def name = 'Nadiem'
        def realm = 'Malorne'

        assertEquals('data/index.html', dwnld.url)
        assertTrue(new File(dwnld.url).exists())

        Character character = new Character()

        MicrosoftConditionalCommentTagTypes.register()
        PHPTagTypes.register()
        PHPTagTypes.PHP_SHORT.deregister() // remove PHP short tags for this example otherwise they override processing instructions
        MasonTagTypes.register()

        Source source = new Source(new URL('file:' + dwnld.url))

        // Call fullSequentialParse manually as most of the source will be parsed.
        source.fullSequentialParse()

        println '\nLinks to other documents:'
        List<Element> linkElements = source.getAllElements(HTMLElementName.A)
        for (Element linkElement : linkElements) {
            String href = linkElement.getAttributeValue( 'href')
            if (href == null) continue

            // A element can contain other tags so need to extract the text from it:
            String label=linkElement.getContent().getTextExtractor().toString()
            println(label + ' <' + href + '>')
        }

        println '\nAll text from file (exluding content inside SCRIPT and STYLE elements):\n'
        println source.getTextExtractor().setIncludeAttributes(true).toString()

        println '\nSame again but this time extend the TextExtractor class to also exclude text from P elements and any elements with class=\"control\":\n'
        
        TextExtractor textExtractor = new TextExtractor(source) {
            public boolean excludeElement(StartTag startTag) {
                return startTag.getName() == HTMLElementName.P || 'control'.equalsIgnoreCase(startTag.getAttributeValue('class'))
            }
        };
        println textExtractor.setIncludeAttributes(true).toString()
        
        character.name = name
        character.realm = realm
        
        println '============================================================'
        
        def classNameDivList = ['title', 'guild', 'best tip', 'achievements']
        
        List<Element> divElementList = source.getAllElements(HTMLElementName.DIV)
        for (Element divElement : divElementList) {
            
            for (className in classNameDivList) {
                switch (className) {
                    case 'title':
                        character.title = getElementContent(divElement, className, 'class')
                        break
                    case 'guild':
                        character.guild = getElementContent(divElement, className, 'class')
                        break
                    case 'best tip':
                        character.bestTip = getElementContent(divElement, className, 'class')
                        break
                    case 'achievements':
                        character.charAchievementPoints = getElementContent(divElement, className, 'class')
                        break
                }
            }
        }
        
        println character.toString()
        
        println '============================================================'
        
        def classNameSpanList = ['realm tip', 'equipped']
        
        List<Element> spanElementsList = source.getAllElements(HTMLElementName.SPAN)
        for (Element spanElement : spanElementsList) {
            
            for (className in classNameSpanList) {
                getElementContent(spanElement, className, 'class')
            }
        }
        
        println '============================================================'
        
        def statsNameList = ['agility', 'stamina', 'mastery', 'meleeattackpower',
            'mastery', 'expertise', 'meleehaste', 'meleehit', 'meleecrit',
            'resilience', 'strength', 'intellect', 'spirit', 'meleedamage',
            'meleedps', 'meleespeed', 'rangeddamage', 'rangeddps',
            'rangedattackpower', 'rangedspeed', 'rangedhaste', 'rangedhit',
            'rangedcrit', 'spellpower', 'spellhaste', 'spellhit', 'spellcrit',
            'spellpenetration', 'manaregen', 'combatregen', 'armor', 'dodge',
            'parry', 'block']
            
        List<Element> liElementsList = source.getAllElements(HTMLElementName.LI)
        for (Element liElement : liElementsList) {
            
            for (statName in statsNameList) {
                getElementContent(liElement, statName, 'data-id')
            }
        }
        
        println '============================================================'
        
        def pvpStatsNameList = ['rating', 'kills']
            
        liElementsList = source.getAllElements(HTMLElementName.LI)
        for (Element liElement : liElementsList) {
            
            for (statName in pvpStatsNameList) {
                getElementContent(liElement, statName, 'class')
            }
        }
        
        println '============================================================'
        
        def professionsNameList = ['summary-professions']
            
        def professionsElementsList = source.getAllElements(HTMLElementName.DIV)
        for (Element divElement : professionsElementsList) {
            
            for (professionName in professionsNameList) {
                getElementContent(divElement, professionName, 'class')
            }
        }
    }
    
    def String getElementContent(def element, def name, def attrName) {
        String attr = element.getAttributeValue(attrName)
        
        String label = null
        
        if (attr == name) {
            label = element.getContent().getTextExtractor().toString()
                            
            println 'content: ' + label
            println element.toString()
            println '----------------------------------------------------------'
        }
        
        return label
    }
}
