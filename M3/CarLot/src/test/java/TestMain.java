
import com.raveenm.carlot.dao.CarLotDao;
import com.raveenm.carlot.dao.CarLotDaoImpl;
import com.raveenm.carlot.dto.Car;
import com.raveenm.carlot.service.CarLotNoSuchCarException;
import com.raveenm.carlot.service.CarLotService;
import com.raveenm.carlot.service.CarLotServiceLayerImpl;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ravee
 */
public class TestMain {
    public static void main(String[] args) {
        CarLotDao dao=new CarLotDaoImpl();
        CarLotService service=new CarLotServiceLayerImpl(dao);
        Car car;
        try {
            car = service.getACar("123");
                    System.out.println("Car Vin is:"+car.getVIN());

        } catch (CarLotNoSuchCarException ex) {
            Logger.getLogger(TestMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
