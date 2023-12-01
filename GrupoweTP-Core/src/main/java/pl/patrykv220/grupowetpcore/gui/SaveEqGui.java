package pl.patrykv220.grupowetpcore.gui;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import pl.patrykv220.grupowetpcore.utils.ChatUtil;
import pl.patrykv220.grupowetpcore.utils.ItemBuilder;




public class SaveEqGui {

	  public static boolean open(Player p)
	  {

	    	final ItemBuilder save = new ItemBuilder(Material.getMaterial(35), 1, (short)5).setName(ChatUtil.fixColor("&2Zapisz swój ekwipunek")).addLore(ChatUtil.fixColor("&7Kliknij, aby zapisać swoje ułożenie ekwipunku."));
	    	final ItemBuilder close = new ItemBuilder(Material.getMaterial(35), 1, (short)14).setName(ChatUtil.fixColor("&4Anuluj zapisywanie")).addLore(ChatUtil.fixColor("&7Kliknij, aby wyjść z zapisywania ekwipunku."));
	        Inventory inv = Bukkit.createInventory((InventoryHolder) p, 9, ChatUtil.fixColor("&8Zapisz ekwipunek:"));
	        final ItemBuilder s1 = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short)15).setName(ChatUtil.fixColor("&8#"));

	        inv.setItem(inv.getSize() - 4, save.build());

	        inv.setItem(inv.getSize() - 6, close.build());

	        p.openInventory(inv);
			return false;
	  }
}
