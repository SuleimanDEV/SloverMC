package br.com.slovermc.gladiator.mysql;

public interface Callback<T> {

	/**
	 * Callback
	 */

	public void finish(T t);

}