package edu.na.controller;

import edu.na.dto.DeviceDto;
import edu.na.entity.Device;
import edu.na.repository.DeviceRepository;
import edu.na.service.DeviceService;
import edu.na.util.MapperUtil;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
@RequestMapping("/devices")
public class DeviceController {
    private final DeviceService deviceService;
    private final DeviceRepository deviceRepository;
    private final MapperUtil mapperUtil;

    public DeviceController(DeviceService deviceService, DeviceRepository deviceRepository, MapperUtil mapperUtil) {
        this.deviceService = deviceService;
        this.deviceRepository = deviceRepository;
        this.mapperUtil = mapperUtil;
    }

    @GetMapping("/add")
    public String addDevice(Model model) {
        model.addAttribute("device", new DeviceDto());
//        model.addAttribute("devices", deviceService.findAll());
        return "/device/add";
    }

    @PostMapping("/add")
    public String saveDevice(@ModelAttribute("device") DeviceDto deviceDto, Model model) {
        Device newDevice = mapperUtil.convert(deviceDto, new Device());
        newDevice.setCheckMeOut(true);


        deviceRepository.save(newDevice);

        return "redirect:/devices/list";

    }
    @GetMapping("/list")
    public String listDevices(Model model) {
//        model.addAttribute("device", new DeviceDto());
        model.addAttribute("devices", deviceService.findAll());
        return "/device/list";
    }

    @GetMapping("/update/{id}")
    public String updateDevice(@PathVariable("id") Long id, Model model) {

        model.addAttribute("device", deviceService.findDevice(id)); // send the devicedto
//        model.addAttribute("devices", deviceService.findAll());
        return "/device/update";
    }

    @PostMapping("/update/{id}")
    public String saveUpdatedDevice(@ModelAttribute("device") DeviceDto deviceDto, Model model) {
        deviceService.update(deviceDto);

        return "redirect:/devices/list";

    }
    @GetMapping("/delete/{id}")
    public String deleteDevice(@PathVariable("id")Long id, DeviceDto deviceDto){
        deviceService.delete(id);
        return "redirect:/devices/list";
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<DeviceDto> findByDeviceId(@PathVariable("id") Long id) {
//        return ResponseEntity.ok(deviceService.findDevice(id));
//    }
//    @DeleteMapping("/{id}")
//    public ResponseEntity<DeviceDto> deleteDevice(@PathVariable("id") Long id){
//        return ResponseEntity.ok(deviceService.delete(id));
//    }

}

