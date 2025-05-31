import org.gradle.api.DefaultTask
import org.gradle.api.provider.ListProperty
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import org.gradle.workers.WorkQueue
import org.gradle.workers.WorkerExecutor
import javax.inject.Inject

abstract class DockerPushAllTask @Inject constructor(
    private val workerExecutor: WorkerExecutor
) : DefaultTask() {

    @get:Input
    abstract val modules: ListProperty<String>

    init {
        group = "docker"
        description = "Push Docker images for all bootable modules"
    }

    @TaskAction
    fun pushImages() {
        val workQueue: WorkQueue = workerExecutor.noIsolation()

        modules.get().forEach { module ->
            println("🚀 Queuing Docker push task for module: $module")

            // ✅ Fix: Use correct lambda syntax
            workQueue.submit(DockerPushWorker::class.java) {
                moduleName.set(module) // ✅ Corrected lambda syntax
            }
        }
    }
}