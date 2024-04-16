package io.github.cuisse.nbt.io;

import io.github.cuisse.nbt.NamedTag;
import io.github.cuisse.nbt.Tag;
import io.github.cuisse.nbt.TagType;
import io.github.cuisse.nbt.tags.*;

/**
 * A class for writing NBT data to an output stream in simple format.
 * 
 * @author Brayan Roman
 * @since  1.0.0
 */
public class SimpleNBTWriter implements NBTWriter {

    @Override
    public void write(NBTOutput output, NamedTag tag) {
        output.writeByte(getTagType(tag.tag()));
        output.writeString(tag.name());
        write(output, tag.tag(), 512);
    }

    @Override
    public void write(NBTOutput output, Tag tag) {
        output.writeByte(getTagType(tag));
        write(output, tag, 512);
    }

    @Override
    public void write(NBTOutput output, Tag tag, int depth) {
        if (depth <= 0) {
            throw new IllegalArgumentException("Depth limit exceeded");
        }
        switch (tag.type()) {
            case TAG_End        -> output.writeByte((byte) TagType.TAG_End.ordinal());
            case TAG_Byte       -> output.writeByte(((ByteTag) tag).value());
            case TAG_Short      -> output.writeShort(((ShortTag) tag).value());
            case TAG_Int        -> output.writeInt(((IntTag) tag).value());
            case TAG_Long       -> output.writeLong(((LongTag) tag).value());
            case TAG_Float      -> output.writeFloat(((FloatTag) tag).value());
            case TAG_Double     -> output.writeDouble(((DoubleTag) tag).value());
            case TAG_Byte_Array -> output.writeByteArray(((ByteArrayTag) tag).value());
            case TAG_String     -> output.writeString(((StringTag) tag).value());
            case TAG_List       -> writeListTag(output, (ListTag) tag, depth);
            case TAG_Compound   -> writeCompoundTag(output, (CompoundTag) tag, depth);
            case TAG_Int_Array  -> output.writeIntArray(((IntArrayTag) tag).value());
            case TAG_Long_Array -> output.writeLongArray(((LongArrayTag) tag).value());
            default             -> throw new IllegalArgumentException("Unknown tag type");
        }
    }

    private byte getTagType(Tag tag) {
        return (byte) tag.type().ordinal();
    }

    private void writeListTag(NBTOutput output, ListTag list, int depth) {
        output.writeByte((byte) list.getType().ordinal());
        output.writeInt(list.value().size());
        for (Tag tag : list.value()) {
            write(output, tag, depth - 1);
        }
    }

    private void writeCompoundTag(NBTOutput output, CompoundTag compound, int depth) {
        compound.value().forEach((name, tag) -> {
            output.writeByte(getTagType(tag));
            output.writeString(name);
            write(output, tag, depth - 1); // write named tag
        });
        write(output, EndTag.DEFAULT, depth - 1);
    }

}
