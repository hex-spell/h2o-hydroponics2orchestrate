package com.modulariz.h2ohydroponics2operate.sensorstate


import org.springframework.data.redis.core.RedisHash
import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "sensor_state")
open class SensorStateEntity {
    @Column(name = "ambient_temperature", nullable = false)
    open var ambientTemperature: Double? = null

    @Column(name = "fire_alarm", nullable = false)
    open var fireAlarm: Boolean? = false

    @Id
    @Column(name = "created_at")
    open var createdAt: String? = System.currentTimeMillis().toString()
}

@RedisHash("SensorState")
data class SensorStateCacheEntity(
    var ambientTemperature: Double,
    var fireAlarm: Boolean,
    var createdAt: String = System.currentTimeMillis().toString(),
    @Id var id: String? = "SensorState"
)

data class SensorStateDto(val ambientTemperature: Double, val fireAlarm: Boolean) : Serializable {
    constructor(cachedSensorState: SensorStateCacheEntity) : this(
        cachedSensorState.ambientTemperature,
        cachedSensorState.fireAlarm
    )


    fun toSQLEntity(): SensorStateEntity {
        val sensorStateEntity = SensorStateEntity()
        sensorStateEntity.ambientTemperature = ambientTemperature
        sensorStateEntity.fireAlarm = fireAlarm
        return sensorStateEntity
    }

    fun toRedisEntity() = SensorStateCacheEntity(ambientTemperature = ambientTemperature, fireAlarm = fireAlarm)
}