package cz.martinbrom.slimybees.core.genetics.alleles;

import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import cz.martinbrom.slimybees.utils.PatternUtil;
import io.github.thebusybiscuit.slimefun4.libraries.commons.lang.Validate;

@ParametersAreNonnullByDefault
public class Allele implements Comparable<Allele> {

    private final String uid;
    private final String name;
    private final String displayName;
    private final boolean dominant;

    public Allele(String uid, String name, boolean dominant) {
    	
        Validate.notEmpty(uid, "The allele uid must not be null or empty!");
        
        Validate.isTrue(PatternUtil.UID_PATTERN.matcher(uid).matches(), "The allele uid must start with a prefix " +
                "and be in the lower snake case format, got " + uid + "!");
        Validate.notEmpty(name, "The allele name must not be null or empty!");
        Validate.isTrue(PatternUtil.UPPER_SNAKE.matcher(name).matches(), "The allele name must be " +
                "in the upper snake case format, got " + name + "!");
        

        this.uid = uid;
        this.name = name;
        // this.displayName = StringUtils.humanizeSnake(name);
        this.dominant = dominant;
        switch(name) {
        	case "FOREST":
        		this.displayName = "森林";
        		break;
        	case "MEADOWS":
        		this.displayName = "草地";
        		break;
        	case "STONE":
        		this.displayName = "石头";
        		break;
        	case "SANDY":
        		this.displayName = "沙地";
        		break;
        	case "WATER":
        		this.displayName = "水上";
        		break;
        	case "NETHER":
        		this.displayName = "下界";
        		break;
        	case "ENDER":
        		this.displayName = "末地";
        		break;
        	case "COMMON":
        		this.displayName = "普通的";
        		break;
        	case "CULTIVATED":
        		this.displayName = "高雅的";
        		break;
        	case "NOBLE":
        		this.displayName = "高贵的";
        		break;
        	case "MAJESTIC":
        		this.displayName = "雄壮的";
        		break;
        	case "IMPERIAL":
        		this.displayName = "帝王的";
        		break;
        	case "DILIGENT":
        		this.displayName = "辛勤的";
        		break;
        	case "UNWEARY":
        		this.displayName = "粗心的";
        		break;
        	case "INDUSTRIOUS":
        		this.displayName = "刻苦的";
        		break;
        	case "FARMER":
        		this.displayName = "农场";
        		break;
        	case "WHEAT":
        		this.displayName = "小麦";
        		break;
        	case "SUGAR_CANE":
        		this.displayName = "甘蔗";
        		break;
        	case "MELON":
        		this.displayName = "西瓜";
        		break;
        	case "PUMPKIN":
        		this.displayName = "南瓜";
        		break;
        	case "POTATO":
        		this.displayName = "马铃薯";
        		break;
        	case "CARROT":
        		this.displayName = "胡萝卜";
        		break;
        	case "BEETROOT":
        		this.displayName = "甜菜根";
        		break;
        	case "COCOA":
        		this.displayName = "可可豆";
        		break;
        	case "BERRY":
        		this.displayName = "浆果";
        		break;
        	case "GLOW_BERRY":
        		this.displayName = "越橘";
        		break;
        	case "SECRET":
        		this.displayName = "未知";
        		break;
        		
        	case "VERY_LOW":
    			this.displayName = "非常低";
    			break;
    		case "LOW":
    			this.displayName = "低";
    			break;
    		case "NORMAL":
    			this.displayName = "中等";
    			break;
    		case "HIGH":
    			this.displayName = "高";
    			break;
    		case "VERY_HIGH":
    			this.displayName = "非常高";
    			break;
    		case "VERY_SHORT":
    			this.displayName = "非常短";
    			break;
    		case "SHORT":
    			this.displayName = "短";
    			break;
    		case "LONG":
    			this.displayName = "长";
    			break;
    		case "VERY_LONG":
    			this.displayName = "非常长";
    			break;
    		case "AWAY":
    			this.displayName = "远";
    			break;
    		case "VERY_AWAY":
    			this.displayName = "非常远";
    			break;
    		case "NONE":
    			this.displayName = "无";
    			break;
    		case "OXEYE_DAISY":
    			this.displayName = "滨菊";
    			break;
    		case "REGENERATION":
    			this.displayName = "再生";
    			break;
    		case "FIREWORK":
    			this.displayName = "火焰";
    			break;
        	default:
        		this.displayName = name;
        		break;
        	
        }
    }

    @Nonnull
    public String getUid() {
        return uid;
    }

    @Nonnull
    public String getName() {
        return name;
    }

    @Nonnull
    public String getDisplayName() {
        return displayName;
    }

    public boolean isDominant() {
        return dominant;
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Allele allele = (Allele) o;
        return uid.equals(allele.uid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid);
    }

    @Override
    public int compareTo(Allele allele) {
        return getUid().compareTo(allele.getUid());
    }

}
