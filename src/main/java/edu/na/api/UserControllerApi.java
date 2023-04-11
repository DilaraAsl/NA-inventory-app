package edu.na.api;

import edu.na.dto.DeviceDto;
import edu.na.service.DeviceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
public class UserControllerApi {
    private final DeviceService deviceService;

    public UserControllerApi(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping("/{id}/devices")
    public ResponseEntity<List<DeviceDto>> getDevicesAssignedToUser(@PathVariable("id") Long userId) {
        List<DeviceDto> devices = deviceService.findDevicesByUserId(userId);
        if (devices != null && !devices.isEmpty()) {
            return ResponseEntity.ok(devices);
        }
        return ResponseEntity.badRequest().body(null);
    }
}
