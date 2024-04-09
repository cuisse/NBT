# NBT 

Simple NBT library written in Java.

## Usage
### Compound
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

### List:
```java
import io.github.cuisse.nbt.Tag;
import io.github.cuisse.nbt.tags.IntTag;
import io.github.cuisse.nbt.tags.ListTag;

var numbers = ListTag.create(
        IntTag.create(1),
        IntTag.create(2),
        IntTag.create(0),
);

System.out.println(numbers.getAt(1).value()); // Integer -> 1
```

### Printing
Very easy, you can use ```io.github.cuisse.nbt.Tag::toString``` or ```io.github.cuisse.nbt.Tag::prettyPrint(tag, depth)```. For example:

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