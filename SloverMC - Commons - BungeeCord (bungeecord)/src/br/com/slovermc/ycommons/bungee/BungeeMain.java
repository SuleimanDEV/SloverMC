package br.com.slovermc.ycommons.bungee;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import br.com.slovermc.ycommons.api.mysql.MySQLAPI;
import br.com.slovermc.ycommons.api.mysql.functions.MySQLFunctions;
import br.com.slovermc.ycommons.bungee.checkproxy.ProxyRunnable;
import br.com.slovermc.ycommons.bungee.command.register.BanCommand;
import br.com.slovermc.ycommons.bungee.command.register.BroadcastCommand;
import br.com.slovermc.ycommons.bungee.command.register.KickCommand;
import br.com.slovermc.ycommons.bungee.command.register.MuteCommand;
import br.com.slovermc.ycommons.bungee.command.register.ServerCommand;
import br.com.slovermc.ycommons.bungee.command.register.StaffChatCommand;
import br.com.slovermc.ycommons.bungee.command.register.StaffCommand;
import br.com.slovermc.ycommons.bungee.command.register.UnbanCommand;
import br.com.slovermc.ycommons.bungee.command.register.UnmuteCommand;
import br.com.slovermc.ycommons.bungee.command.register.YoutuberCommand;
import br.com.slovermc.ycommons.bungee.event.BungeeEvent;
import br.com.slovermc.ycommons.bungee.sheduler.Sheduler;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.event.PreLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import net.md_5.bungee.event.EventHandler;

public class BungeeMain extends Plugin implements Listener {

	public static boolean BungeeCordPlugin = false;

	public ArrayList<String> messages;
	public String px;
	public int sec;
	public boolean random;

	private static Plugin bungee_plugin;
	public static BungeeMain instances;

	public static Plugin getBungeePlugin() {
		return bungee_plugin;
	}

	public static List<String> entrou = new ArrayList<String>();
	public ArrayList<String> lista1 = new ArrayList<String>();

	public static HashMap<String, Integer> ipAttemp = new HashMap<String, Integer>();
	public static HashMap<String, Integer> loginAttemp = new HashMap<String, Integer>();

	public static int loginPerSecond = 0;
	public static boolean validade = false;

	public void onEnable() {

		instances = this;
		BungeeCordPlugin = true;
		bungee_plugin = this;

		MySQLAPI.connect();
		MySQLFunctions.createTables();

		BungeeCord.getInstance().getPluginManager().registerListener(this, new BungeeEvent());
		MySQLFunctions.getAccounts().setGroup("CONSOLE", "DONO", -1);
		registerCommands();
		
		this.messages = new ArrayList<String>();
		try {
			if (!getDataFolder().exists()) {
				getDataFolder().mkdir();
			}
			File file = new File(getDataFolder().getPath(), "config.yml");
			String s;
			if (!file.exists()) {
				file.createNewFile();
				Configuration config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);

				config.set("settings.seconds", Integer.valueOf(60));
				//config.set("settings.randommode", Boolean.valueOf(false));
				ArrayList<String> mensagens = new ArrayList<String>();

				mensagens.add("&fAchou alguem fazendo coisa &4&lERRADA? &fdenuncie utilizando &c/report (nick) (motivo)");
				mensagens.add("&fNos siga no twitter &3&l@RedeMatchMC_ &fpara ficar sabendo de tudo da rede!");
				mensagens.add("&fPara se aplicar a nossa staff utilize o comando &e/aplicar&f!");
				mensagens.add("&fQuer se aplicar para &bYoutuber&f veja os requisitos do servidor, utilize &b/youtuber&f.");
				mensagens.add("&fAcesse logo nosso &2&lSITE&f para saber de novidades! &ehttps://matchmc.com.br");
				mensagens.add("&fAdquira &a&lVIPS &fem nossa loja acesse: &6&lwww.loja.matchmc.com.br");

				config.set("messages", mensagens);
				ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, file);
				this.px = config.getString("settings.prefix").replace("&", "§");
			//	this.sec = config.getInt("settings.seconds");
				this.random = config.getBoolean("settings.randommode");
				for (Iterator<?> localIterator = config.getStringList("messages").iterator(); localIterator.hasNext();) {
					
					s = (String)localIterator.next();
					this.messages.add(ChatColor.translateAlternateColorCodes('&', s));
				}
			} else {
				Configuration config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
				this.px = "§6§lMatch§f§lMC §7» ";
				this.sec = 60;
		//		this.sec = config.getInt("settings.seconds");
				this.random = config.getBoolean("settings.randommode");
				for (String s2 : config.getStringList("messages")) {
					this.messages.add(ChatColor.translateAlternateColorCodes('&', s2));
				}
				System.out.println("AVISO As mensagens automaticas foram iniciadas.");
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		Sheduler s3 = new Sheduler(this);
		s3.msg();

		getProxy().getPluginManager().registerListener(this, this);
		//try {
		//	BufferedReader in = new BufferedReader(new FileReader(getDataFolder() + File.separator + "lista.txt"));
		//	String l;
		//	while ((l = in.readLine()) != null) {
		//		this.lista1.add(l);
		//	}
		//	in.close();
		//}
		//catch (FileNotFoundException e) {
		//	e.printStackTrace();
		//}
		
		//catch (IOException e) {
		//	e.printStackTrace();
		//}
		new ProxyRunnable() {
			public void run() {
				if (BungeeMain.loginPerSecond > 0) {
					BungeeMain.loginPerSecond = BungeeMain.loginPerSecond--;
				}
				if (BungeeMain.validade) {
					if (BungeeMain.loginPerSecond <= 1) {
						BungeeMain.validade = false;
					}
				}
				else if (BungeeMain.loginPerSecond > 2) {
					BungeeMain.validade = true;
				}
			}
		}.runTaskTimer(1L, 1L, TimeUnit.SECONDS);

		new ProxyRunnable() {
			public void run() {
				for (String key : BungeeMain.ipAttemp.keySet()) {
					if (((Integer)BungeeMain.ipAttemp.getOrDefault(key, Integer.valueOf(0))).intValue() > 0) {
						BungeeMain.ipAttemp.put(key, Integer.valueOf(((Integer)BungeeMain.ipAttemp.getOrDefault(key, Integer.valueOf(1))).intValue() - 1));
					}
				}
				for (String key : BungeeMain.loginAttemp.keySet()) {
					if (((Integer)BungeeMain.loginAttemp.getOrDefault(key, Integer.valueOf(0))).intValue() > 0) {
						BungeeMain.loginAttemp.put(key, Integer.valueOf(((Integer)BungeeMain.loginAttemp.getOrDefault(key, Integer.valueOf(1))).intValue() - 1));
					}
				}
			}
		}.runTaskTimer(1L, 1L, TimeUnit.MINUTES);

		new ProxyRunnable() {
			public void run()
			{
				BungeeMain.entrou.clear();
			}
		}.runTaskLater(10L, TimeUnit.MINUTES);
	}

	@EventHandler
	public void onPreJoin(PreLoginEvent e)
			throws Exception {
		loginPerSecond += 1;
		if (validade) {
			String name = e.getConnection().getName();

			String ip = e.getConnection().getAddress().getAddress().getHostAddress().toString();
			if (this.lista1.contains(name)) {
				e.setCancelled(true);
				e.setCancelReason("§4§lBLACKLIST §fVocê está na Blacklist do servidor.");
			}
			if (ip.contains("177.47.27")) {
				e.setCancelled(true);
				e.setCancelReason(
						"§c§lVPN §fSua VPN foi identificada §bAvast SecureLine§f.");
				return;
			}
			if (ip.contains("169.57.142")) {
				e.setCancelled(true);
				e.setCancelReason(
						"§c§lVPN§f Sua VPN foi identificada §bAvira PhantomVPN§f.");
				return;
			}
			if (name.contains("ROD23S")) {
				e.setCancelled(true);
				return;
			}
			if (!entrou.contains(name)) {
				e.setCancelled(true);
				e.setCancelReason("§6§lCHEKING\n\n§f Você está sendo checado, relogue para validar o seu registro!");
				entrou.add(name);
				return;
			}
			if (((Integer)ipAttemp.getOrDefault(ip, Integer.valueOf(0))).intValue() > 3) {
				e.setCancelled(true);
				e.setCancelReason(
						"§cVocê está tentando logar várias vezes, espere que você foi bloqueado de entrar em nossa rede temporiariamente.");
				return;
			}
			ipAttemp.put(ip, Integer.valueOf(((Integer)ipAttemp.getOrDefault(ip, Integer.valueOf(0))).intValue() + 1));
			if (((Integer)loginAttemp.getOrDefault(ip, Integer.valueOf(0))).intValue() > 3) {
				e.setCancelled(true);
				e.setCancelReason(
						"§cVocê está tentando logar várias vezes, espere que você foi bloqueado de entrar em nossa rede temporiariamente");
				return;
			}
			loginAttemp.put(ip, Integer.valueOf(((Integer)loginAttemp.getOrDefault(ip, Integer.valueOf(0))).intValue() + 1));
		}
	}

	public void registerCommands() {
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new BroadcastCommand("bc"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new BroadcastCommand("broadcast"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new BroadcastCommand("aviso"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new BroadcastCommand("alert"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new BanCommand("ban"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new BanCommand("punir"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new BanCommand("banir"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new KickCommand("kick"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new KickCommand("kickar"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new KickCommand("expulsar"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new MuteCommand("mute"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new MuteCommand("mutar"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new ServerCommand("server"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new ServerCommand("send"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new ServerCommand("conectar"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new ServerCommand("connect"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new StaffCommand("staff"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new StaffCommand("aplicar"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new StaffCommand("formulario"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new StaffCommand("form"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new StaffChatCommand("sc"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new StaffChatCommand("s"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new StaffChatCommand("staffchat"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new UnbanCommand("unban"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new UnbanCommand("desbanir"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new UnbanCommand("pardon"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new UnmuteCommand("unmute"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new UnmuteCommand("desmutar"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new YoutuberCommand("youtuber"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new YoutuberCommand("youtube"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new YoutuberCommand("yt"));
	}

	public void onDisable() {
		MySQLAPI.disconnect();
	}
}
