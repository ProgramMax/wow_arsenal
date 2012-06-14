package org.mjp.wow.arsenalparser.io.web

import org.junit.Test
import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertTrue

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class DownloaderTest {
    final def LOGGER = LoggerFactory.getLogger(this.class)
    
    def OFFLINE_TESTURL = 'data/http/eu.battle.net/wow/de/character/' +
        'malorne/nadiem/simple/index.html'
    def ONLINE_TESTURL =  'http://eu.battle.net/wow/de/character/' +
        'malorne/nadiem/simple'

    @Test def void testDownload() {
        def testDownloadTargetFile = new File(OFFLINE_TESTURL)
        LOGGER.debug('destination file exists: {}', testDownloadTargetFile.exists())
        assert testDownloadTargetFile.exists()
        
        def downloader = new Downloader(OFFLINE_TESTURL)
        downloader.checkUrl()
        downloader.download('temp')
        
        def testFile = new File('temp/index.html')
        LOGGER.debug('downloaded file exists: {}', testFile.exists())
        assert testFile.exists()
        
        LOGGER.debug('downloaded file size is correct: {}', 57629 == testFile.size())
        assert 57629 == testFile.size()
    }
    
    @Test def void testCheckUrl() {
        def testDownloader = new Downloader('index.html')
        assert 'file' == testDownloader.checkUrl()
        
        testDownloader = new Downloader('file:index.html')
        assert 'file' == testDownloader.checkUrl()
        
        testDownloader = new Downloader('http://www.heise.de')
        assert 'http' == testDownloader.checkUrl()
    }
}

