package belven.professions;

import org.bukkit.entity.Player;

public class Default extends Profession
{
    public Default(Player currentPlayer, ProfessionManager instance)
    {
        pOwner = currentPlayer;
        plugin = instance;
    }

}
