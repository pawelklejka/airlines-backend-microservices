import org.gradle.process.ExecOperations
import org.gradle.workers.WorkAction
import org.gradle.workers.WorkParameters
import javax.inject.Inject

/**
 * Parallel worker for executing `dockerPush` on each module.
 */
abstract class DockerPushWorker @Inject constructor(
    private val execOperations: ExecOperations
) : WorkAction<DockerPushWorker.Parameters> {

    interface Parameters : WorkParameters {
        val moduleName: org.gradle.api.provider.Property<String>
    }

    override fun execute() {
        val module: String = parameters.moduleName.get()
        println("Running Docker push for module: $module in parallel")
        execOperations.exec {
            commandLine("sh", "-c", "./gradlew $module:dockerPush")
        }
    }
}
