# Iwillfailyou

[![Elegant Objects Respected Here](https://www.elegantobjects.org/badge.svg)](https://www.elegantobjects.org/)

![nullfree status](https://iwillfailyou.com/nullfree/iwillfailyou/service)
![staticfree status](https://iwillfailyou.com/staticfree/iwillfailyou/service)

[![Build Status](https://travis-ci.com/iwillfailyou/service.svg?branch=master)](https://travis-ci.com/iwillfailyou/service)
[![codecov](https://codecov.io/gh/iwillfailyou/service/branch/master/graph/badge.svg)](https://codecov.io/gh/iwillfailyou/service)

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://github.com/iwillfailyou/service/blob/master/LICENSE)

[![logo](https://raw.githubusercontent.com/iwillfailyou/service/master/assets/logo.png)](https://iwillfailyou.com)

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

    public boolean containsIn(String text) {
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
com.example.Words(Words.java:12) > this.words = null
com.example.Words.containsIn(Words.java:20) > words == null

staticfree
com.example.Words(Words.java:7) > public static final String DELIM = " ,.";
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
        skipComparisions = true // default false
        threshold = 3 // default 0
    }
    staticfree {
        threshold = 3 // default 0
    }
}
```
Where `x.y.z` is actual version from gradle plugins 
[![Gradle plugin version](https://img.shields.io/maven-metadata/v/https/plugins.gradle.org/m2/com/iwillfailyou/com.iwillfailyou.gradle.plugin/maven-metadata.xml.svg?label=gradle-plugin)](https://plugins.gradle.org/plugin/com.iwillfailyou)

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
      <skipComparisions>true</skipComparisions><!-- default false -->
      <threshold>3</threshold><!-- default 0 -->
    </nullfree>
    <staticfree>
      <threshold>3</threshold><!-- default 0 -->
    </staticfree>
  </configuration>
</plugin>
```

Where `x.y.z` is actual version from maven central 
[![Maven plugin version](https://img.shields.io/maven-central/v/com.iwillfailyou/iwillfailyou-maven-plugin.svg?label=maven-plugin)](https://maven-badges.herokuapp.com/maven-central/com.iwillfailyou/iwillfailyou-maven-plugin)

## Inspections

1. ✅ [**nullfree**](https://github.com/iwillfailyou/service#nullfree) 
([Why `null` is bad?](https://www.yegor256.com/2014/05/13/why-null-is-bad.html))
elegant code must not use the `null` keywords

2. ✅ [**staticfree**](https://github.com/iwillfailyou/service#staticfree) 
([Why `static` is bad?](https://www.yegor256.com/2014/05/05/oop-alternative-to-utility-classes.html))
elegant code must not use the `static` keywords

3. 🔄 **allfinal** `[in progress]`
every class, every field, every argument, every local variable must be `final`
in the elegant code

4. 🔄 **instanceoffree** `[in progress]`
elegant code must not use the `instanceof` keywords

5. 🔄 **inheritancefree** `[in progress]`
elegant code must not use the class inheritance (when one class `extends`
another one), only composition and type inheritance has been allowed
 
6. 🔄 **enumfree** `[in progress]`
elegant code must not use the `enum`s

7. 🔄 **nomultiplereturn** `[in progress]`
elegant code must contain only one (or no one) return in an any method

8. 🔄 **getterfree** `[in progress]`
elegant code must not contain any getters

9. 🔄 **setterfree** `[in progress]`
elegant code must not contain any getters

10. 🔄 **privatefree** `[in progress]`
elegant code must not use the `private` methods 

11. 🔄 **nopublicmethodnotoverrides** `[in progress]`
every public method in the elegant code must be overrided from an interface

### Nullfree
Plugin configuration options:
- `skipComparisions` allows use `null` in boolean expressions:
```java
if (some == null) {
    ...
}
```
- `threshold` makes it possible to set the maximum allowed `null`s in the
codebase

Can be suppressed in the code by `@SuppressWarnings("nullfree")`


### Staticfree
Plugin configuration options:
- `threshold` makes it possible to set the maximum allowed `statics`s in the 
codebase

Can be suppressed in the code by `@SuppressWarnings("nullfree")`

## Badges
If you use iwillfailyou plugin without `offline = true` settings, then you
can attach the inspection badges to your readme file:
- `![nullfree status](https://iwillfailyou.com/nullfree/<user>/<repo>)`
- `![staticfree status](https://iwillfailyou.com/staticfree/<user>/<repo>)`


## License
[MIT](https://github.com/iwillfailyou/service/blob/master/LICENSE)