package trzd.dev.cif.records;

public record Textify() {
    public enum zdObject {tickets_, users_, thread_}
    public static String prefix = "cif_";
    public static String integrationName = "cif-java-anychat";
    public static String integrationAuthor = "Trees Solutions";
    public static String integrationPushClientId = "zd_trees_integration";
}
