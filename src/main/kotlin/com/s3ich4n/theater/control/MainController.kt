package com.s3ich4n.theater.control

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class MainController {

    @RequestMapping("/")
    fun homePage() : ModelAndView =
        ModelAndView("seatBooking", "bean", CheckAvailabilityBackingBean())

}
