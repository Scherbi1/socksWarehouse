package pro.sky.sockswarehouse.service;





import com.sun.source.tree.ContinueTree;
import liquibase.pro.packaged.L;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pro.sky.sockswarehouse.model.SocksInStock;
import pro.sky.sockswarehouse.repositories.SocksInStockRepositories;

import java.util.Collection;
import java.util.List;

@Service
public class SocksInStockService {
    private final SocksInStockRepositories socksInStockRepositories;

    public SocksInStockService(SocksInStockRepositories socksInStockRepositories) {
        this.socksInStockRepositories = socksInStockRepositories;
    }

    Logger logger = LoggerFactory.getLogger(SocksInStockService.class);

    public SocksInStock create(SocksInStock socksInStock) {
        logger.info("Dog Shelter Was invoked method for createIncomeSocks");
        if (socksInStock.getCottonPart() > 100 || socksInStock.getCottonPart() < 0
                || socksInStock.getColor()== null  || socksInStock.getQuantity()<1) {
            throw new RuntimeException("НЕПРАВИЛЬНО ЗАПИСАНЫ ВХОДНЫЕ ДАННЫЕ");
        }
        return socksInStockRepositories.save(socksInStock);
    }
    public ResponseEntity<?> createIncomeSocks (SocksInStock socksInStock) {
        logger.info("Dog Shelter Was invoked method for createIncomeSocks ");
        if (socksInStock.getCottonPart() > 100 || socksInStock.getCottonPart() < 0
                || socksInStock.getColor()== null  || socksInStock.getQuantity()<1) {
            throw new RuntimeException("НЕПРАВИЛЬНО ЗАПИСАНЫ ВХОДНЫЕ ДАННЫЕ");
        }
        List<SocksInStock> socksList = (List<SocksInStock>) socksInStockRepositories.findByColorAndCottonPart(socksInStock.getColor(), socksInStock.getCottonPart());
        if (socksList.isEmpty()) {
            return new ResponseEntity<>("Носки не найдены на складе", HttpStatus.BAD_REQUEST);
        }
        SocksInStock foundSocks = socksList.get(0);
        int quantity = foundSocks.getQuantity() + socksInStock.getQuantity();
        foundSocks.setQuantity(quantity);
        return  ResponseEntity.ok(socksInStockRepositories.save(foundSocks));
    }
    public ResponseEntity<?> createOutcomeSocks(SocksInStock socksInStock) {
        logger.info("Dog Shelter Was invoked method for createOutcomeSocks");
        if (socksInStock.getCottonPart() > 100 || socksInStock.getCottonPart() < 0
                || socksInStock.getColor()== null  || socksInStock.getQuantity()<1) {
            throw new RuntimeException("НЕПРАВИЛЬНО ЗАПИСАНЫ ВХОДНЫЕ ДАННЫЕ");
        }
        List<SocksInStock> socksList = (List<SocksInStock>) socksInStockRepositories.findByColorAndCottonPart(socksInStock.getColor(), socksInStock.getCottonPart());
        if (socksList.isEmpty()) {
            return new ResponseEntity<>("Носки не найдены на складе", HttpStatus.BAD_REQUEST);
        }
        SocksInStock foundSocks = socksList.get(0);
        int quantity = foundSocks.getQuantity() - socksInStock.getQuantity();
        if (quantity < 0) {
            return new ResponseEntity<>("Недостаточно ноксов в хранилище", HttpStatus.BAD_REQUEST);
        }
        foundSocks.setQuantity(quantity);
        if (foundSocks.getQuantity() == 0) {
            socksInStockRepositories.delete(foundSocks);
        }

        return  ResponseEntity.ok(socksInStockRepositories.save(foundSocks));
    }

    public Collection<SocksInStock> findStocksInStockAll() {
        logger.info("Dog Shelter Was invoked method for findStocksInStockAll");
        return socksInStockRepositories.findAll();
    }

    public SocksInStock editSocks(SocksInStock socksInStock) {
        logger.info("Dog Shelter Was invoked method for editSocks");
        if (socksInStock.getCottonPart() > 100 || socksInStock.getCottonPart() < 0
                || socksInStock.getColor()== null  || socksInStock.getQuantity()<1) {
            throw new RuntimeException("НЕПРАВИЛЬНО ЗАПИСАНЫ ВХОДНЫЕ ДАННЫЕ");
        }
        return socksInStockRepositories.save(socksInStock);
    }

    public ResponseEntity<?> getQuantityByCottonPartAndByColor(String color, String operation, int cottonPart) {
        logger.info("Dog Shelter Was invoked method for getQuantityByCottonPartAndByColor");
        switch (operation){
                case ">":
                   return new  ResponseEntity<>(String.valueOf
                            (socksInStockRepositories.findByColorAndCottonPartGreaterThan(color, cottonPart)
                                    .stream().mapToInt(SocksInStock::getQuantity).sum()), HttpStatus.OK);

                case "<":
                   return new ResponseEntity<>(String.valueOf
                            (socksInStockRepositories.findByColorAndCottonPartLessThan(color, cottonPart)
                                    .stream().mapToInt(SocksInStock::getQuantity).sum()), HttpStatus.OK);
                case "=":
                   return new ResponseEntity<>(String.valueOf
                            (socksInStockRepositories.findByColorAndCottonPart(color, cottonPart)
                                    .stream().mapToInt(SocksInStock::getQuantity).sum()), HttpStatus.OK);

            default: throw new RuntimeException("НЕПРАВИЛЬНО ЗАПИСАНЫ ВХОДНЫЕ ДАННЫЕ!!! ВВЕДИТЕ '<', '>' или '=' ");
        }
    }

    public void deleteStock( SocksInStock socksInStock) {
        logger.info("Dog Shelter Was invoked method for deleteStock");
        socksInStockRepositories.delete(socksInStock);
    }

}
