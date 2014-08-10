package belven.professions;

import java.util.Random;

import org.bukkit.CropState;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Crops;

import belvens.professions.resources.functions;

public class Farmer extends Profession
{
    public Farmer(Player currentPlayer, ProfessionManager instance)
    {
        pOwner = currentPlayer;
        plugin = instance;
    }

    @Override
    public void BlockBreakEvent(BlockBreakEvent event)
    {
        Block b = event.getBlock();
        Material t = b.getType();

        if (functions.isFoodBlock(t))
        {
            int expToDrop = (int) (event.getExpToDrop() * 1.5);

            Random rand = new Random();
            int ran = rand.nextInt(3);
            ran++;
            event.setExpToDrop(expToDrop);

            if (functions.isAHoe(pOwner.getItemInHand().getType()))
            {
                if (t == Material.CROPS)
                {
                    Crops currentCrop = (Crops) b;

                    if (currentCrop.getState() == CropState.RIPE)
                    {
                        pOwner.getInventory().addItem(
                                new ItemStack(Material.WHEAT, ran));
                        pOwner.getInventory().addItem(
                                new ItemStack(Material.SEEDS, ran));
                    }
                }
                else if (t == Material.POTATO)
                {
                    pOwner.getInventory().addItem(
                            new ItemStack(Material.POTATO_ITEM, ran));
                }
                else if (t == Material.CARROT)
                {
                    pOwner.getInventory().addItem(
                            new ItemStack(Material.CARROT_ITEM, ran));
                }
            }
            else if (t == Material.MELON_BLOCK)
            {
                pOwner.getInventory().addItem(
                        new ItemStack(Material.MELON, ran));
            }
            else if (t == Material.PUMPKIN)
            {
                pOwner.getInventory().addItem(
                        new ItemStack(Material.PUMPKIN, rand.nextInt(1)));
            }
            else if (t == Material.COCOA)
            {
                // @SuppressWarnings("deprecation")
                // ItemStack cocoaBeans = new
                // Dye(3).toItemStack(rand.nextInt(2));
                //
                // if (cocoaBeans != null)
                // {
                // pOwner.getInventory().addItem(cocoaBeans);
                // }
            }
        }
    }
}
