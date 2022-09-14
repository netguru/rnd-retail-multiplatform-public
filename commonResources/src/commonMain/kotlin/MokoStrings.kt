import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.format

object MokoStrings {

    fun getFormattedString(stringResource: StringResource, input: String): StringDesc {
        return stringResource.format(input)
    }
}
