package com.modulariz.h2ohydroponics2operate.sensorstate


import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class SensorStateSchedules {
    private val logger = LoggerFactory.getLogger(javaClass)

    @Autowired
    lateinit var sensorStateRepository: SensorStateRepository

    @Autowired
    lateinit var sensorStateCacheRepository: SensorStateCacheRepository

    @Scheduled(cron = "1/3 * * * * ?")
    fun saveHourlyState() {
        val lastSensorStateData = sensorStateCacheRepository.findById("SensorState")
        val previousSensorStateData = sensorStateCacheRepository.findById("PreviousSensorState")
        if (lastSensorStateData.isPresent) {
            val lastSensorState = lastSensorStateData.get()
            if (previousSensorStateData.isPresent && previousSensorStateData.get().createdAt == lastSensorState.createdAt) {
                logger.error("No new sensor data, check if microcontroller is connected")
            }
            else {
                logger.info("Saving sensor state to database")
                sensorStateRepository.save(SensorStateDto(lastSensorState).toSQLEntity())
                lastSensorState.id = "PreviousSensorState"
                sensorStateCacheRepository.save(lastSensorState)
            }
        }
        else {
            logger.info("No sensor data to save")
        }
    }
}
