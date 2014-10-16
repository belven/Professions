package belven.professions;

import java.util.Collection;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import belvens.professions.resources.functions;

public class Forester extends Profession {
	public Forester(Player currentPlayer, ProfessionManager instance) {
		pOwner = currentPlayer;
		plugin = instance;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void BlockBreakEvent(BlockBreakEvent event) {
		Block b = event.getBlock();
		Material t = b.getType();

		if (functions.isForesterBlock(t) && functions.isForesterBlock(b.getRelative(BlockFace.UP).getType())
				&& !b.hasMetadata("Player Placed")) {
			int expToDrop = (int) (event.getExpToDrop() * 1.5);
			int ran = new Random().nextInt(2);

			ran++;
			event.setExpToDrop(expToDrop);

			Collection<ItemStack> drops = event.getBlock().getDrops();

			for (ItemStack is : drops) {
				int amount = is.getAmount();
				amount += ran;
				is.setAmount(amount);
				pOwner.getInventory().addItem(is);
			}

			event.setCancelled(true);
			event.getBlock().setType(Material.AIR);

			short newDurability = (short) (pOwner.getItemInHand().getDurability() + 1);
			pOwner.getItemInHand().setDurability(newDurability);
			pOwner.updateInventory();
		}
	}
}
