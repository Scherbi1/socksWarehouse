package pro.sky.sockswarehouse.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.sockswarehouse.model.SocksInStock;
import pro.sky.sockswarehouse.service.SocksInStockService;

import java.util.Collection;

@RestController
@RequestMapping("/socksWarehouse")
public class SocksInStockController {

    private final SocksInStockService socksInStockService;

    public SocksInStockController(SocksInStockService socksInStockService) {
        this.socksInStockService = socksInStockService;
    }
    @Operation(summary = "Получить  общий списко носков",
            responses = {@ApiResponse(
                    responseCode = "200",
                    description = "Выведен весь список носков",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = SocksInStock.class)
                    )
            )}
    )
    @GetMapping("/getAll")
    public ResponseEntity<Collection<SocksInStock>> getAllStudent() {
        return ResponseEntity.ok(socksInStockService.findStocksInStockAll());
    }


    @Operation(summary = "Добавление носков на склад",
            responses = {@ApiResponse(
                    responseCode = "200",
                    description = "Носков добавилось",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = SocksInStock.class)
                    )
            ), @ApiResponse(
                    responseCode = "500",
                    description = "Неправильно введены входные данные!",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE)
            )}
    )
    @PostMapping("/Income")
    public ResponseEntity<?>сreateIncomeSocksInStocks(@RequestBody SocksInStock socksInStock) {
        return socksInStockService.createIncomeSocks(socksInStock);
    }
    @Operation(summary = "Отпуск носков со склада",
            responses = {@ApiResponse(
                    responseCode = "200",
                    description = "Носков отпущено со скалада",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = SocksInStock.class)
                    )
            ), @ApiResponse(
                    responseCode = "500",
                    description = "Неправильно введены входные данные!",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE)
            )}
    )
     @PostMapping("/OutCome")
    public ResponseEntity<?> сreateOutcomeSocksInStocks(@RequestBody SocksInStock socksInStock) {
        return socksInStockService.createOutcomeSocks(socksInStock);
    }

    @Operation(summary = "Редактирование информации о носках",
            responses = {@ApiResponse(
                    responseCode = "200",
                    description = " Ошибка в записи о носках исправлена ",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = SocksInStock.class)
                    )
            ), @ApiResponse(
                    responseCode = "500",
                    description = "Неправильно введены входные данные!",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE)
            )}
    )
    @PutMapping("/edit")
    public ResponseEntity<SocksInStock> editSocksInStock(@RequestBody SocksInStock socksInStock) {
        SocksInStock foundSocks = socksInStockService.editSocks(socksInStock);
        return ResponseEntity.ok(foundSocks);
    }
    @Operation(summary = "Количество носков по заданным параметрам",
            responses = {@ApiResponse(
                    responseCode = "200",
                    description = "число получено",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = SocksInStock.class)
                    )
            ), @ApiResponse(
                    responseCode = "500",
                    description = "Неправильно введены входные данные! Введите '<', '>' или '=' ",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE)
            )}
    )
    @GetMapping("/get/parameters")
    public ResponseEntity<?> getQuantityByColorAndByCottonPart(@RequestParam String color,
                                                               @RequestParam String operation,
                                                               @RequestParam int cottonPart) {
        return socksInStockService.getQuantityByCottonPartAndByColor(color, operation, cottonPart);
    }
    @Operation(summary = "Удаление партии носков",
            responses = {@ApiResponse(
                    responseCode = "200",
                    description = "Носки выброшены на помойку",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = SocksInStock.class)
                    )
            )}
    )
    @DeleteMapping("/delete")
    public ResponseEntity deleteSocksInStocks(@RequestBody SocksInStock socksInStock) {
        socksInStockService.deleteStock(socksInStock);
        return ResponseEntity.ok().build();
    }
}
