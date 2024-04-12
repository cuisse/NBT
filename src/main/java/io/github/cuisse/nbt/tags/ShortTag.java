package io.github.cuisse.nbt.tags;

import io.github.cuisse.nbt.Tag;
import io.github.cuisse.nbt.TagType;

/**
 * A {@link Tag} that stores a {@code short} value.
 *
 * @author Brayan Roman
 * @since  1.0.0
 */
public class ShortTag implements Tag {

    public static final ShortTag DEFAULT = new ShortTag((short) 0);

    private static final ShortTag[] CACHE;
    private static final int CACHE_MIN = -128;
    private static final int CACHE_OFF = -CACHE_MIN;
    private static final int CACHE_MAX = 1170;

    static {
        CACHE = new ShortTag[-CACHE_MIN + CACHE_MAX + 1];
        for (int i = 0; i < CACHE.length; i++) {
            CACHE[i] = new ShortTag((short) (CACHE_MIN + i));
        }
    }

    /**
     * Creates a new {@link ShortTag} with the specified value.
     *
     * @param value the value of the tag.
     * @return the new tag.
     */
    public static ShortTag create(int value) {
        return (value >= CACHE_MIN && value <= CACHE_MAX) ? CACHE[value + CACHE_OFF] : new ShortTag((short) value);
    }

    private final short value;

    private ShortTag(short value) {
        this.value = value;
    }

    @Override
    public Short value() {
        return value;
    }

    public short primitiveValue() {
        return value;
    }

    @Override
    public TagType type() {
        return TagType.TAG_Short;
    }

    @Override
    public int compareTo(Tag other) {
        if (other instanceof ShortTag tag) {
            return Short.compare(value, tag.value);
        } else {
            throw new IllegalArgumentException("Cannot compare " + getClass().getSimpleName() + " to " + other.getClass().getSimpleName());
        }
    }

    @Override
    public int hashCode() {
        return Short.hashCode(value);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ShortTag tag && value == tag.value;
    }

    @Override
    public String toString() {
        return "ShortTag(" + value + ')';
    }

}
