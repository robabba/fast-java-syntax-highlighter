Fast Java Syntax Highlighter - Alpha Build
================================
:exclamation: NOTE: This is a **alpha build!** Bugs are to expected

JSyntaxHighlighter is a highly configurable Syntax Highlighter for Java.


How to use
----------
To use JSyntaxHighlighter, simply download and add the library JSyntaxHighlighter.jar to your project and add the following to your respective JFrame file:

```java
JSyntaxHighlighter synHighlighter = new JSyntaxHighlighter();
```

####Options available for new JSyntaxHighlighter()

#####Setting the Language
```java
...JSyntaxHighlighter(Language.Java,...);
```
######Languages Available:
+ C
+ C++
+ C#
+ Java
+ Visual Basic

#####Setting the Color Scheme (Language needs to be specified)
```java
...JSyntaxHighlighter(Language.Java, Themes.Obsidian);
```
######Color Schemes Available:
+ Dusk
+ Monokai Extended (Sublime Text 3 ver.)
+ Obsidian
+ Tomorrow
+ Tomrrow Night
+ Solarized Dark
+ Vibrant Ink
+ Xcode

User Defined Languages
----------------------
JSyntaxHighlighter fully supports custom language files. To create a language file, create a new .lang file and follow the example given in the documented
"lua.lang" file which can be found in the root directory.

To then use your custom Language file, simply use this in the constructor:
```java
...JSyntaxHighlighter(new File("path-to-file/file.lang"), Themes.VibrantInk);
```

Or if you have already created a JSyntaxHighlighter object and want to change the language later on, call:

```java
`synHighlighter.changeLanguage(new File("path-to-file/file.lang"));
```
######Rules that can be overridden within the .lang file
+ Single line Comments (s-comments)
+ Multi-line Comments  (m-comments)


User Defined Themes
-------------------
JSyntaxHighlighter fully supports user defined themes. To create a new theme, create a .color file and follow the example given in the "inkpot.color" file which can be found in the root directory.

To then use your new Theme, simply use this in the constructor:

```java
...JSyntaxHighlighter(Language.Java, new File("path-to-file/file.color"));
```


License
-------
JSyntaxHighlighter is licensed under the [MIT License](http://opensource.org/licenses/MIT) and
is created by [Robert Abba](http://www.robabba.co.uk).
