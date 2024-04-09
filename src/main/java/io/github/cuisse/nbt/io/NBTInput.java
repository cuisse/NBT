package io.github.cuisse.nbt.io;

/**
 * An interface for reading NBT data.
 *
 * @author Brayan Roman
 * @since  1.0.0
 */
public interface NBTInput extends AutoCloseable {

    /**
     * Remaining bytes in this input stream.
     * @return The number of bytes remaining.
     */
    int remaining();

    /**
     * Skips over and discards n bytes of data from this input stream.
     * @param n The number of bytes to be skipped.
     * @return The actual number of bytes skipped.
     */
    int skipBytes(int n);

    /**
     * Reads a single byte from this input stream.
     * @return The byte read.
     */
    byte readByte();

    /**
     * Reads a single short from this input stream.
     * @return The short read.
     */
    short readShort();

    /**
     * Reads a single int from this input stream.
     * @return The int read.
     */
    int readInt();

    /**
     * Reads a single long from this input stream.
     * @return The long read.
     */
    long readLong();

    /**
     * Reads a single float from this input stream.
     * @return The float read.
     */
    float readFloat();

    /**
     * Reads a single double from this input stream.
     * @return The double read.
     */
    double readDouble();

    /**
     * Reads a single byte array from this input stream.
     * @return The byte array read.
     */
    byte[] readByteArray();

    /**
     * Reads a single int array from this input stream.
     * @return The int array read.
     */
    String readString();

    /**
     * Reads a single int array from this input stream.
     * @return The int array read.
     */
    int[] readIntArray();

    /**
     * Reads a single long array from this input stream.
     * @return The long array read.
     */
    long[] readLongArray();

}
