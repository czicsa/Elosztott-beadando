package hu.meiit;

import hu.meiit.integration.ExecutionReport;
import hu.meiit.integration.SendEmailService;
import hu.meiit.model.Car;
import hu.meiit.model.EmailDetails;
import hu.meiit.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CarController {

	@Autowired
	private CarRepository repo;

    @Autowired
    private SendEmailService emailService;

    @RequestMapping(value = "/list")
    public ModelAndView getCarListPage() {
        ModelAndView mav = new ModelAndView("list");
        return mav;
    }

    @RequestMapping(value = "/getCars", produces = "application/json")
    @ResponseBody
    public List<Car> getCars() {
        return repo.getCars();
    }

    @RequestMapping(value = "/newcar", method = RequestMethod.GET)
    public ModelAndView addNewCar() {
        ModelAndView mav = new ModelAndView("newcar");
        mav.addObject("car", new Car());
        return mav;
    }

    @RequestMapping(value = "/newcar", method = RequestMethod.POST)
    public ResponseEntity insertCar(@RequestBody Car car) {
        if(!repo.isCarValid(car)){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        repo.addCar(car);
        //ExecutionReport report = emailService.sendEmail(new EmailDetails("Új autó"));

        return new ResponseEntity(HttpStatus.OK);

    }

    @RequestMapping(value = "/modifycar", method = RequestMethod.GET)
    public ModelAndView modifyCarPage() {
        ModelAndView mav = new ModelAndView("modifycar");
        return mav;
    }

    @RequestMapping(value = "/getCarById/{carId}", method = RequestMethod.GET)
    @ResponseBody
    public Car getCarById(@PathVariable int carId) {
        return repo.getCarById(carId);
    }

    @RequestMapping(value = "/modifycar", method = RequestMethod.POST)
    public ResponseEntity modifyCar(@RequestBody Car car) {
        if(!repo.isCarValid(car)){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        repo.editCar(car);
        return new ResponseEntity(HttpStatus.OK);
    }
}
