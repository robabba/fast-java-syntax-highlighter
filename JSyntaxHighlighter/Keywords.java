package JSyntaxHighlighter;

public class Keywords {

	public static String[] getCKeywords(){
		return new String[] {"auto", "break", "case","const", "continue", "default", "do", 
							"double", "else","enum", "extern","float", "goto","if", "int", 
							"long", "regster", "return", "short", "signed", "sizeof", "static",
							"struct", "switch", "typedef", "union", "unsigned", "void", "volatile", "while"};
	}

	
	public static String[] getCPPKeywords(){
		return new String[] {"alginas", "alignof", "and", "and_eq", "asm", "auto", "bitand", "bitor", "bool",
							"break", "case", "catch", "char", "char16_t", "char32_t", "class", "compl", "concept",
							"const", "constexpr", "const_cast", "continue", "decltype", "default", "delete", "do","double",
							"dynamic_cast", "else", "enum", "explicit", "export", "extern", "false", "float", "for", "friend",
							"goto", "if", "inline", "int", "long", "mutable", "namespace", "new", "noexcept", "not", "not_eq", 
							"nullptr", "operator", "or", "or_eq", "private", "protected", "public", "register", "reinterpret_cast",
							"requires", "return", "short", "signed", "sizeof", "static", "static_assert", "static_cast", "struct",
							"switch", "template", "this", "thread_local", "throw", "true", "try", "typedef", "typeid", "typename", "union",
							"unsigned", "usign","virtual", "void", "volatile", "wchar_t", "while", "xor", "xor_eq"};
	}
	
	public static String[] getCSKeywords(){
		return new String[] {"abstract", "as", "base", "bool", "break", "byte", "case", "catch", "char", "checked", "class" ,"const",
							"continue", "decimal", "default", "delegate", "do", "double", "else", "enum", "event", "explicit", "extern",
							"false", "finally", "fixed", "float", "for", "foreach", "goto", "if", "implicit", "in", "int", "interface",
							"internal", "is", "lock", "long", "namespace", "new", "null", "object", "operator", "out", "override", "params",
							"private", "protected", "public", "readonly", "ref", "return", "sbyte", "sealed", "short", "sizeof", "stackalloc",
							"static", "string", "struct", "switch", "this", "throw", "true", "try", "typeof", "uint", "ulong", "unchecked",
							"unsafe", "ushort", "using", "virtual", "void", "volatile", "while" };
	}
	
	public static String[] getJavaKeywords(){
		return new String[]{ "abstract", "assert", "boolean", "break", "byte", "case", "catch" ,
							"char", "class", "continue", "default", "do", "double", "else", "enum", "extends",
							"final", "finally", "float", "for", "goto", "if", "implements", "import", "instanceof", 
							"int", "long", "native", "new", "package", "private", "protected", "public", "return", 
							"short", "static", "strictfp", "super", "switch", "synchronized", "this", "throw", "throws", 
							"transient", "try", "void", "volatile", "while", "false", "null", "true"};
	}
	
	public static String[] getVBKeywords(){
		return new String[]{"AddHandler", "AddressOf", "Alias", "And", "AndAlso", "As", "Boolean", "ByRef", "Byte", "ByVal",
							"Call", "Case", "Catch", "CBool", "CByte", "CChar", "CDate", "CDbl", "CDec", "Char", "CInt", "CLng",
							"CObj", "Const", "Continue", "CSByte", "CShort", "CSng", "CStr", "CType", "CUInt", "CULng", "CUShort",
							"Date", "Decimal", "Declare", "Default", "Delegate", "Dim", "DirectCast", "Do", "Double", "Each", "Else",
							"ElseIf", "End", "EndIf", "Enum", "Erase", "Error", "Event", "Exit", "False", "Finally", "For", "Friend",
							"Function", "Get", "GetType", "GetXMLNamespace", "Global", "GoSub", "GoTo", "Handles", "If", "Implements", 
							"Imports", "In", "Inherits", "Integer", "Interface", "Is", "IsNot", "Let", "Lib", "Like", "Long", "Loop",
							"Me", "Mod", "Module", "MustInherit", "MustOverride", "MyBase", "MyClass", "Namespace", "Narrowing", "New",
							"Next", "Not", "Nothing", "NotInheritable", "NotOverridable", "Object", "Of", "On", "Operator", "Option",
							"Optional", "Or", "OrElse", "Out", "Overloads", "Overridable", "Overrides", "ParamArray", "Partial", "Private",
							"Property", "Protected", "Public", "RaiseEvent", "ReadOnly", "Resume", "Return", "SByte", "Select", "Set", "Shadows",
							"Shared", "Short", "Single", "Static", "Step", "Stop", "String", "Structure", "Sub", "SyncLock", "Then", "Try",
							"TryCast", "TypeOf", "UInteger", "ULong", "UShort", "Using", "Variant", "Wend", "When", "While", "Widening", "With",
							"WithEvents", "WriteOnly", "Xor"};
	}
}
