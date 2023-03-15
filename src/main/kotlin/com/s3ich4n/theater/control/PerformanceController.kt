package com.s3ich4n.theater.control

import com.s3ich4n.theater.data.PerformanceRepository
import com.s3ich4n.theater.domain.Performance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.ModelAndView


@Controller
@RequestMapping("/performances")
class PerformanceController() {

    @Autowired
    lateinit var performanceRepository: PerformanceRepository

    @RequestMapping("/")
    fun performancesHomePage() =
        ModelAndView("performances/home","performances", performanceRepository.findAll())

    @RequestMapping("/add")
    fun addPerformance() =
        ModelAndView("performances/add","performance",Performance(0,""))

    @RequestMapping(value= ["/save"], method = [RequestMethod.POST])
    fun savePerformance(performance: Performance) : String {
        performanceRepository.save(performance)
        return "redirect:/performances/"
    }
}
