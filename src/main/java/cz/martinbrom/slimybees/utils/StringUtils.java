package cz.martinbrom.slimybees.utils;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import org.apache.commons.lang.Validate;

import cz.martinbrom.slimybees.core.genetics.enums.ChromosomeType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.common.CommonPatterns;

@ParametersAreNonnullByDefault
public class StringUtils {

    // prevent instantiation
    private StringUtils() {}

    @Nonnull
    public static String humanizeSnake(@Nullable String s) {
        if (s == null || s.isEmpty()) {
            return "";
        }

        boolean spaceBefore = false;
        String[] parts = CommonPatterns.UNDERSCORE.split(s.toLowerCase(Locale.ROOT));
        StringBuilder sb = new StringBuilder();
        for (String part : parts) {
            if (spaceBefore) {
                sb.append(' ');
            }

            sb.append(capitalize(part));
            spaceBefore = true;
        }

        return sb.toString();
    }

    @Nonnull
    public static String capitalize(String s) {
        if (s.isEmpty() || s.length() == 1) {
            return s;
        }

        return s.substring(0, 1).toUpperCase(Locale.ROOT) + s.substring(1);
    }

    @Nonnull
    public static String nameToUid(ChromosomeType type, String name) {
        Validate.notNull(type, "Given chromosome type cannot be null!");
        Validate.notEmpty(name, "Given species name cannot be null or empty!");

        return (type.name() + ":" + name).toLowerCase(Locale.ROOT);
    }

    @Nonnull
    public static String uidToName(String uid) {
        if (uid.isEmpty()) {
            return "";
        }

        String[] uidParts = uid.split(":");
        
        if (uidParts.length == 1) {
        	return "";
        } else {
        	switch (uidParts[1]) {
        		case "very_low":
        			return "非常低";
        		case "low":
        			return "低";
        		case "normal":
        			return "中等";
        		case "high":
        			return "高";
        		case "very_high":
        			return "非常高";
        		case "very_short":
        			return "非常短";
        		case "short":
        			return "短";
        		case "long":
        			return "长";
        		case "very_long":
        			return "非常长";
        		case "none":
        			return "无";
        		case "oxeye_daisy":
        			return "滨菊";
        		case "wheat":
        			return "小麦";
        		case "sugar_cane":
        			return "甘蔗";
        		case "melon":
        			return "西瓜";
        		case "pumpkin":
        			return "南瓜";
        		case "potato":
        			return "马铃薯";
        		case "carrot":
        			return "胡萝卜";
        		case "beetroot":
        			return "甜菜根";
        		case "cocoa":
        			return "可可豆";
        		case "berry":
        			return "浆果";
        		case "regeneration":
        			return "再生";
        		case "firework":
        			return "火焰";
        		default:
        			return "";
        	}
        }
    }

}
