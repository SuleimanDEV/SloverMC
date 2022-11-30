package br.com.slovermc.ycommons.bungee.command.register;

import br.com.slovermc.ycommons.api.account.Slover;
import br.com.slovermc.ycommons.api.account.SloverPlayer;
import br.com.slovermc.ycommons.bungee.command.CommandArgs;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class StaffCommand extends CommandArgs {

	public StaffCommand(String name) {
		super(name);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void execute(CommandSender sender, String[] args) {
		if (this.isPlayer(sender)) {
			if (this.isPlayer(sender)) {
				ProxiedPlayer p = this.getPlayerFromSender(sender);
				if (p.getServer().getInfo().getName().equalsIgnoreCase("login")) {
					return;
				}
			}
			SloverPlayer lp = Slover.getSloverPlayer(sender.getName());
			if (args.length >= 0) {
				// TRIAL - MOD
				TextComponent trial = new TextComponent("§fAplicação: §5§lTRIAL-MOD §7(Clique)");
				trial.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL,
						"https://docs.google.com/forms/d/1IgyArYBTWJMcT_Xwfh_IKMoIn-JgHLswSmLgtRt-oNI/edit"));
				trial.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§7(Clique Aqui para copiar o Link").create()));
				// AJUDANTE
				TextComponent ajd = new TextComponent("§fAplicação: §9§lAJUDANTE §7(Clique)");
				ajd.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL,
						"https://docs.google.com/forms/d/e/1FAIpQLSeHd2byOcrvoXUoJmpnkRnnxV3JMY4BdECZa7rnzRJjCYtYGQ/viewform"));
				ajd.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§7(Clique Aqui para copiar o Link").create()));
				
				
				// MODGC
				TextComponent modgc = new TextComponent("§fAplicação: §5§lMODGC §7(Clique)");
				modgc.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL,
						"https://docs.google.com/forms/d/e/1FAIpQLScq2fiboQdBhw56iPcaVGceaGsuyCMjvbQDnJK1fPPGWGhTPw/viewform"));
				modgc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§7(Clique Aqui para copiar o Link").create()));
				
				lp.sendMessage("");
				lp.sendMessage("                 §e§lFORMULARIO");
				lp.sendMessage("");
				lp.sendMessage("§fClique no §4§lCARGO §fno qual deseja §b§lAPLICAR-SE§f:");
				lp.sendMessage("");
				lp.sendMessage(modgc);
				lp.sendMessage(trial);
				lp.sendMessage(ajd);
				lp.sendMessage("");
				lp.sendMessage("§7- §fApós envio do formulário não §b§lPEÇA§f para o mesmo ser §c§lLIDO§f,");
				lp.sendMessage("§fisso reduzirá suas chances de §e§lINGRESSAR§f em nossa §6§lEQUIPE§f.");
				lp.sendMessage("");
				return;
			}
		} else {
			sender.sendMessage("§c§lERRO§f Comando disponível apenas in-game!");
			return;
		}
	}
}
