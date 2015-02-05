JSyntaxHighlighter
==================

JSyntaxHighlighter is a highly configurable Syntax Highlighter for Java.


How to use
----------
To use JSyntaxHighlighter, simply download and add the library JSyntaxHighlighter.jar to your project and add the following to your respective JFrame file:

`JSyntaxHighlighter synHighlighter = new JSyntaxHighlighter();`

####Options available for new JSyntaxHighlighter()

#####Setting the Language
`...JSyntaxHighlighter(Language.Java);`

######Languages Available:
+ C
+ C++
+ C#
+ Java
+ Visual Basic

#####Setting the Color Scheme (Language needs to be specified)
`...JSyntaxHighlighter(Language.Java, Themes.Obsidian);`

######Color Schemes Available:
+ Dusk
+ Monokai Extended (Sublime Text 3 ver.)
+ Obsidian
+ Tomorrow
+ Tomrrow Night
+ Solarized Dark
+ Vibrant Ink
+ Xcode

Custom Language Files
---------------------
JSyntaxHighlighter fully supports custom language files. To create a language file, create a new .lang file and follow the example given in the documented
"lua.lang" file which can be found in the root directory.

To then use your custom Language file, simply use this in the constructor:

`...JSyntaxHighlighter(new File("path-to-file/file.lang", Themes.VibrantInk);`

Or if you have already created a JSyntaxHighlighter object and want to change the language later on, call:

`mySyntaxHighlighter.changeLanguage(new File("path-to-file/file.lang"));`


License
-------
JSyntaxHighlighter is licensed under the [MIT License](http://opensource.org/licenses/MIT) and
is created by [Robert Abba](http://www.robabba.co.uk).
