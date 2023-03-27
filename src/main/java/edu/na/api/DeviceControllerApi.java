package edu.na.api;

import edu.na.dto.DeviceDto;
import edu.na.service.DeviceService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/devices")
public class DeviceControllerApi {
    private final DeviceService deviceService;

    public DeviceControllerApi(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping
    public ResponseEntity<List<DeviceDto>> listDevices(){
        return ResponseEntity.ok(deviceService.findAll());
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

}
