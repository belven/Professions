package belvens.professions.resources;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class functions
{
    public static void Heal(LivingEntity entityToHeal, int amountToHeal)
    {
        Damageable dEntityToHeal = (Damageable) entityToHeal;
        double max = dEntityToHeal.getMaxHealth();
        double current = dEntityToHeal.getHealth();

        if (entityToHeal != null)
        {
            for (int i = amountToHeal; i != 0; i--)
            {
                if ((current + i) < max)
                {
                    entityToHeal.setHealth(current + i);
                }
            }
        }
    }

    public static void RestoreHunger(Player entityToRestore, int amountToRestore)
    {
        if (entityToRestore != null)
        {
            for (int i = amountToRestore; i != 0; i--)
            {
                if ((entityToRestore.getFoodLevel() + i) < 10)
                {
                    entityToRestore.setFoodLevel(entityToRestore.getFoodLevel()
                            + i);
                }
            }
        }
    }

    public static void RestoreSaturation(Player entityToRestore,
            int amountToRestore)
    {
        if (entityToRestore != null)
        {
            for (int i = amountToRestore; i != 0; i--)
            {
                if ((entityToRestore.getSaturation() + i) < 10)
                {
                    entityToRestore.setSaturation(entityToRestore
                            .getSaturation() + i);
                }
            }
        }
    }

    public static boolean isArmor(Material material)
    {
        if (material.toString().contains("CHESTPLATE"))
        {
            return true;
        }
        else if (material.toString().contains("LEGGINGS"))
        {
            return true;
        }
        else if (material.toString().contains("HELMET"))
        {
            return true;
        }
        else if (material.toString().contains("BOOTS"))
        {
            return true;
        }

        return false;
    }

    public static boolean isWeapon(Material material)
    {
        if (isAMeeleWeapon(material))
        {
            return true;
        }
        else if (material.toString().contains("BOW"))
        {
            return true;
        }

        return false;
    }

    public static int getEnchantmentMaxLevel(Enchantment e)
    {
        switch (e.toString())
        {
        case "ARROW_DAMAGE":
            return 5;
        case "ARROW_KNOCKBACK":
            return 2;
        case "ARROW_FIRE":
            return 1;
        case "DURABILITY":
            return 3;
        default:
            return 0;
        }
    }

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
        default:
            return false;
        }
    }

    public static boolean isNotInteractiveBlock(Material material)
    {
        switch (material.toString())
        {
        case "CHEST":
            return false;
        case "WORKBENCH":
            return false;
        case "ANVIL":
            return false;
        case "FURNACE":
            return false;
        case "ENCHANTMENT_TABLE":
            return false;
        case "ENDER_CHEST":
            return false;
        case "BED":
            return false;
        case "MINECART":
            return false;
        case "SIGN":
            return false;
        case "BUTTON":
            return false;
        case "LEVER":
            return false;
        default:
            return true;
        }
    }

    public static ArrayList<ItemStack> getAllMeeleWeapons()
    {
        ArrayList<ItemStack> tempWeapons = new ArrayList<ItemStack>();
        tempWeapons.add(new ItemStack(Material.WOOD_SWORD));
        tempWeapons.add(new ItemStack(Material.STONE_SWORD));
        tempWeapons.add(new ItemStack(Material.IRON_SWORD));
        tempWeapons.add(new ItemStack(Material.GOLD_SWORD));
        tempWeapons.add(new ItemStack(Material.DIAMOND_SWORD));
        return tempWeapons;
    }

    public static boolean isAMeeleWeapon(Material material)
    {
        switch (material.toString())
        {
        case "WOOD_SWORD":
            return true;
        case "STONE_SWORD":
            return true;
        case "IRON_SWORD":
            return true;
        case "GOLD_SWORD":
            return true;
        case "DIAMOND_SWORD":
            return true;
        default:
            return false;
        }
    }

    public static boolean isFood(Material material)
    {
        switch (material.toString())
        {
        case "BREAD":
            return true;
        case "RAW_BEEF":
            return true;
        case "RAW_CHICKEN":
            return true;
        case "MELON":
            return true;
        case "ROTTEN_FLESH":
            return true;
        case "COOKED_BEEF":
            return true;
        case "COOKED_CHICKEN":
            return true;
        case "COOKIE":
            return true;
        case "PORK":
            return true;
        case "CARROT":
            return true;
        case "GOLDEN_APPLE":
            return true;
        case "GOLDEN_CARROT":
            return true;
        case "MUSHROOM_SOUP":
            return true;
        default:
            return false;
        }
    }

    public static boolean IsAMob(EntityType currentEntityType)
    {
        if (currentEntityType == EntityType.BLAZE
                || currentEntityType == EntityType.CAVE_SPIDER
                || currentEntityType == EntityType.CREEPER
                || currentEntityType == EntityType.ENDER_DRAGON
                || currentEntityType == EntityType.ENDERMAN
                || currentEntityType == EntityType.GHAST
                || currentEntityType == EntityType.MAGMA_CUBE
                || currentEntityType == EntityType.PIG_ZOMBIE
                || currentEntityType == EntityType.SKELETON
                || currentEntityType == EntityType.SPIDER
                || currentEntityType == EntityType.SLIME
                || currentEntityType == EntityType.WITCH
                || currentEntityType == EntityType.WITHER
                || currentEntityType == EntityType.ZOMBIE)
        {
            return true;
        }
        else
            return false;
    }

    public static int SecondsToTicks(int seconds)
    {
        return (seconds * 20);
    }
}