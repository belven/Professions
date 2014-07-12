package belven.professions.listeners;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerLoginEvent;

import belven.professions.Farmer;
import belven.professions.Hunter;
import belven.professions.Miner;
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
    public void onPlayerLoginEvent(PlayerLoginEvent event)
    {
        plugin.AddProfessionToPlayer(event.getPlayer());
    }

    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent event)
    {
        if (!event.isCancelled() && event.getPlayer() != null)
        {
            Player pl = event.getPlayer();
            Profession p = plugin.CurrentPlayerProfessions.get(pl);

            if (p != null)
            {
                if (p instanceof Farmer)
                {
                    ((Farmer) p).BlockBreakEvent(event);
                }
                else if (p instanceof Miner)
                {
                    ((Miner) p).BlockBreakEvent(event);
                }
            }
        }
    }

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onEntityDeathEvent(EntityDeathEvent event)
    {
        EntityDamageEvent ede = event.getEntity().getLastDamageCause();
        Player currentPlayer;

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
        else if (ede.getEntity().getType() == EntityType.ARROW)
        {
            Arrow currentArrow = (Arrow) ede.getEntity();

            if (currentArrow.getShooter().getType() == EntityType.PLAYER)
            {
                currentPlayer = (Player) currentArrow.getShooter();
                if (currentPlayer != null)
                {
                    Profession p = plugin.CurrentPlayerProfessions
                            .get(currentPlayer);
                    if (p instanceof Hunter)
                    {
                        ((Hunter) currentPlayer).EntityDeathEvent(event);
                    }
                }
            }
        }

    }
}