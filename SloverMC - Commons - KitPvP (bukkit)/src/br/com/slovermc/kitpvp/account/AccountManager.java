package br.com.slovermc.kitpvp.account;

public final class AccountManager {

	public static boolean hasAccount(final String bpName) {
		if (AccountFile.getAccountsFile().contains("SloverAccounts." + bpName.toLowerCase())) {
			return true;
		} else {
			return false;
		}
	}
}