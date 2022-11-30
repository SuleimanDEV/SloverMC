package br.com.slovermc.login.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.com.slovermc.login.api.API;
import br.com.slovermc.login.api.APILogin;
import br.com.slovermc.login.api.Menu;

public final class cLogin extends Command {

	public cLogin(final String name) {
		super(name);
	}

	@Override
	public boolean execute(final CommandSender sender, final String label, final String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("§4§lERRO §cComando apenas in-game.");
			return true;
		}
		final Player p = (Player) sender;
		if (APILogin.isLogged(p)) {
			API.sendPlayerMessage(p, "§4§lLOGIN§f Você já está logado, conecte-se ao Lobby!");
			try {
				Menu.Inventory(p);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
		}
		if (!APILogin.isRegistered(p)) {
			API.sendPlayerMessage(p, "§4§lLOGIN§f Você não está registrado!");
			p.chat("/register");
			return true;
		}
		if (args.length == 0) {
			API.sendPlayerMessage(p, "§4§lLOGIN§f Utilize /login <senha> para se logar.");
			return true;
		}
		if (args.length == 1) {
			APILogin.setPlayerLogin(p, args[0]);
			return true;
		}
		if (args.length > 1) {
			API.sendPlayerMessage(p, "§4§lLOGIN§f Utilize /login <senha> para se logar.");
			return true;
		}
		return false;
	}
}
