package com.example.health_online_backend.controller;

import com.example.health_online_backend.DTO.DoctorResponse;
import com.example.health_online_backend.models.*;
import com.example.health_online_backend.services.DoctorService;
import com.example.health_online_backend.services.MedicineService;
import com.example.health_online_backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    UserService userService;

    @Autowired
    DoctorService doctorService;

    @Autowired
    MedicineService medicineService;

    @GetMapping("get-user")
    ResponseEntity<List<User>> getUser(@RequestParam String email) {
        try {
            List<User> users = userService.getAllUsers(email);
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("edit-user")
    ResponseEntity<String> editUser(@RequestBody User user) {
        try {
            if (user.getFullName() != null && user.getPhoneNumber() != null && user.getPassword() != null) {
                if (UserService.isValidPassword(user.getPassword())) {
                    if (user.getFullName().length() > 4) {
                        User userResponse = userService.getPatientById(user.getId());
                        if (userResponse != null) {
                            userService.updateProfileAdmin(userResponse, user);
                            return new ResponseEntity<>("Update Success", HttpStatus.OK);
                        } else {
                            return new ResponseEntity<>("không tìm thấy bệnh nhân", HttpStatus.INTERNAL_SERVER_ERROR);
                        }
                    }
                    return new ResponseEntity<>("Tên phải có ít nhất 5 kí tự", HttpStatus.UNAUTHORIZED);
                } else {
                    return new ResponseEntity<>("Mật khẩu có ít nhất 5 kí tự và ít nhất 1 chữ cái", HttpStatus.UNAUTHORIZED);
                }
            } else {
                return new ResponseEntity<>("Các thông tin muốn sửa phải có đủ thông tin", HttpStatus.UNAUTHORIZED);
            }


        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("delete-user")
    ResponseEntity<String> deleteUser(@RequestParam String id) {
        try {
            User user = userService.getPatientById(id);

            if (user != null) {
                if (user.getStatus() == 1) {
                    if (userService.reverse(user)) {
                        return new ResponseEntity<>("Khôi phục thành công", HttpStatus.OK);
                    }
                    return new ResponseEntity<>("Khôi phục thất bại", HttpStatus.INTERNAL_SERVER_ERROR);
                }
                if (userService.deleteUser(user)) {
                    return new ResponseEntity<>("Xóa thành công", HttpStatus.OK);
                }
                return new ResponseEntity<>("Xóa thất bại", HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                return new ResponseEntity<>("Không tìm thấy user", HttpStatus.NOT_FOUND);
            }


        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("edit-medical")
    ResponseEntity<String> editMedicine(@RequestBody Medicine medicine) {
        try {
            if (medicine.getName() != null && medicine.getUnit() != null && medicine.getQuantity() >= 0 && medicine.getId() != null) {
                Medicine medicineResponse = medicineService.getMedicineById(medicine.getId());
                if (medicineResponse != null) {
                    medicineService.updateMedicalAdmin(medicineResponse, medicine);
                    return new ResponseEntity<>("Update Success", HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("không tìm thấy thuốc", HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                return new ResponseEntity<>("Các thông tin muốn sửa phải có đủ thông tin", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("delete-medicine")
    ResponseEntity<String> deleteMedical(@RequestParam String id) {
        try {
            Medicine medicine = medicineService.getMedicineById(id);
            if (medicine != null) {
                if (medicine.getStatus() == 1) {
                    if (medicineService.reverse(medicine)) {
                        return new ResponseEntity<>("Khôi phục thành công", HttpStatus.OK);
                    }
                    return new ResponseEntity<>("Khôi phục thất bại", HttpStatus.INTERNAL_SERVER_ERROR);
                }
                if (medicineService.delete(medicine)) {
                    return new ResponseEntity<>("Xóa thành công", HttpStatus.OK);
                }
                return new ResponseEntity<>("Xóa thất bại", HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                return new ResponseEntity<>("Không tìm thấy thuốc", HttpStatus.NOT_FOUND);
            }


        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/get-list-doctor")
    public ResponseEntity<List<Doctor>> findListDoctor(@RequestParam String name) {
        try {
            List<Doctor> doctors = doctorService.findListDoctor(name);
            if (doctors.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(doctors, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/update-doctor")
    public ResponseEntity<String> updateDoctor(@RequestBody Doctor doctor) {
        try {
            if (doctor.getSpecialized() == null || doctor.getDescription() == null || doctor.getPassword() == null || doctor.getName() == null) {
                return new ResponseEntity<>("Vui lòng điền đầy đủ các field", HttpStatus.BAD_REQUEST);
            }
            Doctor oldDoctor = doctorService.getDoctorById(doctor.getId());
            if (oldDoctor == null) {
                return new ResponseEntity<>("Không tìm thấy bác sĩ muốn sửa", HttpStatus.NOT_FOUND);
            }
            doctorService.updateDoctor(oldDoctor, doctor);
            return new ResponseEntity<>("Sửa thành công", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add-doctor")
    public ResponseEntity<String> addDoctor(@RequestBody Doctor doctor) {
        try {
            if (doctor.getEmail() == null || doctor.getSpecialized() == null || doctor.getDescription() == null || doctor.getPassword() == null || doctor.getName() == null) {
                return new ResponseEntity<>("Vui lòng điền đầy đủ các field", HttpStatus.BAD_REQUEST);
            }
            Doctor oldDoctor = doctorService.getDoctorByEmail(doctor.getEmail());
            if (oldDoctor != null) {
                return new ResponseEntity<>("Email bị trùng", HttpStatus.CONFLICT);
            }
            doctorService.addDoctor(doctor);
            return new ResponseEntity<>("Thêm thành công", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("delete-doctor")
    ResponseEntity<String> deleteDoctor(@RequestParam String id) {
        try {
            Doctor doctor = doctorService.getDoctorById(id);
            if (doctor != null) {
                if (doctor.getStatus() == 1) {
                    if (doctorService.reverse(doctor)) {
                        return new ResponseEntity<>("Khôi phục thành công", HttpStatus.OK);
                    }
                    return new ResponseEntity<>("Khôi phục thất bại", HttpStatus.INTERNAL_SERVER_ERROR);
                }
                if (doctorService.delete(doctor)) {
                    return new ResponseEntity<>("Xóa thành công", HttpStatus.OK);
                }
                return new ResponseEntity<>("Xóa thất bại", HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                return new ResponseEntity<>("Không tìm thấy thuốc", HttpStatus.NOT_FOUND);
            }


        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
