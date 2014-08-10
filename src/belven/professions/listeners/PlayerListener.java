package belven.professions.listeners;

import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.metadata.FixedMetadataValue;

import belven.professions.Hunter;
import belven.professions.Profession;
import belven.professions.ProfessionManager;

public class PlayerListener implements Listener
{
    private final ProfessionManager plugin;

    public PlayerListener(ProfessionManager instance)
    {
        plugin = instance;
    }

    @EventHandler
    public void onPlayerInteractEventSigns(PlayerInteractEvent event)
    {
        Sign currentSign;
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK)
        {
            if (event.getClickedBlock().getType() == Material.SIGN)
            {
                currentSign = (Sign) event.getClickedBlock();

                if (currentSign.getLine(0) != null
                        && currentSign.getLine(0).contentEquals("[Profession]"))
                {
                    plugin.SetProfession(event.getPlayer(),
                            currentSign.getLine(1));
                }
            }
            else if (event.getClickedBlock().getType() == Material.WALL_SIGN)
            {
                currentSign = (Sign) event.getClickedBlock().getState();

                if (currentSign.getLine(0) != null
                        && currentSign.getLine(0).contentEquals("[Profession]"))
                {
                    plugin.SetProfession(event.getPlayer(),
                            currentSign.getLine(1));
                }
            }
        }
    }

    @EventHandler
    public void onPlayerLoginEvent(PlayerLoginEvent event)
    {
        plugin.AddProfessionToPlayer(event.getPlayer());
    }

    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent event)
    {
        plugin.CurrentPlayerProfessions.get(event.getPlayer()).BlockBreakEvent(
                event);
    }

    @EventHandler
    public void onBlockPlaceEvent(BlockPlaceEvent event)
    {
        event.getBlockPlaced().setMetadata("Player Placed",
                new FixedMetadataValue(plugin, "Player Placed"));
    }

    @EventHandler
    public void onEntityDeathEvent(EntityDeathEvent event)
    {
        EntityDamageEvent ede = event.getEntity().getLastDamageCause();
        // Player currentPlayer;

        if (ede == null)
        {
            return;
        }

        if (ede.getEntity() instanceof LivingEntity)
        {
            LivingEntity damager = (LivingEntity) ede.getEntity();

            Player pl = (Player) damager.getKiller();
            Profession p = plugin.CurrentPlayerProfessions.get(pl);

            if (p != null)
            {
                if (p instanceof Hunter)
                {
                    ((Hunter) p).EntityDeathEvent(event);
                }
            }
        }
        // else if (ede.getEntity().getType() == EntityType.ARROW)
        // {
        // Arrow currentArrow = (Arrow) ede.getEntity();
        //
        // if (currentArrow.getShooter().getType() == EntityType.PLAYER)
        // {
        // currentPlayer = (Player) currentArrow.getShooter();
        // if (currentPlayer != null)
        // {
        // Profession p = plugin.CurrentPlayerProfessions
        // .get(currentPlayer);
        // if (p instanceof Hunter)
        // {
        // ((Hunter) currentPlayer).EntityDeathEvent(event);
        // }
        // }
        // }
        // }

    }
}