package br.com.slovermc.gladiator.schematics;

public final class ShortTag extends Tag {

	private final short value;

	public ShortTag(String name, short value) {
		super(name);
		this.value = value;
	}

	@Override
	public Short getValue() {
		return value;
	}

	@Override
	public String toString() {
		String name = getName();
		String append = "";
		if (name != null && !name.equals("")) {
			append = "(\"" + this.getName() + "\")";
		}
		return "TAG_Short" + append + ": " + value;
	}
}
