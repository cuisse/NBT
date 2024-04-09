package io.github.cuisse;

import io.github.cuisse.nbt.NamedTag;
import io.github.cuisse.nbt.Tag;
import io.github.cuisse.nbt.tags.CompoundTag;
import io.github.cuisse.nbt.tags.IntTag;
import io.github.cuisse.nbt.tags.StringTag;

import java.util.Map;

public class Main {

    public static void main(String[] args) {
        CompoundTag compound = CompoundTag.create(
                new NamedTag("test", Tag.from(5))
        );
        System.out.println(compound.get("test", IntTag.class).primitiveValue());
        System.out.println(Tag.prettyPrint(new NamedTag("root", compound), 0));
        System.out.println(Tag.prettyPrint(Tag.from(
                Map.of(1, (byte) 5, 2, "two", 3, "three")
        ), 0));

        var profile = CompoundTag.create(
            new NamedTag("name", StringTag.create("John")),
            new NamedTag("age" , IntTag.create(42))
        );

        System.out.println(Tag.prettyPrint(profile, 0));
    }
    
}