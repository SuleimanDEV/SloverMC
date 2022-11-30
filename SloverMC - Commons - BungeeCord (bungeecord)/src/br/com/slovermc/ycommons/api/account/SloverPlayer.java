package br.com.slovermc.ycommons.api.account;

import br.com.slovermc.ycommons.api.mysql.functions.MySQLFunctions;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class SloverPlayer {
	private String name;
	private boolean isBanned;
	private boolean isMuted;
	private int xp;
	private int doublexp;
	private int money;
	private int box;

	public SloverPlayer(String name) {
		this.name = name;
		register();

		this.isBanned = MySQLFunctions.getBans().isBanned(name);
		this.isMuted = MySQLFunctions.getBans().isMuted(name);
		this.xp = MySQLFunctions.getAccounts().getXp(name);
		this.doublexp = MySQLFunctions.getAccounts().getDoubleXp(name);
		this.money = MySQLFunctions.getAccounts().getMoney(name);
		this.box = MySQLFunctions.getAccounts().getBox(name);
	}

	public ProxiedPlayer getProxiedPlayer(CommandSender sender) {
		return (ProxiedPlayer) sender;
	}

	public void addMoney(int qnt) {
		MySQLFunctions.getAccounts().setMoney(this.name, qnt);
	}

	public void removeMoney(int qnt) {
		MySQLFunctions.getAccounts().setMoney(this.name, -qnt);
	}

	public void addXp(int qnt) {
		MySQLFunctions.getAccounts().setXp(this.name, qnt);
	}

	public void removeXp(int qnt) {
		MySQLFunctions.getAccounts().setXp(this.name, -qnt);
	}

	public void addDoubleXp(int qnt) {
		MySQLFunctions.getAccounts().setDoubleXp(this.name, qnt);
	}

	public void removeDoubleXp(int qnt) {
		MySQLFunctions.getAccounts().setDoubleXp(this.name, -qnt);
	}

	public ProxiedPlayer getProxiedPlayer() {
		return BungeeCord.getInstance().getPlayer(this.name);
	}

	public void connect(String server) {
		getProxiedPlayer().connect(BungeeCord.getInstance().getServerInfo(server));
	}

	public boolean isBanned() {
		return this.isBanned;
	}

	public void register() {
		if (Slover.isDatabaseConnected()) {
			MySQLFunctions.getAccounts().register(this.name, "Nenhum");
			MySQLFunctions.getBans().register(this.name);
			MySQLFunctions.getIpAddress().register(this.name, "Nenhum");
		}
	}

	@SuppressWarnings("deprecation")
	public void sendMessage(String message) {
		BungeeCord.getInstance().getPlayer(this.name).sendMessage(message);
	}

	public void sendMessage(TextComponent message) {
		BungeeCord.getInstance().getPlayer(this.name).sendMessage(message);
	}

	public League getLeague() {
		return new League(this.xp);
	}

	public boolean isTempGroup() {
		if (!isRegistered()) {
			return false;
		}
		if (!String.valueOf(MySQLFunctions.getAccounts().getLenth(this.name)).equalsIgnoreCase("-1")) {
			return true;
		}
		return false;
	}

	public boolean isGroupExpired() {
		if (Slover.isDatabaseConnected()) {
			if (isTempGroup()) {
				long lenth = MySQLFunctions.getAccounts().getLenth(this.name);
				if (lenth < System.currentTimeMillis()) {
					return true;
				}
				return false;
			}
			return false;
		}
		return false;
	}

	public void removeGroupIfExpires() {
		if ((Slover.isDatabaseConnected()) && (isGroupExpired())) {
			setGroup("MEMBRO", -1L);
		}
	}

	public void setGroup(String group, long lenth) {
		if (Slover.isDatabaseConnected()) {
			MySQLFunctions.getAccounts().setGroup(this.name, group, lenth);
		}
	}

	public long getBanLenth() {
		return MySQLFunctions.getBans().getBanLenth(this.name);
	}

	public long getMuteLenth() {
		return MySQLFunctions.getBans().getMuteLenth(this.name);
	}

	public void register(String address) {
		if (Slover.isDatabaseConnected()) {
			MySQLFunctions.getAccounts().register(this.name, address);
			MySQLFunctions.getBans().register(this.name);
			MySQLFunctions.getIpAddress().register(this.name, address);
		}
	}

	public void unmute() {
		if ((Slover.isDatabaseConnected()) && (isMuted())) {
			MySQLFunctions.getBans().setUnmuted(this.name);
		}
	}

	public void unban() {
		if ((Slover.isDatabaseConnected()) && (isBanned())) {
			MySQLFunctions.getBans().setUnbanned(this.name);
		}
	}

	public void unmuteIfExpires() {
		if ((Slover.isDatabaseConnected()) && (isTempMuted())) {
			long lenth = MySQLFunctions.getBans().getMuteLenth(this.name);
			if (lenth < System.currentTimeMillis()) {
				MySQLFunctions.getBans().setUnmuted(this.name);
			}
		}
	}

	public void unbanIfExpires() {
		if ((Slover.isDatabaseConnected()) && (isTempBanned())) {
			long lenth = MySQLFunctions.getBans().getBanLenth(this.name);
			if (lenth < System.currentTimeMillis()) {
				MySQLFunctions.getBans().setUnbanned(this.name);
			}
		}
	}

	public void setBanned(String author, String motive, String date, long lenth) {
		MySQLFunctions.getBans().setBanned(this.name, motive, author, lenth, false);
	}

	public void setMuted(String author, String motive, String date, long lenth) {
		MySQLFunctions.getBans().setMuted(this.name, motive, author, lenth);
	}

	public void setIpBanned(String author, String motive, String date, long lenth) {
		MySQLFunctions.getBans().setBanned(this.name, motive, author, lenth, true);
	}

	public boolean isMuted() {
		return this.isMuted;
	}

	public String getGroupName() {
		return MySQLFunctions.getAccounts().getGroup(this.name);
	}

	public int getXp() {
		return this.xp;
	}

	public int getDoubleXp() {
		return this.doublexp;
	}

	public int getMoney() {
		return this.money;
	}

	public int getBox() {
		return this.box;
	}

	public String getName() {
		return this.name;
	}

	public boolean isOnline() {
		return BungeeCord.getInstance().getPlayer(this.name) != null;
	}

	public boolean isRegistered() {
		return MySQLFunctions.getAccounts().isRegistered(this.name);
	}

	public boolean isTempBanned() {
		if (!this.isBanned) {
			return false;
		}
		if (!String.valueOf(MySQLFunctions.getBans().getBanLenth(this.name)).equalsIgnoreCase("-1")) {
			return true;
		}
		return false;
	}

	public boolean isTempMuted() {
		if (!this.isMuted) {
			return false;
		}
		if (!String.valueOf(MySQLFunctions.getBans().getMuteLenth(this.name)).equalsIgnoreCase("-1")) {
			return true;
		}
		return false;
	}
}
