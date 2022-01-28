package com.modulariz.h2ohydroponics2operate.sensorstate


import org.springframework.data.redis.core.RedisHash
import java.io.Serializable
import javax.persistence.*


@Entity
@Table(name = "sensor_state")
open class SensorStateEntity {
    @Column(name = "ambient_temperature", nullable = false)
    open var ambientTemperature: Double? = null

    @Column(name = "ambient_humidity", nullable = false)
    open var ambientHumidity: Double? = null

    @Column(name = "water_temperature", nullable = false)
    open var waterTemperature: Double? = null

    @Column(name = "water_tds", nullable = false)
    open var waterTDS: Double? = null

    @Column(name = "water_ph", nullable = false)
    open var waterPh: Double? = null

    @Column(name = "fire_alarm", nullable = false)
    open var fireAlarm: Boolean? = false

    @Id
    @Column(name = "created_at")
    open var createdAt: String? = System.currentTimeMillis().toString()
}

@RedisHash("SensorState")
data class SensorStateCacheEntity(
    var ambientTemperature: Double,
    var ambientHumidity: Double,
    var waterTemperature: Double,
    var waterTDS: Double,
    var waterPh: Double,
    var fireAlarm: Boolean,
    var createdAt: String = System.currentTimeMillis().toString(),
    @Id var id: String? = "SensorState"
)

data class SensorStateDto(
    val ambientTemperature: Double,
    val ambientHumidity: Double,
    val waterTemperature: Double,
    val waterTDS: Double,
    val waterPh: Double,
    val fireAlarm: Boolean
) : Serializable {
    constructor(cachedSensorState: SensorStateCacheEntity) : this(
        cachedSensorState.ambientTemperature,
        cachedSensorState.ambientHumidity,
        cachedSensorState.waterTemperature,
        cachedSensorState.waterTDS,
        cachedSensorState.waterPh,
        cachedSensorState.fireAlarm
    )

    fun toSQLEntity(): SensorStateEntity {
        val sensorStateEntity = SensorStateEntity()
        sensorStateEntity.ambientTemperature = ambientTemperature
        sensorStateEntity.ambientHumidity = ambientHumidity
        sensorStateEntity.waterTemperature = waterTemperature
        sensorStateEntity.waterTDS = waterTDS
        sensorStateEntity.waterPh = waterPh
        sensorStateEntity.fireAlarm = fireAlarm
        return sensorStateEntity
    }

    fun toRedisEntity() = SensorStateCacheEntity(
        ambientTemperature = ambientTemperature,
        ambientHumidity = ambientHumidity,
        waterTemperature = waterTemperature,
        waterTDS = waterTDS,
        waterPh = waterPh,
        fireAlarm = fireAlarm
    )
}