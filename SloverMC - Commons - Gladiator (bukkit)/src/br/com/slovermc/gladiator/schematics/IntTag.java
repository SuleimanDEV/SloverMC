package br.com.slovermc.gladiator.schematics;

public final class IntTag extends Tag {

	private final int value;

	public IntTag(String name, int value) {
		super(name);
		this.value = value;
	}

	@Override
	public Integer getValue() {
		return value;
	}

	@Override
	public String toString() {
		String name = getName();
		String append = "";
		if (name != null && !name.equals("")) {
			append = "(\"" + this.getName() + "\")";
		}
		return "TAG_Int" + append + ": " + value;
	}
}
