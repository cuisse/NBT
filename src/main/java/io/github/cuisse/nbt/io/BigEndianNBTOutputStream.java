package io.github.cuisse.nbt.io;

import java.io.OutputStream;

/**
 * A class for writing NBT data to an output stream in big-endian format.
 * 
 * @author Brayan Roman
 * @since  1.0.0
 */
public class BigEndianNBTOutputStream extends LittleEndianNBTOutputStream {

    public BigEndianNBTOutputStream(OutputStream os, boolean compress) {
        super(os, compress);
    }

    @Override
    public void writeShort(short value) {
        super.writeShort(Short.reverseBytes(value));
    }

    @Override
    public void writeInt(int value) {
        super.writeInt(Integer.reverseBytes(value));
    }

    @Override
    public void writeLong(long value) {
        super.writeLong(Long.reverseBytes(value));
    }

}
