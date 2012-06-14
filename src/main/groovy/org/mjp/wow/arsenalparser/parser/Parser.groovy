package org.mjp.wow.arsenalparser.parser

import net.htmlparser.jericho.Element
import net.htmlparser.jericho.HTMLElementName as HEN
import net.htmlparser.jericho.MasonTagTypes as MTT
import net.htmlparser.jericho.MicrosoftConditionalCommentTagTypes as MCCTT
import net.htmlparser.jericho.PHPTagTypes as PHPTT
import net.htmlparser.jericho.Source
import net.htmlparser.jericho.TextExtractor

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class SourceParser {
    final static LOGGER = LoggerFactory.getLogger(this.class)

    def linkList = []
    def linkMap = [ : ]

    def source

    SourceParser() {
        this.init()
    }

    private void init() {
        MCCTT.register()
        PHPTT.register()
        // remove PHP short tags for this example otherwise they
        // override processing instructions
        PHPTT.PHP_SHORT.deregister()
        MTT.register()
    }

    def void parseSource(def sourceUrl) {
        this.source = new Source(new URL(sourceUrl))

        this.source.fullSequentialParse()
    }

    def void selectLinkElements() {
        def linkElementList = this.source.getAllElements(HEN.A)

        LOGGER.debug('link list size: {}', linkElementList.size())

        def index = 0
        for (def linkElement : linkElementList) {
            index++

            String href = linkElement.getAttributeValue('href')

            if (href) {
                def label = linkElement.content.textExtractor.toString()

                if (label != '' && href != 'javascript:;') {
                    linkList[index] = label
                    linkMap[label] = href

                    if (href.startsWith('http://eu.battle.net/wow/') &&
                            (href.contains('character') || href.contains('class') ||
                            href.contains('race'))) {
                        LOGGER.debug('{} <{}>', label, href)
                    }
                }
            }
        }

        LOGGER.debug('list size: {}', this.linkList.size())
        LOGGER.debug('map size:  {}', this.linkMap.size())
    }

    def void printLinkElements() {
        LOGGER.info('link list')

        for (link in this.linkList) {
            LOGGER.info('{} -> {}', link, this.linkMap[link])
        }
    }

    def void printScriptsWithExtractor() {
        LOGGER.info('All text from file (exluding content inside SCRIPT and STYLE elements):%n{}',
            this.source.textExtractor.includeAttributes(true).toString())
    }

    def void printScriptsWithExtendetExtractor() {
        def textExtractor = new TextExtractor(this.source) {
            def boolean excludeElement(def startTag) {
                return startTag.name == HEN.P ||
                    'control'.equalsIgnoreCase(startTag.attributeValue('class'))
            }
        }

        LOGGER.info('Same again but this time extend the TextExtractor class to also' +
            ' exclude text from P elements and any elements with class="control"%n{}',
            textExtractor.includeAttributes(true).toString())
    }

    def List<Element> selectDivElements() {
        return this.source.getAllElements(HEN.DIV)
    }

    def List<Element> selectAElements() {
        return this.source.getAllElements(HEN.A)
    }

    def List<Element> selectSpanElements() {
        return this.source.getAllElements(HEN.SPAN)
    }

    def List<Element> selectLiElements() {
        return this.source.getAllElements(HEN.LI)
    }

    def static void printElements(final listName, final list) {
        final listSize = list.size()
        final maxPrintListSize = listSize > 5 ? 5 : listSize

        LOGGER.info('{} list size: {}', listName, listSize)

        for (element in list[0 .. maxPrintListSize]) {
            LOGGER.info('{}: {}', listName, element.toString())
        }
    }
}

