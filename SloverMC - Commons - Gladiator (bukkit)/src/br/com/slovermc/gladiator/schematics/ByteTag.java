package br.com.slovermc.gladiator.schematics;

public final class ByteTag extends Tag {

	private final byte value;

	public ByteTag(String name, byte value) {
		super(name);
		this.value = value;
	}

	@Override
	public Byte getValue() {
		return value;
	}

	@Override
	public String toString() {
		String name = getName();
		String append = "";
		if (name != null && !name.equals("")) {
			append = "(\"" + this.getName() + "\")";
		}
		return "TAG_Byte" + append + ": " + value;
	}
}
