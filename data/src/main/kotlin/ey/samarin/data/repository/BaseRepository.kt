package ey.samarin.data.repository


/**
 * here we can restrict the type hierarchy for repositories
 * (if you need common logic for all repositories),
 * for example by setting them a common interface for asynchronous tasks.
 */
interface BaseRepository<out LOCAL_TYPE : Any, out REMOTE_TYPE : Any> :
    BaseLocalRepository<LOCAL_TYPE>, BaseRemoteRepository<REMOTE_TYPE>

interface BaseRemoteRepository<out REMOTE_TYPE : Any> {
    val remoteDataSource: REMOTE_TYPE
}

interface BaseLocalRepository<out LOCAL_TYPE : Any> {
    val localDataSource: LOCAL_TYPE
}