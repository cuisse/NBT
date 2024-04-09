package io.github.cuisse.nbt.tags;

import io.github.cuisse.nbt.Tag;
import io.github.cuisse.nbt.TagType;

/**
 * A {@link Tag} that contains a {@code float} value.
 *
 * @author Brayan Roman
 * @since  1.0.0
 */
public class IntTag implements Tag {

    public static final IntTag DEFAULT = new IntTag(0);

    private static final IntTag[] CACHE;

    private static final int CACHE_MIN = -128;
    private static final int CACHE_OFF = -CACHE_MIN;
    private static final int CACHE_MAX = 1170;

    static {
        CACHE = new IntTag[-CACHE_MIN + CACHE_MAX + 1];
        for (int i = 0; i < CACHE.length; i++) {
            CACHE[i] = new IntTag(CACHE_MIN + i);
        }
    }

    public static IntTag create(int value) {
        return (value >= CACHE_MIN && value <= CACHE_MAX) ? CACHE[value + CACHE_OFF] : new IntTag(value);
    }

    private final int value;

    private IntTag(int value) {
        this.value = value;
    }

    @Override
    public Integer value() {
        return value;
    }

    public int primitiveValue() {
        return value;
    }

    @Override
    public TagType type() {
        return TagType.TAG_Int;
    }

    @Override
    public int compareTo(Tag other) {
        if (other instanceof IntTag tag) {
            return Integer.compare(value, tag.value);
        } else {
            throw new IllegalArgumentException("Cannot compare " + getClass().getSimpleName() + " to " + other.getClass().getSimpleName());
        }
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof IntTag tag && value == tag.value;
    }

    @Override
    public String toString() {
        return "IntTag(" + value + ')';
    }

}
