package modules

enum class ProjectModules(val simpleName: String) {
    CommonNavigation("commonNavigation"),
    Common("common"),
    CommonResources("commonResources"),
    CommonData("commonData"),
    CommonDomain("commonDomain");

    val projectName: String
        get() = ":$simpleName"

    val packageName: String
        get() = "com.netguru.$simpleName"
}
