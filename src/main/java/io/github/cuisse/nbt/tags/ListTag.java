package io.github.cuisse.nbt.tags;

import io.github.cuisse.nbt.Tag;
import io.github.cuisse.nbt.TagType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * A {@link Tag} that contains a list of {@link Tag}s.
 * <p>
 * 
 * TODO: Make sure that every entry is unique, maybe using a Set?
 *
 * @author Brayan Roman
 * @since 1.0.0
 */
public class ListTag implements Tag {

    public static final ListTag DEFAULT = new ListTag(TagType.TAG_End, Collections.emptyList());

    public static ListTag create(TagType type, List<Tag> value) {
        return new ListTag(type, value);
    }

    /**
     * Creates a new {@link ListTag} using the {@link TagType} of the first {@link Tag} in the array.
     *
     * @param tags The {@link Tag}s to add to the {@link ListTag}.
     * @return A new {@link ListTag} with the specified {@link TagType} and {@link Tag}s.
     */
    public static ListTag create(Tag... tags) {
        if (tags.length == 0) {
            return DEFAULT;
        } else {
            return create(tags[0].type(), tags);
        }
    }

    /**
     * Creates a new {@link ListTag} with the specified {@link TagType} and {@link Tag}s.
     *
     * @param type The {@link TagType} of the {@link ListTag}.
     * @param tags The {@link Tag}s to add to the {@link ListTag}.
     * @return A new {@link ListTag} with the specified {@link TagType} and {@link Tag}s.
     */
    public static ListTag create(TagType type, Tag... tags) {
        return new ListTag(type, Arrays.asList(tags));
    }

    /**
     * Creates a new {@link ListTag} with the specified {@link TagType} and {@link Tag}s.
     *
     * @param tag The {@link TagType} of the {@link ListTag}.
     * @param tags The {@link Tag}s to add to the {@link ListTag}.
     * @return A new {@link ListTag} with the specified {@link TagType} and {@link Tag}s.
     */
    public static<T extends Tag> ListTag create(ListTag tag, T... tags) {
        List<Tag> list = new ArrayList<>(tag.value);
        list.addAll(Arrays.asList(tags));
        return new ListTag(tag.getType(), list);
    }

    private final TagType type;
    private final List<Tag> value;
    private volatile int hashcode;

    public ListTag(TagType type, List<Tag> value) {
        for (Tag tag : value) {
            if (tag.type() != type) {
                throw new IllegalArgumentException("Cannot add " + tag.getClass().getSimpleName() + " to " + getClass().getSimpleName());
            }
        }
        this.type = type;
        this.value = unmodifiable(value);
    }

    public TagType getType() {
        return type;
    }

    @Override
    public List<Tag> value() {
        return value;
    }

    @Override
    public TagType type() {
        return TagType.TAG_List;
    }

    /**
     * Returns the {@link Tag} at the specified index.
     *
     * @param index The index of the {@link Tag} to return.
     * @return The {@link Tag} at the specified index.
     */
    public Tag getAt(int index) {
        return value.get(index);
    }

    public<T extends Tag> T getAt(int index, Class<T> clazz) {
        return clazz.cast(value.get(index));
    }

    /**
     * Returns the size of the list.
     *
     * @return The size of the list.
     */
    public int size() {
        return value.size();
    }

    @Override
    public int compareTo(Tag other) {
        if (other instanceof ListTag tag) {
            if (type != tag.type) {
                throw new IllegalArgumentException("Cannot compare " + getClass().getSimpleName() + " to " + other.getClass().getSimpleName());
            }
            return Integer.compare(value.size(), tag.value.size());
        } else {
            throw new IllegalArgumentException("Cannot compare " + getClass().getSimpleName() + " to " + other.getClass().getSimpleName());
        }
    }

    @Override
    public int hashCode() {
        if (hashcode == 0) {
            int hash = 1;
            for (Tag tag : value) {
                hash = 31 * hash + tag.hashCode();
            }
            hashcode = Tag.hash(hash);
        }
        return hashcode;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ListTag tag) {
            if (type != tag.type) {
                return false;
            }
            return value.equals(tag.value);
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "List(" + type + ", " + value + ')';
    }

    private List<Tag> unmodifiable(List<Tag> value) {
        try {
            value.addAll(List.of());
            return Collections.unmodifiableList(value);
        } catch (UnsupportedOperationException ignored) {
            return value;
        }
    }

}
