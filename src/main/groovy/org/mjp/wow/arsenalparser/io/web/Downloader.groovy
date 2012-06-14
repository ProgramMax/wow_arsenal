package org.mjp.wow.arsenalparser.io.web

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class Downloader {
    final def LOGGER = LoggerFactory.getLogger(this.class)
    
    def sourceUrl
    
    Downloader() {}
    Downloader(def sourceUrl) {
        this.sourceUrl = sourceUrl
    }

    /**
     * Download method.
     * destAddress is the destination address in string format
     */
    def download(final def localDir) {
        def filename = localDir + '/' + sourceUrl.tokenize("/")[-1]
        def file = new FileOutputStream(filename)
        def out = new BufferedOutputStream(file)
        
        LOGGER.debug('download address: {}', sourceUrl)
        LOGGER.debug('temp file:        {}', filename)
        
        LOGGER.info('start download')
        out << new URL(sourceUrl).openStream()
        LOGGER.info('download ready')
        
        out.close()
    }
    
    def String checkUrl() {
        def prefixProtocolList = this.sourceUrl.split(':')
        
        def protocol
        if (prefixProtocolList.size() == 2) {
            protocol = prefixProtocolList[0]
        } else {
            this.sourceUrl = 'file:' + this.sourceUrl
            
            protocol = 'file'
        }
            
        LOGGER.debug('used url protocol: {}', protocol)
        LOGGER.info('source url:        {}', this.sourceUrl)
        
        return protocol
    }
}

