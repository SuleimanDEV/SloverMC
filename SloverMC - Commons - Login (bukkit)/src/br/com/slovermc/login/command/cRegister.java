package br.com.slovermc.login.command;

import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.com.slovermc.login.api.API;
import br.com.slovermc.login.api.APILogin;
import br.com.slovermc.login.api.Menu;

public final class cRegister extends Command {

	public cRegister(final String name) {
		super(name);
	}

	@Override
	public boolean execute(final CommandSender sender, final String label, final String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("§c§lERRO§f Comando apenas in-game!");
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
		if (APILogin.isRegistered(p)) {
			API.sendPlayerMessage(p, "§4§lREGISTER§f Você já está registrado!");
			p.chat("/login");
			return true;
		}
		if (args.length >= 0 && args.length < 2) {
			API.sendPlayerMessage(p, "§4§lREGISTER§f Utilize /register <senha> <confirmarsenha> para se registrar.");
			return true;
		}
		if (args.length == 2) {
			try {
				APILogin.setPlayerRegistry(p, args[0], args[1]);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return true;
		}
		if (args.length > 2) {
			API.sendPlayerMessage(p, "§4§lREGISTER§f Utilize /register <senha> <confirmarsenha> para se registrar.");
			return true;
		}
		return false;
	}
}
