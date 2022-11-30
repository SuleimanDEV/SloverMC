package br.com.slovermc.kitpvp.api;

import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.spigotmc.ProtocolInjector;

import net.minecraft.server.v1_7_R4.ChatSerializer;
import net.minecraft.server.v1_7_R4.IChatBaseComponent;
import net.minecraft.server.v1_7_R4.PacketPlayOutChat;

public final class TittleAPI {

	public static void sendTittle(Player Jogador, String Titulo) {
		if (((CraftPlayer) Jogador).getHandle().playerConnection.networkManager.getVersion() < 45) {
			return;
		}
		((CraftPlayer) Jogador).getHandle().playerConnection.sendPacket(new ProtocolInjector.PacketTitle(
				ProtocolInjector.PacketTitle.Action.TITLE, ChatSerializer.a("{\"text\": \"\"}").a(Titulo)));
	}

	public static void sendSubTittle(Player Jogador, String Titulo) {
		if (((CraftPlayer) Jogador).getHandle().playerConnection.networkManager.getVersion() < 45) {
			return;
		}
		((CraftPlayer) Jogador).getHandle().playerConnection.sendPacket(new ProtocolInjector.PacketTitle(
				ProtocolInjector.PacketTitle.Action.SUBTITLE, ChatSerializer.a("{\"text\": \"\"}").a(Titulo)));
	}

	public static void sendActionBar(Player Jogador, String Titulo) {
		CraftPlayer CraftPlayer = (CraftPlayer) Jogador;
		if (CraftPlayer.getHandle().playerConnection.networkManager.getVersion() != 47) {
			return;
		}
		IChatBaseComponent IChatBaseComponent = ChatSerializer.a("{\"text\": \"" + Titulo + "\"}");
		PacketPlayOutChat PacketPlayOutChat = new PacketPlayOutChat(IChatBaseComponent, 2);
		((CraftPlayer) CraftPlayer).getHandle().playerConnection.sendPacket(PacketPlayOutChat);
		((CraftPlayer) CraftPlayer).getHandle().playerConnection.sendPacket(PacketPlayOutChat);
	}

	public static void clearTittle(Player Jogador) {
		if (((CraftPlayer) Jogador).getHandle().playerConnection.networkManager.getVersion() < 45) {
			return;
		}
		((CraftPlayer) Jogador).getHandle().playerConnection
				.sendPacket(new ProtocolInjector.PacketTitle(ProtocolInjector.PacketTitle.Action.CLEAR));
	}
}
