package belven.professions;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import belvens.professions.resources.functions;

public class Forester extends Profession
{
    public Forester(Player currentPlayer, ProfessionManager instance)
    {
        pOwner = currentPlayer;
        plugin = instance;
    }

    @Override
    public void BlockBreakEvent(BlockBreakEvent event)
    {
        Block b = event.getBlock();
        Material t = b.getType();

        if (functions.isForesterBlock(t))
        {
            int expToDrop = (int) (event.getExpToDrop() * 1.5);

            Random rand = new Random();
            int ran = rand.nextInt(2);
            ran++;
            event.setExpToDrop(expToDrop);

            if (t == Material.COAL_ORE)
            {
                pOwner.getInventory()
                        .addItem(new ItemStack(Material.COAL, ran));
            }
            else if (t == Material.IRON_ORE)
            {
                pOwner.getInventory().addItem(
                        new ItemStack(Material.IRON_ORE, ran));
            }
            else if (t == Material.LAPIS_ORE)
            {
                // pOwner.getInventory().addItem(
                // new ItemStack(Material.lap, ran));
            }
            else if (t == Material.GOLD_ORE)
            {
                pOwner.getInventory().addItem(
                        new ItemStack(Material.GOLD_ORE, ran));
            }
            else if (t == Material.DIAMOND_ORE)
            {
                pOwner.getInventory().addItem(
                        new ItemStack(Material.DIAMOND, ran));
            }
            else if (t == Material.REDSTONE_ORE)
            {
                pOwner.getInventory().addItem(
                        new ItemStack(Material.REDSTONE, ran));
            }
            else if (t == Material.EMERALD_ORE)
            {
                pOwner.getInventory().addItem(
                        new ItemStack(Material.EMERALD, ran));
            }
            else if (t == Material.QUARTZ_ORE)
            {
                pOwner.getInventory().addItem(
                        new ItemStack(Material.QUARTZ, ran));
            }
        }
    }
}
