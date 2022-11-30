package br.com.slovermc.kitpvp.api;

import net.minecraft.server.v1_7_R4.ChatSerializer;
import net.minecraft.server.v1_7_R4.IChatBaseComponent;
import net.minecraft.server.v1_7_R4.PlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.spigotmc.ProtocolInjector;

public final class TabAPI {

	public static void sendTitle(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String message) {
		sendTitle(player, fadeIn, stay, fadeOut, message, null);
	}

	public static void sendSubtitle(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String message) {
		sendTitle(player, fadeIn, stay, fadeOut, null, message);
	}

	public static void sendFullTitle(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String title,
			String subtitle) {
		sendTitle(player, fadeIn, stay, fadeOut, title, subtitle);
	}

	public static void sendTitle(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String title,
			String subtitle) {
		CraftPlayer craftPlayer = (CraftPlayer) player;
		if (craftPlayer.getHandle().playerConnection.networkManager.getVersion() != 47) {
			return;
		}
		if (title == null) {
			title = "";
		}
		title = ChatColor.translateAlternateColorCodes('&', title);
		if (subtitle == null) {
			subtitle = "";
		}
		subtitle = ChatColor.translateAlternateColorCodes('&', subtitle);

		title = title.replaceAll("%player%", player.getDisplayName());
		subtitle = subtitle.replaceAll("%player%", player.getDisplayName());

		IChatBaseComponent serializedTitle = ChatSerializer.a(TextConverter.convert(title));
		IChatBaseComponent serializedSubTitle = ChatSerializer.a(TextConverter.convert(subtitle));
		IChatBaseComponent title2 = serializedTitle;
		IChatBaseComponent subtitle2 = serializedSubTitle;

		craftPlayer.getHandle().playerConnection.sendPacket(new ProtocolInjector.PacketTitle(
				ProtocolInjector.PacketTitle.Action.TIMES, fadeIn.intValue(), stay.intValue(), fadeOut.intValue()));
		if (title != null) {
			craftPlayer.getHandle().playerConnection
					.sendPacket(new ProtocolInjector.PacketTitle(ProtocolInjector.PacketTitle.Action.TITLE, title2));
		}
		if (subtitle != null) {
			craftPlayer.getHandle().playerConnection.sendPacket(
					new ProtocolInjector.PacketTitle(ProtocolInjector.PacketTitle.Action.SUBTITLE, subtitle2));
		}
	}

	public static void sendTabTitle(Player player, String header, String footer) {
		CraftPlayer craftPlayer = (CraftPlayer) player;
		if (craftPlayer.getHandle().playerConnection.networkManager.getVersion() != 47) {
			return;
		}
		PlayerConnection connection = craftPlayer.getHandle().playerConnection;
		if (header == null) {
			header = "";
		}
		header = ChatColor.translateAlternateColorCodes('&', header);
		if (footer == null) {
			footer = "";
		}
		footer = ChatColor.translateAlternateColorCodes('&', footer);

		header = header.replaceAll("%player%", player.getDisplayName());
		footer = footer.replaceAll("%player%", player.getDisplayName());

		IChatBaseComponent header2 = ChatSerializer.a("{'color': 'white', 'text': '" + header + "'}");
		IChatBaseComponent footer2 = ChatSerializer.a("{'color': 'white', 'text': '" + footer + "'}");
		connection.sendPacket(new ProtocolInjector.PacketTabHeader(header2, footer2));
	}

	public boolean onCommand(CommandSender sender, Command command, String string, String[] args) {
		if (args.length < 6) {
			sender.sendMessage(
					ChatColor.RED + "Usage: /title <player> title|subtitle <fadeIn> <stay> <fadeOut> <text>");
			sender.sendMessage(ChatColor.RED
					+ "Note: fadeIn, stay &, fadeOut require to be a number, works in ticks; 20 = 1 second");
			return false;
		}
		if (Bukkit.getPlayer(args[0]) == null) {
			sender.sendMessage(ChatColor.RED + "Player not found");
			return false;
		}
		Player player = Bukkit.getPlayer(args[0]);
		if ((!args[1].equalsIgnoreCase("title")) && (!args[1].equalsIgnoreCase("subtitle"))) {
			sender.sendMessage(ChatColor.RED + "Invalid argument: " + args[1]);
			return false;
		}
		if (!isInteger(args[2])) {
			sender.sendMessage(ChatColor.RED + "Not a number: " + args[2]);
			return false;
		}
		if (!isInteger(args[3])) {
			sender.sendMessage(ChatColor.RED + "Not a number: " + args[3]);
			return false;
		}
		if (!isInteger(args[4])) {
			sender.sendMessage(ChatColor.RED + "Not a number: " + args[4]);
			return false;
		}
		StringBuilder builder = new StringBuilder();
		for (String value : args) {
			builder.append(value).append(" ");
		}
		String message = builder.toString();
		message = message.replace(args[0] + " ", "");
		message = message.replace(args[1] + " ", "");
		message = message.replace(args[2] + " ", "");
		message = message.replace(args[3] + " ", "");
		message = message.replace(args[4] + " ", "");
		if (args[1].equalsIgnoreCase("title")) {
			sendTitle(player, Integer.valueOf(Integer.parseInt(args[2])), Integer.valueOf(Integer.parseInt(args[3])),
					Integer.valueOf(Integer.parseInt(args[4])), message, null);
			sender.sendMessage(ChatColor.GREEN + "Title sent");
			return true;
		}
		if (args[1].equalsIgnoreCase("subtitle")) {
			sendTitle(player, Integer.valueOf(Integer.parseInt(args[2])), Integer.valueOf(Integer.parseInt(args[3])),
					Integer.valueOf(Integer.parseInt(args[4])), null, message);
			sender.sendMessage(ChatColor.GREEN + "Title sent");
			return true;
		}
		return false;
	}

	boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
}
