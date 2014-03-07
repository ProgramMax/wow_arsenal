package org.mjp.wow.arsenalparser.io.web

import net.htmlparser.jericho.Element
import net.htmlparser.jericho.HTMLElementName as HEN
import net.htmlparser.jericho.MasonTagTypes as MTT
import net.htmlparser.jericho.MicrosoftConditionalCommentTagTypes as MCCTT
import net.htmlparser.jericho.PHPTagTypes as PHPTT
import net.htmlparser.jericho.Source
import net.htmlparser.jericho.TextExtractor
import net.htmlparser.jericho.Source

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class HtmlLinkParser {
    final static LOGGER = LoggerFactory.getLogger(HtmlLinkParser.class)
    
    final static def TEST_URL = 'data/http/commons.apache.org/proper/commons-math/userguide/analysis.html'

    def linkList = []
    def linkMap = [ : ]
    def source

    HtmlLinkParser() {
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
    
    /**
     * Method parses an HTML source. This source types are allowed:
     * - File sourceFile
     * - String sourceText
     * - URL sourceUrl
     * @param source html source
     */
    def void parseSource(def source) {
        this.source = new Source(source)
            
        this.source.fullSequentialParse()
    }

    def void selectLinkElements() {
        def linkElementList = this.source.getAllElements(HEN.A)

        LOGGER.debug('link list size: {}', linkElementList.size())

        def index = 0
        for (def linkElement : linkElementList) {
            index++

            String href = linkElement.getAttributeValue('href')
            println href

            if (href) {
                def label = linkElement.content.textExtractor.toString()

                if (label != '' && href != 'javascript:;') {
                    linkList[index] = label
                    linkMap[label] = href

                    LOGGER.debug('+ {} <{}>', label, href)
                } else {
                    LOGGER.debug('- {} <{}>', label, href)
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
    
    static void main(final args) {
        def testParser = new HtmlLinkParser()
        
        testParser.parseSource(new URL('file:' + TEST_URL))
        testParser.selectLinkElements()
        
        testParser.printLinkElements()
    }
}
