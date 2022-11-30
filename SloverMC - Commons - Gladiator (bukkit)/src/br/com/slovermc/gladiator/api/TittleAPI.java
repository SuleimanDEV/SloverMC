package br.com.slovermc.gladiator.api;

import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.spigotmc.ProtocolInjector;

import net.minecraft.server.v1_7_R4.ChatSerializer;
import net.minecraft.server.v1_7_R4.IChatBaseComponent;
import net.minecraft.server.v1_7_R4.PacketPlayOutChat;

public class TittleAPI {

	public static void sendTitle(Player p, String title) {
	    if (((CraftPlayer)p).getHandle().playerConnection.networkManager.getVersion() < 45) {
	    	return;
	    }
	    ((CraftPlayer)p).getHandle().playerConnection.sendPacket(new ProtocolInjector.PacketTitle(ProtocolInjector.PacketTitle.Action.TITLE, ChatSerializer.a("{\"text\": \"\"}").a(title)));
	}
	
	public static void sendSubTitle(Player p, String title) {
	    if (((CraftPlayer)p).getHandle().playerConnection.networkManager.getVersion() < 45) {
	    	return;
	    }
	    ((CraftPlayer)p).getHandle().playerConnection.sendPacket(new ProtocolInjector.PacketTitle(ProtocolInjector.PacketTitle.Action.SUBTITLE, ChatSerializer.a("{\"text\": \"\"}").a(title)));
	}
	
	public static void sendActionbarTitle(Player p, String title) {
		  CraftPlayer CraftPlayer = (CraftPlayer) p;
		  if (CraftPlayer.getHandle().playerConnection.networkManager.getVersion() != 47) {
			  return;
		  }
		  IChatBaseComponent IChatBaseComponent = ChatSerializer.a("{\"text\": \"" + title + "\"}");
		  PacketPlayOutChat PacketPlayOutChat = new PacketPlayOutChat(IChatBaseComponent, 2);
		  ((CraftPlayer) CraftPlayer).getHandle().playerConnection.sendPacket(PacketPlayOutChat);
		  ((CraftPlayer) CraftPlayer).getHandle().playerConnection.sendPacket(PacketPlayOutChat);
	} 
	
	public static void clearTitle(Player p) {
	    if (((CraftPlayer)p).getHandle().playerConnection.networkManager.getVersion() < 45) {
	    	return;
	    }
	    ((CraftPlayer)p).getHandle().playerConnection.sendPacket(new ProtocolInjector.PacketTitle(ProtocolInjector.PacketTitle.Action.CLEAR));
	}
}