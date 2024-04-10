# NBT 
[![Maven Central](https://img.shields.io/maven-central/v/io.github.cuisse/nbt.svg?label=Maven%20Central)](https://central.sonatype.com/artifact/io.github.cuisse/nbt)
[![javadoc](https://javadoc.io/badge2/io.github.cuisse/nbt/javadoc.svg)](https://javadoc.io/doc/io.github.cuisse/nbt)
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](https://opensource.org/licenses/MIT)

NBT library in Java.

## Usage
Creating a simple compound:
```java
import io.github.cuisse.nbt.NamedTag;
import io.github.cuisse.nbt.tags.IntTag;
import io.github.cuisse.nbt.tags.StringTag;
import io.github.cuisse.nbt.tags.CompoundTag;

var profile = CompoundTag.create(
        new NamedTag("name", StringTag.create("John")),
        new NamedTag("age" , IntTag.create(42))
);

System.out.println(profile.get("name").value()); // String  -> John
System.out.println(profile.get("age").value());  // Integer -> 42

// Or
System.out.println(profile.get("name").value()); // String  -> John
System.out.println(profile.get("age", IntTag.class).primitiveValue()); // int -> 42
```

And since we all enjoy the sugar:
```java
import io.github.cuisse.nbt.Tag;
import java.util.Map;

var profile = Tag.from(
        Map.of(
                "name", "John",
                "age" , 42
        )
);

System.out.println(profile.get("name").value()); // String  -> John
System.out.println(profile.get("age").value());  // Integer -> 42
```

If you don't afraid of Unchecked Exceptions:
```java
import io.github.cuisse.nbt.Tag;
import io.github.cuisse.nbt.tags.CompoundTag;

var profile = CompoundTag.create(
        "name", Tag.from("John"),
        "age" , Tag.from(42)
);

System.out.println(profile.get("name").value()); // String  -> John
System.out.println(profile.get("age", IntTag.class).primitiveValue()); // int -> 42
```

You can also work with a List:
```java
import io.github.cuisse.nbt.Tag;
import io.github.cuisse.nbt.tags.IntTag;
import io.github.cuisse.nbt.tags.ListTag;

var numbers = ListTag.create(
        IntTag.create(0),
        IntTag.create(1),
        IntTag.create(2),
);

System.out.println(numbers.getAt(1).value()); // Integer -> 1
```

### Printing
Printing is very easy, you can use ```io.github.cuisse.nbt.Tag::toString``` or ```io.github.cuisse.nbt.Tag::prettyPrint(tag, depth)```. For example:

```java
import io.github.cuisse.nbt.Tag;
import io.github.cuisse.nbt.NamedTag;
import io.github.cuisse.nbt.tags.IntTag;
import io.github.cuisse.nbt.tags.StringTag;
import io.github.cuisse.nbt.tags.CompoundTag;

var profile = CompoundTag.create(
        new NamedTag("name", StringTag.create("John")),
        new NamedTag("age" , IntTag.create(42))
);

System.out.println(Tag.prettyPrint(profile, 0));

// Output:
// TAG_Compound(): 2 parameters
// {
//  name: StringTag("John")
//  age: IntTag(42)
// }
```