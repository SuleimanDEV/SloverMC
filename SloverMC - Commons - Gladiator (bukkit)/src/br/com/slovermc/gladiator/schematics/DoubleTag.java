package br.com.slovermc.gladiator.schematics;

public final class DoubleTag extends Tag {

	private final double value;

	public DoubleTag(String name, double value) {
		super(name);
		this.value = value;
	}

	@Override
	public Double getValue() {
		return value;
	}

	@Override
	public String toString() {
		String name = getName();
		String append = "";
		if (name != null && !name.equals("")) {
			append = "(\"" + this.getName() + "\")";
		}
		return "TAG_Double" + append + ": " + value;
	}
}
