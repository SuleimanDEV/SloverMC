package br.com.slovermc.gladiator.schematics;

public final class LongTag extends Tag {

	private final long value;

	public LongTag(String name, long value) {
		super(name);
		this.value = value;
	}

	@Override
	public Long getValue() {
		return value;
	}

	@Override
	public String toString() {
		String name = getName();
		String append = "";
		if (name != null && !name.equals("")) {
			append = "(\"" + this.getName() + "\")";
		}
		return "TAG_Long" + append + ": " + value;
	}
}
