package br.com.slovermc.gladiator.schematics;

public final class StringTag extends Tag {

	private final String value;

	public StringTag(String name, String value) {
		super(name);
		this.value = value;
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		String name = getName();
		String append = "";
		if (name != null && !name.equals("")) {
			append = "(\"" + this.getName() + "\")";
		}
		return "TAG_String" + append + ": " + value;
	}
}
