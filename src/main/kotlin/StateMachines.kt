//Enumerations as STATE MACHINES
//A state machine is an exclusive list of possible states for a given sys

import java.util.*
import kotlin.concurrent.timerTask
import kotlin.system.exitProcess

enum class DownloadState {

    //Idle,
    /*Adv. of nullability with enum classes->
  They allow us to represent state where you haven't received information without having to explicitly create an Idle/Unknown state
  */
    Starting,
    InProgress,
    Error,
    Success

    //ABOVE are the: Exclusive possible states.
}

//This class figures how to adjust the state machine based on whats happening under the hood
class Downloader {

    private val maxData = 100
    var downloadState: DownloadState? = null //Nullability with enum classes
    private var fakeData: MutableList<Int> = mutableListOf()

    fun downloadData(fromUrl: String, progress: (state: DownloadState?) -> Unit, completion: (error: Error?, data: List<Int>?) -> Unit) {
        println("\"Downloading\" from URL: ${fromUrl}")
        postProgress(progress)
        downloadState = DownloadState.Starting
        keepAddingData(completion)
    }

    private fun keepAddingData(completion: (error: Error?, data: List<Int>?) -> Unit) {
        addMoreData { error ->
            when (downloadState) {
                DownloadState.Error -> completion(error, null)
                DownloadState.Success -> completion(null, fakeData.toList())
                else -> keepAddingData(completion)
            }
        }
    }

    private fun postProgress(progress: (state: DownloadState?) -> Unit) {
        progress(downloadState)

        when (downloadState) {
            DownloadState.Error -> exitProcess(1)
            DownloadState.Success -> exitProcess(0)
            else -> Timer().schedule(timerTask { postProgress(progress) }, 200)
        }
    }

    private fun addMoreData(completion: (error: Error?) -> Unit) {
        Timer().schedule(timerTask {
            val error = randomlyThrowError()
            if (error != null) {
                downloadState = DownloadState.Error
                completion(error)
            } else {
                downloadState = DownloadState.InProgress
                for (i in 0..20) {
                    fakeData.add(i)
                    if (fakeData.size == maxData) {
                        downloadState = DownloadState.Success
                        break
                    }
                }

                completion(null)
            }
        }, 500)
    }

    private fun randomlyThrowError(): Error? {
        val randomNumber: Int = (0..10).random()
        if (randomNumber == 8) {
            return Error("Your download was eaten by a shark.")
        } else {
            return null
        }
    }

}

// Via https://stackoverflow.com/a/45687695/681493
fun ClosedRange<Int>.random() =
    Random().nextInt(endInclusive - start) + start


//OUR FOCUS:State which our download is in.
fun main(arg:Array<String>){
    Downloader().downloadData("foo.com/bar",
        progress ={downloadState->
            when(downloadState){
                //DownloadState.Idle-> println("Download has not yet started")
                null-> println("No download state yet")//Adv. of nullability with enum classes->
                //They allow us to represent state where you haven't received information without having to explicitly create an Idle/Unknown state
                DownloadState.Starting-> println("Starting download")
                DownloadState.InProgress-> println("Downloading data...")
                DownloadState.Error-> println("Error occurred, Download terminated")
                DownloadState.Success-> println("Download completed successfully")
            }
        },
        completion={error,list->
            error?.let{println("Got error: ${error.message}")}
            list?.let{println("Got list with ${list.size} items")}
        })
}

