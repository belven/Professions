package belven.professions;

import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;

public abstract class Profession
{
    public Player pOwner = null;
    public ProfessionManager plugin;
    protected String professionName = "";

    public final String getProfessionName()
    {
        return professionName;
    }
    
    public void BlockBreakEvent(BlockBreakEvent event)
    {
        
    
    }

    @Override
    public boolean equals(Object other)
    {
        if (other == null)
        {
            return false;
        }
        else if (other == this)
        {
            return true;
        }
        else if (!(other instanceof Class))
        {
            return false;
        }
        else
            return false;
    }

}
