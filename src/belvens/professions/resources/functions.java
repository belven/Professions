package belvens.professions.resources;

import org.bukkit.Material;

public class functions
{

    public static boolean isAHoe(Material material)
    {
        switch (material.toString())
        {
        case "WOOD_HOE":
            return true;
        case "STONE_HOE":
            return true;
        case "IRON_HOE":
            return true;
        case "GOLD_HOE":
            return true;
        case "DIAMOND_HOE":
            return true;
        default:
            return false;
        }
    }

    public static boolean isFoodBlock(Material material)
    {
        switch (material.toString())
        {
        case "MELON_BLOCK":
            return true;
        case "CROPS":
            return true;
        case "PUMPKIN":
            return true;
        case "BROWN_MUSHROOM":
            return true;
        case "RED_MUSHROOM":
            return true;
        case "SUGAR_CANE_BLOCK":
            return true;
        case "POTATO":
            return true;
        case "CARROT":
            return true;
        case "COCOA":
            return true;
        default:
            return false;
        }
    }

    public static boolean isForesterBlock(Material material)
    {
        switch (material.toString())
        {
        case "LOG":
            return true;
        case "LEAVES":
            return true;
        default:
            return false;
        }
    }

    public static boolean isOreBlock(Material material)
    {
        switch (material.toString())
        {
        case "COAL_ORE":
            return true;
        case "IRON_ORE":
            return true;
        case "LAPIS_ORE":
            return true;
        case "GOLD_ORE":
            return true;
        case "DIAMOND_ORE":
            return true;
        case "REDSTONE_ORE":
            return true;
        case "EMERALD_ORE":
            return true;
        case "QUARTZ_ORE":
            return true;
        case "OBSIDIAN":
            return true;
        default:
            return false;
        }
    }

}