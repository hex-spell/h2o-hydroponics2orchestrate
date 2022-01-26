package com.modulariz.h2ohydroponics2operate.sensorstate

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@CrossOrigin(origins = ["*"])
@RequestMapping("/sensors")
class SensorStateController {
    @Autowired
    lateinit var sensorStateRepository: SensorStateRepository

    @Autowired
    lateinit var sensorStateCacheRepository: SensorStateCacheRepository

    @GetMapping
    fun getCurrentState(): Optional<SensorStateCacheEntity> {
        return sensorStateCacheRepository.findById("SensorState")
    }

    @GetMapping("/hourly")
    fun getHourlyStates(): MutableList<SensorStateEntity> {
        return sensorStateRepository.findLimited(10,0)
    }
}
