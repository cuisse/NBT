package io.github.cuisse.nbt;

import io.github.cuisse.nbt.tags.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents an immutable tag.
 *
 * @author Brayan Roman
 * @since  1.0.0
 */
public interface Tag extends Comparable<Tag> {

    int MAX_DEPTH = 512;

    /**
     * Returns the value of the tag.
     *
     * @return the value of the tag.
     */
    Object value();

    /**
     * Returns the type of the tag.
     * 
     * @return the type of the tag.
     */
    TagType type();

    /**
     * Returns the hash code of the tag.
     *
     * @return the hash code of the tag.
     */
    static int hash(int hash) {
        return hash == 0 ? 1 : hash;
    }

    static Tag from(Object object) {
        if (object == null) {
            return EndTag.create();
        } else {
            if (object instanceof Tag tag) {
                return tag;
            } else {
                return switch (object) {
                    case Byte    byteValue      -> ByteTag.create(byteValue);
                    case Short   shortValue     -> ShortTag.create(shortValue);
                    case Integer intValue       -> IntTag.create(intValue);
                    case Long    longValue      -> LongTag.create(longValue);
                    case Float   floatValue     -> FloatTag.create(floatValue);
                    case Double  doubleValue    -> DoubleTag.create(doubleValue);
                    case String  stringValue    -> StringTag.create(stringValue);
                    case byte[]  byteArrayValue -> ByteArrayTag.create(byteArrayValue);
                    case int[]   intArrayValue  -> IntArrayTag.create(intArrayValue);
                    case long[]  longArrayValue -> LongArrayTag.create(longArrayValue);
                    default -> throw new NBTException("Unknown tag type: " + object.getClass().getName());
                };
            }
        }
    }

    static Tag from(List<Object> value) {
        if (value.isEmpty()) {
            return EndTag.create();
        } else {
            var entry = value.get(0);
            if (entry instanceof Tag) {
                return ListTag.create(((Tag) entry).type(), (List<Tag>) ((Object) value));
            } else {
                var list = new ArrayList<Tag>();
                var type = TagType.TAG_End;
                for (var val : value) {
                    var tag = Tag.from(val);
                    if (type == TagType.TAG_End) {
                        type = tag.type();
                    } else if (type != tag.type()) {
                        throw new NBTException("List contains different tag types");
                    }
                    list.add(tag);
                }
                return ListTag.create(type, list);
            }
        }
    }

    static Tag from(Map<Object, Object> value) {
        if (value.isEmpty()) {
            return EndTag.create();
        } else {
            var entry = value.entrySet().stream().findFirst().get();
            if (entry.getKey() instanceof String) {
                if (entry.getValue() instanceof Tag) {
                    return CompoundTag.create((Map<String, Tag>) ((Object) value));
                } else {
                    var map = new HashMap<String, Tag>();
                    value.forEach((key, val) -> map.put((String) key, Tag.from(val)));
                    return CompoundTag.create(map);
                }
            } else {
                var map = new HashMap<String, Tag>();
                value.forEach((key, val) -> map.put(String.valueOf(key), Tag.from(val)));
                return CompoundTag.create(map);
            }
        }
    }

    static Tag from(byte value) {
        return ByteTag.create(value);
    }

    static Tag from(short value) {
        return ShortTag.create(value);
    }

    static Tag from(int value) {
        return IntTag.create(value);
    }

    static Tag from(long value) {
        return LongTag.create(value);
    }

    static Tag from(float value) {
        return FloatTag.create(value);
    }

    static Tag from(double value) {
        return DoubleTag.create(value);
    }

    /**
     * Pretty prints the specified tag.
     *
     * @param tag The tag to pretty print.
     * @param depth The depth of the tag.
     * @return The pretty printed tag.
     */
    static String prettyPrint(Object tag, int depth) {
        if (tag instanceof NamedTag ntag) {
            return prettyPrintInternal(ntag.tag(), ntag.name(), depth);
        } else {
            return prettyPrintInternal(tag, "", depth);
        }
    }

    private static String prettyPrintInternal(Object tag, String tname, int depth) {
        if (depth > MAX_DEPTH) {
            throw new NBTException("Reached maximum depth of " + MAX_DEPTH);
        }
        StringBuilder builder = new StringBuilder();
        if (tag instanceof CompoundTag compoundTag) {
            builder.append(compoundTag.type().name()).append("(").append(tname).append("): ");
            builder.append(compoundTag.size()).append(" parameters ").append(System.lineSeparator());
            builder.append(" ".repeat(depth)).append("{ ").append(System.lineSeparator());
            compoundTag.value().forEach((name, value) -> {
                builder.append(" ".repeat(depth + 1));
                builder.append(prettyPrintInternal(value, name, depth + 1));
            });
            builder.append(" ".repeat(depth));
            return builder.append("}").append(System.lineSeparator()).toString();
        }
        else if (tag instanceof ListTag listTag) {
            builder.append(listTag.type().name()).append("(").append(tname).append("): ");
            builder.append(listTag.value().size()).append(" parameters ").append(System.lineSeparator());
            builder.append(" ".repeat(depth)).append("{ ").append(System.lineSeparator());
            listTag.value().forEach(value -> {
                builder.append(" ".repeat(depth + 1));
                builder.append(prettyPrintInternal(value, "", depth + 1));
            });
            builder.append(" ".repeat(depth));
            return builder.append("}").append(System.lineSeparator()).toString();
        }
        else if (tag instanceof Tag vtag && vtag.value().getClass().isArray()) {
            builder.append(vtag.type().name()).append("(").append(tname).append("): [");
            builder.append(java.lang.reflect.Array.getLength(vtag.value())).append("]: ");
            for (int i = 0; i < java.lang.reflect.Array.getLength(vtag.value()); i++) {
                builder.append(java.lang.reflect.Array.get(vtag.value(), i)).append(", ");
            }
            return builder.append(System.lineSeparator()).toString();
        }
        else {
            builder.append(tname).append(": ");
            builder.append(tag.toString());
            return builder.append(System.lineSeparator()).toString();
        }
    }

}
