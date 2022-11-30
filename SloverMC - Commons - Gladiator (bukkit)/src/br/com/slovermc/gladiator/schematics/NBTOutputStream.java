package br.com.slovermc.gladiator.schematics;

import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.zip.GZIPOutputStream;

public final class NBTOutputStream implements Closeable {

	private final DataOutputStream os;

	public NBTOutputStream(OutputStream os) throws IOException {
		this.os = new DataOutputStream(new GZIPOutputStream(os));
	}

	public void writeTag(Tag tag) throws IOException {
		int type = NBTUtils.getTypeCode(tag.getClass());
		String name = tag.getName();
		byte[] nameBytes = name.getBytes(NBTConstants.CHARSET);

		os.writeByte(type);
		os.writeShort(nameBytes.length);
		os.write(nameBytes);

		if (type == NBTConstants.TYPE_END) {
			throw new IOException("Named TAG_End not permitted.");
		}

		writeTagPayload(tag);
	}

	private void writeTagPayload(Tag tag) throws IOException {
		int type = NBTUtils.getTypeCode(tag.getClass());
		switch (type) {
		case NBTConstants.TYPE_END:
			writeEndTagPayload((EndTag) tag);
			break;
		case NBTConstants.TYPE_BYTE:
			writeByteTagPayload((ByteTag) tag);
			break;
		case NBTConstants.TYPE_SHORT:
			writeShortTagPayload((ShortTag) tag);
			break;
		case NBTConstants.TYPE_INT:
			writeIntTagPayload((IntTag) tag);
			break;
		case NBTConstants.TYPE_LONG:
			writeLongTagPayload((LongTag) tag);
			break;
		case NBTConstants.TYPE_FLOAT:
			writeFloatTagPayload((FloatTag) tag);
			break;
		case NBTConstants.TYPE_DOUBLE:
			writeDoubleTagPayload((DoubleTag) tag);
			break;
		case NBTConstants.TYPE_BYTE_ARRAY:
			writeByteArrayTagPayload((ByteArrayTag) tag);
			break;
		case NBTConstants.TYPE_STRING:
			writeStringTagPayload((StringTag) tag);
			break;
		case NBTConstants.TYPE_LIST:
			writeListTagPayload((ListTag) tag);
			break;
		case NBTConstants.TYPE_COMPOUND:
			writeCompoundTagPayload((CompoundTag) tag);
			break;
		default:
			throw new IOException("Invalid tag type: " + type + ".");
		}
	}

	private void writeByteTagPayload(ByteTag tag) throws IOException {
		os.writeByte(tag.getValue());
	}

	private void writeByteArrayTagPayload(ByteArrayTag tag) throws IOException {
		byte[] bytes = tag.getValue();
		os.writeInt(bytes.length);
		os.write(bytes);
	}

	private void writeCompoundTagPayload(CompoundTag tag) throws IOException {
		for (Tag childTag : tag.getValue().values()) {
			writeTag(childTag);
		}
		os.writeByte((byte) 0);
	}

	private void writeListTagPayload(ListTag tag) throws IOException {
		Class<? extends Tag> clazz = tag.getType();
		List<Tag> tags = tag.getValue();
		int size = tags.size();

		os.writeByte(NBTUtils.getTypeCode(clazz));
		os.writeInt(size);
		for (int i = 0; i < size; i++) {
			writeTagPayload(tags.get(i));
		}
	}

	private void writeStringTagPayload(StringTag tag) throws IOException {
		byte[] bytes = tag.getValue().getBytes(NBTConstants.CHARSET);
		os.writeShort(bytes.length);
		os.write(bytes);
	}

	private void writeDoubleTagPayload(DoubleTag tag) throws IOException {
		os.writeDouble(tag.getValue());
	}

	private void writeFloatTagPayload(FloatTag tag) throws IOException {
		os.writeFloat(tag.getValue());
	}

	private void writeLongTagPayload(LongTag tag) throws IOException {
		os.writeLong(tag.getValue());
	}

	private void writeIntTagPayload(IntTag tag) throws IOException {
		os.writeInt(tag.getValue());
	}

	private void writeShortTagPayload(ShortTag tag) throws IOException {
		os.writeShort(tag.getValue());
	}

	private void writeEndTagPayload(EndTag tag) {
	}

	@Override
	public void close() throws IOException {
		os.close();
	}
}
