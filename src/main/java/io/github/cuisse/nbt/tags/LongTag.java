package io.github.cuisse.nbt.tags;

import io.github.cuisse.nbt.Tag;
import io.github.cuisse.nbt.TagType;

/**
 * A {@link Tag} that contains a {@code long} value.
 *
 * @author Brayan Roman
 * @since  1.0.0
 */
public class LongTag implements Tag {

    public static final LongTag DEFAULT = new LongTag(0);

    private static final LongTag[] CACHE;
    private static final int CACHE_MIN = -128;
    private static final int CACHE_OFF = -CACHE_MIN;
    private static final int CACHE_MAX = 1170;

    static {
        CACHE = new LongTag[-CACHE_MIN + CACHE_MAX + 1];
        for (int i = 0; i < CACHE.length; i++) {
            CACHE[i] = new LongTag(CACHE_MIN + i);
        }
    }

    /**
     * Creates a new {@link LongTag} with the specified value.
     *
     * @param value the value of the tag.
     * @return the new tag.
     */
    public static LongTag create(long value) {
        return (value >= CACHE_MIN && value <= CACHE_MAX) ? CACHE[(int) value + CACHE_OFF] : new LongTag(value);
    }

    private final long value;

    private LongTag(long value) {
        this.value = value;
    }

    @Override
    public Long value() {
        return value;
    }

    public long primitiveValue() {
        return value;
    }

    @Override
    public TagType type() {
        return TagType.TAG_Long;
    }

    @Override
    public int compareTo(Tag other) {
        if (other instanceof LongTag tag) {
            return Long.compare(value, tag.value);
        } else {
            throw new IllegalArgumentException("Cannot compare " + getClass().getSimpleName() + " to " + other.getClass().getSimpleName());
        }
    }

    @Override
    public int hashCode() {
        return Long.hashCode(value);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof LongTag tag && value == tag.value;
    }

    @Override
    public String toString() {
        return "Long(" + value + ')';
    }

}
