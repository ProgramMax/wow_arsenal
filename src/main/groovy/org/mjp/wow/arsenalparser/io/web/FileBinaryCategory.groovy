package org.mjp.wow.arsenalparser.io.web

import java.io.File;
import java.net.URL;

class FileBinaryCategory {
    static leftShift(File a_file, URL a_url) {
        def input
        def output

        try {
            input = a_url.openStream()
            output = new BufferedOutputStream(new FileOutputStream(a_file))
    
            output << input
        } finally {
           input?.close()
           output?.close()
        }
    }
    
    static main(final args) {
        def file = new File("logo.gif")

        use (FileBinaryCategory) {
            file << "http://www.google.com/images/logo.gif".toURL()
        }
    }
}
