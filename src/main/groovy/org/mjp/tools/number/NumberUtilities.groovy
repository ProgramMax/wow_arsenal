package org.mjp.tools.number

import java.text.NumberFormat as NF

class NumberUtilities {
    private NumberUtilities() {
    }

    public static String cleanDoubleText(final doubleText) {
        return doubleText.replaceAll('[%+]', '')
    }

    public static Double getDouble(final doubleText) {
        return NF.instance.parse(doubleText).doubleValue()
    }
}
