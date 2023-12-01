package pl.patrykv220.grupowetpcore.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class ItemBuilder {
	private int amount;
	private short data;
	private HashMap<Enchantment, Integer> enchants;
	private Material mat;
	private List<String> lore;
	private String name;

	public ItemBuilder addLore(final String s) {
		this.lore.add(s);
		return this;
	}

	public HashMap<Enchantment, Integer> getEnchants() {
		return this.enchants;
	}

	public ItemBuilder(final Material mat, final short data) {
		this(mat, 1, data);
	}

	public void setData(final short data) {
		this.data = data;
	}

	public void setMat(final Material mat) {
		this.mat = mat;
	}

	public short getData() {
		return this.data;
	}

	public ItemBuilder(final Material mat, final int amount, final short data) {
		final String name = null;
		final boolean data2 = false;
		this.data = (short) (data2 ? 1 : 0);
		this.lore = new ArrayList<String>();
		this.enchants = new HashMap<Enchantment, Integer>();
		this.name = name;
		this.lore = new ArrayList<String>();
		this.enchants = new HashMap<Enchantment, Integer>();
		this.mat = mat;
		this.amount = amount;
		this.data = data;
	}

	public ItemBuilder addEnchant(final Enchantment enchant, final int i) {
		this.enchants.put(enchant, i);
		return this;
	}

	public String getName() {
		return this.name;
	}

	public ItemBuilder(final Material mat, final int amount, final String name) {
		final boolean data = false;
		this.data = (short) (data ? 1 : 0);
		this.lore = new ArrayList<String>();
		this.enchants = new HashMap<Enchantment, Integer>();
		this.mat = mat;
		this.amount = amount;
		this.name = name;
	}

	public List<String> getLore() {
		return this.lore;
	}

	public ItemBuilder setAmount(final int amount) {
		this.amount = amount;
		return this;
	}

	public Material getMat() {
		return this.mat;
	}

	public int getAmount() {
		return this.amount;
	}

	public ItemBuilder addEnchantment(final Enchantment enchant, final int level) {
		if (this.enchants.containsKey(enchant)) {
			this.enchants.remove(enchant);
		}
		this.enchants.put(enchant, level);
		return this;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ItemStack build() {
		final ItemStack is;
		final ItemMeta im = (is = new ItemStack(this.mat, this.amount, this.data)).getItemMeta();
		if (this.name != null) {
			im.setDisplayName(Util.fixColor(this.name));
		}
		if (this.lore != null && !this.lore.isEmpty()) {
			im.setLore((List) Util.fixColor(this.lore));
		}
		final Iterator<Map.Entry<Enchantment, Integer>> iterator2;
		Iterator<Map.Entry<Enchantment, Integer>> iterator = iterator2 = this.enchants.entrySet().iterator();
		while (iterator.hasNext()) {
			final Map.Entry e = iterator2.next();
			im.addEnchant((Enchantment) e.getKey(), (int) e.getValue(), true);
			iterator = iterator2;
		}
		final ItemStack itemStack = is;
		itemStack.setItemMeta(im);
		return itemStack;
	}

	public ItemBuilder(final Material mat) {
		this(mat, 1);
	}

	public ItemBuilder(final Material mat, final int amount, final String name, final List<String> lore) {
		final boolean data = false;
		this.data = (short) (data ? 1 : 0);
		this.lore = new ArrayList<String>();
		this.enchants = new HashMap<Enchantment, Integer>();
		this.mat = mat;
		this.amount = amount;
		this.name = name;
		this.lore = lore;
	}

	public ItemBuilder setName(final String name) {
		this.name = name;
		return this;
	}

	public ItemBuilder(final Material mat, final int amount) {
		this(mat, amount, (short) 0);
	}

	public void setLore(final List<String> lore) {
		this.lore = lore;
	}

	public void setEnchants(final HashMap<Enchantment, Integer> enchants) {
		this.enchants = enchants;
	}
}
