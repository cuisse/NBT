package io.github.cuisse.nbt.io;

import io.github.cuisse.nbt.NBTException;

import java.io.InputStream;

/**
 * A class for reading NBT data from an input stream in var-int format.
 *
 * @author Brayan Roman
 * @since  1.0.0
 */
public class VarIntNBTInputStream extends LittleEndianNBTInputStream {

    public VarIntNBTInputStream(InputStream is, boolean compressed) {
        super(is, compressed);
    }

    @Override
    public int readInt() {
        return decodeZigZag32(
                readUnsignedVarInt()
        );
    }

    @Override
    public long readLong() {
        return decodeZigZag64(
                readUnsignedVarLong()
        );
    }

    @Override
    public float readFloat() {
        return Float.intBitsToFloat(
                super.readInt()
        );
    }

    @Override
    public double readDouble() {
        return Double.longBitsToDouble(
                super.readLong()
        );
    }

    @Override
    public String readString() {
        byte[] bytes = new byte[assertAvailable(
                readUnsignedVarInt()
        )];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = readByte();
        }
        return new String(bytes);
    }

    protected int readUnsignedVarInt() {
        int value = 0;
        int index = 0;
        int b;
        while (((b = readByte()) & 0x80) != 0) {
            value |= (b & 0x7F) << index;
            index += 7;
            if (index > 35) {
                throw new NBTException("VarInt is too big");
            }
        }
        return value | (b << index);
    }

    protected int decodeZigZag32(int n) {
        return (n >>> 1) ^ -(n & 1);
    }

    protected long readUnsignedVarLong() {
        int numRead = 0;
        long result = 0;
        byte read;
        do {
            read = readByte();
            long value = (read & 0b01111111);
            result |= (value << (7 * numRead));

            numRead++;
            if (numRead > 10) {
                throw new NBTException("VarLong is too big");
            }
        } while ((read & 0b10000000) != 0);
        return result;
    }

    protected long decodeZigZag64(long n) {
        return (n >>> 1) ^ -(n & 1);
    }

}
