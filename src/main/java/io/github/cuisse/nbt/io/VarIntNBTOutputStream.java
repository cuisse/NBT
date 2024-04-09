package io.github.cuisse.nbt.io;

import java.io.OutputStream;

/**
 * A class for writing NBT data to an output stream in varint format.
 * 
 * @author Brayan Roman
 * @since  1.0.0
 */
public class VarIntNBTOutputStream extends LittleEndianNBTOutputStream {

    public VarIntNBTOutputStream(OutputStream os, boolean compress) {
        super(os, compress);
    }

    @Override
    public void writeInt(int value) {
        writeUnsignedVarInt(
                encodeZigZag32(value)
        );
    }

    @Override
    public void writeLong(long value) {
        writeUnsignedVarLong(
                encodeZigZag64(value)
        );
    }

    @Override
    public void writeFloat(float value) {
        super.writeInt(
                Float.floatToIntBits(value)
        );
    }

    @Override
    public void writeDouble(double value) {
        super.writeLong(
                Double.doubleToLongBits(value)
        );
    }

    @Override
    public void writeString(String value) {
        byte[] bytes = value.getBytes();
        writeUnsignedVarInt(bytes.length);
        for (byte b : bytes) {
            writeByte(b);
        }
    }

    protected void writeUnsignedVarInt(int value) {
        while ((value & 0xFFFFFF80) != 0L) {
            writeByte((byte) ((value & 0x7F) | 0x80));
            value >>>= 7;
        }
        writeByte((byte) (value & 0x7F));
    }

    protected int encodeZigZag32(int n) {
        return (n << 1) ^ (n >> 31);
    }

    protected void writeUnsignedVarLong(long value) {
        while ((value & 0xFFFFFFFFFFFFFF80L) != 0L) {
            writeByte((byte) ((value & 0x7F) | 0x80));
            value >>>= 7;
        }
        writeByte((byte) (value & 0x7F));
    }

    protected long encodeZigZag64(long n) {
        return (n << 1) ^ (n >> 63);
    }

}
