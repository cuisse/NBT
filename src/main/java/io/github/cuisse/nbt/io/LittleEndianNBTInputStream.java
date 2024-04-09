package io.github.cuisse.nbt.io;

import io.github.cuisse.nbt.NBTException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPInputStream;

/**
 * A class for reading NBT data from an input stream in little-endian format.
 * 
 * @author Brayan Roman
 * @since  1.0.0
 */
public class LittleEndianNBTInputStream implements NBTInput {

    protected final InputStream input;

    public LittleEndianNBTInputStream(InputStream input, boolean compressed) {
        try {
            this.input = compressed ? new GZIPInputStream(input) : input;
        } catch (Exception exception) {
            throw new NBTException(exception);
        }
    }

    @Override
    public int remaining() {
        try {
            return input.available();
        } catch (IOException exception) {
            throw new NBTException(exception);
        }
    }

    @Override
    public int skipBytes(int n) {
        try {
            return (int) input.skip(n);
        } catch (IOException exception) {
            throw new NBTException(exception);
        }
    }

    @Override
    public byte readByte() {
        try {
            return (byte) input.read();
        } catch (IOException exception) {
            throw new NBTException(exception);
        }
    }

    @Override
    public short readShort() {
        try {
            return (short) (input.read() | (input.read() << 8));
        } catch (IOException exception) {
            throw new NBTException(exception);
        }
    }

    @Override
    public int readInt() {
        try {
            return input.read() | (input.read() << 8) | (input.read() << 16) | (input.read() << 24);
        } catch (IOException exception) {
            throw new NBTException(exception);
        }
    }

    @Override
    public long readLong() {
        try {
            return input.read() | (input.read() << 8) | (input.read() << 16) | ((long) input.read() << 24) | ((long) input.read() << 32) | ((long) input.read() << 40) | ((long) input.read() << 48) | ((long) input.read() << 56);
        } catch (IOException exception) {
            throw new NBTException(exception);
        }
    }

    @Override
    public float readFloat() {
        return Float.intBitsToFloat(
                readInt()
        );
    }

    @Override
    public double readDouble() {
        return Double.longBitsToDouble(
                readLong()
        );
    }

    @Override
    public byte[] readByteArray() {
        byte[] bytes = new byte[assertAvailable(readInt())];
        try {
            input.read(bytes);
        } catch (IOException exception) {
            throw new NBTException(exception);
        }
        return bytes;
    }

    @Override
    public String readString() {
        byte[] bytes = new byte[(short) assertAvailable(readShort())];
        try {
            input.read(bytes);
        } catch (IOException e) {
            throw new NBTException(e);
        }
        return new String(bytes, StandardCharsets.UTF_8);
    }

    @Override
    public int[] readIntArray() {
        int[] ints = new int[assertAvailable(readInt())];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = readInt();
        }
        return ints;
    }

    @Override
    public long[] readLongArray() {
        long[] longs = new long[assertAvailable(readInt())];
        for (int i = 0; i < longs.length; i++) {
            longs[i] = readLong();
        }
        return longs;
    }

    @Override
    public void close() throws IOException {
        input.close();
    }

    protected int assertAvailable(int length) {
        if (length < 0) {
            throw new IndexOutOfBoundsException("Length must be positive, got " + length + ".");
        }
        if (length > remaining()) {
            throw new NBTException("Not enough data available, expected " + length + " bytes but only " + remaining() + " bytes available.");
        }
        return length;
    }

}
