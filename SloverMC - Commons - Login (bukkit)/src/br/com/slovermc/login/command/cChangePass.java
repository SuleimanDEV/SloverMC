package br.com.slovermc.login.command;

import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.com.slovermc.login.api.API;
import br.com.slovermc.login.api.APILogin;

public final class cChangePass extends Command {

	public cChangePass(final String name) {
		super(name);
	}

	@Override
	public boolean execute(final CommandSender sender, final String label, final String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("§4§lERRO§f Comando apenas in-game.");
			return true;
		}
		final Player p = (Player) sender;
		if (!APILogin.isLogged(p)) {
			API.sendPlayerMessage(p, "§a§lLOGIN§f Você precisa estar logado para alterar uma senha!");
			return true;
		}
		if (args.length >= 0 && args.length < 2) {
			API.sendPlayerMessage(p, "§4§lCHANGEPASS§f Utilize /changepass <senhaatual> <novasenha>.");
			return true;
		}
		if (args.length == 2) {
			try {
				APILogin.setPlayerNewPass(p, args[0], args[1]);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return true;
		}
		if (args.length > 2) {
			API.sendPlayerMessage(p, "§4§lCHANGEPASS§f Utilize /changepass <senhaatual> <novasenha>.");
			return true;
		}
		return false;
	}
}
