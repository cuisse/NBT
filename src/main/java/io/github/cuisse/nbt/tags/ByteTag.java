package io.github.cuisse.nbt.tags;

import io.github.cuisse.nbt.Tag;
import io.github.cuisse.nbt.TagType;

/**
 * A {@link Tag} that contains a single {@code byte} value.
 *
 * @author Brayan Roman
 * @since  1.0.0
 */
public class ByteTag implements Tag {

    public static final ByteTag DEFAULT = new ByteTag((byte) 0);

    private static final ByteTag[] CACHE;
    private static final int CACHE_MIN = -128;
    private static final int CACHE_OFF = -CACHE_MIN;
    private static final int CACHE_MAX = 127;

    static {
        CACHE = new ByteTag[-CACHE_MIN + CACHE_MAX + 1];
        for (int i = 0; i < CACHE.length; i++) {
            CACHE[i] = new ByteTag((byte) (CACHE_MIN + i));
        }
    }

    /**
     * Creates a new {@link ByteTag} with the specified value.
     *
     * @param value the value of the tag.
     * @return the new tag.
     */
    public static ByteTag create(int value) {
        return CACHE[value + CACHE_OFF];
    }

    private final byte value;

    private ByteTag(byte value) {
        this.value = value;
    }

    @Override
    public Byte value() {
        return value;
    }

    public byte primitiveValue() {
        return value;
    }

    @Override
    public TagType type() {
        return TagType.TAG_Byte;
    }

    @Override
    public int compareTo(Tag other) {
        if (other instanceof ByteTag tag) {
            return Byte.compare(value, tag.value);
        } else {
            throw new IllegalArgumentException("Cannot compare " + getClass().getSimpleName() + " to " + other.getClass().getSimpleName());
        }
    }

    @Override
    public int hashCode() {
        return Byte.hashCode(value);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof ByteTag tag && value == tag.value;
    }

    @Override
    public String toString() {
        return "ByteTag(" + value + ')';
    }

}
