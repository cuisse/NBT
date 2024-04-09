package io.github.cuisse.nbt;

import io.github.cuisse.nbt.io.BigEndianNBTInputStream;
import io.github.cuisse.nbt.io.BigEndianNBTOutputStream;
import io.github.cuisse.nbt.io.LittleEndianNBTInputStream;
import io.github.cuisse.nbt.io.LittleEndianNBTOutputStream;
import io.github.cuisse.nbt.io.NBTInput;
import io.github.cuisse.nbt.io.NBTOutput;
import io.github.cuisse.nbt.io.NBTReader;
import io.github.cuisse.nbt.io.NBTStream;
import io.github.cuisse.nbt.io.NBTWriter;
import io.github.cuisse.nbt.io.SimpleNBTReader;
import io.github.cuisse.nbt.io.SimpleNBTWriter;
import io.github.cuisse.nbt.io.VarIntNBTInputStream;
import io.github.cuisse.nbt.io.VarIntNBTOutputStream;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

public final class NBT {

    private static final NBTReader READER = new SimpleNBTReader();
    private static final NBTWriter WRITER = new SimpleNBTWriter();

    /**
     * Returns the default NBT reader.
     * @return the default NBT reader.
     */
    public static NBTReader reader() {
        return READER;
    }

    /**
     * Returns the default NBT writer.
     * @return the default NBT writer.
     */
    public static NBTWriter writer() {
        return WRITER;
    }

    /**
     * Creates a new NBT reader.
     * 
     * @param type the NBT stream type.
     * @param data the NBT data.
     * @param compressed whether the data is compressed.
     * @return a new NBT reader.
     */
    public static NBTInput input(NBTStream type, byte[] data, boolean compressed) {
        var stream = new ByteArrayInputStream(data);
        return switch (type) {
            case LITTLE_ENDIAN -> new LittleEndianNBTInputStream(stream, compressed);
            case BIG_ENDIAN    -> new BigEndianNBTInputStream(stream, compressed);
            case VARINT        -> new VarIntNBTInputStream(stream, compressed);
        };
    }

    /**
     * Creates a new NBT reader.
     * 
     * @param type the NBT stream type.
     * @param data the NBT data.
     * @param compressed whether the data is compressed.
     * @return a new NBT reader.
     */
    public static NBTInput input(NBTStream type, ByteBuffer data, boolean compressed) {
        var stream = new ByteBufferInputStream(data);
        return switch (type) {
            case LITTLE_ENDIAN -> new LittleEndianNBTInputStream(stream, compressed);
            case BIG_ENDIAN    -> new BigEndianNBTInputStream(stream, compressed);
            case VARINT        -> new VarIntNBTInputStream(stream, compressed);
        };
    }

    /**
     * Creates a new NBT reader.
     * 
     * @param type the NBT stream type.
     * @param stream the input stream.
     * @param compressed whether the data is compressed.
     * @return a new NBT reader.
     */
    public static NBTInput input(NBTStream type, InputStream stream, boolean compressed) {
        return switch (type) {
            case LITTLE_ENDIAN -> new LittleEndianNBTInputStream(stream, compressed);
            case BIG_ENDIAN    -> new BigEndianNBTInputStream(stream, compressed);
            case VARINT        -> new VarIntNBTInputStream(stream, compressed);
        };
    }

    /**
     * Creates a new NBT writer.
     * 
     * @param type the NBT stream type.
     * @param data the NBT data.
     * @param compressed whether the data is compressed.
     * @return a new NBT writer.
     */
    public static NBTOutput output(NBTStream type, ByteBuffer data, boolean compressed) {
        var stream = new ByteBufferOutputStream(data);
        return switch (type) {
            case LITTLE_ENDIAN -> new LittleEndianNBTOutputStream(stream, compressed);
            case BIG_ENDIAN    -> new BigEndianNBTOutputStream(stream, compressed);
            case VARINT        -> new VarIntNBTOutputStream(stream, compressed);
        };
    }

    /**
     * Creates a new NBT writer.
     * 
     * @param type the NBT stream type.
     * @param stream the output stream.
     * @param compressed whether the data is compressed.
     * @return a new NBT writer.
     */
    public static NBTOutput output(NBTStream type, OutputStream stream, boolean compressed) {
        return switch (type) {
            case LITTLE_ENDIAN -> new LittleEndianNBTOutputStream(stream, compressed);
            case BIG_ENDIAN    -> new BigEndianNBTOutputStream(stream, compressed);
            case VARINT        -> new VarIntNBTOutputStream(stream, compressed);
        };
    }

    private NBT() {
        // nope
    }

}
