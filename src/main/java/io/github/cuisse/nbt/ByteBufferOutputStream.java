package io.github.cuisse.nbt;

import java.io.OutputStream;
import java.nio.ByteBuffer;

/**
 * OutputStream implementation that writes to a ByteBuffer.
 * 
 * @author Brayan Roman
 * @since  1.0.0
 */
public class ByteBufferOutputStream extends OutputStream {

    private final ByteBuffer buffer;

    public ByteBufferOutputStream(ByteBuffer buffer) {
        if (buffer == null) {
            throw new NullPointerException("buffer == null");
        } else {
            this.buffer = buffer;
        }
    }

    @Override
    public void write(int b) {
        buffer.put((byte) b);
    }

    @Override
    public void write(byte[] bytes, int off, int length) {
        buffer.put(bytes, off, length);
    }

    @Override
    public void flush() { }

    @Override
    public void close() { }

}
