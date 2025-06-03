package vn.vplay.vlive.myapplication.data.remote.response

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}

data class Resource<out T>(
    val status: Status,
    val message: List<String>? = null,
    val errorCode: Int? = null,
    val statusCode: Int? = null,
    val data: T? = null,
) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data = data)
        }

        fun <T> error(
            statusCode: Int?,
            errorCode: Int? = null,
            msg: List<String>? = null
        ): Resource<T> {
            return Resource(Status.ERROR, msg, errorCode, statusCode)
        }
        fun <T> error(
            msg: List<String>? = null
        ): Resource<T> {
            return Resource(Status.ERROR, msg, -1, -1)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data = data)
        }
    }
}