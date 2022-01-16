package com.modulariz.h2ohydroponics2operate.sensorstate;

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface SensorStateRepository : JpaRepository<SensorStateEntity, UUID> {
    @Query(value = "SELECT * FROM sensor_state state ORDER BY created_at DESC LIMIT :limit OFFSET :offset" , nativeQuery = true)
    fun findLimited(@Param("limit") limit: Int,@Param("offset") offset: Int): MutableList<SensorStateEntity>
}


@Repository
interface SensorStateCacheRepository: CrudRepository<SensorStateCacheEntity, String>