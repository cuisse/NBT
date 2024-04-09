package io.github.cuisse.nbt.tags;

import io.github.cuisse.nbt.NamedTag;
import io.github.cuisse.nbt.Tag;
import io.github.cuisse.nbt.TagType;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * A {@link Tag} that contains a map of {@link String} to {@link Tag}.
 *
 * @author Brayan Roman
 * @since  1.0.0
 */
public class CompoundTag implements Tag, Iterable<Map.Entry<String, Tag>> {

    public static final CompoundTag DEFAULT = new CompoundTag(Collections.emptyMap());

    /**
     * <p>Creates a new {@link CompoundTag} with the given objects as the tags.</p>
     *
     * <p>Each object must be a {@link String} and a {@link Tag}.</p>
     *
     * @param objects The objects to add to the compound tag.
     * @return The new compound tag.
     * @throws ClassCastException If the object is not a {@link String} or a {@link Tag}.
     * @throws IndexOutOfBoundsException If the object is not a pair of {@link String} and {@link Tag}.
     */
    public static CompoundTag create(Object... objects) throws ClassCastException, IndexOutOfBoundsException {
        if (objects.length == 0) {
            return DEFAULT;
        } else {
            if (objects.length % 2 != 0) {
                throw new IndexOutOfBoundsException("Odd number of objects");
            }
            if (objects.length == 2) {
                return new CompoundTag(Map.of(String.valueOf(objects[0]), Tag.from(objects[1])));
            } else {
                var map = new HashMap<String, Tag>();
                for (int i = 0; i < objects.length; i += 2) {
                    map.put(String.valueOf(objects[i]), Tag.from(objects[i + 1]));
                }
                return new CompoundTag(map);
            }
        }
    }

    /**
     * Creates a new {@link CompoundTag} with the given map of {@link String} to {@link Tag}.
     *
     * @param value The map of tags to add to the compound tag.
     * @return The new compound tag.
     */
    public static CompoundTag create(Map<String, Tag> value) {
        return new CompoundTag(value);
    }

    /**
     * Creates a new {@link CompoundTag} with the given {@link NamedTag}s.
     *
     * @param tags The tags to add to the compound tag.
     * @return The new compound tag.
     */
    public static CompoundTag create(NamedTag... tags) {
        var map = new HashMap<String, Tag>();
        for (NamedTag tag : tags) {
            if (tag == null) {
                throw new IllegalArgumentException("Tag cannot be null");
            }
            map.put(tag.name(), tag.tag());
        }
        return new CompoundTag(map);
    }

    /**
     * Creates a new {@link CompoundTag} with the given {@link NamedTag}s.
     *
     * @param compoundTag The compound tag to add the tags to.
     * @param tags The tags to add to the compound tag.
     * @return The new compound tag.
     */
    public static CompoundTag create(CompoundTag compoundTag, NamedTag... tags) {
        var map = new HashMap<>(compoundTag.value);
        for (NamedTag tag : tags) {
            if (tag == null) {
                throw new IllegalArgumentException("Tag cannot be null");
            }
            map.put(tag.name(), tag.tag());
        }
        return new CompoundTag(map);
    }

    private final Map<String, Tag> value;
    private volatile int hashcode;

    private CompoundTag(Map<String, Tag> value) {
        this.value = unmodifiable(value);
    }

    @Override
    public Map<String, Tag> value() {
        return value;
    }

    @Override
    public TagType type() {
        return TagType.TAG_Compound;
    }

    /**
     * Returns the {@link Tag} with the given name.
     *
     * @param name The name of the tag.
     * @return The tag with the given name.
     */
    public Tag get(String name) {
        return get(name, Tag.class);
    }

    /**
     * Returns the {@link Tag} with the given name.
     *
     * @param name The name of the tag.
     * @param expectedType The class of the tag.
     * @return The tag with the given name.
     */
    public<T extends Tag> T get(String name, Class<T> expectedType) {
        Tag tag = value.get(name);
        return tag != null ? expectedType.cast(tag) : null;
    }

    /**
     * Returns the size of the compound tag.
     *
     * @return The size of the compound tag.
     */
    public int size() {
        return value.size();
    }

    @Override
    public int compareTo(Tag other) {
        if (other instanceof CompoundTag tag) {
            return Integer.compare(value.size(), tag.value.size());
        } else {
            throw new IllegalArgumentException("Cannot compare " + getClass().getSimpleName() + " to " + other.getClass().getSimpleName());
        }
    }

    @Override
    public int hashCode() {
        if (hashcode == 0) {
            int hash = 1;
            for (Map.Entry<String, Tag> entry : value.entrySet()) {
                hash = 31 * hash + entry.getKey().hashCode();
                hash = 31 * hash + entry.getValue().hashCode();
            }
            hashcode = Tag.hash(hash);
        }
        return hashcode;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) { return true; }
        if (other == null) { return false; }
        if (other instanceof CompoundTag tag) {
            if (value.size() != tag.value.size()) {
                return false;
            }
            for (Map.Entry<String, Tag> entry : value.entrySet()) {
                if (!entry.getValue().equals(tag.value.get(entry.getKey()))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public Iterator<Map.Entry<String, Tag>> iterator() {
        return value.entrySet().iterator();
    }

    @Override
    public String toString() {
        return "TAG_COMPOUND(" + value + ")";
    }

    private Map<String, Tag> unmodifiable(Map<String, Tag> value) {
        try {
            value.putAll(Map.of());
            return Collections.unmodifiableMap(value);
        } catch (UnsupportedOperationException ignored) {
            return value;
        }
    }

}
