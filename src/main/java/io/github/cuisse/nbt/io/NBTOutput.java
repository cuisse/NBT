package io.github.cuisse.nbt.io;

/**
 * An interface for writing NBT data.
 *
 * @author Brayan Roman
 * @since  1.0.0
 */
public interface NBTOutput extends AutoCloseable {

    /**
     * Writes a single byte to this output stream.
     * @param value The byte to be written.
     */
    void writeByte(byte value);

    /**
     * Writes a single short to this output stream.
     * @param value The short to be written.
     */
    void writeShort(short value);

    /**
     * Writes a single int to this output stream.
     * @param value The int to be written.
     */
    void writeInt(int value);

    /**
     * Writes a single long to this output stream.
     * @param value The long to be written.
     */
    void writeLong(long value);

    /**
     * Writes a single float to this output stream.
     * @param value The float to be written.
     */
    void writeFloat(float value);

    /**
     * Writes a single double to this output stream.
     * @param value The double to be written.
     */
    void writeDouble(double value);

    /**
     * Writes a single byte array to this output stream.
     * @param value The byte array to be written.
     */
    void writeByteArray(byte[] value);

    /**
     * Writes a single int array to this output stream.
     * @param value The int array to be written.
     */
    void writeString(String value);

    /**
     * Writes a single int array to this output stream.
     * @param value The int array to be written.
     */
    void writeIntArray(int[] value);

    /**
     * Writes a single long array to this output stream.
     * @param value The long array to be written.
     */
    void writeLongArray(long[] value);

}
