package edu.na.api;

import edu.na.dto.DeviceDto;
import edu.na.dto.TransactionDto;
import edu.na.service.DeviceService;
import edu.na.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/devices")
public class DeviceControllerApi {
    private final DeviceService deviceService;
    private final TransactionService transactionService;

    public DeviceControllerApi(DeviceService deviceService, TransactionService transactionService) {
        this.deviceService = deviceService;
        this.transactionService = transactionService;
    }

    @GetMapping
    public ResponseEntity<List<DeviceDto>> listDevices(){

        return ResponseEntity.ok(deviceService.listBySerialNo());
    }

    @PostMapping()
    public ResponseEntity<DeviceDto> saveDevice(@Valid @RequestBody DeviceDto deviceDto, BindingResult bindingResult) {

        DeviceDto savedDevice = deviceService.save(deviceDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")  // append id variable
                .buildAndExpand(savedDevice.getId())  // replace it with id
                .toUri();
        return ResponseEntity.created(location).build();

    }

    @GetMapping("/{id}")
    public ResponseEntity<DeviceDto> findByDeviceId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(deviceService.findDevice(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<DeviceDto> deleteDevice(@PathVariable("id") Long id){
        return ResponseEntity.ok(deviceService.delete(id));
    }
    @GetMapping("/add")
    public ResponseEntity<List<DeviceDto>> getDevicesForTransaction(@RequestParam("transactionId") Long transactionId) {
        if (transactionId != null) {
            TransactionDto transaction = transactionService.findById(transactionId);
            if (transaction.getDescription().equals("Assign")) {
                return ResponseEntity.ok(deviceService.findDevicesToCheckIn());
            }
            else return ResponseEntity.ok(deviceService.findDevicesToCheckOut());
        }
        return ResponseEntity.badRequest().build();

    }
    @GetMapping("/graph")
    public Map<String, Map<String,Integer>> getDevicesByMakeAndModel(){
        return deviceService.mapDevicesByMakeModelAndCount();
    }
    @GetMapping("/graph/assigned")
    public Map<String, Map<String,Integer>> getRetrievedDevicesByMakeAndModel(){
        return deviceService.mapAssignedDevicesByMakeModelAndCount();
    }
//@GetMapping("/add")
//public ResponseEntity<List<DeviceDto>> getDevicesForTransaction(@RequestParam("transactionId") Long transactionId, @RequestParam(name = "userId", required = false) Long userId) {
//    if (transactionId != null) {
//        TransactionDto transaction = transactionService.findById(transactionId);
//        if (transaction.getDescription().equals("Assigned")) {
//            return ResponseEntity.ok(deviceService.findDevicesToCheckIn());
//        } else if (transaction.getDescription().equals("Retrieved") && userId != null) {
//            return ResponseEntity.ok(deviceService.findDevicesByUserId(userId));
//        } else {
//            return ResponseEntity.ok(deviceService.findDevicesToCheckOut());
//        }
//    }
//    return ResponseEntity.badRequest().build();
//}

}
