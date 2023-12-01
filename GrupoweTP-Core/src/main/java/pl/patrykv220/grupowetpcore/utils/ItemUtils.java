package pl.patrykv220.grupowetpcore.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import pl.patrykv220.grupowetpcore.Main;


public class ItemUtils {
	public static Map<String, Enchantment> ENCHANTMENTS;

	public static Enchantment getEnchantment(String g) {
		return ENCHANTMENTS.get(g);
	}

	static {
		(ItemUtils.ENCHANTMENTS = new HashMap<String, Enchantment>()).put("alldamage", Enchantment.DAMAGE_ALL);
		ItemUtils.ENCHANTMENTS.put("alldmg", Enchantment.DAMAGE_ALL);
		ItemUtils.ENCHANTMENTS.put("sharpness", Enchantment.DAMAGE_ALL);
		ItemUtils.ENCHANTMENTS.put("sharp", Enchantment.DAMAGE_ALL);
		ItemUtils.ENCHANTMENTS.put("dal", Enchantment.DAMAGE_ALL);
		ItemUtils.ENCHANTMENTS.put("ardmg", Enchantment.DAMAGE_ARTHROPODS);
		ItemUtils.ENCHANTMENTS.put("baneofarthropods", Enchantment.DAMAGE_ARTHROPODS);
		ItemUtils.ENCHANTMENTS.put("baneofarthropod", Enchantment.DAMAGE_ARTHROPODS);
		ItemUtils.ENCHANTMENTS.put("arthropod", Enchantment.DAMAGE_ARTHROPODS);
		ItemUtils.ENCHANTMENTS.put("dar", Enchantment.DAMAGE_ARTHROPODS);
		ItemUtils.ENCHANTMENTS.put("undeaddamage", Enchantment.DAMAGE_UNDEAD);
		ItemUtils.ENCHANTMENTS.put("smite", Enchantment.DAMAGE_UNDEAD);
		ItemUtils.ENCHANTMENTS.put("du", Enchantment.DAMAGE_UNDEAD);
		ItemUtils.ENCHANTMENTS.put("digspeed", Enchantment.DIG_SPEED);
		ItemUtils.ENCHANTMENTS.put("efficiency", Enchantment.DIG_SPEED);
		ItemUtils.ENCHANTMENTS.put("minespeed", Enchantment.DIG_SPEED);
		ItemUtils.ENCHANTMENTS.put("cutspeed", Enchantment.DIG_SPEED);
		ItemUtils.ENCHANTMENTS.put("ds", Enchantment.DIG_SPEED);
		ItemUtils.ENCHANTMENTS.put("eff", Enchantment.DIG_SPEED);
		ItemUtils.ENCHANTMENTS.put("durability", Enchantment.DURABILITY);
		ItemUtils.ENCHANTMENTS.put("dura", Enchantment.DURABILITY);
		ItemUtils.ENCHANTMENTS.put("unbreaking", Enchantment.DURABILITY);
		ItemUtils.ENCHANTMENTS.put("d", Enchantment.DURABILITY);
		ItemUtils.ENCHANTMENTS.put("thorns", Enchantment.THORNS);
		ItemUtils.ENCHANTMENTS.put("highcrit", Enchantment.THORNS);
		ItemUtils.ENCHANTMENTS.put("thorn", Enchantment.THORNS);
		ItemUtils.ENCHANTMENTS.put("highercrit", Enchantment.THORNS);
		ItemUtils.ENCHANTMENTS.put("t", Enchantment.THORNS);
		ItemUtils.ENCHANTMENTS.put("fireaspect", Enchantment.FIRE_ASPECT);
		ItemUtils.ENCHANTMENTS.put("fire", Enchantment.FIRE_ASPECT);
		ItemUtils.ENCHANTMENTS.put("meleefire", Enchantment.FIRE_ASPECT);
		ItemUtils.ENCHANTMENTS.put("meleeflame", Enchantment.FIRE_ASPECT);
		ItemUtils.ENCHANTMENTS.put("fa", Enchantment.FIRE_ASPECT);
		ItemUtils.ENCHANTMENTS.put("knockback", Enchantment.KNOCKBACK);
		ItemUtils.ENCHANTMENTS.put("kback", Enchantment.KNOCKBACK);
		ItemUtils.ENCHANTMENTS.put("kb", Enchantment.KNOCKBACK);
		ItemUtils.ENCHANTMENTS.put("k", Enchantment.KNOCKBACK);
		ItemUtils.ENCHANTMENTS.put("blockslootbonus", Enchantment.LOOT_BONUS_BLOCKS);
		ItemUtils.ENCHANTMENTS.put("fortune", Enchantment.LOOT_BONUS_BLOCKS);
		ItemUtils.ENCHANTMENTS.put("fort", Enchantment.LOOT_BONUS_BLOCKS);
		ItemUtils.ENCHANTMENTS.put("lbb", Enchantment.LOOT_BONUS_BLOCKS);
		ItemUtils.ENCHANTMENTS.put("mobslootbonus", Enchantment.LOOT_BONUS_MOBS);
		ItemUtils.ENCHANTMENTS.put("mobloot", Enchantment.LOOT_BONUS_MOBS);
		ItemUtils.ENCHANTMENTS.put("looting", Enchantment.LOOT_BONUS_MOBS);
		ItemUtils.ENCHANTMENTS.put("lbm", Enchantment.LOOT_BONUS_MOBS);
		ItemUtils.ENCHANTMENTS.put("oxygen", Enchantment.OXYGEN);
		ItemUtils.ENCHANTMENTS.put("respiration", Enchantment.OXYGEN);
		ItemUtils.ENCHANTMENTS.put("breathing", Enchantment.OXYGEN);
		ItemUtils.ENCHANTMENTS.put("breath", Enchantment.OXYGEN);
		ItemUtils.ENCHANTMENTS.put("o", Enchantment.OXYGEN);
		ItemUtils.ENCHANTMENTS.put("protection", Enchantment.PROTECTION_ENVIRONMENTAL);
		ItemUtils.ENCHANTMENTS.put("prot", Enchantment.PROTECTION_ENVIRONMENTAL);
		ItemUtils.ENCHANTMENTS.put("protect", Enchantment.PROTECTION_ENVIRONMENTAL);
		ItemUtils.ENCHANTMENTS.put("p", Enchantment.PROTECTION_ENVIRONMENTAL);
		ItemUtils.ENCHANTMENTS.put("explosionsprotection", Enchantment.PROTECTION_EXPLOSIONS);
		ItemUtils.ENCHANTMENTS.put("explosionprotection", Enchantment.PROTECTION_EXPLOSIONS);
		ItemUtils.ENCHANTMENTS.put("expprot", Enchantment.PROTECTION_EXPLOSIONS);
		ItemUtils.ENCHANTMENTS.put("blastprotection", Enchantment.PROTECTION_EXPLOSIONS);
		ItemUtils.ENCHANTMENTS.put("blastprotect", Enchantment.PROTECTION_EXPLOSIONS);
		ItemUtils.ENCHANTMENTS.put("pe", Enchantment.PROTECTION_EXPLOSIONS);
		ItemUtils.ENCHANTMENTS.put("fallprotection", Enchantment.PROTECTION_FALL);
		ItemUtils.ENCHANTMENTS.put("fallprot", Enchantment.PROTECTION_FALL);
		ItemUtils.ENCHANTMENTS.put("featherfall", Enchantment.PROTECTION_FALL);
		ItemUtils.ENCHANTMENTS.put("featherfalling", Enchantment.PROTECTION_FALL);
		ItemUtils.ENCHANTMENTS.put("pfa", Enchantment.PROTECTION_FALL);
		ItemUtils.ENCHANTMENTS.put("fireprotection", Enchantment.PROTECTION_FIRE);
		ItemUtils.ENCHANTMENTS.put("flameprotection", Enchantment.PROTECTION_FIRE);
		ItemUtils.ENCHANTMENTS.put("fireprotect", Enchantment.PROTECTION_FIRE);
		ItemUtils.ENCHANTMENTS.put("flameprotect", Enchantment.PROTECTION_FIRE);
		ItemUtils.ENCHANTMENTS.put("fireprot", Enchantment.PROTECTION_FIRE);
		ItemUtils.ENCHANTMENTS.put("flameprot", Enchantment.PROTECTION_FIRE);
		ItemUtils.ENCHANTMENTS.put("pf", Enchantment.PROTECTION_FIRE);
		ItemUtils.ENCHANTMENTS.put("projectileprotection", Enchantment.PROTECTION_PROJECTILE);
		ItemUtils.ENCHANTMENTS.put("projprot", Enchantment.PROTECTION_PROJECTILE);
		ItemUtils.ENCHANTMENTS.put("pp", Enchantment.PROTECTION_PROJECTILE);
		ItemUtils.ENCHANTMENTS.put("silktouch", Enchantment.SILK_TOUCH);
		ItemUtils.ENCHANTMENTS.put("softtouch", Enchantment.SILK_TOUCH);
		ItemUtils.ENCHANTMENTS.put("st", Enchantment.SILK_TOUCH);
		ItemUtils.ENCHANTMENTS.put("waterworker", Enchantment.WATER_WORKER);
		ItemUtils.ENCHANTMENTS.put("aquaaffinity", Enchantment.WATER_WORKER);
		ItemUtils.ENCHANTMENTS.put("watermine", Enchantment.WATER_WORKER);
		ItemUtils.ENCHANTMENTS.put("ww", Enchantment.WATER_WORKER);
		ItemUtils.ENCHANTMENTS.put("firearrow", Enchantment.ARROW_FIRE);
		ItemUtils.ENCHANTMENTS.put("flame", Enchantment.ARROW_FIRE);
		ItemUtils.ENCHANTMENTS.put("flamearrow", Enchantment.ARROW_FIRE);
		ItemUtils.ENCHANTMENTS.put("af", Enchantment.ARROW_FIRE);
		ItemUtils.ENCHANTMENTS.put("arrowdamage", Enchantment.ARROW_DAMAGE);
		ItemUtils.ENCHANTMENTS.put("power", Enchantment.ARROW_DAMAGE);
		ItemUtils.ENCHANTMENTS.put("arrowpower", Enchantment.ARROW_DAMAGE);
		ItemUtils.ENCHANTMENTS.put("ad", Enchantment.ARROW_DAMAGE);
		ItemUtils.ENCHANTMENTS.put("arrowknockback", Enchantment.ARROW_KNOCKBACK);
		ItemUtils.ENCHANTMENTS.put("arrowkb", Enchantment.ARROW_KNOCKBACK);
		ItemUtils.ENCHANTMENTS.put("punch", Enchantment.ARROW_KNOCKBACK);
		ItemUtils.ENCHANTMENTS.put("arrowpunch", Enchantment.ARROW_KNOCKBACK);
		ItemUtils.ENCHANTMENTS.put("ak", Enchantment.ARROW_KNOCKBACK);
		ItemUtils.ENCHANTMENTS.put("infinitearrows", Enchantment.ARROW_INFINITE);
		ItemUtils.ENCHANTMENTS.put("infarrows", Enchantment.ARROW_INFINITE);
		ItemUtils.ENCHANTMENTS.put("infinity", Enchantment.ARROW_INFINITE);
		ItemUtils.ENCHANTMENTS.put("infinite", Enchantment.ARROW_INFINITE);
		ItemUtils.ENCHANTMENTS.put("unlimited", Enchantment.ARROW_INFINITE);
		ItemUtils.ENCHANTMENTS.put("unlimitedarrows", Enchantment.ARROW_INFINITE);
		ItemUtils.ENCHANTMENTS.put("ai", Enchantment.ARROW_INFINITE);
	}

	

	public static Material getMaterialfromString(final String mat) {
		final Material m = Material.getMaterial(mat.toUpperCase());
		if (m != null) {
			return m;
		}
		return Material.AIR;
	}

	public static boolean has(final List<ItemStack> items, final Player player) {
		for (final ItemStack is : items) {
			if (getAmountOfItem(is.getType(), player, is.getDurability()) < is.getAmount()) {
				return false;
			}
		}
		return true;
	}

	public static int getAmountOfItem(final Material material, final Player player, final short durability) {
		int amount = 0;
		ItemStack[] contents;
		for (int length = (contents = player.getInventory().getContents()).length, i = 0; i < length; ++i) {
			final ItemStack itemStack = contents[i];
			if (itemStack != null && itemStack.getType().equals((Object) material)
					&& itemStack.getDurability() == durability) {
				amount += itemStack.getAmount();
			}
		}
		return amount;
	}

	public static void remove(final List<ItemStack> items, final Player player) {
		for (final ItemStack is : items) {
			remove(is, player, is.getAmount());
		}
	}

	public static void remove(final ItemStack is, final Player player, final int amount) {
		int removed = 0;
		boolean all = false;
		final List<ItemStack> toRemove = new ArrayList<ItemStack>();
		final ItemStack[] contents = player.getInventory().getContents();
		for (int i = 0; i < contents.length; ++i) {
			final ItemStack item = contents[i];
			if (item != null) {
				if (!item.getType().equals((Object) Material.AIR)) {
					if (item.getType().equals((Object) is.getType())) {
						if (item.getDurability() == is.getDurability()) {
							if (!all) {
								if (removed != amount) {
									if (item.getAmount() == amount) {
										if (removed == 0) {
											toRemove.add(item.clone());
											all = true;
											removed = item.getAmount();
										} else {
											final int a = amount - removed;
											final ItemStack s = item.clone();
											s.setAmount(a);
											toRemove.add(s);
											removed += a;
											all = true;
										}
									} else if (item.getAmount() > amount) {
										if (removed == 0) {
											final ItemStack s2 = item.clone();
											s2.setAmount(amount);
											toRemove.add(s2);
											all = true;
											removed = amount;
										} else {
											final int a = amount - removed;
											final ItemStack s = item.clone();
											s.setAmount(a);
											toRemove.add(s);
											removed += a;
											all = true;
										}
									} else if (item.getAmount() < amount) {
										if (removed == 0) {
											toRemove.add(item.clone());
											removed = item.getAmount();
										} else {
											final int a = amount - removed;
											if (a == item.getAmount()) {
												toRemove.add(item.clone());
												removed += item.getAmount();
												all = true;
											} else if (item.getAmount() > a) {
												final ItemStack s = item.clone();
												s.setAmount(a);
												toRemove.add(s);
												removed += a;
												all = true;
											} else if (item.getAmount() < a) {
												toRemove.add(item.clone());
												removed += item.getAmount();
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		removeItem(player, toRemove);
	}

	public static boolean hasItem(final Player player, final ItemStack item) {
		return player != null && item != null && player.getInventory().containsAtLeast(item, item.getAmount());
	}

	public static boolean hasItem(final Player player, final List<ItemStack> items) {
		if (player == null || items == null) {
			return false;
		}
		if (items.isEmpty()) {
			return true;
		}
		for (final ItemStack is : items) {
			if (!player.getInventory().containsAtLeast(is, is.getAmount())) {
				return false;
			}
		}
		return true;
	}

	public static void removeItem(final Player player, final ItemStack item) {
		if (player == null || item == null) {
			return;
		}
		player.getInventory().removeItem(new ItemStack[] { item });
	}

	public static void removeItem(final Player player, final List<ItemStack> items) {
		if (player == null || items == null || items.isEmpty()) {
			return;
		}
		for (final ItemStack is : items) {
			player.getInventory().removeItem(new ItemStack[] { is });
		}
	}

	public static void giveItem(final Player player, final List<ItemStack> items, final Location location) {
		final PlayerInventory inv = player.getInventory();
		final HashMap<Integer, ItemStack> notStored = (HashMap<Integer, ItemStack>) inv
				.addItem((ItemStack[]) items.toArray(new ItemStack[items.size()]));
		for (final Map.Entry<Integer, ItemStack> en : notStored.entrySet()) {
			if (en.getValue() != null) {
				if (en.getValue().getType().equals((Object) Material.AIR)) {
					continue;
				}
				location.getWorld().dropItemNaturally(location, (ItemStack) en.getValue());
			}
		}
	}

	public static void giveItemAsync(final Player player, final List<ItemStack> items, final Location location) {
		Bukkit.getScheduler().runTask((Plugin) Main.getPlugin(), (Runnable) new Runnable() {
			@Override
			public void run() {
				ItemUtils.giveItem(player, items, location);
			}
		});
	}

	public static void itemDrops(final List<ItemStack> items, final Location location) {
		for (final ItemStack is : items) {
			location.getWorld().dropItemNaturally(location, is);
		}
	}

	public static int playerHasItems(final Player player, final Material mat) {
		int size = 0;
		for (final Map.Entry<Integer, ? extends ItemStack> set : player.getInventory().all(mat).entrySet()) {
			size += ((ItemStack) set.getValue()).getAmount();
		}
		return size;
	}


}
