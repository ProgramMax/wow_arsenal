package org.mjp.wow.arsenalparser.parser

import java.text.NumberFormat
import java.text.SimpleDateFormat as SDF

import net.htmlparser.jericho.Element

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import org.mjp.wow.arsenalparser.domain.WoWCharacter
import org.mjp.wow.arsenalparser.domain.CharBasics
import org.mjp.wow.arsenalparser.domain.enums.EWowClassType
import org.mjp.wow.arsenalparser.domain.enums.EWowFractionType
import org.mjp.wow.arsenalparser.domain.enums.EWowRaceType
import org.mjp.wow.arsenalparser.domain.stats.BasicStats
import org.mjp.wow.arsenalparser.domain.stats.CharacterStats
import org.mjp.wow.arsenalparser.domain.stats.CommonStats
import org.mjp.wow.arsenalparser.domain.stats.DefenseStats
import org.mjp.wow.arsenalparser.domain.stats.MeleeStats
import org.mjp.wow.arsenalparser.domain.stats.RangedStats
import org.mjp.wow.arsenalparser.domain.stats.SpellStats

import org.mjp.tools.number.NumberUtilities as NU
import org.mjp.tools.xml.JerichoUtilities as JU
import org.mjp.tools.xml.SlurperUtilities as SU

class CharacterParser {
    final static LOGGER = LoggerFactory.getLogger(CharacterParser.class)

    def aElementList
    def aElementsClassMap

    def divElementList
    def divElementsClassMap

    def spanElementList
    def spanElementsClassMap

    def liElementList
    def liElementsClassMap
    def liElementsDataidMap

    def void initParse() {
        this.divElementsClassMap = JU.findElementsByAttribute('class', this.divElementList)
        this.aElementsClassMap = JU.findElementsByAttribute('class', this.aElementList)
        this.spanElementsClassMap = JU.findElementsByAttribute('class', this.spanElementList)
        this.liElementsClassMap = JU.findElementsByAttribute('class', this.liElementList)
        this.liElementsDataidMap = JU.findElementsByAttribute('data-id', this.liElementList)
    }

    def WoWCharacter parseCharacter() {
        def wowChar = this.parseMainCharacterData()
        wowChar.charBasics = this.parseBasicCharacterData()

        wowChar.charEquipment.maximumAverageItemLevel = JU.getInt(this.divElementsClassMap, 'best tip')

        wowChar.charEquipment.actualAverageItemLevel = JU.getInt(this.spanElementsClassMap, 'equipped')

        LOGGER.debug('char talent:   {}', JU.getText(aElementsClassMap, 'spec tip'))

        def charStats = new CharacterStats()
        charStats.basicStats = this.getBasicStats()
        charStats.commonStats = this.getCommonStats()
        charStats.meleeStats = this.getMeleeStats()
        charStats.rangedStats = this.getRangedStats()
        charStats.spellStats = this.getSpellStats()
        charStats.defenseStats = this.getDefenseStats()

        LOGGER.info('character:     {}', wowChar.toString())
        LOGGER.info('char basics:   {}', wowChar.charBasics.toString())
        LOGGER.info('basic stats:   {}', charStats.basicStats.toString())
        LOGGER.info('common stats:  {}', charStats.commonStats.toString())
        LOGGER.info('melee stats:   {}', charStats.meleeStats.toString())
        LOGGER.info('ranged stats:  {}', charStats.rangedStats.toString())
        LOGGER.info('spell stats:   {}', charStats.spellStats.toString())
        LOGGER.info('defense stats: {}', charStats.defenseStats.toString())

        LOGGER.debug('rating:        {}', JU.getText(this.liElementsClassMap, 'rating', 'span', 1))
        LOGGER.debug('kills:         {}', JU.getText(this.liElementsClassMap, 'kills', 'span', 1))

        return wowChar
    }

    public WoWCharacter parseMainCharacterData() {
        def wowChar = new WoWCharacter()

        wowChar.downloadDate = Calendar.instance.time

        def lastUpdateArsenalMatcher =
            (JU.getText(this.divElementsClassMap['summary-lastupdate']) =~ /(\d\d\.\d\d\.\d\d\d\d)/)
        wowChar.lastUpdateArsenal = new SDF('dd.MM.yyyy').parse(lastUpdateArsenalMatcher[0][1])

        wowChar.name = JU.getText(this.divElementsClassMap, 'profile-info', 'div', 0)
        wowChar.realm = JU.getText(this.spanElementsClassMap, 'realm tip')
        wowChar.realmPool = SU.getText(this.spanElementsClassMap['realm tip'].toString(), '@data-battlegroup')
        wowChar.charAchievementPoints = JU.getText(this.divElementsClassMap['achievements'])

        return wowChar
    }

    public CharBasics parseBasicCharacterData() {
        def charBasics = new CharBasics()

        def inte = JU.getInt(this.spanElementsClassMap['level'])

        charBasics.level = inte

        charBasics.title = JU.getText(this.divElementsClassMap, 'title-guild', 'div', 0)
        charBasics.guild = JU.getText(this.divElementsClassMap, 'title-guild', 'div', 1)

        def raceTypeName = JU.getText(this.aElementsClassMap['race'])
        charBasics.raceType = EWowRaceType.getRaceType(raceTypeName)
        charBasics.fractionType = EWowFractionType.getFractionType(charBasics.raceType)

        def classTypeName = JU.getText(this.aElementsClassMap['class'])
        charBasics.classType = EWowClassType.getClassType(classTypeName)

        return charBasics
    }

    public BasicStats getBasicStats() {
        def basicStats = new BasicStats()

        basicStats.stamina = JU.getInt(this.liElementsDataidMap, 'stamina', 'span', 1)
        basicStats.agility = JU.getInt(this.liElementsDataidMap, 'agility', 'span', 1)
        basicStats.strength = JU.getInt(this.liElementsDataidMap, 'strength', 'span', 1)
        basicStats.intellect = JU.getInt(this.liElementsDataidMap, 'intellect', 'span', 1)
        basicStats.spirit = JU.getInt(this.liElementsDataidMap, 'spirit', 'span', 1)

        return basicStats
    }

    public CommonStats getCommonStats() {
        def commonStats = new CommonStats()

        commonStats.mastery = JU.getDouble(this.liElementsDataidMap, 'mastery', 'span', 1)

        return commonStats
    }

    public MeleeStats getMeleeStats() {
        def meleeStats = new MeleeStats()

        meleeStats.meleeAttackpower = JU.getInt(this.liElementsDataidMap, 'meleeattackpower', 'span', 1)

        def meleeDamageText = JU.getText(this.liElementsDataidMap, 'meleedamage', 'span', 1)
        def meleeDamageArray = meleeDamageText =~ /(\d+)\D(\d+)/
        meleeStats.minMeleeDamage = Integer.parseInt(meleeDamageArray[0][1])
        meleeStats.maxMeleeDamage = Integer.parseInt(meleeDamageArray[0][2])

        def meleeDps = JU.getText(this.liElementsDataidMap, 'meleedps', 'span', 1).split(java.util.regex.Pattern.quote("/"))
        meleeStats.mainhandMeleeDps = NU.getDouble(meleeDps[0])
        meleeStats.offhandMeleeDps = NU.getDouble(meleeDps[1])

        def meleeSpeed = JU.getText(this.liElementsDataidMap, 'meleespeed', 'span', 1).split(java.util.regex.Pattern.quote("/"))
        meleeStats.mainhandMeleeSpeed = NU.getDouble(meleeSpeed[0])
        meleeStats.offhandMeleeSpeed = NU.getDouble(meleeSpeed[1])

        def expertise = JU.getText(this.liElementsDataidMap, 'expertise', 'span', 1).split(java.util.regex.Pattern.quote("/"))
        meleeStats.mainhandExpertise = Integer.parseInt(expertise[0])
        meleeStats.offhandExpertise = Integer.parseInt(expertise[1])

        meleeStats.meleeHaste = JU.getDouble(this.liElementsDataidMap, 'meleehaste', 'span', 1)
        meleeStats.meleeHit = JU.getDouble(this.liElementsDataidMap, 'meleehit', 'span', 1)
        meleeStats.meleeCrit = JU.getDouble(this.liElementsDataidMap, 'meleecrit', 'span', 1)

        return meleeStats
    }

    public RangedStats getRangedStats() {
        def rangedStats = new RangedStats()

        rangedStats.rangedAttackpower = JU.getInt(this.liElementsDataidMap, 'rangedattackpower', 'span', 1)
        rangedStats.rangedDamage = JU.getText(this.liElementsDataidMap, 'rangeddamage', 'span', 1)
        rangedStats.rangedDps = JU.getDouble(this.liElementsDataidMap, 'rangeddps', 'span', 1)
        rangedStats.rangedSpeed = JU.getDouble(this.liElementsDataidMap, 'rangedspeed', 'span', 1)
        rangedStats.rangedHaste = JU.getDouble(this.liElementsDataidMap, 'rangedhaste', 'span', 1)
        rangedStats.rangedHit = JU.getDouble(this.liElementsDataidMap, 'rangedhit', 'span', 1)
        rangedStats.rangedCrit = JU.getDouble(this.liElementsDataidMap, 'rangedcrit', 'span', 1)

        return rangedStats
    }

    public SpellStats getSpellStats() {
        def spellStats = new SpellStats()

        spellStats.spellPower = JU.getInt(this.liElementsDataidMap, 'spellpower', 'span', 1)
        spellStats.spellHaste = JU.getText(this.liElementsDataidMap, 'spellhaste', 'span', 1)
        spellStats.spellHit = JU.getText(this.liElementsDataidMap, 'spellhit', 'span', 1)
        spellStats.spellCrit = JU.getText(this.liElementsDataidMap, 'spellcrit', 'span', 1)
        spellStats.spellPenetration = JU.getText(this.liElementsDataidMap, 'spellpenetration', 'span', 1)
        spellStats.manaRegen = JU.getText(this.liElementsDataidMap, 'manaregen', 'span', 1)
        spellStats.combatRegen = JU.getText(this.liElementsDataidMap, 'combatregen', 'span', 1)

        return spellStats
    }

    public DefenseStats getDefenseStats() {
        def defenseStats = new DefenseStats()

        defenseStats.armor = JU.getInt(this.liElementsDataidMap, 'armor', 'span', 1)
        defenseStats.dodge = JU.getDouble(this.liElementsDataidMap, 'dodge', 'span', 1)
        defenseStats.parry = JU.getDouble(this.liElementsDataidMap, 'parry', 'span', 1)
        defenseStats.block = JU.getDouble(this.liElementsDataidMap, 'block', 'span', 1)
        defenseStats.resilience = JU.getInt(this.liElementsDataidMap, 'resilience', 'span', 1)

        return defenseStats
    }
}

