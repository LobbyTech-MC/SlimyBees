package cz.martinbrom.slimybees.setup;

import javax.annotation.ParametersAreNonnullByDefault;

import cz.martinbrom.slimybees.core.genetics.enums.ChromosomeType;

import static cz.martinbrom.slimybees.utils.StringUtils.nameToUid;

/**
 * This class holds a uid for every base allele registered in SlimyBees.
 */
@ParametersAreNonnullByDefault
public class AlleleUids {

    // prevent instantiation
    private AlleleUids() {}
    
    public static final String PRODUCTIVITY_VERY_LOW = nameToUid(ChromosomeType.PRODUCTIVITY, "very_low");
    public static final String PRODUCTIVITY_LOW = nameToUid(ChromosomeType.PRODUCTIVITY, "low");
    public static final String PRODUCTIVITY_NORMAL = nameToUid(ChromosomeType.PRODUCTIVITY, "normal");
    public static final String PRODUCTIVITY_HIGH = nameToUid(ChromosomeType.PRODUCTIVITY, "high");
    public static final String PRODUCTIVITY_VERY_HIGH = nameToUid(ChromosomeType.PRODUCTIVITY, "very_high");

    public static final String FERTILITY_LOW = nameToUid(ChromosomeType.FERTILITY, "low");
    public static final String FERTILITY_NORMAL = nameToUid(ChromosomeType.FERTILITY, "normal");
    public static final String FERTILITY_HIGH = nameToUid(ChromosomeType.FERTILITY, "high");
    public static final String FERTILITY_VERY_HIGH = nameToUid(ChromosomeType.FERTILITY, "very_high");

    public static final String LIFESPAN_VERY_SHORT = nameToUid(ChromosomeType.LIFESPAN, "very_short");
    public static final String LIFESPAN_SHORT = nameToUid(ChromosomeType.LIFESPAN, "short");
    public static final String LIFESPAN_NORMAL = nameToUid(ChromosomeType.LIFESPAN, "normal");
    public static final String LIFESPAN_LONG = nameToUid(ChromosomeType.LIFESPAN, "long");
    public static final String LIFESPAN_VERY_LONG = nameToUid(ChromosomeType.LIFESPAN, "very_long");

    public static final String RANGE_VERY_SHORT = nameToUid(ChromosomeType.RANGE, "very_short");
    public static final String RANGE_SHORT = nameToUid(ChromosomeType.RANGE, "short");
    public static final String RANGE_NORMAL = nameToUid(ChromosomeType.RANGE, "normal");
    public static final String RANGE_LONG = nameToUid(ChromosomeType.RANGE, "long");
    public static final String RANGE_VERY_LONG = nameToUid(ChromosomeType.RANGE, "very_long");

    public static final String PLANT_NONE = nameToUid(ChromosomeType.PLANT, "none");
    public static final String PLANT_OXEYE_DAISY = nameToUid(ChromosomeType.PLANT, "oxeye_daisy");
    public static final String PLANT_WHEAT = nameToUid(ChromosomeType.PLANT, "wheat");
    public static final String PLANT_SUGAR_CANE = nameToUid(ChromosomeType.PLANT, "sugar_cane");
    public static final String PLANT_MELON = nameToUid(ChromosomeType.PLANT, "melon");
    public static final String PLANT_PUMPKIN = nameToUid(ChromosomeType.PLANT, "pumpkin");
    public static final String PLANT_POTATO = nameToUid(ChromosomeType.PLANT, "potato");
    public static final String PLANT_CARROT = nameToUid(ChromosomeType.PLANT, "carrot");
    public static final String PLANT_BEETROOT = nameToUid(ChromosomeType.PLANT, "beetroot");
    public static final String PLANT_COCOA = nameToUid(ChromosomeType.PLANT, "cocoa");
    public static final String PLANT_BERRY = nameToUid(ChromosomeType.PLANT, "berry");

    public static final String EFFECT_NONE = nameToUid(ChromosomeType.EFFECT, "none");
    public static final String EFFECT_REGENERATION = nameToUid(ChromosomeType.EFFECT, "regeneration");
    public static final String EFFECT_FIREWORK = nameToUid(ChromosomeType.EFFECT, "firework");


    /*
    public static final String PRODUCTIVITY_VERY_LOW = nameToUid(ChromosomeType.PRODUCTIVITY, "非常低");
    public static final String PRODUCTIVITY_LOW = nameToUid(ChromosomeType.PRODUCTIVITY, "低");
    public static final String PRODUCTIVITY_NORMAL = nameToUid(ChromosomeType.PRODUCTIVITY, "中");
    public static final String PRODUCTIVITY_HIGH = nameToUid(ChromosomeType.PRODUCTIVITY, "高");
    public static final String PRODUCTIVITY_VERY_HIGH = nameToUid(ChromosomeType.PRODUCTIVITY, "非常高");

    public static final String FERTILITY_LOW = nameToUid(ChromosomeType.FERTILITY, "低");
    public static final String FERTILITY_NORMAL = nameToUid(ChromosomeType.FERTILITY, "中");
    public static final String FERTILITY_HIGH = nameToUid(ChromosomeType.FERTILITY, "高");
    public static final String FERTILITY_VERY_HIGH = nameToUid(ChromosomeType.FERTILITY, "非常高");

    public static final String LIFESPAN_VERY_SHORT = nameToUid(ChromosomeType.LIFESPAN, "非常短");
    public static final String LIFESPAN_SHORT = nameToUid(ChromosomeType.LIFESPAN, "短");
    public static final String LIFESPAN_NORMAL = nameToUid(ChromosomeType.LIFESPAN, "普通");
    public static final String LIFESPAN_LONG = nameToUid(ChromosomeType.LIFESPAN, "长");
    public static final String LIFESPAN_VERY_LONG = nameToUid(ChromosomeType.LIFESPAN, "非常长");

    public static final String RANGE_VERY_SHORT = nameToUid(ChromosomeType.RANGE, "非常短");
    public static final String RANGE_SHORT = nameToUid(ChromosomeType.RANGE, "短");
    public static final String RANGE_NORMAL = nameToUid(ChromosomeType.RANGE, "中");
    public static final String RANGE_LONG = nameToUid(ChromosomeType.RANGE, "长");
    public static final String RANGE_VERY_LONG = nameToUid(ChromosomeType.RANGE, "非常长");

    public static final String PLANT_NONE = nameToUid(ChromosomeType.PLANT, "无");
    public static final String PLANT_OXEYE_DAISY = nameToUid(ChromosomeType.PLANT, "牛眼菊");
    public static final String PLANT_WHEAT = nameToUid(ChromosomeType.PLANT, "小麦");
    public static final String PLANT_SUGAR_CANE = nameToUid(ChromosomeType.PLANT, "甘蔗");
    public static final String PLANT_MELON = nameToUid(ChromosomeType.PLANT, "西瓜");
    public static final String PLANT_PUMPKIN = nameToUid(ChromosomeType.PLANT, "南瓜");
    public static final String PLANT_POTATO = nameToUid(ChromosomeType.PLANT, "马铃薯");
    public static final String PLANT_CARROT = nameToUid(ChromosomeType.PLANT, "胡萝卜");
    public static final String PLANT_BEETROOT = nameToUid(ChromosomeType.PLANT, "甜菜根");
    public static final String PLANT_COCOA = nameToUid(ChromosomeType.PLANT, "可可豆");
    public static final String PLANT_BERRY = nameToUid(ChromosomeType.PLANT, "浆果");

    public static final String EFFECT_NONE = nameToUid(ChromosomeType.EFFECT, "无");
    public static final String EFFECT_REGENERATION = nameToUid(ChromosomeType.EFFECT, "再生");
    public static final String EFFECT_FIREWORK = nameToUid(ChromosomeType.EFFECT, "火焰");
    */
    
    

}
