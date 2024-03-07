package backEnd;

import java.util.HashMap;

/**
 * An enumeration representing the programming languages that can be used for a project.
 */
public enum Language {
    NONE,
    CSHARP,
    CPP,
    C,
    JAVA,
    JAVASCRIPT,
    PYTHON,
    HTML,
    CSS;

    /**
     * Returns a map of language names to their corresponding Language enum values.
     * @return a HashMap containing the mapping of language names to their enum values.
     */
    public static HashMap<String, Language> getLanguageMap() {
        HashMap<String, Language> langaugeMap = new HashMap<String, Language>();
        langaugeMap.put("None", Language.NONE);
        langaugeMap.put("C#", Language.CSHARP);
        langaugeMap.put("C++", Language.CPP);
        langaugeMap.put("C", Language.C);
        langaugeMap.put("Java", Language.JAVA);
        langaugeMap.put("JavaScript", Language.JAVASCRIPT);
        langaugeMap.put("Python", Language.PYTHON);
        langaugeMap.put("HTML", Language.HTML);
        langaugeMap.put("CSS", Language.CSS);

        return langaugeMap;
    }
}
