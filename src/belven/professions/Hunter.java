package belven.professions;

import java.util.Iterator;
import java.util.Random;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class Hunter extends Profession
{
    public Hunter(Player currentPlayer, ProfessionManager instance)
    {
        pOwner = currentPlayer;
        plugin = instance;
    }

    public void EntityDeathEvent(EntityDeathEvent event)
    {
        Random rand = new Random();

        Iterator<ItemStack> eventDrops = event.getDrops().iterator();

        while (eventDrops.hasNext())
        {
            int ran = rand.nextInt(3);
            ran++;

            ItemStack is = eventDrops.next();
            event.getDrops().add(new ItemStack(is.getType(), ran));
        }

    }
}
