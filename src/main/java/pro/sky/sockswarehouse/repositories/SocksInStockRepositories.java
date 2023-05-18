package pro.sky.sockswarehouse.repositories;

import liquibase.pro.packaged.S;
import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.sockswarehouse.model.SocksInStock;


import java.util.Collection;

public interface SocksInStockRepositories extends JpaRepository<SocksInStock, Long> {

    Collection<SocksInStock> findByColorAndCottonPartGreaterThan(String color, int cottonPart);

    Collection<SocksInStock> findByColorAndCottonPartLessThan(String color, int cottonPart);

    Collection<SocksInStock> findByColorAndCottonPart(String color, int cottonPart);
}
