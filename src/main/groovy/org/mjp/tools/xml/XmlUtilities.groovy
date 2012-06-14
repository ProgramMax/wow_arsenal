package org.mjp.tools.xml

import net.htmlparser.jericho.Element

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import org.mjp.tools.number.NumberUtilities as NU

class JerichoUtilities {
    final static LOGGER = LoggerFactory.getLogger(JerichoUtilities.class)

    private JerichoUtilities() {
    }

    def static Double getDouble(Map<String, Element> map, def elementName, def nodeName, def index) {
        def text = getText(map, elementName, nodeName, index)

        return NU.getDouble(NU.cleanDoubleText(text))
    }

    def static Double getDouble(Map<String, Element> map, def elementName) {
        def text = this.getText(map, elementName)

        return NU.getDouble(NU.cleanDoubleText(text))
    }

    def static Double getDouble(Element element) {
        def text = this.getText(element)

        return NU.getDouble(NU.cleanDoubleText(text))
    }

    def static Integer getInt(Map<String, Element> map, def elementName, def nodeName, def index) {
        return Integer.parseInt(getText(map, elementName, nodeName, index))
    }

    def static Integer getInt(Map<String, Element> map, def elementName) {
        return Integer.parseInt(getText(map, elementName))
    }

    def static Integer getInt(Element element) {
        return Integer.parseInt(getText(element))
    }

    def static String getText(Map<String, Element> map, def elementName, def nodeName, def index) {
        return SlurperUtilities.getText(map[elementName].toString(), nodeName, index)
    }

    def static String getText(Map<String, Element> map, def elementName) {
        return getText(map[elementName])
    }

    def static String getText(Element element) {
        return element.content.textExtractor.toString()
    }

    def static Map<String, Element> findElementsByAttribute(def keyName, List<Element> elementList) {
        def map = [ : ]

        for (element in elementList) {
            getElementAsMap(element, keyName).each { key, valueElement ->
                if (key) {
                    map[key] = valueElement

                    LOGGER.trace(String.format('%-20s: %-20s -> %s\n%s',
                        keyName, key, getText(valueElement),
                        '-' * 80))
                }

            }
        }

        return map
    }

    def static String findElementByAttribute(Element element, def filterName, attributeName) {
        def attributeValue = element.getAttributeValue attributeName
        def elementContent

        if (attributeValue == filterName) {
            elementContent = getText(element)

            LOGGER.trace('element content: {}\n{}', attributeValue, elementContent)
        }

        return elementContent
    }

    def static Map<String, Element> getElementAsMap(Element element, def attributeName) {
        def attributeValue = element.getAttributeValue attributeName
        def elementContent = getText(element)
        def map = [ : ]

        if (attributeValue && elementContent) {
            map[attributeValue] = element

            LOGGER.trace(String.format('element content: %-20s -> %s\n%s',
                attributeValue, elementContent, map))
        }

        return map
    }
}

class SlurperUtilities {
    final static LOGGER = LoggerFactory.getLogger(SlurperUtilities.class)

    private SlurperUtilities() {
    }

    def static Integer getInt(def xmlString, def attributeName) {
        return Integer.parseInt(getText(xmlString, attributeName))
    }

    def static Integer getInt(def xmlString) {
        return Integer.parseInt(getText(xmlString))
    }

    def static Integer getInt(def xmlString, def nodeName, def index) {
        return Integer.parseInt(getText(xmlString, nodeName, index))
    }

    def static String getText(def xmlString, def attributeName) {
        return new XmlSlurper().parseText(xmlString)[attributeName]
    }

    def static String getText(def xmlString) {
        return new XmlSlurper().parseText(xmlString).toString().trim()
    }

    def static String getText(def xmlString, def nodeName, def index) {
        return new XmlSlurper().parseText(xmlString)[nodeName][index].toString().trim()
    }
}

