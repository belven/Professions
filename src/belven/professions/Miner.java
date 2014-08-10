package belven.professions;

import java.util.Collection;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import belvens.professions.resources.functions;

public class Miner extends Profession
{
    public Miner(Player currentPlayer, ProfessionManager instance)
    {
        pOwner = currentPlayer;
        plugin = instance;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void BlockBreakEvent(BlockBreakEvent event)
    {
        Block b = event.getBlock();
        Material t = b.getType();

        if (functions.isOreBlock(t) && !b.hasMetadata("Player Placed"))
        {
            int expToDrop = (int) (event.getExpToDrop() * 1.5);

            Random rand = new Random();
            int ran = rand.nextInt(2);
            ran++;
            event.setExpToDrop(expToDrop);

            Collection<ItemStack> drops = event.getBlock().getDrops();

            for (ItemStack is : drops)
            {
                int amount = is.getAmount();
                amount += ran;
                is.setAmount(amount);
                pOwner.getInventory().addItem(is);
            }
            event.setCancelled(true);
            event.getBlock().setType(Material.AIR);

            short newDurability = (short) (pOwner.getItemInHand()
                    .getDurability() + 1);
            
            pOwner.getItemInHand().setDurability(newDurability);
            
            pOwner.updateInventory();

            // if (t == Material.COAL_ORE)
            // {
            // pOwner.getInventory()
            // .addItem(new ItemStack(Material.COAL, ran));
            // }
            // else if (t == Material.IRON_ORE)
            // {
            // pOwner.getInventory().addItem(
            // new ItemStack(Material.IRON_ORE, ran));
            // }
            // else if (t == Material.LAPIS_ORE)
            // {
            // // pOwner.getInventory().addItem(
            // // new ItemStack(Material.lap, ran));
            // }
            // else if (t == Material.GOLD_ORE)
            // {
            // pOwner.getInventory().addItem(
            // new ItemStack(Material.GOLD_ORE, ran));
            // }
            // else if (t == Material.DIAMOND_ORE)
            // {
            // pOwner.getInventory().addItem(
            // new ItemStack(Material.DIAMOND, ran));
            // }
            // else if (t == Material.REDSTONE_ORE)
            // {
            // pOwner.getInventory().addItem(
            // new ItemStack(Material.REDSTONE, ran));
            // }
            // else if (t == Material.EMERALD_ORE)
            // {
            // pOwner.getInventory().addItem(
            // new ItemStack(Material.EMERALD, ran));
            // }
            // else if (t == Material.QUARTZ_ORE)
            // {
            // pOwner.getInventory().addItem(
            // new ItemStack(Material.QUARTZ, ran));
            // }
        }
    }
}
