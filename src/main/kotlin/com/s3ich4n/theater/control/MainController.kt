package com.s3ich4n.theater.control

import com.s3ich4n.theater.data.SeatRepository
import com.s3ich4n.theater.services.BookingService
import com.s3ich4n.theater.services.TheaterService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.ModelAndView

@Controller
class MainController {

    // 이걸써서 서비스에 잇는 로직을 갈아준다
    // 나중에 초기화하라고 lateinit 키워드를 사용한다
    @Autowired
    lateinit var theaterService: TheaterService

    @Autowired
    lateinit var bookingService: BookingService

    @Autowired
    lateinit var seatRepository: SeatRepository

    @RequestMapping("/")
    fun homePage() : ModelAndView =
        ModelAndView("seatBooking", "bean", CheckAvailabilityBackingBean())

    // arrayOf 로 감싸줄 수도 있고 아래방식대로 해도 됨
    @RequestMapping("checkAvailability", method= [RequestMethod.POST])
    fun checkAvailability(bean: CheckAvailabilityBackingBean) : ModelAndView {
        val selectedSeat = theaterService.find(bean.selectedSeatNum, bean.selectedSeatRow)
        val result = bookingService.isSeatFree(selectedSeat)

        bean.result = "Seat $selectedSeat is " + if (result) "available" else "booked"

        // 빈을 받고 업데이트를 했으니, 그대로 쓴다.
        return ModelAndView("seatBooking", "bean", bean)
    }

//    @RequestMapping("bootstrap")
//    fun createInitialData() : ModelAndView {
//        val seats = theaterService.seats
//        seatRepository.saveAll(seats)
//
//        return homePage()
//    }
}
