package io.github.cuisse.nbt;

import java.io.InputStream;
import java.nio.ByteBuffer;

/**
 * InputStream implementation that reads from a ByteBuffer.
 * 
 * @author Brayan Roman
 * @since  1.0.0
 */
public class ByteBufferInputStream extends InputStream {

    private final ByteBuffer buffer;

    public ByteBufferInputStream(ByteBuffer buffer) {
        if (buffer == null) {
            throw new NullPointerException("buffer == null");
        } else {
            this.buffer = buffer;
        }
    }

    @Override
    public int read() {
        if (buffer.hasRemaining()) {
            return buffer.get() & 0xFF;
        }
        return -1;
    }

    @Override
    public int read(byte[] bytes, int off, int length) {
        if (buffer.hasRemaining()) {
            buffer.get(bytes, off, (length = Math.min(length, buffer.remaining())));
            return length;
        }
        return -1;
    }

    @Override
    public long skip(long n) {
        int len = Math.min((int) n, buffer.remaining());
        buffer.position(buffer.position() + len);
        return len;
    }

    @Override
    public int available() {
        return buffer.remaining();
    }

    @Override
    public boolean markSupported() {
        return true;
    }

    @Override
    public void mark(int readLimit) {
        buffer.mark();
    }

    @Override
    public void reset() {
        buffer.reset();
    }

    @Override
    public void close() {
    }

}
