package com.s3ich4n.theater.control

import com.s3ich4n.theater.data.BookingRepository
import com.s3ich4n.theater.data.PerformanceRepository
import com.s3ich4n.theater.data.SeatRepository
import com.s3ich4n.theater.domain.TheaterData
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

    @Autowired
    lateinit var performanceRepository: PerformanceRepository

    @Autowired
    lateinit var bookingRepository: BookingRepository

    @RequestMapping("/")
    fun homePage() : ModelAndView {
        val theaterData = TheaterData()

        val model = mapOf (
            "bean" to CheckAvailabilityBackingBean(),
            "performances" to performanceRepository.findAll(),
            "seatNums" to theaterData.seatNums,
            "seatRows" to theaterData.seatRows,
        )

        return ModelAndView("seatBooking", model)
    }
    // arrayOf 로 감싸줄 수도 있고 아래방식대로 해도 됨
    @RequestMapping("checkAvailability", method= [RequestMethod.POST])
    fun checkAvailability(bean: CheckAvailabilityBackingBean) : ModelAndView {

        val theaterData = TheaterData()

        val selectedSeat = bookingService.findSeat(bean.selectedSeatNum, bean.selectedSeatRow)!!
        val selectedPerformance = performanceRepository.findById(bean.selectedPerformance!!).get()

        bean.seat = selectedSeat
        bean.performance = selectedPerformance

        val result = bookingService.isSeatFree(selectedSeat, selectedPerformance)

        bean.available = result

        if (!result) {
            bean.booking = bookingService.findBooking(selectedSeat, selectedPerformance)
        }

        val model = mapOf (
            "bean" to bean,
            "performances" to performanceRepository.findAll(),
            "seatNums" to theaterData.seatNums,
            "seatRows" to theaterData.seatRows,
        )

        // 빈을 받고 업데이트를 했으니, 그대로 쓴다.
        return ModelAndView("seatBooking", model)
    }

    @RequestMapping("booking", method= [RequestMethod.POST])
    fun bookSeat(bean: CheckAvailabilityBackingBean) : ModelAndView {
        val booking = bookingService.reserveSeat(bean.seat!!, bean.performance!!, bean.customerName)

        return ModelAndView("bookingConfirmed", "booking", booking)
    }

//    @RequestMapping("bootstrap")
//    fun createInitialData() : ModelAndView {
//        val seats = theaterService.seats
//        seatRepository.saveAll(seats)
//
//        return homePage()
//    }
}
