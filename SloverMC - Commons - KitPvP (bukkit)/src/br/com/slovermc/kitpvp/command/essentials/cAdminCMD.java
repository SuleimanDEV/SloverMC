package br.com.slovermc.kitpvp.command.essentials;

import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.minecraft.util.com.google.common.collect.Maps;

@SuppressWarnings({ "deprecation" })
public class cAdminCMD implements CommandExecutor, Listener {

	public Map<String, ItemStack[]> inv = Maps.newHashMap();
	public Map<String, ItemStack[]> armor = Maps.newHashMap();
	public static ArrayList<UUID> jogadores = new ArrayList<>();
	public static ArrayList<String> admins = new ArrayList<>();

	@SuppressWarnings("unlikely-arg-type")
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("§c§lERRO §fComando apenas in-game.");
			return true;
		}

		Player player = (Player) sender;

		if (label.equalsIgnoreCase("admin")) {
			if (!player.hasPermission("slovermc.admin")) {
				player.sendMessage("§c§lERRO §fVocê não possui permissão para executar este comando.");
				return true;
			}
			if (!admins.contains(player.getName())) {
				admins.add(player.getName());

				for (Player players : Bukkit.getOnlinePlayers()) {
					players.hidePlayer(player);
				}

				jogadores.remove(player.getName());

				inv.put(player.getName(), player.getInventory().getContents());
				armor.put(player.getName(), player.getInventory().getArmorContents());

				player.setGameMode(GameMode.CREATIVE);

				player.getInventory().clear();
				player.getInventory().setArmorContents(null);
				player.setAllowFlight(true);

				ItemStack fast = new ItemStack(Material.MAGMA_CREAM);
				ItemMeta fastm = fast.getItemMeta();
				fastm.setDisplayName("§eQuickAdmin §7(Troca rápida)");
				fast.setItemMeta(fastm);

				player.getInventory().setItem(4, fast);

				player.updateInventory();

				player.sendMessage("§c§lADMIN §fVocê entrou no modo vanish.");
		//		ScoreBoard.UpdateScore1(player);
			} else {
				admins.remove(player.getName());

				for (Player players : Bukkit.getOnlinePlayers()) {
					players.showPlayer(player);
				}

				jogadores.add(player.getUniqueId());

				player.setGameMode(GameMode.SURVIVAL);
				player.setAllowFlight(false);
				player.setFlying(false);

				player.getInventory().setContents(inv.get(player.getName()));
				player.getInventory().setArmorContents(armor.get(player.getName()));

				inv.remove(player.getName());
				armor.remove(player.getName());

				player.updateInventory();

				player.sendMessage("§c§lADMIN §fVocê saiu do modo vanish.");
			//	ScoreBoard.UpdateScore(player);

			}
		}

		return false;
	}
}