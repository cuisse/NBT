package io.github.cuisse.nbt;

import java.util.function.Consumer;

/**
 * An enum representing the different types of Tags.
 *
 * @author Brayan Roman
 * @since  1.0.0
 */
public enum TagType {
    TAG_End,
    TAG_Byte,
    TAG_Short,
    TAG_Int,
    TAG_Long,
    TAG_Float,
    TAG_Double,
    TAG_Byte_Array,
    TAG_String,
    TAG_List,
    TAG_Compound,
    TAG_Int_Array,
    TAG_Long_Array;

    private static final TagType[] values = values();

    public static void forEach(Consumer<TagType> consumer) {
        for (TagType type : values) {
            consumer.accept(type);
        }
    }

    public static TagType fromInt(int value) {
        try {
            return values[value];
        } catch (ArrayIndexOutOfBoundsException error) {
            throw new NBTException("Invalid tag type: " + value);
        }
    }

    @Override
    public String toString() {
        return switch (this) {
            case TAG_End        -> "TAG_End";
            case TAG_Byte       -> "TAG_Byte";
            case TAG_Short      -> "TAG_Short";
            case TAG_Int        -> "TAG_Int";
            case TAG_Long       -> "TAG_Long";
            case TAG_Float      -> "TAG_Float";
            case TAG_Double     -> "TAG_Double";
            case TAG_Byte_Array -> "TAG_Byte_Array";
            case TAG_String     -> "TAG_String";
            case TAG_List       -> "TAG_List";
            case TAG_Compound   -> "TAG_Compound";
            case TAG_Int_Array  -> "TAG_Int_Array";
            case TAG_Long_Array -> "TAG_Long_Array";
        };
    }

}
