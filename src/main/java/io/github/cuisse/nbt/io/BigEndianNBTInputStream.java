package io.github.cuisse.nbt.io;

import java.io.InputStream;

/**
 * A class for reading NBT data from an input stream in big-endian format.
 * 
 * @author Brayan Roman
 * @since  1.0.0
 */
public class BigEndianNBTInputStream extends LittleEndianNBTInputStream {

    public BigEndianNBTInputStream(InputStream is, boolean compressed) {
        super(is, compressed);
    }

    @Override
    public short readShort() {
        return Short.reverseBytes(super.readShort());
    }

    @Override
    public int readInt() {
        return Integer.reverseBytes(super.readInt());
    }

    @Override
    public long readLong() {
        return Long.reverseBytes(super.readLong());
    }

}
