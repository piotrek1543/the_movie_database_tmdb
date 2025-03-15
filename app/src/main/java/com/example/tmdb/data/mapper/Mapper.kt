@file:Suppress("unused")

package com.example.tmdb.data.mapper
/**
 * A generic interface for mapping data between different representations.
 * This is commonly used to convert data from a network or database layer (Entity/Remote)
 * to a domain or UI layer (Domain/UI Model) and vice-versa.
 *
 * @param E The type of the entity or source data (e.g., database model).
 * @param D The type of the domain or destination data (e.g., UI model).
 */
interface Mapper<E, D> {

    /**
     * Maps an object of type [E] to an object of type [D].
     *
     * @param entity The object to be mapped.
     * @return The mapped object of type [D].
     */
    fun fromEntity(entity: E): D

    /**
     * Maps an object of type [D] to an object of type [E].
     *
     * @param domain The object to be mapped.
     * @return The mapped object of type [E].
     */
    fun toEntity(domain: D): E
}

/**
 * A more specific mapper interface for cases where data comes from a remote source.
 *
 * @param R The type of the remote data.
 * @param D The type of the domain data.
 */
interface RemoteMapper<R, D> {
    /**
     * Maps an object of type [R] (Remote) to an object of type [D] (Domain).
     *
     * @param remote The remote object to be mapped.
     * @return The mapped object of type [D].
     */
    fun fromRemote(remote: R): D
}

/**
 * A more specific mapper interface for cases where data comes from a local database.
 *
 * @param L The type of the local data.
 * @param D The type of the domain data.
 */
interface LocalMapper<L, D> {
    /**
     * Maps an object of type [L] (Local) to an object of type [D] (Domain).
     *
     * @param local The local object to be mapped.
     * @return The mapped object of type [D].
     */
    fun fromLocal(local: L): D

    /**
     * Maps an object of type [D] to an object of type [L].
     *
     * @param domain The domain object to be mapped.
     * @return The mapped object of type [L].
     */
    fun toLocal(domain:D):L
}
