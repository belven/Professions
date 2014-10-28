package belven.professions.listeners;

import java.util.Iterator;

import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import belven.professions.Profession;
import belven.professions.ProfessionManager;

public class PlayerListener implements Listener {
	private final ProfessionManager plugin;

	public PlayerListener(ProfessionManager instance) {
		plugin = instance;
	}

	@EventHandler
	public void onPlayerInteractEventSigns(PlayerInteractEvent event) {
		Sign currentSign;
		if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (event.getClickedBlock().getType() == Material.SIGN) {
				currentSign = (Sign) event.getClickedBlock();

				if (currentSign.getLine(0) != null && currentSign.getLine(0).contentEquals("[Profession]")) {
					plugin.SetProfession(event.getPlayer(), currentSign.getLine(1));
				}
			} else if (event.getClickedBlock().getType() == Material.WALL_SIGN) {
				currentSign = (Sign) event.getClickedBlock().getState();

				if (currentSign.getLine(0) != null && currentSign.getLine(0).contentEquals("[Profession]")) {
					plugin.SetProfession(event.getPlayer(), currentSign.getLine(1));
				}
			}
		}
	}

	@EventHandler
	public void onPlayerLoginEvent(PlayerLoginEvent event) {
		plugin.AddProfessionToPlayer(event.getPlayer());
	}

	@EventHandler
	public void onBlockBreakEvent(BlockBreakEvent event) {
		plugin.CurrentPlayerProfessions.get(event.getPlayer()).BlockBreakEvent(event);
	}

	@EventHandler
	public void onBlockPlaceEvent(BlockPlaceEvent event) {
		event.getBlockPlaced().setMetadata("Player Placed", new FixedMetadataValue(plugin, "Player Placed"));
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onEntityDeathEvent(EntityDeathEvent event) {
		EntityDamageEvent ede = event.getEntity().getLastDamageCause();

		// TODO Fix for Player drops
		if (ede != null && ede.getEntity() instanceof LivingEntity && ede.getEntity().getType() != EntityType.PLAYER) {
			LivingEntity damager = (LivingEntity) ede.getEntity();

			Player pl = damager.getKiller();
			Profession p = plugin.CurrentPlayerProfessions.get(pl);

			if (p != null) {
				p.EntityDeathEvent(event);

				Iterator<ItemStack> eventDrops = event.getDrops().iterator();

				while (eventDrops.hasNext()) {
					ItemStack is = eventDrops.next();
					if (is.getMaxStackSize() != 1) {
						p.pOwner.getInventory().addItem(is);
					}
				}

				event.getDrops().clear();
			}
		}
	}
}