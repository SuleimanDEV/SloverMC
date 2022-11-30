package br.com.slovermc.login.api;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.entity.Player;

public final class APILogin {

	public static final HashMap<Player, InetAddress> playerAddress = new HashMap<>();

	public static final HashMap<Player, Logins> logMode = new HashMap<>();

	public static final HashMap<Player, Integer> count1 = new HashMap<>();
	public static final HashMap<Player, Integer> count2 = new HashMap<>();

	public static final ArrayList<Player> inQueeu = new ArrayList<>();

	public static enum Logins {
		Logged, Logging
	}

	public static final boolean isInQueeu(final Player p) {
		return inQueeu.contains(p);
	}

	public static final void setPlayerLogMode(final Player p, final Logins mode) {
		logMode.put(p, mode);
	}

	public static final void addPlayerToQueeu(final Player p) {
		if (isInQueeu(p)) {
			return;
		}
		inQueeu.add(p);
	}

	public static final void removePlayerOfQueeu(final Player p) {
		if (!isInQueeu(p)) {
			return;
		}
		inQueeu.remove(p);
	}

	public static final String getPlayerPass(final Player p) {
		return Account.getAccounts().getString("Users." + p.getName().toLowerCase() + ".MyPass");
	}

	public static final boolean truePass(final Player p, final String pass) {
		return pass.equals(getPlayerPass(p));
	}

	public static final void setPlayerRegistry(final Player p, final String pass, final String confirm)
			throws IOException {
		if (confirm.length() < 8) {
			API.sendPlayerMessage(p, "§4§lREGISTER§f Utilize uma senha com mais de 8 carateres!");
			return;
		}
		if (confirm.length() > 16) {
			API.sendPlayerMessage(p, "§4§lREGISTER§f Utilize uma senha com menos de 16 carateres!");
			return;
		}
		if (confirm.equalsIgnoreCase("12345678") && pass.equalsIgnoreCase("12345678")) {
			API.sendPlayerMessage(p, "§4§lREGISTER§f Utilize uma senha mais forte!");
			return;
		}
		if (!confirm.equals(pass)) {
			API.sendPlayerMessage(p, "§4§lREGISTRO§f As senhas não se conhecidem.");
			return;
		}
		setPlayerLogMode(p, Logins.Logged);
		removePlayerOfQueeu(p);
		Account.getAccounts().set("Users." + p.getName().toLowerCase() + ".MyPass", confirm);
		Account.getAccounts().set("Users." + p.getName().toLowerCase() + ".UUID", p.getUniqueId().toString());
		Account.saveAccounts();

		API.sendPlayerMessage(p, "");
		API.sendPlayerMessage(p, "§4§lREGISTRO§f Você foi registrado com sucesso! Para trocar sua senha, utilize: §e/changepass§f.");
		API.sendPlayerMessage(p, "§6§lNOVIDADES§f Teremos futuramente, um sistema de recuperação por email! Saiba mais em §ehttps://twitter.com/slovermc_");
		API.sendPlayerMessage(p, "");
	}

	public static final void setPlayerNewPass(final Player p, final String oldPass, final String newPass)
			throws IOException {
		if (!oldPass.equals(getPlayerPass(p))) {
			API.sendPlayerMessage(p, "§e§lCHANGEPASS§f Esta senha não é a original!");
			return;
		}
		if (oldPass.length() < 8) {
			API.sendPlayerMessage(p, "§e§lCHANGEPASS§f Use uma nova senha com mais de 8 carateres!");
			return;
		}
		if (oldPass.length() > 16) {
			API.sendPlayerMessage(p, "§e§lCHANGEPASS§f Use uma senha com menos de 16 carateres!");
			return;
		}
		if (oldPass.equalsIgnoreCase("12345678")) {
			API.sendPlayerMessage(p, "§e§lCHANGEPASS§f Utilize uma senha mais forte!");
			return;
		}
		Account.getAccounts().set("Users." + p.getName().toLowerCase() + ".MyPass", newPass);
		Account.saveAccounts();

		API.sendPlayerMessage(p, "");
		API.sendPlayerMessage(p, "§e§lCHANGEPASS§f Sua senha foi alterada com sucesso!");
		API.sendPlayerMessage(p,
				"§6§lNOVIDADES§f Teremos futuramente, um sistema de recuperação por email! Saiba mais em §ehttps://twitter.com/slovermc_");
		API.sendPlayerMessage(p, "");
	}

	public static final void setPlayerLogin(final Player p, final String pass) {
		if (!truePass(p, pass)) {
			API.sendPlayerMessage(p, "§4§lLOGIN§f Esta senha está incorreta.");
			return;
		}
		removePlayerOfQueeu(p);
		setPlayerLogMode(p, Logins.Logged);
		API.sendPlayerMessage(p, "");
		API.sendPlayerMessage(p,
				"§a§lLOGIN§f Você foi autenticado com sucesso! Para alterar sua senha utilize: §e/changepass§f.");
		API.sendPlayerMessage(p,
				"§6§lNOVIDADES§f Teremos futuramente, um sistema de recuperação por email! Saiba mais em §ehttps://twitter.com/slovermc_");
		API.sendPlayerMessage(p, "");
	}

	public static final boolean isRegistered(final Player p) {
		return Account.getAccounts().contains("Users." + p.getName().toLowerCase());
	}

	public static final boolean isLogging(final Player p) {
		return logMode.get(p) == Logins.Logging && isInQueeu(p);
	}

	public static final boolean isLogged(final Player p) {
		return logMode.get(p) == Logins.Logged && !isInQueeu(p);
	}
}
