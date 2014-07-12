package belven.professions;

import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import belven.professions.listeners.PlayerListener;

public class ProfessionManager extends JavaPlugin
{
    private final PlayerListener playerListener = new PlayerListener(this);
    // private final BlockListener blockListener = new BlockListener(this);
    // private final MobListener mobListener = new MobListener(this);

    public HashMap<Player, Profession> CurrentPlayerProfessions = new HashMap<Player, Profession>();

    @Override
    public void onEnable()
    {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(playerListener, this);

        if (this.getServer().getOnlinePlayers().length > 0)
        {
            for (Player currentPlayer : this.getServer().getOnlinePlayers())
            {
                if (currentPlayer != null)
                {
                    AddProfessionToPlayer(currentPlayer);
                }
            }
        }
    }

    public void AddProfessionToPlayer(Player playerToAdd)
    {
        String PlayerName = playerToAdd.getName();
        this.reloadConfig();

        if (!this.getConfig().contains(PlayerName))
        {
            SetProfession(playerToAdd, "DEFAULT");
        }
        else
        {
            String ProfessionString = this.getConfig().getString(
                    PlayerName + ".Profession");

            if (ProfessionString != null)
            {
                if (CurrentPlayerProfessions.get(playerToAdd) == null)

                    CurrentPlayerProfessions.put(playerToAdd,
                            StringToProfession(ProfessionString, playerToAdd));
            }

            this.getServer().broadcastMessage(
                    PlayerName + " was given Profession " + ProfessionString);
        }
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label,
            String[] args)
    {
        Player[] currentPlayers = this.getServer().getOnlinePlayers();
        Player player = (Player) sender;
        String commandSent = cmd.getName();

        if (commandSent.equalsIgnoreCase("bpfarmer"))
        {
            this.SetProfession(player, "Farmer");
            return true;
        }
        else if (commandSent.equalsIgnoreCase("bphunter"))
        {
            this.SetProfession(player, "Hunter");
            return true;
        }
        else if (commandSent.equalsIgnoreCase("bpminer"))
        {
            this.SetProfession(player, "Miner");
            return true;
        }
        else if (commandSent.equalsIgnoreCase("listprofessions"))
        {
            if (currentPlayers != null)
            {
                for (Player currentPlayer : currentPlayers)
                {
                    if (currentPlayer != null)
                    {
                        Profession currentProfession = CurrentPlayerProfessions
                                .get(currentPlayer);

                        if (currentProfession != null)
                        {
                            player.sendMessage(currentPlayer.getName()
                                    + " is a "
                                    + currentProfession.professionName);
                        }
                    }
                }

                return true;
            }
            else
                return false;
        }

        else
            return false;
    }

    public Profession StringToProfession(String professionName, Player player)
    {
        switch (professionName.toLowerCase())
        {
        case "farmer":
            return new Farmer(player, this);
        case "hunter":
            return new Hunter(player, this);
        case "miner":
            return new Miner(player, this);
        default:
            return new Default(player, this);
        }
    }

    public void SetProfession(Player playerToChange, String professionString)
    {
        String PlayerName = playerToChange.getName();

        this.reloadConfig();
        getConfig().set(PlayerName + ".Profession", professionString);

        playerToChange.sendMessage(PlayerName + " is Profession "
                + professionString);

        this.saveConfig();
        CurrentPlayerProfessions.put(playerToChange,
                StringToProfession(professionString, playerToChange));
    }

    @Override
    public void onDisable()
    {
        getLogger().info("Goodbye world!");
        this.saveConfig();
    }

}
