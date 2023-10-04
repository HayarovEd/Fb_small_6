package rock.paper.scissors.presentation

sealed class ApplicationStatus {
    object Loading: ApplicationStatus()
    class Succsess(val url:String): ApplicationStatus()

    object Mock: ApplicationStatus()
    class Error (val error:String): ApplicationStatus()
}
