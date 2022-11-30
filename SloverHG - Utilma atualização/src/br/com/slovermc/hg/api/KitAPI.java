package br.com.slovermc.hg.api;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.bukkit.entity.Player;

import net.minecraft.util.com.google.common.collect.Maps;

public class KitAPI {

	private static KitAPI instance = new KitAPI();

	private Map<String, String> kit = Maps.newHashMap();
	private Map<String, String> kit2 = Maps.newHashMap();
	private Map<Player, Long> cooldown = Maps.newHashMap();
	private Map<Player, Long> cooldown2 = Maps.newHashMap();

	public static KitAPI getInstance() {
		return instance;
	}

	public void setKit(Player p, String nome) {
		if (kit.containsKey(p.getName()))
			kit.remove(p.getName());
		kit.put(p.getName(), nome);
	}

	public void setKit2(Player p, String name) {
		if (kit2.containsKey(p.getName()))
			kit2.remove(p.getName());
		kit2.put(p.getName(), name);
	}

	public void removeKit(Player p) {
		if (kit.containsKey(p.getName()))
			kit.remove(p.getName());
	}

	public void removeKit2(Player p) {
		if (kit2.containsKey(p.getName()))
			kit2.remove(p.getName());
	}

	public boolean hasKit(Player p, String nome) {
		if (!kit.containsKey(p.getName()))
			return false;
		if (getKit(p).equalsIgnoreCase(nome))
			return true;
		return false;
	}

	public boolean hasKit2(Player p, String name) {
		if (!kit2.containsKey(p.getName()))
			return false;
		if (getKit2(p).equalsIgnoreCase(name))
			return true;
		return false;
	}

	public String getKit(Player p) {
		String kite;
		if (!kit.containsKey(p.getName())) {
			kite = "Nenhum";
		} else {
			kite = kit.get(p.getName());
		}
		return kite;
	}

	public String getKit2(Player p) {
		String kite;
		if (!kit2.containsKey(p.getName())) {
			kite = "Nenhum";
		} else {
			kite = kit2.get(p.getName());
		}
		return kite;
	}

	public void addCooldown(Player p, int tempo) {
		cooldown.put(p, Long.valueOf(System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(tempo)));
	}

	public void addCooldown2(Player p, int tempo) {
		cooldown2.put(p, Long.valueOf(System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(tempo)));
	}

	public void removeCooldown(Player p) {
		if (cooldown.containsKey(p))
			cooldown.remove(p);
	}

	public void removeCooldown2(Player p) {
		if (cooldown2.containsKey(p))
			cooldown2.remove(p);
	}

	public boolean hasCooldown(Player p) {
		if ((!cooldown.containsKey(p))
				|| (Long.valueOf(((Long) cooldown.get(p)).longValue()).longValue() <= System.currentTimeMillis())) {
			return false;
		} else {
			return true;
		}
	}

	public boolean hasCooldown2(Player p) {
		if ((!cooldown2.containsKey(p))
				|| (Long.valueOf(((Long) cooldown2.get(p)).longValue()).longValue() <= System.currentTimeMillis())) {
			return false;
		} else {
			return true;
		}
	}

	public void messageCooldown(Player p) {
		p.sendMessage("§3§lKIT §fVocê está em cooldown, aguarde " + TimeUnit.MILLISECONDS
				.toSeconds(Long.valueOf(cooldown.get(p)).longValue() - System.currentTimeMillis()) + " segundos para usar!");
	}

	public void messageCooldown2(Player p) {
		p.sendMessage("§3§lKIT §fVocê está em cooldown, aguarde " + TimeUnit.MILLISECONDS
				.toSeconds(Long.valueOf(cooldown2.get(p)).longValue() - System.currentTimeMillis()) + " segundos para usar!");
	}
}
