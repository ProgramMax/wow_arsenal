package org.mjp.wow.arsenalparser.io.web

import org.testng.annotations.AfterTest
import org.testng.annotations.BeforeTest
import org.testng.annotations.Test

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertTrue

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import bsh.This;

class DownloaderTest {
    final static def LOGGER = LoggerFactory.getLogger(DownloaderTest.class)

    def OFFLINE_TESTURL = 'data/http/commons.apache.org/proper/commons-math/userguide/analysis.html'
    def ONLINE_TESTURL =  'http://commons.apache.org/proper/commons-math/userguide/analysis.html'

    def oUT
    def testLocalFile
    def testDownloadTargetFile

    @BeforeTest
    def void setUp() {
        this.oUT = new Downloader('temp')
        this.testLocalFile = new File('temp/analysis.html')

        this.testLocalFile.delete()

        this.testDownloadTargetFile = new File(OFFLINE_TESTURL)
    }

    @AfterTest
    def void tearDown() {
        this.oUT = null

        this.testLocalFile.delete()
        this.testLocalFile = null

        this.testDownloadTargetFile = new File(OFFLINE_TESTURL)
    }

    @Test
    def void testDownload() {
        LOGGER.debug('destination file exists: {}', this.testDownloadTargetFile.exists())
        assertTrue this.testDownloadTargetFile.exists()

        this.oUT.download(OFFLINE_TESTURL)

        LOGGER.debug('downloaded file exists: {}', this.testLocalFile.exists())
        assertTrue this.testLocalFile.exists()

        LOGGER.debug('downloaded file size is correct: {}', 5257, this.testLocalFile.size())
        assertEquals 5257, this.testLocalFile.size()
    }

    @Test
    def void testFixLocalUrl() {
        def testDownloader = new Downloader('temp')

        assertEquals('file:analysis.html', testDownloader.fixLocalUrl('analysis.html'))
        assertEquals('file:index.html', testDownloader.fixLocalUrl('file:index.html'))
        assertEquals('http://www.heise.de/', testDownloader.fixLocalUrl('http://www.heise.de/'))
    }

    @Test
    def void testGetFileName() {
        def testDownloader = new Downloader('temp')

        assertEquals('analysis.html', testDownloader.getFileName('temp/analysis.html'))
        assertEquals('index.html', testDownloader.getFileName('http://www.heise.de/'))
    }
}
