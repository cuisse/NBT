package io.github.cuisse.nbt.io;

import io.github.cuisse.nbt.NBTException;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPOutputStream;

/**
 * A class for writing NBT data to an output stream in little-endian format.
 * 
 * @author Brayan Roman
 * @since  1.0.0
 */
public class LittleEndianNBTOutputStream implements NBTOutput {

    protected final OutputStream output;

    public LittleEndianNBTOutputStream(OutputStream output, boolean compress) {
        try {
            this.output = compress ? new GZIPOutputStream(output) : output;
        } catch (IOException exception) {
            throw new NBTException(exception);
        }
    }

    @Override
    public void writeByte(byte value) {
        try {
            output.write(value);
        } catch (Exception exception) {
            throw new NBTException(exception);
        }
    }

    @Override
    public void writeShort(short value) {
        try {
            output.write(value);
            output.write(value >>> 8);
        } catch (Exception exception) {
            throw new NBTException(exception);
        }
    }

    @Override
    public void writeInt(int value) {
        try {
            output.write(value);
            output.write(value >>> 8);
            output.write(value >>> 16);
            output.write(value >>> 24);
        } catch (Exception exception) {
            throw new NBTException(exception);
        }
    }

    @Override
    public void writeLong(long value) {
        try {
            output.write((int) value);
            output.write((int) (value >>> 8));
            output.write((int) (value >>> 16));
            output.write((int) (value >>> 24));
            output.write((int) (value >>> 32));
            output.write((int) (value >>> 40));
            output.write((int) (value >>> 48));
            output.write((int) (value >>> 56));
        } catch (Exception exception) {
            throw new NBTException(exception);
        }
    }

    @Override
    public void writeFloat(float value) {
        writeInt(Float.floatToIntBits(value));
    }

    @Override
    public void writeDouble(double value) {
        writeLong(Double.doubleToLongBits(value));
    }

    @Override
    public void writeString(String value) {
        try {
            byte[] bytes = value.getBytes(StandardCharsets.UTF_8);
            writeShort((short) bytes.length);
            output.write(bytes);
        } catch (Exception exception) {
            throw new NBTException(exception);
        }
    }

    @Override
    public void writeByteArray(byte[] value) {
        try {
            writeInt(value.length);
            output.write(value);
        } catch (Exception exception) {
            throw new NBTException(exception);
        }
    }

    @Override
    public void writeIntArray(int[] value) {
        writeInt(value.length);
        for (int i : value) {
            writeInt(i);
        }
    }

    @Override
    public void writeLongArray(long[] value) {
        writeInt(value.length);
        for (long i : value) {
            writeLong(i);
        }
    }

    @Override
    public void close() throws Exception {
        output.close();
    }

}
