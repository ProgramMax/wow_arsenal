package org.mjp.wow.arsenalparser.domain.enums

import org.slf4j.Logger
import org.slf4j.LoggerFactory

enum EWowProfessionType {
    TAILORING,
    JEWELERY,
    BLACKSMITHING,
    ENCHANTING,
    ALCHEMESTRY,
    ENGINEERING,
    LEATHERWORKING,
    INSCRIPTION,

    MINING,
    HERBALISM,
    SKINNING,

    FISHING,
    FIRSTAID,
    COOKING,
    ARCHAEOLOGY,

    UNKNOWN

    final static BUNDLE = ResourceBundle.getBundle('profession')
    final static LOGGER = LoggerFactory.getLogger(this.class)

    def static EWowProfessionType getProfessionType(final professionName) {
        def professionType

        switch (professionName.toLowerCase()) {
            case BUNDLE.getString(TAILORING.name()).toLowerCase():
                professionType = TAILORING
                break
            case BUNDLE.getString(JEWELERY.name()).toLowerCase():
                professionType = JEWELERY
                break
            case BUNDLE.getString(BLACKSMITHING.name()).toLowerCase():
                professionType = BLACKSMITHING
                break
            case BUNDLE.getString(ENCHANTING.name()).toLowerCase():
                professionType = ENCHANTING
                break
            case BUNDLE.getString(ALCHEMESTRY.name()).toLowerCase():
                professionType = ALCHEMESTRY
                break
            case BUNDLE.getString(ENGINEERING.name()).toLowerCase():
                professionType = ENGINEERING
                break
            case BUNDLE.getString(LEATHERWORKING.name()).toLowerCase():
                professionType = LEATHERWORKING
                break
            case BUNDLE.getString(INSCRIPTION.name()).toLowerCase():
                professionType = INSCRIPTION
                break
            case BUNDLE.getString(MINING.name()).toLowerCase():
                professionType = MINING
                break
            case BUNDLE.getString(HERBALISM.name()).toLowerCase():
                professionType = HERBALISM
                break
            case BUNDLE.getString(SKINNING.name()).toLowerCase():
                professionType = SKINNING
                break
            case BUNDLE.getString(FISHING.name()).toLowerCase():
                professionType = FISHING
                break
            case BUNDLE.getString(FIRSTAID.name()).toLowerCase():
                professionType = FIRSTAID
                break
            case BUNDLE.getString(COOKING.name()).toLowerCase():
                professionType = COOKING
                break
            case BUNDLE.getString(ARCHAEOLOGY.name()).toLowerCase():
                professionType = ARCHAEOLOGY
                break
            default:
                professionType = UNKNOWN
        }
    }

    def String toString() {
        return BUNDLE.getString(this.name())
    }
}
