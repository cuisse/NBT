package io.github.cuisse.nbt.io;

import io.github.cuisse.nbt.NBTException;
import io.github.cuisse.nbt.NamedTag;
import io.github.cuisse.nbt.Tag;
import io.github.cuisse.nbt.TagType;
import io.github.cuisse.nbt.tags.*;

import java.util.HashMap;

/**
 * A class for reading NBT data from an input stream in simple format.
 * 
 * @author Brayan Roman
 * @since  1.0.0
 */
public class SimpleNBTReader implements NBTReader {

    @Override
    public NamedTag read(NBTInput input) {
        var type = TagType.fromInt(input.readByte());
        return new NamedTag(input.readString(), readTag(input, type, Tag.MAX_DEPTH));
    }

    @Override
    public Tag readTag(NBTInput input, int depth) {
        return readTag(input, TagType.fromInt(input.readByte()), depth);
    }

    @Override
    public Tag readTag(NBTInput input, TagType type, int depth) {
        if (depth <= 0) {
            throw new NBTException("NBT tag depth exceeded");
        }
        return switch (type) {
            case TAG_End        -> EndTag.create();
            case TAG_Byte       -> ByteTag.create(input.readByte());
            case TAG_Short      -> ShortTag.create(input.readShort());
            case TAG_Int        -> IntTag.create(input.readInt());
            case TAG_Long       -> LongTag.create(input.readLong());
            case TAG_Float      -> FloatTag.create(input.readFloat());
            case TAG_Double     -> DoubleTag.create(input.readDouble());
            case TAG_Byte_Array -> ByteArrayTag.create(input.readByteArray());
            case TAG_String     -> StringTag.create(input.readString());
            case TAG_List       -> readListTag(input, depth);
            case TAG_Compound   -> readCompoundTag(input, depth);
            case TAG_Int_Array  -> IntArrayTag.create(input.readIntArray());
            case TAG_Long_Array -> LongArrayTag.create(input.readLongArray());
        };
    }

    private ListTag readListTag(NBTInput input, int depth) {
        var type = TagType.fromInt(input.readByte());
        int size = input.readInt();
        if (size > depth) {
            throw new NBTException("NBT tag depth exceeded");
        }
        var list = new Tag[size];
        for (int i = 0; i < size; i++) {
            list[i] = readTag(input, type, depth - 1);
        }
        return ListTag.create(list);
    }

    private CompoundTag readCompoundTag(NBTInput input, int depth) {
        var compound = new HashMap<String, Tag>();
        while (true) {
            var type = TagType.fromInt(input.readByte());
            if (type == TagType.TAG_End) {
                break;
            }
            String name = input.readString();
            compound.put(name, readTag(input, type, depth - 1));
        }
        return CompoundTag.create(compound);
    }

}
