# Iwillfailyou

<img src="https://raw.githubusercontent.com/iwillfailyou/service/master/assets/logo.png" height="200px"/>

[![Elegant Objects Respected Here](https://www.elegantobjects.org/badge.svg)](https://www.elegantobjects.org/)

![nullfree status](https://iwillfailyou.com/nullfree/iwillfailyou/service)
![staticfree status](https://iwillfailyou.com/staticfree/iwillfailyou/service)
![allfinal status](https://iwillfailyou.com/allfinal/iwillfailyou/service)
![allpublic status](https://iwillfailyou.com/allpublic/iwillfailyou/service)
![setterfree status](https://iwillfailyou.com/setterfree/iwillfailyou/service)

[![Build Status](https://travis-ci.com/iwillfailyou/service.svg?branch=master)](https://travis-ci.com/iwillfailyou/service)
[![codecov](https://codecov.io/gh/iwillfailyou/service/branch/master/graph/badge.svg)](https://codecov.io/gh/iwillfailyou/service)

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://github.com/iwillfailyou/service/blob/master/LICENSE)

## What is it?

**Iwillfailyou** is the static analyser for your 
[elegant](https://www.elegantobjects.org/) code.

## How it works?

Just see, what it can find in seemingly usual code:
```java
package com.example;

import java.util.Collection;
import java.util.StringTokenizer;

public class Words {
    public static final String DELIM = " ,.";

    private Collection<String> words;

    public Words() {
        this.words = null;
    }

    public Words(Collection<String> words) {
        this.words = words;
    }

    boolean containsIn(String text) {
        if (words == null) return false;

        StringTokenizer tokenizer = new StringTokenizer(text, DELIM);
        while (tokenizer.hasMoreTokens()) {
            String nextWord = tokenizer.nextToken();
            if (words.contains(nextWord)) return true;
        }

        return false;
    }
}
```
The violations of the **iwillfailyou** analysis:
```
nullfree
com.example.Words(Words.java:12) > null
com.example.Words.containsIn(Words.java:20) > null

staticfree
com.example.A.main(A.java:6) > static 
com.example.Words(Words.java:7) > static 

allfinal
com.example.A.main(A.java:6) > String[] args
com.example.A(A.java:5) > A
com.example.Words(Words.java:9) > words
com.example.Words.containsIn(Words.java:22) > StringTokenizer tokenizer = new StringTokenizer(text, DELIM)
com.example.Words.containsIn(Words.java:24) > String nextWord = tokenizer.nextToken()
com.example.Words(Words.java:15) > Collection<String> words
com.example.Words.containsIn(Words.java:19) > String text
com.example.Words(Words.java:6) > Words

allpublic
com.example.Words.containsIn(Words.java:19)

nomultiplereturn
com.example.Words.containsIn(Words.java:19)
```

## Get started

### Gradle
Add the plugin to the root `build.gradle`
```groovy
plugins {
    id 'com.iwillfailyou' version 'x.y.z'
}

// then configure it, if you need:
iwillfailyou {
    offline = true // default false
    nullfree {
        disabled = true // default false
        threshold = 3 // default 0
        skipComparisons = true // default false
    }
    staticfree {
        disabled = true // default false
        threshold = 2 // default 0
    }
    allfinal {
        disabled = true // default false
        threshold = 1 // default 0
        skipInterfaceMethodParams = false // default true
        skipLambdaParams = true // default false
        skipCatchParams = true // default false
    }
    allpublic {
        disabled = true // default false
        threshold = 4 // default 0
    } 
    setterfree {
        disabled = true // default false
        threshold = 5 // default 0
    }
    nomultiplereturn {
        disabled = true // default false
        threshold = 6 // default 0
    }
}
```
Where `x.y.z` is actual version from gradle plugins 
[![Gradle plugin version](https://img.shields.io/maven-metadata/v/https/plugins.gradle.org/m2/com/iwillfailyou/com.iwillfailyou.gradle.plugin/maven-metadata.xml.svg?label=gradle-plugin)](https://plugins.gradle.org/plugin/com.iwillfailyou)

Invoke it:
```bash
./gradlew iwillfailyou
```

### Maven
Add the plugin to the `pom.xml`
```xml
<plugin>
  <groupId>com.iwillfailyou</groupId>
  <artifactId>iwillfailyou-maven-plugin</artifactId>
  <version>x.y.z</version>

  <!-- then configure it, if you need: -->
  <configuration>
    <offline>true</offline><!-- default false -->
    <nullfree>
      <disabled>true</disabled><!-- default false -->
      <threshold>3</threshold><!-- default 0 -->
      <skipComparisons>true</skipComparisons><!-- default false -->
    </nullfree>
    <staticfree>
      <disabled>true</disabled><!-- default false -->
      <threshold>2</threshold><!-- default 0 -->
    </staticfree>
    <allfinal>
      <disabled>true</disabled><!-- default false -->
      <threshold>1</threshold><!-- default 0 -->
      <skipInterfaceMethodParams>false</skipInterfaceMethodParams><!-- default true -->
      <skipLambdaParams>true</skipLambdaParams><!-- default false -->
      <skipCatchParams>true</skipCatchParams><!-- default false -->
    </allfinal>
    <allpublic>
      <disabled>true</disabled><!-- default false -->
      <threshold>4</threshold><!-- default 0 -->
    </allpublic>
    <setterfree>
      <disabled>true</disabled><!-- default false -->
      <threshold>5</threshold><!-- default 0 -->
    </setterfree>
    <nomultiplereturn>
      <disabled>true</disabled><!-- default false -->
      <threshold>6</threshold><!-- default 0 -->
    </nomultiplereturn>
  </configuration>
</plugin>
```

Invoke it:
```bash
mvn iwillfailyou:iwillfailyou
```

Where `x.y.z` is actual version from maven central 
[![Maven plugin version](https://img.shields.io/maven-central/v/com.iwillfailyou/iwillfailyou-maven-plugin.svg?label=maven-plugin)](https://maven-badges.herokuapp.com/maven-central/com.iwillfailyou/iwillfailyou-maven-plugin)

## Inspections

1. ✅ [**NullFree**](https://github.com/iwillfailyou/service#NullFree) 
([Why `null` is bad?](https://www.yegor256.com/2014/05/13/why-null-is-bad.html))
elegant code must not use the `null` keywords

2. ✅ [**StaticFree**](https://github.com/iwillfailyou/service#StaticFree) 
([Why `static` is bad?](https://www.yegor256.com/2014/05/05/oop-alternative-to-utility-classes.html))
elegant code must not use the `static` keywords

3. ✅ [**AllFinal**](https://github.com/iwillfailyou/service#AllFinal)
every class, every field, every argument, every local variable must be `final`
in the elegant code

4. 🔄 **instanceoffree** `[in progress]`
elegant code must not use the `instanceof` keywords

5. 🔄 **inheritancefree** `[in progress]`
elegant code must not use the class inheritance (when one class `extends`
another one), only composition and type inheritance has been allowed
 
6. 🔄 **enumfree** `[in progress]`
elegant code must not use the `enum`s

7. 🔄 **switchfree** `[in progress]`
elegant code must not use the `switch` blocks/expressions

8. ✅ [**NoMultipleReturn**](https://github.com/iwillfailyou/service#NoMultipleReturn)
elegant code must contain only one (or no one) return in an any method

9. 🔄 **getterfree** `[in progress]`
elegant code must not contain any getters

10. ✅ [**SetterFree**](https://github.com/iwillfailyou/service#SetterFree)
elegant code must not contain any setters

11. ✅ [**AllPublic**](https://github.com/iwillfailyou/service#AllPublic)
elegant code must use only `public` methods 

12. 🔄 **nopublicmethodnotoverrides** `[in progress]`
every public method in the elegant code must be overrided from an interface

### NullFree
Plugin configuration options:
- `skipComparisons` allows use `null` in boolean expressions:
```java
if (some == null) {
    ...
}
```

Can be suppressed in the code by `@SuppressWarnings("nullfree")`


### StaticFree
Can be suppressed in the code by `@SuppressWarnings("staticfree")`

### AllFinal
Plugin configuration options:
- `skipInterfaceMethodParams` allows restricting or not interface method
parameter `final`s, by default there is no needed to set `final` for such
places 
- `skipLambdaParams` allows skip `final` in lambda parameters, by default
lambda parameter needs to be `final` 
- `skipCatchParams` allows skip `final` in `catch` parameters, by default
`catch` parameter needs to be `final` 

Can be suppressed in the code by `@SuppressWarnings("allfinal")`

### AllPublic
Can be suppressed in the code by `@SuppressWarnings("allpublic")`

### SetterFree
Can be suppressed in the code by `@SuppressWarnings("setterfree")`

### NoMultipleReturn
Can be suppressed in the code by `@SuppressWarnings("nomultiplereturn")`


## Badges
If you use iwillfailyou plugin without `offline = true` settings, then you
can attach the inspection badges to your readme file:
- `![nullfree status](https://iwillfailyou.com/nullfree/<user>/<repo>)`
- `![staticfree status](https://iwillfailyou.com/staticfree/<user>/<repo>)`
- `![allfinal status](https://iwillfailyou.com/allfinal/<user>/<repo>)`
- `![allpublic status](https://iwillfailyou.com/allpublic/<user>/<repo>)`
- `![setterfree status](https://iwillfailyou.com/setterfree/<user>/<repo>)`
- `![nomultiplereturn status](https://iwillfailyou.com/nomultiplereturn/<user>/<repo>)`


## Inspection threshold
Any inspection can be configured with `threshold`:

In `gradle`
```groovy
iwillfailyou {
    ...
    staticfree {
        threshold = 19
    }
    ...
}
```

In `maven`
```xml
<configuration>
  <staticfree>
    <threshold>19</threshold>
  </staticfree>
</configuration>
```


## Disabling inspections
Any inspection can be disabled by `disabled` settings:

In `gradle`
```groovy
iwillfailyou {
    ...
    staticfree {
        disabled = true
    }
    ...
}
```

In `maven`
```xml
<configuration>
  <staticfree>
    <disabled>true</disabled>
  </staticfree>
</configuration>
```

All inspections are enabled by default.

## Excluding files from inspects 
There is global `exclude` settings, which can be used for defining exclude
patterns: 
In `gradle`
```groovy
iwillfailyou {
    exclude = ["glob:**/test/**/*Test.java"]
}
```

In `maven`
```xml
<configuration>
  <exclude>glob:**/test/**/*Test.java</exclude>
</configuration>
```

There is inspection local exclude configuration option, which has higher
priority than global exclude configuration:

In `gradle`
```groovy
nullfree {
    exclude = ["glob:**/*SomeBadFile.java"]
}
```

In `maven`
```xml
<nullfree>
  <exclude>glob:**/*SomeBadFile.java</exclude>
</nullfree>
```

## License
[MIT](https://github.com/iwillfailyou/service/blob/master/LICENSE)